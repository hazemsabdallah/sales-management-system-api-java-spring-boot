package com.example.salesmanagment.service;

import com.example.salesmanagment.entity.Sale;

import java.util.List;

public interface SaleService {

    List<Sale> findAll();

    Sale create(Sale sale);

    Sale update(Sale sale);
}
