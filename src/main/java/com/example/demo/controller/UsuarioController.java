package com.example.demo.controller;

import com.example.demo.model.Usuario;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.repository.UsuarioRepository;

@RestController 
@RequestMapping("/Usuarios")
public class UsuarioController {
    private final UsuarioRepository repo;

    public UsuarioController(UsuarioRepository repo){
        this.repo = repo;
    }

    @GetMapping
    public List <Usuario> listar(){
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public Usuario buscarPorId(@PathVariable Long id){
        return repo.findById(id).orElse(null);
    }

   @GetMapping("/nombre/{username}")
public Usuario buscarPorUsername(@PathVariable String username) {
    return repo.findByUsername(username).orElse(null);
}


}
