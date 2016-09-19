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
import javax.faces.context.FacesContext;

/**
 *
 * @author javergarav
 */
public class UserDAO {
    
    public EntityManagerFactory emf = Persistence.createEntityManagerFactory("RecuerdosPU");
    
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
    
    public User consultUser(Integer id) {
        EntityManager em = emf.createEntityManager();
        User user = null;
        Query q = em.createNamedQuery("User.findByDocument");
        q.setParameter("document", id);
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
        //User user = em.find(User.class, userUpdated.getId());
        em.getTransaction().begin();
        try {
            
            user.setDocument(userUpdated.getId());
            user.setUserName(userUpdated.getUserName());
            user.setEmail(userUpdated.getEmail());
            user.setRole(userUpdated.getRole());
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
    
    public String deleteUser(Integer id) {
        EntityManager em = emf.createEntityManager();
        User user = em.find(User.class, id);
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
