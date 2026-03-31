package io.github.guilhermeewe.libraryapi.repository;

import io.github.guilhermeewe.libraryapi.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AuthorRepository extends JpaRepository<Author, UUID> {
}
