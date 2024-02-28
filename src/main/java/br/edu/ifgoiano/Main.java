package br.edu.ifgoiano;

import br.edu.ifgoiano.model.Autor;
import br.edu.ifgoiano.model.Editora;
import br.edu.ifgoiano.model.Livro;
import br.edu.ifgoiano.repository.AutorRepository;
import br.edu.ifgoiano.repository.EditoraRespository;
import br.edu.ifgoiano.repository.LivroRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Main {

    public static AutorRepository autorRepository = new AutorRepository();
    public static EditoraRespository editoraRespository = new EditoraRespository();

    public static LivroRepository livroRepository = new LivroRepository();

    public static void main(String[] args) {
        testeCrudAutor();

        testeCrudEditora();

        testeCrudLivro();

        testeConsultas();

    }

    public static void testeCrudAutor(){
        //Teste CRUD Autor

        Autor autor = new Autor();
        autor.setNome("teste");
        autorRepository.save(autor);

        Autor autorGet = autorRepository.getById(1L);

        System.out.println("Id = "+autorGet.getId() + ", nome = "+autorGet.getNome());

        autorGet.setNome("teste2");
        autorRepository.update(1L,autorGet);

        autorRepository.delete(1L);
    }

    public static void testeCrudEditora(){
        // Teste CRUD Editora

        Editora editora = new Editora();
        editora.setNome("teste");
        editoraRespository.save(editora);

        Editora editoraGet = editoraRespository.getById(1L);

        System.out.println("Id = "+editoraGet.getId() + ", nome = "+editoraGet.getNome());

        editoraGet.setNome("teste2");
        editoraRespository.update(1L,editoraGet);

        editoraRespository.delete(1L);
    }

    public static void testeCrudLivro(){
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

        livroGet.setNome("Testando Livro");
        livroGet.setIsbn("321321321");
        livroGet.setAnoPub(2023);
        livroRepository.update(1L,livroGet);

        livroRepository.delete(1L);
    }

    public static void testeConsultas(){
        List<Autor> autorList = new ArrayList<>();

        Editora editoraLivro = new Editora();
        editoraLivro.setId(3L);
        editoraLivro.setNome("Teste Editora Livro");

        editoraRespository.save(editoraLivro);

        Autor autorLivro1 = new Autor();
        autorLivro1.setId(3L);
        autorLivro1.setNome("Teste Autor Livro 1");

        autorRepository.save(autorLivro1);
        autorList.add(autorLivro1);

        Autor autorLivro2 = new Autor();
        autorLivro2.setId(4L);
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
        // quais autores por livro
        List<Autor> autors = livroRepository.getAutorByIdLivro(2L);
        autors.forEach(System.out::println);

        // quais livros por autor;
        List<Livro> livros = autorRepository.getLivrosByIdAutor(4L);
        livros.forEach(System.out::println);

        //quais editoras por livro;
        Editora editora = livroRepository.getEditoraByIdLivro(2L);
        System.out.println(editora);

        //quais livros por editora;
        List<Livro> livroList = editoraRespository.getLivrosByIdEditora(3L);
        livroList.forEach(System.out::println);

        //quais autores por editora;
        Set<Autor> autorListEditora = editoraRespository.getAutoresByIdEditora(3L);
        autorListEditora.forEach(System.out::println);

        //quais editoras por autor;
        Set<Editora> editoraListAutor = autorRepository.getEditorasByIdAutor(4L);
        editoraListAutor.forEach(System.out::println);
    }
}