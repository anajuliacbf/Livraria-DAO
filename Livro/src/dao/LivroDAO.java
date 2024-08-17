package dao;
import entity.Autor;
import entity.Livro;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LivroDAO extends BaseDAO{

    public void inserir(Livro livro){
        String sql = """
                insert into Livro(ID_Livro, Titulo, Ano_Publicacao, ID_Autor) values(?, ?, ?, ?);
                """;
        try(Connection con = con();
            PreparedStatement pre = con.prepareStatement(sql)){
            pre.setInt(1,livro.getId());
            pre.setString(2,livro.getTitulo());
            pre.setInt(3,livro.getAnoPublicacao());
            pre.setInt(4,livro.getIdAutor());
            pre.execute();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void deletar(Livro livro){
        String sql = """
                delete from Livro where ID_Livro = ?;
                """;
        try(Connection con = con();
            PreparedStatement pre = con.prepareStatement(sql)){
            pre.setInt(1,livro.getId());
            pre.execute();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void atualizar(Livro livro){
        String sql = """
                update Livro set Titulo = ?, Ano_Publicacao = ? where ID_Livro = ?;
                """;
        try(Connection con = con();
            PreparedStatement pre = con.prepareStatement(sql)){
            pre.setString(1,livro.getTitulo());
            pre.setInt(2,livro.getAnoPublicacao());
            pre.setInt(3,livro.getId());
            pre.execute();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }



    public List<Livro> obterTodos(){
        List<Livro> lista = new ArrayList<>();
        String sql = """
                select ID_Livro, Titulo, Ano_Publicacao from Livro
                order by Titulo asc
                """;
        try(Connection con = con();
            PreparedStatement pre = con.prepareStatement(sql)){
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

    public Optional<Livro> obterPeloId(int id){
        String sql = """
                select ID_Livro, Titulo, Ano_Publicacao from Livro
                where ID_Livro = ?
                """;
        try(Connection con = con();
            PreparedStatement pre = con.prepareStatement(sql)){
            pre.setInt(1,id);
            ResultSet rs = pre.executeQuery();
            // Existe o proximo?
            if(rs.next()){
                Livro l = new Livro();
                l.setId(rs.getInt("ID_Livro"));
                l.setTitulo(rs.getString("Titulo"));
                l.setAnoPublicacao(rs.getInt("Ano_Publicacao"));
                return Optional.of(l);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return Optional.empty();
    }

}
