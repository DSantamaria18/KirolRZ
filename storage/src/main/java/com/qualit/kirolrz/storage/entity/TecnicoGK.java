package com.qualit.kirolrz.storage.entity;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name="tecnicogk")
public class TecnicoGK {
    @Id
    @GeneratedValue
    @Column(name="id")
    private Long id;

    @Column(name = "dni")
    @NotNull
    private String dni;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "apellidos")
    private String apellidos;

    @OneToMany(mappedBy = "centro_deportivo", fetch = FetchType.EAGER, targetEntity = CentroDeportivo.class)
    private List<CentroDeportivo> centrosDeportivos;

    public TecnicoGK(){}

    public TecnicoGK(@NotNull String dni, String nombre, String apellidos, List<CentroDeportivo> centrosDeportivos) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.centrosDeportivos = centrosDeportivos;
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

    public List<CentroDeportivo> getCentrosDeportivos() {
        return centrosDeportivos;
    }

    public void setCentrosDeportivos(List<CentroDeportivo> centrosDeportivos) {
        this.centrosDeportivos = centrosDeportivos;
    }

    @Override
    public String toString() {
        return this.nombre.toUpperCase() + ' ' + this.apellidos.toUpperCase();
    }


}
