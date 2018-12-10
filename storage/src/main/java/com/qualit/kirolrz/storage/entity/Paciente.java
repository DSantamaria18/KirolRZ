package com.qualit.kirolrz.storage.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@Entity
@Table(name = "paciente")
public class Paciente extends AuditModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "apellidos")
    private String apellidos;

    @Column(name = "dni", unique = true, nullable = false)
    private String dni;

    @Column(name = "email")
    private String email;

    @Column(name = "fecha_nacimiento")
    private Date fnacimiento;

    @Column(name = "genero")
    @Enumerated(EnumType.STRING)
    private Genero genero;

    public Paciente(){}

    public Paciente(String nombre, String apellidos, String dni, String email, Date fnacimiento, Genero genero) {
        this.nombre = nombre.toUpperCase();
        this.apellidos = apellidos.toUpperCase();
        this.dni = dni.toUpperCase();
        this.email = email.toLowerCase();
        this.fnacimiento = fnacimiento;
        this.genero = genero;
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

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos.toUpperCase();
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni.toUpperCase();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email.toLowerCase();
    }

    public Date getFnacimiento() {
        return fnacimiento;
    }

    public void setFnacimiento(Date fnacimiento) {
        this.fnacimiento = fnacimiento;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    @Override
    public String toString() {
        return this.nombre.toUpperCase() + ' ' + this.apellidos.toUpperCase();
    }
}
