package com.example.taks_microservices.Controllers;


import com.example.taks_microservices.Models.Dto.TaksDto;
import com.example.taks_microservices.Models.Entity.TaksEntity;
import com.example.taks_microservices.Services.TaksService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/taks")
public class TaksController {

    @Autowired
    private final TaksService taksService;

    @PostMapping
    public ResponseEntity<Object> createTaks(@RequestBody TaksDto taksDto){

        TaksEntity taksEntity = new TaksEntity();

        taksEntity.setName(taksDto.getName());
        taksEntity.setDescription(taksDto.getDescription());
        taksEntity.setPoints(taksDto.getPoints());
        taksEntity.setDeathline(taksDto.getDeathline());

        taksService.createTaks(taksEntity);

        return new ResponseEntity<>(taksEntity, HttpStatus.CREATED);

    }

    @GetMapping("/{uuid}")
    public ResponseEntity<TaksEntity>findByUuid(@PathVariable String uuid){
        TaksEntity taks = taksService.findByuuid(uuid);
        return new ResponseEntity<>(taks, HttpStatus.OK);
    }
}
