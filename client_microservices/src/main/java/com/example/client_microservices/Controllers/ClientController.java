package com.example.client_microservices.Controllers;

import com.example.client_microservices.Models.Dto.ClientDto;
import com.example.client_microservices.Models.Entity.ClientEntity;
import com.example.client_microservices.Services.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/client")
public class ClientController {
    @Autowired
    private final ClientService clientService;

    @PostMapping
    public ResponseEntity<Object> CreateClient(@RequestBody ClientDto clientDto){
        ClientEntity clientTosave = new ClientEntity();

        clientTosave.setUsername(clientDto.getUsername());
        clientTosave.setPassword(clientDto.getPassword());
        clientTosave.setEmail(clientDto.getEmail());

        clientService.CreateRol(clientTosave);

        return new ResponseEntity<>(clientTosave, HttpStatus.CREATED);
    }
}
