package com.example.project.dao.impl;

import com.example.project.dao.UserDao;
import com.example.project.entity.Match;
import com.example.project.entity.RoleType;
import com.example.project.entity.User;
import com.example.project.exception.DaoException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.time.LocalDate;

public class UserDaoImpl implements UserDao {
    private static final String URL = "jdbc:mysql://localhost/project?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "root";
    private static final String FIND_ALL_USERS = "SELECT * FROM project.users";
    private static final String FIND_USER_BY_LOGIN_AND_PASSWORD = "SELECT * FROM project.users WHERE login = ? AND password = ?";
    private static final String CREATE_NEW_USER = "INSERT INTO `users` (`login`, `password`, `role`, `surname`, `name`, `age`, `phone`, `mail`) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String FIND_MAIL_AND_LOGIN = "SELECT * FROM project.users WHERE mail = ? AND login = ?";
    private static final String CREATE_NEW_MATCH = "INSERT INTO project.sports (`name`, `player1`, `player2`, `rate1`, `rate0`, `rate2`, `date`) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String SHOW_ALL_MATCHES = "SELECT * FROM project.sports";
    private static final String DELETE_MATCH = "DELETE FROM project.sports WHERE `sport_id` = ?";
    private static final String SEARCH_MATCH_BY_ID = "SELECT * FROM project.sports WHERE `sport_id`=?";
    private static final String SEARCH_MATCH_BY_NAME = "SELECT * FROM project.sports WHERE `name` = ?";
    private static final String SORT_MATCH_BY_NAME = "SELECT * FROM project.sports ORDER BY `name`";
    private static final String SORT_MATCH_BY_DATE = "SELECT * FROM project.sports ORDER BY `date`";

