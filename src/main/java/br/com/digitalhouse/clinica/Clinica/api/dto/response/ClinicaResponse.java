package br.com.digitalhouse.clinica.Clinica.api.dto.response;

import br.com.digitalhouse.clinica.Clinica.domain.entity.Contato;
import br.com.digitalhouse.clinica.Clinica.domain.entity.Endereco;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;
@Getter
@Setter
public class ClinicaResponse {

    private UUID id;
    private String nome;
    private String cnpj;
    private String razao_social;
    private String descricao;
    private Endereco endereco;
    private Contato contato;
}
