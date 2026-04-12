package io.github.guilhermeewe.libraryapi.repository;

import io.github.guilhermeewe.libraryapi.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface AuthorRepository extends JpaRepository<Author, UUID> {

    List<Author> findByNome(String nome);
    List<Author> findByNacionalidade(String nacionalidade);
    List<Author> findByNomeAndNacionalidade(String nome, String nacionalidade);

}
