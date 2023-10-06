package br.com.digitalhouse.clinica.Clinica.domain.service;

import br.com.digitalhouse.clinica.Clinica.domain.entity.Clinica;
import br.com.digitalhouse.clinica.Clinica.domain.entity.Paciente;

import java.util.List;
import java.util.UUID;

public interface PacienteService {
    Paciente criarPaciente(Paciente Paciente);

    Paciente buscarPacientePorId(UUID id);
    List<Paciente> buscarTodosOsPacientes();

    List<Paciente> buscarPacientesTermo(String termo);

    Paciente atualizarPaciente(Paciente paciente);

    boolean excluirPaciente(UUID id);
}
