package com.example.client_microservices.Controllers;

import com.example.client_microservices.Models.Dto.ClientDto;
import com.example.client_microservices.Models.Entity.ClientEntity;
import com.example.client_microservices.Services.ClientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping({"/v1/clients", "/v1/client"})
public class ClientController {

    private final ClientService clientService;

    @GetMapping
    public ResponseEntity<List<ClientDto>> findAll() {
        List<ClientDto> clients = clientService.findAll()
                .stream()
                .map(this::toDto)
                .toList();
        return ResponseEntity.ok(clients);
    }

    @PostMapping
    public ResponseEntity<ClientDto> createClient(@Valid @RequestBody ClientDto clientDto) {
        ClientEntity savedClient = clientService.createClient(clientDto);
        return new ResponseEntity<>(toDto(savedClient), HttpStatus.CREATED);
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<ClientDto> findByUuid(@PathVariable String uuid) {
        ClientEntity client = clientService.findByUuid(uuid);
        return ResponseEntity.ok(toDto(client));
    }

    @PutMapping("/{uuid}")
    public ResponseEntity<ClientDto> updateClient(@PathVariable String uuid, @Valid @RequestBody ClientDto clientDto) {
        ClientEntity updatedClient = clientService.updateClient(uuid, clientDto);
        return ResponseEntity.ok(toDto(updatedClient));
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<Void> deleteClient(@PathVariable String uuid) {
        clientService.deleteClient(uuid);
        return ResponseEntity.noContent().build();
    }

    private ClientDto toDto(ClientEntity client) {
        return new ClientDto(
                client.getUuid(),
                client.getUsername(),
                null,
                client.getCreationDate(),
                client.getStatus(),
                client.getEmail(),
                client.getRol().getUuid(),
                client.getRol().getName()
        );
    }
}
