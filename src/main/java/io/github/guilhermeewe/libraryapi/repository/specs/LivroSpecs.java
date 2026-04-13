package io.github.guilhermeewe.libraryapi.repository.specs;


import io.github.guilhermeewe.libraryapi.model.GeneroLivro;
import io.github.guilhermeewe.libraryapi.model.Livro;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import org.springframework.boot.autoconfigure.rsocket.RSocketProperties;
import org.springframework.data.jpa.domain.Specification;

public class LivroSpecs {

    public static Specification<Livro> isbnEqual(String isbn) {
        return ((root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("isbn"), isbn)
                );
    }

    public static Specification<Livro> tituloLike(String titulo) {
        return (root, query, criteriaBuilder) ->

                criteriaBuilder.like(criteriaBuilder.upper(root.get("titulo")), "%" + titulo.toUpperCase() + "%");
    }

    public static Specification<Livro> generoEqual(GeneroLivro generoLivro) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("genero"), generoLivro);
    }

    public static Specification<Livro> anoEquals(Integer anoLivro) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(
                        criteriaBuilder.function("to_char", String.class, root.get("dataPublicacao"),
                                criteriaBuilder.literal("YYYY"))
                        , anoLivro.toString());
    }

    public static Specification<Livro> nomeAuthorLike(String nome) {
        return (root, query, criteriaBuilder) -> {
            Join<Object, Object> joinAuthor = root.join("author", JoinType.LEFT);
            return criteriaBuilder.like(criteriaBuilder.upper(joinAuthor.get("nome")), "%" + nome.toUpperCase() + "%");
           // return criteriaBuilder.like(criteriaBuilder.upper(root.get("author").get("nome")), "%" + "%");
        };

    }
}
