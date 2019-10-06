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

abstract public class ProductoDao {
	public static int contador;

	public ProductoDao() {
			
	}
	abstract public ArrayList<Producto> leerProductos();
	abstract public ArrayList<Producto> insertarProductos(ArrayList<Producto> productos);
	abstract public ArrayList<Producto> modificarProductos(ArrayList<Producto> productos);
	abstract public ArrayList<Producto> eliminarProductos(ArrayList<Producto> productos);
	
}
