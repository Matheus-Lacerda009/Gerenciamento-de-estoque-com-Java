package interfaces;

import dto.InserirAtualizarProdutoDTO;
import model.Produto;

import java.util.List;

public interface ProdutoServiceInterface {
    Produto inserirProduto(InserirAtualizarProdutoDTO produtoDTO);

    Produto buscarProdutoPorId(Long id);

    Produto buscarProdutoPorNome(String nome);

    List<Produto> listarProdutos();

    Produto atualizarProduto(InserirAtualizarProdutoDTO produtoDTO, Long id);

    void deletarProduto(Long id);

    void venderProduto(Long id, int quantidade);

    void adicionarProdutoEstoque(Long id, int quantidade);
}
