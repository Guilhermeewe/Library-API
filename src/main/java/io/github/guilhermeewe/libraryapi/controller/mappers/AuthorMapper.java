package io.github.guilhermeewe.libraryapi.controller.mappers;


import io.github.guilhermeewe.libraryapi.controller.dto.AuthorDTO;
import io.github.guilhermeewe.libraryapi.model.Author;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthorMapper {

    Author toEntity(AuthorDTO dto);

    AuthorDTO toDTO(Author author);

}
