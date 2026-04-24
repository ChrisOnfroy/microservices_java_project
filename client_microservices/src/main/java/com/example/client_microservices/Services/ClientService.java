package com.example.client_microservices.Services;


import com.example.client_microservices.Models.Dto.ClientDto;

import com.example.client_microservices.Models.Entity.ClientEntity;

import java.util.List;

public interface ClientService {

    List<ClientEntity> findAll();

    ClientEntity findByUuid(String uuid);

    ClientEntity createClient(ClientDto clientDto);

    ClientEntity updateClient(String uuid, ClientDto clientDto);

    void deleteClient(String uuid);

}
