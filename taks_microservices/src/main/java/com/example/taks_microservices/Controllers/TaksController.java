package com.example.taks_microservices.Controllers;


import com.example.taks_microservices.Models.Dto.TaksDto;
import com.example.taks_microservices.Models.Entity.TaksEntity;
import com.example.taks_microservices.Services.TaksService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping({"/v1/tasks", "/v1/taks"})
public class TaksController {

    private final TaksService taksService;

    @GetMapping
    public ResponseEntity<List<TaksDto>> findAll() {
        List<TaksDto> tasks = taksService.findAll()
                .stream()
                .map(this::toDto)
                .toList();
        return ResponseEntity.ok(tasks);
    }

    @PostMapping
    public ResponseEntity<TaksDto> createTaks(@Valid @RequestBody TaksDto taksDto) {
        TaksEntity savedTask = taksService.createTaks(taksDto);
        return new ResponseEntity<>(toDto(savedTask), HttpStatus.CREATED);
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<TaksDto> findByUuid(@PathVariable String uuid) {
        TaksEntity taks = taksService.findByUuid(uuid);
        return ResponseEntity.ok(toDto(taks));
    }

    @PutMapping("/{uuid}")
    public ResponseEntity<TaksDto> updateTaks(@PathVariable String uuid, @Valid @RequestBody TaksDto taksDto) {
        TaksEntity updatedTask = taksService.updateTaks(uuid, taksDto);
        return ResponseEntity.ok(toDto(updatedTask));
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<Void> deleteTaks(@PathVariable String uuid) {
        taksService.deleteTaks(uuid);
        return ResponseEntity.noContent().build();
    }

    private TaksDto toDto(TaksEntity task) {
        return new TaksDto(
                task.getUuid(),
                task.getName(),
                task.getDescription(),
                task.getPoints(),
                task.getStatus(),
                task.getCreationDate(),
                task.getDeadline()
        );
    }
}
