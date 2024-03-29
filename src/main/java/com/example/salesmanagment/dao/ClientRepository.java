package com.example.salesmanagment.dao;

import com.example.salesmanagment.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ClientRepository extends JpaRepository<Client, Long> {

    @Query(value = "SELECT " +
            "CONCAT(client.first_name, ' ', client.last_name) AS client_name, " +
            "SUM(sale.total_quantity) AS total_purchased_quantity, " +
            "SUM(sale.total_price) AS total_spending " +
            "FROM client " +
            "LEFT JOIN sale ON sale.client_id = client.id " +
            "GROUP BY client_name " +
            "ORDER BY total_spending DESC, total_purchased_quantity DESC",
            nativeQuery = true)
    List<Object[]> summarize();
}
