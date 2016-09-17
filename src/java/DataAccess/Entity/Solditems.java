/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccess.Entity;

import java.io.Serializable;

/**
 *
 * @author JuanC Sexy
 */
public class Solditems implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer quantity;
    private Integer item;
    
    public Solditems() {
    }

    public Solditems(Integer quantity, Integer item) {
        this.quantity = quantity;
        this.item = item;
    }

   
    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getItems() {
        return item;
    }

    public void setItems(Integer item) {
        this.item = item;
    }

    @Override
    public String toString() {
        return "DataAccess.Entity.Solditem[ solditemPK=" + item+ " , Cuanti: "+ quantity+"]";
    }
    
}
