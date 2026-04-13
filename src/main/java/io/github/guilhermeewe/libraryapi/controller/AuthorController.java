package io.github.guilhermeewe.libraryapi.controller;

import io.github.guilhermeewe.libraryapi.controller.dto.AuthorDTO;
import io.github.guilhermeewe.libraryapi.controller.dto.ErroResposta;
import io.github.guilhermeewe.libraryapi.controller.mappers.AuthorMapper;
import io.github.guilhermeewe.libraryapi.exceptions.RegistroDuplicadoException;
import io.github.guilhermeewe.libraryapi.model.Author;
import io.github.guilhermeewe.libraryapi.service.AuthorService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/autores")
//http://localhost:8080/autores
public class AuthorController implements GenericController {
    private final AuthorService authorService;
    private final AuthorMapper authorMapper;

    public AuthorController(AuthorService authorService, AuthorMapper authorMapper){
        this.authorService = authorService;
        this.authorMapper = authorMapper;
    }

    @PostMapping
    public ResponseEntity<Object> salvar(@RequestBody @Valid AuthorDTO author) {
        Author authorEntity = authorMapper.toEntity(author);
        authorService.salvarAuthor(authorEntity);
        URI locationURL = gerarHeaderLocation(authorEntity.getId());
        return ResponseEntity.created(locationURL).build();

    }

    @GetMapping("{id}")
    public ResponseEntity<AuthorDTO> obterDetalhes(@PathVariable("id") String id) {

        var idAuthor = UUID.fromString(id);

        return authorService.obterAuthorPorId(idAuthor)
                .map(author -> {
                    AuthorDTO authorDTO = authorMapper.toDTO(author);
                    return ResponseEntity.ok(authorDTO);
                })
                .orElseGet( () -> ResponseEntity.notFound().build());
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
            @RequestParam(value = "nacionalidade", required = false) String nacionalidade
    ) {
        List<Author> authors = authorService.listarAutores(nome, nacionalidade);

        List<AuthorDTO> authorDTOS = authors
                .stream()
                .map(authorMapper::toDTO).toList();

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

        authorEntity.setNome(dto.nome());
        authorEntity.setNacionalidade(dto.nacionalidade());
        authorEntity.setDataNascimento(dto.dataNascimento());

        authorService.atualizar(authorEntity);

        return ResponseEntity.noContent().build();
    }
}
