package br.com.digitalhouse.clinica.Clinica.domain.exception;

import java.util.UUID;

public class ConsultaNotFoundException extends RuntimeException{
    public ConsultaNotFoundException(UUID id) {
        super("Consulta %s não encontrada".formatted(id));
    }
}
