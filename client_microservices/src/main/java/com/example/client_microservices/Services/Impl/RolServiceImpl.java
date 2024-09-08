package com.example.client_microservices.Services.Impl;

import com.example.client_microservices.Models.Dto.RolDto;
import com.example.client_microservices.Models.Entity.RolEntity;
import com.example.client_microservices.Repositories.RolRepository;
import com.example.client_microservices.Services.RolService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class RolServiceImpl implements RolService {

    @Autowired
    private final RolRepository rolRepository;


    @Override
    public RolEntity findByUuid(String uuid) {
        return rolRepository.findByUuid(uuid).orElseThrow(()->new RuntimeException("Rol Not Found"));
    }

    @Override
    public RolEntity CreateRol(RolEntity rol) {

        rol.setUuid(UUID.randomUUID().toString());

        rol.setString(rol.getString());

        RolEntity saveRol = rolRepository.save(rol);

        return saveRol;

    }

    @Override
    public RolEntity UpdateRol(String uuid, RolDto rolDto) {
        Optional<RolEntity> existingRolOtp = rolRepository.findByUuid(uuid);

        if (existingRolOtp.isEmpty()){
            throw new RuntimeException("Rol Not Found");
        }

        RolEntity existingRol = existingRolOtp.get();

        existingRol.setString(rolDto.getString());

        RolEntity saveRol = rolRepository.save(existingRol);

        return saveRol;

    }

    @Override
    public boolean DeleteRol(String uuid) {
        Optional<RolEntity> existingRolOtp = rolRepository.findByUuid(uuid);

        if (existingRolOtp.isPresent()){
            rolRepository.delete(existingRolOtp.get());
            return true;
        }
        return false;
    }

}
