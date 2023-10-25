package br.com.digitalhouse.clinica.Clinica.domain.service.Impl;

import br.com.digitalhouse.clinica.Clinica.domain.entity.Dentista;
import br.com.digitalhouse.clinica.Clinica.domain.repository.DentistaRepository;
import br.com.digitalhouse.clinica.Clinica.domain.service.DentistaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
@Service
public class DentistaServiceImpl implements DentistaService {

    private final DentistaRepository dentistaRepository;
    @Autowired
    public DentistaServiceImpl(DentistaRepository dentistaRepository){
        this.dentistaRepository = dentistaRepository;
    }
    @Override
    public Dentista criarDentista(Dentista dentista) {
        return this.dentistaRepository.save(dentista);
    }

    @Override
    public Dentista buscarDentistaPorId(UUID id) {

        return this.dentistaRepository
                .findById(id)
                .orElseThrow();
    }

    @Override
    public List<Dentista> buscarTodosOsDentistas() {
        return this.dentistaRepository.findAll();
    }

    @Override
    public List<Dentista> buscarDentistasTermo(String termo) {
        return this.dentistaRepository.findAll();
    }

    @Override
    public Dentista atualizarDentista(Dentista dentista) {
        return this.dentistaRepository.save(dentista);
    }

    @Override
    public boolean excluirDentista(UUID id) {

        if (dentistaRepository.existsById(id)) {
            // Se existir, exclua o dentista
            dentistaRepository.deleteById(id);
            return true; // Indica que a exclusão foi bem-sucedida
        }
        return false; // Indica que o dentista não foi encontrado para exclusão
    }
    }

