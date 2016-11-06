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
public class BCheck {

    static public Integer bCheck(Integer document) {
        OrderDAO orderDAO = new OrderDAO();
        ArrayList<Order> result = orderDAO.consultOrderByUser(document);
        if (result.size() > 0) {
            return 1;
        } else {
            return 0;
        }
        
    }

}
