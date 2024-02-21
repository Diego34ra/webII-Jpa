package br.edu.ifgoiano.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "tb_editora")
@Getter
@Setter
public class Editora {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "editora_id")
    private Long id;

    private String nome;

    @OneToMany(mappedBy = "editora", fetch = FetchType.EAGER)
    private List<Livro> livros;

    @Override
    public String toString() {
        return "Editora{" +
                "id=" + id +
                ", nome='" + nome +
                '}';
    }
}