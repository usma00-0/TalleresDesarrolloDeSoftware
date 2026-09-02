package metodos;

public class factura {

    private int id;
    private String servicio;
    private double valor;
    private cliente cliente;
    private boolean pagada;

    public factura(int id,
                   String servicio,
                   double valor,
                   cliente cliente) {

        this.id = id;
        this.servicio = servicio;
        this.valor = valor;
        this.cliente = cliente;
        this.pagada = false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getServicio() {
        return servicio;
    }

    public double getValor() {
        return valor;
    }

    public cliente getCliente() {
        return cliente;
    }

    public boolean isPagada() {
        return pagada;
    }

    public void setPagada(boolean pagada) {
        this.pagada = pagada;
    }

    @Override
    public String toString() {
        return "Factura{" +
                "id=" + id +
                ", servicio='" + servicio + '\'' +
                ", valor=" + valor +
                ", pagada=" + pagada +
                '}';
    }
}