package io.github.guilhermeewe.libraryapi.repository;

import io.github.guilhermeewe.libraryapi.model.Author;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
public class AuthorRepositoryTest {

    @Autowired
    AuthorRepository authorRepository;

    @Test
    public void salvarTest(){
        Author author = new Author();

        author.setName("Maria");
        author.setNacionalidade("Brazil");
        author.setDate(LocalDate.of(2023, 12, 12));

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

            author.setDate(LocalDate.of(2024, 1, 10));

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
}
