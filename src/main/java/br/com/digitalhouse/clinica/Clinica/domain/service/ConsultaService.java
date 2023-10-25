package br.com.digitalhouse.clinica.Clinica.domain.service;

import br.com.digitalhouse.clinica.Clinica.domain.entity.Consulta;


import java.util.List;
import java.util.UUID;

public interface ConsultaService {
    Consulta criarConsulta(Consulta consulta);

    Consulta buscarConsultaPorId(UUID id);

    List<Consulta> buscarConsultasTermo(String termo);

    List<Consulta> buscarTodasAsConsultas();

    Consulta atualizarConsulta(Consulta consulta);


    boolean excluirConsulta(UUID id);
}

