package service;

import dto.InserirAtualizarProdutoDTO;
import model.Produto;

import java.sql.SQLException;
import java.util.List;

public interface ProdutoServiceInterface {
    Produto inserirProduto(InserirAtualizarProdutoDTO produtoDTO) throws SQLException;

    Produto buscarProdutoPorId(Long id) throws SQLException;

    Produto buscarProdutoPorNome(String nome) throws SQLException;

    List<Produto> listarProdutos() throws SQLException;

    Produto atualizarProduto(InserirAtualizarProdutoDTO produtoDTO, Long id) throws SQLException;

    void deletarProduto(Long id) throws SQLException;

    void venderProduto(Long id, int quantidade) throws SQLException;

    void adicionarProdutoEstoque(Long id, int quantidade) throws SQLException;
}
