package br.com.digitalhouse.clinica.Clinica.api.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;
@Getter
@Setter
public class PacienteResponse {

    private UUID id;
    private String nome;
    private LocalDate data_nascimento;
    private String sexo;
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
