package br.com.digitalhouse.clinica.Clinica.api.controller;

import br.com.digitalhouse.clinica.Clinica.api.dto.request.PacienteRequest;
import br.com.digitalhouse.clinica.Clinica.api.dto.response.*;
import br.com.digitalhouse.clinica.Clinica.domain.entity.Contato;
import br.com.digitalhouse.clinica.Clinica.domain.entity.Endereco;
import br.com.digitalhouse.clinica.Clinica.domain.entity.Paciente;
import br.com.digitalhouse.clinica.Clinica.domain.service.PacienteService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("v1/pacientes")
public class PacienteController {


    private final PacienteService pacienteService;


@Autowired
    public PacienteController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }


    @GetMapping("{id}")
    public ResponseEntity<PacienteResponse> buscarPacientePorId(@PathVariable UUID id) {
        log.info("chamando buscar Paciente por id: " + id);

        Paciente paciente = pacienteService.buscarPacientePorId(id);
        if (paciente == null) {
            throw new ResourceNotFoundException("Paciente não encontrado com o ID: " + id);
        }
        PacienteResponse response = pacienteResponseByPaciente(paciente);
        return ResponseEntity.ok(response);

    }

/*
    @GetMapping
    ResponseEntity<PacienteWrapperResponse> buscarTodosPorTermo(@RequestParam String termo) {
        List<Paciente> pacientes = pacienteService.buscarPacientesTermo(termo);
        PacienteWrapperResponse pacienteWrapperResponse = new PacienteWrapperResponse();
        pacienteWrapperResponse.setPacientes(pacientes.stream().map(paciente -> {
            PacienteListResponse pacienteListResponse = new PacienteListResponse();
            pacienteListResponse.setId(paciente.getId());
            pacienteListResponse.setNome(paciente.getNome());
            pacienteListResponse.setData_nascimento(paciente.getData_nascimento());
            pacienteListResponse.setSexo(paciente.getSexo());
            pacienteListResponse.setEndereco(paciente.getEndereco());
            pacienteListResponse.setContato(paciente.getContato());
            return pacienteListResponse;
        }).toList());
        return ResponseEntity.ok(pacienteWrapperResponse);
    }
*/

    @GetMapping
    ResponseEntity<PacienteWrapperResponse> buscarTodosOsPacientes() {
        List<Paciente> pacientes = pacienteService.buscarTodosOsPacientes();
        List<PacienteListResponse> pacientesListResponse = pacientes.stream().map(paciente -> {
            PacienteListResponse response = new PacienteListResponse();
            response.setId(UUID.fromString(paciente.getId().toString()));
            response.setNome(paciente.getNome());
            return response;
        }).collect(Collectors.toList());

        PacienteWrapperResponse wrapperResponse = new PacienteWrapperResponse();
        wrapperResponse.setPacientes(pacientesListResponse);

        return ResponseEntity.ok(wrapperResponse);
    }


    @PutMapping("{id}")
    public ResponseEntity<String> atualizarPaciente(@PathVariable UUID id, @RequestBody Paciente paciente) {
        log.info("chamando atualizar paciente");
        paciente.setId(id);
        pacienteService.atualizarPaciente(paciente);
        return ResponseEntity.ok("Paciente atualizado com sucesso!");
    }


    @PostMapping
    ResponseEntity<UUID> criarPaciente(@RequestBody @Valid PacienteRequest request) {
        Paciente paciente = new Paciente();
        paciente.setNome(request.getNome());
        paciente.setData_nascimento(request.getData_nascimento());
        paciente.setSexo(request.getSexo());


        Endereco endereco = new Endereco();
        endereco.setLogradouro(request.getEndereco().getLogradouro());
        endereco.setBairro(request.getEndereco().getBairro());
        endereco.setCep(request.getEndereco().getCep());
        endereco.setCidade(request.getEndereco().getCidade());
        endereco.setEstado(request.getEndereco().getEstado());

        Contato contato = new Contato();
        contato.setEmail(request.getContato().getEmail());
        contato.setTelefone(request.getContato().getTelefone());


        paciente.setEndereco(endereco);
        paciente.setContato(contato);

        Instant now = ZonedDateTime.now().toInstant();
        paciente.setCreatedAt(now);
        paciente.setUpdatedAt(now);
        Paciente pacienteCriado = pacienteService.criarPaciente(paciente);
        return ResponseEntity.ok(pacienteCriado.getId());

    }


    @DeleteMapping("{id}")
    public ResponseEntity<String> excluirPaciente(@PathVariable UUID id) {
        log.info("chamando excluir paciente");
        boolean sucesso = pacienteService.excluirPaciente(id);
        if (sucesso) {
            return ResponseEntity.ok("Paciente excluído com sucesso!");
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Paciente não encontrada com o ID: " + id);        }
    }


    private PacienteResponse pacienteResponseByPaciente(Paciente paciente) {
        PacienteResponse pacienteResponse = new PacienteResponse();
        pacienteResponse.setNome(paciente.getNome());
        pacienteResponse.setData_nascimento(paciente.getData_nascimento());
        pacienteResponse.setSexo(paciente.getSexo());

        pacienteResponse.setId(paciente.getId());

        PacienteResponse.Endereco enderecoResponse = new PacienteResponse.Endereco();
        enderecoResponse.setId(paciente.getEndereco().getId());
        enderecoResponse.setLogradouro(paciente.getEndereco().getLogradouro());
        enderecoResponse.setBairro(paciente.getEndereco().getBairro());
        enderecoResponse.setCep(paciente.getEndereco().getCep());
        enderecoResponse.setCidade(paciente.getEndereco().getCidade());
        enderecoResponse.setEstado(paciente.getEndereco().getEstado());

        PacienteResponse.Contato contatoResponse = new PacienteResponse.Contato();
        contatoResponse.setId(paciente.getContato().getId());
        contatoResponse.setEmail(paciente.getContato().getEmail());
        contatoResponse.setTelefone(paciente.getContato().getTelefone());

        pacienteResponse.setEndereco(enderecoResponse);
        pacienteResponse.setContato(contatoResponse);
        return pacienteResponse;

    }


}

