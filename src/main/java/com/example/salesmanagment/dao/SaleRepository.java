package com.example.salesmanagment.dao;

import com.example.salesmanagment.entity.Sale;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaleRepository extends JpaRepository<Sale, Long> {
}
