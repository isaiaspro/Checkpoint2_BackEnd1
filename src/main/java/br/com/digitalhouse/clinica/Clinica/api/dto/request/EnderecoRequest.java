package br.com.digitalhouse.clinica.Clinica.api.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnderecoRequest {
    private String logradouro;

    private String bairro;

    private String cidade;

    private String estado;
    private String cep;
}
