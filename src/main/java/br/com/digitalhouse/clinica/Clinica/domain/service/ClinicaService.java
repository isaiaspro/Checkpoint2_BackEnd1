package br.com.digitalhouse.clinica.Clinica.domain.service;

import br.com.digitalhouse.clinica.Clinica.domain.entity.Clinica;


import java.util.List;
import java.util.UUID;

public interface ClinicaService {
    Clinica criarClinica(Clinica clinica);

    Clinica buscarClinicaPorId(UUID id);

    List<Clinica> buscarClinicasTermo(String termo);

    List<Clinica> buscarTodasAsClinicas();

    Clinica atualizarClinica(Clinica clinica);


    boolean excluirClinica(UUID id);
}
