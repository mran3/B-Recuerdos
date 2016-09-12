/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessLogic.Controller;

import DataAccess.DAO.ItemDAO;
import DataAccess.Entity.Item;
import java.util.ArrayList;

/**
 *
 * @author JuanC Sexy
 */
public class ItemController {
    public static String createItem(Item item) {
        ItemDAO itemDAO = new ItemDAO();
        String accountCreate = itemDAO.createItem(item);
        if (!accountCreate.equals("Fail")) {
            return accountCreate;
        } else {
            return "The account has not been created!";
        }        
    } 
    
    public static ArrayList consultItem(Integer id) {
        ItemDAO itemDAO = new ItemDAO();
        ArrayList<Item> accountConsult = new ArrayList<Item>();
        accountConsult = itemDAO.consultItem(id);
        if(!accountConsult.isEmpty()){
            return accountConsult;
        } else {
            return null;
        }
    }
    public static ArrayList<Item> consultItemAll() {
        ItemDAO itemDAO = new ItemDAO();
        ArrayList<Item> accountConsult = new ArrayList<Item>();
        accountConsult = itemDAO.consultItemAll();
        if(!accountConsult.isEmpty()){
            return accountConsult;
        } else {
            return null;
        }
    }
    public static String deleteItem(Item item) {
        ItemDAO itemDAO = new ItemDAO();
        String accountDeleted = itemDAO.deleteItem(item);
        if (!accountDeleted.equals("Fail")) {
            return "The account has been deleted successfully!";
        } else {
            return "The account has not been updated!";
        }        
    } 

    public static String updateItem(Item item) {
        ItemDAO itemDAO = new ItemDAO();
        String accountUpdate = itemDAO.updateItem(item);
        if (!accountUpdate.equals("Fail")) {
            return "The account has been updated successfully!";
        } else {
            return "The account has not been updated!";
        }
    }

    
    
}
