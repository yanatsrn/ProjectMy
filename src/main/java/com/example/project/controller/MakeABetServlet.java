package com.example.project.controller;

import com.example.project.entity.Match;
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
import java.util.Optional;

@WebServlet("/make_a_bet")
public class MakeABetServlet extends HttpServlet {
    private UserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String IdString = request.getParameter("matchId");
        try {
            Long id = Long.valueOf(IdString);
            Optional<Match> optionalMatch = userService.searchMatchById(id);
            request.setAttribute("match", optionalMatch.get());
            request.setAttribute("matchId", id);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("pages/makeABet.jsp");
            requestDispatcher.forward(request, response);
        } catch (ServiceException | NumberFormatException e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user.getRoleType().equals(RoleType.USER)) {
            String idString = request.getParameter("id");
            String sumString = request.getParameter("sum");
            String idMatchString = request.getParameter("matchId");
            boolean dataCorrect = true;
            try {
                Long id = Long.valueOf(idString);
                Integer matchId = Integer.valueOf(idMatchString);
//                if () todo validator sum
                if (dataCorrect) {
                    User users = new User();
                    users.setId(Long.valueOf(id));
                    users.setSum(Double.parseDouble(sumString));
                    users.setMatchId(matchId);
                    userService.makeABet(users);
                    response.sendRedirect("pages/main.jsp");
                } else {
                    RequestDispatcher requestDispatcher = request.getRequestDispatcher("pages/main.jsp");
                    requestDispatcher.forward(request, response);
                }
            } catch (ServiceException e) {
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }
        } else {
            request.setAttribute("errorRole", "Нет прав");
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("pages/signIn.jsp");
            requestDispatcher.forward(request, response);
        }
    }
}
