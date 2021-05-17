package com.example.project.controller;

import com.example.project.entity.RoleType;
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
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(value = "/search_match_by_id")
public class SearchMatchByIdServlet extends HttpServlet {
    UserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("pages/main.jsp");
        requestDispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String matchIdString = request.getParameter("matchId");
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
            try {
                Long matchId = Long.valueOf(matchIdString); // todo реши как делать через ваоидацию или так как есть
                userService.searchMatchById(matchId);

                response.sendRedirect("pages/main.jsp");
            } catch (ServiceException | NumberFormatException e) {
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }
    }
}
