package io.github.guilhermeewe.libraryapi.controller.mappers;

import io.github.guilhermeewe.libraryapi.controller.dto.CadastroLivroDTO;
import io.github.guilhermeewe.libraryapi.controller.dto.ResultadoPesquisaLivroDTO;
import io.github.guilhermeewe.libraryapi.model.Livro;
import io.github.guilhermeewe.libraryapi.repository.AuthorRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring", uses = {AuthorMapper.class})
public abstract class LivroMapper {

    @Autowired
    AuthorRepository authorRepository;

    @Mapping(target = "author", expression = "java(authorRepository.findById(cadastroLivroDTO.idAuthor()).orElse(null))")
    public abstract Livro toEntity(CadastroLivroDTO cadastroLivroDTO);

    public abstract ResultadoPesquisaLivroDTO toDTO(Livro livro);




}
