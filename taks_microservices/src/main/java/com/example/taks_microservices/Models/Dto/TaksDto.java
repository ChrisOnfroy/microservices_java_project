package com.example.taks_microservices.Models.Dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class TaksDto {

    public Long id;

    public String uuid;

    public String name;

    public String description;

    public String points;

    public Boolean status;

    public LocalDate creationDate;

    public LocalDate deathline;

}
