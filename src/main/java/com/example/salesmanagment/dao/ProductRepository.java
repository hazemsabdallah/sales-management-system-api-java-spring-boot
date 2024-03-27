package com.example.salesmanagment.dao;

import com.example.salesmanagment.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
