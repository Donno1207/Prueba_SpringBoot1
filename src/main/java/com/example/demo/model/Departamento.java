package com.example.demo.model;
import jakarta.persistence.*;


@Entity //Esto le dice al JPA que esta clase representa una tabla en la base de datos. Cada objeto Departamento sera un objeto
@Table(name = "Departamento") //Utiliza el nombre de la tabla, es algo opcional porque Spring usa el nombre del objeto
public class Departamento {
	@Id //Marcamos que esta variable va a ser nuestro identificador de cada linea
	@GeneratedValue(strategy = GenerationType.IDENTITY) //El valor lo genera la base de datos
	@Column(name = "id_departamento") //Con @Column le indicamos que nuestro id se llama de otra forma
	private long id_departamento;	//importante que el nombre de las variables tiene que ser igual nombre de las columnas de la tabla de Mysql
	
	//Sin ninguna anotación JPA lo convierte en una columna
	private String nombre;

	public Departamento() {
		super();
	}

	public Departamento(long id_departamento, String nombre) {
		super();
		this.id_departamento = id_departamento;
		this.nombre = nombre;
	}

	public long getId_departamento() {
		return id_departamento;
	}

	public void setId_departamento(long id_departamento) {
		this.id_departamento = id_departamento;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	

}
