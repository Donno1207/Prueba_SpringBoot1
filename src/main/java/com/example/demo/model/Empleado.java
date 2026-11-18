package com.example.demo.model;

import org.hibernate.annotations.OnDelete;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity //Con esto indicamos que representa una tabla en la base de datos. Cada objeto empleado representa una fila
public class Empleado {
    
    @Id //Marcamos este valor como la PK, lo que identifica a cada empleado
    @GeneratedValue(strategy=GenerationType.IDENTITY) //El id lo génera la base de datos auto
    private Long id; //Se pone el long con l mayuscula para que cada vez que se introduzca un empelado se identifique como nuevo

    //Atributos del empleado, son columnas de la tabla
    private String nombre;
    private String apellido1;
    private String apellido2;
    private String dni;
    private String correo;
    private String numeroTelefono;
    private double salario;
    private String puesto;

    @OneToOne(cascade = CascadeType.ALL) //Un empleado tiene un usuario. Todo lo que le pasa al objeto empleado le pasa al objeto usuario del mismo empleado
    @JoinColumn(name = "usuario_id") //Crear una tabla usuario que usa de clave foránea "usuario_id" que apunta al id del usuario
    private Usuario usuario; //El empleado guardado como un objeto empleado

    public Empleado() { //Constructor vacío porque se crea el objeto vacío al leer del BBDD y luego se rellena
    }

    //Constructor
    public Empleado(Long id, String nombre, String apellido1, String apellido2, String dni, String correo,
            String numeroTelefono, double salario, String puesto) {
        this.id = id;
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.dni = dni;
        this.correo = correo;
        this.numeroTelefono = numeroTelefono;
        this.salario = salario;
        this.puesto = puesto;
    }

   
   
    }

    
}
