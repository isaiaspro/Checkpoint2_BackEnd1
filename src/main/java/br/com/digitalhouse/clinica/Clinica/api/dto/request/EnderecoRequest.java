package br.com.digitalhouse.clinica.Clinica.api.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnderecoRequest {
    @NotNull
    private String logradouro;
    @NotNull
    private String bairro;
    @NotNull
    private String cidade;
    @NotNull
    private String estado;
    @NotNull
    private String cep;
}
