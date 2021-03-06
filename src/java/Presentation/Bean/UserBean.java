/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation.Bean;

import BusinessLogic.Controller.LoginLdapController;
import BusinessLogic.Controller.UserController;
import static BusinessLogic.Controller.UserController.USER_SESSION_KEY;
import DataAccess.Entity.User;
import java.io.IOException;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.application.NavigationHandler;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author javergarav
 */
@ManagedBean(name = "userBean")
@ViewScoped
public class UserBean implements Serializable {

    private Integer id;
    private Integer role;
    private String userName;
    private String email;
    private String password;
    public Integer shop_id;
    private String message = "";

    public Integer getRole() {
        return role;
    }
     
            
    public void redirect() throws IOException {
          FacesContext context = FacesContext.getCurrentInstance();
          String currentPage = FacesContext.getCurrentInstance().getViewRoot().getViewId();
          if(!currentPage.contains("index") && !currentPage.contains("login") && !currentPage.contains("createUser")){
              if (!this.isLoggedIn()){
                NavigationHandler nh = FacesContext.getCurrentInstance().getApplication().getNavigationHandler();
                nh.handleNavigation(FacesContext.getCurrentInstance(), null, "login");
              }
          }
          
          

    }

    public void setRole(Integer role) {
        this.role = role;
    }
    
    public boolean isLoggedIn() {
        FacesContext context = FacesContext.getCurrentInstance();
        return context.getExternalContext().getSessionMap().get(USER_SESSION_KEY) != null;
    }
    
     public String logOut() {
        FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().getSessionMap().clear();
        return "main-index"; 
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getShop_id() {
        return shop_id;
    }

    public void setShop_id(Integer shop_id) {
        this.shop_id = shop_id;
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
    public void createUser() {
        UserController userController = new UserController();
        message = userController.createUser(id, userName, email,
                                            password, 1, shop_id);
    }
    
    public void consultUser() {
        UserController userController = new UserController();
        User consultedUser  = userController.consultUser(id);
        if(consultedUser== null){
            this.clearUserInfo();
            this.setMessage("Usuario no encontrado.");
            return;
        }
        this.setMessage("Usuario encontrado.");
        userName = consultedUser.getUserName();
        email = consultedUser.getEmail();
        role = consultedUser.getRole();
    }
    
    public void clearUserInfo() {
        this.userName = this.email ="";
        this.role = null;
        
    }
    
    public String loginUser() {
        FacesContext context = FacesContext.getCurrentInstance();
        LoginLdapController login = new LoginLdapController();
        message = login.login(userName, password);
        
        UserController loginUsers = new UserController();
        String response = loginUsers.loginUser(userName, password);
        
        if (response.equals("err1")){
            message = "LDAP - " + message + "Falló el Login! Usuario,El usuario no existe.";
            /*FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Falló el Login! Usuario",
                    "El usuario no existe.");
            context.addMessage(null, message);
            */
            return null;
        } else if (response.equals("err2")) {
            message = "LDAP - " + message + "Falló el Login! password,El password no es correcto..";
            
            /*
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Falló el Login! password",
                    "El password no es correcto.");
            context.addMessage(null, message);
            */
            return null;
        } else if (!message.equals("Your login was successful.")) //Autenticacion con el LDAP
        { 
            //return null; //Commentar para omitir LDAP
            return response; //Comentar para autenticar con LDAP
        }
        else {
            return response;
        }
    }
    
    public void updateUser() {
        UserController userController = new UserController();
        message = userController.updateUser(id,userName, email,
                                            password,role, shop_id); 
    }
    
    
    public void deleteUser() {
        UserController userController = new UserController();
        message = userController.deleteUser(id);
    }
    
}
