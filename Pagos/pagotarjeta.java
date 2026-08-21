package Pagos;
import java.math.BigDecimal;
//Implementación 1: pago con tarjeta de crédito
public class PagoTarjeta implements Pago {
    private final String numeroTarjeta;

    public PagoTarjeta(String numeroTarjeta) { this.numeroTarjeta = numeroTarjeta; }

    @Override
    public void procesar(BigDecimal monto) {
        System.out.println("Cargando $" + monto + " a la tarjeta " + numeroTarjeta);
        // lógica de autorización con la red de tarjetas    
        }

    @Override
    public String getDescripcion() { return "Tarjeta " + numeroTarjeta; }
}