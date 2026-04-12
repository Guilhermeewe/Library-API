package io.github.guilhermeewe.libraryapi.controller;

import io.github.guilhermeewe.libraryapi.controller.dto.AuthorDTO;
import io.github.guilhermeewe.libraryapi.model.Author;
import io.github.guilhermeewe.libraryapi.service.AuthorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/autores")
//htp://localhost:8080/autores
public class AuthorController {


    private final AuthorService authorService;

    public AuthorController(AuthorService authorService){
        this.authorService = authorService;
    }

    @PostMapping
    public ResponseEntity<Void> salvar(@RequestBody AuthorDTO author) {

        Author authorEntity = author.mapearParaAuthor();

        authorService.salvarAuthor(authorEntity);

        URI locationURL = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(authorEntity.getId())
                .toUri();

        return ResponseEntity.created(locationURL).build();

    }

    @GetMapping("{id}")
    public ResponseEntity<AuthorDTO> obterDetalhes(@PathVariable("id") String id) {

        var idAuthor = UUID.fromString(id);

        Optional<Author> authorOptional = authorService.obterAuthorPorId(idAuthor);

        if (authorOptional.isPresent()) {

            Author author = authorOptional.get();
            AuthorDTO authorDTO = new AuthorDTO(
                    author.getId(),
                    author.getName(),
                    author.getDate(),
                    author.getNacionalidade()
                    );
            return ResponseEntity.ok(authorDTO);


        } else {
            return ResponseEntity.notFound().build();
        }

    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deletarAuthor(@PathVariable("id") String id) {
        var idAuthor = UUID.fromString(id);

        Optional<Author> author = authorService.obterAuthorPorId(idAuthor);

        if (author.isPresent()) {

            authorService.deletarPorId(author.get());
            return ResponseEntity.noContent().build();

        } else {

            return ResponseEntity.notFound().build();

        }
    }

    @GetMapping
    public ResponseEntity<List<AuthorDTO>> buscarAutores(
            @RequestParam(value = "nome", required = false) String nome,
            @RequestParam(value = "nome", required = false) String nacionalidade
    ) {
        List<Author> authors = authorService.listarAutores(nome, nacionalidade);

        List<AuthorDTO> authorDTOS = authors
                .stream()
                .map(author -> new AuthorDTO(
                        author.getId(),
                        author.getName(),
                        author.getDate(),
                        author.getNacionalidade()
                )).toList();

        return ResponseEntity.ok(authorDTOS);
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> atualizar(@PathVariable("id") String id, @RequestBody AuthorDTO dto) {

        var idAuthor = UUID.fromString(id);

        Optional<Author> author = authorService.obterAuthorPorId(idAuthor);


        if (author.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        var authorEntity = author.get();

        authorEntity.setName(dto.nome());

    }
}
