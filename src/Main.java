import service.ProdutoService;
import view.Menu;

public class Main {
    public static void main(String[] args) {

        ProdutoService produtoService = new ProdutoService();

        Menu menu = new Menu(produtoService);
        menu.inicializarMenu();
    }
}