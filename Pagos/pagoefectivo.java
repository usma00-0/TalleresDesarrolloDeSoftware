package Pagos;
import java.math.BigDecimal;

// Implementación 3: pago en efectivo
public class PagoEfectivo implements Pago {
    @Override
    public void procesar(BigDecimal monto) {
        System.out.println("Registrando pago en efectivo de $" + monto);
    }

    @Override
    public String getDescripcion() { return "Efectivo"; }
}
// ✅ El servicio de checkout trabaja con la abstracción Pago,
// // sin importar cuál es la implementación concreta

