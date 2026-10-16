package service;

import dto.InserirAtualizarProdutoDTO;
import exception.*;
import interfaces.ProdutoRepositoryInterface;
import interfaces.ProdutoServiceInterface;
import model.Produto;
import repository.ProdutoRepository;

import java.util.List;

public class ProdutoService implements ProdutoServiceInterface {
    private final ProdutoRepositoryInterface produtoRepository;

    public ProdutoService(ProdutoRepositoryInterface produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    private void validarDTO(InserirAtualizarProdutoDTO produtoDTO){
        if(produtoDTO.getNome().isBlank()){
            throw new ErroInsercaoException("O nome não pode ser vazio!");
        } else if(produtoDTO.getQuantidade() < 0){
            throw new ErroInsercaoException("A quantidade não pode ser menor que 0!");
        } else if(produtoDTO.getPreco() < 0){
            throw new ErroInsercaoException("O preço não pode ser menor que 0!");
        }
    }

    @Override
    public Produto inserirProduto(InserirAtualizarProdutoDTO produtoDTO){
        validarDTO(produtoDTO);

        Produto produtoInserido = produtoDTO.paraModel();
        Produto respostaBanco = produtoRepository.inserirProduto(produtoInserido);

        return respostaBanco;
    }

    @Override
    public Produto buscarProdutoPorId(Long id){
        Produto respostaBanco = produtoRepository.buscarProdutoPorId(id);

        return respostaBanco;
    }

    @Override
    public Produto buscarProdutoPorNome(String nome){
        Produto respostaBanco = produtoRepository.buscarProdutoPorNome(nome);

        return respostaBanco;
    }

    @Override
    public List<Produto> listarProdutos(){
        List<Produto> produtosEstoque = produtoRepository.listarProduto();

        if(produtosEstoque.isEmpty()){
            throw new NenhumResultadoEncontradoException("Não existe nenhum produto cadastrado!");
        }

        return produtosEstoque;
    }

    @Override
    public Produto atualizarProduto(InserirAtualizarProdutoDTO produtoDTO, Long id){
        validarDTO(produtoDTO);

        //Valida a existência do produto. Lança uma exceção caso não encontre
        produtoRepository.buscarProdutoPorId(id);

        Produto produtoInserido = produtoDTO.paraModel();
        Produto respostaBanco = produtoRepository.atualizarProduto(produtoInserido, id);

        return respostaBanco;
    }

    @Override
    public void deletarProduto(Long id){
        //Valida a existência do produto. Lança uma exceção caso não encontre
        produtoRepository.buscarProdutoPorId(id);

        produtoRepository.deletarProduto(id);
    }

    @Override
    public void venderProduto(Long id, int quantidade){
        //Valida a existência do produto. Lança uma exceção caso não encontre
        Produto produtoVendido = produtoRepository.buscarProdutoPorId(id);

        if(quantidade < 0){
            throw new DadosInvalidosVendaException("A quantidade não pode ser menor que 0!");
        } else if(produtoVendido.getQuantidade() < quantidade){
            throw new QuantidadeInsuficienteException("A quantidade de produtos não é o suficiente para suprir a venda!");
        }

        produtoRepository.venderProduto(id, quantidade);
    }

    @Override
    public void adicionarProdutoEstoque(Long id, int quantidade){
        //Valida a existência do produto. Lança uma exceção caso não encontre
        produtoRepository.buscarProdutoPorId(id);

        if(quantidade < 0){
            throw new DadosInvalidosAdicaoProdutosException("A quantidade não pode ser menor que 0!");
        }

        produtoRepository.adicionarProdutoEstoque(id, quantidade);
    }
}
