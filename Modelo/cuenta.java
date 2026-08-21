package Modelo;
import java.math.BigDecimal;

public class Cuenta {
 
    // Atributos de identidad: no cambian una vez creada la cuenta.
    private final String numero;
    private final String titular;
 
    // Estado mutable. Protected (no private) para que CuentaCorriente
    // pueda leerlo/modificarlo directamente en su propio debitar().
    protected BigDecimal saldo;
 
    public Cuenta(String numero, String titular, BigDecimal saldoInicial) {
        if (numero == null || numero.isBlank()) {
            throw new IllegalArgumentException("El número de cuenta no puede estar vacío");
        }
        if (titular == null || titular.isBlank()) {
            throw new IllegalArgumentException("El titular no puede estar vacío");
        }
        if (saldoInicial == null || saldoInicial.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("El saldo inicial no puede ser negativo");
        }
 
        this.numero = numero;
        this.titular = titular;
        this.saldo = saldoInicial;
    }
 
    /** Aumenta el saldo. El monto debe ser positivo. */
    public void depositar(BigDecimal monto) {
        validarMontoPositivo(monto);
        this.saldo = this.saldo.add(monto);
    }
 
    /** Disminuye el saldo, validando que haya fondos suficientes. */
    public void debitar(BigDecimal monto) {
        validarMontoPositivo(monto);
        if (monto.compareTo(this.saldo) > 0) {
            throw new SaldoInsuficienteException(
                "Saldo: " + this.saldo + ", solicitado: " + monto);
        }
        this.saldo = this.saldo.subtract(monto);
    }
 
    private void validarMontoPositivo(BigDecimal monto) {
        if (monto == null || monto.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("El monto debe ser positivo");
        }
    }
 
    public String getNumero() {
        return numero;
    }
 
    public String getTitular() {
        return titular;
    }
 
    public BigDecimal getSaldo() {
        return saldo;
    }
 
    @Override
    public String toString() {
        return "Cuenta{numero='" + numero + "', titular='" + titular + "', saldo=" + saldo + '}';
    }
}
 
/**
 * Excepción de negocio: se intenta debitar más de lo disponible.
 */
class SaldoInsuficienteException extends RuntimeException {
    public SaldoInsuficienteException(String mensaje) {
        super(mensaje);
    }
}