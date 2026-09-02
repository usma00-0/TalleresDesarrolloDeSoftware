
import java.util.Scanner;
import metodos.CuentaAhorros;
import metodos.cliente;
import metodos.cuenta;
import metodos.cuentaCorriente;
import service.clienteService;
import service.cuentaService;
import service.facturaService;
import service.pagoService;

public class Main {

    public static void main(String[] args) {

        try (Scanner sc = new Scanner(System.in)) {

            clienteService clienteService = new clienteService();
            cuentaService cuentaService = new cuentaService();
            facturaService facturaService = new facturaService();
            pagoService pagoService = new pagoService();

            int opcionPrincipal;

            do {

                System.out.println("\n===== SISTEMA DE PAGOS =====");
                System.out.println("1. Gestion de Clientes");
                System.out.println("2. Gestion de Cuentas");
                System.out.println("3. Gestion de Facturas");
                System.out.println("4. Gestion de Pagos");
                System.out.println("0. Salir");

                System.out.print("Seleccione una opcion: ");
                opcionPrincipal = sc.nextInt();

                switch (opcionPrincipal) {

                    case 1:
                        menuClientes(sc, clienteService);
                        break;

                    case 2:
                        menuCuentas(
                                sc,
                                clienteService,
                                cuentaService
                        );
                        break;

                    case 3:
                        menuFacturas(
                                sc,
                                clienteService,
                                facturaService
                        );
                        break;

                    case 4:
                        menuPagos(
                                sc,
                                cuentaService,
                                facturaService,
                                pagoService
                        );
                        break;

                    case 0:
                        System.out.println("Saliendo...");
                        break;

                    default:
                        System.out.println("Opcion invalida.");
                }

            } while (opcionPrincipal != 0);
        } // Cierre del try-catch
    }

    public static void menuClientes(
            Scanner sc,
            clienteService clienteService) {

        int opcion;

        do {

            System.out.println("\n===== GESTION CLIENTES =====");
            System.out.println("1. Crear Cliente");
            System.out.println("2. Buscar Cliente");
            System.out.println("3. Actualizar Cliente");
            System.out.println("4. Eliminar Cliente");
            System.out.println("5. Listar Clientes");
            System.out.println("0. Volver");

            opcion = sc.nextInt();

            switch (opcion) {

                case 1:

                    sc.nextLine();

                    System.out.print("Nombre: ");
                    String nombre = sc.nextLine();

                    System.out.print("Cedula: ");
                    String documento = sc.nextLine();

                    clienteService.crearcliente(
                            nombre,
                            documento
                    );

                    break;

                case 2:

                    System.out.print("CC Cliente: ");
                    String buscar = sc.next();

                    System.out.println(
                            clienteService.buscarclientePorDocumento(
                                    buscar
                            )
                    );

                    break;

                case 3:

                    System.out.print("CC Cliente: ");
                    String idActualizar = sc.next();
                    sc.nextLine();

                    System.out.print("Nuevo Nombre: ");
                    String nuevoNombre = sc.nextLine();

                    System.out.print("Nuevo Documento: ");
                    String nuevoDocumento = sc.nextLine();

                    clienteService.actualizarcliente(
                            idActualizar,
                            nuevoNombre,
                            nuevoDocumento
                    );

                    break;

                case 4:

                    System.out.print("CC Cliente: ");
                    String eliminar = sc.next();

                    clienteService.eliminarcliente(
                            eliminar
                    );

                    break;

                case 5:

                    clienteService.listarclientes();

                    break;

            }

        } while (opcion != 0);
    }

