package br.com.digitalhouse.clinica.Clinica.domain.exception;

public class ClinicaNotFoundException extends RuntimeException{
    public ClinicaNotFoundException(String id) {
        super("Clinica %s não encontrada".formatted(id));
    }
}
