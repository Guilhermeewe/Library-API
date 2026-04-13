package io.github.guilhermeewe.libraryapi.repository;


import io.github.guilhermeewe.libraryapi.model.Author;
import io.github.guilhermeewe.libraryapi.model.GeneroLivro;
import io.github.guilhermeewe.libraryapi.model.Livro;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@SpringBootTest
public class LivroRepositoryTest {


    @Autowired
    LivroRepository livroRepository;

    @Autowired
    AuthorRepository authorRepository;

    @Test
    void salvarTest(){
        Livro livro = new Livro();
        livro.setIsbn("9832983901312");
        livro.setPreco(BigDecimal.valueOf(100));
        livro.setGenero(GeneroLivro.FICCAO);
        livro.setTittle("UFO");
        livro.setDataPublicacao(LocalDate.of(2024, 12, 2));


        Author author = authorRepository
                .findById(UUID.fromString("f65e6df5-df70-436b-9d96-102861deaad6"))
                .orElse(null);


        livro.setAuthor(author);

        livroRepository.save(livro);
    }

    @Test
    void salvarCascadeTest(){
        Livro livro = new Livro();
        livro.setIsbn("9832983901312");
        livro.setPreco(BigDecimal.valueOf(100));
        livro.setGenero(GeneroLivro.FICCAO);
        livro.setTittle("UFO");
        livro.setDataPublicacao(LocalDate.of(2024, 12, 2));

        Author author = new Author();

        author.setNome("Joaozinho");
        author.setNacionalidade("Germany");
        author.setDataNascimento(LocalDate.of(1920, 11, 12));

        livro.setAuthor(author);

        livroRepository.save(livro);
    }

    @Test
    void atualizarLivroAutor() {

        UUID id = UUID.fromString("e24d71c4-44e2-498a-b650-b07982348cd3");
        var livroAtualizar = livroRepository.findById(id).orElse(null);

        UUID idMaria = UUID.fromString("f65e6df5-df70-436b-9d96-102861deaad6");
        var maria = authorRepository.findById(idMaria).orElse(null);

        livroAtualizar.setAuthor(maria);

        livroRepository.save(livroAtualizar);

    }

    @Test
    void pesquisaPorTitulo(){
        List<Livro> paoReceita = livroRepository.findByTittle("UFO");
        for (Livro livro : paoReceita) {
            System.out.println(livro);
        }
    }

}
