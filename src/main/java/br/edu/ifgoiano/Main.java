package br.edu.ifgoiano;

import br.edu.ifgoiano.model.Autor;
import br.edu.ifgoiano.model.Editora;
import br.edu.ifgoiano.model.Livro;
import br.edu.ifgoiano.repository.AutorRepository;
import br.edu.ifgoiano.repository.EditoraRespository;
import br.edu.ifgoiano.repository.LivroRepository;
import com.google.gson.Gson;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static AutorRepository autorRepository = new AutorRepository();
    public static EditoraRespository editoraRespository = new EditoraRespository();

    public static LivroRepository livroRepository = new LivroRepository();

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

        // Teste CRUD Livro

        List<Autor> autorList = new ArrayList<>();

        Editora editoraLivro = new Editora();
        editoraLivro.setId(2L);
        editoraLivro.setNome("Teste Editora Livro");

        editoraRespository.save(editoraLivro);

        Autor autorLivro1 = new Autor();
        autorLivro1.setId(2L);
        autorLivro1.setNome("Teste Autor Livro 1");

        autorRepository.save(autorLivro1);
        autorList.add(autorLivro1);

        Autor autorLivro2 = new Autor();
        autorLivro2.setId(3L);
        autorLivro2.setNome("Teste Autor Livro 2");

        autorRepository.save(autorLivro2);
        autorList.add(autorLivro2);

        Livro livro = new Livro();
        livro.setNome("Teste Livro");
        livro.setIsbn("123123123");
        livro.setAnoPub(2024);
        livro.setEditora(editoraLivro);
        livro.setAutores(autorList);

        livroRepository.save(livro);

        Livro livroGet = livroRepository.getById(1L);
        System.out.println(livroGet);
//        System.out.println(gson.toJson(livroGet));


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