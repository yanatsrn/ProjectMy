package com.example.project.controller;

import com.example.project.entity.User;
import com.example.project.exception.ServiceException;
import com.example.project.service.UserService;
import com.example.project.service.impl.UserServiceImpl;
import com.example.project.util.Report;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@WebServlet(value = "/send_report")
public class ReportServlet extends HttpServlet {
    private UserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            response.setContentType("application/octet-stream");
            DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
            String currentDateTime = dateFormatter.format(new Date());
            String headerKey = "Content-Disposition";
            String headerValue = "attachment; filename=stuff_" + currentDateTime + ".xlsx";
            response.setHeader(headerKey, headerValue);
            List<User> allUser = userService.findAllUser();
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet("Users");
            Report.writeHeaderLine(sheet);

            Report.writeDataLines(allUser, workbook, sheet);

            ServletOutputStream outputStream = response.getOutputStream();
            workbook.write(outputStream);
            workbook.close();

            outputStream.close();

            RequestDispatcher requestDispatcher = request.getRequestDispatcher("pages/main.jsp");
            requestDispatcher.forward(request, response);
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}

