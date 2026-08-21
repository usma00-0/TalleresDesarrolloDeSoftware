import Modelo.*;
import Pagos.*;
import java.math.BigDecimal;
import java.util.Scanner;
 
public class Main {
 
    private static final Scanner sc = new Scanner(System.in);
    private static Cuenta cuenta; // cuenta activa en esta sesión
 
    public static void main(String[] args) {
        int opcion;
        do {
            mostrarMenu();
            opcion = leerEntero("Elige una opción: ");
 
            switch (opcion) {
                case 1:
                    crearCuenta();
                    break;
                case 2:
                    depositar();
                    break;
                case 3:
                    debitar();
                    break;
                case 4:
                    verSaldo();
                    break;
                case 5:
                    finalizarCompra();
                    break;
                case 0:
                    System.out.println("¡Hasta luego!");
                    break;
                default:
                    System.out.println("Opción inválida, intenta de nuevo.");
            }
            System.out.println();
        } while (opcion != 0);
 
        sc.close();
    }
 
    // ---------- Opciones del menú ----------
 
    private static void mostrarMenu() {
        System.out.println("===== MENÚ =====");
        System.out.println("1. Crear cuenta");
        System.out.println("2. Depositar");
        System.out.println("3. Debitar");
        System.out.println("4. Ver saldo");
        System.out.println("5. Finalizar compra");
        System.out.println("0. Salir");
    }
 
    private static void crearCuenta() {
        System.out.println("Tipo de cuenta:");
        System.out.println("1. Cuenta simple");
        System.out.println("2. Cuenta corriente (con descubierto)");
        int tipo = leerEntero("Elige un tipo: ");
 
        String numero = leerTexto("Número de cuenta: ");
        String titular = leerTexto("Titular: ");
        BigDecimal saldoInicial = leerMonto("Saldo inicial: ");
 
        try {
            switch (tipo) {
                case 1:
                    cuenta = new Cuenta(numero, titular, saldoInicial);
                    System.out.println("Cuenta simple creada. " + cuenta);
                    break;
                case 2:
                    BigDecimal limite = leerMonto("Límite de descubierto: ");
                    cuenta = new CuentaCorriente(numero, titular, saldoInicial, limite);
                    System.out.println("Cuenta corriente creada. " + cuenta);
                    break;
                default:
                    System.out.println("Tipo inválido, no se creó la cuenta.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("No se pudo crear la cuenta: " + e.getMessage());
        }
    }
 
    private static void depositar() {
        if (!hayCuenta()) return;
        BigDecimal monto = leerMonto("Monto a depositar: ");
        try {
            cuenta.depositar(monto);
            System.out.println("Depósito realizado. " + cuenta);
        } catch (IllegalArgumentException e) {
            System.out.println("No se pudo depositar: " + e.getMessage());
        }
    }
 
    private static void debitar() {
        if (!hayCuenta()) return;
        BigDecimal monto = leerMonto("Monto a debitar: ");
        try {
            cuenta.debitar(monto);
            System.out.println("Débito realizado. " + cuenta);
        } catch (RuntimeException e) {
            System.out.println("No se pudo debitar: " + e.getMessage());
        }
    }
 
    private static void verSaldo() {
        if (!hayCuenta()) return;
        System.out.println(cuenta);
    }
 
    private static void finalizarCompra() {
        BigDecimal total = leerMonto("Total del pedido: ");
        Pedido pedido = new Pedido(total);
 
        System.out.println("Método de pago:");
        System.out.println("1. Efectivo");
        System.out.println("2. Tarjeta");
        System.out.println("3. Transferencia");
        int metodo = leerEntero("Elige un método: ");
 
        Pago pago;
        switch (metodo) {
            case 1:
                pago = new PagoEfectivo();
                break;
            case 2:
                String numeroTarjeta = leerTexto("Número de tarjeta: ");
                pago = new PagoTarjeta(numeroTarjeta);
                break;
            case 3:
                String cbu = leerTexto("CBU: ");
                pago = new PagoTransferencia(cbu);
                break;
            default:
                System.out.println("Método inválido, se cancela la compra.");
                return;
        }
 
        // CheckoutService no sabe (ni le importa) qué implementación de Pago recibió.
        new CheckoutService().finalizarCompra(pedido, pago);
    }
 
    // ---------- Utilidades de entrada ----------
 
    private static boolean hayCuenta() {
        if (cuenta == null) {
            System.out.println("Primero crea una cuenta (opción 1).");
            return false;
        }
        return true;
    }
 
    private static int leerEntero(String mensaje) {
        System.out.print(mensaje);
        while (true) {
            try {
                return Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.print("Ingresa un número válido: ");
            }
        }
    }
 
    private static String leerTexto(String mensaje) {
        System.out.print(mensaje);
        return sc.nextLine().trim();
    }
 
    private static BigDecimal leerMonto(String mensaje) {
        System.out.print(mensaje);
        while (true) {
            try {
                return new BigDecimal(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.print("Ingresa un monto válido (ej: 1000.50): ");
            }
        }
    }
}
 