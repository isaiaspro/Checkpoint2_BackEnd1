package br.com.digitalhouse.clinica.Clinica.domain.service;

import br.com.digitalhouse.clinica.Clinica.domain.entity.Dentista;

import java.util.List;
import java.util.UUID;

public interface DentistaService {
    Dentista criarDentista(Dentista dentista);

    Dentista buscarDentistaPorId(UUID id);

    List<Dentista> buscarTodosOsDentistas();

    List<Dentista> buscarDentistasTermo(String termo);

    Dentista atualizarDentista(Dentista dentista);

    boolean excluirDentista(UUID id);
}