    @Override
    public List<User> findAllUser() throws DaoException {
        List<User> users = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            preparedStatement = connection.prepareStatement(FIND_ALL_USERS);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong(1));
                user.setLogin(resultSet.getString(2));
                user.setPassword(resultSet.getString(3));
                user.setRoleType(RoleType.valueOf(resultSet.getString(4).toUpperCase()));
                user.setSurname(resultSet.getString(5));
                user.setName(resultSet.getString(6));
                user.setAge(resultSet.getInt(7));
                user.setPhone(resultSet.getString(8));
                user.setMail(resultSet.getString(9));
                users.add(user);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            try { //только connection или все ?
                if (resultSet != null)
                    resultSet.close();
                if (preparedStatement != null)
                    preparedStatement.close();
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                e.printStackTrace(); // todo
            }
        }
        return users;
    }

    @Override
    public Optional<User> findUserByLoginAndPassword(String login, String password) throws DaoException {
        Optional<User> optionalUser = Optional.empty();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            preparedStatement = connection.prepareStatement(FIND_USER_BY_LOGIN_AND_PASSWORD);
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong(1));
                user.setLogin(resultSet.getString(2));
                user.setPassword(resultSet.getString(3));
                user.setRoleType(RoleType.valueOf(resultSet.getString(4).toUpperCase()));
                user.setSurname(resultSet.getString(5));
                user.setName(resultSet.getString(6));
                user.setAge(resultSet.getInt(7));
                user.setPhone(resultSet.getString(8));
                user.setMail(resultSet.getString(9));
                optionalUser = Optional.ofNullable(user); //принимает в себя null, но проверка раньше на это есть
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace(); // todo
            }
        }
        return optionalUser;

    }

    @Override
    public boolean createUser(User user) throws DaoException {
        boolean isAdd;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            preparedStatement = connection.prepareStatement(CREATE_NEW_USER);
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getRoleType().name());
            preparedStatement.setString(4, user.getSurname());
            preparedStatement.setString(5, user.getName());
            preparedStatement.setInt(6, user.getAge());
            preparedStatement.setString(7, user.getPhone());
            preparedStatement.setString(8, user.getMail());

            isAdd = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            try { //только connection или все ?
                if (preparedStatement != null)
                    preparedStatement.close();
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                e.printStackTrace(); // todo
            }
        }
        return isAdd;
    }

    @Override
    public boolean isExistMailAndLogin(String mail, String login) throws DaoException {
        boolean isExist = false;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            preparedStatement = connection.prepareStatement(FIND_MAIL_AND_LOGIN);
            preparedStatement.setString(1, mail);
            preparedStatement.setString(2, login);

            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                isExist = true;
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace(); // todo
            }
        }
        return isExist;
    }

    @Override
    public boolean createMatch(Match match) throws DaoException {
        boolean isAdd;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            preparedStatement = connection.prepareStatement(CREATE_NEW_MATCH);
            preparedStatement.setString(1, match.getName());
            preparedStatement.setString(2, match.getPlayer1());
            preparedStatement.setString(3, match.getPlayer2());
            preparedStatement.setDouble(4, match.getRate1());
            preparedStatement.setDouble(5, match.getRate0());
            preparedStatement.setDouble(6, match.getRate2());
            preparedStatement.setDate(7, Date.valueOf(match.getDate()));

            isAdd = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
                if (preparedStatement != null)
                    preparedStatement.close();
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return isAdd;
    }

    @Override
    public List<Match> showAllMatches() throws DaoException {
        List<Match> matches = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            preparedStatement = connection.prepareStatement(SHOW_ALL_MATCHES);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Match match = new Match();
                match.setSportId(resultSet.getLong(1));
                match.setName(resultSet.getString(2));
                match.setPlayer1(resultSet.getString(3));
                match.setPlayer2(resultSet.getString(4));
                match.setRate1(resultSet.getDouble(5));
                match.setRate0(resultSet.getDouble(6));
                match.setRate2(resultSet.getDouble(7));
                match.setDate(resultSet.getDate(8).toLocalDate());
                matches.add(match);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
                if (resultSet != null)
                    resultSet.close();
                if (preparedStatement != null)
                    preparedStatement.close();
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return matches;
    }

    @Override
    public boolean deleteMatch(Long id) throws DaoException {
        boolean isDeleted = false;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            preparedStatement = connection.prepareStatement(DELETE_MATCH);
            preparedStatement.setLong(1, id);
            isDeleted = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
                if (preparedStatement != null)
                    preparedStatement.close();
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return isDeleted;
    }

    @Override
    public Optional<Match> searchMatchById(Long id) throws DaoException {
        Optional<Match> optionalMatch = Optional.empty();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            preparedStatement = connection.prepareStatement(SEARCH_MATCH_BY_ID);
            preparedStatement.setLong(1, id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Match match = new Match();
                match.setSportId(resultSet.getLong(1));
                match.setName(resultSet.getString(2));
                match.setPlayer1(resultSet.getString(3));
                match.setPlayer2(resultSet.getString(4));
                match.setRate1(resultSet.getDouble(5));
                match.setRate0(resultSet.getDouble(6));
                match.setRate2(resultSet.getDouble(7));
                match.setDate(resultSet.getDate(8).toLocalDate());
                optionalMatch = Optional.ofNullable(match);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return optionalMatch;
    }

    @Override
    public List<Match> searchMatchByName(String name) throws DaoException {
        List<Match> matches = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            preparedStatement = connection.prepareStatement(SEARCH_MATCH_BY_NAME);
            preparedStatement.setString(1, name);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Match match = new Match();
                match.setSportId(resultSet.getLong(1));
                match.setName(resultSet.getString(2));
                match.setPlayer1(resultSet.getString(3));
                match.setPlayer2(resultSet.getString(4));
                match.setRate1(resultSet.getDouble(5));
                match.setRate0(resultSet.getDouble(6));
                match.setRate2(resultSet.getDouble(7));
                match.setDate(resultSet.getDate(8).toLocalDate());
                matches.add(match);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
                if (resultSet != null)
                    resultSet.close();
                if (preparedStatement != null)
                    preparedStatement.close();
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return matches;
    }

    @Override
    public List<Match> sortMatchByName() throws DaoException {
        List<Match> matches = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            preparedStatement = connection.prepareStatement(SORT_MATCH_BY_NAME);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Match match = new Match();
                match.setSportId(resultSet.getLong(1));
                match.setName(resultSet.getString(2));
                match.setPlayer1(resultSet.getString(3));
                match.setPlayer2(resultSet.getString(4));
                match.setRate1(resultSet.getDouble(5));
                match.setRate0(resultSet.getDouble(6));
                match.setRate2(resultSet.getDouble(7));
                match.setDate(resultSet.getDate(8).toLocalDate());
                matches.add(match);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
                if (resultSet != null)
                    resultSet.close();
                if (preparedStatement != null)
                    preparedStatement.close();
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return matches;
    }

    @Override
    public List<Match> sortMatchByDate() throws DaoException {
        List<Match> matches = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            preparedStatement = connection.prepareStatement(SORT_MATCH_BY_DATE);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Match match = new Match();
                match.setSportId(resultSet.getLong(1));
                match.setName(resultSet.getString(2));
                match.setPlayer1(resultSet.getString(3));
                match.setPlayer2(resultSet.getString(4));
                match.setRate1(resultSet.getDouble(5));
                match.setRate0(resultSet.getDouble(6));
                match.setRate2(resultSet.getDouble(7));
                match.setDate(resultSet.getDate(8).toLocalDate());
                matches.add(match);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
                if (resultSet != null)
                    resultSet.close();
                if (preparedStatement != null)
                    preparedStatement.close();
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return matches;
    }
}

