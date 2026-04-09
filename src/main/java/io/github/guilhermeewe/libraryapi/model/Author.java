package io.github.guilhermeewe.libraryapi.model;



import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "tb_author", schema = "public")
@Getter
@Setter
@ToString
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @Column(name = "nome", length = 100, nullable = false)
    private String name;

    @Column(name = "data_nascimento", nullable = false)
    private LocalDate date;

    @Column(name = "nacionalidade", length = 50, nullable = false)
    private String nacionalidade;


    @OneToMany(mappedBy = "author", fetch = FetchType.LAZY)
    private List<Livro> livrosList;
}
