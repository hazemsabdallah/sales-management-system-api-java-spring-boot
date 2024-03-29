package com.example.salesmanagment.service;

import com.example.salesmanagment.dto.ProductDto;
import com.example.salesmanagment.entity.Product;
import com.example.salesmanagment.exception.ResourceNotFoundException;

import java.util.List;

public interface ProductService {

    Product findById(Long id) throws ResourceNotFoundException;

    List<Product> findAll();

    Product create(ProductDto productdto) throws ResourceNotFoundException;

    Product update(ProductDto productDto) throws ResourceNotFoundException;

    void deleteById(Long id) throws ResourceNotFoundException;
}
