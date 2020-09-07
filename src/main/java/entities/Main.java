/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import utils.EMF_Creator;

/**
 *
 * @author thorc
 */
public class Main {
    
    public static void main(String[] args) {
        EntityManagerFactory emf = EMF_Creator.createEntityManagerFactory();
        EntityManager em = emf.createEntityManager();
        
        try{
            em.getTransaction().begin();
            em.persist(new Movie(2005, "Lol", "Lolleren"));
            em.getTransaction().commit();
        } finally {
        em.close();
    }
    }
    
}
