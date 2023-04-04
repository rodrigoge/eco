package br.com.eco.pointservice.api;

import br.com.eco.pointservice.exceptions.CustomException;
import br.com.eco.pointservice.exceptions.GenericError;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.time.ZoneId;

@ControllerAdvice
public class GlobalControllerAdvice {

    @Autowired
    private ObjectMapper objectMapper;

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<GenericError> handleCustomException(CustomException customException) {
        var status = customException.getHttpStatus();
        return ResponseEntity
                .status(status)
                .body(GenericError
                        .builder()
                        .status(status.value())
                        .time(LocalDateTime.now(ZoneId.of("UTC")).toString())
                        .message(customException.getMessage())
                        .build()
                );
    }
}
