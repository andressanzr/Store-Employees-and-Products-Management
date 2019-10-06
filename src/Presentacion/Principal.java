package Presentacion;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Scanner;

import Dominio.*;
import Persistencia.EmpleadoDao;
import Persistencia.EmpleadoDiurnoDao;
import Persistencia.ProductoNoPerecederoDao;
import Persistencia.ProductoPerecederoDao;

class ExceptionCodigoErroneo extends Exception {
	int codigoError = 111;
}

class ExceptionPassErronea extends Exception {
	int codigoError = 222;
}

class ExceptionNumeroProductosInsuficientes extends Exception {
	int codigoError = 333;
}

public class Principal { // Clase Principal
	
	static Scanner teclado = new Scanner(System.in);
	static int tipoEmpleadoGlobal = 0;
	static String nombreEmpleadoGlobal = "";

	// tipo 1 Diurno
	// tipo 2 Nocturno
	public static void main(String[] args) throws IOException {
		boolean cerrarSesion = false;
		do {
			//CARGAR TODOS LOS DATOS DE LOS FICHEROS
			ArrayList<Empleado> empleadosDiurnos = new ArrayList<Empleado>();
			Empleado emp1 = new EmpleadoDiurno();
			empleadosDiurnos = emp1.leerEmpleados();

			ArrayList<Empleado> empleadosNocturnos = new ArrayList<Empleado>();
			Empleado emp2 = new EmpleadoNocturno();
			empleadosNocturnos = emp2.leerEmpleados();

			ArrayList<Producto> productosPerecederos = new ArrayList<Producto>();
			Producto pro1 = new ProductoPerecedero();

			productosPerecederos = pro1.leerProducto();
			ArrayList<Producto> productosNoPerecederos = new ArrayList<Producto>();
			Producto pro2 = new ProductoNoPerecedero();

			productosNoPerecederos = pro2.leerProducto();

			ArrayList<Oferta> ofertas = new ArrayList<Oferta>();
			Oferta of1 = new Oferta();

			ofertas = of1.leerOferta();

			//DECLARACION DE VARIABLES
			ArrayList<Producto> cesta = new ArrayList<Producto>();

			String pass;
			int codigoAcceso = 0, opcionSeleccionada, case1_respuesta, case2_respuesta, codigoNuevo;
			int codigoProductoAnadir = 0;
			int codigoProductoModificar = 0;
			String nombreNuevo, contrasenaNueva;
			double precioNuevo;
			double totalCesta = 0;
			boolean loginCorrecto = false;
			int[][] tipoProductoModIndex = new int[2][2];
			int numUnidadesNuevo;
			boolean datosRepetidos = false;
			boolean opcionInvalida = false;
			while (!loginCorrecto) {
				System.out.println("Introduzca su codigo de acceso:");
				codigoAcceso = teclado.nextInt();
				System.out.println("Introduzca su contrase�a:");
				pass = teclado.next();
				loginCorrecto = comprobarLogin(empleadosDiurnos, empleadosNocturnos, codigoAcceso, pass);
			}
			do {
				System.out.println(
						"1.	Hacer pedido\n2.	Modificar producto\n3.	Cambiar contrase�a empleado\n4.	Log out");
				opcionSeleccionada = teclado.nextInt();
				switch (opcionSeleccionada) {// switch principal primer menu

				case 1:// caso 1 hacer pedido
					boolean caso1Bucle = true;
					while (caso1Bucle) {
						System.out.println("1.1	A�adir un tipo de producto\n" + "1.2	Visualizar precio total\n"
								+ "1.3	Imprimir factura\n" + "1.4	Terminar pedido");
						case1_respuesta = teclado.nextInt();
						switch (case1_respuesta) {// switch caso hacer pedido
						case 1:// caso 1.1 a�adir productos
							int numTiposProducto = 0;
							do {
								System.out.println("Indique el numero de tipos de producto que desea comprar: ");
								numTiposProducto = teclado.nextInt();
								if (numTiposProducto > (ProductoPerecederoDao.contadorPerecedero
										+ ProductoNoPerecederoDao.contadorNoPerecedero)) {
									System.err.println("Numero de tipos de producto demasiado grande");
								}
							} while (numTiposProducto > (ProductoPerecederoDao.contadorPerecedero
									+ ProductoNoPerecederoDao.contadorNoPerecedero));

							for (int i = 0; i < productosPerecederos.size(); i++) {
								System.out.println("C�digo: " + productosPerecederos.get(i).getCodigoProducto()
										+ " Nombre: " + productosPerecederos.get(i).getNombre()
										+ "| N�mero de unidades disponibles: "
										+ productosPerecederos.get(i).getUnidades());
							}
							for (int i = 0; i < productosNoPerecederos.size(); i++) {
								System.out.println("C�digo : " + productosNoPerecederos.get(i).getCodigoProducto()
										+ " Nombre: " + productosNoPerecederos.get(i).getNombre()
										+ "| N�mero de unidades disponibles: "
										+ productosNoPerecederos.get(i).getUnidades());
							}
							for (int j = 0; j < numTiposProducto; j++) {

								do {
									System.out.println("Introduzca el codigo de producto que desea a�adir :");
									codigoProductoAnadir = teclado.nextInt();
								} while (!anadirProductos(codigoProductoAnadir, cesta, productosPerecederos,
										productosNoPerecederos));
							}
							System.out.println("Productos a�adidos correctamente");
							break;
						case 2:// caso 1.2 Visualizar precio total
							System.out.println("Total de la cesta: " + totalCesta);
							break;
						case 3:// caso 1.3 Imprimir factura
							totalCesta = ((ProductoPerecedero) pro1).calcularOfertasPerecedero(cesta,
									productosPerecederos)
									+ ((ProductoNoPerecedero) pro2).calcularOfertasNoPerecedero(cesta,
											productosNoPerecederos);
							imprimirCesta(cesta, totalCesta);
							System.out.println("Le atendi� :" + nombreEmpleadoGlobal);
							break;
						case 4:// caso 4 Terminar pedido
							totalCesta = ((ProductoPerecedero) pro1).calcularOfertasPerecedero(cesta,
									productosPerecederos)
									+ ((ProductoNoPerecedero) pro2).calcularOfertasNoPerecedero(cesta,
											productosNoPerecederos);
							System.out.println("Total de la cesta: " + totalCesta);
							break;
						}// fin switch

					} // fin while
					break;
				case 2:// caso 2 modificar producto
						// Imprimir productos
					for (int i = 0; i < productosPerecederos.size(); i++) {
						System.out.println("C�digo : " + productosPerecederos.get(i).getCodigoProducto() + " Nombre: "
								+ productosPerecederos.get(i).getNombre());
					}
					for (int i = 0; i < productosNoPerecederos.size(); i++) {
						System.out.println("C�digo : " + productosNoPerecederos.get(i).getCodigoProducto() + " Nombre: "
								+ productosNoPerecederos.get(i).getNombre());
					}

					boolean codigoModCorrecto = false;
					do {
						System.out.println("Introduzca el codigo de producto que desea modificar :");
						codigoProductoModificar = teclado.nextInt();
						for (int i = 0; i < productosPerecederos.size(); i++) {
							if (codigoProductoModificar == (productosPerecederos.get(i).getCodigoProducto())) {

								// Producto existe
								codigoModCorrecto = true;
								// [0] [0] se guarda el tipo de producto 1=Perecedero 2=noPerecedero
								// [1] [1] se guarda la posicion en la lista
								tipoProductoModIndex[0][0] = 1;
								tipoProductoModIndex[1][1] = i;
								i = productosPerecederos.size();
							}
						}
						for (int i = 0; i < productosNoPerecederos.size(); i++) {
							if (codigoProductoModificar == (productosNoPerecederos.get(i).getCodigoProducto())) {

								// Producto existe
								codigoModCorrecto = true;
								tipoProductoModIndex[0][0] = 2;
								tipoProductoModIndex[1][1] = i;
								i = productosNoPerecederos.size();

							}
						}
					} while (!codigoModCorrecto);
					System.out.println("1.Modificar nombre\n" + "2.Modificar precio\n" + "3.Modificar c�digo\n"
							+ "4.Modificar num unidades \n");
					case2_respuesta = teclado.nextInt();
					switch (case2_respuesta) {// case 2 Modificar productos
					case 1:// modificar nombre
						do {
							System.out.println("Introduzca el nuevo nombre:");
							nombreNuevo = teclado.next();
							datosRepetidos = modificarNombreCodigoProducto(1, nombreNuevo, 0, productosPerecederos,
									productosNoPerecederos);
							if (!datosRepetidos) {
								switch (tipoProductoModIndex[0][0]) {
								case 1:
									productosPerecederos.get(tipoProductoModIndex[1][1]).setNombre(nombreNuevo);
									pro1.modificarProductos(productosPerecederos);
									break;
								case 2:
									productosNoPerecederos.get(tipoProductoModIndex[1][1]).setNombre(nombreNuevo);
									pro2.modificarProductos(productosNoPerecederos);
									break;
								}
								System.out.println("Se ha cambiado satisfactoriamente el nombre del producto");
							}
						} while (datosRepetidos);
						break;
					case 2:// modificar precio
						System.out.println("Introduzca el nuevo precio:");
						precioNuevo = teclado.nextDouble();
						switch (tipoProductoModIndex[0][0]) {
						case 1:
							productosPerecederos.get(tipoProductoModIndex[1][1]).setPrecio(precioNuevo);
							pro1.modificarProductos(productosPerecederos);
							break;
						case 2:
							productosNoPerecederos.get(tipoProductoModIndex[1][1]).setPrecio(precioNuevo);
							pro2.modificarProductos(productosNoPerecederos);
							break;
						}
						System.out.println("Se ha cambiado satisfactoriamente el precio del producto");
						break;
					case 3:// modificar codigo
						do {
							System.out.println("Introduzca el nuevo codigo:");
							codigoNuevo = teclado.nextInt();
							datosRepetidos = modificarNombreCodigoProducto(2, "", codigoNuevo, productosPerecederos,
									productosNoPerecederos);

						} while (datosRepetidos);
						if (!datosRepetidos) {
							switch (tipoProductoModIndex[0][0]) {
							case 1:
								productosPerecederos.get(tipoProductoModIndex[0][1]).setCodigoProducto(codigoNuevo);
								pro1.modificarProductos(productosPerecederos);
								break;
							case 2:
								productosNoPerecederos.get(tipoProductoModIndex[1][1]).setCodigoProducto(codigoNuevo);
								pro2.modificarProductos(productosNoPerecederos);
								break;
							}
							System.out.println("Se ha cambiado satisfactoriamente el c�digo del producto");
						}
						break;
					case 4:// modificar num unidades
						do {
							System.out.println("Introduzca el nuevo n�mero de unidades:");
							numUnidadesNuevo = teclado.nextInt();
						} while (numUnidadesNuevo < 1);

						switch (tipoProductoModIndex[0][0]) {
						case 1:
							productosPerecederos.get(tipoProductoModIndex[0][1]).setUnidades(numUnidadesNuevo);
							pro1.modificarProductos(productosPerecederos);
							break;
						case 2:
							productosNoPerecederos.get(tipoProductoModIndex[1][1]).setUnidades(numUnidadesNuevo);
							pro2.modificarProductos(productosNoPerecederos);
							break;
						}
						break;
					}
					opcionInvalida = true;
					break;
				case 3:// caso 3 modificar contrase�a empleado
					System.out.println("Introduzca una contrase�a nueva");
					contrasenaNueva = teclado.next();
					int tipoEmpleado;
					tipoEmpleado = modificarContra(codigoAcceso, contrasenaNueva, empleadosDiurnos, empleadosNocturnos);
					switch (tipoEmpleado) {
					case 1:
						emp1.modificarEmpleados(empleadosDiurnos);
						break;
					case 2:
						emp2.modificarEmpleados(empleadosNocturnos);
						break;
					}
					cerrarSesion = true;
					break;
				case 4:// caso 4 cerrar sesion
					double totalProductividadEmpleado = 0;
					switch (tipoEmpleadoGlobal) {
					case 1:
						totalProductividadEmpleado = ((EmpleadoDiurno) emp1)
								.calcularProductividadDiurno(((EmpleadoDiurno) emp1), totalCesta);
						break;
					case 2:
						totalProductividadEmpleado = ((EmpleadoNocturno) emp2)
								.calcularProductividadNocturno(((EmpleadoNocturno) emp1), totalCesta);
						break;
					}
					System.out.println("Productividad total empleado :" + totalProductividadEmpleado + " �");
					cerrarSesion = true;
					cesta.clear();// vacia la cesta
					System.out.println("Sesion cerrada");

					break;
				default:// caso por defecto si no introduce una de las opciones
					System.out.println("Introduzca una de las opciones");
					opcionInvalida = true;
					loginCorrecto = false;
					break;
				}// fin switch principal primer menu
			} while (opcionInvalida);
		} while (cerrarSesion);
	}// fin main

