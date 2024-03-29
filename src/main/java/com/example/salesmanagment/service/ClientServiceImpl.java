package com.example.salesmanagment.service;

import com.example.salesmanagment.dao.ClientRepository;
import com.example.salesmanagment.entity.Client;
import com.example.salesmanagment.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
}
