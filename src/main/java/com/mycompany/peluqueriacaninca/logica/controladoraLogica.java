
package com.mycompany.peluqueriacaninca.logica;

import com.mycompany.peluqueriacaninca.persistencia.controladora;
import java.util.LinkedList;
import java.util.List;



public class controladoraLogica {
    controladora control = new controladora();
    
     public void crearMascotas(Mascotas mas){
        
        control.crearMascotas(mas);
    }
    
    public void eliminarMascotas(int id){
        control.eliminarMascotas(id);
    }
    
    public void editarMascotas(Mascotas x){
        control.editarMascotas(x);
    }
    
    public Mascotas traerMascotas(int id){
        
        return control.traerMascotas(id);
        
    }
    
    public LinkedList<Mascotas> ListaMascotas(){
      
        return control.ListaMascotas();
        
    }
    
    //****************duenio************
    
      public void crearDuenio(Duenio due){
        
        control.crearDuenio(due);
    }
    
    public void eliminarDuenio(int id){
        control.eliminarDuenio(id);
    }
    
    public void editarDuenio(Duenio x){
        control.editarDuenio(x);
    }
    
    public Duenio traerDuenio(int id){
        
        return control.traerDuenio(id);
        
    }
    
    public LinkedList<Duenio> ListaDuenio(){
      
        return control.ListaDuenio();
        
    }

    public void guardar(String nom, String raza, String color, String alergico, String especial, String duenio, String cel, String obs) {
        Duenio due = new Duenio(duenio,cel);
        Mascotas mas = new Mascotas(nom,raza,color,alergico,especial,obs,due);
        
        control.crearDuenio(due);
        control.crearMascotas(mas);
    }

    public List<Mascotas> traerMascotas() {
        return control.traerMascotas();
     }

    public void borrarMascota(int num_cliente) {
        control.borrarMascota(num_cliente);
    }

    public void modificarMascota(Mascotas masco, String nom, String raza, String color, String alergico, String especial, String duenio, String cel, String obs) {
        masco.setNombre_mascota(nom);
        masco.setColor(color);
        masco.setRaza(raza);
        masco.setAlergico(alergico);
        masco.setAtencion_especial(especial);
        masco.setObservaciones(obs);
        
        control.modificarMascota(masco);
        
        //seteo valores de dueño
        Duenio due = this.buscarDuenio(masco.getDuenio().getId_duenio());
        due.setNombre(duenio);
        due.setCelular(cel);
        
        this.modificarDuenio(due);
    }

    private Duenio buscarDuenio(int id_duenio) {
       return control.traerDuenio(id_duenio);
    }

    private void modificarDuenio(Duenio due) {
      control.modificarDuenio(due);
    }

    

    

    
    
    
}
