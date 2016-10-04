/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation.Bean;

import BusinessLogic.Controller.ShopController;
import BusinessLogic.Controller.UserController;
import DataAccess.Entity.Shop;
import DataAccess.Entity.User;
import java.io.Serializable;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author JuanSe
 */
@ManagedBean(name = "shopBean")
@ViewScoped

public class ShopBean implements Serializable {

    public Integer idShop;

    private Integer idManager;
    private Integer roleadd;

    public void updateUserFull(Integer id, Integer shop_id) {
        UserController userController = new UserController();
        User userUpdate = userController.consultUser(id);
        userUpdate.setShopId(shop_id);
        message = userController.updateUser(id, userUpdate.getUserName(), userUpdate.getEmail(),
                userUpdate.getPassword(), userUpdate.getRole(), shop_id);
    }

    public Integer getRoleadd() {
        return roleadd;
    }

    public void setRoleadd(Integer roleadd) {
        this.roleadd = roleadd;
    }
    private String shopName;
    private String shopAddress;
    private String shopURL;
    private String message;

    private ArrayList<Shop> list;
    private ArrayList<User> listEmployee;

    public ArrayList<User> listDeEmpleados(int id_shop) {
        consultListEmployee(id_shop);
        return listEmployee;
    }

    public ArrayList<User> listEmployeeAll() {
        return ShopController.consultAllEmployee();
    }

    public Integer getIdShop() {
        return idShop;
    }

    public ArrayList<Shop> getList() {
        if (list == null) {
            consultItemAll();
        }
        return list;
    }

    public void setList(ArrayList<Shop> list) {
        this.list = list;
    }

    public void setIdShop(Integer idShop) {
        this.idShop = idShop;
    }

    public Integer getIdManager() {
        return idManager;
    }

    public void setIdManager(Integer idManager) {
        this.idManager = idManager;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getShopAddress() {
        return shopAddress;
    }

    public void setShopAddress(String shopAddress) {
        this.shopAddress = shopAddress;
    }

    public String getShopURL() {
        return shopURL;
    }

    public void setShopURL(String shopURL) {
        this.shopURL = shopURL;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void createShop() {
        message = ShopController.createShop(new Shop(idShop, idManager, shopName, shopAddress, shopURL));
    }

    public void consutShop() {
        ArrayList<Shop> temp = ShopController.consultShop(idShop);
        if (temp == null) {
            message = "Not item with id " + idShop;
        } else {
            extractForEntity(temp.get(0));
        }
    }

    public void updateShop() {
        message = ShopController.updateShop(new Shop(idShop, idManager, shopName, shopAddress, shopURL));
    }

    public void deleteShop() {
        message = ShopController.deleteShop(new Shop(idShop));
    }

    private void extractForEntity(Shop shop) {
        idShop = shop.getId();
        idManager = shop.getManagerId();
        shopName = shop.getName();
        shopAddress = shop.getAddress();
        shopURL = shop.getUrl();
    }

    private void consultItemAll() {
        ArrayList<Shop> temp = ShopController.consultItemAll();
        if (temp == null) {
            list = new ArrayList<>();
            list.add(new Shop(3, 1, "Mi primera Tienda", "Calle Falsa No 1 - 35", "www.unal.edu.co"));
            message = "Not shop with id " + idShop;

        } else {
            list = temp;
        }
    }

    private void consultListEmployee(int id_shop) {
        ArrayList<User> temp = ShopController.consultUserByShop(id_shop);
        if (temp == null) {
            listEmployee = new ArrayList<>();
            listEmployee.add(new User(-1, 2, "Disculpa no tienes empleados", "", ""));
            message = "Disculpa no tienes empleados" + id_shop;

        } else {
            listEmployee = temp;
        }
    }
}
