package view;

import dto.InserirAtualizarProdutoDTO;
import model.Produto;
import service.ProdutoService;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Menu {
    public void inicializarMenu(){
        int opcao = -1;
        Scanner scanner = new Scanner(System.in);

        final ProdutoService produtoService = new ProdutoService();

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
                        break;

                    case 2:
                        System.out.println("Buscando produto (por id):");

                        System.out.println("Insira o id do produto:");
                        Long idBusca = scanner.nextLong();

                        //Limpando buffer
                        scanner.nextLine();

                        Produto resultadoBuscaId = produtoService.buscarProdutoPorId(idBusca);

                        System.out.println("Resultado da busca:\n" + resultadoBuscaId);
                        break;

                    case 3:
                        System.out.println("Buscando produto (por nome):");

                        System.out.println("Insira o nome do produto:");
                        String nomeBusca = scanner.nextLine();

                        Produto resultadoBuscaNome = produtoService.buscarProdutoPorNome(nomeBusca);

                        System.out.println("Resultado da busca:\n" + resultadoBuscaNome);
                        break;

                    case 4:
                        System.out.println("Listando produtos:");

                        List<Produto> produtosEstoque = produtoService.listarProdutos();

                        System.out.println("---------------------------------------");

                        for(Produto produto : produtosEstoque){
                            System.out.println(produto);
                            System.out.println("---------------------------------------");
                        }
                        break;

                    case 5:
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
                        break;

                    case 6:
                        System.out.println("Excluindo produto:");

                        System.out.println("Insira o id do produto:");
                        Long idExclusao = scanner.nextLong();

                        //Limpando buffer
                        scanner.nextLine();

                        produtoService.deletarProduto(idExclusao);

                        System.out.println("Sucesso!");
                        break;

                    case 7:
                        System.out.println("Vendendo produto:");

                        System.out.println("Insira o id do produto:");
                        Long idVenda = scanner.nextLong();

                        System.out.println("Insira a quantidade que está sendo vendida:");
                        int quantidadeVenda = scanner.nextInt();

                        //Limpando buffer
                        scanner.nextLine();

                        produtoService.venderProduto(idVenda, quantidadeVenda);

                        System.out.println("Sucesso!");
                        break;

                    case 8:
                        System.out.println("Reabastecendo produto:");

                        System.out.println("Insira o id do produto:");
                        Long idReabastecer = scanner.nextLong();

                        System.out.println("Insira a quantidade que está sendo adquirida:");
                        int quantidadeReabastecer = scanner.nextInt();

                        //Limpando buffer
                        scanner.nextLine();

                        produtoService.adicionarProdutoEstoque(idReabastecer, quantidadeReabastecer);

                        System.out.println("Sucesso!");
                        break;
                }
            } catch(InputMismatchException e){
                System.out.println(
                        "Erro: Insira os caracteres com suas devidas tipagens!\n" +
                                "Exemplo: Preço e quantidade não podem ser textos!"
                );

                //Limpando buffer
                scanner.nextLine();
            } catch(Exception e){
                System.out.println("Erro: " + e.getMessage());
            }
        } while(opcao != 0);

        scanner.close();
    }
}
