/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessLogic.Controller;

import DataAccess.DAO.OrderDAO;

import DataAccess.Entity.Order;
import java.util.ArrayList;

/**
 *
 * @author JuanC Sexy
 */
public class OrderController {

    public static String createOrder(Order order) {
        OrderDAO orderDAO = new OrderDAO();
        String accountCreate = orderDAO.createOrder(order);
        if (!accountCreate.equals("Fail")) {
            return accountCreate;
        } else {
            return "The account has not been created!";
        }
    }

    public static ArrayList<Order> consultOrder(Integer id) {
        OrderDAO orderDAO = new OrderDAO();
        ArrayList<Order> Consult = new ArrayList<Order>();
        Consult = orderDAO.consultOrder(id);
        if (!Consult.isEmpty()) {
            return Consult;
        } else {
            return null;
        }
    }

    public static ArrayList<Order> consultOrderAll() {
        OrderDAO orderDAO = new OrderDAO();
        ArrayList<Order> Consult = new ArrayList<Order>();
        Consult = orderDAO.consultOrderAll();
        if (!Consult.isEmpty()) {
            return Consult;
        } else {
            return null;
        }
    }

    public static String deleteOrder(Order order) {
        OrderDAO orderDAO = new OrderDAO();
        String accountDeleted = orderDAO.deleteOrder(order);
        if (!accountDeleted.equals("Fail")) {
            return "The account has been deleted successfully!";
        } else {
            return "The account has not been updated!";
        }
    }

}
