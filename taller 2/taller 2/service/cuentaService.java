package service;

import java.util.ArrayList;
import java.util.List;
import metodos.cliente;
import metodos.cuenta;
import metodos.cuentaCorriente;

public class cuentaService {

    private List<cuenta> cuentas;

    public cuentaService() {
        this.cuentas = new ArrayList<>();
    }

    // CREATE
    public void crearcuenta(cuenta cuenta) {
        if (cuenta == null || cuenta.getCliente() == null
                || cuenta.getNumeroCuenta() <= 0
                || cuenta.getSaldo() < 0) {
            System.out.println("Los datos de la cuenta no son validos.");
            return;
        }

        if (cuenta instanceof cuentaCorriente cuentaCorriente
                && cuentaCorriente.getCreditoDisponible() < 0) {
            System.out.println("El credito inicial no puede ser negativo.");
            return;
        }

        if (buscarcuentaPorDocumento(cuenta.getCliente().getDocumento()) != null) {
            System.out.println("Ya existe una cuenta para esa cedula.");
            return;
        }

        cuentas.add(cuenta);
        System.out.println("cuenta creada correctamente.");
    }

    public cuenta buscarcuentaPorDocumento(String documento) {

        if (documento == null) {
            return null;
        }

        for (cuenta cuenta : cuentas) {
            cliente cliente = cuenta.getCliente();
            if (cliente != null && documento.equals(cliente.getDocumento())) {
                return cuenta;
            }
        }

        return null;
    }
    // Listar todas las cuentas
    public void listarCuentas() {
        if (cuentas.isEmpty()) {
            System.out.println("No hay cuentas registradas.");
        } else {
            System.out.println("\n===== LISTADO DE CUENTAS =====");
            for (cuenta c : cuentas) {
                System.out.println(c);
            }
        }
    }
}