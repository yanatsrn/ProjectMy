package com.example.project;

import com.example.project.exception.DaoException;
import com.example.project.exception.ServiceException;
import com.example.project.service.UserService;
import com.example.project.service.impl.UserServiceImpl;

import java.text.ParseException;

public class Main {


    public static void main(String[] args) throws DaoException, ParseException, ServiceException {

        UserService userService = new UserServiceImpl();


    }
}
