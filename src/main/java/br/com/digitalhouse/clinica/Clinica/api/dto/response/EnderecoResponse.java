package br.com.digitalhouse.clinica.Clinica.api.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;
@Getter
@Setter
public class EnderecoResponse {
    @JsonIgnore
    private UUID id;

    private String logradouro;

    private String bairro;

    private String cidade;

    private String estado;
    private String cep;
}
