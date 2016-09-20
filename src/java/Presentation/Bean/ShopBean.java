/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation.Bean;

import BusinessLogic.Controller.ShopController;
import DataAccess.Entity.Shop;
import java.io.Serializable;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author JuanSe
 */

@ManagedBean (name = "shopBean")
@ViewScoped

public class ShopBean implements Serializable  {
    public Integer idShop;
    private Integer idManager;
    private String shopName;
    private String shopAddress;
    private String shopURL;
    private String message;

    public Integer getIdShop() {
        return idShop;
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
    
    public void createShop(){
        message = ShopController.createShop(new Shop(idShop,idManager,shopName,shopAddress,shopURL));
    }
    
    
        public void consutShop() {
        ArrayList<Shop> temp = ShopController.consultShop(idShop);
        if (temp == null) {
            message = "Not item with id " + idShop;
        } else {
            extractForEntity(temp.get(0));
        }
    }
    
    public void updateShop(){
        message = ShopController.updateShop(new Shop(idShop,idManager,shopName,shopAddress,shopURL));
    }
    
    public void deleteShop(){
        message = ShopController.deleteShop(new Shop (idShop));
    }
    
        private void extractForEntity(Shop shop) {
        idShop = shop.getId();
        idManager = shop.getManagerId();
        shopName = shop.getName();
        shopAddress = shop.getAddress();
        shopURL = shop.getUrl();
    }
}