package br.com.digitalhouse.clinica.Clinica.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
@Slf4j
@Getter
@Setter
@Entity
public class Dentista {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, length = 255)
private String nome;

    @Column(name = "data_nascimento")
private LocalDate dataNascimento;

    @Column(nullable = false, length = 80)
private EspecialidadeEnum Especialidade;

    @Transient
    @Column(columnDefinition = "DATETIME")
    private Instant createdAt;
    @Transient
    @Column(columnDefinition = "DATETIME")
    private Instant updatedAt;
    @Column(length = 1, nullable = false)
    private String sexo;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(
            name = "contact_id",
            referencedColumnName = "id",
            foreignKey =
            @ForeignKey(name = "fk_contact_dentist"))
    private Contato contato;
    @PrePersist
    protected void onCreate() {
        createdAt = Instant.now();
        updatedAt = Instant.now();
        log.info("New dentist created: {}", nome);
    }
    @PreUpdate
    protected void onUpdate() {
        updatedAt = Instant.now();
        log.info("Dentist updated : {}", nome);
    }

}
