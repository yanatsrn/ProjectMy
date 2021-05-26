package com.example.project.controller;

import com.example.project.entity.Match;
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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@WebServlet(value = "/search_match_by_name")
public class SearchMatchByNameServlet extends HttpServlet {
    UserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String matchNameString = request.getParameter("matchName");
        boolean dataCorrect = true;
        if (!UserValidator.isValidName(matchNameString)) {
            request.setAttribute("errorSearch", "Неверное название!");
            dataCorrect = false;
        }
        if (dataCorrect) {
            try {
                List<Match> matches = userService.searchMatchByName(matchNameString);
                if (matches != null) {
                    request.setAttribute("matches", matches);
                    RequestDispatcher requestDispatcher = request.getRequestDispatcher("pages/main.jsp");
                    requestDispatcher.forward(request, response);
                } else {
                    request.setAttribute("errorMatchNotFound", "Такого вида спорта не существует!");
                    RequestDispatcher requestDispatcher = request.getRequestDispatcher("pages/main.jsp");
                    requestDispatcher.forward(request, response);
                }
            } catch (ServiceException e) {
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }
        }
    }
}

