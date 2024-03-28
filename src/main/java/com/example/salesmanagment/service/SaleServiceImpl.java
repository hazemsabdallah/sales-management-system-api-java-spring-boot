package com.example.salesmanagment.service;

import com.example.salesmanagment.dao.SaleRepository;
import com.example.salesmanagment.entity.Sale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SaleServiceImpl implements SaleService{

    private final SaleRepository saleRepository;

    @Autowired
    public SaleServiceImpl(SaleRepository saleRepository) {
        this.saleRepository = saleRepository;
    }

    @Override
    public List<Sale> findAll() {
        return null;
    }

    @Override
    public Sale create(Sale sale) {
        return null;
    }

    @Override
    public Sale update(Sale sale) {
        return null;
    }
}
