package com.example.project.controller;

import com.example.project.entity.Match;
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
import java.util.Optional;

@WebServlet(value = "/method")
public class MethodServlet extends HttpServlet {
    private UserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String matchIdString = request.getParameter("match_id");
        String player = request.getParameter("player"); // todo valid
        try {
            Long matchId = Long.valueOf(matchIdString);
            Optional<Match> optionalMatch = userService.searchMatchById(matchId);
            request.setAttribute("match", optionalMatch.get());
            double percent = userService.method(player);
            request.setAttribute("percent", percent);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("pages/method.jsp");
            requestDispatcher.forward(request, response);
        } catch (ServiceException | NumberFormatException e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}
