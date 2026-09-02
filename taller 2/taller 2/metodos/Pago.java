package metodos;

public class Pago {

    private int id;
    private factura factura;
    private double valor;

    public Pago(int id, factura factura, double valor) {
        this.id = id;
        this.factura = factura;
        this.valor = valor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public factura getFactura() {
        return factura;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return "Pago{" +
                "id=" + id +
                ", valor=" + valor +
                '}';
    }
}