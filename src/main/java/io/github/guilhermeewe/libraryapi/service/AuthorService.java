package io.github.guilhermeewe.libraryapi.service;

import io.github.guilhermeewe.libraryapi.model.Author;
import io.github.guilhermeewe.libraryapi.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public Author salvarAuthor(Author author) {
        return  authorRepository.save(author);
    }

}
