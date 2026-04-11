package io.github.guilhermeewe.libraryapi.controller;

import io.github.guilhermeewe.libraryapi.controller.dto.AuthorDTO;
import io.github.guilhermeewe.libraryapi.model.Author;
import io.github.guilhermeewe.libraryapi.service.AuthorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponents;

import java.net.URI;

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

}
