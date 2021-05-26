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

@WebServlet(value = "/search_match_by_id")
public class SearchMatchByIdServlet extends HttpServlet {
    UserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String matchIdString = request.getParameter("matchId");
        try {
            Long matchId = Long.valueOf(matchIdString);
            Optional<Match> matchOptional = userService.searchMatchById(matchId);
            request.setAttribute("match", matchOptional.get());
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("pages/main.jsp");
            requestDispatcher.forward(request, response);
        } catch (ServiceException | NumberFormatException e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}
