package com.example.springexcel.services;

import com.example.springexcel.dtos.UserDTO;
import com.example.springexcel.exceptions.BadRequestException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class ExcelService {

    public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
    static String[] HEADERs = { "Email", "FirstName", "LastName" };

    public String importExcel(
            MultipartFile file
    ){

        if (!TYPE.equals(file.getContentType())) {
            throw new BadRequestException("file must be format .xlsx");
        }

        try {
            List<UserDTO> userDTOS = new ArrayList<>();
            Workbook workbook = new XSSFWorkbook(file.getInputStream());

            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rows = sheet.iterator();

            int rowNumber = 0;
            while (rows.hasNext()) {
                Row currentRow = rows.next();

                // skip header
                if (rowNumber == 0) {
                    rowNumber++;
                    continue;
                }

                if (currentRow.getCell(0).getStringCellValue().equals("")) {
                    continue;
                }

                Iterator<Cell> cellsInRow = currentRow.iterator();

                UserDTO userDTO = new UserDTO();

                int cellIdx = 0;
                while (cellIdx < 3) {
                    Cell currentCell = cellsInRow.next();

                    switch (cellIdx) {
                        case 0:
                            if (currentCell.getStringCellValue().equals("")) {
                                throw new BadRequestException("Email is require");
                            }
                            userDTO.setEmail(currentCell.getStringCellValue());
                            break;

                        case 1:
                            if (currentCell.getStringCellValue().equals("")) {
                                throw new BadRequestException("FirstName is require");
                            }
                            userDTO.setFirstName(currentCell.getStringCellValue());
                            break;

                        case 2:
                            if (currentCell.getStringCellValue().equals("")) {
                                throw new BadRequestException("LastName is require");
                            }
                            userDTO.setLastName(currentCell.getStringCellValue());
                            break;

                        default:
                            break;
                    }

                    cellIdx++;
                }

                userDTOS.add(userDTO);
            }

            for (UserDTO userDTO : userDTOS) {
                System.out.println("Email: " + userDTO.getEmail());
                System.out.println("FirstName: " + userDTO.getFirstName());
                System.out.println("LastName: " + userDTO.getLastName());
                System.out.println("-----------------------");
            }

            workbook.close();

        } catch (IOException e) {
            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
        }

        return "success";
    }

    public ByteArrayInputStream exportExcel(){
        ByteArrayInputStream in;
        try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream();) {
            Sheet sheet = workbook.createSheet("Test");

            // Header
            Row headerRow = sheet.createRow(0);

            for (int col = 0; col < HEADERs.length; col++) {
                Cell cell = headerRow.createCell(col);
                cell.setCellValue(HEADERs[col]);
            }

            int rowIdx = 1;
            for (int i = 0; i < 5 ; i++) {
                Row row = sheet.createRow(rowIdx++);

                row.createCell(0).setCellValue("Email_" + i);
                row.createCell(1).setCellValue("FirstName_" + i);
                row.createCell(2).setCellValue("LastName_" + i);
            }

            workbook.write(out);
            in = new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("fail to import data to Excel file: " + e.getMessage());
        }

        return in;
    }
}
