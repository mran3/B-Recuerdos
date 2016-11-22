/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccess.DAO;

import DataAccess.Entity.User;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import java.util.ArrayList;

/*
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
 */
/**
 *
 * @author javergarav
 */
public class UserDAO {

    public DBCollection coll;

    public Boolean connectDB() {
        try {
            // To connect to mongo dbserver
            MongoClient mongoClient = new MongoClient(Constants.HOST_BD_MONGO, Constants.PORT_BD_MONGO);
            // Now connect to your databases
            DB db = mongoClient.getDB("recuerdos");
            System.out.println("Connect to database successfully");
            coll = db.getCollection("users");
            System.out.println("Collection account selected successfully");
            return true;
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            return false;
        }
    }


    /*
    public EntityManagerFactory emf = Persistence.createEntityManagerFactory("RecuerdosPU");

    public User createUser(User user) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            em.persist(user);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
        return user;
    }
     */
    public User createUser(User user) {
        try {
            if (connectDB()) {
                BasicDBObject doc = new BasicDBObject("id_user", user.getId()).
                        append("role", user.getRole()).
                        append("user_name", user.getUserName()).
                        append("email", user.getEmail()).
                        append("password", user.getPassword()).
                        append("id_shop", user.getShopId());
                coll.insert(doc);
                System.out.println("Document (SHOP) inserted successfully");
                return user;
            } else {
                return null;
            }
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            return null;
        }
    }

    /*
    public User consultUser(Integer id) {
        EntityManager em = emf.createEntityManager();
        User user = null;
        Query q = em.createNamedQuery("User.findById");
        q.setParameter("id", id);
        try {
            user = (User) q.getSingleResult();
        } catch (Exception e) {
            System.err.println("Error DAO User::" + e);
        } finally {
            em.close();
        }
        return user;
    }
     */
    public User consultUser(Integer id) {
        ArrayList<User> userConsult = new ArrayList<>();
        try {
            if (connectDB()) {
                BasicDBObject whereQuery = new BasicDBObject();
                // Sentence to search one account number
                whereQuery.put("id_user", id);
                DBCursor cursor = coll.find(whereQuery);
                while (cursor.hasNext()) {
                    DBObject consultDocument = cursor.next();
                    User user = new User();
                    user.setId((Integer) consultDocument.get("id_user"));
                    user.setRole((int) consultDocument.get("role"));
                    user.setUserName((String) consultDocument.get("user_name"));
                    user.setEmail((String) consultDocument.get("email"));
                    user.setPassword((String) consultDocument.get("password"));
                    user.setShopId((Integer) consultDocument.get("id_shop"));

                    userConsult.add(user);
                }
                System.out.println("Document (User) consulted successfully");
                if (userConsult.size() > 0) {
                    return userConsult.get(0);
                } else {
                    return null;
                }
            } else {
                return null;
            }
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            return null;
        }
    }

    /*
    public ArrayList<User> consultbyUser(Integer userId) {
        EntityManager em = emf.createEntityManager();
        ArrayList<User> list = new ArrayList<>();
        Query q = em.createNamedQuery("User.findByUserId");
        q.setParameter("userId", userId);
        try {
            List listemp = q.getResultList();
            for (Object user : listemp) {
                list.add((User) user);
            }
        } catch (Exception e) {

            System.err.println("Error DAO User::" + e);
        } finally {
            em.close();
        }
        return list;
    }
    
    
     */
    public ArrayList<User> consultbyShop(Integer id) {
        ArrayList<User> userConsult = new ArrayList<>();
        try {
            if (connectDB()) {
                BasicDBObject whereQuery = new BasicDBObject();
                // Sentence to search one account number
                whereQuery.put("id_shop", id);
                DBCursor cursor = coll.find(whereQuery);
                while (cursor.hasNext()) {
                    DBObject consultDocument = cursor.next();
                    User user = new User();
                    user.setId((Integer) consultDocument.get("id_user"));
                    user.setRole((int) consultDocument.get("role"));
                    user.setUserName((String) consultDocument.get("user_name"));
                    user.setEmail((String) consultDocument.get("email"));
                    user.setPassword((String) consultDocument.get("password"));
                    user.setShopId((Integer) consultDocument.get("id_shop"));

                    userConsult.add(user);
                }
                System.out.println("Document (User) consulted successfully");
                return userConsult;
            } else {
                return userConsult;
            }
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            return userConsult;
        }
    }

    /*
    public ArrayList<User> consultbyRole(Integer role) {
         EntityManager em = emf.createEntityManager();
        ArrayList<User> list = new ArrayList<>();
        Query q = em.createNamedQuery("User.findByRole");
        q.setParameter("role", role);
        try {
            List listemp = q.getResultList();
            for (Object user : listemp) {
                list.add((User) user);
            }
        } catch (Exception e) {

            System.err.println("Error DAO User::" + e);
        } finally {
            em.close();
        }
        return list;
    }
     */
    public ArrayList<User> consultbyRole(Integer role) {
        ArrayList<User> userConsult = new ArrayList<>();
        try {
            if (connectDB()) {
                BasicDBObject whereQuery = new BasicDBObject();
                // Sentence to search one account number
                whereQuery.put("role", role);
                DBCursor cursor = coll.find(whereQuery);
                while (cursor.hasNext()) {
                    DBObject consultDocument = cursor.next();
                    User user = new User();
                    user.setId((Integer) consultDocument.get("id_user"));
                    user.setRole((int) consultDocument.get("role"));
                    user.setUserName((String) consultDocument.get("user_name"));
                    user.setEmail((String) consultDocument.get("email"));
                    user.setPassword((String) consultDocument.get("password"));
                    user.setShopId((Integer) consultDocument.get("id_shop"));

                    userConsult.add(user);
                }
                System.out.println("Document (User) consulted successfully");
                return userConsult;
            } else {
                return userConsult;
            }
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            return userConsult;
        }
    }

