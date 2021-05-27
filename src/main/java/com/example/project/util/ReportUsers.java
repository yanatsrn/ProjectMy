package com.example.project.util;

import com.example.project.entity.User;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.sql.SQLException;
import java.util.List;

public class ReportUsers {

    private ReportUsers() {
    }

    public static void writeHeaderLine(XSSFSheet sheet) {

        Row headerRow = sheet.createRow(0);

        Cell headerCell = headerRow.createCell(0);
        headerCell.setCellValue("user_id");

        headerCell = headerRow.createCell(1);
        headerCell.setCellValue("login");

        headerCell = headerRow.createCell(2);
        headerCell.setCellValue("password");

        headerCell = headerRow.createCell(3);
        headerCell.setCellValue("role");

        headerCell = headerRow.createCell(4);
        headerCell.setCellValue("surname");

        headerCell = headerRow.createCell(5);
        headerCell.setCellValue("name");

        headerCell = headerRow.createCell(6);
        headerCell.setCellValue("age");

        headerCell = headerRow.createCell(7);
        headerCell.setCellValue("phone");

        headerCell = headerRow.createCell(8);
        headerCell.setCellValue("mail");

        headerCell = headerRow.createCell(9);
        headerCell.setCellValue("sum");

        headerCell = headerRow.createCell(10);
        headerCell.setCellValue("matchId");
    }

    public static void writeDataLines(List<User> allUser, XSSFWorkbook workbook,
                                      XSSFSheet sheet) throws SQLException {
        int rowCount = 1;
        for (User user : allUser) {
            String userId = String.valueOf(user.getId());
            String login = user.getLogin();
            String password = user.getPassword();
            String role = user.getRoleType().toString();
            String surname = user.getSurname();
            String name = user.getName();
            String age = String.valueOf(user.getAge());
            String phone = user.getPhone();
            String mail = user.getMail();
            String sum = user.getSum().toString();
            String matchId = String.valueOf(user.getMatchId());

            Row row = sheet.createRow(rowCount++);

            int columnCount = 0;
            Cell cell = row.createCell(columnCount++);
            cell.setCellValue(userId);

            cell = row.createCell(columnCount++);
            cell.setCellValue(login);

            cell = row.createCell(columnCount++);
            cell.setCellValue(password);

            cell = row.createCell(columnCount++);
            cell.setCellValue(role);

            cell = row.createCell(columnCount++);
            cell.setCellValue(surname);

            cell = row.createCell(columnCount++);
            cell.setCellValue(name);

            cell = row.createCell(columnCount++);
            cell.setCellValue(age);

            cell = row.createCell(columnCount++);
            cell.setCellValue(phone);

            cell = row.createCell(columnCount++);
            cell.setCellValue(mail);

            cell = row.createCell(columnCount++);
            cell.setCellValue(sum);

            cell = row.createCell(columnCount++);
            cell.setCellValue(matchId);
        }
    }
}
