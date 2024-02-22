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
            Editora editora = session.get(Editora.class, id);
            livroList = editora.getLivros();
            System.out.println("tamanho = "+livroList.size());
        }
        return livroList;
    }

    public List<Autor> getAutoresByIdEditora(Long id){
        List<Autor> autorList = new ArrayList<>();
        try (Session session = sessionFactory.openSession()) {
            Editora editora = session.get(Editora.class, id);
            List<Livro> livroList = editora.getLivros();
            for(Livro livro : livroList){
                autorList.addAll(livro.getAutores());
            }
        }
        return autorList;
    }
}
