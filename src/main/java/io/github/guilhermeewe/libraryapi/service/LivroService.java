package io.github.guilhermeewe.libraryapi.service;

import io.github.guilhermeewe.libraryapi.model.GeneroLivro;
import io.github.guilhermeewe.libraryapi.model.Livro;
import io.github.guilhermeewe.libraryapi.repository.LivroRepository;
import io.github.guilhermeewe.libraryapi.repository.specs.LivroSpecs;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static io.github.guilhermeewe.libraryapi.repository.specs.LivroSpecs.anoEquals;

@Service
public class LivroService {

    private final LivroRepository livroRepository;

    public LivroService(LivroRepository livroRepository) {
        this.livroRepository = livroRepository;
    }

    public Livro salvarLivro(Livro livro) {
        return livroRepository.save(livro);
    }

    public Optional<Livro> obterLivroPorId(UUID id) {
        return livroRepository.findById(id);
    }

    public void deletarLivro(Livro livro) {
        livroRepository.delete(livro);
    }

    public List<Livro> pesquisaBySpecification(
            String isbn,
            String nomeAuthor,
            String titulo,
            GeneroLivro generoLivro,
            Integer anoPublicacao
    ) {
        /*
        Specification<Livro> specs = Specification.where(LivroSpecs.isbnEqual(isbn))
                .and(LivroSpecs.tituloLike(titulo))
                .and(LivroSpecs.generoEqual(generoLivro));
        */

        Specification<Livro> specs = Specification.where((root, query, criteriaBuilder) ->
                criteriaBuilder.conjunction()
        );

        if (isbn != null) {
            specs = specs.and(LivroSpecs.isbnEqual(isbn));
        }

        if (titulo != null) {
            specs = specs.and(LivroSpecs.tituloLike(titulo));
        }

        if (generoLivro != null ) {
            specs = specs.and(LivroSpecs.generoEqual(generoLivro));
        }

        if (anoPublicacao != null) {
            specs = specs.and(anoEquals(anoPublicacao));
        }

        if (nomeAuthor != null) {
            specs = specs.and(LivroSpecs.nomeAuthorLike(nomeAuthor));
        }

        return livroRepository.findAll(LivroSpecs.isbnEqual(isbn));
    }

    public void atualizar(Livro livro) {
        if (livro.getId() == null) {
            throw new IllegalArgumentException("Para atualizar é necessário que o livro esteja na base");
        }

        livroRepository.save(livro);
    }
}
