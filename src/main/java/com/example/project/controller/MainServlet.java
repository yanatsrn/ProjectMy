package com.example.project.controller;

import com.example.project.entity.User;
import com.example.project.exception.ServiceException;
import com.example.project.service.UserService;
import com.example.project.service.impl.UserServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(value = "/show_all_users")
public class MainServlet extends HttpServlet {
    private UserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<User> allUser = null;
        try {
            allUser = userService.findAllUser();
        } catch (ServiceException e) {
            // todo переход на 505
        }
        request.setAttribute("users", allUser);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("pages/main.jsp");
        requestDispatcher.forward(request, response);
    }
}
