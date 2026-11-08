package com.example.demo;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.demo.model.Usuario;
import com.example.demo.repository.UsuarioRepository;

public class cifradoContraseñas {
    private final UsuarioRepository repo;
    private final BCryptPasswordEncoder codificador = new BCryptPasswordEncoder();

    public cifradoContraseñas (UsuarioRepository repo){
        this.repo = repo;
    }

    public Usuario registrar(String username, String password){
        if(repo.existsByUsername(username)){
            throw new IllegalArgumentException("El usuario ya existe");
        }

        Usuario u = new Usuario();
        u.setUsername(username);
        u.setPassword(codificador.encode(password)); //Aquí guardamos el Hash
        u.setRango("USER");
        return repo.save(u);
    }

    public boolean login (String username, String password){
        return repo.findByUsername(username).map(u -> codificador.matches(password, u.getPassword())).orElse(false);
    }
}
