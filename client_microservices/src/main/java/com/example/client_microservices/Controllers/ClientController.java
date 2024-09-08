package com.example.client_microservices.Controllers;

import com.example.client_microservices.Exceptions.RolExceptions;
import com.example.client_microservices.Models.Dto.ClientDto;
import com.example.client_microservices.Models.Entity.ClientEntity;
import com.example.client_microservices.Models.Entity.RolEntity;
import com.example.client_microservices.Repositories.RolRepository;
import com.example.client_microservices.Services.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/client")
public class ClientController {
    @Autowired
    private final ClientService clientService;

    @Autowired
    private final RolRepository rolRepository;

    @PostMapping
    public ResponseEntity<Object> CreateClient(@RequestBody ClientDto clientDto){

        RolEntity rol = rolRepository.getReferenceByUuid(clientDto.getRol());

        if (rol == null ){
            throw new RolExceptions("Rol whit Uuid " + clientDto.getRol() + "Not found");
        }

        ClientEntity clientTosave = new ClientEntity();

        clientTosave.setRol(rol);
        clientTosave.setUsername(clientDto.getUsername());
        clientTosave.setPassword(clientDto.getPassword());
        clientTosave.setEmail(clientDto.getEmail());


        clientService.CreateRol(clientTosave);

        return new ResponseEntity<>(clientTosave, HttpStatus.CREATED);
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<ClientEntity>findByUuid(@PathVariable String uuid){
        ClientEntity client = clientService.findByUuid(uuid);
        return new ResponseEntity<>(client, HttpStatus.OK);
    }
}
