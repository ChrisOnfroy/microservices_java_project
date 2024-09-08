package com.example.taks_microservices.Services.Impl;

import com.example.taks_microservices.Models.Dto.TaksDto;
import com.example.taks_microservices.Models.Entity.TaksEntity;
import com.example.taks_microservices.Repositories.TaksRepository;
import com.example.taks_microservices.Services.TaksService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class TaksServiceImpl implements TaksService {

    @Autowired
    private final TaksRepository taksRepository;

    @Override
    public TaksEntity findByuuid(String uuid) {
        return taksRepository.findByUuid(uuid).orElseThrow(()-> new RuntimeException("Task not found"));
    }

    @Override
    public TaksEntity createTaks(TaksEntity taks) {
        taks.setUuid(UUID.randomUUID().toString());

        taks.setName(taks.getName());
        taks.setDescription(taks.getDescription());
        taks.setPoints(taks.getPoints());
        taks.setDeathline(taks.getDeathline());

        TaksEntity saveTaks = taksRepository.save(taks);

        return saveTaks;

    }

    @Override
    public TaksEntity UpdateTaks(String uuid, TaksDto taksDto) {
        Optional<TaksEntity> existingTaksOtp = taksRepository.findByUuid(uuid);

        if (existingTaksOtp.isEmpty()){
            throw new RuntimeException("Taks Not Found");
        }

        TaksEntity existingTaks = existingTaksOtp.get();

        existingTaks.setName(taksDto.getName());
        existingTaks.setDescription(taksDto.getDescription());
        existingTaks.setPoints(taksDto.getPoints());
        existingTaks.setDeathline(taksDto.getDeathline());

        TaksEntity saveTaks = taksRepository.save(existingTaks);

        return saveTaks;
    }

    @Override
    public boolean DeleteTaks(String uuid) {
        Optional<TaksEntity> existingTaksOtp = taksRepository.findByUuid(uuid);

        if (existingTaksOtp.isPresent()){
            taksRepository.delete(existingTaksOtp.get());
            return true;
        }

        return false;
    }

}
