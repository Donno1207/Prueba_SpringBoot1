package com.example.demo.repository;

import com.example.demo.model.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

//Acceso en mysql

public interface EmpleadoRepository extends JpaRepository<Empleado, Long> {

    List<Empleado> findByNombre(String nombre);
    List<Empleado> findByApellido(String apellido);
    List<Empleado> findByPuesto(String puesto);
    List<Empleado> findByCorreo(String correo);
}
