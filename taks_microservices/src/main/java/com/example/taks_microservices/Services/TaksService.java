package com.example.taks_microservices.Services;

import com.example.taks_microservices.Models.Dto.TaksDto;
import com.example.taks_microservices.Models.Entity.TaksEntity;

import java.util.List;

public interface TaksService {

    List<TaksEntity> findAll();

    TaksEntity findByUuid(String uuid);

    TaksEntity createTaks(TaksDto taksDto);

    TaksEntity updateTaks(String uuid, TaksDto taksDto);

    void deleteTaks(String uuid);
}
