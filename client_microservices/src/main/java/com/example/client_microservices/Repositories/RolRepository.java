package com.example.client_microservices.Repositories;

import com.example.client_microservices.Models.Entity.RolEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RolRepository extends JpaRepository<RolEntity, Long> {

    RolEntity getReferenceByUuid(String Uuid);

    Optional<RolEntity> findByUuid(String uuid);
}
