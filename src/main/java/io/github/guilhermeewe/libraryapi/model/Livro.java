package io.github.guilhermeewe.libraryapi.model;


import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;


/*

CREATE TABLE tb_livro (

	id uuid not null primary key,
	isbn varchar(20) not null,
	titulo varchar(150) not null,
	data_publicacao date not null,
	genero varchar(30) not null,
	preco numeric(18, 2),

	id_author uuid not null references tb_author(id)

	constraint chk_genero check (genero in ('FICCAO', 'FANTASIA', 'MISTERIO', 'ROMANCE', 'BIOGRAFIA', 'CIENCIA'))

);


 */


@Entity
@Data // -> @Data engloba um monte de outras annotations (Getter, Setters, Equal and HashCode)
@Table(name = "tb_livro", schema = "public")
public class Livro {

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "titulo", length = 150, nullable = false)
    private String tittle;

    @Column(name = "isbn", length = 50, nullable = false)
    private String isbn;

    @Column(name = "data_publicacao", nullable = false)
    private LocalDate dataPublicacao;

    @Enumerated(EnumType.STRING)
    @Column(name = "genero", length = 30, nullable = false)
    private GeneroLivro genero;

    @Column(name = "preco", precision = 18, scale = 2, nullable = false)
    private BigDecimal preco;

    @ManyToOne
    @JoinColumn(name = "id_author")
    private Author author;


}
