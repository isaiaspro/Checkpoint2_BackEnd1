package br.com.digitalhouse.clinica.Clinica.api.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class ClinicaWrapperResponse {
    private List<ClinicaListResponse> clinicas;
}
