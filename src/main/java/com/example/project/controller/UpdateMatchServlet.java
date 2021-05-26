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
import java.time.LocalDate;
import java.util.Optional;

@WebServlet("/update_match")
public class UpdateMatchServlet extends HttpServlet {
    private UserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String matchIdString = request.getParameter("matchId");
        try {
            Long matchId = Long.valueOf(matchIdString);
            Optional<Match> optionalMatch = userService.searchMatchById(matchId);
            request.setAttribute("match", optionalMatch.get());
        } catch (ServiceException | NumberFormatException e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("pages/updateMatch.jsp");
        requestDispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user.getRoleType().equals(RoleType.ADMIN)) {
            String idString = request.getParameter("id");
            String name = request.getParameter("name");
            String player1 = request.getParameter("player1");
            String player2 = request.getParameter("player2");
            String rate1 = request.getParameter("rate1");
            String rate0 = request.getParameter("rate0");
            String rate2 = request.getParameter("rate2");
            String date = request.getParameter("date");
            boolean dataCorrect = true;
            try {
                Long id = Long.valueOf(idString);
                if (!UserValidator.isValidName(name)) {
                    request.setAttribute("errorName", "Проверьте корректность названия игры");
                    dataCorrect = false;
                }
                if (!UserValidator.isValidPlayers(player1, player2)) {
                    request.setAttribute("errorPlayers", "Проверьте корректность имен игроков");
                    dataCorrect = false;
                }
                if (!UserValidator.isValidRates(rate1, rate0, rate2)) {
                    request.setAttribute("errorRates", "Проверьте корректность коэффициентов");
                    dataCorrect = false;
                }
                if (!UserValidator.isValidDate(date)) {
                    request.setAttribute("errorDate", "Проверьте корректность даты");
                    dataCorrect = false;
                }
                if (dataCorrect) {
                    Match match = new Match();
                    match.setSportId(id);
                    match.setName(name);
                    match.setPlayer1(player1);
                    match.setPlayer2(player2);
                    match.setRate1(Double.parseDouble(rate1));
                    match.setRate0(Double.parseDouble(rate0));
                    match.setRate2(Double.parseDouble(rate2));
                    match.setDate(LocalDate.parse(date));
                    userService.updateMatch(match);
                    response.sendRedirect("pages/main.jsp");
                } else {
                    RequestDispatcher requestDispatcher = request.getRequestDispatcher("pages/addMatch.jsp");
                    requestDispatcher.forward(request, response);
                }
            } catch (ServiceException e) {
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }
        } else {
            request.setAttribute("errorRole", "Нет прав");
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("pages/addMatch.jsp");
            requestDispatcher.forward(request, response);
        }
    }
}
