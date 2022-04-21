package com.itesm.komorebi.models;

import java.util.List;
import java.util.UUID;

public class Agent {

    private String nombre;
    private int edad;
    private String id;

    public Agent(){}

    public Agent(String nombre, int edad, UUID id){
        this.nombre = nombre;
        this.edad = edad;
        this.id = id.toString();
    }

    public int getEdad() {
        return edad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public UUID getId(){
        return UUID.fromString(this.id);
    }
}
