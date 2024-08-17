package dao;

import entity.Autor;
import entity.Livro;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AutorDAO extends BaseDAO{

    public void inserir(Autor autor){
        String sql = """
                insert into Autor(ID_Autor, Nome, Nacionalidade) values(?, ?, ?);
                """;
        try(Connection con = con();
            PreparedStatement pre = con.prepareStatement(sql)){
            pre.setInt(1,autor.getId());
            pre.setString(2,autor.getNome());
            pre.setString(3,autor.getNacionalidade());
            pre.execute();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void deletar(Autor autor){
        String sql = """
                delete from Autor where ID_Autor = ?;
                """;
        try(Connection con = con();
            PreparedStatement pre = con.prepareStatement(sql)){
            pre.setInt(1,autor.getId());
            pre.execute();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void atualizar(Autor autor){
        String sql = """
                update Autor set Nome = ?, Nacionalidade = ? where ID_Autor = ?;
                """;
        try(Connection con = con();
            PreparedStatement pre = con.prepareStatement(sql)){
            pre.setString(1,autor.getNome());
            pre.setString(2,autor.getNacionalidade());
            pre.setInt(3,autor.getId());
            pre.execute();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }



    public List<Autor> obterTodos(){
        List<Autor> lista = new ArrayList<>();
        String sql = """
                select ID_Autor, Nome, Nacionalidade from Autor
                order by Nome asc
                """;
        try(Connection con = con();
            PreparedStatement pre = con.prepareStatement(sql)){
            ResultSet rs = pre.executeQuery();
            // Existe o proximo?
            while(rs.next()){
                Autor a = new Autor();
                a.setId(rs.getInt("ID_Autor"));
                a.setNome(rs.getString("Nome"));
                a.setNacionalidade(rs.getString("Nacionalidade"));
                lista.add(a);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return lista;
    }

    public Optional<Autor> obterPeloId(int id){
        String sql = """
                select ID_Autor, Nome, Nacionalidade from Autor
                where ID_Autor = ?
                """;
        try(Connection con = con();
            PreparedStatement pre = con.prepareStatement(sql)){
            pre.setInt(1,id);
            ResultSet rs = pre.executeQuery();
            // Existe o proximo?
            if(rs.next()){
                Autor p = new Autor();
                p.setId(rs.getInt("ID_Autor"));
                p.setNome(rs.getString("Nome"));
                p.setNacionalidade(rs.getString("Nacionalidade"));
                return Optional.of(p);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return Optional.empty();
    }
    public List<Livro> buscarLivrosPorAutor (Autor autor){
        List<Livro> lista = new ArrayList<>();
        String sql = """
                select ID_Livro, Titulo, Ano_Publicacao from Livro
                where ID_Autor = ?
                order by Titulo asc
                """;
        try(Connection con = con();
            PreparedStatement pre = con.prepareStatement(sql)){
            pre.setInt(1,autor.getId());
            ResultSet rs = pre.executeQuery();
            // Existe o proximo?
            while(rs.next()){
                Livro l = new Livro();
                l.setId(rs.getInt("ID_Livro"));
                l.setTitulo(rs.getString("Titulo"));
                l.setAnoPublicacao(rs.getInt("Ano_Publicacao"));
                lista.add(l);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return lista;
    }
}
