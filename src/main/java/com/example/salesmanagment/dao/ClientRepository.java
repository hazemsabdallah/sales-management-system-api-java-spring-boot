package com.example.salesmanagment.dao;

import com.example.salesmanagment.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
