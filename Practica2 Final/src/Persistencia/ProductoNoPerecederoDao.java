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
import Dominio.ProductoNoPerecedero;
import Dominio.ProductoNoPerecedero;

public class ProductoNoPerecederoDao {
	public static int contadorNoPerecedero;

	public ProductoNoPerecederoDao() {
			
	}
	public ArrayList<Producto> leerProductos() throws IOException {
		ArrayList<Producto> productos=new ArrayList<Producto>();
		
		String cadena=null;
		Scanner out=new Scanner (new FileReader("productosnoperecederos.txt"));
		out.next();
		contadorNoPerecedero= out.nextInt();
		//Leer productos
		for (int i=0;i<contadorNoPerecedero;i++) {
			out.next();
			int codigoProducto=out.nextInt();
			out.next();
			String nombre=out.next();
			out.next();
			double precio=out.nextDouble();
			out.next();
			int unidades=out.nextInt();
			out.next();
			String oferta=out.next();
			Producto p1=new ProductoNoPerecedero(codigoProducto, nombre, precio,unidades,oferta);
			
			productos.add(p1);
		}
		
		
		
		return productos; 
	}
	public ArrayList<Producto> insertarProductos(ArrayList<Producto> productos) throws IOException {
		PrintWriter out=new PrintWriter(new FileWriter("productosnoPerecederos.txt"));
		out.println("productosnoperecederos:");
		out.println(productos.size());
		for(int i=0;i<productos.size();i++) {
			out.println("codigo:");
			out.println(productos.get(i).getCodigoProducto());
			out.println("nombre:");
			out.println(productos.get(i).getNombre());
			out.println("precio:");
			String precio=productos.get(i).getPrecio()+"";
			precio=precio.replace(".", ",");
			out.println(precio);
			out.println("unidades:");
			out.println(productos.get(i).getUnidades());
			out.println("oferta:");
			out.println(((ProductoNoPerecedero)productos.get(i)).getOferta());
		}
		out.close();
		return productos;
			}
	public ArrayList<Producto> modificarProductos(ArrayList<Producto> productos) throws IOException {
		PrintWriter out=new PrintWriter(new FileWriter("productosnoPerecederos.txt"));
		out.println("productosnoperecederos:");
		out.println(productos.size());
		for(int i=0;i<productos.size();i++) {
			out.println("codigo:");
			out.println(productos.get(i).getCodigoProducto());
			out.println("nombre:");
			out.println(productos.get(i).getNombre());
			out.println("precio:");
			String precio=productos.get(i).getPrecio()+"";
			precio=precio.replace(".", ",");
			out.println(precio);
			out.println("unidades:");
			out.println(productos.get(i).getUnidades());
			out.println("oferta:");
			out.println(((ProductoNoPerecedero)productos.get(i)).getOferta());
		}
		out.close();
		return productos;
			}
	public ArrayList<Producto> eliminarProductos(ArrayList<Producto> productos) throws IOException {
		PrintWriter out=new PrintWriter(new FileWriter("productosnoPerecederos.txt"));
		out.println("productosnoperecederos:");
		out.println(productos.size());
		for(int i=0;i<productos.size();i++) {
			out.println("codigo:");
			out.println(productos.get(i).getCodigoProducto());
			out.println("nombre:");
			out.println(productos.get(i).getNombre());
			out.println("precio:");
			String precio=productos.get(i).getPrecio()+"";
			precio=precio.replace(".", ",");
			out.println(precio);
			out.println("unidades:");
			out.println(productos.get(i).getUnidades());
			out.println("oferta:");
			out.println(((ProductoNoPerecedero)productos.get(i)).getOferta());
		}
		out.close();
		return productos;
			}
}
