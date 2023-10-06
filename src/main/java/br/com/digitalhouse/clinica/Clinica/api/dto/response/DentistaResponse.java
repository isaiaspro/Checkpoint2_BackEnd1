package br.com.digitalhouse.clinica.Clinica.api.dto.response;

import br.com.digitalhouse.clinica.Clinica.domain.entity.Contato;
import br.com.digitalhouse.clinica.Clinica.domain.entity.Endereco;
import br.com.digitalhouse.clinica.Clinica.domain.entity.EspecialidadeEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DentistaResponse {
    private UUID id;
    private String nome;
    private LocalDate dataNascimento;
    private EspecialidadeEnum especialidade;
    private String sexo;
    private Contato contato;
    private Endereco endereco;


    @Getter
    @Setter
    public static class Contato {
        @JsonIgnore
        private UUID id;
        private String telefone;
        private String email;
    }
}
