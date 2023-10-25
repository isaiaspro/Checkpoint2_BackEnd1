package br.com.digitalhouse.clinica.Clinica.domain.exception;

public class PacienteNotFoundException extends RuntimeException{
    public PacienteNotFoundException(String id) {
        super("Paciente %s não encontrada".formatted(id));
    }
}