    /*


    public ArrayList<User> consultall() {
        EntityManager em = emf.createEntityManager();
        ArrayList<User> list = null;
        Query q = em.createNamedQuery("User.findAll");
        try {
            list = (ArrayList<User>) q.getResultList();
        } catch (Exception e) {
        } finally {
            em.close();
        }
        return list;
    }
     */
    public ArrayList<User> consultall() {
        ArrayList<User> userConsult = new ArrayList<>();
        try {
            if (connectDB()) {
                BasicDBObject whereQuery = new BasicDBObject();
                // Sentence to search one account number
                DBCursor cursor = coll.find(whereQuery);
                while (cursor.hasNext()) {
                    DBObject consultDocument = cursor.next();
                    User user = new User();
                    user.setId((Integer) consultDocument.get("id_user"));
                    user.setRole((int) consultDocument.get("role"));
                    user.setUserName((String) consultDocument.get("user_name"));
                    user.setEmail((String) consultDocument.get("email"));
                    user.setPassword((String) consultDocument.get("password"));
                    user.setShopId((Integer) consultDocument.get("id_shop"));

                    userConsult.add(user);
                }
                System.out.println("Document (User) consulted successfully");
                return userConsult;
            } else {
                return userConsult;
            }
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            return userConsult;
        }
    }

    /*

    public User loginUser(String userName) {
        EntityManager em = emf.createEntityManager();
        User user = null;
        Query q = em.createNamedQuery("User.findByUserName");
        q.setParameter("userName", userName);
        try {
            user = (User) q.getSingleResult();
        } catch (Exception e) {
        } finally {
            em.close();
        }
        return user;
    }
     */
    public User loginUser(String userName) {
        ArrayList<User> userConsult = new ArrayList<>();
        try {
            if (connectDB()) {
                BasicDBObject whereQuery = new BasicDBObject();
                // Sentence to search one account number
                whereQuery.put("user_name", userName);
                DBCursor cursor = coll.find(whereQuery);
                while (cursor.hasNext()) {
                    DBObject consultDocument = cursor.next();
                    User user = new User();
                    user.setId((Integer) consultDocument.get("id_user"));
                    //int role = ((Double)(consultDocument.get("role"))).intValue();
                    int role = ((Integer)(consultDocument.get("role"))).intValue();
                    user.setRole(role);
                    user.setUserName((String) consultDocument.get("user_name"));
                    user.setEmail((String) consultDocument.get("email"));
                    user.setPassword((String) consultDocument.get("password"));
                    user.setShopId((Integer) consultDocument.get("id_shop"));

                    userConsult.add(user);
                }
                System.out.println("Document (User) consulted successfully");
                if (userConsult.size() > 0) {
                    return userConsult.get(0);
                } else {
                    return null;
                }
            } else {
                return null;
            }
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            return null;
        }
    }

    /*
    public User updateUser(User userUpdated) {
        EntityManager em = emf.createEntityManager();
        User user = em.find(User.class, userUpdated.getId());
        em.getTransaction().begin();
        try {
            user.setId(userUpdated.getId());
            user.setUserName(userUpdated.getUserName());
            user.setEmail(userUpdated.getEmail());
            user.setRole(userUpdated.getRole());
            user.setPassword(userUpdated.getPassword());
            em.merge(user);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
        return user;
    }
     */
    public User updateUser(User user) {
        try {
            if (connectDB()) {
                BasicDBObject whereQuery = new BasicDBObject();
                whereQuery.put("id_user", user.getId());
                DBCursor cursor = coll.find(whereQuery);
                while (cursor.hasNext()) {
                    DBObject updateDocument = cursor.next();
                    updateDocument.put("role", user.getRole());
                    updateDocument.put("user_name", user.getUserName());
                    updateDocument.put("email", user.getEmail());
                    updateDocument.put("password", user.getPassword());
                    updateDocument.put("id_shop", user.getShopId());
                    coll.update(whereQuery, updateDocument);
                }
                System.out.println("Document updated (User) successfully");
                return user;
            } else {
                return null;
            }
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            return null;
        }
    }

    /*
    public String deleteUser(Integer id) {
        EntityManager em = emf.createEntityManager();
        User user = em.find(User.class, id);
        em.getTransaction().begin();
        try {
            em.remove(user);
            em.getTransaction().commit();
            return "Success";
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
        return "Nonsuccess";
    }
     */
    public String deleteUser(int id) {
        try {
            if (connectDB()) {
                BasicDBObject whereQuery = new BasicDBObject();
                whereQuery.put("id_user", id);
                DBCursor cursor = coll.find(whereQuery);
                coll.remove(whereQuery);
                System.out.println("Document deleted successfully");
                return "Success, User " + id + " deleted";
            } else {
                return "Fail";
            }
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            return "Fail";
        }
    }

}
