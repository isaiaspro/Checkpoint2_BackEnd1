package br.com.digitalhouse.clinica.Clinica.domain.service.Impl;

import br.com.digitalhouse.clinica.Clinica.domain.entity.Clinica;
import br.com.digitalhouse.clinica.Clinica.domain.repository.ClinicaRepository;
import br.com.digitalhouse.clinica.Clinica.domain.service.ClinicaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
@Service
public class ClinicaServiceImpl implements ClinicaService {

    private  final ClinicaRepository clinicaRepository;
@Autowired
    public ClinicaServiceImpl(ClinicaRepository clinicaRepository) {
        this.clinicaRepository = clinicaRepository;
    }


    @Override
    public Clinica criarClinica(Clinica clinica) {
        return this.clinicaRepository.save(clinica);
    }

    @Override
    public Clinica buscarClinicaPorId(UUID id) {
        return this.clinicaRepository
                .findById(id)
                .orElseThrow();
    }

    @Override
    public List<Clinica> buscarClinicasTermo(String termo) {
        return this.clinicaRepository.findAll();
    }


    @Override
    public Clinica atualizarClinica(Clinica clinica) {
        return this.clinicaRepository.save(clinica);
    }

    @Override
    public boolean excluirClinica(UUID id) {
        if (clinicaRepository.existsById(id)) {
            // Se existir, exclua a Clinica
            clinicaRepository.deleteById(id);
            return true; // Indica que a exclusão foi bem-sucedida
        }
        return false; // Indica que a clinica não foi encontrado para exclusão
    }
}
