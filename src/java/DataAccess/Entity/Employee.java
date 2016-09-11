/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccess.Entity;

import java.io.Serializable;
import java.math.BigInteger;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author JuanC Sexy
 */
@Entity
@Table(name = "employees")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Employees.findAll", query = "SELECT e FROM Employees e"),
    @NamedQuery(name = "Employees.findById", query = "SELECT e FROM Employees e WHERE e.id = :id"),
    @NamedQuery(name = "Employees.findBySalary", query = "SELECT e FROM Employees e WHERE e.salary = :salary"),
    @NamedQuery(name = "Employees.findByPosition", query = "SELECT e FROM Employees e WHERE e.position = :position"),
    @NamedQuery(name = "Employees.findByRole", query = "SELECT e FROM Employees e WHERE e.role = :role")})
public class Employee implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @Column(name = "salary")
    private BigInteger salary;
    @Size(max = 255)
    @Column(name = "position")
    private String position;
    @Column(name = "role")
    private Integer role;
    @JoinColumn(name = "Shop_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Shop shopid;
    @JoinColumn(name = "User_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private User userid;

    public Employee() {
    }

    public Employee(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigInteger getSalary() {
        return salary;
    }

    public void setSalary(BigInteger salary) {
        this.salary = salary;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public Shop getShopid() {
        return shopid;
    }

    public void setShopid(Shop shopid) {
        this.shopid = shopid;
    }

    public User getUserid() {
        return userid;
    }

    public void setUserid(User userid) {
        this.userid = userid;
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
        if (!(object instanceof Employee)) {
            return false;
        }
        Employee other = (Employee) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DataAccess.Entity.Employees[ id=" + id + " ]";
    }
    
}
