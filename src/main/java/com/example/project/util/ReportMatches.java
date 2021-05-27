package com.example.project.util;

import com.example.project.entity.Match;
import com.example.project.entity.User;
import com.example.project.service.UserService;
import com.example.project.service.impl.UserServiceImpl;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.sql.SQLException;
import java.util.List;


public class ReportMatches {
    private ReportMatches() {

    }

    public static void writeHeaderLine(XSSFSheet sheet) {

        Row headerRow = sheet.createRow(0);

        Cell headerCell = headerRow.createCell(0);
        headerCell.setCellValue("sport_id");

        headerCell = headerRow.createCell(1);
        headerCell.setCellValue("name");

        headerCell = headerRow.createCell(2);
        headerCell.setCellValue("player1");

        headerCell = headerRow.createCell(3);
        headerCell.setCellValue("player2");

        headerCell = headerRow.createCell(4);
        headerCell.setCellValue("rate1");

        headerCell = headerRow.createCell(5);
        headerCell.setCellValue("rate0");

        headerCell = headerRow.createCell(6);
        headerCell.setCellValue("rate2");

        headerCell = headerRow.createCell(7);
        headerCell.setCellValue("date");
    }

    public static void writeDataLines(List<Match> allMatch, XSSFWorkbook workbook,
                                      XSSFSheet sheet) throws SQLException {
        int rowCount = 1;
        for (Match match : allMatch) {
            String sportId = String.valueOf(match.getSportId());
            String name = match.getName();
            String player1 = match.getPlayer1();
            String player2 = match.getPlayer2();
            //String rate1 = match.getRate1().toString();

            String rate1 = String.valueOf(match.getRate1());
            String rate0 = String.valueOf(match.getRate0());
            String rate2 = String.valueOf(match.getRate2());
            String date = String.valueOf(match.getDate());

            Row row = sheet.createRow(rowCount++);

            int columnCount = 0;
            Cell cell = row.createCell(columnCount++);
            cell.setCellValue(sportId);

            cell = row.createCell(columnCount++);
            cell.setCellValue(name);

            cell = row.createCell(columnCount++);
            cell.setCellValue(player1);

            cell = row.createCell(columnCount++);
            cell.setCellValue(player2);

            cell = row.createCell(columnCount++);
            cell.setCellValue(rate1);

            cell = row.createCell(columnCount++);
            cell.setCellValue(rate0);

            cell = row.createCell(columnCount++);
            cell.setCellValue(rate2);

            cell = row.createCell(columnCount++);
            cell.setCellValue(date);

        }
    }
}
