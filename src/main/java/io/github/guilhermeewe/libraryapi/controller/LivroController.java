package io.github.guilhermeewe.libraryapi.controller;

import io.github.guilhermeewe.libraryapi.controller.dto.CadastroLivroDTO;
import io.github.guilhermeewe.libraryapi.controller.dto.ErroResposta;
import io.github.guilhermeewe.libraryapi.controller.dto.ResultadoPesquisaLivroDTO;
import io.github.guilhermeewe.libraryapi.controller.mappers.LivroMapper;
import io.github.guilhermeewe.libraryapi.exceptions.RegistroDuplicadoException;
import io.github.guilhermeewe.libraryapi.model.GeneroLivro;
import io.github.guilhermeewe.libraryapi.model.Livro;
import io.github.guilhermeewe.libraryapi.repository.LivroRepository;
import io.github.guilhermeewe.libraryapi.service.LivroService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("livros")
public class LivroController implements GenericController {

    private final LivroService livroService;
    private final LivroMapper livroMapper;

    public LivroController(LivroService livroService, LivroMapper livroMapper) {
        this.livroService = livroService;
        this.livroMapper = livroMapper;
    }

    @PostMapping
    public ResponseEntity<Object> salvarLivro(@RequestBody @Valid CadastroLivroDTO dto) {
        Livro livroEntity = livroMapper.toEntity(dto);
        livroService.salvarLivro(livroEntity);
        URI uri = gerarHeaderLocation(livroEntity.getId());

        return ResponseEntity.created(uri).build();
    }

    @GetMapping("{id}")
    public ResponseEntity<ResultadoPesquisaLivroDTO> obterDetalhes(
            @PathVariable("id") String id) {
            return livroService.obterLivroPorId(UUID.fromString(id))
                    .map(livro -> {
                        ResultadoPesquisaLivroDTO dto = livroMapper.toDTO(livro);
                        return ResponseEntity.ok(dto);
                    }).orElseGet( () -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> deletarLivro(@PathVariable("id") String id ){

        return livroService.obterLivroPorId(UUID.fromString(id))
                .map( livro -> {
                  livroService.deletarLivro(livro);
                  return ResponseEntity.noContent().build();
                })
                .orElseGet( () -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<ResultadoPesquisaLivroDTO>> pesquisa(
            @RequestParam(value = "isbn", required = false)
            String isbn,
            @RequestParam(value = "titulo", required = false)
            String titulo,
            @RequestParam(value = "nome-author", required = false)
            String nomeAuthor,
            @RequestParam(value = "generoLivro", required = false)
            GeneroLivro generoLivro,
            @RequestParam(value = "ano-publicacao", required = false)
            Integer anoPublicacao
    ){
        var resultado = livroService.pesquisaBySpecification( isbn, titulo, nomeAuthor, generoLivro, anoPublicacao );
        var lista = resultado.stream().map(livroMapper::toDTO).toList();

        return ResponseEntity.ok(lista);

    }

    @PutMapping("{id}")
    public ResponseEntity<Object> atualizar(
            @PathVariable("id")
            String id,

            @RequestBody CadastroLivroDTO dto
    ) {

        return livroService.obterLivroPorId(UUID.fromString(id))
                .map(livro -> {
                    Livro entity = livroMapper.toEntity(dto);

                    livro.setDataPublicacao(entity.getDataPublicacao());
                    livro.setTittle(entity.getTittle());
                    livro.setPreco(entity.getPreco());
                    livro.setIsbn(entity.getIsbn());
                    livro.setGenero(entity.getGenero());
                    livro.setAuthor(entity.getAuthor());

                    livroService.atualizar(livro);


                    return ResponseEntity.noContent().build();

                }).orElseGet( () -> ResponseEntity.notFound().build());

    }

}
