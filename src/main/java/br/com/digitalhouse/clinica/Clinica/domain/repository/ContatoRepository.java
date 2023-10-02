package br.com.digitalhouse.clinica.Clinica.domain.repository;

import br.com.digitalhouse.clinica.Clinica.domain.entity.Contato;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ContatoRepository extends JpaRepository<Contato, UUID> {
}
