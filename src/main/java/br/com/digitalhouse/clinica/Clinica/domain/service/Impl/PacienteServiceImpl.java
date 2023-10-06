package br.com.digitalhouse.clinica.Clinica.domain.service.Impl;

import br.com.digitalhouse.clinica.Clinica.domain.entity.Clinica;
import br.com.digitalhouse.clinica.Clinica.domain.entity.Paciente;
import br.com.digitalhouse.clinica.Clinica.domain.repository.PacienteRepository;
import br.com.digitalhouse.clinica.Clinica.domain.service.PacienteService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
@Service
public class PacienteServiceImpl implements PacienteService {

    private final PacienteRepository pacienteRepository;

    public PacienteServiceImpl(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    @Override
    public Paciente criarPaciente(Paciente paciente) {
        return this.pacienteRepository.save(paciente);
    }

    @Override
    public Paciente buscarPacientePorId(UUID id) {
        return this.pacienteRepository
                .findById(id)
                .orElseThrow();
    }

    @Override
    public List<Paciente> buscarTodosOsPacientes() {
        return this.pacienteRepository.findAll();
    }


    @Override
    public List<Paciente> buscarPacientesTermo(String termo) {
        return this.pacienteRepository.findAll();
    }

    @Override
    public Paciente atualizarPaciente(Paciente paciente) {
        return this.pacienteRepository.save(paciente);
    }

    @Override
    public boolean excluirPaciente(UUID id) {
        if (pacienteRepository.existsById(id)) {
            // Se existir, exclua o dentista
            pacienteRepository.deleteById(id);
            return true; // Indica que a exclusão foi bem-sucedida
        }
        return false; // Indica que o dentista não foi encontrado para exclusão
    }
}
