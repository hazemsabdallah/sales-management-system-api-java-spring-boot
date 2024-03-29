package com.example.salesmanagment.service;

import com.example.salesmanagment.dao.SaleRepository;
import com.example.salesmanagment.entity.Sale;
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
