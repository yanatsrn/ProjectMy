package com.example.project.dao;

import com.example.project.entity.Match;
import com.example.project.entity.Player;
import com.example.project.entity.User;
import com.example.project.exception.DaoException;
import com.example.project.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public interface UserDao {
    boolean createMatch(Match match) throws DaoException;
    List<User> findAllUser() throws DaoException;
    Optional<User> findUserByLoginAndPassword(String login, String password) throws DaoException;
    boolean createUser(User user) throws DaoException;
    boolean isExistMailAndLogin(String mail, String login) throws DaoException;
    List<Match> showAllMatches() throws DaoException;
    boolean deleteMatch(Long id) throws DaoException;
    Optional<Match> searchMatchById(Long id) throws DaoException;
    List<Match> searchMatchByName(String name) throws DaoException;
    List<Match> sortMatchByName() throws DaoException;
    List<Match> sortMatchByDate() throws DaoException;
    boolean updateMatch(Match match) throws DaoException;
    List<Player> method(String player) throws DaoException;
    boolean makeABet(User user) throws DaoException;
}
