package service;

import dto.InserirAtualizarProdutoDTO;
import exception.ErroInsercaoException;
import exception.NenhumResultadoEncontradoException;
import model.Produto;
import repository.ProdutoRepository;

import java.util.List;

public class ProdutoService {

    private final ProdutoRepository produtoRepository = new ProdutoRepository();

    public Produto inserirProduto(InserirAtualizarProdutoDTO produtoDTO){
        if(produtoDTO.getNome().isBlank()){
            throw new ErroInsercaoException("O nome não pode ser vazio!");
        } else if(produtoDTO.getQuantidade() < 0){
            throw new ErroInsercaoException("A quantidade não pode ser menor que 0!");
        } else if(produtoDTO.getPreco() < 0){
            throw new ErroInsercaoException("O preço não pode ser menor que 0!");
        }

        Produto produtoInserido = produtoDTO.paraModel();
        Produto respostaBanco = produtoRepository.inserirProduto(produtoInserido);

        return respostaBanco;
    }

    public Produto buscarProdutoPorId(Long id){
        Produto respostaBanco = produtoRepository.buscarProdutoPorId(id);

        return respostaBanco;
    }

    public Produto buscarProdutoPorNome(String nome){
        Produto respostaBanco = produtoRepository.buscarProdutoPorNome(nome);

        return respostaBanco;
    }

    public List<Produto> listarProdutos(){
        List<Produto> produtosEstoque = produtoRepository.listarProduto();

        if(produtosEstoque.isEmpty()){
            throw new NenhumResultadoEncontradoException("Não existe nenhum produto cadastrado!");
        }

        return produtosEstoque;
    }

    public Produto atualizarProduto(InserirAtualizarProdutoDTO produtoDTO, Long id){
        if(produtoDTO.getNome().isBlank()){
            throw new ErroInsercaoException("O nome não pode ser vazio!");
        } else if(produtoDTO.getQuantidade() < 0){
            throw new ErroInsercaoException("A quantidade não pode ser menor que 0!");
        } else if(produtoDTO.getPreco() < 0){
            throw new ErroInsercaoException("O preço não pode ser menor que 0!");
        }

        //Valida a existência do produto. Lança uma exceção caso não encontre
        produtoRepository.buscarProdutoPorId(id);

        Produto produtoInserido = produtoDTO.paraModel();
        Produto respostaBanco = produtoRepository.atualizarProduto(produtoInserido, id);

        return respostaBanco;
    }
}
