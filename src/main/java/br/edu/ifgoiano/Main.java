package br.edu.ifgoiano;

import br.edu.ifgoiano.model.Autor;
import br.edu.ifgoiano.model.Editora;
import br.edu.ifgoiano.model.Livro;
import br.edu.ifgoiano.repository.AutorRepository;
import br.edu.ifgoiano.repository.EditoraRespository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class Main {

    public static AutorRepository autorRepository = new AutorRepository();
    public static EditoraRespository editoraRespository = new EditoraRespository();

    public static void main(String[] args) {
        Configuration configuration = new Configuration().configure();
        SessionFactory sessionFactory = configuration.buildSessionFactory();

        //Teste CRUD Autor

        Autor autor = new Autor();
        autor.setNome("teste");
        autorRepository.save(autor);

        Autor autorGet = autorRepository.getById(1L);

        System.out.println("Id = "+autorGet.getId() + ", nome = "+autorGet.getNome());

        autorGet.setNome("teste2");
        autorRepository.update(1L,autorGet);

        autorRepository.delete(1L);

        // Teste CRUD Editora

        Editora editora = new Editora();
        editora.setNome("teste");
        editoraRespository.save(editora);

        Editora editoraGet = editoraRespository.getById(1L);

        System.out.println("Id = "+editoraGet.getId() + ", nome = "+editoraGet.getNome());

        editoraGet.setNome("teste2");
        editoraRespository.update(1L,editoraGet);

        editoraRespository.delete(1L);

//        // Salva o livro no banco de dados
//        try (Session session = sessionFactory.openSession()) {
//            Transaction transaction = session.beginTransaction();
//            session.save(book);
//            transaction.commit();
//        }
//
//        // Recupera um livro pelo ID
//        try (Session session = sessionFactory.openSession()) {
//            Livro retrievedBook = session.get(Livro.class, 1L);
//            System.out.println("Retrieved Book: " + retrievedBook.getNome());
//        }

//        // Atualiza um livro
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