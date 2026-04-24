package com.example.client_microservices.Services;

import com.example.client_microservices.Models.Entity.RolEntity;
import com.example.client_microservices.Models.Dto.RolDto;

import java.util.List;

public interface RolService {

    List<RolEntity> findAll();

    RolEntity findByUuid(String uuid);

    RolEntity createRol(RolDto rolDto);

    RolEntity updateRol(String uuid, RolDto rolDto);

    void deleteRol(String uuid);
}
