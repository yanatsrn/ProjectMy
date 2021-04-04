package com.example.project.controller;

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
import java.util.Optional;

@WebServlet(value = "/sign_in")
public class SignInServlet extends HttpServlet {
    private UserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("pages/signUp.jsp");
        requestDispatcher.forward(request, response);
    }// todo may be removed


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        boolean validLogin = UserValidator.isValidLogin(login);
        boolean validPassword = UserValidator.isValidPassword(password);
        boolean dataCorrect = true;
        if (!validLogin || !validPassword) {
            // todo вернуься на странцу signIn и вывести ошибки
            dataCorrect = false;
        }
        if (dataCorrect) {
            try {
                Optional<User> userOptional = userService.findUserByLoginAngPassword(login, password);
                if (userOptional.isPresent()) {
                    response.sendRedirect("pages/main.jsp");
                } else {
                    //todo вернуься на странцу signIn и вывести ошибки пользователя нет
                }

            } catch (ServiceException e) {
                //todo 505
            }

        }
    }
}
