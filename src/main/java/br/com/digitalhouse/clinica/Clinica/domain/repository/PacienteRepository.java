package br.com.digitalhouse.clinica.Clinica.domain.repository;

import br.com.digitalhouse.clinica.Clinica.domain.entity.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PacienteRepository extends JpaRepository<Paciente, UUID> {
}
