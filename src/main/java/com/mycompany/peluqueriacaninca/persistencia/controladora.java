
package com.mycompany.peluqueriacaninca.persistencia;

import com.mycompany.peluqueriacaninca.logica.Duenio;
import com.mycompany.peluqueriacaninca.logica.Mascotas;
import com.mycompany.peluqueriacaninca.persistencia.exceptions.NonexistentEntityException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class controladora {
    MascotasJpaController masjpa = new MascotasJpaController();
    DuenioJpaController duejpa = new DuenioJpaController();
    
     public void crearMascotas(Mascotas mas) {
       masjpa.create(mas);
    }

    public void eliminarMascotas(int id) {
        try {
            masjpa.destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(controladora.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void editarMascotas(Mascotas mas) {
        try {
            masjpa.edit(mas);
        } catch (Exception ex) {
            Logger.getLogger(controladora.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Mascotas traerMascotas(int id) {
       return masjpa.findMascotas(id); 
    }

    public LinkedList<Mascotas> ListaMascotas() {
        List<Mascotas> lista = masjpa.findMascotasEntities();
        LinkedList<Mascotas> listaMascotas = new LinkedList(lista); 
        return listaMascotas;
    }

    //**********************************duenio*********************
    
     public void crearDuenio(Duenio due) {
       duejpa.create(due);
    }

    public void eliminarDuenio(int id) {
        try {
            duejpa.destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(controladora.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void editarDuenio(Duenio due) {
        try {
            duejpa.edit(due);
        } catch (Exception ex) {
            Logger.getLogger(controladora.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Duenio traerDuenio(int id) {
       return duejpa.findDuenio(id); 
    }

    public LinkedList<Duenio> ListaDuenio() {
        List<Duenio> lista = duejpa.findDuenioEntities();
        LinkedList<Duenio> listaDuenio = new LinkedList(lista); 
        return listaDuenio;
    }

    public List<Mascotas> traerMascotas() {
        
        return masjpa.findMascotasEntities();
        
     }

    public void borrarMascota(int num_cliente) {
        try{
            masjpa.destroy(num_cliente);
        } catch(NonexistentEntityException ex){
            Logger.getLogger(controladora.class.getName()).log(Level.SEVERE,null,ex);
        }
    }

    public void modificarMascota(Mascotas masco) {
        try {
            masjpa.edit(masco);
        } catch (Exception ex) {
            Logger.getLogger(controladora.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void modificarDuenio(Duenio due) {
        try {
            duejpa.edit(due);
        } catch (Exception ex) {
            Logger.getLogger(controladora.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}
