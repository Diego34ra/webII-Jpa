package br.edu.ifgoiano.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

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

    @ManyToMany
    @JoinTable(
            name = "tb_livro_autor",
            joinColumns = @JoinColumn(name = "livro_id"),
            inverseJoinColumns = @JoinColumn(name = "autor_id"))
    private List<Autor> autores;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "editora_id")
    private Editora editora;

    @Override
    public String toString() {
        return "Livro{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", anoPub=" + anoPub +
                ", isbn='" + isbn + '\'' +
                ", autores=" + autores +
                ", editora=" + editora +
                '}';
    }
}
