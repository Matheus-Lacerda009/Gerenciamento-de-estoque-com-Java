package dto;

import model.Produto;

public class InserirAtualizarProdutoDTO {
    private String nome;
    private double preco;
    private int quantidade;

    public InserirAtualizarProdutoDTO(String nome, double preco, int quantidade) {
        this.nome = nome;
        this.preco = preco;
        this.quantidade = quantidade;
    }

    public String getNome() {
        return nome;
    }

    public double getPreco() {
        return preco;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public Produto paraModel(){
        return new Produto(
                nome,
                preco,
                quantidade
        );
    }
}
