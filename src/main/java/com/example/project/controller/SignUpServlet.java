package com.example.project.controller;

import com.example.project.entity.RoleType;
import com.example.project.entity.User;
import com.example.project.exception.ServiceException;
import com.example.project.service.UserService;
import com.example.project.service.impl.UserServiceImpl;
import com.example.project.validator.UserValidator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/sign_up")
public class SignUpServlet extends HttpServlet {
    private UserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("pages/signUp.jsp");
        requestDispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String repeatPassword = request.getParameter("repeat_password");
        String surname = request.getParameter("surname");
        String name = request.getParameter("name");
        String age = request.getParameter("age");
        String phone = request.getParameter("phone");
        String mail = request.getParameter("mail");
        boolean dataCorrect = true;
        try {
            if (!UserValidator.isValidLogin(login)) {
                request.setAttribute("errorLogin", "Проверьте корректность логина");
                dataCorrect = false;
            }
            if (!UserValidator.isValidPasswordAndRepeatPassword(password, repeatPassword)) {
                request.setAttribute("errorPassword", "Проверьте корректность пароля");
                dataCorrect = false;
            }
            if (!UserValidator.isValidSurnameAndName(surname, name)) {
                request.setAttribute("errorInitials",  "Проверьте корректность ининциалов");
                dataCorrect = false;
            }
            if (!UserValidator.isValidAge(age)) {
                request.setAttribute("errorAge", "Проверьте корректность возраста");
                dataCorrect = false;
            }
            if (!UserValidator.isValidPhone(phone)) {
                request.setAttribute("errorPhone", "Проверить корректность телефона");
                dataCorrect = false;
            }
            if (!UserValidator.isValidMail(mail)) {
                request.setAttribute("errorMail", "Проверить корректность почты");
                dataCorrect = false;
            }
            if (dataCorrect) {
                boolean isExist = userService.isExistMailAndLogin(mail, login);
                if (isExist) {
                    request.setAttribute("errorExist", "Такая почта или логин существуют");
                    dataCorrect = false;
                }
            }
            if (dataCorrect) {
                User user = new User();
                user.setLogin(login);
                user.setPassword(password);
                user.setRoleType(RoleType.USER);
                user.setSurname(surname);
                user.setName(name);
                user.setAge(Integer.parseInt(age));
                user.setPhone(phone);
                user.setMail(mail);
                userService.createUser(user);
                response.sendRedirect("sign_in");
            } else {
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("pages/signUp.jsp");
                requestDispatcher.forward(request, response);
            }
        } catch (ServiceException e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}
