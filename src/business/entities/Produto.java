package business.entities;

public class Produto {
    private Long id;
    private String nome;
    private double preco;
    private int quantidade;

    public Produto(Long id, String nome, double preco, int quantidade) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.quantidade = quantidade;
    }

    public Produto(String nome, double preco, int quantidade) {
        this.nome = nome;
        this.preco = preco;
        this.quantidade = quantidade;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public double getPreco() {
        return preco;
    }

    public int getQuantidade(){
        return quantidade;
    }

    public void setId(Long id){
        this.id = id;
    }

    @Override
    public String toString() {
        return "Id - " + id +
                "\nNome - " + nome +
                "\nPreço - " + preco +
                "\nQuantidade - " + quantidade;
    }
}
