package io.github.guilhermeewe.libraryapi.validator;

import io.github.guilhermeewe.libraryapi.exceptions.RegistroDuplicadoException;
import io.github.guilhermeewe.libraryapi.model.Author;
import io.github.guilhermeewe.libraryapi.repository.AuthorRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AuthorValidator {

    private final AuthorRepository authorRepository;

    public AuthorValidator(AuthorRepository authorRepository){
        this.authorRepository = authorRepository;
    }

    public void validar(Author author) {
        if (existeAuthorCadastrado(author)) {
            throw new RegistroDuplicadoException("Autor ducplicado");
        }
    }

    private boolean existeAuthorCadastrado(Author author) {
        Optional<Author> authorOptional = authorRepository
                .findByNameAndDateAndNacionalidade(
                    author.getName(),
                    author.getDate(),
                    author.getNacionalidade());

        if (author.getId() == null) {
            return authorOptional.isPresent();
        }

        return !author.getId().equals(authorOptional.get().getId()) && authorOptional.isPresent();
    }
}
