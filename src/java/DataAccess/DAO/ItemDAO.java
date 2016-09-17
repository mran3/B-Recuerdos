/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccess.DAO;

import DataAccess.Entity.Item;
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
 * @author JuanC Sexy
 */
public class ItemDAO {

    public DBCollection coll;

    public Boolean connectDB() {
        try {
            // To connect to mongo dbserver
            MongoClient mongoClient = new MongoClient("localhost", 27017);
            // Now connect to your databases
            DB db = mongoClient.getDB("recuerdos");
            System.out.println("Connect to database successfully");
            coll = db.getCollection("items");
            System.out.println("Collection account selected successfully");
            return true;
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            return false;
        }
    }

    public String createItem(Item item) {
        try {
            if (connectDB()) {
                BasicDBObject doc = new BasicDBObject("id_item", item.getId()).
                        append("name", item.getName()).
                        append("description", item.getDescription()).
                        append("price", item.getPrice().longValue()).
                        append("stock", item.getStock()).
                        append("shop_id", item.getShopid());

                coll.insert(doc);
                System.out.println("Document (ITEM) inserted successfully");
                return "Save Item " + item.getId() + " sucess";
            } else {
                return "Fail";
            }
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            return "Fail";
        }
    }

    public ArrayList<Item> consultItemAll() {
        ArrayList<Item> accountConsult = new ArrayList<Item>();
        try {
            if (connectDB()) {
                DBCursor cursor = coll.find();
                while (cursor.hasNext()) {
                    DBObject consultDocument = cursor.next();
                    Item item = new Item();

                    item.setId((int) consultDocument.get("id_item"));
                    item.setName((String) consultDocument.get("name"));
                    item.setDescription((String) consultDocument.get("description"));
                    item.setPrice(BigInteger.valueOf((long) consultDocument.get("price")));
                    item.setStock((int) consultDocument.get("stock"));
                    item.setShopid((int) consultDocument.get("shop_id"));

                    accountConsult.add(item);

                }
                System.out.println("Document (Items) consulted successfully");
                return accountConsult;
            } else {
                return accountConsult;
            }
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            return accountConsult;
        }
    }

    public ArrayList<Item> consultItem(Integer id) {
        ArrayList accountConsult = new ArrayList();
        try {
            if (connectDB()) {
                BasicDBObject whereQuery = new BasicDBObject();
                // Sentence to search one account number
                whereQuery.put("id_item", id);
                DBCursor cursor = coll.find(whereQuery);
                while (cursor.hasNext()) {
                    DBObject consultDocument = cursor.next();
                    Item item = new Item();

                    item.setId((int) consultDocument.get("id_item"));
                    item.setName((String) consultDocument.get("name"));
                    item.setDescription((String) consultDocument.get("description"));
                    item.setPrice(BigInteger.valueOf((long) consultDocument.get("price")));
                    item.setStock((int) consultDocument.get("stock"));
                    item.setShopid((int) consultDocument.get("shop_id"));

                    accountConsult.add(item);
                }
                System.out.println("Document (Items) consulted successfully");
                return accountConsult;
            } else {
                return accountConsult;
            }
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            return accountConsult;
        }
    }

    public String updateItem(Item item) {
        try {
            if (connectDB()) {
                BasicDBObject whereQuery = new BasicDBObject();
                whereQuery.put("id_item", item.getId());
                DBCursor cursor = coll.find(whereQuery);
                while (cursor.hasNext()) {
                    DBObject updateDocument = cursor.next();
                    updateDocument.put("name", item.getName());
                    updateDocument.put("description", item.getDescription());
                    updateDocument.put("price", item.getPrice().longValue());
                    updateDocument.put("stock", item.getStock());
                    updateDocument.put("shop_id", item.getShopid());
                    coll.update(whereQuery, updateDocument);
                }
                System.out.println("Document updated (Item) successfully");
                return "Success, Item "+item.getId()+" updated ";
            } else {
                return "Fail";
            }
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            return "Fail";
        }
    }

    public String deleteItem(Item item) {
        try {
            if (connectDB()) {
                BasicDBObject whereQuery = new BasicDBObject();
                whereQuery.put("id_item", item.getId());
                DBCursor cursor = coll.find(whereQuery);
                coll.remove(whereQuery);
                System.out.println("Document deleted successfully");
                return "Success, Item "+item.getId()+" deleted";
            } else {
                return "Fail";
            }
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            return "Fail";
        }
    }
    public static void test(String args){
        ItemDAO dao = new ItemDAO();
        Item item= new Item();
        item.setId(2);
        item.setName("TestDAO");
        item.setDescription(args);
        item.setPrice(BigInteger.valueOf(10000));
        item.setStock(100);
        item.setShopid(1);
        dao.createItem(item);
        ArrayList<Item> list = dao.consultItem(2);
        Item consItem = list.get(0);
        consItem.setStock(100-2);
        dao.updateItem(consItem);
        dao.deleteItem(item);
        
        
        
    }

}
