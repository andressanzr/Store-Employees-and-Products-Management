package Persistencia;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import Dominio.Empleado;
import Dominio.EmpleadoNocturno;

public class EmpleadoNocturnoDao extends EmpleadoDao{
	public ArrayList<Empleado> leerEmpleados() throws FileNotFoundException {
		ArrayList <Empleado> empleados = new ArrayList <Empleado>();
		Scanner in = new Scanner (new FileReader("empleadosNocturno.txt"));
		in.next();
		int contador = in.nextInt();
		//Leer empleados
		for (int i=0; i<contador;i++) {
			in.next();
			int codigo = in.nextInt();
			in.next();
			String nombre = in.next();
			in.next();
			String password = in.next();
			in.next();
			int nivel = in.nextInt();
			in.next();
			String turno = in.next();
			in.next();
			double plusproductividad = in.nextDouble();
			//Guardo un empleado
			Empleado emp = new EmpleadoNocturno (codigo,nombre,password,nivel, turno, plusproductividad);
			
			//incluyo en la lista
			empleados.add(emp);
		}
		
		return empleados;
	}
	public ArrayList<Empleado> insertarEmpleados(ArrayList<Empleado> empleados) throws IOException {
		PrintWriter out = new PrintWriter (new FileWriter ("empleadosNocturno.txt"));
		out.println("EmpleadosNocturno:");
		out.println(empleados.size());
		for(int i=0;i<empleados.size();i++) {
			out.println("Codigo:");
			out.println(empleados.get(i).getCodigoAcceso());
			out.println("Nombre:");
			out.println(empleados.get(i).getNombreUsuario());
			out.println("Password:");
			out.println(empleados.get(i).getPassword());
			out.println("Nivel:");
			out.println(empleados.get(i).getNivel());
			out.println("Turno:");
			out.println(empleados.get(i).getTurno());
			out.println("Plusproductividad:");
			String plus=((EmpleadoNocturno)empleados.get(i)).getPlusProductividad()+"";
			plus=plus.replace(".",",");
			out.println(plus);
			
		}
		out.close();
		return empleados;	
		}
	public ArrayList<Empleado> modificarEmpleados(ArrayList<Empleado> empleados) throws IOException {
		PrintWriter out = new PrintWriter (new FileWriter ("empleadosNocturno.txt"));
		out.println("EmpleadosNocturno:");
		out.println(empleados.size());
		for(int i=0;i<empleados.size();i++) {
			out.println("Codigo:");
			out.println(empleados.get(i).getCodigoAcceso());
			out.println("Nombre:");
			out.println(empleados.get(i).getNombreUsuario());
			out.println("Password:");
			out.println(empleados.get(i).getPassword());
			out.println("Nivel:");
			out.println(empleados.get(i).getNivel());
			out.println("Turno:");
			out.println(empleados.get(i).getTurno());
			out.println("Plusproductividad:");
			String plus=((EmpleadoNocturno)empleados.get(i)).getPlusProductividad()+"";
			plus=plus.replace(".",",");
			out.println(plus);
			
		}
		out.close();
		return empleados;	
		}
	public ArrayList<Empleado> eliminarEmpleados(ArrayList<Empleado> empleados) throws IOException {
		PrintWriter out = new PrintWriter (new FileWriter ("empleadosNocturno.txt"));
		out.println("EmpleadosNocturno:");
		out.println(empleados.size());
		for(int i=0;i<empleados.size();i++) {
			out.println("Codigo:");
			out.println(empleados.get(i).getCodigoAcceso());
			out.println("Nombre:");
			out.println(empleados.get(i).getNombreUsuario());
			out.println("Password:");
			out.println(empleados.get(i).getPassword());
			out.println("Nivel:");
			out.println(empleados.get(i).getNivel());
			out.println("Turno:");
			out.println(empleados.get(i).getTurno());
			out.println("retencion:");
			String plus=((EmpleadoNocturno)empleados.get(i)).getPlusProductividad()+"";
			plus=plus.replace(".",",");
			out.println(plus);
		}
		out.close();
		return empleados;	
		}


}
