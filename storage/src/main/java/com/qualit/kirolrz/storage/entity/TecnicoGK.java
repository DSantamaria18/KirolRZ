package com.qualit.kirolrz.storage.entity;


import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name="tecnicogk")
public class TecnicoGK extends AuditModel {
    @Id
    @GeneratedValue
    @Column(name="id")
    private Long id;

    @Column(name = "dni", nullable = false, unique = true)
    private String dni;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "apellidos", nullable = false)
    private String apellidos;

//    @OneToMany(mappedBy = "centro_deportivo", fetch = FetchType.EAGER, targetEntity = CentroDeportivo.class)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "centro_deportivo_id", nullable = false)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private CentroDeportivo centroDeportivo;

    public TecnicoGK(){}

//    public TecnicoGK(String dni, String nombre, String apellidos, List<CentroDeportivo> centrosDeportivos) {
    public TecnicoGK(String dni, String nombre, String apellidos, CentroDeportivo centrosDeportivo) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellidos = apellidos;
//        this.centrosDeportivos = centrosDeportivos;
        this.centroDeportivo = centroDeportivo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
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

    /*public List<CentroDeportivo> getCentrosDeportivos() {
        return centrosDeportivos;
    }

    public void setCentrosDeportivos(List<CentroDeportivo> centrosDeportivos) {
        this.centrosDeportivos = centrosDeportivos;
    }*/

    public CentroDeportivo getCentroDeportivo() {
        return centroDeportivo;
    }

    public void setCentroDeportivo(CentroDeportivo centroDeportivo) {
        this.centroDeportivo = centroDeportivo;
    }

    @Override
    public String toString() {
        return this.nombre.toUpperCase() + ' ' + this.apellidos.toUpperCase();
    }


}
