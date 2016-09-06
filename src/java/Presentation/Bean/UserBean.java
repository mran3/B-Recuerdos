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
    
    public Integer document;
    private String firstName;
    private String lastName;
    private String userName;
    private String password;
    private String message;

    public Integer getDocument() {
        return document;
    }

    public void setDocument(Integer document) {
        this.document = document;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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
        message = userController.createUser(document,
                                            firstName,
                                            lastName,
                                            userName,
                                            password);
    }
    
    public void consultUser() {
        UserController userController = new UserController();
        firstName = userController.consultUser(document).getFirstName();
        lastName = userController.consultUser(document).getLastName();
        userName = userController.consultUser(document).getUserName();
    }
    
    public void loginUser() {
        UserController userController = new UserController();
        userName = userController.loginUser(userName).getUserName();
    }
    
    public void updateUser() {
        UserController userController = new UserController();
        message = userController.updateUser(document,
                                            firstName,
                                            lastName,
                                            userName,
                                            password); 
    }
    
    public void deleteUser() {
        UserController userController = new UserController();
        message = userController.deleteUser(document);
    }
    
}
