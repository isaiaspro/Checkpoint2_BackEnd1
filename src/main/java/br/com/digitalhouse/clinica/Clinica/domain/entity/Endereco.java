package br.com.digitalhouse.clinica.Clinica.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
public class Endereco {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(length = 100)
    private String logradouro;
    @Column(length = 100)
    private String bairro;
    @Column(length = 100)
    private String cidade;
    @Column(length = 100)
    private String estado;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
