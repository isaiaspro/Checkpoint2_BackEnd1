package br.com.digitalhouse.clinica.Clinica.domain.repository;

import br.com.digitalhouse.clinica.Clinica.domain.entity.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EnderecoRespository extends JpaRepository<Endereco, UUID> {
}
