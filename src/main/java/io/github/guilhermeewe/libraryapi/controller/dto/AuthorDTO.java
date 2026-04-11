package io.github.guilhermeewe.libraryapi.controller.dto;

import io.github.guilhermeewe.libraryapi.model.Author;

import java.time.LocalDate;

public record AuthorDTO(
        String nome,
        LocalDate dataNascimento,
        String nacionalidade) {

    public Author mapearParaAuthor() {
        Author author = new Author();

        author.setName(nome);
        author.setDate(dataNascimento);
        author.setNacionalidade(nacionalidade);

        return author;
    }
}
