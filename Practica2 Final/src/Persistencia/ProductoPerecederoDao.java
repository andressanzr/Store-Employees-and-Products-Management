package Persistencia;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import Dominio.Empleado;
import Dominio.Producto;
import Dominio.ProductoPerecedero;
import Dominio.ProductoPerecedero;

public class ProductoPerecederoDao {
	public static int contadorPerecedero;

	public ProductoPerecederoDao() {

	}

	public ArrayList<Producto> leerProductos() throws IOException {
		ArrayList<Producto> productos = new ArrayList<Producto>();

		String cadena = null;
		Scanner out = new Scanner(new FileReader("productosperecederos.txt"));
		out.next();
		contadorPerecedero = out.nextInt();
		// Leer productos
		for (int i = 0; i < contadorPerecedero; i++) {
			out.next();
			int codigoProducto = out.nextInt();
			out.next();
			String nombre = out.next();
			out.next();
			double precio = out.nextDouble();
			out.next();
			int unidades = out.nextInt();
			out.next();
			int diasCaducar = out.nextInt();
			// Descuento Productos Perecederos
			if (diasCaducar == 1)
				precio *= 0.25;
			if (diasCaducar == 2)
				precio *= 0.33;
			if (diasCaducar == 3)
				precio *= 0.5;

			Producto p1 = new ProductoPerecedero(codigoProducto, nombre, precio, unidades, diasCaducar);

			productos.add(p1);
		}

		return productos;
	}

	public ArrayList<Producto> insertarProductos(ArrayList<Producto> productos) throws IOException {
		PrintWriter out = new PrintWriter(new FileWriter("productosPerecederos.txt"));
		out.println("Productos perecederos:");
		out.println(productos.size());
		for (int i = 0; i < productos.size(); i++) {
			out.println("codigo:");
			out.println(productos.get(i).getCodigoProducto());
			out.println("nombre:");
			out.println(productos.get(i).getNombre());
			out.println("precio:");
			String precio = productos.get(i).getPrecio() + "";
			precio = precio.replace(".", ",");
			out.println(precio);
			out.println("unidades:");
			out.println(productos.get(i).getUnidades());
			out.println("dias caducar:");
			out.println(((ProductoPerecedero) productos.get(i)).getDiasCaducar());
		}
		out.close();
		return productos;
	}

	public ArrayList<Producto> modificarProductos(ArrayList<Producto> productos) throws IOException {
		PrintWriter out = new PrintWriter(new FileWriter("productosPerecederos.txt"));
		out.println("Productos perecederos:");
		out.println(productos.size());
		for (int i = 0; i < productos.size(); i++) {
			out.println("codigo:");
			out.println(productos.get(i).getCodigoProducto());
			out.println("nombre:");
			out.println(productos.get(i).getNombre());
			out.println("precio:");
			String precio = productos.get(i).getPrecio() + "";
			precio = precio.replace(".", ",");
			out.println(precio);
			out.println("unidades:");
			out.println(productos.get(i).getUnidades());
			out.println("dias caducar:");
			out.println(((ProductoPerecedero) productos.get(i)).getDiasCaducar());
		}
		out.close();
		return productos;
	}

	public ArrayList<Producto> eliminarProductos(ArrayList<Producto> productos) throws IOException {
		PrintWriter out = new PrintWriter(new FileWriter("productosPerecederos.txt"));
		out.println("Productos Perecederos:");
		out.println(productos.size());
		for (int i = 0; i < productos.size(); i++) {
			out.println("codigo:");
			out.println(productos.get(i).getCodigoProducto());
			out.println("nombre:");
			out.println(productos.get(i).getNombre());
			out.println("precio:");
			String precio = productos.get(i).getPrecio() + "";
			precio = precio.replace(".", ",");
			out.println(precio);
			out.println("unidades:");
			out.println(productos.get(i).getUnidades());
			out.println("dias caducar:");
			out.println(((ProductoPerecedero) productos.get(i)).getDiasCaducar());
		}
		out.close();
		return productos;
	}
}
