package br.com.digitalhouse.clinica.Clinica.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.UUID;
@Slf4j
@Entity
@Getter
@Setter
public class Endereco {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(length = 100)
    private String logradouro;
    @Column(length = 100)
    private String bairro;
    @Column(length = 100)
    private String cidade;
    @Column(length = 100)
    private String estado;
    @Column(length = 10)
    private String cep;
    @Transient
    @Column(columnDefinition = "DATETIME")
    private Instant createdAt;
    @Transient
    @Column(columnDefinition = "DATETIME")
    private Instant updatedAt;
    @PrePersist
    protected void onCreate() {
        createdAt = Instant.now();
        updatedAt = Instant.now();
        log.info("New registered address: {}, {},{},{},{}",logradouro, bairro, cidade,estado, cep);
    }
    @PreUpdate
    protected void onUpdate() {
        updatedAt = Instant.now();
        log.info("Updated address: {}, {}", bairro, cidade);
    }
}
