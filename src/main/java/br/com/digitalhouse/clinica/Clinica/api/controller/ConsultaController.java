package br.com.digitalhouse.clinica.Clinica.api.controller;


import br.com.digitalhouse.clinica.Clinica.api.dto.request.ConsultaRequest;
import br.com.digitalhouse.clinica.Clinica.domain.entity.Clinica;
import br.com.digitalhouse.clinica.Clinica.domain.entity.Consulta;
import br.com.digitalhouse.clinica.Clinica.domain.entity.Dentista;
import br.com.digitalhouse.clinica.Clinica.domain.entity.Paciente;
import br.com.digitalhouse.clinica.Clinica.domain.exception.ClinicaNotFoundException;
import br.com.digitalhouse.clinica.Clinica.domain.exception.DentistaNotFoundException;
import br.com.digitalhouse.clinica.Clinica.domain.exception.PacienteNotFoundException;
import br.com.digitalhouse.clinica.Clinica.domain.repository.ClinicaRepository;
import br.com.digitalhouse.clinica.Clinica.domain.repository.DentistaRepository;
import br.com.digitalhouse.clinica.Clinica.domain.repository.PacienteRepository;
import br.com.digitalhouse.clinica.Clinica.domain.service.ConsultaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;
@Slf4j
@RestController
@RequestMapping("/v1/consultas")
public class ConsultaController {

    private final ConsultaService consultaService;
    private final PacienteRepository pacienteRepository;
    private final DentistaRepository dentistaRepository;
    private final ClinicaRepository clinicaRepository;


    @Autowired
    public ConsultaController(ConsultaService consultaService, PacienteRepository pacienteRepository, DentistaRepository dentistaRepository, ClinicaRepository clinicaRepository) {
        this.consultaService = consultaService;
        this.pacienteRepository = pacienteRepository;
        this.dentistaRepository = dentistaRepository;
        this.clinicaRepository = clinicaRepository;
    }

    // Endpoint para criar uma nova consulta
    @PostMapping
    public ResponseEntity<String> criarNovaConsulta(@RequestBody ConsultaRequest request) {
        try {

            Paciente paciente = pacienteRepository.findById(request.getPacienteId())
                    .orElseThrow(() -> new PacienteNotFoundException("Paciente não encontrado com o ID fornecido"));


            Dentista dentista = dentistaRepository.findById(request.getDentistaId())
                    .orElseThrow(() -> new DentistaNotFoundException("Dentista não encontrado com o ID fornecido"));


            Clinica clinica = clinicaRepository.findById(request.getClinicaId())
                    .orElseThrow(() -> new ClinicaNotFoundException("Clínica não encontrada com o ID fornecido"));


            Consulta consulta = new Consulta();
            consulta.setPaciente(paciente);
            consulta.setDentista(dentista);
            consulta.setClinica(clinica);
            consulta.setData_consulta(LocalDate.parse(request.getData_consulta()));
            consulta.setDescricao(request.getDescricao());
            consulta.setCancelado(request.getCancelada());
            consulta.setMotivo_cancelamento(request.getRazao_cancelamento());

            Instant now = ZonedDateTime.now().toInstant();
            consulta.setCreatedAt(now);
            consulta.setUpdatedAt(now);

            Consulta consultacriada = consultaService.criarConsulta(consulta);

            return ResponseEntity.ok("Consulta criada com sucesso com ID: " + consulta.getId());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao criar a consulta");
        }
    }



    @GetMapping("/{id}")
    public ResponseEntity<Consulta> buscarConsultaPorId(@PathVariable UUID id) {
        Consulta consulta = consultaService.buscarConsultaPorId(id);
        if (consulta != null) {
            return ResponseEntity.ok(consulta);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Consulta>> buscarTodasAsConsultas() {
        List<Consulta> consultas = consultaService.buscarTodasAsConsultas();
        return ResponseEntity.ok(consultas);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Consulta> atualizarConsulta(@PathVariable UUID id, @RequestBody Consulta consulta) {
        consulta.setId(id);
        Consulta consultaAtualizada = consultaService.atualizarConsulta(consulta);
        if (consultaAtualizada != null) {
            return ResponseEntity.ok(consultaAtualizada);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @DeleteMapping("{id}")
    public ResponseEntity<String> excluirconsulta(@PathVariable UUID id) {
        log.info("chamando excluir consulta");
        boolean sucesso = consultaService.excluirConsulta(id);
        if (sucesso) {
            return ResponseEntity.ok("consulta excluída com sucesso!");
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Consulta não encontrada com o ID: " + id);
        }
    }
}
