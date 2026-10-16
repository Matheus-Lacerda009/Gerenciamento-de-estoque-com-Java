package service;

import dto.InserirAtualizarProdutoDTO;
import exception.ErroInsercaoException;
import model.Produto;
import repository.ProdutoRepository;

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
}
