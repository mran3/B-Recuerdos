/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccess.DAO;

import DataAccess.Entity.Shop;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import java.math.BigInteger;
import java.util.ArrayList;
/**
 *
 * @author JuanSe
 */
public class ShopDAO {
    
    public DBCollection coll;

    public Boolean connectDB() {
        try {
            // To connect to mongo dbserver
            MongoClient mongoClient = new MongoClient("localhost", 27017);
            // Now connect to your databases
            DB db = mongoClient.getDB("recuerdos");
            System.out.println("Connect to database successfully");
            coll = db.getCollection("shops");
            System.out.println("Collection account selected successfully");
            return true;
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            return false;
        }
    }
    
    public String createShop(Shop shop) {
        try {
            if (connectDB()) {
                BasicDBObject doc = new BasicDBObject("id_shop", shop.getId()).
                        append("name", shop.getName()).
                        append("manager_id", shop.getManagerId()).
                        append("address", shop.getAddress()).
                        append("url", shop.getUrl());
                coll.insert(doc);
                System.out.println("Document (SHOP) inserted successfully");
                return "Save Shop " + shop.getId() + " sucess";
            } else {
                return "Fail";
            }
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            return "Fail";
        }
    }
        public ArrayList<Shop> consutShop(Integer id) {
        ArrayList accountConsult = new ArrayList();
        try {
            if (connectDB()) {
                BasicDBObject whereQuery = new BasicDBObject();
                // Sentence to search one account number
                whereQuery.put("id_shop", id);
                DBCursor cursor = coll.find(whereQuery);
                while (cursor.hasNext()) {
                    DBObject consultDocument = cursor.next();
                    Shop shop = new Shop();

                    shop.setId((int) consultDocument.get("id_shop"));
                    shop.setManagerId((Integer) consultDocument.get("manager_id"));
                    shop.setName((String) consultDocument.get("name"));
                    shop.setAddress((String) consultDocument.get("address"));
                    shop.setUrl((String) consultDocument.get("url"));

                    accountConsult.add(shop);
                }
                System.out.println("Document (Shop) consulted successfully");
                return accountConsult;
            } else {
                return accountConsult;
            }
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            return accountConsult;
        }
    }
        
 public String updateShop(Shop shop) {
        try {
            if (connectDB()) {
                BasicDBObject whereQuery = new BasicDBObject();
                whereQuery.put("id_shop", shop.getId());
                DBCursor cursor = coll.find(whereQuery);
                while (cursor.hasNext()) {
                    DBObject updateDocument = cursor.next();
                    updateDocument.put("manager_id", shop.getManagerId());
                    updateDocument.put("name", shop.getName());
                    updateDocument.put("address", shop.getAddress());
                    updateDocument.put("url", shop.getUrl());
                    coll.update(whereQuery, updateDocument);
                }
                System.out.println("Document updated (Shop) successfully");
                return "Success, Item "+shop.getId()+" updated ";
            } else {
                return "Fail";
            }
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            return "Fail";
        }
    }
        
 public String deleteShop(Shop shop) {
        try {
            if (connectDB()) {
                BasicDBObject whereQuery = new BasicDBObject();
                whereQuery.put("id_shop", shop.getId());
                DBCursor cursor = coll.find(whereQuery);
                coll.remove(whereQuery);
                System.out.println("Document deleted successfully");
                return "Success, Item "+shop.getId()+" deleted";
            } else {
                return "Fail";
            }
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            return "Fail";
        }
    }        
}
