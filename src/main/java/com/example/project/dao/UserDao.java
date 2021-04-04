package com.example.project.dao;

import com.example.project.entity.User;
import com.example.project.exception.DaoException;

import java.util.List;
import java.util.Optional;

public interface UserDao {
    List<User> findAllUser() throws DaoException;
    Optional<User> findUserByLoginAndPassword(String login, String password) throws DaoException;
    boolean createUser(User user) throws DaoException;
}
