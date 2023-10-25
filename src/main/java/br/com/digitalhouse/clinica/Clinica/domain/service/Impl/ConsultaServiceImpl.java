package br.com.digitalhouse.clinica.Clinica.domain.service.Impl;


import br.com.digitalhouse.clinica.Clinica.domain.entity.Consulta;
import br.com.digitalhouse.clinica.Clinica.domain.repository.ConsultaRepository;
import br.com.digitalhouse.clinica.Clinica.domain.service.ConsultaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
@Service
public class ConsultaServiceImpl implements ConsultaService {
    private  final ConsultaRepository consultaRepository;
    @Autowired
    public ConsultaServiceImpl(ConsultaRepository consultaRepository) {
        this.consultaRepository = consultaRepository;
    }


    @Override
    public Consulta criarConsulta(Consulta consulta) {
        return this.consultaRepository.save(consulta);
    }

    @Override
    public Consulta buscarConsultaPorId(UUID id) {
        return this.consultaRepository
                .findById(id)
                .orElseThrow();
    }

    @Override
    public List<Consulta> buscarConsultasTermo(String termo) {
        return this.consultaRepository.findAll();
    }

    @Override
    public List<Consulta> buscarTodasAsConsultas() {
        return this.consultaRepository.findAll();
    }


    @Override
    public Consulta atualizarConsulta(Consulta consulta) {
        return this.consultaRepository.save(consulta);
    }

    @Override
    public boolean excluirConsulta(UUID id) {
        if (consultaRepository.existsById(id)) {
            // Se existir, exclua a Consulta
            consultaRepository.deleteById(id);
            return true; // Indica que a exclusão foi bem-sucedida
        }
        return false; // Indica que a consulta não foi encontrado para exclusão
    }
}
