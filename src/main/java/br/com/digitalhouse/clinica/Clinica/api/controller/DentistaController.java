package br.com.digitalhouse.clinica.Clinica.api.controller;


import br.com.digitalhouse.clinica.Clinica.api.dto.request.DentistaRequest;
import br.com.digitalhouse.clinica.Clinica.api.dto.response.*;
import br.com.digitalhouse.clinica.Clinica.domain.entity.Contato;
import br.com.digitalhouse.clinica.Clinica.domain.entity.Dentista;
import br.com.digitalhouse.clinica.Clinica.domain.entity.Paciente;
import br.com.digitalhouse.clinica.Clinica.domain.service.DentistaService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("v1/dentistas")
public class DentistaController {

    private final DentistaService dentistaService;

    @Autowired
    public DentistaController(DentistaService dentistaService) {
        this.dentistaService = dentistaService;
    }


    @GetMapping("{id}")
    public ResponseEntity<DentistaResponse> buscarDentistaPorId(@PathVariable UUID id) {
        log.info("chamando buscar dentista por id: " + id);

        Dentista dentista = dentistaService.buscarDentistaPorId(id);
        if (dentista == null) {
            throw new ResourceNotFoundException("Dentista não encontrado com o ID: " + id);
        }
        DentistaResponse response = dentistaResponseByDentista(dentista);
        return ResponseEntity.ok(response);

    }

/*
    @GetMapping
    ResponseEntity<DentistaWrapperResponse> buscarTodosPorTermo(@RequestParam String termo) {
        List<Dentista> dentistas = dentistaService.buscarDentistasTermo(termo);
        DentistaWrapperResponse dentistaWrapperResponse = new DentistaWrapperResponse();
        dentistaWrapperResponse.setDentistas(dentistas.stream().map(dentista -> {
            DentistaListResponse dentistaListResponse = new DentistaListResponse();
            dentistaListResponse.setId(dentista.getId());
            dentistaListResponse.setNome(dentista.getNome());
            dentistaListResponse.setDataNascimento(dentista.getDataNascimento());
            dentistaListResponse.setEspecialidade(dentista.getEspecialidade());
            dentistaListResponse.setSexo(dentista.getSexo());
            dentistaListResponse.setContato(dentista.getContato());
            return dentistaListResponse;
        }).toList());
        return ResponseEntity.ok(dentistaWrapperResponse);
    }
*/

    @GetMapping
    ResponseEntity<DentistaWrapperResponse> buscarTodosOsDentistas() {
        List<Dentista> dentistas = dentistaService.buscarTodosOsDentistas();
        List<DentistaListResponse> dentistasListResponse = dentistas.stream().map(dentista -> {
            DentistaListResponse response = new DentistaListResponse();
            response.setId(UUID.fromString(dentista.getId().toString()));
            response.setNome(dentista.getNome());
            response.setEspecialidade(dentista.getEspecialidade());
            response.setSexo(dentista.getSexo());
            response.setContato(dentista.getContato());
            return response;
        }).collect(Collectors.toList());

        DentistaWrapperResponse wrapperResponse = new DentistaWrapperResponse();
        wrapperResponse.setDentistas(dentistasListResponse);

        return ResponseEntity.ok(wrapperResponse);
    }

    @PutMapping("{id}")
    public ResponseEntity<String> atualizarDentista(@PathVariable UUID id, @RequestBody Dentista dentista) {
        log.info("chamando atualizar dentista");
        dentista.setId(id);
        dentistaService.atualizarDentista(dentista);
        return ResponseEntity.ok("Dentista atualizado com sucesso!");
    }


    @PostMapping
    ResponseEntity<UUID> criarDentista(@RequestBody @Valid DentistaRequest request) {
        Dentista dentista = new Dentista();
        dentista.setNome(request.getNome());
        dentista.setDataNascimento(request.getDataNascimento());
        dentista.setEspecialidade(request.getEspecialidade());
        dentista.setSexo(request.getSexo());

        Contato contato = new Contato();
        contato.setEmail(request.getContato().getEmail());
        contato.setTelefone(request.getContato().getTelefone());



        dentista.setContato(contato);

        Instant now = ZonedDateTime.now().toInstant();
        dentista.setCreatedAt(now);
        dentista.setUpdatedAt(now);
        Dentista dentistaCriado = dentistaService.criarDentista(dentista);
        return ResponseEntity.ok(dentistaCriado.getId());

    }


    @DeleteMapping("{id}")
    public ResponseEntity<String> excluirDentista(@PathVariable UUID id) {
        log.info("chamando excluir dentista");
        boolean sucesso = dentistaService.excluirDentista(id);
        if (sucesso) {
            return ResponseEntity.ok("Dentista excluído com sucesso!");
        } else {
            throw new ResourceNotFoundException("Dentista não encontrado com o ID: " + id);
        }
    }


    private DentistaResponse dentistaResponseByDentista(Dentista dentista) {
        DentistaResponse dentistaResponse = new DentistaResponse();
        dentistaResponse.setNome(dentista.getNome());
        dentistaResponse.setDataNascimento(dentista.getDataNascimento());
        dentistaResponse.setEspecialidade(dentista.getEspecialidade());
        dentistaResponse.setSexo(dentista.getSexo());

        dentistaResponse.setId(dentista.getId());

        DentistaResponse.Contato contatoResponse = new DentistaResponse.Contato();
        contatoResponse.setId(dentista.getContato().getId());
        contatoResponse.setEmail(dentista.getContato().getEmail());
        contatoResponse.setTelefone(dentista.getContato().getTelefone());


        dentistaResponse.setContato(contatoResponse);
        return dentistaResponse;

    }


}
