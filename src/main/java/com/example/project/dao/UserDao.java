package com.example.project.dao;

import com.example.project.entity.Match;
import com.example.project.entity.User;
import com.example.project.exception.DaoException;

import java.util.List;
import java.util.Optional;

public interface UserDao {
    boolean createMatch(Match match) throws DaoException;
    List<User> findAllUser() throws DaoException;
    Optional<User> findUserByLoginAndPassword(String login, String password) throws DaoException;
    boolean createUser(User user) throws DaoException;
    boolean isExistMailAndLogin(String mail, String login) throws DaoException;
}
