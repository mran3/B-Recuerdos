/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation.Bean;

import static BusinessLogic.Controller.UserController.USER_SESSION_KEY;
import DataAccess.Entity.User;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author JuanC Sexy
 */
@ManagedBean(name = "sessionBean")
@ViewScoped
public class SessionBean {

    private Integer id;
    private String userName;
    private int role;
    private String email;
    private String password;
    private Integer shopId;

    public void checkUserofLogged() {
        if (userName == null) {
            FacesContext context = FacesContext.getCurrentInstance();
            User user = (User) context.getExternalContext().getSessionMap().get(USER_SESSION_KEY);
            if (user != null) {
                extractfOfEntity(user);
            }
        }
    }

    private void extractfOfEntity(User user) {
        id = user.getId();
        userName = user.getUserName();
        role = user.getRole();
        email = user.getEmail();
        shopId = user.getShopId();
        password = user.getPassword();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        checkUserofLogged();
        if (userName == null) {
            return "Anonimo";
        } else {
            return userName;
        }
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRole() {
        checkUserofLogged();
        switch (role) {
            case 1:
                return "Cliente";
            case 2:
                return "Operador";
            case 3:
                return "Administrador";
            default:
                return "Invitado";
        }

    }

    public void setRole(int role) {
        this.role = role;
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

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }
    /*
     001 - 1 - Solo cliente tiene acceso
     010 - 2 - Solo operador tiene acceso
     011 - 3 - Operador y cliente tiene acceso
     100 - 4 - Solo admin tiene acesso
     101 - 5 - Solo admin tiene acesso
     110 - 6 - operador y admin tienen aceso
     111 - 7 - Cliente, operador y admin tienen acceso
    */
    
    public String getPermision(int access){
     checkUserofLogged();
     int enable = (int)Math.pow(2, (role-1)) & access;
     if ( enable == 0){
         return "hidden";
     }
     else{
         return "none";
     }
    }

}
