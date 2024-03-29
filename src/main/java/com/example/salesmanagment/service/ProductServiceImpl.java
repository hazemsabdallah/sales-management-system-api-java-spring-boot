package com.example.salesmanagment.service;

import com.example.salesmanagment.dao.ProductCategoryRepository;
import com.example.salesmanagment.dao.ProductRepository;
import com.example.salesmanagment.dao.SellerRepository;
import com.example.salesmanagment.dto.ProductDto;
import com.example.salesmanagment.entity.Product;
import com.example.salesmanagment.entity.ProductCategory;
import com.example.salesmanagment.entity.Seller;
import com.example.salesmanagment.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    private final ProductCategoryRepository productCategoryRepository;

    private final SellerRepository sellerRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository,
                              ProductCategoryRepository productCategoryRepository,
                              SellerRepository sellerRepository) {
        this.productRepository = productRepository;
        this.productCategoryRepository = productCategoryRepository;
        this.sellerRepository = sellerRepository;
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
    public Product create(ProductDto productDto) throws ResourceNotFoundException {
        Product newProduct = mapDtoToProduct(productDto);
        newProduct.setCreationDate(new Date(System.currentTimeMillis()));

        return productRepository.save(newProduct);
    }

    @Override
    public Product update(ProductDto productDto) throws ResourceNotFoundException {
        Product existingProduct = this.findById(productDto.getId());

        Product updatedProduct = mapDtoToProduct(productDto);
        updatedProduct.setCreationDate(existingProduct.getCreationDate());

        return productRepository.save(updatedProduct);
    }

    private Product mapDtoToProduct(ProductDto productDto) throws ResourceNotFoundException {
        Product product = new Product(productDto);

        Seller seller = sellerRepository.findById(productDto.getSellerId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Seller with id " + productDto.getSellerId() + " is not found!"));
        product.setSeller(seller);

        ProductCategory productCategory = productCategoryRepository.findById(productDto.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Category with id " + productDto.getCategoryId() + " is not found!"));
        product.setCategory(productCategory);

        return product;
    }

    @Override
    public void deleteById(Long id) throws ResourceNotFoundException {
        Product existingProduct = this.findById(id);
        productRepository.delete(existingProduct);
    }
}
