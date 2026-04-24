package com.example.client_microservices.Services.Impl;

import com.example.client_microservices.Exceptions.RolExceptions;
import com.example.client_microservices.Models.Dto.RolDto;
import com.example.client_microservices.Models.Entity.RolEntity;
import com.example.client_microservices.Repositories.RolRepository;
import com.example.client_microservices.Services.RolService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class RolServiceImpl implements RolService {

    private final RolRepository rolRepository;


    @Override
    public List<RolEntity> findAll() {
        return rolRepository.findAll(Sort.by(Sort.Direction.ASC, "name"));
    }

    @Override
    public RolEntity findByUuid(String uuid) {
        return rolRepository.findByUuid(uuid)
                .orElseThrow(() -> new RolExceptions("Role with uuid " + uuid + " was not found"));
    }

    @Override
    public RolEntity createRol(RolDto rolDto) {
        RolEntity roleToSave = new RolEntity();
        roleToSave.setUuid(UUID.randomUUID().toString());
        roleToSave.setName(rolDto.getName());
        return rolRepository.save(roleToSave);
    }

    @Override
    public RolEntity updateRol(String uuid, RolDto rolDto) {
        RolEntity existingRole = findByUuid(uuid);
        existingRole.setName(rolDto.getName());
        return rolRepository.save(existingRole);
    }

    @Override
    public void deleteRol(String uuid) {
        RolEntity existingRole = findByUuid(uuid);
        rolRepository.delete(existingRole);
    }

}
