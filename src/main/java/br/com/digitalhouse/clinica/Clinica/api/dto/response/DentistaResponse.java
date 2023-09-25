package br.com.digitalhouse.clinica.Clinica.api.dto.response;

import br.com.digitalhouse.clinica.Clinica.api.dto.request.ContatoRequest;
import br.com.digitalhouse.clinica.Clinica.domain.entity.EspecialidadeEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
public class DentistaResponse {
    private UUID id;
    private String nome;
    private LocalDate dataNascimento;
    private EspecialidadeEnum Especialidade;
    private String sexo;
    private ContatoRequest contato;
}
