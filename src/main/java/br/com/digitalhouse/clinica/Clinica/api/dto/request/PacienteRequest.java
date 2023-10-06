package br.com.digitalhouse.clinica.Clinica.api.dto.request;

import br.com.digitalhouse.clinica.Clinica.api.dto.response.ClinicaResponse;
import br.com.digitalhouse.clinica.Clinica.domain.entity.Contato;
import br.com.digitalhouse.clinica.Clinica.domain.entity.Endereco;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;
@Getter
@Setter
public class PacienteRequest {
    @NotNull
    private String nome;
    @NotNull
    private LocalDate data_nascimento;
    @NotNull
    private String sexo;
    @NotNull
    private Endereco endereco;
    @NotNull
    private Contato contato;
}
