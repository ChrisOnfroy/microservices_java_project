package com.example.taks_microservices.Repositories;

import com.example.taks_microservices.Models.Dto.TaksDto;
import com.example.taks_microservices.Models.Entity.TaksEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TaksRepository extends JpaRepository<TaksEntity, Long> {

    TaksEntity getReferenceByUuid(String Uuid);

    Optional<TaksEntity> findByUuid(String uuid);

}
