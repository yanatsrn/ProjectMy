package com.example.project.dao.impl;

import com.example.project.dao.UserDao;
import com.example.project.entity.RoleType;
import com.example.project.entity.User;
import com.example.project.exception.DaoException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDaoImpl implements UserDao {
    private static final String URL = "jdbc:mysql://localhost/project?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "root";
    private static final String FIND_ALL_USERS = "SELECT * FROM project.users";
    private static final String FIND_USER_BY_LOGIN_AND_PASSWORD = "SELECT * FROM project.users WHERE login = ? AND password = ?";
    private static final String CREATE_NEW_USER = "INSERT INTO `project.users` (`login`, `password`, `role`, `surname`, `name`, `age`, `phone`, `mail`) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

    @Override
    public List<User> findAllUser() throws DaoException {
        List<User> users =  new ArrayList<>();
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
                resultSet.close();
                preparedStatement.close();
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
            try { //только connection или все ?
                resultSet.close();
                preparedStatement.close();
                connection.close();
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
        ResultSet resultSet = null;
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
                resultSet.close();
                preparedStatement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace(); // todo
            }
        }
        return isAdd;
    }
}
