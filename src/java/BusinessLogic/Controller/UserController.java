/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessLogic.Controller;

import DataAccess.DAO.UserDAO;
import DataAccess.Entity.User;
import javax.faces.context.FacesContext;

/**
 *
 * @author fasto
 */
public class UserController {
    public static final String USER_SESSION_KEY = "user";
    public String createUser(Integer id,
                             String userName,
                             String email,
                             String password,
                             Integer role,
                             Integer shop_id) {
        
        User user = new User();
        user.setId(id);
        user.setUserName(userName);
        user.setEmail(email);
        user.setPassword(password);
        user.setRole(role);
        user.setShopId(shop_id);
        
        UserDAO userDAO = new UserDAO();
        User userCreate = userDAO.createUser(user);
        
        if (userCreate != null) {
            return "El usuario fue creado";
        } else {
            return "El usuario NO fue creado";
        }        
    } 
    
    public User consultUser(Integer id) {
        
        UserDAO userDAO = new UserDAO();
        User userConsult = userDAO.consultUser(id);
        
        if (userConsult != null) {
            return userConsult;
        } else {
            return null;
        }        
    }
    
    public String loginUser(String userName, String password) {
        FacesContext context = FacesContext.getCurrentInstance();
        UserDAO userDAO = new UserDAO();
        User userLogin = userDAO.loginUser(userName);
        if (userLogin != null) {
            if (!userLogin.getPassword().equals(password)) {
                return "err2";
            }
            context.getExternalContext().getSessionMap().put(USER_SESSION_KEY, userLogin);
            return "main-index";
        } else {           
            return "err1";
        }        
    }
    
    public String updateUser(Integer id,
                             String userName,
                             String email,
                             String password,
                             Integer role,
                             Integer shop_id) {
        
        User user = new User();  
        user.setId(id);
        user.setUserName(userName);
        user.setEmail(email);
        user.setPassword(password);
        user.setRole(role);
        user.setShopId(shop_id);
        UserDAO userDAO = new UserDAO();
        User userUpdate = userDAO.updateUser(user);
        
        if (userUpdate != null) {
            return "The user has been updated successfully!";
        } else {
            return "The user has not been updated!";
        }        
    }
    
    public String deleteUser(Integer id) {
        
        UserDAO userDAO = new UserDAO();
        String userDelete = userDAO.deleteUser(id);
        
        if (userDelete.equals("Success")) {
            return "The user has been deleted successfully!";
        } else {
            return "The user has not been deleted!";
        }        
    } 
}
