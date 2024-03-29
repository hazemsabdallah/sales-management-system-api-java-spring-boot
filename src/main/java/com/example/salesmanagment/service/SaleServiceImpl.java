package com.example.salesmanagment.service;

import com.example.salesmanagment.dao.SaleRepository;
import com.example.salesmanagment.entity.Sale;
import com.example.salesmanagment.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class SaleServiceImpl implements SaleService{

    private final SaleRepository saleRepository;

    @Autowired
    public SaleServiceImpl(SaleRepository saleRepository) {
        this.saleRepository = saleRepository;
    }

    @Override
    public Sale findById(Long id) throws ResourceNotFoundException {
        Optional<Sale> result = saleRepository.findById(id);
        return result.orElseThrow(() ->
                new ResourceNotFoundException("Sale with id " + id + " is not found!"));
    }

    @Override
    public List<Sale> findAll() {
        return saleRepository.findAll();
    }

    @Override
    public Sale create(Sale sale) {
        sale.setId(0L);
        return saleRepository.save(sale);
    }

    @Override
    public Sale update(Sale sale) throws ResourceNotFoundException {
        Sale existingProduct = this.findById(sale.getId());
        return saleRepository.save(sale);
    }
}
