package br.com.digitalhouse.clinica.Clinica.api.controller;

import br.com.digitalhouse.clinica.Clinica.api.dto.request.ClinicaRequest;
import br.com.digitalhouse.clinica.Clinica.api.dto.response.*;
import br.com.digitalhouse.clinica.Clinica.domain.entity.Clinica;
import br.com.digitalhouse.clinica.Clinica.domain.entity.Contato;
import br.com.digitalhouse.clinica.Clinica.domain.entity.Endereco;
import br.com.digitalhouse.clinica.Clinica.domain.service.ClinicaService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/v1/clinicas")
public class ClinicaController {

private final ClinicaService clinicaService;

@Autowired
    public ClinicaController(ClinicaService clinicaService) {
        this.clinicaService = clinicaService;
    }

    @GetMapping("{id}")
    public ResponseEntity<ClinicaResponse> buscarclinicaPorId(@PathVariable UUID id) {
        log.info("chamando buscar clinica por id: " + id);

        Clinica clinica = clinicaService.buscarClinicaPorId(id);
        if (clinica == null) {
            throw new ResourceNotFoundException("clinica não encontrado com o ID: " + id);
        }
        ClinicaResponse response = clinicaResponseByclinica(clinica);
        return ResponseEntity.ok(response);

    }


    /*@GetMapping
    ResponseEntity<ClinicaWrapperResponse> buscarTodosPorTermo(@RequestParam String termo) {
        List<Clinica> clinicas = clinicaService.buscarClinicasTermo(termo);
        ClinicaWrapperResponse clinicaWrapperResponse = new ClinicaWrapperResponse();
        clinicaWrapperResponse.setClinicas(clinicas.stream().map(clinica -> {
            ClinicaListResponse clinicaListResponse = new ClinicaListResponse();
            clinicaListResponse.setId(clinica.getId());
            clinicaListResponse.setNome(clinica.getNome());
            clinicaListResponse.setCnpj(clinica.getCnpj());
            return clinicaListResponse;
        }).toList());
        return ResponseEntity.ok(clinicaWrapperResponse);
    }*/

    @GetMapping
    ResponseEntity<ClinicaWrapperResponse> buscarTodasAsClinicas() {
        List<Clinica> clinicas = clinicaService.buscarTodasAsClinicas();
        List<ClinicaListResponse> clinicasListResponse = clinicas.stream().map(clinica -> {
            ClinicaListResponse response = new ClinicaListResponse();
            response.setId(UUID.fromString(clinica.getId().toString()));
            response.setNome(clinica.getNome());
            response.setCnpj(clinica.getCnpj());
            return response;
        }).collect(Collectors.toList());

        ClinicaWrapperResponse wrapperResponse = new ClinicaWrapperResponse();
        wrapperResponse.setClinicas(clinicasListResponse);

        return ResponseEntity.ok(wrapperResponse);
    }


    @PutMapping("{id}")
    public ResponseEntity<String> atualizarClinica(@PathVariable UUID id, @RequestBody Clinica clinica) {
        log.info("chamando atualizar clinica");
        clinica.setId(id);
        clinicaService.atualizarClinica(clinica);
        return ResponseEntity.ok("clinica atualizado com sucesso!");
    }


    @PostMapping
    ResponseEntity<UUID> criarClinica(@RequestBody @Valid ClinicaRequest request) {
        Clinica clinica = new Clinica();
        clinica.setNome(request.getNome());
        clinica.setCnpj(request.getCnpj());
        clinica.setRazao_social(request.getRazao_social());
        clinica.setDescricao(request.getDescricao());



        Endereco endereco = new Endereco();
        endereco.setId(request.getEndereco().getId());
        endereco.setLogradouro(request.getEndereco().getLogradouro());
        endereco.setBairro(request.getEndereco().getBairro());
        endereco.setCep(request.getEndereco().getCep());
        endereco.setCidade(request.getEndereco().getCidade());
        endereco.setEstado(request.getEndereco().getEstado());

        clinica.setEndereco(endereco);


        Contato contato = new Contato();
        contato.setId(request.getContato().getId());
        contato.setEmail(request.getContato().getEmail());
        contato.setTelefone(request.getContato().getTelefone());

        clinica.setContato(contato);

        Instant now = ZonedDateTime.now().toInstant();
        clinica.setCreatedAt(now);
        clinica.setUpdatedAt(now);

        Clinica clinicaCriado = clinicaService.criarClinica(clinica);

        return ResponseEntity.ok(clinicaCriado.getId());

    }


    @DeleteMapping("{id}")
    public ResponseEntity<String> excluirclinica(@PathVariable UUID id) {
        log.info("chamando excluir clinica");
        boolean sucesso = clinicaService.excluirClinica(id);
        if (sucesso) {
            return ResponseEntity.ok("clinica excluída com sucesso!");
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Clínica não encontrada com o ID: " + id);
        }
    }


    private ClinicaResponse clinicaResponseByclinica(Clinica clinica) {
        ClinicaResponse clinicaResponse = new ClinicaResponse();
        clinicaResponse.setNome(clinica.getNome());
        clinicaResponse.setCnpj(clinica.getCnpj());
        clinicaResponse.setRazao_social(clinica.getRazao_social());
        clinicaResponse.setDescricao(clinica.getDescricao());

        clinicaResponse.setId(clinica.getId());

        ClinicaResponse.Endereco enderecoResponse = new ClinicaResponse.Endereco();
        enderecoResponse.setId(clinica.getEndereco().getId());
        enderecoResponse.setLogradouro(clinica.getEndereco().getLogradouro());
        enderecoResponse.setBairro(clinica.getEndereco().getBairro());
        enderecoResponse.setCidade(clinica.getEndereco().getCidade());
        enderecoResponse.setEstado(clinica.getEndereco().getEstado());
        enderecoResponse.setCep(clinica.getEndereco().getCep());

        clinicaResponse.setEndereco(enderecoResponse);



        ClinicaResponse.Contato contatoResponse = new ClinicaResponse.Contato();
        contatoResponse.setId(clinica.getContato().getId());
        contatoResponse.setEmail(clinica.getContato().getEmail());
        contatoResponse.setTelefone(clinica.getContato().getTelefone());

        clinicaResponse.setContato(contatoResponse);



        return clinicaResponse;

    }

}
