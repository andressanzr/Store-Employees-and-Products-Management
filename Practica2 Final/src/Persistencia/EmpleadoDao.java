package Persistencia;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import Dominio.Empleado;

abstract public class EmpleadoDao {

	public EmpleadoDao() {
		
	}
	abstract ArrayList<Empleado> leerEmpleados() throws FileNotFoundException;
	abstract ArrayList<Empleado> insertarEmpleados(ArrayList<Empleado> empleados) throws FileNotFoundException, IOException;
	abstract ArrayList<Empleado> modificarEmpleados(ArrayList<Empleado> empleados) throws FileNotFoundException, IOException;
	abstract ArrayList<Empleado> eliminarEmpleados(ArrayList<Empleado> empleados) throws FileNotFoundException,IOException;

	
}
