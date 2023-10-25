package br.com.digitalhouse.clinica.Clinica.api.handler;

import br.com.digitalhouse.clinica.Clinica.domain.exception.ClinicaNotFoundException;
import br.com.digitalhouse.clinica.Clinica.domain.exception.ConsultaNotFoundException;
import br.com.digitalhouse.clinica.Clinica.domain.exception.DentistaNotFoundException;
import br.com.digitalhouse.clinica.Clinica.domain.exception.PacienteNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(DentistaNotFoundException.class)
    public ResponseEntity<?> dentistaNotFoundExceptionHandler(PacienteNotFoundException exception) {
        return ResponseEntity.badRequest().body(
                """
                {
                    "status": 400,
                    "message": "%s"
                }
                """.formatted(exception.getMessage())
        );
    }

    @ExceptionHandler(PacienteNotFoundException.class)
    public ResponseEntity<?> paceinteNotFoundExceptionHandler(PacienteNotFoundException exception) {
        return ResponseEntity.badRequest().body(
                """
                {
                    "status": 400,
                    "message": "%s"
                }
                """.formatted(exception.getMessage())
        );
    }

    @ExceptionHandler(ClinicaNotFoundException.class)
    public ResponseEntity<?> clinicaNotFoundExceptionHandler(ClinicaNotFoundException exception) {
        return ResponseEntity.badRequest().body(
                """
                {
                    "status": 400,
                    "message": "%s"
                }
                """.formatted(exception.getMessage())
        );
    }

    @ExceptionHandler(ConsultaNotFoundException.class)
    public ResponseEntity<?> consultaNotFoundExceptionHandler(ConsultaNotFoundException exception) {
        return ResponseEntity.badRequest().body(
                """
                {
                    "status": 400,
                    "message": "%s"
                }
                """.formatted(exception.getMessage())
        );
    }
}
