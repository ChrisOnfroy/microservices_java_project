package com.example.client_microservices.Controllers;


import com.example.client_microservices.Models.Dto.RolDto;
import com.example.client_microservices.Models.Entity.RolEntity;
import com.example.client_microservices.Services.RolService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping({"/v1/roles", "/v1/rol"})
public class RolController {


    private final RolService rolService;

    @GetMapping
    public ResponseEntity<List<RolDto>> findAll() {
        List<RolDto> roles = rolService.findAll()
                .stream()
                .map(this::toDto)
                .toList();
        return ResponseEntity.ok(roles);
    }

    @PostMapping
    public ResponseEntity<RolDto> createRol(@Valid @RequestBody RolDto rolDto) {
        RolEntity savedRole = rolService.createRol(rolDto);
        return new ResponseEntity<>(toDto(savedRole), HttpStatus.CREATED);
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<RolDto> findByUuid(@PathVariable String uuid) {
        RolEntity rol = rolService.findByUuid(uuid);
        return ResponseEntity.ok(toDto(rol));
    }

    @PutMapping("/{uuid}")
    public ResponseEntity<RolDto> updateRol(@PathVariable String uuid, @Valid @RequestBody RolDto rolDto) {
        RolEntity updatedRole = rolService.updateRol(uuid, rolDto);
        return ResponseEntity.ok(toDto(updatedRole));
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<Void> deleteRol(@PathVariable String uuid) {
        rolService.deleteRol(uuid);
        return ResponseEntity.noContent().build();
    }

    private RolDto toDto(RolEntity role) {
        return new RolDto(role.getUuid(), role.getName());
    }
}
