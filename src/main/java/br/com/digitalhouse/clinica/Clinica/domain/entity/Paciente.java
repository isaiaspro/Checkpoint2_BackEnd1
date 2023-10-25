package br.com.digitalhouse.clinica.Clinica.domain.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.time.Instant;
import java.time.LocalDate;
import java.util.UUID;
@Slf4j
@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(length = 255)
    private String nome;
    @Column (name = "data_nascimento")
    private LocalDate data_nascimento;
    @Transient
    @Column(columnDefinition = "DATETIME")
    private Instant createdAt;
    @Transient
    @Column(columnDefinition = "DATETIME")
    private Instant updatedAt;


    @Column(length = 1)
    private String sexo;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(
            name = "contato_id",
            referencedColumnName = "id",
            foreignKey =
            @ForeignKey(name = "fk_contato_paciente"))
    private Contato contato;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(
            name = "endereco_id",
            referencedColumnName = "id",
            foreignKey =
            @ForeignKey(name = "fk_endereco_paciente"))
    private Endereco endereco;

    @PrePersist
    protected void onCreate() {
        createdAt = Instant.now();
        updatedAt = Instant.now();
        log.info("Novo paciente Criado: {}", nome);
    }
    @PreUpdate
    protected void onUpdate() {
        updatedAt = Instant.now();
        log.info("Paciente Atualizado: {}", nome);
    }

}