	public static boolean comprobarLogin(ArrayList<Empleado> empleadosDiurnos, ArrayList<Empleado> empleadosNocturnos,

			int codigoAcceso, String pass) {
		boolean loginCorrecto = false;
		boolean codigoCorrecto = false;
		boolean passCorrecto = false;
		try {
			for (int i = 0; i < empleadosDiurnos.size(); i++) {
				if (empleadosDiurnos.get(i).getCodigoAcceso() == codigoAcceso) {
					codigoCorrecto = true;
					if (empleadosDiurnos.get(i).getPassword().equals(pass)) {
						passCorrecto = true;
						loginCorrecto = true;
						tipoEmpleadoGlobal = 1;
						nombreEmpleadoGlobal = empleadosDiurnos.get(i).getNombreUsuario();
						System.out.println("Ha entrado correctamente a su cuenta");
						break;
					}

				}
			}
			if (loginCorrecto == false) {
				for (int j = 0; j < empleadosNocturnos.size(); j++) {
					if (empleadosNocturnos.get(j).getCodigoAcceso() == codigoAcceso) {
						codigoCorrecto = true;
						if (empleadosNocturnos.get(j).getPassword().equals(pass)) {
							passCorrecto = true;
							loginCorrecto = true;
							tipoEmpleadoGlobal = 2;
							nombreEmpleadoGlobal = empleadosNocturnos.get(j).getNombreUsuario();
							System.out.println("Ha entrado correctamente a su cuenta");
							break;
						}
					}
				}
			}
			if (loginCorrecto == false) {
				if (!codigoCorrecto)
					throw new ExceptionCodigoErroneo();
				if (!passCorrecto)
					throw new ExceptionPassErronea();
			}
		} catch (ExceptionCodigoErroneo e1) {
			System.err.println("Error " + e1.codigoError);
			System.out.println("Codigo erroneo");
		} catch (ExceptionPassErronea e2) {
			System.err.println("Error " + e2.codigoError);
			System.out.println("Contrase�a erronea");
		}
		return loginCorrecto;
	}

