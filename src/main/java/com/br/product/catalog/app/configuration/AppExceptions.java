package com.br.product.catalog.app.configuration;

import com.br.product.catalog.app.models.response.DetailErrorResponseDTO;
import com.br.product.catalog.app.models.response.ErrorResponseDTO;
import com.br.product.catalog.app.models.response.ErrorResponseMessageDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@ControllerAdvice
public class AppExceptions {

    @Autowired
    private MessageErrors messageErrors;

    @ExceptionHandler(value = ResponseStatusException.class)
    public ResponseEntity<?> reponseStatusExceptionTratament(ResponseStatusException error) {
        ObjectMapper mapper = new ObjectMapper();
        ErrorResponseDTO errorResponseDTO = null;
        try {
            ErrorResponseMessageDTO errorResponseStatus = mapper.readValue(error.getReason(), ErrorResponseMessageDTO.class);
            errorResponseDTO = ErrorResponseDTO.of(errorResponseStatus, error.getStatus());
        } catch (JsonProcessingException exception) {
            errorResponseDTO = ErrorResponseDTO.of(error.getStatus(), error.getReason());
        }
        return new ResponseEntity<>(errorResponseDTO, error.getStatus());
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<Object> reponseStatusExceptionTratament(MethodArgumentNotValidException error) {
        List<FieldError> fieldErrors = error.getBindingResult().getFieldErrors();
        List<DetailErrorResponseDTO> listErrors = messageErrors.createListErrors(fieldErrors);
        ErrorResponseDTO errorResponseDTO = ErrorResponseDTO.from(listErrors);
        return new ResponseEntity<>(errorResponseDTO, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }
}
