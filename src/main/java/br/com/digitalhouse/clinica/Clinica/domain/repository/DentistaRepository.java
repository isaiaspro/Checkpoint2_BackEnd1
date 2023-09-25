package br.com.digitalhouse.clinica.Clinica.domain.repository;

import br.com.digitalhouse.clinica.Clinica.domain.entity.Dentista;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DentistaRepository extends JpaRepository<Dentista, UUID> {
}
