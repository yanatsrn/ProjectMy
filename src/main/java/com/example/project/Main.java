package com.example.project;

import com.example.project.dao.UserDao;
import com.example.project.dao.impl.UserDaoImpl;
import com.example.project.entity.Match;
import com.example.project.exception.DaoException;
import com.example.project.exception.ServiceException;
import com.example.project.service.UserService;
import com.example.project.service.impl.UserServiceImpl;
import com.example.project.util.Report;
import com.example.project.validator.UserValidator;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {


    public static void main(String[] args) throws DaoException, ParseException, ServiceException {

        UserService userService = new UserServiceImpl();


    }
}
