package producer;

public class PedidoCLI {

    public static void main(String[] args) throws Exception {
        for (int i = 0; i < 100; i++) {
            PedidoService pedidoService = new PedidoService();
            pedidoService.novoPedido();
        }
    }
}
