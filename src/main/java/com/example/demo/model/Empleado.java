package com.example.demo.model;
import java.sql.Date;

import com.example.demo.enums.EstadoCliente;
import com.example.demo.enums.RolEmpleado;

import jakarta.persistence.*;

@Entity
@Table(name = "Empleado")
public class Empleado{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_empleado")
	private long id_empleado;
	
	private String nombre;
	
	private String apellidos;
	@Column(name = "correo_electronico")
	
	private String correo;
	private String telefono;
	private Date fecha_nacimiento;
	
	@ManyToOne //Muchos empleados pueden pertenecer a un solo departamento
	@JoinColumn(name = "id_departameno") //Es el nombre de la columna de clave foránea en la tabla Departamento
	private Departamento departamento; //Toma el objeto departamento, toma su id y lo pone en la columna
	
	@Enumerated(EnumType.STRING)
	@Column(name = "rol", nullable = false)
	private RolEmpleado rol = RolEmpleado.EMPLEADO;

	private Date fecha_ingreso;
	private double salario;
	private double comision;
	
	public Empleado() {
		super();
	}

	public Empleado(long id_empleado, String nombre, String apellidos, String correo, String telefono,
			Date fecha_nacimiento, Departamento departamento, RolEmpleado rol, Date fecha_ingreso,
			double salario, double comision) {
		super();
		this.id_empleado = id_empleado;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.correo = correo;
		this.telefono = telefono;
		this.fecha_nacimiento = fecha_nacimiento;
		this.departamento = departamento;
		this.rol = rol;
		this.fecha_ingreso = fecha_ingreso;
		this.salario = salario;
		this.comision = comision;
	}

	public long getId_empleado() {
		return id_empleado;
	}

	public void setId_empleado(long id_empleado) {
		this.id_empleado = id_empleado;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public Date getFecha_nacimiento() {
		return fecha_nacimiento;
	}

	public void setFecha_nacimiento(Date fecha_nacimiento) {
		this.fecha_nacimiento = fecha_nacimiento;
	}

	public Departamento getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}

	public RolEmpleado getRol() {
		return rol;
	}

	public void setRol(RolEmpleado rol) {
		this.rol = rol;
	}

	public Date getFecha_ingreso() {
		return fecha_ingreso;
	}

	public void setFecha_ingreso(Date fecha_ingreso) {
		this.fecha_ingreso = fecha_ingreso;
	}

	public double getSalario() {
		return salario;
	}

	public void setSalario(double salario) {
		this.salario = salario;
	}

	public double getComision() {
		return comision;
	}

	public void setComision(double comision) {
		this.comision = comision;
	}
	
	
	
	
}

