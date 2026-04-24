package com.example.client_microservices.Models.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
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

    @Column(nullable = false, length = 100, unique = true)
    private String username;

    @Column(nullable = false, length = 255)
    private String password;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDate creationDate;

    @Builder.Default
    @Column(nullable = false)
    private Boolean status = true;

    @Column(nullable = false, length = 300, unique = true)
    private String email;

    @ManyToOne
    @JoinColumn(name = "rol_id", nullable = false)
    private RolEntity rol;

}
