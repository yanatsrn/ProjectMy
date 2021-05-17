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

@WebServlet(value = "/delete_match")
public class DeleteMatchServlet extends HttpServlet {
    UserService userService = new UserServiceImpl();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String matchIdString = request.getParameter("matchId");
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user.getRoleType().equals(RoleType.ADMIN)) {
            try {
                Long matchId = Long.valueOf(matchIdString); // todo реши как делать через ваоидацию или так как есть
                userService.deleteMatch(matchId);
                response.sendRedirect("pages/main.jsp");
            } catch (ServiceException | NumberFormatException e) {
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }
        } else {
            request.setAttribute("errorDeleteMatch", "Ошибка удаления!");
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("pages/main.jsp");
            requestDispatcher.forward(request, response);
        }
    }
}
