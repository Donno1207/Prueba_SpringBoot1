package com.example.Lanzador;

import java.io.File;
import java.nio.file.Files;

public class DetenerServidor {

    public static void main(String[] args) throws Exception {
        File archivo = new File("servidor.pid");
        if (!archivo.exists()) {
            System.out.println("No hay servidor registrado.");
            return;
        }
        long pid = Long.parseLong(Files.readString(archivo.toPath()).trim());
        ProcessHandle.of(pid).ifPresentOrElse(p -> {
            p.destroy();
            System.out.println("Servidor detenido.");
        }, () -> System.out.println("El servidor ya estaba apagado."));
        archivo.delete();
    }
}