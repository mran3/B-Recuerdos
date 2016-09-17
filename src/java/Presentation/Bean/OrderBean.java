/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation.Bean;

import BusinessLogic.Controller.ItemController;
import BusinessLogic.Controller.OrderController;
import DataAccess.Entity.Item;
import DataAccess.Entity.Order;
import DataAccess.Entity.Solditems;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author JuanC Sexy
 */
@ManagedBean(name = "orderBean")
@ViewScoped

public class OrderBean {

    private static final long serialVersionUID = 1L;
    private Integer id;
    private Date date;
    private Long totalPrice;
    private Integer userId;
    private Collection<Solditems> solditemsCollection;

    private String message;
    private ArrayList<Order> list;

    public Integer getQuanty() {
        return quanty;
    }

    public void setQuanty(Integer quanty) {
        this.quanty = quanty;
    }
    private Integer quanty;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void addToCart(Integer id, Integer quanty, Long price) {
        if (!findSolditemsAndUdate(id, 1)) {
            getSolditemsCollection().add(new Solditems(1, id));
        }
        setTotalPrice(getTotalPrice() + price);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDate() {
        if (date == null) {
            date = new Date();
        }
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Long getTotalPrice() {
        if (totalPrice == null) {
            totalPrice = Long.valueOf("0");
        }
        return totalPrice;
    }

    public void setTotalPrice(Long totalPrice) {
        if (totalPrice == null) {
            totalPrice = Long.valueOf("0");
        }
        this.totalPrice = totalPrice;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Collection<Solditems> getSolditemsCollection() {
        if (solditemsCollection == null) {
            solditemsCollection = new ArrayList<Solditems>();
        }
        return solditemsCollection;
    }

    public void setSolditemsCollection(Collection<Solditems> solditemsCollection) {
        this.solditemsCollection = solditemsCollection;
    }

    private boolean findSolditemsAndUdate(Integer id, Integer quanty) {
        for (Solditems solditems : solditemsCollection) {
            if (solditems.getItems().equals(id)) {
                solditems.setQuantity(solditems.getQuantity() + quanty);
                return true;
            }
        }
        return false;
    }

    public void createOrder() {
        message = disminuirStock();
        if (!message.equals("Fail")) {
            message = OrderController.createOrder(new Order(id, date, totalPrice, userId, solditemsCollection));

        }
//
    }

    public void consultOrder() {

        ArrayList<Order> temp = OrderController.consultOrder(id);
        if (temp == null) {
            message = "Not item with id " + id;
        } else {
            extractForEntity(temp.get(0));
        }
    }

    public void consultorderAll() {
        ArrayList<Order> temp = OrderController.consultOrderAll();
        if (temp == null) {
            list = new ArrayList<Order>();
            list.add(new Order(99, new Date(), new Long("99"), 0, getSolditemsCollection()));
            message = "Not item with id " + id;

        } else {
            list = temp;
        }

    }

    public void deleteOrder() {
        message = OrderController.deleteOrder(new Order(id, date, totalPrice, userId, solditemsCollection));
    }

    private void extractForEntity(Order order) {
        id = order.getId();
        date = order.getDate();
        userId = order.getUserId();
        totalPrice = order.getTotalPrice();
        solditemsCollection = order.getSolditemsCollection();

    }

    private String disminuirStock() {
        String s = "Sucess";
        for (Solditems solditems : solditemsCollection) {

            ArrayList<Item> consult = ItemController.consultItem(solditems.getItems());
            if (consult != null) {
                Item item = consult.get(0);
                int result = item.getStock() - solditems.getQuantity();
                if (result < 0) {
                    return "Fail";
                }
                item.setStock(result);
                s = ItemController.updateItem(item);

            } else {
                return "Fail";
            }
        }
        return s;
    }

}
