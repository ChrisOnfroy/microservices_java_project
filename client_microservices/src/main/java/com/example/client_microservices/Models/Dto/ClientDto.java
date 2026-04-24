package com.example.client_microservices.Models.Dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientDto {

    private String uuid;

    @NotBlank(message = "Username is required")
    @Size(max = 100, message = "Username cannot exceed 100 characters")
    private String username;

    @NotBlank(message = "Password is required")
    @Size(min = 8, max = 100, message = "Password must contain between 8 and 100 characters")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    private LocalDate creationDate;

    private Boolean status;

    @NotBlank(message = "Email is required")
    @Email(message = "Email must be valid")
    @Size(max = 300, message = "Email cannot exceed 300 characters")
    private String email;

    @NotBlank(message = "Role uuid is required")
    @JsonAlias("rol")
    private String roleUuid;

    private String roleName;
}
