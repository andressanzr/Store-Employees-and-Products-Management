package Dominio;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import Persistencia.EmpleadoDiurnoDao;

public class EmpleadoDiurno extends Empleado {
	private double retencion;
	private EmpleadoDiurnoDao empdao = new EmpleadoDiurnoDao();

	public EmpleadoDiurno(int codigoAcceso, String nombreUsuario, String password, int nivel, String turno,
			double retencion) {
		super(codigoAcceso, nombreUsuario, password, nivel, turno);
		this.retencion = retencion;
	}

	public EmpleadoDiurno() {

	}

	public double getRetencion() {
		return retencion;
	}

	public void setRetencion(double retencion) {
		this.retencion = retencion;
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

	public double calcularProductividadDiurno(EmpleadoDiurno empleado, double totalCesta) {
		double total = 0;
		//calcular retencion
		if(empleado.getNivel()==1) {
			//sin retencion
			if(totalCesta>=Bonificaciones.minDobleBonusEmpN1) {
				total+=empleado.calcularBonusEmpleado(empleado)*2;
			}
		}else if(empleado.getNivel()==2 && totalCesta>=minEliminarRetencionEmpN2){
			total+=empleado.calcularBonusEmpleado(empleado);
		}else {
			total += (empleado.getRetencion()/100)*empleado.calcularBonusEmpleado(empleado);
		}
		return total;
	}

}
