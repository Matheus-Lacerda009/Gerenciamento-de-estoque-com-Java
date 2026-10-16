package interfaces;

import connection.Conexao;
import exception.ErroAtualizacaoException;
import exception.ErroInsercaoException;
import exception.ProdutoNaoEncontradoException;
import model.Produto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public interface ProdutoRepositoryInterface {
    Produto inserirProduto(Produto produto);

    Produto buscarProdutoPorId(Long id);

    Produto buscarProdutoPorNome(String nome);

    List<Produto> listarProduto();

    Produto atualizarProduto(Produto produto, Long id);

    void deletarProduto(Long id);

    void venderProduto(Long id, int quantidade);

    void adicionarProdutoEstoque(Long id, int quantidade);
}
