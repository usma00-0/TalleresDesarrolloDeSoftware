package service;

import java.util.ArrayList;
import java.util.List;
import metodos.factura;

public class facturaService {

    private List<factura> facturas;
    private int siguienteId;

    public facturaService() {
        this.facturas = new ArrayList<>();
        this.siguienteId = 1;
    }

    public void crearFactura(String servicio, double valor,
                             metodos.cliente cliente) {
        crearfactura(new factura(0, servicio, valor, cliente));
    }

    // CREATE
    public void crearfactura(factura factura) {
        if (factura == null || factura.getCliente() == null
                || factura.getServicio() == null
                || factura.getServicio().trim().isEmpty()
                || factura.getValor() <= 0) {
            System.out.println("Los datos de la factura no son validos.");
            return;
        }

        factura.setId(siguienteId++);

        facturas.add(factura);
        System.out.println("factura creada correctamente.");
    }

    public List<factura> obtenerfacturasPorDocumento(String documento) {

        List<factura> resultado = new ArrayList<>();

        for (factura factura : facturas) {
            if (factura.getCliente() != null
                    && documento.equals(factura.getCliente().getDocumento())) {
                resultado.add(factura);
            }
        }

        return resultado;
    }

    public factura buscarFacturaPorDocumentoYServicio(String documento,
                                                       String servicio) {
        for (factura factura : facturas) {
            if (!factura.isPagada()
                    && factura.getCliente() != null
                    && documento.equals(factura.getCliente().getDocumento())
                    && factura.getServicio().equalsIgnoreCase(servicio)) {
                return factura;
            }
        }
        return null;
    }

    // Listar todas las facturas
    public void listarfacturas() {

        if (facturas.isEmpty()) {
            System.out.println("No existen facturas registradas.");
            return;
        }

        for (factura factura : facturas) {
            System.out.println(factura);
        }
    }


    public void listarFacturasCliente(String documento) {
        List<factura> resultado = obtenerfacturasPorDocumento(documento);

        if (resultado.isEmpty()) {
            System.out.println("El cliente no tiene facturas.");
            return;
        }

        for (factura factura : resultado) {
            System.out.println(factura);
        }
    }
}