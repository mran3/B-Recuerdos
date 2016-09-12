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
 * @author JuanC Sexy
 */
public class Solditems implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    @Column(name = "quantity")
    private Integer quantity;
    @JoinColumn(name = "item_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Item item;
    
    public Solditems() {
    }

   
    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Item getItems() {
        return item;
    }

    public void setItems(Item item) {
        this.item = item;
    }

    @Override
    public String toString() {
        return "DataAccess.Entity.Solditem[ solditemPK=" + item+ " , Cuanti: "+ quantity+"]";
    }
    
}