    public static void menuCuentas(
            Scanner sc,
            clienteService clienteService,
            cuentaService cuentaService) {

        int opcion;

        do {

            System.out.println("\n===== GESTION CUENTAS =====");
            System.out.println("1. Crear Cuenta Ahorros");
            System.out.println("2. Crear Cuenta Corriente");
            System.out.println("3. Consultar Saldo");
            System.out.println("4. Listar Cuentas");
            System.out.println("0. Volver");

            opcion = sc.nextInt();

            switch (opcion) {

                case 1:

                    System.out.print("Numero Cuenta: ");
                    int numero = sc.nextInt();

                    System.out.print("Saldo Inicial: ");
                    double saldo = sc.nextDouble();
                    sc.nextLine();

                    System.out.print("Cedula Cliente: ");
                    String docCliente = sc.nextLine();

                    cliente cli
                            = clienteService.buscarclientePorDocumento(
                                    docCliente
                            );

                        if (cli == null) {
                        System.out.println("Cliente no encontrado.");
                        } else {

                        CuentaAhorros ahorro
                                = new CuentaAhorros(
                                        numero,
                                        saldo,
                                        cli
                                );

                        cuentaService.crearcuenta(
                                ahorro
                        );
                    }

                    break;

                case 2:

                    System.out.print("Numero Cuenta: ");
                    int numeroCorriente = sc.nextInt();

                    System.out.print("Saldo Inicial: ");
                    double saldoCorriente = sc.nextDouble();

                    System.out.print("Credito: ");
                    double credito = sc.nextDouble();
                    sc.nextLine();

                    System.out.print("Cedula Cliente: ");
                    String docCorriente = sc.nextLine();

                    cliente cliCorriente
                            = clienteService.buscarclientePorDocumento(
                                    docCorriente
                            );

                        if (cliCorriente == null) {
                        System.out.println("Cliente no encontrado.");
                        } else {

                        cuentaCorriente corriente
                                = new cuentaCorriente(
                                        numeroCorriente,
                                        saldoCorriente,
                                        cliCorriente,
                                        credito
                                );

                        cuentaService.crearcuenta(
                                corriente
                        );
                    }

                    break;

                case 3:

                    sc.nextLine();
                    System.out.print("Cedula Cliente: ");
                    String documentoConsulta = sc.nextLine();
                    cuenta cuentaConsultada =
                        cuentaService.buscarcuentaPorDocumento(
                            documentoConsulta
                        );

                    if (cuentaConsultada == null) {
                    System.out.println("Cuenta no encontrada para ese cliente.");
                    } else {
                        double saldoDisponible = cuentaConsultada.getSaldo();

                        if (cuentaConsultada instanceof cuentaCorriente corriente) {
                            saldoDisponible += corriente.getCreditoDisponible();
                            System.out.println("Saldo en cuenta: "
                                + cuentaConsultada.getSaldo());
                            System.out.println("Credito disponible: "
                                + corriente.getCreditoDisponible());
                        }

                        System.out.println("Saldo disponible: " + saldoDisponible);
                    }

                    break;

                case 4:

                    cuentaService.listarCuentas();

                    break;

            }

        } while (opcion != 0);
    }

    public static void menuFacturas(
            Scanner sc,
            clienteService clienteService,
            facturaService facturaService) {

        int opcion;

        do {

            System.out.println("\n===== GESTION FACTURAS =====");
            System.out.println("1. Crear Factura");
            System.out.println("2. Buscar Factura");
            System.out.println("3. Facturas por Cliente");
            System.out.println("4. Listar Facturas");
            System.out.println("0. Volver");

            opcion = sc.nextInt();

            switch (opcion) {

                case 1:
                    System.out.print("Servicio: ");
                    sc.nextLine();
                    String servicio = sc.nextLine();

                    System.out.print("Valor: ");
                    double valor = sc.nextDouble();
                    sc.nextLine();

                    System.out.print("Cedula Cliente: ");
                    String documentoCliente = sc.nextLine();
                    cliente clienteFactura =
                            clienteService.buscarclientePorDocumento(
                                    documentoCliente
                            );

                    if (clienteFactura == null) {
                        System.out.println("Cliente no encontrado.");
                    } else {
                        facturaService.crearFactura(
                                servicio,
                                valor,
                                clienteFactura
                        );
                    }
                    break;

                case 2:
                    sc.nextLine();
                    System.out.print("Cedula Cliente: ");
                    String documentoBusqueda = sc.nextLine();
                    facturaService.listarFacturasCliente(documentoBusqueda);
                    break;

                case 3:

                    sc.nextLine();
                    System.out.print("Cedula Cliente: ");
                    String documentoFacturas = sc.nextLine();

                    facturaService.listarFacturasCliente(documentoFacturas);

                    break;

                case 4:

                    facturaService.listarfacturas();

                    break;
            }

        } while (opcion != 0);
    }

    public static void menuPagos(
            Scanner sc,
            cuentaService cuentaService,
            facturaService facturaService,
            pagoService pagoService) {

        int opcion;

        do {

            System.out.println("\n===== GESTION PAGOS =====");
            System.out.println("1. Procesar Pago");
            System.out.println("2. Ver Pagos por Cliente");
            System.out.println("3. Listar Todos los Pagos");
            System.out.println("0. Volver");

            opcion = sc.nextInt();

            switch (opcion) {

                case 1:

                    sc.nextLine();
                    System.out.print("Cedula Cliente: ");
                    String documentoPago = sc.nextLine();

                    System.out.print("Servicio de la Factura: ");
                    String servicioPago = sc.nextLine();

                    pagoService.procesarPago(
                        cuentaService.buscarcuentaPorDocumento(documentoPago),
                        facturaService.buscarFacturaPorDocumentoYServicio(
                            documentoPago,
                            servicioPago
                        )
                    );

                    break;

                case 2:

                    sc.nextLine();
                    System.out.print("Cedula Cliente: ");
                    String documentoPagos = sc.nextLine();

                    pagoService.listarPagosCliente(documentoPagos);

                    break;

                case 3:

                    pagoService.listarPagos();

                    break;
            }

        } while (opcion != 0);

    }

}
