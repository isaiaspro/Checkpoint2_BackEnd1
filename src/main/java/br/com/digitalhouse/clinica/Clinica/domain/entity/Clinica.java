package br.com.digitalhouse.clinica.Clinica.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.br.CNPJ;

import java.time.Instant;
import java.util.UUID;
@Slf4j
@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
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
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(
            name = "endereco_id",
            referencedColumnName = "id",
            foreignKey =
            @ForeignKey(name = "fk_endereco_clinica"))
    private Endereco endereco;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(
            name = "contato_id",
            referencedColumnName = "id",
            foreignKey =
            @ForeignKey(name = "fk_contato_clinica"))
    private Contato contato;

    private Instant createdAt;
    private Instant updatedAt;
    @PrePersist
    protected void onCreate() {
        createdAt = Instant.now();
        updatedAt = Instant.now();
        log.info("Nova Clinica criada: {}", nome);
    }
    @PreUpdate
    protected void onUpdate() {
        updatedAt = Instant.now();
        log.info("Clinica atualizada : {}", nome);
    }
}
