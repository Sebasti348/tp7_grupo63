package ar.edu.unju.escmi.main;

import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import ar.edu.unju.escmi.dao.*;
import ar.edu.unju.escmi.dao.imp.*;
import ar.edu.unju.escmi.entities.Cliente;
import ar.edu.unju.escmi.entities.DetalleFactura;
import ar.edu.unju.escmi.entities.Factura;
import ar.edu.unju.escmi.entities.Producto;

public class Main {
	private static Scanner scanner = new Scanner(System.in);
	private static IClienteDao clienteDao = new ClienteDaoImp();
	private static IProductoDao productoDao = new ProductoDaoImp();
	private static IFacturaDao facturaDao = new FacturaDaoImp();
	private static IDetalleFactura detalleFactura = new DetalleFacturaDaoImp();

	public static void main(String[] args) {
		String opcion;
		do {
			System.out.println("=== MENÚ DE OPCIONES ===");
			System.out.println("1 - Alta de cliente");
			System.out.println("2 - Alta de producto");
			System.out.println("3 - Realizar la venta de productos (Alta de una nueva factura)");
			System.out.println("4 - Buscar una factura por número");
			System.out.println("5 - Eliminar una factura (eliminación lógica)");
			System.out.println("6 - Eliminar un producto (eliminación lógica)");
			System.out.println("7 - Modificar datos de cliente");
			System.out.println("8 - Modificar precio de producto");
			System.out.println("9 - Eliminar producto (eliminación lógica)");
			System.out.println("10 - Mostrar todas las facturas");
			System.out.println("11 - Mostrar todos los clientes");
			System.out.println("12 - Mostrar facturas con total mayor a $500.000");
			System.out.println("0 - Salir");
			System.out.print("Seleccione una opción: ");
			opcion = scanner.nextLine();

			switch (opcion) {
			case "1":
				altaCliente();
				break;
			case "2":
				altaProducto();
				break;
			case "3":
				realizarVenta();
				break;
			case "4":
				buscarFactura();
				break;
			case "5":
				eliminarFactura();
				break;
			case "6":
				eliminarProducto();
				break;
			case "7":
				modificarCliente();
				break;
			case "8":
				modificarPrecioProducto();
				break;
			case "9":
				eliminarProducto();
				break;
			case "10":
				mostrarFacturas();
				break;
			case "11":
				mostrarClientes();
				break;
			case "12":
				mostrarFacturasMayor500k();
				break;
			case "0":
				System.out.println("Saliendo del sistema...");
				break;
			default:
				System.out.println("Opción no válida. Intente nuevamente.");
				break;
			}
		} while (!opcion.equals("0"));
	}

	private static void altaCliente() {
		System.out.print("Ingrese nombre del cliente: ");
		String nombre = scanner.nextLine();
		System.out.print("Ingrese apellido del cliente: ");
		String apellido = scanner.nextLine();
		System.out.print("Ingrese domicilio del cliente: ");
		String domicilio = scanner.nextLine();
		int dni = 0;
		while (true) {
			System.out.print("Ingrese DNI del cliente: ");
			try {
				dni = scanner.nextInt();
				scanner.nextLine();
				break;
			} catch (InputMismatchException e) {
				System.out.println("Error: Debe ingresar un número entero para el DNI del cliente.");
				scanner.nextLine();
			}
		}
		Cliente cliente = new Cliente(nombre, apellido, domicilio, dni, true);
		clienteDao.guardarCliente(cliente);
		System.out.println("Cliente registrado exitosamente.");
	}

	private static void altaProducto() {
		System.out.print("Ingrese nombre del producto: ");
		String nombre = scanner.nextLine();
		System.out.print("Ingrese precio del producto: ");
		double precio = 0;
		while (true) {
			try {
				precio = scanner.nextDouble();
				scanner.nextLine();
				break;
			} catch (NumberFormatException e) {
				System.out.println("Error: Debe ingresar un número valido.");
				scanner.nextLine();
			}
		}
		Producto producto = new Producto(nombre, precio, true);
		productoDao.guardarProducto(producto);
		System.out.println("Producto registrado exitosamente.");
	}

