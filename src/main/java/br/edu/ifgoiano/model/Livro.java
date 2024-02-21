package br.edu.ifgoiano.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "tb_livro")
@Getter
@Setter
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "livro_id")
    private Long id;

    private String nome;

    private Integer anoPub;

    private String isbn;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "tb_livro_autor",
            joinColumns = @JoinColumn(name = "livro_id"),
            inverseJoinColumns = @JoinColumn(name = "autor_id"))
    private List<Autor> autores;

    @ManyToOne
    @JoinColumn(name = "editora_id")
    private Editora editora;
}
