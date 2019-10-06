package Dominio;

import java.io.IOException;
import java.util.ArrayList;

import Persistencia.ProductoDao;


abstract public class Producto {
	private int codigoProducto;
	private String nombre;
	private double precio;
	private int unidades;
	
	public Producto(int codigoProducto, String nombre, double precio, int unidades) {
		this.codigoProducto = codigoProducto;
		this.nombre = nombre;
		this.precio = precio;
		this.unidades = unidades;
	}
	public Producto() {
		
	}
	public int getCodigoProducto() {
		return codigoProducto;
	}
	public void setCodigoProducto(int codigoProducto) {
		this.codigoProducto = codigoProducto;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	
	

	public int getUnidades() {
		return unidades;
	}
	public void setUnidades(int unidades) {
		this.unidades = unidades;
	}
	
	abstract public ArrayList<Producto> leerProducto() throws IOException;
	abstract public ArrayList<Producto> insertarProductos(ArrayList<Producto> Productos, Producto Producto) throws IOException;
	abstract public ArrayList<Producto> modificarProductos(ArrayList<Producto> Productos) throws IOException;
	abstract public ArrayList<Producto> eliminarProductos(ArrayList<Producto> Productos) throws IOException;
	
}
