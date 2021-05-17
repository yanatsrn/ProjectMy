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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;

@WebServlet(value = "/sign_in")
public class SignInServlet extends HttpServlet {
    private UserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("pages/signIn.jsp");
        requestDispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        HttpSession session = request.getSession();
        boolean dataCorrect = true;
        if (!UserValidator.isValidSignIn(login, password)) {
            request.setAttribute("errorSignInLoginAndPassword", "Неверный логин или пароль!");
            dataCorrect = false;
        }
        if (dataCorrect) {
            try {
                Optional<User> userOptional = userService.findUserByLoginAngPassword(login, password);
                if (userOptional.isPresent()) {
                    session.setAttribute("user", userOptional.get());
                    response.sendRedirect("pages/main.jsp"); // todo show all matches
                } else {
                    request.setAttribute("errorSignInUserNotFound", "Такого пользователя не существует!");
                    RequestDispatcher requestDispatcher = request.getRequestDispatcher("pages/signIn.jsp");
                    requestDispatcher.forward(request, response);
                }
            } catch (ServiceException e) {
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            } 
        } else {
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("pages/signIn.jsp");
            requestDispatcher.forward(request, response);
        }
    }
}
