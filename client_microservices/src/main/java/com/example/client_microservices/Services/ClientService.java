package com.example.client_microservices.Services;


import com.example.client_microservices.Models.Dto.ClientDto;

import com.example.client_microservices.Models.Entity.ClientEntity;


public interface ClientService {

    ClientEntity findByUuid(String uuid);

    ClientEntity CreateRol(ClientEntity client);

    ClientEntity UpdateRol(String uuid, ClientDto clientDto);

    boolean DeleteRol(String uuid);

}
