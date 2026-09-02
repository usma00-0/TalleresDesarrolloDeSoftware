package Modelo;
import java.math.BigDecimal;
public class CuentaCorriente extends Cuenta {
    private BigDecimal limiteDescubierto; // atributo propio de CuentaCorriente
    public CuentaCorriente(String numero, String titular, BigDecimal saldoInicial, BigDecimal limiteDescubierto) {
        super(numero, titular, saldoInicial); // llama al constructor del padre
        this.limiteDescubierto = limiteDescubierto;
    }
 
    // Sobreescribe el comportamiento para permitir saldo negativo hasta el límite
    @Override
    public void debitar(BigDecimal monto) {
        BigDecimal saldoDisponible = this.saldo.add(limiteDescubierto);
        if (monto.compareTo(saldoDisponible) > 0)
            throw new SaldoInsuficienteException("Supera el límite de descubierto");
        this.saldo = this.saldo.subtract(monto);
    }
}