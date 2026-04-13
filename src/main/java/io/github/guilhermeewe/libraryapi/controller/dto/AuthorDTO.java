package io.github.guilhermeewe.libraryapi.controller.dto;

import io.github.guilhermeewe.libraryapi.model.Author;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.UUID;

public record AuthorDTO(
        UUID id,

        @NotBlank(message = "Campo obrigatório")
        @Size(min= 2, max = 100, message = "fora do tamanho padrão ")
        String nome,

        @NotNull(message = "Campo obrigatório")
        LocalDate dataNascimento,

        @NotBlank(message = "Campo obrigatório")
        String nacionalidade
) {

}
