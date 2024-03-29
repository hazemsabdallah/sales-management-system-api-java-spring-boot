package com.example.salesmanagment.service;

import com.example.salesmanagment.dao.ClientRepository;
import com.example.salesmanagment.entity.Client;
import com.example.salesmanagment.exception.ResourceNotFoundException;
import com.opencsv.CSVWriter;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    @Autowired
    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public Client findById(Long id) throws ResourceNotFoundException {
        Optional<Client> result = clientRepository.findById(id);
        return result.orElseThrow(() ->
                new ResourceNotFoundException("Client with id " + id + " is not found!"));
    }

    @Override
    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    @Override
    public Client create(Client client) {
        client.setId(0L);
        return clientRepository.save(client);
    }

    @Override
    public Client update(Client client) throws ResourceNotFoundException {
        Client existingClient = this.findById(client.getId());
        return clientRepository.save(client);
    }

    @Override
    public void deleteById(Long id) throws ResourceNotFoundException {
        Client existingClient = this.findById(id);
        clientRepository.delete(existingClient);
    }

    @Override
    public void generateClientsReport(HttpServletResponse response) throws IOException {

        List<Object[]> report = clientRepository.summarize();

        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment; file=clients-report.csv");

        CSVWriter writer = new CSVWriter(response.getWriter());

        // write column names to CSV file
        writer.writeNext(new String[] {
                "Top Clients",
                "Total Purchased Quantity",
                "Total Spending"
        });

        // write rows to CSV file
        for (Object[] row : report) {
            writer.writeNext(new String[] {
                    String.valueOf(row[0]),
                    String.valueOf(row[1]),
                    String.valueOf(row[2])
            });
        }
        writer.close();
    }
}
