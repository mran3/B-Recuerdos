/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccess.Entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

/**
 *
 * @author JuanC Sexy
 */
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer id;
    private Date date;
    private Long totalPrice;
    private Integer userId;
    private Collection<Solditems> solditemsCollection;

    public Order() {
    }

    public Order(Integer id, Date date, Long totalPrice, Integer userId, Collection<Solditems> solditemsCollection) {
        this.id = id;
        this.date = date;
        this.totalPrice = totalPrice;
        this.userId = userId;
        this.solditemsCollection = solditemsCollection;
    }

    public Order(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Long getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Long totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Collection<Solditems> getSolditemsCollection() {
        return solditemsCollection;
    }

    public void setSolditemsCollection(Collection<Solditems> solditemsCollection) {
        this.solditemsCollection = solditemsCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Order)) {
            return false;
        }
        Order other = (Order) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DataAccess.Entity.Orders[ id=" + id + " ]";
    }
    
}
