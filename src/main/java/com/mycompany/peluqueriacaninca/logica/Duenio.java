
package com.mycompany.peluqueriacaninca.logica;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Duenio implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    private int id_duenio;
    
    private String nombre;
    private String celular;
    private String direccion;
    
    @OneToMany(mappedBy = "duenio")
    private List<Mascotas> masco;

    public Duenio(int id_duenio, String nombre, String celular, String direccion, List<Mascotas> masco) {
        this.id_duenio = id_duenio;
        this.nombre = nombre;
        this.celular = celular;
        this.direccion = direccion;
        this.masco = masco;
    }

    public Duenio(String nombre, String celular) {
        this.nombre = nombre;
        this.celular = celular;
    }
    
    

    public Duenio() {
    }

    public int getId_duenio() {
        return id_duenio;
    }

    public void setId_duenio(int id_duenio) {
        this.id_duenio = id_duenio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public List<Mascotas> getMasco() {
        return masco;
    }

    public void setMasco(List<Mascotas> masco) {
        this.masco = masco;
    }
    
    
    
}
