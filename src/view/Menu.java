package view;

import dto.InserirAtualizarProdutoDTO;
import exception.ErroAtualizacaoException;
import exception.ErroInsercaoException;
import exception.ProdutoNaoEncontradoException;
import interfaces.ProdutoServiceInterface;
import model.Produto;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Menu {

    private final ProdutoServiceInterface produtoService;

    public Menu(ProdutoServiceInterface produtoService) {
        this.produtoService = produtoService;
    }

    public void iniciar(){
        int opcao = -1;
        Scanner scanner = new Scanner(System.in);

        System.out.println(
                "Bem vindo! Você está entrando no sistema de gerenciamento de estoque da Empresa X!"
        );

        do{
            try {
                System.out.println(
                        "\nInsira a opção desejada:" +
                                "\n1 - Inserir novo produto" +
                                "\n2 - Buscar um produto pelo id" +
                                "\n3 - Buscar um produto pelo nome" +
                                "\n4 - Listar produtos" +
                                "\n5 - Atualizar produto" +
                                "\n6 - Deletar produto" +
                                "\n7 - Vender produto" +
                                "\n8 - Reabastecer estoque"
                );
                opcao = scanner.nextInt();

                //Limpando buffer
                scanner.nextLine();

                switch(opcao){

                    case 0:
                        System.out.println("Saindo...");
                        break;

                    case 1:
                        inserirProdutoMenu(scanner);
                        break;

                    case 2:
                        buscarProdutoPorIdMenu(scanner);
                        break;

                    case 3:
                        buscarProdutoPorNomeMenu(scanner);
                        break;

                    case 4:
                        listarProdutosMenu(scanner);
                        break;

                    case 5:
                        atualizarProdutoMenu(scanner);
                        break;

                    case 6:
                        deletarProdutoMenu(scanner);
                        break;

                    case 7:
                        venderProdutoMenu(scanner);
                        break;

                    case 8:
                        adicionarProdutoEstoqueMenu(scanner);
                        break;
                }
            } catch(InputMismatchException e){
                System.out.println("Erro: Entrada inválida! Tente novamente");

                //Limpando buffer
                scanner.nextLine();
            } catch(Exception e){
                System.out.println("Erro: Erro não identificado. Por favor, reporte ao desenvolvedor\n" + e.getMessage());
            }
        } while(opcao != 0);

        scanner.close();
    }

    public void inserirProdutoMenu(Scanner scanner){
        try {
            System.out.println("Inserindo produto:");

            System.out.println("Insira o nome do produto:");
            String nomeInsercao = scanner.nextLine();

            System.out.println("Insira o preço do produto:");
            double precoInsercao = scanner.nextDouble();

            System.out.println("Insira a quantidade do produto:");
            int quantidadeInsercao = scanner.nextInt();

            //Limpando buffer
            scanner.nextLine();

            InserirAtualizarProdutoDTO produtoDTOInsercao =
                    new InserirAtualizarProdutoDTO(
                            nomeInsercao,
                            precoInsercao,
                            quantidadeInsercao
                    );

            Produto resultadoInsercao = produtoService.inserirProduto(produtoDTOInsercao);

            System.out.println("Produto inserido com sucesso:\n" + resultadoInsercao);
        } catch(InputMismatchException e){
            System.out.println("Erro: Entrada inválida! Tente novamente");
        } catch(ErroInsercaoException e){
            System.out.println(e.getMessage());
        } catch(SQLException e){
            System.out.println("Erro: Erro interno dentro do banco de dados");
        }
    }

    public void buscarProdutoPorIdMenu(Scanner scanner){
        try{
            System.out.println("Buscando produto (por id):");

            System.out.println("Insira o id do produto:");
            Long idBusca = scanner.nextLong();

            //Limpando buffer
            scanner.nextLine();

            Produto resultadoBuscaId = produtoService.buscarProdutoPorId(idBusca);

            System.out.println("Resultado da busca:\n" + resultadoBuscaId);
        } catch(InputMismatchException e){
            System.out.println("Erro: Entrada inválida! Tente novamente");
        } catch(ProdutoNaoEncontradoException e){
            System.out.println(e.getMessage());
        } catch(SQLException e){
            System.out.println("Erro: Erro interno dentro do banco de dados");
        }
    }

    public void buscarProdutoPorNomeMenu(Scanner scanner){
        try {
            System.out.println("Buscando produto (por nome):");

            System.out.println("Insira o nome do produto:");
            String nomeBusca = scanner.nextLine();

            Produto resultadoBuscaNome = produtoService.buscarProdutoPorNome(nomeBusca);

            System.out.println("Resultado da busca:\n" + resultadoBuscaNome);
        } catch(InputMismatchException e){
            System.out.println("Erro: Entrada inválida! Tente novamente");
        } catch(ProdutoNaoEncontradoException e){
            System.out.println(e.getMessage());
        } catch(SQLException e){
            System.out.println("Erro: Erro interno dentro do banco de dados");
        }
    }

    public void listarProdutosMenu(Scanner scanner){
        try {
            System.out.println("Listando produtos:");

            List<Produto> produtosEstoque = produtoService.listarProdutos();

            System.out.println("---------------------------------------");

            for (Produto produto : produtosEstoque) {
                System.out.println(produto);
                System.out.println("---------------------------------------");
            }
        } catch(SQLException e){
            System.out.println("Erro: Erro interno dentro do banco de dados");
        }
    }

    public void atualizarProdutoMenu(Scanner scanner){
        try {
            System.out.println("Atualizando produto:");

            System.out.println("Insira o id do produto:");
            Long idAtualizacao = scanner.nextLong();

            //Limpando buffer
            scanner.nextLine();

            System.out.println("Insira o nome do produto:");
            String nomeAtualizacao = scanner.nextLine();

            System.out.println("Insira o preço do produto:");
            double precoAtualizacao = scanner.nextDouble();

            System.out.println("Insira a quantidade do produto:");
            int quantidadeAtualizacao = scanner.nextInt();

            //Limpando buffer
            scanner.nextLine();

            InserirAtualizarProdutoDTO produtoDTOAtualizacao =
                    new InserirAtualizarProdutoDTO(
                            nomeAtualizacao,
                            precoAtualizacao,
                            quantidadeAtualizacao
                    );

            Produto resultadoAtualizacao = produtoService.atualizarProduto(produtoDTOAtualizacao, idAtualizacao);

            System.out.println("Produto atualizado com sucesso:\n" + resultadoAtualizacao);
        } catch(InputMismatchException e){
            System.out.println("Erro: Entrada inválida! Tente novamente");
        } catch(ErroAtualizacaoException e){
            System.out.println(e.getMessage());
        } catch(SQLException e){
            System.out.println("Erro: Erro interno dentro do banco de dados");
        }
    }

    public void deletarProdutoMenu(Scanner scanner){
        try {
            System.out.println("Excluindo produto:");

            System.out.println("Insira o id do produto:");
            Long idExclusao = scanner.nextLong();

            //Limpando buffer
            scanner.nextLine();

            produtoService.deletarProduto(idExclusao);

            System.out.println("Sucesso!");
        } catch(InputMismatchException e){
            System.out.println("Erro: Entrada inválida! Tente novamente");
        } catch(SQLException e){
            System.out.println("Erro: Erro interno dentro do banco de dados");
        }
    }

    public void venderProdutoMenu(Scanner scanner){
        try {
            System.out.println("Vendendo produto:");

            System.out.println("Insira o id do produto:");
            Long idVenda = scanner.nextLong();

            System.out.println("Insira a quantidade que está sendo vendida:");
            int quantidadeVenda = scanner.nextInt();

            //Limpando buffer
            scanner.nextLine();

            produtoService.venderProduto(idVenda, quantidadeVenda);

            System.out.println("Sucesso!");
        } catch(InputMismatchException e){
            System.out.println("Erro: Entrada inválida! Tente novamente");
        } catch(ErroAtualizacaoException e){
            System.out.println(e.getMessage());
        } catch(SQLException e){
            System.out.println("Erro: Erro interno dentro do banco de dados");
        }
    }

    public void adicionarProdutoEstoqueMenu(Scanner scanner){
        try {
            System.out.println("Reabastecendo produto:");

            System.out.println("Insira o id do produto:");
            Long idReabastecer = scanner.nextLong();

            System.out.println("Insira a quantidade que está sendo adquirida:");
            int quantidadeReabastecer = scanner.nextInt();

            //Limpando buffer
            scanner.nextLine();

            produtoService.adicionarProdutoEstoque(idReabastecer, quantidadeReabastecer);

            System.out.println("Sucesso!");
        } catch(InputMismatchException e){
            System.out.println("Erro: Entrada inválida! Tente novamente");
        } catch(ErroAtualizacaoException e){
            System.out.println(e.getMessage());
        } catch(SQLException e){
            System.out.println("Erro: Erro interno dentro do banco de dados");
        }
    }
}
