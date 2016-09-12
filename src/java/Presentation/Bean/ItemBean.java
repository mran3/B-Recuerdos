package Presentation.Bean;

import BusinessLogic.Controller.ItemController;
import DataAccess.Entity.Item;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author JuanC Sexy
 */
@ManagedBean(name = "itemBean")
@ViewScoped

public class ItemBean implements Serializable {

    private Integer id;
    private String name;
    private String description;
    private BigInteger price;
    private Integer stock;
    private Integer shopid = 0;
    private String message;
    private ArrayList<Item> list;

    public ArrayList<Item> getList() {
        if (list == null) {
            consultItemAll();
        }
        return list;
    }

    public void setList(ArrayList list) {
        this.list = list;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigInteger getPrice() {
        return price;
    }

    public void setPrice(BigInteger price) {
        this.price = price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public int getShopid() {
        return shopid;
    }

    public void setShopid(int shopid) {
        this.shopid = shopid;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void createItem() {
        message = ItemController.createItem(new Item(id, name, description, price, stock, shopid));
    }

    public void consultItem() {
        ArrayList<Item> temp = ItemController.consultItem(id);
        if (temp == null) {
            message = "Not item with id " + id;
        } else {
            extractForEntity(temp.get(0));
        }
    }

    public void consultItemAll() {
        ArrayList<Item> temp = ItemController.consultItemAll();
        if (temp == null) {
            list = new ArrayList<>();
            list.add(new Item(2, "asdad", "aaa", price, 10, 1));
            message = "Not item with id " + id;
            
        } else {
            list = temp;
        }

    }

    public void updateItem() {
        message = ItemController.updateItem(new Item(id, name, description, price, stock, shopid));
    }

    public void deleteItem() {
        message = ItemController.deleteItem(new Item(id, name, description, price, stock, shopid));
    }

    private void extractForEntity(Item item) {
        id = item.getId();
        name = item.getName();
        description = item.getDescription();
        price = item.getPrice();
        stock = item.getStock();
        shopid = item.getShopid();
    }

}
