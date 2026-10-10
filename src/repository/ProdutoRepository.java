package repository;

import connection.Conexao;
import model.Produto;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ProdutoRepository {

    public Produto inserirProduto(Produto produto){
        final String sql = "insert into Estoque(nome, preco) values (?, ?);";

        try(PreparedStatement declaracaoPreparada = Conexao.conectando().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){

            declaracaoPreparada.setString(1, produto.getNome());
            declaracaoPreparada.setDouble(2, produto.getPreco());
            declaracaoPreparada.executeUpdate();

            ResultSet resultadoInsercao = declaracaoPreparada.getResultSet();

            final Long idResultado = resultadoInsercao.getLong("GENERATED_KEYS");
            final String nomeResultado = resultadoInsercao.getString("nome");
            final double precoResultado = resultadoInsercao.getDouble("preco");

            Produto produtoInserido = new Produto(
                    idResultado,
                    nomeResultado,
                    precoResultado
            );

            return produtoInserido;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Produto buscarProduto(String nome){
        final String sql = "select * from Estoque where nome like ?;";

        try(PreparedStatement declaracaoPreparada = Conexao.conectando().prepareStatement(sql)){

            declaracaoPreparada.setString(1, "%" + nome + "%");
            ResultSet resultadoInsercao = declaracaoPreparada.executeQuery();

            final String nomeResultado = resultadoInsercao.getString("nome");
            final double precoResultado = resultadoInsercao.getDouble("preco");

            Produto produtoInserido = new Produto(
                    nomeResultado,
                    precoResultado
            );

            return produtoInserido;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
