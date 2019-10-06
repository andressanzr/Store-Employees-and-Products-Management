package Dominio;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import Persistencia.OfertaDao;

 public class Oferta {
	private String tipo;
	private int idOferta;
	private OfertaDao ofedao = new OfertaDao();
	
	public Oferta( int idOferta,String tipo) {
		this.tipo = tipo;
		this.idOferta = idOferta;
	}

	public Oferta() {

	}

	public int getIdOferta() {
		return idOferta;
	}

	public void setIdOferta(int idOferta) {
		this.idOferta = idOferta;
	}	

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}	
	
	public ArrayList<Oferta> leerOferta() throws IOException {
		return ofedao.leerOfertas();
	}
	public ArrayList<Oferta> insertarOferta(ArrayList<Oferta> ofertas, Oferta oferta) throws IOException {
		ofertas.add(oferta);
		return ofedao.insertarOfertas(ofertas);
	}
	public ArrayList<Oferta> modificarOferta(ArrayList<Oferta> ofertas, Oferta oferta) throws IOException {
		ofertas.add(oferta);
		return ofedao.modificarOfertas(ofertas);
	}
	public ArrayList<Oferta> eliminarOferta(ArrayList<Oferta> ofertas, Oferta oferta) throws IOException {
		ofertas.add(oferta);
		return ofedao.eliminarOfertas(ofertas);
	}
}
