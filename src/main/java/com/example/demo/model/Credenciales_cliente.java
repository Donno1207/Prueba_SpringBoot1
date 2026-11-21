package com.example.demo.model;
import jakarta.persistence.*;

@Entity
@Table(name = "Credenciales_cliente")
public class Credenciales_cliente {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_credencial")
	private long id_credencial;
	
	@ManyToOne
	@JoinColumn(name = "id_cliente")
	private Empleado empleado;
	
	private String usuario;
	
	@Column(name = "contrasena_hash")
	private String contrasenaHash;

	public Credenciales_cliente() {
		super();
	}

	public Credenciales_cliente(long id_credencial, Empleado empleado, String usuario, String contrasenaHash) {
		super();
		this.id_credencial = id_credencial;
		this.empleado = empleado;
		this.usuario = usuario;
		this.contrasenaHash = contrasenaHash;
	}

	public long getId_credencial() {
		return id_credencial;
	}

	public void setId_credencial(long id_credencial) {
		this.id_credencial = id_credencial;
	}

	public Empleado getEmpleado() {
		return empleado;
	}

	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getContrasenaHash() {
		return contrasenaHash;
	}

	public void setContrasenaHash(String contrasenaHash) {
		this.contrasenaHash = contrasenaHash;
	}
	
	
	
}
