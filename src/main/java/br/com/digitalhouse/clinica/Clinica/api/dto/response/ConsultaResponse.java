package br.com.digitalhouse.clinica.Clinica.api.dto.response;

import br.com.digitalhouse.clinica.Clinica.domain.entity.*;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.time.LocalDate;
import java.util.UUID;
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ConsultaResponse {
    private UUID id;
    private Paciente paciente;
    private Dentista dentista;
    private Clinica clinica;
    private LocalDate data_consulta;
    private Instant createdAt;
    private Instant updatedAt;
    private Endereco endereco;
    private Contato contato;
}

