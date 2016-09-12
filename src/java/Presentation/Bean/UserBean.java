/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation.Bean;

import BusinessLogic.Controller.UserController;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author javergarav
 */
@ManagedBean(name = "userBean")
@ViewScoped
public class UserBean implements Serializable {
    
//    public Integer document;
//    private String firstName;
//    private String lastName;
//    private String userName;
    private Integer id;
    private String email;
    private String password;
    private Integer role;
    private String message;
    
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
        message = userController.createUser(id, email,
                                            password, 1, null);
    }
    
    public void consultUser() {
        UserController userController = new UserController();
        /*
        firstName = userController.consultUser(document).getFirstName();
        lastName = userController.consultUser(document).getLastName();
        userName = userController.consultUser(document).getUserName();
        */
    }
    
    public void loginUser() {
        UserController userController = new UserController();
    //    userName = userController.loginUser(userName).getUserName();
    }
    
    public void updateUser() {
        UserController userController = new UserController();
        message = userController.updateUser(email,
                                            password,1, null); 
    }
    
    public void deleteUser() {
        UserController userController = new UserController();
        //message = userController.deleteUser(document);
    }
    
}
