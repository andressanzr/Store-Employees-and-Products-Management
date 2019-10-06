package Persistencia;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import Dominio.Oferta;

public class OfertaDao{
	public ArrayList<Oferta> leerOfertas() throws FileNotFoundException {
		ArrayList <Oferta> ofertas = new ArrayList <Oferta>();
		Scanner out = new Scanner (new FileReader("ofertas.txt"));
		out.next();
		int contador = out.nextInt();
		//Leer ofertas
		for (int i=0; i<contador;i++) {
			out.next();
			int idOferta = out.nextInt();
			out.next();
			String tipo =out.next();
			
			//Guardo un oferta
			Oferta ofe = new Oferta(idOferta, tipo);
			
			//incluyo en la lista
			ofertas.add(ofe);
		}
		
		return ofertas;
	}
	
	public ArrayList<Oferta> insertarOfertas(ArrayList<Oferta> ofertas) throws IOException {
		PrintWriter out = new PrintWriter (new FileWriter ("ofertas.txt"));
		out.println("Ofertas:");
		out.println(ofertas.size());
		for(int i=0;i<ofertas.size();i++) {
			out.println("idOferta:");
			out.println(ofertas.get(i).getIdOferta());
			out.println("Tipo:");
			out.println(ofertas.get(i).getTipo());			
		}
		out.close();
		return ofertas;	
		}
	public ArrayList<Oferta> eliminarOfertas(ArrayList<Oferta> ofertas) throws IOException {
		PrintWriter out = new PrintWriter (new FileWriter ("ofertas.txt"));
		out.println("Ofertas:");
		out.println(ofertas.size());
		for(int i=0;i<ofertas.size();i++) {
			out.println("idOferta:");
			out.println(ofertas.get(i).getIdOferta());
			out.println("Tipo:");
			out.println(ofertas.get(i).getTipo());			
		}
		out.close();
		return ofertas;	
		}
	public ArrayList<Oferta> modificarOfertas(ArrayList<Oferta> ofertas) throws IOException {
		PrintWriter out = new PrintWriter (new FileWriter ("ofertas.txt"));
		out.println("Ofertas:");
		out.println(ofertas.size());
		for(int i=0;i<ofertas.size();i++) {
			out.println("idOferta:");
			out.println(ofertas.get(i).getIdOferta());
			out.println("Tipo:");
			out.println(ofertas.get(i).getTipo());			
		}
		out.close();
		return ofertas;	
		}
}
