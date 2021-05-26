package com.example.project.controller;

import com.example.project.entity.RoleType;
import com.example.project.entity.User;
import com.example.project.service.UserService;
import com.example.project.service.impl.UserServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/graphics")
public class GraficsServlet extends HttpServlet {
    private UserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = new User();
        response.sendRedirect("pages/graphics.jsp");
       /* try {
            if (user.getRoleType().equals(RoleType.USER)) {
            response.sendRedirect("pages/diagram.jsp");
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("pages/diagram.jsp");
            requestDispatcher.forward(request, response);
            }
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }*/
    }
}
