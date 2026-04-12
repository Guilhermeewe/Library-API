package io.github.guilhermeewe.libraryapi.repository;

import io.github.guilhermeewe.libraryapi.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AuthorRepository extends JpaRepository<Author, UUID> {

    List<Author> findByName(String nome);
    List<Author> findByNacionalidade(String nacionalidade);
    List<Author> findByNameAndNacionalidade(String nome, String nacionalidade);

    Optional<Author> findByNameAndDateAndNacionalidade(
            String name,
            LocalDate dataNascimento,
            String nacionalidade
    );

}
