package br.com.digitalhouse.clinica.Clinica.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.time.Instant;
import java.time.LocalDateTime;
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
        log.info("New email registered: {}", email);
        log.info("New phone registered: {}", telefone);

    }
    @PreUpdate
    protected void onUpdate() {
        updatedAt = Instant.now();
        log.info("Updated email contact: {}", email);
        log.info("Updated phone contact: {}", telefone);

    }


}

