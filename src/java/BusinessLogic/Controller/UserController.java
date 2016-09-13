/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessLogic.Controller;

import DataAccess.DAO.UserDAO;
import DataAccess.Entity.Users;
import javax.faces.context.FacesContext;

/**
 *
 * @author fasto
 */
public class UserController {
    public static final String USER_SESSION_KEY = "users";
    public String createUser(Integer id,
                            String email,
                             String password,
                             Integer role,
                             Integer shop_id) {
        
        Users user = new Users();
        user.setId(id);
        user.setEmail(email);
        user.setPassword(password);
        user.setRole(1);
        user.setShopId(null);
        /*
        user.setDocument(document);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setUserName(userName);
        user.setPassword(password);
        */
        UserDAO userDAO = new UserDAO();
        Users userCreate = userDAO.createUser(user);
        
        if (userCreate != null) {
            return "El usuario fue creado";
        } else {
            return "El usuario NO fue creado";
        }        
    } 
    
    public Users consultUser(Integer document) {
        
        UserDAO userDAO = new UserDAO();
        Users userConsult = userDAO.consultUser(document);
        
        if (userConsult != null) {
            return userConsult;
        } else {
            return null;
        }        
    }
    
    public String loginUser(String email, String password) {
        FacesContext context = FacesContext.getCurrentInstance();
        UserDAO userDAO = new UserDAO();
        Users userLogin = userDAO.loginUser(email);
        if (userLogin != null) {
            if (!userLogin.getPassword().equals(password)) {
                return "err2";
            }
            context.getExternalContext().getSessionMap().put(USER_SESSION_KEY, userLogin);
            return "main-admin";
        } else {           
            return "err1";
        }        
    }
    
    public String updateUser(String email,
                             String password,
                             Integer role,
                             Integer shop_id) {
        
        Users user = new Users();
        /*
        user.setDocument(document);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setUserName(userName);
        user.setPassword(password);
        */
        UserDAO userDAO = new UserDAO();
        Users userUpdate = userDAO.updateUser(user);
        
        if (userUpdate != null) {
            return "The user has been updated successfully!";
        } else {
            return "The user has not been updated!";
        }        
    }
    
    public String deleteUser(Integer document) {
        
        UserDAO userDAO = new UserDAO();
        String userDelete = userDAO.deleteUser(document);
        
        if (userDelete == "Success") {
            return "The user has been deleted successfully!";
        } else {
            return "The user has not been deleted!";
        }        
    } 
}
