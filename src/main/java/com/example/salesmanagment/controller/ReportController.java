package com.example.salesmanagment.controller;

import com.example.salesmanagment.service.ClientService;
import com.example.salesmanagment.service.SaleService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Date;

@RestController
@RequestMapping("/api/generate-csv-report")
public class ReportController {

    private final SaleService saleService;

    private final ClientService clientService;

    @Autowired
    public ReportController(SaleService saleService, ClientService clientService) {
        this.saleService = saleService;
        this.clientService = clientService;
    }

    @GetMapping("/sales-report")
    public ResponseEntity<String> getSalesReport(
            @RequestParam(value = "from", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
            @RequestParam(value = "to", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate,
            HttpServletResponse response) throws IOException {

        saleService.generateSalesReport(startDate, endDate, response);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/clients-report")
    public ResponseEntity<String> getClientsReport(HttpServletResponse response) throws IOException {

        clientService.generateClientsReport(response);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
