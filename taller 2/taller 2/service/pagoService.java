package service;

import java.util.ArrayList;
import java.util.List;
import metodos.Pago;
import metodos.cuenta;
import metodos.cuentaCorriente;
import metodos.factura;

public class pagoService {

    private List<Pago> pagos;
    private int siguienteId;

    public pagoService() {
        this.pagos = new ArrayList<>();
        this.siguienteId = 1;
    }

    public boolean procesarPago(cuenta cuenta, factura factura) {
        return procesarPago(siguienteId++, cuenta, factura);
    }

    // READ
    public Pago buscarPagoPorId(int id) {

        for (Pago pago : pagos) {
            if (pago.getId() == id) {
                return pago;
            }
        }

        return null;
    }


    // FUNCIONALIDAD PRINCIPAL
    public boolean procesarPago(int idPago,
                                cuenta cuenta,
                                factura factura) {

        if (cuenta == null || factura == null) {
            System.out.println("La cuenta o la factura no existen.");
            return false;
        }

        if (idPago <= 0 || factura.getValor() <= 0
                || buscarPagoPorId(idPago) != null) {
            System.out.println("Los datos del pago no son validos.");
            return false;
        }

        if (factura.isPagada()) {
            System.out.println("La factura ya se encuentra pagada.");
            return false;
        }

        double valorFactura = factura.getValor();
        double saldoDisponible = cuenta.getSaldo();
        double creditoDisponible = 0;

        if (cuenta instanceof cuentaCorriente cuentaCorriente) {
            creditoDisponible = cuentaCorriente.getCreditoDisponible();
        }

        if (saldoDisponible + creditoDisponible < valorFactura) {
            System.out.println("Saldo insuficiente.");
            return false;
        }

        if (saldoDisponible >= valorFactura) {
            cuenta.setSaldo(saldoDisponible - valorFactura);
        } else if (cuenta instanceof cuentaCorriente cuentaCorriente) {
            double creditoUsado = valorFactura - saldoDisponible;
            cuenta.setSaldo(0);
            cuentaCorriente.setCreditoDisponible(
                    creditoDisponible - creditoUsado
            );
        }

        factura.setPagada(true);

        Pago pago = new Pago(
                idPago,
                factura,
                factura.getValor()
        );

        pagos.add(pago);

        System.out.println("Pago realizado correctamente.");

        return true;
    }

    // FUNCIONALIDAD SOLICITADA
    public List<Pago> obtenerPagosPorCliente(int idCliente) {

        List<Pago> resultado = new ArrayList<>();

        for (Pago pago : pagos) {

            if (pago.getFactura()
                    .getCliente()
                    .getId() == idCliente) {

                resultado.add(pago);
            }
        }

        return resultado;
    }

    public List<Pago> obtenerPagosPorDocumento(String documento) {
        List<Pago> resultado = new ArrayList<>();

        for (Pago pago : pagos) {
            if (pago.getFactura() != null
                    && pago.getFactura().getCliente() != null
                    && documento.equals(
                            pago.getFactura().getCliente().getDocumento())) {
                resultado.add(pago);
            }
        }

        return resultado;
    }

    public void listarPagosCliente(String documento) {
        List<Pago> resultado = obtenerPagosPorDocumento(documento);

        if (resultado.isEmpty()) {
            System.out.println("No existen pagos para este cliente.");
            return;
        }

        for (Pago pago : resultado) {
            System.out.println(pago);
        }
    }

    // Mostrar todos los pagos
    public void listarPagos() {

        if (pagos.isEmpty()) {
            System.out.println("No existen pagos registrados.");
            return;
        }

        for (Pago pago : pagos) {
            System.out.println(pago);
        }
    }
}