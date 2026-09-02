package service;

import java.util.ArrayList;
import java.util.List;
import metodos.cliente;

public class clienteService {

    private List<cliente> clientes;
    private int siguienteId;

    public clienteService() {
        this.clientes = new ArrayList<>();
        this.siguienteId = 1;
    }

    // CREATE
    public boolean crearcliente(String nombre, String documento) {

        if (nombre == null || nombre.trim().isEmpty()) {
            System.out.println("El nombre es obligatorio.");
            return false;
        }

        if (documento == null || documento.trim().isEmpty()) {
            System.out.println("La cedula es obligatoria.");
            return false;
        }

        if (existeDocumento(documento)) {
            System.out.println("Ya existe un cliente con esa cedula.");
            return false;
        }

        cliente nuevoCliente =
                new cliente(
                        siguienteId++,
                        nombre,
                        documento
                );

        clientes.add(nuevoCliente);

        System.out.println("Cliente registrado correctamente.");
        return true;
    }

    // VALIDAR CEDULA REPETIDA
    private boolean existeDocumento(String documento) {

        for (cliente cliente : clientes) {

            if (cliente.getDocumento().equals(documento)) {
                return true;
            }
        }

        return false;
    }

    // READ
    public cliente buscarclientePorDocumento(String documento) {

        for (cliente cliente : clientes) {

            if (cliente.getDocumento().equals(documento)) {
                return cliente;
            }
        }

        return null;
    }

    // UPDATE
    public boolean actualizarcliente(
            String documento,
            String nuevoNombre,
            String nuevoDocumento) {

        cliente cliente = buscarclientePorDocumento(documento);

        if (cliente == null) {
            System.out.println("Cliente no encontrado.");
            return false;
        }

        if (!nuevoDocumento.equals(documento)) {
            if (existeDocumento(nuevoDocumento)) {
                System.out.println(
                        "La cedula ya pertenece a otro cliente."
                );
                return false;
            }
        }

        cliente.setNombre(nuevoNombre);
        cliente.setDocumento(nuevoDocumento);

        System.out.println("Cliente actualizado.");

        return true;
    }

    // DELETE
    public boolean eliminarcliente(String documento) {

        cliente cliente = buscarclientePorDocumento(documento);

        if (cliente != null) {

            clientes.remove(cliente);

            System.out.println("Cliente eliminado.");

            return true;
        }

        System.out.println("Cliente no encontrado.");

        return false;
    }

    // LISTAR
    public void listarclientes() {

        if (clientes.isEmpty()) {

            System.out.println(
                    "No existen clientes registrados."
            );

            return;
        }

        for (cliente cliente : clientes) {
            System.out.println(cliente);
        }
    }
}