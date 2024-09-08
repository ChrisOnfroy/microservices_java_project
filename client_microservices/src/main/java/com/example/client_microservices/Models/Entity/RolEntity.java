package com.example.client_microservices.Models.Entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Builder
@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "rol")
public class RolEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false, unique = true)
    @JsonIgnore
    private Long id;

    @Column(updatable = false, nullable = false, length = 100, unique = true)
    private String uuid;


    @Column(updatable = false, nullable = false, length = 100, unique = true)
    private String String;

    @Builder.Default
    @OneToMany(mappedBy="rol", cascade = CascadeType.MERGE, fetch = FetchType.LAZY, orphanRemoval = true)
    @JsonBackReference
    private List<ClientEntity> clientEntityList = new ArrayList<>();
}
