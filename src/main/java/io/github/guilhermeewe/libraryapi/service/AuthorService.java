package io.github.guilhermeewe.libraryapi.service;

import io.github.guilhermeewe.libraryapi.model.Author;
import io.github.guilhermeewe.libraryapi.repository.AuthorRepository;
import io.github.guilhermeewe.libraryapi.validator.AuthorValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.desktop.AboutHandler;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;
    private final AuthorValidator authorValidator;

    public AuthorService(AuthorRepository authorRepository, AuthorValidator authorValidator) {
        this.authorRepository = authorRepository;
        this.authorValidator = authorValidator;
    }

    public Author salvarAuthor(Author author) {
        authorValidator.validar(author);
        return authorRepository.save(author);
    }

    public void atualizar(Author author) {
        if (author.getId() == null ) {
            throw new IllegalArgumentException("Para atualizar, é necessário que o autor já esteja salvo na base de dados");
        }
    }

    public Optional<Author> obterAuthorPorId(UUID id) {
        return authorRepository.findById(id);
    }

    public void deletarPorId(Author author) {
        authorRepository.delete(author);
    }

    public List<Author> listarAutores(String nome, String nacionalidade){
        if (nome != null && nacionalidade != null) {
            return authorRepository.findByNameAndNacionalidade(nome, nacionalidade);
        }

        if (nome != null ) {
            return authorRepository.findByName(nome);
        }

        if(nacionalidade != null) {
            return authorRepository.findByNacionalidade(nacionalidade);
        }

        return authorRepository.findAll();

    }
}
