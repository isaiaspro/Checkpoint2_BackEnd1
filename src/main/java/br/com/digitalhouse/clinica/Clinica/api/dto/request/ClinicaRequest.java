package br.com.digitalhouse.clinica.Clinica.api.dto.request;

import br.com.digitalhouse.clinica.Clinica.api.dto.response.ContatoResponse;
import br.com.digitalhouse.clinica.Clinica.api.dto.response.EnderecoResponse;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClinicaRequest {
    @NotNull
    private String nome;
    @NotNull
    private String cnpj;
    @NotNull
    private String razao_social;
    @NotNull
    private String descricao;
    private EnderecoResponse endereco;
    private ContatoResponse contato;
}
