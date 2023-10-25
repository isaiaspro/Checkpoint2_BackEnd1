package br.com.digitalhouse.clinica.Clinica.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.time.Instant;
import java.util.UUID;

@Slf4j
@Getter
@Setter
@Entity
public class Contato {
    @Id
    @GeneratedValue (strategy = GenerationType.UUID)
    private UUID id;
    @Column(length = 100)
    private String email;
    @Column(length = 15)
    private String telefone;
    @Column(name = "fax")
    private String fax;
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
        log.info("Novo email registrado: {}", email);
        log.info("Novo telefone registrado: {}", telefone);

    }
    @PreUpdate
    protected void onUpdate() {
        updatedAt = Instant.now();
        log.info("Email atualizado: {}", email);
        log.info("Telefone Atualizado: {}", telefone);

    }


}

