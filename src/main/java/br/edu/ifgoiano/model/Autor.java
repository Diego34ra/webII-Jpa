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

    @ManyToMany(mappedBy = "autores",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Livro> livros;

    @Override
    public String toString() {
        return "Autor{" +
                "id=" + id +
                ", nome='" + nome +
                '}';
    }
}
