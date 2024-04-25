/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.peluqueriacaninca.persistencia;

import com.mycompany.peluqueriacaninca.logica.Duenio;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.mycompany.peluqueriacaninca.logica.Mascotas;
import com.mycompany.peluqueriacaninca.persistencia.exceptions.NonexistentEntityException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Usuario
 */
public class DuenioJpaController implements Serializable {

    public DuenioJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    
    public DuenioJpaController(){
       emf =  Persistence.createEntityManagerFactory("PeluqueriaPersistencia");
    }
    
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Duenio duenio) {
        if (duenio.getMasco() == null) {
            duenio.setMasco(new ArrayList<Mascotas>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Mascotas> attachedMasco = new ArrayList<Mascotas>();
            for (Mascotas mascoMascotasToAttach : duenio.getMasco()) {
                mascoMascotasToAttach = em.getReference(mascoMascotasToAttach.getClass(), mascoMascotasToAttach.getNum_cliente());
                attachedMasco.add(mascoMascotasToAttach);
            }
            duenio.setMasco(attachedMasco);
            em.persist(duenio);
            for (Mascotas mascoMascotas : duenio.getMasco()) {
                Duenio oldDuenioOfMascoMascotas = mascoMascotas.getDuenio();
                mascoMascotas.setDuenio(duenio);
                mascoMascotas = em.merge(mascoMascotas);
                if (oldDuenioOfMascoMascotas != null) {
                    oldDuenioOfMascoMascotas.getMasco().remove(mascoMascotas);
                    oldDuenioOfMascoMascotas = em.merge(oldDuenioOfMascoMascotas);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Duenio duenio) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Duenio persistentDuenio = em.find(Duenio.class, duenio.getId_duenio());
            List<Mascotas> mascoOld = persistentDuenio.getMasco();
            List<Mascotas> mascoNew = duenio.getMasco();
            List<Mascotas> attachedMascoNew = new ArrayList<Mascotas>();
            for (Mascotas mascoNewMascotasToAttach : mascoNew) {
                mascoNewMascotasToAttach = em.getReference(mascoNewMascotasToAttach.getClass(), mascoNewMascotasToAttach.getNum_cliente());
                attachedMascoNew.add(mascoNewMascotasToAttach);
            }
            mascoNew = attachedMascoNew;
            duenio.setMasco(mascoNew);
            duenio = em.merge(duenio);
            for (Mascotas mascoOldMascotas : mascoOld) {
                if (!mascoNew.contains(mascoOldMascotas)) {
                    mascoOldMascotas.setDuenio(null);
                    mascoOldMascotas = em.merge(mascoOldMascotas);
                }
            }
            for (Mascotas mascoNewMascotas : mascoNew) {
                if (!mascoOld.contains(mascoNewMascotas)) {
                    Duenio oldDuenioOfMascoNewMascotas = mascoNewMascotas.getDuenio();
                    mascoNewMascotas.setDuenio(duenio);
                    mascoNewMascotas = em.merge(mascoNewMascotas);
                    if (oldDuenioOfMascoNewMascotas != null && !oldDuenioOfMascoNewMascotas.equals(duenio)) {
                        oldDuenioOfMascoNewMascotas.getMasco().remove(mascoNewMascotas);
                        oldDuenioOfMascoNewMascotas = em.merge(oldDuenioOfMascoNewMascotas);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = duenio.getId_duenio();
                if (findDuenio(id) == null) {
                    throw new NonexistentEntityException("The duenio with id " + id + " no longer exists.");
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
            Duenio duenio;
            try {
                duenio = em.getReference(Duenio.class, id);
                duenio.getId_duenio();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The duenio with id " + id + " no longer exists.", enfe);
            }
            List<Mascotas> masco = duenio.getMasco();
            for (Mascotas mascoMascotas : masco) {
                mascoMascotas.setDuenio(null);
                mascoMascotas = em.merge(mascoMascotas);
            }
            em.remove(duenio);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Duenio> findDuenioEntities() {
        return findDuenioEntities(true, -1, -1);
    }

    public List<Duenio> findDuenioEntities(int maxResults, int firstResult) {
        return findDuenioEntities(false, maxResults, firstResult);
    }

    private List<Duenio> findDuenioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Duenio.class));
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

    public Duenio findDuenio(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Duenio.class, id);
        } finally {
            em.close();
        }
    }

    public int getDuenioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Duenio> rt = cq.from(Duenio.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
