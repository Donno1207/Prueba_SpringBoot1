package com.example.Lanzador;

import java.io.File;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.file.Files;

public class IniciarServidor {

    // Cambia esta ruta si mueves el proyecto
    private static final String JAR =
            "D:\\Programación\\Java\\Prueba_SpringBoot1\\target\\demo-0.0.1-SNAPSHOT.jar";
    private static final int PUERTO = 8080;

    public static void main(String[] args) throws Exception {
        if (puertoAbierto()) {
            System.out.println("El servidor ya está encendido.");
            return;
        }

        File jar = new File(JAR);
        if (!jar.exists()) {
            System.out.println("No existe el jar: " + JAR);
            System.out.println("Genéralo primero con el proyecto (package).");
            return;
        }

        ProcessBuilder pb = new ProcessBuilder("java", "-jar", jar.getAbsolutePath());
        pb.directory(jar.getParentFile().getParentFile());
        pb.redirectErrorStream(true);
        pb.redirectOutput(new File("servidor.log"));
        Process proceso = pb.start();

        Files.writeString(new File("servidor.pid").toPath(), String.valueOf(proceso.pid()));
        System.out.println("Arrancando servidor...");

        for (int i = 0; i < 30; i++) {
            Thread.sleep(1000);
            if (puertoAbierto()) {
                System.out.println("Servidor encendido en http://localhost:" + PUERTO);
                return;
            }
        }
        System.out.println("No arrancó a tiempo. Revisa servidor.log");
    }

    private static boolean puertoAbierto() {
        try (Socket s = new Socket()) {
            s.connect(new InetSocketAddress("localhost", PUERTO), 300);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}