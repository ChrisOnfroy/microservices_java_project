package com.example.client_microservices.Controllers;


import com.example.client_microservices.Models.Dto.RolDto;
import com.example.client_microservices.Models.Entity.ClientEntity;
import com.example.client_microservices.Models.Entity.RolEntity;
import com.example.client_microservices.Services.ClientService;
import com.example.client_microservices.Services.RolService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/rol")
public class RolController {


    @Autowired
    private final RolService rolService;

    @PostMapping
    public ResponseEntity<Object> CreateRol(@RequestBody RolDto rolDto) {

        RolEntity rolToSave = new RolEntity();

        rolToSave.setString(rolDto.getString());
        rolService.CreateRol(rolToSave);

        return new ResponseEntity<>(rolToSave, HttpStatus.CREATED);
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<RolEntity> findByUuid(@PathVariable String uuid){
        RolEntity rol = rolService.findByUuid(uuid);
        return new ResponseEntity<>(rol, HttpStatus.OK);
    }
}
