package br.com.digitalhouse.clinica.Clinica.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
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

    private LocalDate data_conslta;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;
    private String descricao;
    private boolean cancelado;
    @Column(length = 80)
    private String motivo_cancelamento;
}
