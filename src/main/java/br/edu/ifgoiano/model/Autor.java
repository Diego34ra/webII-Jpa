package br.edu.ifgoiano.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

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

    @ManyToMany(mappedBy = "autores")
    private List<Livro> livros;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Autor autor = (Autor) o;
        return Objects.equals(id, autor.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Autor{" +
                "id=" + id +
                ", nome='" + nome +
                '}';
    }
}
