package controller;

import dto.InserirAtualizarProdutoDTO;
import exception.DadosInvalidosAdicaoProdutosException;
import exception.DadosInvalidosVendaException;
import exception.NenhumResultadoEncontradoException;
import exception.QuantidadeInsuficienteException;
import model.Produto;
import service.ProdutoService;

import java.sql.SQLException;
import java.util.List;

public class ProdutoController implements ProdutoControllerInterface{
    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @Override
    public Produto inserirProduto(InserirAtualizarProdutoDTO produtoDTO) throws SQLException {
        Produto respostaBanco = produtoService.inserirProduto(produtoDTO);

        return respostaBanco;
    }

    @Override
    public Produto buscarProdutoPorId(Long id) throws SQLException{
        Produto respostaBanco = produtoService.buscarProdutoPorId(id);

        return respostaBanco;
    }

    @Override
    public Produto buscarProdutoPorNome(String nome) throws SQLException{
        Produto respostaBanco = produtoService.buscarProdutoPorNome(nome);

        return respostaBanco;
    }

    @Override
    public List<Produto> listarProdutos() throws SQLException{
        List<Produto> produtosEstoque = produtoService.listarProdutos();

        return produtosEstoque;
    }

    @Override
    public Produto atualizarProduto(InserirAtualizarProdutoDTO produtoDTO, Long id) throws SQLException{
        Produto respostaBanco = produtoService.atualizarProduto(produtoDTO, id);

        return respostaBanco;
    }

    @Override
    public void deletarProduto(Long id) throws SQLException{
        produtoService.deletarProduto(id);
    }

    @Override
    public void venderProduto(Long id, int quantidade) throws SQLException{
        produtoService.venderProduto(id, quantidade);
    }

    @Override
    public void adicionarProdutoEstoque(Long id, int quantidade) throws SQLException{
        produtoService.adicionarProdutoEstoque(id, quantidade);
    }
}
