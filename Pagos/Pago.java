package Pagos;

import java.math.BigDecimal;
    // Interfaz común: define el contrato
    public interface Pago {
    void procesar(BigDecimal monto);
    String getDescripcion();
}

