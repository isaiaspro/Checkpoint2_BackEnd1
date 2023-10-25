package br.com.digitalhouse.clinica.Clinica.api.dto.response;

import br.com.digitalhouse.clinica.Clinica.domain.entity.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.time.LocalDate;
import java.util.UUID;
@Getter
@Setter
public class ConsultaListResponse {
    private UUID id;
    private Paciente patiente;
    private Dentista dentista;
    private Clinica Clinica;
    private LocalDate data_consulta;
    private Instant createdAt;
    private Instant updatedAt;
    private Endereco endereco;
    private Contato contato;
}
