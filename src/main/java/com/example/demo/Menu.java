package com.example.demo;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

/*
¿Cómo funciona el menu?
Nuestro menu no hace una conexión directa con Spring o con MySql, sino que hace peticiones http a nuestro servidor de Spring, y el servidor de Spring es el que se encarga de 
"contactar" con la base de datos.
*/

public class Menu {

    //1.Declaramos la dirección de el http para hacer las peticiones
    //2.Declaramos lo que se encarga de enviar las peticiones http y de recibirlas
    private static final String URL = "http://localhost:8080/empleados";
    private static final HttpClient cliente = HttpClient.newHttpClient();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Usario: ");
        String usuario = sc.next();
        System.out.println("Contraseña: ");
        


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

            try {
                switch (opcion) {
                    case 1 -> crear(sc);
                    case 2 -> listar();
                    case 3 -> System.out.println("Hasta luego.");
                    default -> System.out.println("Opción no válida.");
                }
            } catch (Exception e) {
                System.out.println("No se pudo conectar con el servidor. ¿Está encendido?");
            }
        }
    }

    private static String limpiar(String texto) {
        return texto.replace("\\", "\\\\").replace("\"", "\\\"");
    }

    private static void crear(Scanner sc) throws Exception {
        System.out.print("Nombre: ");   String nombre = limpiar(sc.nextLine());
        System.out.print("Apellido: "); String apellido = limpiar(sc.nextLine());
        System.out.print("Puesto: ");   String puesto = limpiar(sc.nextLine());
        System.out.print("Correo: ");   String correo = limpiar(sc.nextLine());
        System.out.print("Teléfono: "); String telefono = sc.nextLine().trim();

        if (!telefono.matches("\\d*")) {
            System.out.println("Teléfono no válido, se guardará vacío.");
            telefono = "";
        }

        String json = "{\"nombre\":\"" + nombre + "\","
                + "\"apellido\":\"" + apellido + "\","
                + "\"puesto\":\"" + puesto + "\","
                + "\"correo\":\"" + correo + "\","
                + "\"telefono\":" + (telefono.isEmpty() ? "null" : telefono) + "}";

        HttpRequest req = HttpRequest.newBuilder(URI.create(URL))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .build();

        HttpResponse<String> resp = cliente.send(req, HttpResponse.BodyHandlers.ofString());
        System.out.println(resp.statusCode() == 200 ? "Empleado guardado." : "Error: " + resp.body());
    }

    private static void listar() throws Exception {
        HttpRequest req = HttpRequest.newBuilder(URI.create(URL)).GET().build();
        HttpResponse<String> resp = cliente.send(req, HttpResponse.BodyHandlers.ofString());
        System.out.println(resp.body());
    }
}

