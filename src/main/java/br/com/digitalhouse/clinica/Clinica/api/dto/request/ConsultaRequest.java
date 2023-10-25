package br.com.digitalhouse.clinica.Clinica.api.dto.request;


import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Getter
@Setter
@NoArgsConstructor
public class ConsultaRequest {

    @NotNull
    private UUID pacienteId;
    @NotNull
    private UUID dentistaId;
    @NotNull
    private UUID clinicaId;
    @NotNull
    private String data_consulta;
    @NotNull
    private String descricao;
    @NotNull
    private Boolean cancelada;
    @NotNull
    private String razao_cancelamento;
}

