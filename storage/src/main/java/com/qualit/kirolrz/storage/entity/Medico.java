package com.qualit.kirolrz.storage.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "medico")
public class Medico {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "nss")
    private Long nss;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "apellidos")
    private String apellidos;

    @OneToMany(mappedBy = "centro_salud", fetch = FetchType.EAGER, targetEntity = CentroSalud.class)
    private List<CentroSalud> centrosSalud;

    public Medico(){}

    public Medico(Long nss, String nombre, String apellidos, List<CentroSalud> centrosSalud) {
        this.nss = nss;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.centrosSalud = centrosSalud;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getNss() {
        return nss;
    }

    public void setNss(Long nss) {
        this.nss = nss;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public List<CentroSalud> getCentrosSalud() {
        return centrosSalud;
    }

    public void setCentrosSalud(List<CentroSalud> centrosSalud) {
        this.centrosSalud = centrosSalud;
    }

    @Override
    public String toString() {
        return this.nombre.toUpperCase() + ' ' + this.apellidos.toUpperCase();
    }
}
