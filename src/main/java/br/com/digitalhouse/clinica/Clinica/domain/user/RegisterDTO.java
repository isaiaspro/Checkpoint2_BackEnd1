package br.com.digitalhouse.clinica.Clinica.domain.user;

public record RegisterDTO(String login, String password, UserRole role) {
}
