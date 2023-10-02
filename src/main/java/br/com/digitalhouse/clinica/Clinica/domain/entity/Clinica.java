package br.com.digitalhouse.clinica.Clinica.domain.entity;
import java.util.HashSet;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CNPJ;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Entity
public class Clinica {
@Id
@GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
@Column(name = "nome")
    private String nome;
@CNPJ
    private String cnpj;
    private String razao_social;
    private String descricao;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(
            name = "endereco_id",
            referencedColumnName = "id",
            foreignKey =
            @ForeignKey(name = "fk_endereco_clinica"))
    private Endereco endereco;

@OneToOne
@JoinColumn(
        name = "contato_id",
        referencedColumnName = "id",
        foreignKey =
        @ForeignKey(name = "fk_contato_clinica"))

    private Contato contato;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "clinica_dentista",
            joinColumns = @JoinColumn(name = "clinica_id"),
            inverseJoinColumns = @JoinColumn(name = "dentista_id"))
    private Set<Dentista> dentistas = new HashSet<>();
}
