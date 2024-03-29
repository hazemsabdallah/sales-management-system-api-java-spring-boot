package com.example.salesmanagment.dao;

import com.example.salesmanagment.entity.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface SaleRepository extends JpaRepository<Sale, Long> {

    @Query(value = "SELECT " +
            "product.name, " +
            "CONCAT(seller.first_name, ' ', seller.last_name) AS seller_name, " +
            "SUM(sale.total_quantity) AS total_sold_quantity, " +
            "SUM(sale.total_price) AS total_revenue " +
            "FROM sale " +
            "LEFT JOIN product ON sale.product_id = product.id " +
            "LEFT JOIN seller ON sale.seller_id = seller.id " +
            "WHERE sale.creation_date BETWEEN :startDate AND :endDate " +
            "GROUP BY product.name, seller_name " +
            "ORDER BY total_sold_quantity DESC, total_revenue DESC",
            nativeQuery = true)
    List<Object[]> summarize(@Param("startDate") Date startDate,
                             @Param("endDate") Date endDate);
}