package io.github.guilhermeewe.libraryapi.controller.dto;

import io.github.guilhermeewe.libraryapi.model.Author;

import java.time.LocalDate;
import java.util.UUID;

public record AuthorDTO(
        UUID id,
        String nome,
        LocalDate dataNascimento,
        String nacionalidade
) {

    public Author mapearParaAuthor() {
        Author author = new Author();

        author.setName(this.nome);
        author.setDate(this.dataNascimento);
        author.setNacionalidade(this.nacionalidade);

        return author;
    }
}
