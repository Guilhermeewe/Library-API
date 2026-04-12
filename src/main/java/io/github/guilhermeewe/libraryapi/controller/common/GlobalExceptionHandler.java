package io.github.guilhermeewe.libraryapi.controller.common;

import io.github.guilhermeewe.libraryapi.controller.dto.ErroCampo;
import io.github.guilhermeewe.libraryapi.controller.dto.ErroResposta;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErroResposta handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        List<FieldError> fieldErrors = e.getFieldErrors();

        List<ErroCampo> listErros = fieldErrors
                .stream()
                .map(fieldError -> new ErroCampo(
                        fieldError.getField(),
                        fieldError.getDefaultMessage()))
                .toList();


        return  new ErroResposta(
                HttpStatus.UNPROCESSABLE_ENTITY.value(),
                "Erro de validação",
                listErros);
    }
}
