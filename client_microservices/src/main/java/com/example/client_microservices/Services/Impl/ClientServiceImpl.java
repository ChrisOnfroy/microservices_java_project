package com.example.client_microservices.Services.Impl;

import com.example.client_microservices.Exceptions.ClientExceptions;
import com.example.client_microservices.Exceptions.RolExceptions;
import com.example.client_microservices.Models.Dto.ClientDto;
import com.example.client_microservices.Models.Entity.ClientEntity;
import com.example.client_microservices.Models.Entity.RolEntity;
import com.example.client_microservices.Repositories.ClientRepository;
import com.example.client_microservices.Repositories.RolRepository;
import com.example.client_microservices.Services.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;
    private final RolRepository rolRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public List<ClientEntity> findAll() {
        return clientRepository.findAll(Sort.by(Sort.Direction.ASC, "username"));
    }

    @Override
    public ClientEntity findByUuid(String uuid) {
        return clientRepository.findByUuid(uuid)
                .orElseThrow(() -> new ClientExceptions("Client with uuid " + uuid + " was not found"));
    }

    @Override
    public ClientEntity createClient(ClientDto clientDto) {
        ClientEntity clientToSave = new ClientEntity();
        clientToSave.setUuid(UUID.randomUUID().toString());
        applyChanges(clientToSave, clientDto);
        return clientRepository.save(clientToSave);
    }

    @Override
    public ClientEntity updateClient(String uuid, ClientDto clientDto) {
        ClientEntity existingClient = findByUuid(uuid);
        applyChanges(existingClient, clientDto);
        return clientRepository.save(existingClient);
    }

    @Override
    public void deleteClient(String uuid) {
        ClientEntity existingClient = findByUuid(uuid);
        clientRepository.delete(existingClient);
    }

    private void applyChanges(ClientEntity client, ClientDto clientDto) {
        RolEntity role = rolRepository.findByUuid(clientDto.getRoleUuid())
                .orElseThrow(() -> new RolExceptions("Role with uuid " + clientDto.getRoleUuid() + " was not found"));

        client.setUsername(clientDto.getUsername());
        client.setPassword(passwordEncoder.encode(clientDto.getPassword()));
        client.setEmail(clientDto.getEmail());
        client.setStatus(clientDto.getStatus() == null ? Boolean.TRUE : clientDto.getStatus());
        client.setRol(role);
    }
}
