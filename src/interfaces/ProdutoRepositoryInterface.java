package interfaces;

import model.Produto;

import java.sql.SQLException;
import java.util.List;

public interface ProdutoRepositoryInterface {
    Produto inserirProduto(Produto produto) throws SQLException;

    Produto buscarProdutoPorId(Long id) throws SQLException;

    Produto buscarProdutoPorNome(String nome) throws SQLException;

    List<Produto> listarProduto() throws SQLException;

    Produto atualizarProduto(Produto produto, Long id) throws SQLException;

    void deletarProduto(Long id) throws SQLException;

    void venderProduto(Long id, int quantidade) throws SQLException;

    void adicionarProdutoEstoque(Long id, int quantidade) throws SQLException;
}
