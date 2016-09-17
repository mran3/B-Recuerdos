/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessLogic.Controller;

import DataAccess.DAO.ShopDAO;
import DataAccess.Entity.Shop;
import com.mongodb.MongoClient;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.DBCursor;
import java.util.ArrayList;

/**
 *
 * @author JuanSe
 */
public class ShopController {
    /*
    public String createShop(Integer idShop,
                                Integer idManager,
                                String shopName,
                                 String shopAddress, 
                                 String shopURL){
        String shopCreate = null;
        
        try {
            // To connect to mongo dbserver
            MongoClient mongoClient = new MongoClient("localhost", 27017);
            
            //Now connecto to your databases
            DB db = mongoClient.getDB("recuerdos");
            System.out.println("Connect to database successfully");
            
            DBCollection coll = db.getCollection("shops");
            System.out.println("Collection shops selected successfully");
            
            BasicDBObject doc = new BasicDBObject ("idShop", idShop).append("idManager", idManager).append("shopName", shopName).
                    append("shopAddress", shopAddress).append("shopURL", shopURL);
            
            coll.insert(doc);
            System.out.println("Document inserted successfully");
            shopCreate = "Success";

        }
        catch (Exception e){
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        if (shopCreate != null){
            return "The shop has been created successfully";        
        }
        else {
            return "The shop has not been created";
        }
    }
    */
        public static String createShop(Shop shop) {
        ShopDAO shopDAO = new ShopDAO();
        String accountCreate = shopDAO.createShop(shop);
        if (!accountCreate.equals("Fail")) {
            return accountCreate;
        } else {
            return "The account has not been created!";
        }        
    } 
    /*    
    public ArrayList consultShop (Integer idShop){
        ArrayList shopConsult = new ArrayList();
         try {
            // To connect to mongo dbserver
            MongoClient mongoClient = new MongoClient("localhost", 27017);
            
            //Now connecto to your databases
            DB db = mongoClient.getDB("recuerdos");
            System.out.println("Connect to database successfully");
            
            DBCollection coll = db.getCollection("shops");
            System.out.println("Collection shops selected successfully");
            
            BasicDBObject whereQuery = new BasicDBObject();
            
            whereQuery.put("idShop", idShop);
            
            DBCursor cursor = coll.find(whereQuery);
            
            while(cursor.hasNext()){
                DBObject  consultDocument = cursor.next();
                shopConsult.add(consultDocument.get("idManager"));
                shopConsult.add(consultDocument.get("shopName"));
                shopConsult.add(consultDocument.get("shopAddress"));
                shopConsult.add(consultDocument.get("shopURL"));
            }
            System.out.println("Document consulted successfully");
        }
        catch (Exception e){
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
         if (!shopConsult.isEmpty()){
             return shopConsult;
         } else{
             return null;
         }
    }

*/
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
   
        /*
    public String updateShop (Integer idShop,
                                Integer idManager,
                                String shopName,
                                 String shopAddress, 
                                 String shopURL){
        
    String shopUpdate = null;
    
    try {
            // To connect to mongo dbserver
            MongoClient mongoClient = new MongoClient("localhost", 27017);
            
            //Now connecto to your databases
            DB db = mongoClient.getDB("recuerdos");
            System.out.println("Connect to database successfully");
            
            DBCollection coll = db.getCollection("shops");
            System.out.println("Collection shops selected successfully");
            
            BasicDBObject whereQuery = new BasicDBObject();
            
            whereQuery.put("idShop", idShop);
            
            DBCursor cursor = coll.find(whereQuery);
            
            while(cursor.hasNext()){
                DBObject updateDocument = cursor.next();
                updateDocument.put("idManager", idManager);
                updateDocument.put("shopName", shopName);
                updateDocument.put("shopAddress", shopAddress);
                updateDocument.put("shopURL", shopURL);
                coll.update(whereQuery, updateDocument);
            }
            System.out.println("Document updated successfully");
            shopUpdate = "Success";
            } catch (Exception e){
                System.err.println(e.getClass().getName() + ": " + e.getMessage());
            }
            if (shopUpdate != null){
                return "the shop has been updated successfully";
            } else{
                return "the shop has not been updated";
            }
    }
    
        
    public String deleteShop (Integer idShop){
        String shopDelete = null;
        try {
            // To connect to mongo dbserver
            MongoClient mongoClient = new MongoClient("localhost", 27017);
            
            //Now connecto to your databases
            DB db = mongoClient.getDB("recuerdos");
            System.out.println("Connect to database successfully");
            
            DBCollection coll = db.getCollection("shops");
            System.out.println("Collection shops selected successfully");
            
            BasicDBObject whereQuery = new BasicDBObject();
            
            coll.remove(whereQuery);
            System.out.println("Document deleted successfully");
            shopDelete = "Success";
        }catch (Exception e){
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        if(shopDelete != null){
            return "The shop has been deleted successfully";
        }else {
            return "The shop has not been deleted";
        }
    }
*/
        
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
