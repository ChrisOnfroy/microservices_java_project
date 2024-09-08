package com.example.taks_microservices.Services;

import com.example.taks_microservices.Models.Dto.TaksDto;
import com.example.taks_microservices.Models.Entity.TaksEntity;

public interface TaksService {

    TaksEntity findByuuid(String uuid);

    TaksEntity createTaks(TaksEntity taks);

    TaksEntity UpdateTaks(String uuid, TaksDto taksDto);

    boolean DeleteTaks(String uuid);

}
