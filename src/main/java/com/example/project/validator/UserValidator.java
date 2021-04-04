package com.example.project.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserValidator {
    private static final Pattern AGE_VALIDATOR = Pattern.compile("\\d+");

    private UserValidator() {
    }

    public static boolean isValidLogin(String login) {
        boolean isValid = true;
        if (login == null && login.trim().isEmpty()) {
            isValid = false;
        }
        return isValid;
    }

    public static boolean isValidPassword(String password) {
        boolean isValid = true;
        if (password == null && password.trim().isEmpty()) {
            isValid = false;
        }
        return isValid;
    }



    public static boolean createUser(String login, String password, String surname, String name, String age, String phone, String mail){
        boolean createUser = true;
        if (login == null && login.trim().isEmpty()) {
            createUser = false;
        }
        if (password == null && password.trim().isEmpty()) {
            createUser = false;
        }
        if (surname == null && surname.trim().isEmpty()) {
            createUser = false;
        }
        if (name == null && name.trim().isEmpty()) {
            createUser = false;
        }
        Matcher matcher = AGE_VALIDATOR.matcher(age);
        if (!matcher.matches()) {
            createUser = false;
        }
        if (phone == null && phone.trim().isEmpty()) {
            createUser = false;
        }
        if (mail == null && mail.trim().isEmpty()) {
            createUser = false;
        }

        return createUser;
    }
}
