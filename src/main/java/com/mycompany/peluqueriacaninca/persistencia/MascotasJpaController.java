/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.peluqueriacaninca.persistencia;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.mycompany.peluqueriacaninca.logica.Duenio;
import com.mycompany.peluqueriacaninca.logica.Mascotas;
import com.mycompany.peluqueriacaninca.persistencia.exceptions.NonexistentEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Usuario
 */
public class MascotasJpaController implements Serializable {

    public MascotasJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    
    public MascotasJpaController(){
       emf =  Persistence.createEntityManagerFactory("PeluqueriaPersistencia");
    }
    
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Mascotas mascotas) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Duenio duenio = mascotas.getDuenio();
            if (duenio != null) {
                duenio = em.getReference(duenio.getClass(), duenio.getId_duenio());
                mascotas.setDuenio(duenio);
            }
            em.persist(mascotas);
            if (duenio != null) {
                duenio.getMasco().add(mascotas);
                duenio = em.merge(duenio);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Mascotas mascotas) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Mascotas persistentMascotas = em.find(Mascotas.class, mascotas.getNum_cliente());
            Duenio duenioOld = persistentMascotas.getDuenio();
            Duenio duenioNew = mascotas.getDuenio();
            if (duenioNew != null) {
                duenioNew = em.getReference(duenioNew.getClass(), duenioNew.getId_duenio());
                mascotas.setDuenio(duenioNew);
            }
            mascotas = em.merge(mascotas);
            if (duenioOld != null && !duenioOld.equals(duenioNew)) {
                duenioOld.getMasco().remove(mascotas);
                duenioOld = em.merge(duenioOld);
            }
            if (duenioNew != null && !duenioNew.equals(duenioOld)) {
                duenioNew.getMasco().add(mascotas);
                duenioNew = em.merge(duenioNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = mascotas.getNum_cliente();
                if (findMascotas(id) == null) {
                    throw new NonexistentEntityException("The mascotas with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(int id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Mascotas mascotas;
            try {
                mascotas = em.getReference(Mascotas.class, id);
                mascotas.getNum_cliente();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The mascotas with id " + id + " no longer exists.", enfe);
            }
            Duenio duenio = mascotas.getDuenio();
            if (duenio != null) {
                duenio.getMasco().remove(mascotas);
                duenio = em.merge(duenio);
            }
            em.remove(mascotas);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Mascotas> findMascotasEntities() {
        return findMascotasEntities(true, -1, -1);
    }

    public List<Mascotas> findMascotasEntities(int maxResults, int firstResult) {
        return findMascotasEntities(false, maxResults, firstResult);
    }

    private List<Mascotas> findMascotasEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Mascotas.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Mascotas findMascotas(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Mascotas.class, id);
        } finally {
            em.close();
        }
    }

    public int getMascotasCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Mascotas> rt = cq.from(Mascotas.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
