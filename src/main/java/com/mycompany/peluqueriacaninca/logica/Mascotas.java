
package com.mycompany.peluqueriacaninca.logica;

import java.io.Serializable;
import java.util.logging.Logger;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Mascotas implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE) 
    private int num_cliente;
    
    private String nombre_mascota;
    private String raza;
    private String color;
    private String alergico;
    private String atencion_especial;
    private String observaciones;
    
    @ManyToOne()
    @JoinColumn(name = "id_duenio")
    private Duenio duenio;

    public Mascotas() {
    }

    public Mascotas(int num_cliente, String nombre_mascota, String raza, String color, String alergico, String atencion_especial, String observaciones, Duenio duenio) {
        this.num_cliente = num_cliente;
        this.nombre_mascota = nombre_mascota;
        this.raza = raza;
        this.color = color;
        this.alergico = alergico;
        this.atencion_especial = atencion_especial;
        this.observaciones = observaciones;
        this.duenio = duenio;
    }

    public Mascotas(String nombre_mascota, String raza, String color, String alergico, String atencion_especial, String observaciones, Duenio duenio) {
        this.nombre_mascota = nombre_mascota;
        this.raza = raza;
        this.color = color;
        this.alergico = alergico;
        this.atencion_especial = atencion_especial;
        this.observaciones = observaciones;
        this.duenio = duenio;
    }
    
    

    public int getNum_cliente() {
        return num_cliente;
    }

    public void setNum_cliente(int num_cliente) {
        this.num_cliente = num_cliente;
    }

    public String getNombre_mascota() {
        return nombre_mascota;
    }

    public void setNombre_mascota(String nombre_mascota) {
        this.nombre_mascota = nombre_mascota;
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String isAlergico() {
        return alergico;
    }

    public void setAlergico(String alergico) {
        this.alergico = alergico;
    }

    public String isAtencion_especial() {
        return atencion_especial;
    }

    public void setAtencion_especial(String atencion_especial) {
        this.atencion_especial = atencion_especial;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Duenio getDuenio() {
        return duenio;
    }

    public void setDuenio(Duenio duenio) {
        this.duenio = duenio;
    }

    @Override
    public String toString() {
        return "Mascotas{" + "num_cliente=" + num_cliente + ", nombre_mascota=" + nombre_mascota + ", raza=" + raza + ", color=" + color + ", alergico=" + alergico + ", atencion_especial=" + atencion_especial + ", observaciones=" + observaciones + ", duenio=" + duenio + '}';
    }
    
    
    
    
    

}
