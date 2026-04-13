package io.github.guilhermeewe.libraryapi.repository;

import io.github.guilhermeewe.libraryapi.model.Author;
import io.github.guilhermeewe.libraryapi.model.GeneroLivro;
import io.github.guilhermeewe.libraryapi.model.Livro;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
public class AuthorRepositoryTest {

    @Autowired
    AuthorRepository authorRepository;

    @Autowired
    LivroRepository livroRepository;

    @Test
    public void salvarTest(){
        Author author = new Author();

        author.setNome("Maria");
        author.setNacionalidade("Brazil");
        author.setDataNascimento(LocalDate.of(2023, 12, 12));

        var authorId
                = authorRepository.save(author);

        System.out.println(authorId);
    }

    @Test
    public void atualizarTest(){
        var id = UUID.fromString("1a7d6fc9-ab67-4b78-a4e6-13289d743a2c");

        Optional<Author> possivelAuthor = authorRepository.findById(id);

        if (possivelAuthor.isPresent()) {
            Author author = possivelAuthor.get();
            System.out.println("Dados do autor: \n");
            System.out.println(author);

            author.setDataNascimento(LocalDate.of(2024, 1, 10));

            authorRepository.save(author);
        }


    }

    @Test
    public void listarTest(){
        List<Author> lista = authorRepository.findAll();

        lista.forEach(System.out::println);

    }

    @Test
    public void authorCount(){
        System.out.println("Autores : " + authorRepository.count());
    }


    @Test
    public void deleteByIdTest(){
        var id = UUID.fromString("1a7d6fc9-ab67-4b78-a4e6-13289d743a2c");
        authorRepository.deleteById(id);

        listarTest();
    }

    @Test
    void atualizarAuthorLivro(){
        Author author = new Author();

        author.setNome("Marta S");
        author.setNacionalidade("Brazil");
        author.setDataNascimento(LocalDate.of(1999, 12, 12));


        Livro livro = new Livro();
        livro.setIsbn("9832983901312");
        livro.setPreco(BigDecimal.valueOf(100));
        livro.setGenero(GeneroLivro.BIOGRAFIA);
        livro.setTittle("Pão Receita");
        livro.setDataPublicacao(LocalDate.of(2022, 12, 2));
        livro.setAuthor(author);

        author.setLivrosList(new ArrayList<>());
        author.getLivrosList().add(livro);

        authorRepository.save(author);

        livroRepository.saveAll(author.getLivrosList());

    }


    @Test
    void listarLivrosAutor(){
        UUID id = UUID.fromString("f65e6df5-df70-436b-9d96-102861deaad6");
        var author = authorRepository.findById(id).get();

        List<Livro> livroList = livroRepository.findByAuthor(author);
        author.setLivrosList(livroList);

        author.getLivrosList().forEach(System.out::println);
    }

}
