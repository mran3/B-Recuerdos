/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation.Bean;

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
        getSolditemsCollection().add(new Solditems(quanty, id));
        setTotalPrice(getTotalPrice()+ price) ;
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
        if(totalPrice == null)
            totalPrice = Long.valueOf("0");
        return totalPrice;
    }

    public void setTotalPrice(Long totalPrice) {
        if(totalPrice == null)
            totalPrice = Long.valueOf("0");
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

}
