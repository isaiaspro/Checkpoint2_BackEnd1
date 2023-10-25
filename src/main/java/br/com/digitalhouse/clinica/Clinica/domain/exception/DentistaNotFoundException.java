package br.com.digitalhouse.clinica.Clinica.domain.exception;

public class DentistaNotFoundException extends RuntimeException{
    public DentistaNotFoundException(String id) {
        super("Dentista %s não encontrado".formatted(id));
    }
}
