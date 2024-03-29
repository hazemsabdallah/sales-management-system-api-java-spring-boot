package com.example.salesmanagment.service;

import com.example.salesmanagment.dao.ClientRepository;
import com.example.salesmanagment.dao.ProductRepository;
import com.example.salesmanagment.dao.SaleRepository;
import com.example.salesmanagment.dao.SellerRepository;
import com.example.salesmanagment.dto.SaleDto;
import com.example.salesmanagment.entity.*;
import com.example.salesmanagment.exception.ResourceNotFoundException;
import com.opencsv.CSVWriter;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class SaleServiceImpl implements SaleService{

    private final SaleRepository saleRepository;

    private final ClientRepository clientRepository;

    private final SellerRepository sellerRepository;

    private final ProductRepository productRepository;

    @Autowired
    public SaleServiceImpl(SaleRepository saleRepository,
                           ClientRepository clientRepository,
                           SellerRepository sellerRepository,
                           ProductRepository productRepository) {
        this.saleRepository = saleRepository;
        this.clientRepository = clientRepository;
        this.sellerRepository = sellerRepository;
        this.productRepository = productRepository;
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
    public Sale create(SaleDto saleDto) throws ResourceNotFoundException {
        Sale sale = mapDtoToSale(saleDto);
        sale.setCreationDate(new java.sql.Date(System.currentTimeMillis()));

        return saleRepository.save(sale);
    }

    @Override
    public Sale update(SaleDto saleDto) throws ResourceNotFoundException {
        Sale existingProduct = this.findById(saleDto.getId());

        Sale sale = mapDtoToSale(saleDto);
        sale.setCreationDate(existingProduct.getCreationDate());

        return saleRepository.save(sale);
    }

    private Sale mapDtoToSale(SaleDto saleDto) throws ResourceNotFoundException {
        Sale sale = new Sale(saleDto);

        Seller seller = sellerRepository.findById(saleDto.getSellerId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Seller with id " + saleDto.getSellerId() + " is not found!"));
        sale.setSeller(seller);

        Client client = clientRepository.findById(saleDto.getClientId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Client with id " + saleDto.getClientId() + " is not found!"));
        sale.setClient(client);

        Product product = productRepository.findById(saleDto.getProductId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Product with id " + saleDto.getProductId() + " is not found!"));
        sale.setProduct(product);

        return sale;
    }

    @Override
    public void generateSalesReport(Date startDate, Date endDate, HttpServletResponse response) throws IOException {

        if(startDate == null) startDate = new Date(0);
        if(endDate == null) endDate = new Date(System.currentTimeMillis());
        List<Object[]> report = saleRepository.summarize(startDate, endDate);

        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment; file=sales-report.csv");

        CSVWriter writer = new CSVWriter(response.getWriter());

        // write column names to CSV file
        writer.writeNext(new String[] {
                "Top Products",
                "Seller Name",
                "Total Sold Quantity",
                "Total Revenue"
        });

        // write rows to CSV file
        for (Object[] row : report) {
            writer.writeNext(new String[] {
                    String.valueOf(row[0]),
                    String.valueOf(row[1]),
                    String.valueOf(row[2]),
                    String.valueOf(row[3])
            });
        }
        writer.close();
    }
}
