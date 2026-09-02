package metodos;

public class cuentaCorriente extends cuenta {

    private double creditoDisponible;

    public cuentaCorriente(int numeroCuenta,
                           double saldo,
                           cliente cliente,
                           double creditoDisponible) {

        super(numeroCuenta, saldo, cliente);
        this.creditoDisponible = creditoDisponible;
    }

    public double getCreditoDisponible() {
        return creditoDisponible;
    }

    public void setCreditoDisponible(double creditoDisponible) {
        this.creditoDisponible = creditoDisponible;
    }

    @Override
    public String toString() {
        return "Cuenta{" +
                "tipo=CuentaCorriente" +
                ", numeroCuenta=" + getNumeroCuenta() +
                ", saldo=" + getSaldo() +
                ", creditoDisponible=" + creditoDisponible +
                ", cliente=" + getCliente().getNombre() +
                ", cedula=" + getCliente().getDocumento() +
                '}';
    }
}