package io.github.guilhermeewe.libraryapi.service;

import io.github.guilhermeewe.libraryapi.model.Livro;
import io.github.guilhermeewe.libraryapi.repository.LivroRepository;
import org.springframework.stereotype.Service;

@Service
public class LivroService {

    private final LivroRepository livroRepository;

    public LivroService(LivroRepository livroRepository) {
        this.livroRepository = livroRepository;
    }

    public Livro salvarLivro(Livro livro) {
        return livroRepository.save(livro);
    }
}