	public static boolean anadirProductos(int codigoProductoAnadir, ArrayList<Producto> cesta,
			ArrayList<Producto> productosPerecederos, ArrayList<Producto> productosNoPerecederos) {
		boolean codigoCorrecto = false;
		int tipoProducto = 0;
		int numUnidades = 0;
		boolean numUnidadesOK = false;
		boolean productoRepetido = false;
		ArrayList<Producto> tipoBuscar = null;

		for (int i = 0; i < productosPerecederos.size(); i++) {
			if (codigoProductoAnadir == (productosPerecederos.get(i).getCodigoProducto())) {
				i = productosPerecederos.size();
				// Producto existe
				codigoCorrecto = true;
				tipoProducto = 1;
			}
		}
		for (int i = 0; i < productosNoPerecederos.size(); i++) {
			if (codigoProductoAnadir == (productosNoPerecederos.get(i).getCodigoProducto())) {
				i = productosNoPerecederos.size();
				// Producto existe
				codigoCorrecto = true;
				tipoProducto = 2;
			}
		}
		for (int i = 0; i < cesta.size(); i++) {
			if (codigoProductoAnadir == (cesta.get(i).getCodigoProducto())) {
				productoRepetido = true;
				System.out.println("Producto ya en la cesta");
			}
		} // fin if codigo correcto
		if (codigoCorrecto && productoRepetido == false) {
			do {
				System.out.println("Cuantas unidades quiere a�adir: ");
				numUnidades = teclado.nextInt();
				switch (tipoProducto) {
				case 1:
					tipoBuscar = productosPerecederos;
					break;
				case 2:
					tipoBuscar = productosNoPerecederos;
					break;
				}
				for (int i = 0; i < tipoBuscar.size(); i++) {
					if (codigoProductoAnadir == (tipoBuscar.get(i).getCodigoProducto())) {
						if (numUnidades > tipoBuscar.get(i).getUnidades()) {
							numUnidadesOK = false;
							try {
								throw new ExceptionNumeroProductosInsuficientes();
							} catch (ExceptionNumeroProductosInsuficientes e3) {
								System.err.println("Error " + e3.codigoError);
								System.out.println("Numero de productos insuficientes");
							}
						} else {
							numUnidadesOK = true;
						}
					}

				}
			} while (!numUnidadesOK);// fin do while num unidades

			if (!productoRepetido) {
				switch (tipoProducto) {
				case 1:
					tipoBuscar = productosPerecederos;
					break;
				case 2:
					tipoBuscar = productosNoPerecederos;
					break;
				}

				for (int p = 0; p < tipoBuscar.size(); p++) {
					if (codigoProductoAnadir == tipoBuscar.get(p).getCodigoProducto()) {
						cesta.add(tipoBuscar.get(p));
						System.out.println("Producto a�adido correctamente");
					}
				}
			}
			if (!codigoCorrecto && !productoRepetido) {
				return false;
			} else {
				return true;
			}

		}
		return false;
	}

