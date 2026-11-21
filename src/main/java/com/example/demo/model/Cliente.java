package com.example.demo.model;
import java.time.LocalDateTime;

import com.example.demo.enums.EstadoCliente;

import jakarta.persistence.*;

@Entity
@Table(name = "Cliente")
public class Cliente {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_cliente")
	private long id_cliente;
	
	private String nombre;
	private String apellidos;
	private String correo;
	private String telefono;
	private String pais;
	private String ciudad;
	
	@Column(name = "codigo_postal")
	private String codigoPostal;
	
	private String direccion;
	
	//Insertable -> JPA no incluye la columna al crear el cliente
	//Updatable -> JPA no modifica la columna al actualizar
	@Column(name = "fecha_creacion", insertable = false, updatable = false)
	private LocalDateTime fecha_creacion;
	
	@Enumerated(EnumType.STRING) //Gurdamos el nombre del valor como texto
	@Column(name = "estado", nullable = false)
	private EstadoCliente estado = EstadoCliente.ACTIVO; //al crear un cliente, su estado es activo

	public Cliente() {
		super();
	}

	public Cliente(long id_cliente, String nombre, String apellidos, String correo, String telefono, String pais,
			String ciudad, String codigoPostal, String direccion, LocalDateTime fecha_creacion, EstadoCliente estado) {
		super();
		this.id_cliente = id_cliente;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.correo = correo;
		this.telefono = telefono;
		this.pais = pais;
		this.ciudad = ciudad;
		this.codigoPostal = codigoPostal;
		this.direccion = direccion;
		this.fecha_creacion = fecha_creacion;
		this.estado = estado;
	}

	public long getId_cliente() {
		return id_cliente;
	}

	public void setId_cliente(long id_cliente) {
		this.id_cliente = id_cliente;
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

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public String getCodigoPostal() {
		return codigoPostal;
	}

	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public LocalDateTime getFecha_creacion() {
		return fecha_creacion;
	}

	public void setFecha_creacion(LocalDateTime fecha_creacion) {
		this.fecha_creacion = fecha_creacion;
	}

	public EstadoCliente getEstado() {
		return estado;
	}

	public void setEstado(EstadoCliente estado) {
		this.estado = estado;
	}
	
	
}
