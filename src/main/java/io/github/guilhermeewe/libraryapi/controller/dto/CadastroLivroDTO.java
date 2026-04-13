package io.github.guilhermeewe.libraryapi.controller.dto;

import io.github.guilhermeewe.libraryapi.model.GeneroLivro;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record CadastroLivroDTO(

        @NotBlank(message = "campo obrigatório")
        String isbn,

        @NotBlank(message = "campo obrigatório")
        String titulo,

        @NotNull(message = "campo obrigatório")
        @Past(message = "não pode ser uma data futura")
        LocalDate dataPublicacao,

        GeneroLivro generoLivro,

        BigDecimal preco,

        @NotNull(message = "campo obrigatório")
        UUID id_author
) {

}
