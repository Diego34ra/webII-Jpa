package br.edu.ifgoiano.repository;

import br.edu.ifgoiano.model.Autor;
import br.edu.ifgoiano.model.Editora;
import br.edu.ifgoiano.model.Livro;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.List;

public class AutorRepository {

    Configuration configuration = new Configuration().configure();
    SessionFactory sessionFactory = configuration.buildSessionFactory();

    public void save(Autor autor){
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(autor);
            transaction.commit();
        }
    }

    public Autor getById(Long id){
        Autor autor;
        try (Session session = sessionFactory.openSession()) {
            autor = session.get(Autor.class, id);
        }
        return autor;
    }

    public void update(Long id, Autor autorUpdate){
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            Autor autor = getById(id);
            autor.setNome(autorUpdate.getNome());
            session.update(autor);
            transaction.commit();
        }
    }

    public void delete(Long id){
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            Autor autorDelete = session.get(Autor.class, id);
            session.delete(autorDelete);
            transaction.commit();
        }
    }

    public List<Livro> getLivrosByIdAutor(Long id){
        List<Livro> livros;
        try (Session session = sessionFactory.openSession()) {
            Autor autor = session.get(Autor.class, id);
            livros = autor.getLivros();
        }
        return livros;
    }

    public List<Editora> getEditorasByIdAutor(Long id){
        List<Editora> editoraList = new ArrayList<>();
        List<Livro> livros = getLivrosByIdAutor(id);
        for(Livro livro : livros){
            editoraList.add(livro.getEditora());
        }
        return editoraList;
    }
}
