package metodos;

public abstract class cuenta {

    private int numeroCuenta;
    private double saldo;
    private cliente cliente;

    public cuenta(int numeroCuenta, double saldo, cliente cliente) {
        this.numeroCuenta = numeroCuenta;
        this.saldo = saldo;
        this.cliente = cliente;
    }

    public int getNumeroCuenta() {
        return numeroCuenta;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public cliente getCliente() {
        return cliente;
    }
    
    @Override
    public String toString() {
        return "Cuenta{" +
                "tipo=" + getClass().getSimpleName() +
                ", numeroCuenta=" + numeroCuenta +
                ", saldo=" + saldo +
                ", cliente=" + cliente.getNombre() +
                ", cedula=" + cliente.getDocumento() +
                '}';
    }
}