package br.edu.ifgoiano.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "tb_autor")
@Getter
@Setter
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "autor_id")
    private Long id;

    private String nome;

    @ManyToMany(mappedBy = "autores",cascade = CascadeType.ALL)
    private List<Livro> livros;
}
