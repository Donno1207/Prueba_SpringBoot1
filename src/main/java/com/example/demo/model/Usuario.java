package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity 
public class Usuario {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    private String username;
    
    @JsonIgnore //Este lo he añadido para cuando se abra la URL no enseñe la contraseña directamente en texto plano
    private String password;
    private String rango;

    public long getId(){
        return id;
    }

    public void setId(long id){
        this.id = id;
    }

    public String getUsername(){
        return username;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public void setPassword(String password){
       this.password = password;
    }

    public String getPassword(){
        return password;
    }

    public void setRango(String rango){
        this.rango = rango;
    }

    public String getRango(){
        return rango;
    }
}
