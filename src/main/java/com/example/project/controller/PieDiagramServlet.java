package com.example.project.controller;

import com.example.project.entity.Match;
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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(value = "/diagram")
public class PieDiagramServlet extends HttpServlet {
    private UserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user.getRoleType().equals(RoleType.USER)) {
            try {
                List<Match> matches = userService.showAllMatches();
                List<String> matchesName = new ArrayList<>();
                Map<String, Integer> map = new HashMap<>();
                for (Match match : matches) {
                    matchesName.add(match.getName());
                }
                for (String match : matchesName) {
                    map.merge(match, 1, Integer::sum);
                }
                request.setAttribute("statisticTeamValues", map);
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("pages/diagram.jsp");
                requestDispatcher.forward(request, response);
            } catch (Exception e) {
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }
        }  else {
            request.setAttribute("errorRole", "Нет прав");
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("pages/main.jsp");
            requestDispatcher.forward(request, response);
        }
    }
}
