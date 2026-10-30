package com.example.demo;

import com.example.demo.model.Empleado;
import com.example.demo.repository.EmpleadoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class ConsolaEmpleado implements CommandLineRunner {

    private final EmpleadoRepository repo;

    public ConsolaEmpleado(EmpleadoRepository repo) {
        this.repo = repo;
    }

    @Override
    public void run(String... args) {
        Scanner sc = new Scanner(System.in);
        int opcion = 0;

        while (opcion != 3) {
            System.out.println("\n=== MENÚ EMPLEADOS ===");
            System.out.println("1. Crear empleado");
            System.out.println("2. Ver empleados");
            System.out.println("3. Salir");
            System.out.print("Elige una opción: ");

            try {
                opcion = Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Opción no válida.");
                continue;
            }

            switch (opcion) {
                case 1 -> crearEmpleado(sc);
                case 2 -> listarEmpleados();
                case 3 -> System.out.println("Hasta luego.");
                default -> System.out.println("Opción no válida.");
            }
        }
    }

    private void crearEmpleado(Scanner sc) {
        Empleado e = new Empleado();

        System.out.print("Nombre: ");
        e.setNombre(sc.nextLine());
        System.out.print("Apellido: ");
        e.setApellido(sc.nextLine());
        System.out.print("Puesto: ");
        e.setPuesto(sc.nextLine());
        System.out.print("Correo: ");
        e.setCorreo(sc.nextLine());
        System.out.print("Teléfono: ");
        try {
            e.setTelefono(Long.parseLong(sc.nextLine().trim()));
        } catch (NumberFormatException ex) {
            System.out.println("Teléfono no válido, se guardará vacío.");
        }

        repo.save(e);
        System.out.println("Empleado guardado con id " + e.getId());
    }

    private void listarEmpleados() {
        var empleados = repo.findAll();
        if (empleados.isEmpty()) {
            System.out.println("No hay empleados.");
            return;
        }
        for (Empleado e : empleados) {
            System.out.println(e.getId() + " | " + e.getNombre() + " " + e.getApellido()
                    + " | " + e.getPuesto() + " | " + e.getCorreo() + " | " + e.getTelefono());
        }
    }
}
