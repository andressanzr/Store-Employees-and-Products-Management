package Dominio;

import java.io.IOException;
import java.util.ArrayList;

import Persistencia.ProductoDao;
import Persistencia.ProductoNoPerecederoDao;

public class ProductoNoPerecedero extends Producto {
	private String oferta;
	static private String textoOfertaNoPere;
	private ProductoNoPerecederoDao productodao = new ProductoNoPerecederoDao();

	public ProductoNoPerecedero(int codigoProducto, String nombre, double precio, int unidades, String oferta) {
		super(codigoProducto, nombre, precio, unidades);
		this.oferta = oferta;
	}

	public ProductoNoPerecedero() {

	}
	public String getTextoOfertaNoPere() {
		return textoOfertaNoPere;
	}
	public String getOferta() {
		return oferta;
	}

	public void setOferta(String oferta) {
		this.oferta = oferta;
	}

	public ArrayList<Producto> leerProducto() throws IOException {
		return productodao.leerProductos();
	}

	public ArrayList<Producto> insertarProductos(ArrayList<Producto> Productos, Producto Producto) throws IOException {
		System.out.println(Productos.size());
		Productos.add(Producto);
		System.out.println(Productos.size());

		return productodao.insertarProductos(Productos);
	}

	public ArrayList<Producto> modificarProductos(ArrayList<Producto> Productos) throws IOException {
		return productodao.modificarProductos(Productos);
	}

	public ArrayList<Producto> eliminarProductos(ArrayList<Producto> Productos) throws IOException {
		return productodao.eliminarProductos(Productos);
	}

	public double calcularOfertasNoPerecedero(ArrayList<Producto> cesta, ArrayList<Producto> productosNoPerecederos) {
		double totalCestaNoPerecederos = 0;

		for (int i = 0; i < cesta.size(); i++) {
			for (int t = 0; t < productosNoPerecederos.size(); t++) {
				if (cesta.get(i).getCodigoProducto() == productosNoPerecederos.get(t).getCodigoProducto()) {
					((ProductoNoPerecedero) cesta.get(i)).getOferta();
					int unidadesPagar = 0;
					switch (((ProductoNoPerecedero) cesta.get(i)).getOferta()) {
					case "2x1"://oferta 2x1
						if (cesta.get(i).getUnidades() % 2 == 0) {
							unidadesPagar = (cesta.get(i).getUnidades() / 2);
						} else {
							unidadesPagar = (cesta.get(i).getUnidades() / 2) + 1;
							System.out.println(cesta.get(i).getUnidades()/2);
						}
						totalCestaNoPerecederos += unidadesPagar * cesta.get(i).getPrecio();
						break;
					case "3x2"://oferta 3x2
						int calculo=cesta.get(i).getUnidades()%3;
						if (calculo == 0 || calculo-1==0 || calculo -2==0) {
							unidadesPagar = (cesta.get(i).getUnidades() - 1*(cesta.get(i).getUnidades()/3));
							totalCestaNoPerecederos += cesta.get(i).getPrecio() * unidadesPagar;
						}else {
							unidadesPagar = (cesta.get(i).getUnidades());
							totalCestaNoPerecederos += cesta.get(i).getPrecio() * unidadesPagar;
						}
						break;
					case "000"://sin oferta
						totalCestaNoPerecederos += cesta.get(i).getPrecio() * cesta.get(i).getUnidades();
					default://oferta porcentaje
						int descuento = Integer.parseInt(((ProductoNoPerecedero) cesta.get(i)).getOferta());
						totalCestaNoPerecederos += cesta.get(i).getPrecio() * descuento/100 * cesta.get(i).getUnidades();
					}
					System.out.println("El producto: " + cesta.get(i).getNombre() + " tiene una oferta de "
							+ ((ProductoNoPerecedero) cesta.get(i)).getOferta());
					textoOfertaNoPere+="El producto: " + cesta.get(i).getNombre() + " tiene una oferta de "
							+ ((ProductoNoPerecedero) cesta.get(i)).getOferta()+"\n";
				}
			}
		} // fin for no perecederos
		return totalCestaNoPerecederos;
	}

}
