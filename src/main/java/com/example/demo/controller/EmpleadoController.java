package com.example.demo.controller;

import com.example.demo.model.Empleado;
import com.example.demo.repository.EmpleadoRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;


//Sirve para las solicitudes web
@RestController
@RequestMapping("/empleados")
public class EmpleadoController {

    private final EmpleadoRepository repo;

    public EmpleadoController(EmpleadoRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<Empleado> listar() {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public Empleado buscarPorId(@PathVariable Long id) {
        return repo.findById(id).orElse(null);
    }

    @GetMapping("/puesto/{puesto}")
    public List<Empleado> buscarPorPuesto(@PathVariable String puesto) {
        return repo.findByPuesto(puesto);
    }

    @PostMapping
    public Empleado crear(@RequestBody Empleado empleado) {
        return repo.save(empleado);
    }

    @PutMapping("/{id}")
    public Empleado actualizar(@PathVariable Long id, @RequestBody Empleado empleado) {
        empleado.setId(id);
        return repo.save(empleado);
    }

    @DeleteMapping("/{id}")
    public void borrar(@PathVariable Long id) {
        repo.deleteById(id);
    }
}
