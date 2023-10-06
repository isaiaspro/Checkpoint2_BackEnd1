package br.com.digitalhouse.clinica.Clinica.api.dto.response;

import br.com.digitalhouse.clinica.Clinica.domain.entity.Contato;
import br.com.digitalhouse.clinica.Clinica.domain.entity.Endereco;
import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;
@Getter
@Setter
public class PacienteListResponse {
    private UUID id;
    private String nome;
    private LocalDate data_nascimento;
    private String sexo;
    private Endereco endereco;
    private Contato contato;
}
