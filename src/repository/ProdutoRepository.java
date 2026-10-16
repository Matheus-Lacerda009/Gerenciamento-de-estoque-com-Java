package repository;

import connection.Conexao;
import exception.ErroAtualizacaoException;
import exception.ErroInsercaoException;
import exception.ProdutoNaoEncontradoException;
import model.Produto;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProdutoRepository {

    public Produto inserirProduto(Produto produto){
        final String sql = "insert into Estoque(nome, preco, quantidade) values (?, ?, ?);";

        try(PreparedStatement declaracaoPreparada = Conexao.conectando().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){

            declaracaoPreparada.setString(1, produto.getNome());
            declaracaoPreparada.setDouble(2, produto.getPreco());
            declaracaoPreparada.setInt(3, produto.getQuantidade());
            declaracaoPreparada.executeUpdate();

            ResultSet resultadoInsercao = declaracaoPreparada.getGeneratedKeys();

            if(resultadoInsercao.next()) {
                final Long idResultado = resultadoInsercao.getLong(1);

                produto.setId(idResultado);

                return produto;
            } else {
                throw new ErroInsercaoException("Não foi retornado nenhuma informação da inserção do produto!");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Produto buscarProdutoPorId(Long id){
        final String sql = "select * from Estoque where id = ? and ativo = true;";

        try(PreparedStatement declaracaoPreparada = Conexao.conectando().prepareStatement(sql)){

            declaracaoPreparada.setLong(1, id);
            ResultSet resultadoBusca = declaracaoPreparada.executeQuery();

            if(resultadoBusca.next()) {
                final String nomeResultado = resultadoBusca.getString("nome");
                final double precoResultado = resultadoBusca.getDouble("preco");
                final int quantidadeResultado = resultadoBusca.getInt("quantidade");

                Produto produtoBusca = new Produto(
                        id,
                        nomeResultado,
                        precoResultado,
                        quantidadeResultado
                );

                return produtoBusca;
            } else {
                throw new ProdutoNaoEncontradoException("Não foi possível encontrar um produto com esse id!");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Produto buscarProdutoPorNome(String nome){
        final String sql = "select * from Estoque where nome like ? and ativo = true;";

        try(PreparedStatement declaracaoPreparada = Conexao.conectando().prepareStatement(sql)){

            declaracaoPreparada.setString(1, "%" + nome + "%");
            ResultSet resultadoBusca = declaracaoPreparada.executeQuery();

            if(resultadoBusca.next()) {
                final Long idResultado = resultadoBusca.getLong("id");
                final String nomeResultado = resultadoBusca.getString("nome");
                final double precoResultado = resultadoBusca.getDouble("preco");
                final int quantidadeResultado = resultadoBusca.getInt("quantidade");

                Produto produtoBusca = new Produto(
                        idResultado,
                        nomeResultado,
                        precoResultado,
                        quantidadeResultado
                );

                return produtoBusca;
            } else {
                throw new ProdutoNaoEncontradoException("Não foi possível encontrar um produto com esse id!");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Produto> listarProduto(){
        final String sql = "select * from Estoque where ativo = true;";

        try(PreparedStatement declaracaoPreparada = Conexao.conectando().prepareStatement(sql)){

            List<Produto> produtosEstoque = new ArrayList<>();

            ResultSet resultadoBusca = declaracaoPreparada.executeQuery();

            while(resultadoBusca.next()) {
                final Long idResultado = resultadoBusca.getLong("id");
                final String nomeResultado = resultadoBusca.getString("nome");
                final double precoResultado = resultadoBusca.getDouble("preco");
                final int quantidadeResultado = resultadoBusca.getInt("quantidade");

                Produto produto = new Produto(
                        idResultado,
                        nomeResultado,
                        precoResultado,
                        quantidadeResultado
                );

                produtosEstoque.add(produto);
            }

            return produtosEstoque;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Produto atualizarProduto(Produto produto, Long id){
        final String sql = "update Estoque set nome = ?, preco = ?, quantidade = ? where id = ? and ativo = true;";

        try(PreparedStatement declaracaoPreparada = Conexao.conectando().prepareStatement(sql)){

            declaracaoPreparada.setString(1, produto.getNome());
            declaracaoPreparada.setDouble(2, produto.getPreco());
            declaracaoPreparada.setInt(3, produto.getQuantidade());
            declaracaoPreparada.setLong(4, id);
            int quantidadeDeLinhasAfetadas = declaracaoPreparada.executeUpdate();

            if(quantidadeDeLinhasAfetadas != 0) {
                produto.setId(id);
                return produto;
            } else {
                throw new ErroAtualizacaoException("Não foi retornado nenhuma informação da atualização do produto!");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deletarProduto(Long id){
        final String sql = "update Estoque set ativo = false where id = ? and ativo = true;";

        try(PreparedStatement declaracaoPreparada = Conexao.conectando().prepareStatement(sql)){

            declaracaoPreparada.setLong(1, id);
            declaracaoPreparada.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void venderProduto(Long id, int quantidade){
        final String sql = "update Estoque set quantidade = quantidade - ? where id = ? and quantidade - ? >= 0 and ativo = true;";

        try(PreparedStatement declaracaoPreparada = Conexao.conectando().prepareStatement(sql)){

            declaracaoPreparada.setInt(1, quantidade);
            declaracaoPreparada.setLong(2, id);
            declaracaoPreparada.setInt(3, quantidade);
            int quantidadeDeLinhasAfetadas = declaracaoPreparada.executeUpdate();

            if(quantidadeDeLinhasAfetadas == 0) {
                throw new ErroAtualizacaoException("Não foi retornado nenhuma informação da venda do produto!");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void adicionarProdutoEstoque(Long id, int quantidade){
        final String sql = "update Estoque set quantidade = quantidade + ? where id = ? and ativo = true;";

        try(PreparedStatement declaracaoPreparada = Conexao.conectando().prepareStatement(sql)){

            declaracaoPreparada.setInt(1, quantidade);
            declaracaoPreparada.setLong(2, id);
            int quantidadeDeLinhasAfetadas = declaracaoPreparada.executeUpdate();

            if(quantidadeDeLinhasAfetadas == 0) {
                throw new ErroAtualizacaoException("Não foi retornado nenhuma informação da adição do produto!");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
