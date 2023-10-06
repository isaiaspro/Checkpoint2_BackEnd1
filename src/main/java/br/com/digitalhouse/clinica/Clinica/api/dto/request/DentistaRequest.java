package br.com.digitalhouse.clinica.Clinica.api.dto.request;

import br.com.digitalhouse.clinica.Clinica.domain.entity.Contato;
import br.com.digitalhouse.clinica.Clinica.domain.entity.EspecialidadeEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class DentistaRequest {
    @NotBlank
    private String nome;
    @NotNull
    private LocalDate dataNascimento;
    @NotNull
    private EspecialidadeEnum Especialidade;
    @NotNull
    private String sexo;
    @NotNull
    private Contato contato;
}
