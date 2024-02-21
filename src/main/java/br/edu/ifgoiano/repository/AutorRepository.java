package br.edu.ifgoiano.repository;

import br.edu.ifgoiano.model.Autor;
import br.edu.ifgoiano.model.Livro;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.Optional;

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
}
