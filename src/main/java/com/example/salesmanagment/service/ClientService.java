package com.example.salesmanagment.service;

import com.example.salesmanagment.entity.Client;

import java.util.List;

public interface ClientService {

    List<Client> findAll();

    Client create(Client client);

    Client update(Client client);

    void deleteById(Long id);
}
