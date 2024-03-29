package com.example.salesmanagment.service;

import com.example.salesmanagment.entity.Sale;
import com.example.salesmanagment.exception.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Date;
import java.util.List;

public interface SaleService {

    Sale findById(Long id) throws ResourceNotFoundException;

    List<Sale> findAll();

    Sale create(Sale sale);

    Sale update(Sale sale) throws ResourceNotFoundException;

    void generateSalesReport(Date startDate, Date endDate, HttpServletResponse response) throws IOException;
}
