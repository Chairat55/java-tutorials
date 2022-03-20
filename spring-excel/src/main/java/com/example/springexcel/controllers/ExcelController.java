package com.example.springexcel.controllers;

import com.example.springexcel.services.ExcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/excel")
public class ExcelController {

    @Autowired
    private ExcelService excelService;

    @PostMapping(value = "/import")
    public String importExcel(
            @RequestParam("file") MultipartFile file
    ) {
        return excelService.importExcel(file);
    }

    @GetMapping("/export")
    public ResponseEntity<Resource> exportExcel() {
        String filename = "test-export.xlsx";
        InputStreamResource file = new InputStreamResource(excelService.exportExcel());

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
                .contentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
                .body(file);
    }

}