	private static void realizarVenta() {
		long iid_cli = 0;
		while (true) {
			System.out.print("Ingrese DNI del cliente: ");
			try {
				iid_cli = scanner.nextLong();
				scanner.nextLine();
				break;
			} catch (InputMismatchException e) {
				System.out.println("Error: Debe ingresar un número entero para el DNI del cliente.");
				scanner.nextLine();
			}
		}

		long idProd = 0;
		while (true) {
			System.out.print("Ingrese ID del producto: ");
			try {
				idProd = scanner.nextLong();
				scanner.nextLine();
				break;
			} catch (InputMismatchException e) {
				System.out.println("Error: Debe ingresar un número válido para el ID del producto.");
				scanner.nextLine();
			}
		}

		int cantidad = 0;
		while (true) {
			System.out.print("Ingrese la cantidad de productos a comprar: ");
			try {
				cantidad = scanner.nextInt();
				scanner.nextLine();
				if (cantidad > 0)
					break;
				System.out.println("Error: La cantidad debe ser mayor que cero.");
			} catch (InputMismatchException e) {
				System.out.println("Error: Debe ingresar un número entero válido para la cantidad.");
				scanner.nextLine();
			}
		}

		long numeroFactura = 0;
		while (true) {
			System.out.print("Ingrese el número de factura: ");
			try {
				numeroFactura = scanner.nextLong();
				scanner.nextLine();
				break;
			} catch (InputMismatchException e) {
				System.out.println("Error: Debe ingresar un número entero válido para el número de factura.");
				scanner.nextLine();
			}
		}

		Cliente cliente = clienteDao.obtenerClienteId(iid_cli);

		if (cliente == null) {
			System.out.println("Error: No existe un cliente con ese DNI.");
			return;
		}

		Producto producto = productoDao.obtenerProductoPorId(idProd);
		if (producto == null || !producto.isEstado()) {
			System.out.println("Error: El producto con ID proporcionado no existe o está inactivo.");
			return;
		}

		detalleFactura.guardarDetalle(new DetalleFactura(producto, cantidad, cantidad * producto.getPrecioUnitario()));
		facturaDao.guardarFactura(new Factura(LocalDate.now(), cliente, cliente.getDomicilio(), numeroFactura, true));
		System.out.println("Factura creada exitosamente.");
	}

	private static void buscarFactura() {
		long numeroFactura = 0;
		while (true) {
			System.out.print("Ingrese el número de factura: ");
			try {
				numeroFactura = scanner.nextLong();
				scanner.nextLine();
				break;
			} catch (InputMismatchException e) {
				System.out.println("Error: Debe ingresar un número entero válido para el número de factura.");
				scanner.nextLine();
			}
		}
		Factura factura = facturaDao.obtenerFacturaPorNumero(numeroFactura);
		if (factura != null) {
			System.out.println("Factura encontrada:");
			System.out.println("ID: " + factura.getId());
			System.out.println("Fecha: " + factura.getFecha());
			System.out
					.println("Cliente: " + factura.getCliente().getNombre() + " " + factura.getCliente().getApellido());
			System.out.println("Domicilio del cliente: " + factura.getDomicilio());
			System.out.println("Total: $" + factura.getTotal());
			System.out.println("Estado: " + (factura.isEstado() ? "Activa" : "Inactiva"));

			DetalleFactura detalleFac = detalleFactura.obtenerDetallePorId(factura.getId());
			if (detalleFac != null) {

				System.out.println("Detalles de la factura:");
				System.out.println("- Producto: " + detalleFac.getProducto().getDescripcion());
				System.out.println("  Cantidad: " + detalleFac.getCantidad());
				System.out.println("  Subtotal: $" + detalleFac.getSubtotal());

			} else {
				System.out.println("No hay detalles de productos en esta factura.");
			}
		} else {
			System.out.println("Factura no encontrada.");
		}
	}

	private static void eliminarFactura() {
		long numeroFactura = 0;
		while (true) {
			System.out.print("Ingrese el número de factura a eliminar: ");
			try {
				numeroFactura = scanner.nextLong();
				scanner.nextLine();
				break;
			} catch (InputMismatchException e) {
				System.out.println("Error: Debe ingresar un número entero válido para el número de factura.");
				scanner.nextLine();
			}
		}
		Factura factura = facturaDao.obtenerFacturaPorNumero(numeroFactura);
		if (factura != null) {
			facturaDao.eliminarFactura(factura);
			System.out.println("Factura eliminada lógicamente.");
		} else {
			System.out.println("Factura no encontrada.");
		}
	}

