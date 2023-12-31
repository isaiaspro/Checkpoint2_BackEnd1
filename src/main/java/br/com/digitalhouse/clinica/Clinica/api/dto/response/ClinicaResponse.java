package br.com.digitalhouse.clinica.Clinica.api.dto.response;

import br.com.digitalhouse.clinica.Clinica.domain.entity.Contato;
import br.com.digitalhouse.clinica.Clinica.domain.entity.Endereco;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ClinicaResponse {

    private UUID id;
    private String nome;
    private String cnpj;
    private String razao_social;
    private String descricao;
    private Endereco endereco;
    private Contato contato;

    @Getter
    @Setter
    public static class Endereco {

        private UUID id;

        private String logradouro;

        private String bairro;

        private String cidade;

        private String estado;
        private String cep;
    }
    @Getter
    @Setter
    public static class Contato {
        private UUID id;
        private String telefone;
        private String email;
    }


}
