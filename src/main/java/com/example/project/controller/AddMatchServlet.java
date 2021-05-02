package com.example.project.controller;
import com.example.project.entity.Match;
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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

@WebServlet(value = "/add_match")
public class AddMatchServlet extends HttpServlet{
    private UserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("pages/signIn.jsp");
        requestDispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      // todo сделать чтоб только admin
        String name = request.getParameter("name");
        String player1 = request.getParameter("player1");
        String player2 = request.getParameter("player2");
        String rate1 = request.getParameter("rate1");
        String rate0 = request.getParameter("rate0");
        String rate2 = request.getParameter("rate2");
        String date = request.getParameter("date");
        boolean dataCorrect = true;
        try {
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
                match.setName(name);
                match.setPlayer1(player1);
                match.setPlayer2(player2);
                match.setRate1(Double.parseDouble(rate1));
                match.setRate0(Double.parseDouble(rate0));
                match.setRate2(Double.parseDouble(rate2));
                match.setDate(LocalDate.parse(date));
                userService.createMatch(match);
                response.sendRedirect("menuAdmin");//todo
            } else {
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("pages/menuAdmin.jsp");
                requestDispatcher.forward(request, response);
            }
        } catch (ServiceException e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}
