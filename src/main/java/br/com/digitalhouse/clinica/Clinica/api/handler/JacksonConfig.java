package br.com.digitalhouse.clinica.Clinica.api.handler;

import br.com.digitalhouse.clinica.Clinica.domain.service.PacienteService;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JacksonConfig {

    @Autowired
    private PacienteService pacienteService; // Certifique-se de injetar pacienteService

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();

        // Configuração relacionada a datas (JavaTimeModule)
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        // Configuração para não incluir valores nulos
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        // Configuração para evitar falha na serialização de beans vazios
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);

        return objectMapper;
    }
}

