package br.com.digitalhouse.clinica.Clinica.api.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContatoRequest {
    @NotNull
    private String email;
    @NotNull
    private String telefone;
}
