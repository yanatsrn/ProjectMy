package com.example.project.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserValidator {
    private static final Pattern NUMBER_PATTERN = Pattern.compile("\\d+");
    private static final Pattern LOGIN_PATTERN = Pattern.compile("^[a-zA-Z][a-zA-Z0-9-_\\.]{1,20}$");
    private static final Pattern STRING_PATTERN = Pattern.compile("^[\\p{L}]+$");
    //проверка на строку
    private static final Pattern PASSWORD_PATTERN = Pattern
            .compile("^.*(?=.{8,})(?=..*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).*$");
    /* должен включать хотя бы одну букву в верхнем и нижнем регистре, хотя бы одину цифру,
         хотя бы один специальный символ ("@", "#". "$", "%", "^", "&", "( "или") ",
         без пробелов, табуляции и т. Д и не менее 8 символов*/
    private static final Pattern MAIL_PATTERN = Pattern
            .compile("^[-0-9a-zA-Z.+_]+@[-0-9a-zA-Z.+_]+\\.[a-zA-Z]{2,4}");
    private static final Pattern PHONE_PATTERN = Pattern
            .compile("^(\\+375|80)(29|25|44|33)(\\d{3})(\\d{2})(\\d{2})$");
    //(\s*)?(\+)?([- _():=+]?\d[- _():=+]?){10,14}(\s*)? c пробелами считывалось
    private static final Pattern PLAYER_NAME = Pattern.compile(("^[\\p{L}\\-]{0,}?$"));
    //название игроков с буквами, дефисами, без пробелов
    private static final Pattern RATE_PATTERN = Pattern.compile("\\-?\\d+(\\.\\d{0,})?");
    //целые числа и числа с плавающей ТОЧКОЙ
    private static Pattern DATE_PATTERN = Pattern.compile(
            "^((2000|2400|2800|(19|2[0-9](0[48]|[2468][048]|[13579][26])))-02-29)$"
                    + "|^(((19|2[0-9])[0-9]{2})-02-(0[1-9]|1[0-9]|2[0-8]))$"
                    + "|^(((19|2[0-9])[0-9]{2})-(0[13578]|10|12)-(0[1-9]|[12][0-9]|3[01]))$"
                    + "|^(((19|2[0-9])[0-9]{2})-(0[469]|11)-(0[1-9]|[12][0-9]|30))$");
    //дата

    private UserValidator() {
    }

    public static boolean isValidSignIn(String login, String password) {
        boolean isValid = true;
        if (login == null || login.isEmpty() || password == null || password.isEmpty()) {
            isValid = false;
        }
        return isValid;
    }

    public static boolean isValidLogin(String login) {
        boolean isValid = true;
        if (login == null || login.isEmpty() || !LOGIN_PATTERN.matcher(login).matches()) {
            isValid = false;
        }
        return isValid;
    }
    public static boolean isValidPasswordAndRepeatPassword(String password, String repeat_password) {
        boolean isValid = true;
        if (password == null || password.isEmpty() || !PASSWORD_PATTERN.matcher(password).matches()
        || !repeat_password.equals(password)) {
            isValid = false;
        }
        return isValid;
    }

    public static boolean isValidSurnameAndName(String surname, String name) {
        boolean isValid = true;
        if (surname == null || surname.isEmpty() || !STRING_PATTERN.matcher(surname).matches() || name == null || name.isEmpty() || !STRING_PATTERN.matcher(name).matches()) {
            isValid = false;
        }
        return isValid;
    }

    public static boolean isValidAge(String age) {
        boolean isValid = true;
        if (age == null || age.isEmpty() || !NUMBER_PATTERN.matcher(age).matches()) {
            isValid = false;
        }
        return isValid;
    }

    public static boolean isValidPhone(String phone) {
        boolean isValid = true;
        if (phone == null || phone.isEmpty() || !PHONE_PATTERN.matcher(phone).matches()) {
            isValid = false;
        }
        return isValid;
    }

    public static boolean isValidMail(String mail) {
        boolean isValid = true;
        if (mail == null || mail.isEmpty() || !MAIL_PATTERN.matcher(mail).matches()) {
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
        Matcher matcher = NUMBER_PATTERN.matcher(age);
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

    public static boolean isValidName(String name) {
        boolean isValid = true;
        if (name == null || name.isEmpty() || !STRING_PATTERN.matcher(name).matches()) {
            isValid = false;
        }
        return isValid;
    }

    public static boolean isValidPlayers(String player1, String player2) {
        boolean isValid = true;
        if (player1 == null || player2 == null || player1.isEmpty() || player2.isEmpty() || !PLAYER_NAME.matcher(player1).matches() || !PLAYER_NAME.matcher(player2).matches()) {
            isValid = false;
        }
        return isValid;
    }

    public static boolean isValidRates(String rate1, String rate0, String rate2) {
        boolean isValid = true;
        if (rate1 == null || rate0 == null || rate2 == null || rate1.isEmpty() || rate0.isEmpty() || rate2.isEmpty() || !RATE_PATTERN.matcher(rate1).matches() || !RATE_PATTERN.matcher(rate0).matches() || !RATE_PATTERN.matcher(rate2).matches()) {
            isValid = false;
        }
        return isValid;
    }

    public static boolean isValidDate(String date) {
        boolean isValid = true;
        if (date == null || date.isEmpty() || !DATE_PATTERN.matcher(date).matches()) {
            isValid = false;
        }
        return isValid;
    }
}
