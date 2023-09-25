package br.com.digitalhouse.clinica.Clinica.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
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

    @Column(name = "created_at")
    LocalDateTime CreatedAt;

    @Column(name = "updated_at")
    LocalDateTime UpdatedAt;

    @Column(nullable = false, length = 1)
    private String sexo;


    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(
            name = "contato_id",
            referencedColumnName = "id",
            foreignKey =
            @ForeignKey(name = "fk_contato_dentista"))
    private Contato contato;

    @ManyToMany(mappedBy = "dentistas")
    private Set<Clinica> clinicas = new HashSet<>();

}
