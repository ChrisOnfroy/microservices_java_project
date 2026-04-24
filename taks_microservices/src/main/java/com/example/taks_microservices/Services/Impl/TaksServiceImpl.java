package com.example.taks_microservices.Services.Impl;

import com.example.taks_microservices.Exceptions.ResourceNotFoundException;
import com.example.taks_microservices.Models.Dto.TaksDto;
import com.example.taks_microservices.Models.Entity.TaksEntity;
import com.example.taks_microservices.Repositories.TaksRepository;
import com.example.taks_microservices.Services.TaksService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class TaksServiceImpl implements TaksService {

    private final TaksRepository taksRepository;

    @Override
    public List<TaksEntity> findAll() {
        return taksRepository.findAll(Sort.by(Sort.Direction.ASC, "creationDate", "name"));
    }

    @Override
    public TaksEntity findByUuid(String uuid) {
        return taksRepository.findByUuid(uuid)
                .orElseThrow(() -> new ResourceNotFoundException("Task with uuid " + uuid + " was not found"));
    }

    @Override
    public TaksEntity createTaks(TaksDto taksDto) {
        TaksEntity taskToSave = new TaksEntity();
        taskToSave.setUuid(UUID.randomUUID().toString());
        applyChanges(taskToSave, taksDto);
        return taksRepository.save(taskToSave);
    }

    @Override
    public TaksEntity updateTaks(String uuid, TaksDto taksDto) {
        TaksEntity existingTask = findByUuid(uuid);
        applyChanges(existingTask, taksDto);
        return taksRepository.save(existingTask);
    }

    @Override
    public void deleteTaks(String uuid) {
        TaksEntity existingTask = findByUuid(uuid);
        taksRepository.delete(existingTask);
    }

    private void applyChanges(TaksEntity task, TaksDto taksDto) {
        task.setName(taksDto.getName());
        task.setDescription(taksDto.getDescription());
        task.setPoints(taksDto.getPoints());
        task.setDeadline(taksDto.getDeadline());
        task.setStatus(taksDto.getStatus() == null ? Boolean.TRUE : taksDto.getStatus());
    }
}
