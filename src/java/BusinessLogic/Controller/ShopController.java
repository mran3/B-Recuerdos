/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessLogic.Controller;

import DataAccess.DAO.ShopDAO;
import DataAccess.Entity.Shop;
import java.util.ArrayList;

/**
 *
 * @author JuanSe
 */
public class ShopController {

        public static String createShop(Shop shop) {
        ShopDAO shopDAO = new ShopDAO();
        String accountCreate = shopDAO.createShop(shop);
        if (!accountCreate.equals("Fail")) {
            return accountCreate;
        } else {
            return "The account has not been created!";
        }        
    } 

        public static ArrayList consultShop(Integer id) {
        ShopDAO shopDAO = new ShopDAO();
        ArrayList<Shop> accountConsult = new ArrayList<Shop>();
        accountConsult = shopDAO.consutShop(id);
        if(!accountConsult.isEmpty()){
            return accountConsult;
        } else {
            return null;
        }
    }    
        public static String updateShop(Shop shop) {
        ShopDAO shopDAO = new ShopDAO();
        String accountUpdate = shopDAO.updateShop(shop);
        if (!accountUpdate.equals("Fail")) {
            return "The account has been updated successfully!";
        } else {
            return "The account has not been updated!";
        }
    }
        
    public static String deleteShop(Shop shop) {
        ShopDAO shopDAO = new ShopDAO();
        String accountDeleted = shopDAO.deleteShop(shop);
        if (!accountDeleted.equals("Fail")) {
            return "The account has been deleted successfully!";
        } else {
            return "The account has not been updated!";
        }        
    } 

}
