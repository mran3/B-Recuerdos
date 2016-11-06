/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccess.DAO;

import DataAccess.Entity.Item;
import DataAccess.Entity.Order;
import DataAccess.Entity.Solditems;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

/**
 *
 * @author JuanC Sexy
 */
public class OrderDAO {

    public DBCollection coll;

    public Boolean connectDB() {
        try {
            // To connect to mongo dbserver
            MongoClient mongoClient = new MongoClient(Constansnt.HOST_BD_MONGO, Constansnt.PORT_BD_MONGO);
            // Now connect to your databases
            DB db = mongoClient.getDB("recuerdos");
            System.out.println("Connect to database successfully");
            coll = db.getCollection("orders");
            System.out.println("Collection orders selected successfully");
            return true;
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            return false;
        }
    }

    public String createOrder(Order order) {
        try {
            if (connectDB()) {
                BasicDBObject doc = new BasicDBObject("id_order", order.getId()).
                        append("date", order.getDate()).
                        append("total_price", order.getTotalPrice()).
                        append("user_id", order.getUserId());
                BasicDBList solditemsBDL = new BasicDBList();
                Collection<Solditems> list = order.getSolditemsCollection();
                for (Solditems soleditem : list) {
                    BasicDBObject soleditemBDO = new BasicDBObject("id_item", soleditem.getItems()).
                            append("quantity", soleditem.getQuantity());
                    solditemsBDL.add(soleditemBDO);
                }
                doc.append("solditems", solditemsBDL);
                coll.insert(doc);
                System.out.println("Document (Order) inserted successfully");
                //return "Save Item " + order.getId() + " sucess";
                return "Tu orden fue procesada y recibirás tus productos dentro de poco";
            } else {
                return "Fail";
            }
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            return "Fail";
        }
    }

    public ArrayList<Order> consultOrderAll() {
        ArrayList<Order> consult = new ArrayList<Order>();
        try {
            if (connectDB()) {
                DBCursor cursor = coll.find();
                while (cursor.hasNext()) {
                    DBObject consultDocument = cursor.next();
                    Order order = new Order();

                    order.setId((int) consultDocument.get("id_order"));
                    order.setDate((Date) consultDocument.get("date"));
                    order.setTotalPrice((Long) consultDocument.get("total_price"));
                    order.setUserId((Integer) consultDocument.get("user_id"));
                    BasicDBList soleditemsBDL = (BasicDBList) consultDocument.get("solditems");
                    Collection<Solditems> soleditemslist = new ArrayList<Solditems>();
                    for (Object solditemBDO : soleditemsBDL) {
                        DBObject temp = (DBObject) solditemBDO;
                        Solditems si = new Solditems((Integer) temp.get("quantity"), (Integer) temp.get("id_item"));
                        soleditemslist.add(si);
                    }
                    order.setSolditemsCollection(soleditemslist);
                    consult.add(order);

                }
                System.out.println("Document (Items) consulted successfully");
                return consult;
            } else {
                return consult;
            }
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            return consult;
        }
    }

    public ArrayList<Order> consultOrder(Integer id) {
        ArrayList<Order> consult = new ArrayList<Order>();
        try {
            if (connectDB()) {
                BasicDBObject whereQuery = new BasicDBObject();
                whereQuery.put("id_order", id);

                DBCursor cursor = coll.find();
                while (cursor.hasNext()) {
                    DBObject consultDocument = cursor.next();
                    Order order = new Order();

                    order.setId((int) consultDocument.get("id_order"));

                    if (order.getId().equals(id)) {
                        order.setDate((Date) consultDocument.get("date"));
                        order.setTotalPrice((Long) consultDocument.get("total_price"));
                        order.setUserId((Integer) consultDocument.get("user_id"));
                        BasicDBList soleditemsBDL = (BasicDBList) consultDocument.get("solditems");
                        Collection<Solditems> soleditemslist = new ArrayList<Solditems>();
                        for (Object solditemBDO : soleditemsBDL) {
                            DBObject temp = (BasicDBObject) solditemBDO;
                            Solditems si = new Solditems((Integer) temp.get("quantity"), (Integer) temp.get("id_item"));
                            soleditemslist.add(si);
                        }
                        order.setSolditemsCollection(soleditemslist);
                        consult.add(order);
                    }

                }
                System.out.println("Document (Items) consulted successfully");
                return consult;
            } else {
                return consult;
            }
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            return consult;
        }
    }

    public String deleteOrder(Order order) {
        try {
            if (connectDB()) {
                BasicDBObject whereQuery = new BasicDBObject();
                whereQuery.put("id_order", order.getId());
                DBCursor cursor = coll.find(whereQuery);
                coll.remove(whereQuery);
                System.out.println("Document order deleted successfully");
                return "Success, Order " + order.getId() + " deleted";
            } else {
                return "Fail";
            }
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            return "Fail";
        }
    }

    public ArrayList<Order> consultOrderByUser(Integer document) {
        ArrayList<Order> consult = new ArrayList<Order>();
        try {
            if (connectDB()) {
                BasicDBObject whereQuery = new BasicDBObject();
                whereQuery.put("user_id", document);

                DBCursor cursor = coll.find();
                while (cursor.hasNext()) {
                    DBObject consultDocument = cursor.next();
                    Order order = new Order();

                    order.setUserId((int) consultDocument.get("user_id"));

                    if (order.getUserId().equals(document)) {
                        order.setDate((Date) consultDocument.get("date"));
                        order.setTotalPrice((Long) consultDocument.get("total_price"));
                        order.setId((Integer) consultDocument.get("id_order"));
                        BasicDBList soleditemsBDL = (BasicDBList) consultDocument.get("solditems");
                        Collection<Solditems> soleditemslist = new ArrayList<Solditems>();
                        for (Object solditemBDO : soleditemsBDL) {
                            DBObject temp = (BasicDBObject) solditemBDO;
                            Solditems si = new Solditems((Integer) temp.get("quantity"), (Integer) temp.get("id_item"));
                            soleditemslist.add(si);
                        }
                        order.setSolditemsCollection(soleditemslist);
                        consult.add(order);
                    }

                }
                System.out.println("Document (Items) consulted successfully");
                return consult;
            } else {
                return consult;
            }
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            return consult;
        }
    }

}
