package com.example.client_microservices.Services.Impl;

import com.example.client_microservices.Models.Dto.ClientDto;
import com.example.client_microservices.Models.Entity.ClientEntity;
import com.example.client_microservices.Repositories.ClientRepository;
import com.example.client_microservices.Services.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private final ClientRepository clientRepository;


    @Override
    public ClientEntity findByUuid(String uuid) {
        return clientRepository.findByUuid(uuid).orElseThrow(()-> new RuntimeException("Client not Found"));
    }

    @Override
    public ClientEntity CreateRol(ClientEntity client) {
        client.setUuid(UUID.randomUUID().toString());

        client.setUsername(client.getUsername());
        client.setPassword(client.getPassword());
        client.setEmail(client.getEmail());

        ClientEntity saveClient = clientRepository.save(client);

        return saveClient;
    }

    @Override
    public ClientEntity UpdateRol(String uuid, ClientDto clientDto) {
        Optional<ClientEntity> existingClientOtp = clientRepository.findByUuid(uuid);

        if (existingClientOtp.isEmpty()){
            throw new RuntimeException("Client Not Found");
        }

        ClientEntity existingClient = existingClientOtp.get();

        existingClient.setUsername(clientDto.getUsername());
        existingClient.setPassword(clientDto.getPassword());
        existingClient.setEmail(clientDto.getEmail());

        ClientEntity saveClient = clientRepository.save(existingClient);

        return saveClient;

    }

    @Override
    public boolean DeleteRol(String uuid) {
        Optional<ClientEntity> existingClientOtp = clientRepository.findByUuid(uuid);
        if (existingClientOtp.isPresent()){
            clientRepository.delete(existingClientOtp.get());
            return true;
        }
        return false;
    }
}
