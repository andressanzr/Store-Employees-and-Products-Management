package Dominio;

import java.io.IOException;
import java.util.ArrayList;

import Persistencia.ProductoDao;
import Persistencia.ProductoPerecederoDao;

public class ProductoPerecedero extends Producto {
	private int diasCaducar;
	private String textoOfertaPere;
	private ProductoPerecederoDao productodao = new ProductoPerecederoDao();

	public ProductoPerecedero(int codigoProducto, String nombre, double precio, int unidades, int diasCaducar) {
		super(codigoProducto, nombre, precio, unidades);
		this.diasCaducar = diasCaducar;
	}
	public String getTextoOfertaPere() {
		return textoOfertaPere;
	}
	public ProductoPerecedero() {

	}

	public ArrayList<Producto> leerProducto() throws IOException {
		return productodao.leerProductos();
	}

	public ArrayList<Producto> insertarProductos(ArrayList<Producto> Productos, Producto Producto) throws IOException {
		Productos.add(Producto);
		return productodao.insertarProductos(Productos);
	}

	public ArrayList<Producto> modificarProductos(ArrayList<Producto> Productos) throws IOException {
		return productodao.modificarProductos(Productos);
	}

	public ArrayList<Producto> eliminarProductos(ArrayList<Producto> Productos) throws IOException {
		return productodao.eliminarProductos(Productos);
	}

	public int getDiasCaducar() {
		return diasCaducar;
	}

	public void setDiasCaducar(int diasCaducar) {
		this.diasCaducar = diasCaducar;
	}

	public double calcularOfertasPerecedero(ArrayList<Producto> cesta, ArrayList<Producto> productosPerecederos) {
		double totalCestaPerecederos = 0;

		for (int i = 0; i < cesta.size(); i++) {
			for (int p = 0; p < productosPerecederos.size(); p++) {
				if (cesta.get(i).getCodigoProducto() == productosPerecederos.get(p).getCodigoProducto()) {

					((ProductoPerecedero) cesta.get(i)).getDiasCaducar();
					textoOfertaPere+="El producto: " + cesta.get(i).getCodigoProducto()
							+ " tiene una oferta por caducidad proxima ("
							+ ((ProductoPerecedero) cesta.get(i)).getDiasCaducar() + " dias)\n";
					System.out.println("El producto: " + cesta.get(i).getCodigoProducto()
							+ " tiene una oferta por caducidad proxima ("
							+ ((ProductoPerecedero) cesta.get(i)).getDiasCaducar() + " dias)");
					switch (((ProductoPerecedero) cesta.get(i)).getDiasCaducar()) {
					case 1:
						totalCestaPerecederos += cesta.get(i).getPrecio() * 0.25;
						break;
					case 2:
						totalCestaPerecederos += cesta.get(i).getPrecio() * 0.33;
						break;
					case 3:
						totalCestaPerecederos += cesta.get(i).getPrecio() * 0.5;
						break;
					}
					
					
				}
			}
		} // fin for perecedero
		return totalCestaPerecederos;
	}// fin calcularOfertasPerecedero

}
