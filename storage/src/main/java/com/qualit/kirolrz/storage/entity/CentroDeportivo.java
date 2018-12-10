package com.qualit.kirolrz.storage.entity;

import javax.persistence.*;

@Entity
@Table(name = "centro_deportivo")
public class CentroDeportivo extends AuditModel {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", nullable = false, unique = true)
    private String nombre;



    public CentroDeportivo(){}

    public CentroDeportivo(String nombre) {
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