	/*
	 * public static double calcularPrecioCesta(ArrayList<Producto> cesta,
	 * ArrayList<Producto> productosPerecederos, ArrayList<Producto>
	 * productosNoPerecederos) { int numProdOferta = 0; double totalCesta = 0; for
	 * (int i = 0; i < cesta.size(); i++) { for (int p = 0; p <
	 * productosPerecederos.size(); p++) { if (cesta.get(i).getCodigoProducto() ==
	 * productosPerecederos.get(p).getCodigoProducto()) {
	 * 
	 * ((ProductoPerecedero) cesta.get(i)).getDiasCaducar();
	 * System.out.println("El producto con codigo :" +
	 * cesta.get(i).getCodigoProducto() + " tiene un descuento al quedarle " +
	 * ((ProductoPerecedero) cesta.get(i)).getDiasCaducar() + " dias para caducar");
	 * switch (((ProductoPerecedero) cesta.get(i)).getDiasCaducar()) { case 1:
	 * cesta.get(i).setPrecio(cesta.get(i).getPrecio() * 0.25); break; case 2:
	 * cesta.get(i).setPrecio(cesta.get(i).getPrecio() * 0.33); break; case 3:
	 * cesta.get(i).setPrecio(cesta.get(i).getPrecio() * 0.5); break; }
	 * productosConOferta[numProdOferta] = "El producto :" +
	 * cesta.get(i).getCodigoProducto() +
	 * " tiene una oferta por caducidad proxima (" + ((ProductoPerecedero)
	 * cesta.get(i)).getDiasCaducar() + " dias)"; numProdOferta++; totalCesta +=
	 * cesta.get(i).getPrecio() * cesta.get(i).getUnidades(); }
	 * 
	 * } // fin for perecedero
	 * 
	 * for (int t = 0; t < productosNoPerecederos.size(); t++) { if
	 * (cesta.get(i).getCodigoProducto() ==
	 * productosNoPerecederos.get(t).getCodigoProducto()) {
	 * 
	 * ((ProductoNoPerecedero) cesta.get(i)).getOferta(); int unidadesPagar = 0;
	 * switch (((ProductoNoPerecedero) cesta.get(i)).getOferta()) { case "2x1":
	 * unidadesPagar = (cesta.get(i).getUnidades() / 2) +
	 * (cesta.get(i).getUnidades() % 2); cesta.get(i).setPrecio(unidadesPagar);
	 * break; case "3x2": if (cesta.get(i).getUnidades() % 3 == 0) { unidadesPagar =
	 * (cesta.get(i).getUnidades() - 1); cesta.get(i).setPrecio(unidadesPagar); }
	 * break; default: int descuento = Integer.parseInt(((ProductoNoPerecedero)
	 * cesta.get(i)).getOferta()); cesta.get(i).setPrecio(cesta.get(i).getPrecio() *
	 * descuento * cesta.get(i).getUnidades()); } System.out.println("El producto :"
	 * + cesta.get(i).getCodigoProducto() + " tiene una oferta de " +
	 * ((ProductoNoPerecedero) cesta.get(i)).getOferta()); totalCesta +=
	 * cesta.get(i).getPrecio(); }
	 * 
	 * } // fin for no perecederos } // fin for cesta return totalCesta;
	 * 
	 * }
	 */

