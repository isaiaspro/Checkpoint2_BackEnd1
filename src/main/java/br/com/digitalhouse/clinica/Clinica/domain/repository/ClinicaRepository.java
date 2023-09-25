package br.com.digitalhouse.clinica.Clinica.domain.repository;

import br.com.digitalhouse.clinica.Clinica.domain.entity.Clinica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface ClinicaRepository extends JpaRepository<Clinica, UUID> {
}
