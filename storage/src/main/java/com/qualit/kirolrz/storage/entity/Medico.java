package com.qualit.kirolrz.storage.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "medico")
public class Medico extends AuditModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nss", nullable = false, unique = true)
    private Long nss;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "apellidos", nullable = false)
    private String apellidos;

    /*
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "centro_salud_id", nullable = false)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private CentroSalud centroSalud;
    */

    public Medico() {
    }

//    public Medico(Long nss, String nombre, String apellidos, CentroSalud centroSalud) {
    public Medico(Long nss, String nombre, String apellidos) {
        this.nss = nss;
        this.nombre = nombre.toUpperCase();
        this.apellidos = apellidos.toUpperCase();
//        this.centroSalud = centroSalud;
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
        return nombre.toUpperCase();
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos.toUpperCase();
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos.toUpperCase();
    }

//    public CentroSalud getCentroSalud() {
//        return centroSalud;
//    }

//    public void setCentroSalud(CentroSalud centroSalud) {
//        this.centroSalud = centroSalud;
//    }

    public String toShortString() {
        return this.nombre.toUpperCase() + ' ' + this.apellidos.toUpperCase();
    }

    @Override
    public String toString() {
        return "Medico{" +
                "id=" + id +
                ", nss=" + nss +
                ", nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
//                ", centroSalud=" + centroSalud +
                '}';
    }
}
