package producer;

import java.math.BigDecimal;

public class Pedido {

    private String id;
    private String usuarioId;
    private BigDecimal valorTotal;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(String usuarioId) {
        this.usuarioId = usuarioId;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "id='" + id + '\'' +
                ", usuarioId='" + usuarioId + '\'' +
                ", valorTotal=" + valorTotal +
                '}';
    }
}
