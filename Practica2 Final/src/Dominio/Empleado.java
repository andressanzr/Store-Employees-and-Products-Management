package Dominio;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import Persistencia.EmpleadoDao;

abstract public class Empleado implements Bonificaciones{
	private int codigoAcceso;
	private String nombreUsuario;
	private String password;
	private int nivel;
	private String turno;

	public Empleado(int codigoAcceso, String nombreUsuario, String password, int nivel, String turno) {
		this.codigoAcceso = codigoAcceso;
		this.nombreUsuario = nombreUsuario;
		this.password = password;
		this.nivel = nivel;
		this.turno = turno;
	}

	public Empleado() {

	}

	public int getCodigoAcceso() {
		return codigoAcceso;
	}

	public void setCodigoAcceso(int codigoAcceso) {
		this.codigoAcceso = codigoAcceso;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getNivel() {
		return nivel;
	}

	public void setNivel(int nivel) {
		this.nivel = nivel;
	}

	public String getTurno() {
		return turno;
	}

	public void setTurno(String turno) {
		this.turno = turno;
	}
	public double calcularBonusEmpleado(Empleado empleado) {
		switch (empleado.getNivel()) {
		case 1:
			return Bonificaciones.bonusPedidoEmpN1;
		case 2:
			return Bonificaciones.bonusPedidoEmpN2;
		case 3:
			return Bonificaciones.bonusPedidoEmpN3;
		}
		return 0;
	}
	abstract public ArrayList<Empleado> leerEmpleados() throws FileNotFoundException;

	abstract public ArrayList<Empleado> insertarEmpleados(ArrayList<Empleado> empleados, Empleado empleado)
			throws IOException;

	abstract public ArrayList<Empleado> modificarEmpleados(ArrayList<Empleado> empleados)
			throws IOException;

	abstract public ArrayList<Empleado> eliminarEmpleados(ArrayList<Empleado> empleados)
			throws IOException;
	
}
