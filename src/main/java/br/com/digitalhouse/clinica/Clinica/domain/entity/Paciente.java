package br.com.digitalhouse.clinica.Clinica.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;
@Getter
@Setter
@Entity
public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(length = 255)
    private String nome;
    @Column (name = "data_nascimento")
    private LocalDate data_nascimento;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
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

}
