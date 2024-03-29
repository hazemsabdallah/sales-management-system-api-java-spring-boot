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
@RequestMapping("/api/sales")
public class SaleController {

    private final SaleService saleService;

    @Autowired
    public SaleController(SaleService saleService) {
        this.saleService = saleService;
    }

    @GetMapping
    public ResponseEntity<List<Sale>> readSales() {
        List<Sale> products = saleService.findAll();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Sale> readProduct(@PathVariable Long id) throws ResourceNotFoundException {
        Sale sale = saleService.findById(id);
        return new ResponseEntity<>(sale, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Sale> createSale(@Valid @RequestBody Sale sale) {
        return new ResponseEntity<>(saleService.create(sale), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Sale> updateSale(@Valid @RequestBody Sale sale, @PathVariable Long id)
            throws ResourceNotFoundException {
        sale.setId(id);
        return new ResponseEntity<>(saleService.update(sale), HttpStatus.OK);
    }
}
