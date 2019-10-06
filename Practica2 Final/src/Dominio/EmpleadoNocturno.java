package Dominio;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import Persistencia.EmpleadoNocturnoDao;

public class EmpleadoNocturno extends Empleado {

	private double plusProductividad;
	private EmpleadoNocturnoDao empdao = new EmpleadoNocturnoDao();

	public EmpleadoNocturno(int codigoAcceso, String nombreUsuario, String password, int nivel, String turno,
			double plusProductividad) {
		super(codigoAcceso, nombreUsuario, password, nivel, turno);
		this.plusProductividad = plusProductividad;
	}

	public EmpleadoNocturno() {

	}

	public double getPlusProductividad() {
		return plusProductividad;
	}

	public void setPlusProductividad(double plusProductividad) {
		this.plusProductividad = plusProductividad;
	}

	public ArrayList<Empleado> leerEmpleados() throws FileNotFoundException {
		return empdao.leerEmpleados();

	}

	public ArrayList<Empleado> insertarEmpleados(ArrayList<Empleado> empleados, Empleado empleado) throws IOException {
		empleados.add(empleado);
		return empdao.insertarEmpleados(empleados);

	}

	public ArrayList<Empleado> modificarEmpleados(ArrayList<Empleado> empleados) throws IOException {
		return empdao.modificarEmpleados(empleados);
	}

	public ArrayList<Empleado> eliminarEmpleados(ArrayList<Empleado> empleados) throws IOException {
		return empdao.eliminarEmpleados(empleados);
	}

	public double calcularProductividadNocturno(Empleado empleado, double totalCesta) {
		double total = 0;

		if (empleado.getNivel()==1 && totalCesta>=Bonificaciones.minDobleBonusEmpN1) {
			total += empleado.calcularBonusEmpleado(empleado) * 2;
		} else {
			total += empleado.calcularBonusEmpleado(empleado);
		}
		total += ((EmpleadoNocturno) empleado).getPlusProductividad() * totalCesta;
		
		return 0;
	}

}
