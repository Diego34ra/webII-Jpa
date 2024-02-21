package br.edu.ifgoiano.repository;

import br.edu.ifgoiano.model.Editora;
import br.edu.ifgoiano.model.Livro;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

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

//    public void update(Long id, Editora editoraUpdate){
//        try (Session session = sessionFactory.openSession()) {
//            Transaction transaction = session.beginTransaction();
//            Editora editora = getById(id);
//            editora.setNome(editoraUpdate.getNome());
//            session.update(editora);
//            transaction.commit();
//        }
//    }
//
//    public void delete(Long id){
//        try (Session session = sessionFactory.openSession()) {
//            Transaction transaction = session.beginTransaction();
//            Editora editoraDelte = session.get(Editora.class, id);
//            session.delete(editoraDelte);
//            transaction.commit();
//        }
//    }
}
