package com.example.taks_microservices.Models.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;


@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "taks")
public class TaksEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false, unique = true)
    @JsonIgnore
    private Long id;

    @Column(nullable = false, length = 100, unique = true)
    private String uuid;

    @Column(nullable = false, length = 100, unique = true)
    private String name;

    @Column(nullable = false, length = 300)
    private String description;

    @Column(nullable = false, length = 300)
    private String points;

    private Boolean status = true;

    @CreationTimestamp
    private LocalDate creationDate;

    private LocalDate deathline;

}
