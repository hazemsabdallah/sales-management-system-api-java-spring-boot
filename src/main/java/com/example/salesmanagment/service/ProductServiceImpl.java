package com.example.salesmanagment.service;

import com.example.salesmanagment.dao.ProductRepository;
import com.example.salesmanagment.entity.Product;
import com.example.salesmanagment.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product findById(Long id) throws ResourceNotFoundException {
        Optional<Product> result = productRepository.findById(id);
        return result.orElseThrow(() ->
                new ResourceNotFoundException("Product with id " + id + " is not found!"));
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Product create(Product product) {
        product.setId(0L);
        return productRepository.save(product);
    }

    @Override
    public Product update(Product product) throws ResourceNotFoundException {
        Product existingProduct = this.findById(product.getId());
        return productRepository.save(product);
    }

    @Override
    public void deleteById(Long id) throws ResourceNotFoundException {
        Product existingProduct = this.findById(id);
        productRepository.delete(existingProduct);
    }
}
