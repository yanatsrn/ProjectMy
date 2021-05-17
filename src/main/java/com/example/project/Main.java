package com.example.project;

import com.example.project.dao.UserDao;
import com.example.project.dao.impl.UserDaoImpl;
import com.example.project.entity.Match;
import com.example.project.entity.User;
import com.example.project.exception.DaoException;
import com.example.project.exception.ServiceException;
import com.example.project.service.UserService;
import com.example.project.service.impl.UserServiceImpl;
import com.example.project.validator.UserValidator;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {


    public static void main(String[] args) throws DaoException, ParseException, ServiceException {
        UserDao userDao = new UserDaoImpl();
        UserService userService = new UserServiceImpl();
        Optional<User> admin = userService.findUserByLoginAngPassword("Admin", "Yana@221");
        System.out.println(admin.get().getRoleType().toString().equals("ADMIN"));

    }
}
