package com.example.salesmanagment.dao;

import com.example.salesmanagment.entity.Seller;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SellerRepository extends JpaRepository<Seller, Long> {
}
