package repository;

import connection.Conexao;
import exception.ErroInsercaoException;
import exception.ProdutoNaoEncontradoException;
import model.Produto;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class ProdutoRepository {

    public Produto inserirProduto(Produto produto){
        final String sql = "insert into Estoque(nome, preco) values (?, ?);";

        try(PreparedStatement declaracaoPreparada = Conexao.conectando().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){

            declaracaoPreparada.setString(1, produto.getNome());
            declaracaoPreparada.setDouble(2, produto.getPreco());
            declaracaoPreparada.executeUpdate();

            ResultSet resultadoInsercao = declaracaoPreparada.getResultSet();

            if(resultadoInsercao.next()) {
                final Long idResultado = resultadoInsercao.getLong("GENERATED_KEYS");
                final String nomeResultado = resultadoInsercao.getString("nome");
                final double precoResultado = resultadoInsercao.getDouble("preco");

                Produto produtoInserido = new Produto(
                        idResultado,
                        nomeResultado,
                        precoResultado
                );

                return produtoInserido;
            } else {
                throw new ErroInsercaoException("Não foi retornado nenhuma informação da inserção do produto!");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Produto buscarProduto(Long id){
        final String sql = "select * from Estoque where id = ?;";

        try(PreparedStatement declaracaoPreparada = Conexao.conectando().prepareStatement(sql)){

            declaracaoPreparada.setLong(1, id);
            ResultSet resultadoInsercao = declaracaoPreparada.executeQuery();

            if(resultadoInsercao.next()) {
                final String nomeResultado = resultadoInsercao.getString("nome");
                final double precoResultado = resultadoInsercao.getDouble("preco");

                Produto produtoInserido = new Produto(
                        id,
                        nomeResultado,
                        precoResultado
                );

                return produtoInserido;
            } else {
                throw new ProdutoNaoEncontradoException("Não foi possível encontrar um produto com esse id!");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
