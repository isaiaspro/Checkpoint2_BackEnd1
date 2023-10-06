package br.com.digitalhouse.clinica.Clinica.api.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;
@Getter
@Setter
public class ContatoResponse {
    @JsonIgnore
    private UUID id;
    private String telefone;
    private String email;
}
