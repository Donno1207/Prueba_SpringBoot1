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

        while (opcion != 4) {
            System.out.println("1. Crear empleado");
            System.out.println("2. Ver empleados");
            System.out.println("3. Eliminar empleado");
            System.out.println("4. Modificar empleado");
            System.out.println("5. Salir");
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
                case 3 -> eliminarEmpleado(sc);
                case 4 ->  modificarEmpleado(sc);
                case 5 -> System.out.println("Hasta luego.");
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

    private void eliminarEmpleado(Scanner sc) {
        System.out.print("Id del empleado a eliminar: ");
        try {  
            long id = Long.parseLong(sc.nextLine().trim());
            if (repo.existsById(id)) {
                repo.deleteById(id);
                System.out.println("Empleado eliminado.");
            } else {
                System.out.println("Empleado no encontrado.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Id no válido.");
        }
    }

    private void modificarEmpleado(Scanner sc) {
        System.out.print("Id del empleado a modificar: ");
        try {
            long id = Long.parseLong(sc.nextLine().trim());
            var empleadoOpt = repo.findById(id);
            if (empleadoOpt.isPresent()) {
                Empleado e = empleadoOpt.get();
                System.out.print("Nuevo nombre (" + e.getNombre() + "): ");
                String nombre = sc.nextLine();
                if (!nombre.isBlank()) e.setNombre(nombre);

                System.out.print("Nuevo apellido (" + e.getApellido() + "): ");
                String apellido = sc.nextLine();
                if (!apellido.isBlank()) e.setApellido(apellido);

                System.out.print("Nuevo puesto (" + e.getPuesto() + "): ");
                String puesto = sc.nextLine();
                if (!puesto.isBlank()) e.setPuesto(puesto);

                System.out.print("Nuevo correo (" + e.getCorreo() + "): ");
                String correo = sc.nextLine();
                if (!correo.isBlank()) e.setCorreo(correo);

                System.out.print("Nuevo teléfono (" + e.getTelefono() + "): ");
                String telefonoStr = sc.nextLine();
                if (!telefonoStr.isBlank()) {
                    try {
                        long telefono = Long.parseLong(telefonoStr.trim());
                        e.setTelefono(telefono);
                    } catch (NumberFormatException ex) {
                        System.out.println("Teléfono no válido, se mantendrá el anterior.");
                    }
                }

                repo.save(e);
                System.out.println("Empleado modificado.");
            } else {
                System.out.println("Empleado no encontrado.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Id no válido.");
        }
    }
}