	private static void eliminarProducto() {
		long idProd = 0;
		while (true) {
			System.out.print("Ingrese el ID del producto a eliminar: ");
			try {
				idProd = scanner.nextLong();
				scanner.nextLine();
				break;
			} catch (InputMismatchException e) {
				System.out.println("Error: Debe ingresar un número válido para el ID del producto.");
				scanner.nextLine();
			}
		}
		Producto producto = productoDao.obtenerProductoPorId(idProd);
		if (producto != null) {
			productoDao.eliminarProducto(producto);
			System.out.println("Producto eliminado lógicamente.");
		} else {
			System.out.println("Producto no encontrado.");
		}
	}

	private static void modificarCliente() {

		long iid_cli = 0;
		while (true) {
			System.out.print("Ingrese el ID del cliente a modificar: ");
			try {
				iid_cli = scanner.nextLong();
				scanner.nextLine();
				break;
			} catch (InputMismatchException e) {
				System.out.println("Error: Debe ingresar un número entero para el DNI del cliente.");
				scanner.nextLine();
			}
		}
		Cliente cliente = clienteDao.obtenerClienteId(iid_cli);
		if (cliente != null) {
			System.out.print("Ingrese nuevo nombre del cliente: ");
			String nombre = scanner.nextLine();
			System.out.print("Ingrese nuevo apellido del cliente: ");
			String apellido = scanner.nextLine();
			System.out.print("Ingrese nuevo domicilio del cliente: ");
			String domicilio = scanner.nextLine();
			int dni = 0;
			while (true) {
				System.out.print("Ingrese nuevo DNI del cliente: ");
				try {
					dni = scanner.nextInt();
					scanner.nextLine();
					break;
				} catch (InputMismatchException e) {
					System.out.println("Error: Debe ingresar un número entero para el DNI del cliente.");
					scanner.nextLine();
				}
			}
			cliente.setApellido(apellido);
			cliente.setDni(dni);
			cliente.setDomicilio(domicilio);
			cliente.setNombre(nombre);
			clienteDao.modificarCliente(cliente);
			System.out.println("Cliente modificado exitosamente.");
		} else {
			System.out.println("Cliente no encontrado.");
		}
	}

	private static void modificarPrecioProducto() {
		long idProd = 0;
		while (true) {
			System.out.print("Ingrese el ID del producto a modificar: ");
			try {
				idProd = scanner.nextLong();
				scanner.nextLine();
				break;
			} catch (InputMismatchException e) {
				System.out.println("Error: Debe ingresar un número válido para el ID del producto.");
				scanner.nextLine();
			}
		}
		Producto producto = productoDao.obtenerProductoPorId(idProd);
		if (producto != null) {
			double nuevoPrecio = 0;
			while (true) {
				System.out.print("Ingrese nuevo precio: ");
				try {
					nuevoPrecio = Double.parseDouble(scanner.nextLine());
					if (nuevoPrecio > 0) {
						productoDao.modificarPrecioProducto(idProd, nuevoPrecio);
						System.out.println(
								"Precio de producto modificado exitosamente a: $" + producto.getPrecioUnitario());
						break;
					} else {
						System.out.println("El precio debe ser un valor positivo.");
					}
				} catch (NumberFormatException e) {
					System.out.println("Error: Debe ingresar un número válido para el precio.");
				}
			}
		} else {
			System.out.println("Producto no encontrado.");
		}
	}

	private static void mostrarFacturas() {
		List<Factura> facturas = facturaDao.obtenerFacturas();
		System.out.println("Lista de facturas:");
		for (Factura factura : facturas) {
			System.out.println(factura.toString());
		}
	}

	private static void mostrarClientes() {
		List<Cliente> clientes = clienteDao.mostrarClientes();
		System.out.println("Lista de clientes:");
		for (Cliente cliente : clientes) {
			System.out.println(cliente.toString());
		}
	}

	private static void mostrarFacturasMayor500k() {
		List<Factura> facturas = facturaDao.obtenerFacturas();
		System.out.println("Facturas con un total mayor a $500,000:");
		for (Factura factura : facturas) {
			if (factura.getTotal() > 500000) {
				System.out.println(factura.toString());
			}
		}
	}

}
