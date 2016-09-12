/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccess.DAO;

import DataAccess.Entity.User;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author javergarav
 */
public class UserDAO {
    
    public EntityManagerFactory emf = Persistence.createEntityManagerFactory("BankPU");
    
    public User createUser(User user) {
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
    
    public User consultUser(Integer document) {
        EntityManager em = emf.createEntityManager();
        User user = null;
        Query q = em.createNamedQuery("User.findByDocument");
        q.setParameter("document", document);
        try {
            user = (User) q.getSingleResult();
        } catch (Exception e) {
        } finally {
            em.close();
        }
        return user;
    }
    
    public User loginUser(String userName) {
        EntityManager em = emf.createEntityManager();
        User user = null;
        Query q = em.createNamedQuery("User.findByUserName");
        q.setParameter("userName", userName);
        try {
            user = (User) q.getSingleResult();
        } catch (Exception e) {
        } finally {
            em.close();
        }
        return user;
    }
    
    public User updateUser(User userUpdated) {
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
        User user = em.find(User.class, document);
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
