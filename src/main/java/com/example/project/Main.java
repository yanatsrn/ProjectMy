package com.example.project;

import com.example.project.dao.UserDao;
import com.example.project.dao.impl.UserDaoImpl;
import com.example.project.entity.User;
import com.example.project.exception.DaoException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    private static final Pattern AGE_VALIDATOR = Pattern.compile("\\d+");

    public static void main(String[] args) {

        String a = "dvds";
        String v = "32";
        System.out.println(AGE_VALIDATOR.matcher(a).matches());
        System.out.println(AGE_VALIDATOR.matcher(v).matches());

    }
}
