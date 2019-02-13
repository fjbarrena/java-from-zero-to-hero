/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.iti.formacion.tecnogochi.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Salvador Morera i Soler <smorera@iti.es>
 */
@Entity
@Table(name = "my_gochis", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"name"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MyGochis.findAll", query = "SELECT m FROM MyGochis m"),
    @NamedQuery(name = "MyGochis.findById", query = "SELECT m FROM MyGochis m WHERE m.id = :id"),
    @NamedQuery(name = "MyGochis.findByName", query = "SELECT m FROM MyGochis m WHERE m.name = :name"),
    @NamedQuery(name = "MyGochis.findByBirthDate", query = "SELECT m FROM MyGochis m WHERE m.birthDate = :birthDate"),
    @NamedQuery(name = "MyGochis.findByDeathDate", query = "SELECT m FROM MyGochis m WHERE m.deathDate = :deathDate"),
    @NamedQuery(name = "MyGochis.findByLastAccess", query = "SELECT m FROM MyGochis m WHERE m.lastAccess = :lastAccess"),
    @NamedQuery(name = "MyGochis.findByFood", query = "SELECT m FROM MyGochis m WHERE m.food = :food"),
    @NamedQuery(name = "MyGochis.findByDrink", query = "SELECT m FROM MyGochis m WHERE m.drink = :drink"),
    @NamedQuery(name = "MyGochis.findBySocial", query = "SELECT m FROM MyGochis m WHERE m.social = :social"),
    @NamedQuery(name = "MyGochis.findByFun", query = "SELECT m FROM MyGochis m WHERE m.fun = :fun")})
public class MyGochis implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "name", nullable = false, length = 255)
    private String name;
    @Basic(optional = false)
    @Column(name = "birthDate", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date birthDate;
    @Column(name = "deathDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date deathDate;
    @Basic(optional = false)
    @Column(name = "lastAccess", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastAccess;
    @Basic(optional = false)
    @Column(name = "food", nullable = false)
    private int food;
    @Basic(optional = false)
    @Column(name = "drink", nullable = false)
    private int drink;
    @Basic(optional = false)
    @Column(name = "social", nullable = false)
    private int social;
    @Basic(optional = false)
    @Column(name = "fun", nullable = false)
    private int fun;
    @JoinColumn(name = "gochi_type", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private GochiType gochiType;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "gochi")
    private List<Action> actionList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "toGochi")
    private List<Message> messageList;

    public MyGochis() {
    }

    public MyGochis(Integer id) {
        this.id = id;
    }

    public MyGochis(Integer id, String name, Date birthDate, Date lastAccess, int food, int drink, int social, int fun) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
        this.lastAccess = lastAccess;
        this.food = food;
        this.drink = drink;
        this.social = social;
        this.fun = fun;
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

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Date getDeathDate() {
        return deathDate;
    }

    public void setDeathDate(Date deathDate) {
        this.deathDate = deathDate;
    }

    public Date getLastAccess() {
        return lastAccess;
    }

    public void setLastAccess(Date lastAccess) {
        this.lastAccess = lastAccess;
    }

    public int getFood() {
        return food;
    }

    public void setFood(int food) {
        this.food = food;
    }

    public int getDrink() {
        return drink;
    }

    public void setDrink(int drink) {
        this.drink = drink;
    }

    public int getSocial() {
        return social;
    }

    public void setSocial(int social) {
        this.social = social;
    }

    public int getFun() {
        return fun;
    }

    public void setFun(int fun) {
        this.fun = fun;
    }

    public GochiType getGochiType() {
        return gochiType;
    }

    public void setGochiType(GochiType gochiType) {
        this.gochiType = gochiType;
    }

    @XmlTransient
    public List<Action> getActionList() {
        return actionList;
    }

    public void setActionList(List<Action> actionList) {
        this.actionList = actionList;
    }

    @XmlTransient
    public List<Message> getMessageList() {
        return messageList;
    }

    public void setMessageList(List<Message> messageList) {
        this.messageList = messageList;
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
        if (!(object instanceof MyGochis)) {
            return false;
        }
        MyGochis other = (MyGochis) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "es.iti.formacion.tecnogochi.model.MyGochis[ id=" + id + " ]";
    }
    
}
