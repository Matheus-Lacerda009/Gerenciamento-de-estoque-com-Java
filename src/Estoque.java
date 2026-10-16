import java.util.Scanner;

public class Estoque {
    public static void main(String[] args){

        Scanner scanner = new Scanner(System.in);
        int opcao, i = 0;
        String[][] estoque = new String[5][2];

        while (true){
            System.out.println("O que deseja fazer?\n1- Cadastrar algo\n2- Excluir algo\n3- Buscar produto por código\n4- Mostrar estoque\n5- Encerrar programa");
            opcao = scanner.nextInt();
            scanner.nextLine();
            if (opcao == 1){

                System.out.print("Insira aqui o que deseja cadastrar:\n");
                estoque[i][0] = scanner.nextLine();
                
                System.out.print("Insira aqui o código desse produto:\n");
                estoque[i][1] = scanner.nextLine();
                i += 1;
            }
            else if (opcao == 2){
                System.out.println("Deseja excluir por código ou nome?");
                String exclusao = scanner.nextLine();
                
                if(exclusao.intern() == "codigo"){
                    System.out.println("Insira aqui o código do produto a ser excluído:");
                    exclusao = scanner.nextLine();
                    for(int x = 0; x < 5; x++){
                        if(estoque[x][1].intern() == exclusao.intern()){
                            estoque[x][1] = "";
                            estoque[x][0] = "";
                            break;
                        }
                        if(x == 4){
                            System.out.println("Não foi encontrado o código inserido");
                        }
                    }
                }
                else if(exclusao.intern() == "nome"){
                   System.out.println("Insira aqui o nome do produto a ser excluído:");
                   exclusao = scanner.nextLine();
                    for(int x = 0; x < 5; x++){
                        if(estoque[x][0].intern() == exclusao.intern()){
                            estoque[x][0] = "";
                            estoque[x][1] = "";
                            break;
                        }
                        if(x == 4){
                            System.out.println("Não foi encontrado o nome inserido");
                        }
                    }
                }
            }
            else if(opcao == 3){
                System.out.println("Insira aqui o código do produto à se buscar:");
                String codigo = scanner.nextLine();
                for(int j = 0; j < 5; j++){
                    if(codigo.intern() == estoque[j][1].intern()){
                        System.out.println("O nome do produto é: " + estoque[j][0]);
                        break;
                    }
                    if(j == 4){
                        System.out.println("Código não encontrado");
                    }
                }
            }
            else if(opcao == 4){
               for(int linha = 0; linha < 5; linha++){
                    for(int coluna = 0; coluna < 2; coluna++){
                        if(estoque[linha][coluna] != null && estoque[linha][coluna].isEmpty() == false){
                            if(coluna == 0){
                                System.out.print(estoque[linha][coluna]);
                            }
                            else if(coluna == 1){
                                System.out.print(" = " + estoque[linha][coluna]);
                            }
                        }
                    }
                    if(estoque[linha][0] != null && estoque[linha][1] != null && estoque[linha][0].isEmpty() == false && estoque[linha][1].isEmpty() == false){
                        System.out.print("\n");
                    }
               } 
            }
            else if(opcao == 5){
                System.out.println("Obrigado por utilizar este código!");
                break;
            }
            else{
                System.out.println("Comando não identificado, tente novamente");
            }
        }
        scanner.close();
    }
}