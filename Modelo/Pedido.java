package Modelo;
import java.math.BigDecimal;

public class Pedido {

    private final BigDecimal total;

    public Pedido(BigDecimal total) {
        this.total = total;
    }

    public BigDecimal getTotal() {
        return total;
    }
}

