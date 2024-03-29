package com.example.salesmanagment.service;

import com.example.salesmanagment.entity.Client;
import com.example.salesmanagment.exception.ResourceNotFoundException;

import java.util.List;

public interface ClientService {

    Client findById(Long id) throws ResourceNotFoundException;

    List<Client> findAll();

    Client create(Client client);

    Client update(Client client) throws ResourceNotFoundException;

    void deleteById(Long id) throws ResourceNotFoundException;
}
