package service;

import dto.InserirAtualizarProdutoDTO;
import exception.*;
import repository.ProdutoRepositoryInterface;
import model.Produto;

import java.sql.SQLException;
import java.util.List;

public class ProdutoService implements ProdutoServiceInterface {
    private final ProdutoRepositoryInterface produtoRepository;

    public ProdutoService(ProdutoRepositoryInterface produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    private void validarDTO(InserirAtualizarProdutoDTO produtoDTO){
        if(produtoDTO.getNome().isBlank()){
            throw new DadosDTOInvalidosException("O nome não pode ser vazio!");
        } else if(produtoDTO.getQuantidade() < 0){
            throw new DadosDTOInvalidosException("A quantidade não pode ser menor que 0!");
        } else if(produtoDTO.getPreco() < 0){
            throw new DadosDTOInvalidosException("O preço não pode ser menor que 0!");
        }
    }

    @Override
    public Produto inserirProduto(InserirAtualizarProdutoDTO produtoDTO) throws SQLException {
        validarDTO(produtoDTO);

        Produto produtoInserido = produtoDTO.paraModel();
        Produto respostaBanco = produtoRepository.inserirProduto(produtoInserido);

        return respostaBanco;
    }

    @Override
    public Produto buscarProdutoPorId(Long id) throws SQLException{
        Produto respostaBanco = produtoRepository.buscarProdutoPorId(id);

        return respostaBanco;
    }

    @Override
    public Produto buscarProdutoPorNome(String nome) throws SQLException{
        Produto respostaBanco = produtoRepository.buscarProdutoPorNome(nome);

        return respostaBanco;
    }

    @Override
    public List<Produto> listarProdutos() throws SQLException{
        List<Produto> produtosEstoque = produtoRepository.listarProdutos();

        if(produtosEstoque.isEmpty()){
            throw new NenhumResultadoEncontradoException("Não existe nenhum produto cadastrado!");
        }

        return produtosEstoque;
    }

    @Override
    public Produto atualizarProduto(InserirAtualizarProdutoDTO produtoDTO, Long id) throws SQLException{
        validarDTO(produtoDTO);

        //Valida a existência do produto. Lança uma exceção caso não encontre
        produtoRepository.buscarProdutoPorId(id);

        Produto produtoInserido = produtoDTO.paraModel();
        Produto respostaBanco = produtoRepository.atualizarProduto(produtoInserido, id);

        return respostaBanco;
    }

    @Override
    public void deletarProduto(Long id) throws SQLException{
        //Valida a existência do produto. Lança uma exceção caso não encontre
        produtoRepository.buscarProdutoPorId(id);

        produtoRepository.deletarProduto(id);
    }

    @Override
    public void venderProduto(Long id, int quantidade) throws SQLException{
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
    public void adicionarProdutoEstoque(Long id, int quantidade) throws SQLException{
        //Valida a existência do produto. Lança uma exceção caso não encontre
        produtoRepository.buscarProdutoPorId(id);

        if(quantidade < 0){
            throw new DadosInvalidosAdicaoProdutosException("A quantidade não pode ser menor que 0!");
        }

        produtoRepository.adicionarProdutoEstoque(id, quantidade);
    }
}