	public static int modificarContra(int codigoAcceso, String contrasenaNueva, ArrayList<Empleado> empleadosDiurnos,
			ArrayList<Empleado> empleadosNocturnos) {
		boolean contraCambiada = false;
		int tipoEmpleado = 0;
		for (int i = 0; i < empleadosDiurnos.size(); i++) {
			if (empleadosDiurnos.get(i).getCodigoAcceso() == codigoAcceso) {
				empleadosDiurnos.get(i).setPassword(contrasenaNueva);
				contraCambiada = true;
				tipoEmpleado = 1;
				i = empleadosDiurnos.size();
				break;
			}

		}
		if (contraCambiada == false) {
			for (int j = 0; j < empleadosNocturnos.size(); j++) {
				if (empleadosNocturnos.get(j).getCodigoAcceso() == codigoAcceso) {
					empleadosNocturnos.get(j).setPassword(contrasenaNueva);
					contraCambiada = true;
					tipoEmpleado = 2;
					j = empleadosDiurnos.size();
					break;
				}
			}
		}
		if (contraCambiada) {
			System.out.println("Contrase�a cambiada correctamente");
		}
		return tipoEmpleado;
	}

	public static boolean modificarNombreCodigoProducto(int control, String nuevoNombre, int nuevoCodigo,
			ArrayList<Producto> productosPerecederos, ArrayList<Producto> productosNoPerecederos) {
		boolean datoRepetido = false;
		switch (control) {
		case 1:// caso modificar nombre
			for (int i = 0; i < productosPerecederos.size(); i++) {
				if (nuevoNombre.equals(productosPerecederos.get(i).getNombre())) {
					datoRepetido = true;
					i = productosPerecederos.size();
				}
			}
			for (int i = 0; i < productosNoPerecederos.size(); i++) {
				if (nuevoNombre.equals(productosNoPerecederos.get(i).getNombre())) {
					datoRepetido = true;
					i = productosNoPerecederos.size();
				}
			}
			break;
		case 2:// caso modificar codigo
			for (int i = 0; i < productosNoPerecederos.size(); i++) {
				if (nuevoCodigo == productosNoPerecederos.get(i).getCodigoProducto()) {
					datoRepetido = true;
					i = productosNoPerecederos.size();
				}
			}
			for (int i = 0; i < productosPerecederos.size(); i++) {
				if (nuevoCodigo == productosPerecederos.get(i).getCodigoProducto()) {
					datoRepetido = true;
					i = productosPerecederos.size();
				}
			}
			break;
		}// fin switch

		return datoRepetido;
	}

	public static void imprimirCesta(ArrayList<Producto> cesta, double totalCesta,) {
		System.out.println("Factura");
		for (int i = 0; i < cesta.size(); i++) {
			System.out.println(cesta.get(i).getUnidades() + " x " + cesta.get(i).getNombre() + " precio por unidad:"
					+ cesta.get(i).getPrecio() + "�");
		}
		System.out.println("Total de la cesta: " + totalCesta + "�");
	}

	public void imprimirFactura(ArrayList<Producto> cesta, Producto proPere, Producto proNoPere ) throws IOException {
		PrintWriter out = new PrintWriter(new FileWriter("factura.txt"));
		out.println("Factura:");
		out.println("Supermercado IFP");
		out.println("CIF: R3552293G");
		for (int i = 0; i < cesta.size(); i++) {
			out.println(cesta.get(i).getUnidades() + " x " + cesta.get(i).getNombre() + " precio por unidad:"
					+ cesta.get(i).getPrecio()+"�");
		}
		out.println("Ofertas de la cesta: ");
		out.println(((ProductoPerecedero)proPere).getTextoOfertaPere());
		out.close();
		
	}
}
