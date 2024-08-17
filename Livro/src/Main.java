import dao.AutorDAO;
import dao.LivroDAO;
import entity.Autor;
import entity.Livro;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        AutorDAO autor_dao = new AutorDAO();
        Autor autor1 = new Autor();
        autor1.setId(1);
        autor1.setNome("Thallys");
        autor1.setNacionalidade("brasileiro");

        LivroDAO livro_dao = new LivroDAO();
        Livro livro1 = new Livro();
        Livro livro2 = new Livro();
        livro1.setId(1);
        livro2.setId(2);
        livro1.setTitulo("The Witcher");
        livro1.setAnoPublicacao(2010);
        livro1.setIdAutor(autor1.getId());
        livro2.setTitulo("Verity");
        livro2.setAnoPublicacao(2022);
        livro2.setIdAutor(autor1.getId());

        livro_dao.inserir(livro1);
        livro_dao.inserir(livro2);

        autor_dao.inserir(autor1);

        Autor autor2 = new Autor();
        autor2.setId(2);
        autor2.setNome("Baunilha");
        autor2.setNacionalidade("Felina");

        autor_dao.inserir(autor2);


        //   Alterar Autor
        Optional<Autor> t = autor_dao.obterPeloId(autor1.getId());
        if (t.isPresent()) {
            Autor autor_modificado = t.get();

            autor_modificado.setNome("Ana");
            autor_modificado.setNacionalidade("Holandesa");
            autor_dao.atualizar(autor_modificado);
        }

        // deleta
//         autor_dao.deletar(autor1);


       // buscar todos
        List<Autor> todos_autores = autor_dao.obterTodos();

        // Listar livros de um autor
        List<Livro> livros_autores = autor_dao.buscarLivrosPorAutor(autor1);
        System.out.println(livros_autores);
    }
}