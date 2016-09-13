/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccess.DAO;

import DataAccess.Entity.Users;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.faces.context.FacesContext;

/**
 *
 * @author javergarav
 */
public class UserDAO {
    
    public EntityManagerFactory emf = Persistence.createEntityManagerFactory("pupuputa");
    
    public Users createUser(Users user) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            em.persist(user);
            em.getTransaction().commit();
        } catch(Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
        return user;
    }
    
    public Users consultUser(Integer document) {
        EntityManager em = emf.createEntityManager();
        Users user = null;
        Query q = em.createNamedQuery("User.findByDocument");
        q.setParameter("document", document);
        try {
            user = (Users) q.getSingleResult();
        } catch (Exception e) {
        } finally {
            em.close();
        }
        return user;
    }
    
    public Users loginUser(String email) {
        EntityManager em = emf.createEntityManager();
        Users user = null;
        Query q = em.createNamedQuery("User.findByUserName");
        q.setParameter("userName", email);
        try {
            user = (Users) q.getSingleResult();
        } catch (Exception e) {
        } finally {
            em.close();
        }
        return user;
    }
    
    public Users updateUser(Users userUpdated) {
        /* 
        EntityManager em = emf.createEntityManager();
        //User user = em.find(User.class, userUpdated.getDocument());
        em.getTransaction().begin();
        try {
            
            user.setDocument(userUpdated.getDocument());
            user.setFirstName(userUpdated.getFirstName());
            user.setLastName(userUpdated.getLastName());
            user.setUserName(userUpdated.getUserName());
            user.setPassword(userUpdated.getPassword());
            em.merge(user);
            em.getTransaction().commit();
        } catch(Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
        return user;
        */
        return null;
    }
    
    public String deleteUser(Integer document) {
        EntityManager em = emf.createEntityManager();
        Users user = em.find(Users.class, document);
        em.getTransaction().begin();
        try {
            em.remove(user);
            em.getTransaction().commit();
            return "Success";
        } catch(Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
        return "Nonsuccess";
    }
    
}
