package com.example.client_microservices.Models.Dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ClientDto {
    public Long id;

    public String uuid;

    public String username;

    public String password;

    public LocalDateTime creationDate;

    public Boolean status;

    public String email;

    public String rol;

}
