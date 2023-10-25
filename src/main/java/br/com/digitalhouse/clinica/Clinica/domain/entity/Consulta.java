package br.com.digitalhouse.clinica.Clinica.domain.entity;

import jakarta.persistence.*;
import jdk.jfr.BooleanFlag;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;
@Slf4j
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Consulta {
    @Id
    @GeneratedValue (strategy = GenerationType.UUID)
    private UUID id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(
            name = "paciente_id",
            referencedColumnName = "id",
            foreignKey =
            @ForeignKey(name = "fk_paciente_id"))
    private Paciente paciente;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(
            name = "dentista_id",
            referencedColumnName = "id",
            foreignKey =
            @ForeignKey(name = "fk_dentista_id"))
    private Dentista dentista;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(
            name = "clinica_id",
            referencedColumnName = "id",
            foreignKey =
            @ForeignKey(name = "fk_clinica_id"))
    private Clinica clinica;

    private LocalDate data_consulta;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;
    private String descricao;
    @Column(length = 80)
    private String motivo_cancelamento;
    @BooleanFlag
    private Boolean cancelado;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(
            name = "paciente_id",
            referencedColumnName = "id",
            foreignKey =
            @ForeignKey(name = "fk_paciente_cnsulta"))
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
        log.info("Nova consulta cadastrada para: {}", paciente.getNome());
    }
    @PreUpdate
    protected void onUpdate() {
        updatedAt = Instant.now();
        log.info("Consulta Autalizada para: {}", paciente.getNome());
    }
}

