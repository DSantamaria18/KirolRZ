package com.qualit.kirolrz.storage.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "centro_salud")
public class CentroSalud extends AuditModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;


    @Column(name = "nombre", nullable = false, unique = true)
    private String nombre;

    public CentroSalud(){}

    public CentroSalud(@NotNull String nombre) {
        this.nombre = nombre.toUpperCase();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre.toUpperCase();
    }

    @Override
    public String toString() {
        return "CentroSalud{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}
