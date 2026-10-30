import controller.ProdutoController;
import repository.ProdutoRepository;
import service.ProdutoService;
import view.Menu;

public class Main {
    public static void main(String[] args) {

        ProdutoRepository produtoRepository = new ProdutoRepository();

        ProdutoService produtoService = new ProdutoService(produtoRepository);

        ProdutoController produtoController = new ProdutoController(produtoService);

        Menu menu = new Menu(produtoController);
        menu.iniciar();
    }
}