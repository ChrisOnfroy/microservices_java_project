package com.example.client_microservices.Models.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;



@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "client")
public class ClientEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false, unique = true)
    @JsonIgnore
    private Long id;

    @Column(updatable = false, nullable = false, length = 100, unique = true)
    private String uuid;

    @Column(updatable = false, nullable = false, length = 100, unique = true)
    private String username;

    @Column(updatable = false, nullable = false, length = 100, unique = true)
    private String password;

    @CreationTimestamp
    private LocalDate creationDate;

    private Boolean status = true;

    @Column(updatable = false, nullable = false, length = 300, unique = true)
    private String email;

}
