/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccess.Entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author fasto
 */
@Entity
@Table(name = "SoldItems")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SoldItems.findAll", query = "SELECT s FROM SoldItems s"),
    @NamedQuery(name = "SoldItems.findByQuantity", query = "SELECT s FROM SoldItems s WHERE s.quantity = :quantity"),
    @NamedQuery(name = "SoldItems.findByItemId", query = "SELECT s FROM SoldItems s WHERE s.soldItemsPK.itemId = :itemId"),
    @NamedQuery(name = "SoldItems.findByOrderId", query = "SELECT s FROM SoldItems s WHERE s.soldItemsPK.orderId = :orderId")})
public class SoldItems implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected SoldItemsPK soldItemsPK;
    @Column(name = "quantity")
    private Integer quantity;
    @JoinColumn(name = "item_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Items items;
    @JoinColumn(name = "order_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Orders orders;

    public SoldItems() {
    }

    public SoldItems(SoldItemsPK soldItemsPK) {
        this.soldItemsPK = soldItemsPK;
    }

    public SoldItems(int itemId, int orderId) {
        this.soldItemsPK = new SoldItemsPK(itemId, orderId);
    }

    public SoldItemsPK getSoldItemsPK() {
        return soldItemsPK;
    }

    public void setSoldItemsPK(SoldItemsPK soldItemsPK) {
        this.soldItemsPK = soldItemsPK;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Items getItems() {
        return items;
    }

    public void setItems(Items items) {
        this.items = items;
    }

    public Orders getOrders() {
        return orders;
    }

    public void setOrders(Orders orders) {
        this.orders = orders;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (soldItemsPK != null ? soldItemsPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SoldItems)) {
            return false;
        }
        SoldItems other = (SoldItems) object;
        if ((this.soldItemsPK == null && other.soldItemsPK != null) || (this.soldItemsPK != null && !this.soldItemsPK.equals(other.soldItemsPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DataAccess.Entity.SoldItems[ soldItemsPK=" + soldItemsPK + " ]";
    }
    
}
