package com.example.client_microservices.Repositories;

import com.example.client_microservices.Models.Entity.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<ClientEntity, Long> {
    Optional<ClientEntity> findByUuid(String uuid);
}
