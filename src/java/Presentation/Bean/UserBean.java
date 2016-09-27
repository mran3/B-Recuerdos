/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation.Bean;

import BusinessLogic.Controller.UserController;
import static BusinessLogic.Controller.UserController.USER_SESSION_KEY;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
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
                                            password, 1, null);
    }
    
    public void consultUser() {
        UserController userController = new UserController();
        /*
        userName = userController.consultUser(id).getUserName();
        email = userController.consultUser(id).getEmail();
        role = userController.consultUser(id).getRole();
        */
    }
    
    public String loginUser() {
        FacesContext context = FacesContext.getCurrentInstance();
        UserController loginUsers = new UserController();
        String response = loginUsers.loginUser(userName, password);
        if (response.equals("err1")){
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Falló el Login! Usuario",
                    "El usuario no existe.");
            context.addMessage(null, message);
            return null;
        } else if (response.equals("err2")) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Falló el Login! password",
                    "El password no es correcto.");
            context.addMessage(null, message);
            return null;
        } else {
            return response;
        }
    }
    
    public void updateUser() {
        UserController userController = new UserController();
        message = userController.updateUser(userName, email,
                                            password,1, null); 
    }
    
    public void deleteUser() {
        UserController userController = new UserController();
        //message = userController.deleteUser(id);
    }
    
}
