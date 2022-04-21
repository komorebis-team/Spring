package com.itesm.komorebi.models;

import java.util.UUID;

public class Personnel {
    private String nombre;
    private int edad;
    private String id;

    public Personnel(){}

    public Personnel(String nombre, int edad, UUID id) {
        this.nombre = nombre;
        this.edad = edad;
        this.id = id.toString();
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }
    public void setEdad(int edad) {
        this.edad = edad;
    }

    public UUID getId() {
        return UUID.fromString(this.id);
    }
    public void setId(UUID id) {
        this.id = id.toString();
    }
}
