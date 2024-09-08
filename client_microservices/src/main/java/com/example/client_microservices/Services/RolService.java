package com.example.client_microservices.Services;

import com.example.client_microservices.Models.Entity.RolEntity;
import com.example.client_microservices.Models.Dto.RolDto;

public interface RolService {

    RolEntity findByUuid(String uuid);

    RolEntity CreateRol(RolEntity rol);

    RolEntity UpdateRol(String uuid, RolDto rolDto);

    boolean DeleteRol(String uuid);
}
