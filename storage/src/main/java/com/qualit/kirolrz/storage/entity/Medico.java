package com.qualit.kirolrz.storage.entity;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(name = "medico")
public class Medico extends AuditModel {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "nss", nullable = false, unique = true)
    private Long nss;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "apellidos", nullable = false)
    private String apellidos;

    //    @OneToMany(mappedBy = "centro_salud", fetch = FetchType.EAGER, targetEntity = CentroSalud.class)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "centro_salud_id", nullable = false)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private CentroSalud centroSalud;
//    private List<CentroSalud> centrosSalud;

    public Medico() {
    }

//    public Medico(Long nss, String nombre, String apellidos, List<CentroSalud> centrosSalud) {
    public Medico(Long nss, String nombre, String apellidos, CentroSalud centroSalud) {
        this.nss = nss;
        this.nombre = nombre;
        this.apellidos = apellidos;
//        this.centrosSalud = centrosSalud;
        this.centroSalud = centroSalud;
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

   /* public List<CentroSalud> getCentrosSalud() {
        return centrosSalud;
    }

    public void setCentrosSalud(List<CentroSalud> centrosSalud) {
        this.centrosSalud = centrosSalud;
    }*/

    public CentroSalud getCentroSalud() {
        return centroSalud;
    }

    public void setCentroSalud(CentroSalud centroSalud) {
        this.centroSalud = centroSalud;
    }

    @Override
    public String toString() {
        return this.nombre.toUpperCase() + ' ' + this.apellidos.toUpperCase();
    }
}
