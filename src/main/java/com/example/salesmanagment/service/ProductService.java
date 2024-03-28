package com.example.salesmanagment.service;

import com.example.salesmanagment.entity.Product;

import java.util.List;

public interface ProductService {

    List<Product> findAll();

    Product create(Product product);

    Product update(Product product);

    void deleteById(Long id);
}
