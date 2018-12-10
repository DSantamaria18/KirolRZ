package com.qualit.kirolrz.storage.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
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
}
