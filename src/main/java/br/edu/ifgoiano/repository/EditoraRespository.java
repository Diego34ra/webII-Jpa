package br.edu.ifgoiano.repository;

import br.edu.ifgoiano.model.Autor;
import br.edu.ifgoiano.model.Editora;
import br.edu.ifgoiano.model.Livro;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class EditoraRespository {

    Configuration configuration = new Configuration().configure();
    SessionFactory sessionFactory = configuration.buildSessionFactory();

    public void save(Editora editora){
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(editora);
            transaction.commit();
        }
    }

    public Editora getById(Long id){
        Editora editora;
        try (Session session = sessionFactory.openSession()) {
            editora = session.get(Editora.class, id);
        }
        return editora;
    }

    public void update(Long id, Editora editoraUpdate){
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            Editora editora = getById(id);
            editora.setNome(editoraUpdate.getNome());
            session.update(editora);
            transaction.commit();
        }
    }

    public void delete(Long id){
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            Editora editoraDelte = session.get(Editora.class, id);
            session.delete(editoraDelte);
            transaction.commit();
        }
    }

    public List<Livro> getLivrosByIdEditora(Long id){
        List<Livro> livroList;
        try (Session session = sessionFactory.openSession()) {
            Query<Livro> query = session.createQuery(
                    "SELECT l FROM Livro l JOIN FETCH l.editora e WHERE e.id = :id", Livro.class);
            query.setParameter("id", id);
            livroList = query.getResultList();
        }
        return livroList;
    }

    public Set<Autor> getAutoresByIdEditora(Long id){
        List<Livro> livroList = getLivrosByIdEditora(id);
        return livroList.stream()
                .flatMap(livro -> livro.getAutores().stream())
                .collect(Collectors.toSet());
    }
}
