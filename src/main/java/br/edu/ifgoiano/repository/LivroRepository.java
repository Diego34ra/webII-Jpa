package br.edu.ifgoiano.repository;

import br.edu.ifgoiano.model.Autor;
import br.edu.ifgoiano.model.Editora;
import br.edu.ifgoiano.model.Livro;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class LivroRepository {

    Configuration configuration = new Configuration().configure();
    SessionFactory sessionFactory = configuration.buildSessionFactory();

    EditoraRespository editoraRespository = new EditoraRespository();

    public void save(Livro livro){
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(livro);
            transaction.commit();
        }
    }

    public Livro getById(Long id){
        Livro livro;
        try (Session session = sessionFactory.openSession()) {
            livro = session.get(Livro.class, id);
        }
        return livro;
    }

    public void update(Long id, Livro livroUpdate){
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            Livro livro = getById(id);
            if(livroUpdate.getNome() != null)
                livro.setNome(livroUpdate.getNome());
            if(livroUpdate.getIsbn() != null)
                livro.setIsbn(livroUpdate.getIsbn());
            if(livro.getAnoPub() != null)
                livro.setAnoPub(livroUpdate.getAnoPub());
            session.update(livro);
            transaction.commit();
        }
    }

    public void delete(Long id){
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            Livro livroDelete = session.get(Livro.class, id);
            session.delete(livroDelete);
            transaction.commit();
        }
    }

    public List<Autor> getAutorByIdLivro(Long id){
        List<Autor> autorList;
        try (Session session = sessionFactory.openSession()) {
            Livro livro = session.get(Livro.class, id);
            autorList = livro.getAutores();
            autorList = autorList.stream().distinct().collect(Collectors.toList());
            session.close();
            return autorList;
        }
    }

    public Editora getEditoraByIdLivro(Long id){
        Editora editora;
        try (Session session = sessionFactory.openSession()) {
            Livro livro = session.get(Livro.class, id);
            editora = livro.getEditora();
        }
        return editora;
    }
}
