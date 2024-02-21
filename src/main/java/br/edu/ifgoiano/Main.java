package br.edu.ifgoiano;

import br.edu.ifgoiano.model.Livro;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class Main {
    public static void main(String[] args) {
        Configuration configuration = new Configuration().configure();
        SessionFactory sessionFactory = configuration.buildSessionFactory();

        // Cria um novo livro
        Livro book = new Livro();
        book.setNome("Teste");


        // Salva o livro no banco de dados
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(book);
            transaction.commit();
        }

        // Recupera um livro pelo ID
        try (Session session = sessionFactory.openSession()) {
            Livro retrievedBook = session.get(Livro.class, 1L);
            System.out.println("Retrieved Book: " + retrievedBook.getNome());
        }

        // Atualiza um livro
//        try (Session session = sessionFactory.openSession()) {
//            Transaction transaction = session.beginTransaction();
//            Book bookToUpdate = session.get(Book.class, 1L);
//            bookToUpdate.setTitle("Updated Title");
//            session.update(bookToUpdate);
//            transaction.commit();
//        }
//
//        // Deleta um livro
//        try (Session session = sessionFactory.openSession()) {
//            Transaction transaction = session.beginTransaction();
//            Book bookToDelete = session.get(Book.class, 1L);
//            session.delete(bookToDelete);
//            transaction.commit();
//        }

        sessionFactory.close();
    }
}