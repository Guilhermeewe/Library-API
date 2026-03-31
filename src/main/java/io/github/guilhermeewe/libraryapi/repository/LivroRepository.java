package io.github.guilhermeewe.libraryapi.repository;

import io.github.guilhermeewe.libraryapi.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface LivroRepository extends JpaRepository<Livro, UUID> {
}
