package com.example.salesmanagment.controller;

import com.example.salesmanagment.entity.Sale;
import com.example.salesmanagment.exception.ResourceNotFoundException;
import com.example.salesmanagment.service.SaleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class SaleController {

    private final SaleService saleService;

    @Autowired
    public SaleController(SaleService saleService) {
        this.saleService = saleService;
    }

    @GetMapping("/sales")
    public ResponseEntity<List<Sale>> readSales() {
        List<Sale> products = saleService.findAll();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @PostMapping("/sales")
    public ResponseEntity<Sale> createSale(@Valid @RequestBody Sale sale) {
        return new ResponseEntity<>(saleService.create(sale), HttpStatus.CREATED);
    }

    @PutMapping("/sales/{id}")
    public ResponseEntity<Sale> updateSale(@Valid @RequestBody Sale sale, @PathVariable Long id)
            throws ResourceNotFoundException {
        sale.setId(id);
        return new ResponseEntity<>(saleService.update(sale), HttpStatus.OK);
    }
}
