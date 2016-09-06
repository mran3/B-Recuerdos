/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessLogic.Controller;

import DataAccess.DAO.UserDAO;
import DataAccess.Entity.User;

/**
 *
 * @author javergarav
 */
public class UserController {
    
    public String createUser(Integer document,
                             String firstName,
                             String lastName,
                             String userName,
                             String password) {
        
        User user = new User();
        user.setDocument(document);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setUserName(userName);
        user.setPassword(password);
        
        UserDAO userDAO = new UserDAO();
        User userCreate = userDAO.createUser(user);
        
        if (userCreate != null) {
            return "The user has been created successfully!";
        } else {
            return "The user has not been created!";
        }        
    } 
    
    public User consultUser(Integer document) {
        
        UserDAO userDAO = new UserDAO();
        User userConsult = userDAO.consultUser(document);
        
        if (userConsult != null) {
            return userConsult;
        } else {
            return null;
        }        
    }
    
    public User loginUser(String userName) {
        
        UserDAO userDAO = new UserDAO();
        User userLogin = userDAO.loginUser(userName);
        
        if (userLogin != null) {
            return userLogin;
        } else {
            return null;
        }        
    }
    
    public String updateUser(Integer document,
                             String firstName,
                             String lastName,
                             String userName,
                             String password) {
        
        User user = new User();
        user.setDocument(document);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setUserName(userName);
        user.setPassword(password);
        
        UserDAO userDAO = new UserDAO();
        User userUpdate = userDAO.updateUser(user);
        
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
