import persistence.ProdutoRepository;
import business.ProdutoService;
import presentation.Menu;

public class Main {
    public static void main(String[] args) {

        ProdutoRepository produtoRepository = new ProdutoRepository();

        ProdutoService produtoService = new ProdutoService(produtoRepository);

        Menu menu = new Menu(produtoService);
        menu.iniciar();
    }
}