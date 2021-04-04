package com.example.project.service;

import com.example.project.entity.User;
import com.example.project.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> findAllUser() throws ServiceException;
    Optional<User> findUserByLoginAngPassword(String login, String password) throws ServiceException;
    boolean createUser(User user) throws ServiceException;
}
