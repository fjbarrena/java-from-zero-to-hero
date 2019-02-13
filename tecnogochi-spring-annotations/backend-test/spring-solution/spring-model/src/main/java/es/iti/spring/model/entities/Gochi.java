/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.iti.spring.model.entities;

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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author fjbarrena
 */
@Entity
@Table(name = "gochi")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Gochi.findAll", query = "SELECT g FROM Gochi g")
    , @NamedQuery(name = "Gochi.findById", query = "SELECT g FROM Gochi g WHERE g.id = :id")
    , @NamedQuery(name = "Gochi.findByName", query = "SELECT g FROM Gochi g WHERE g.name = :name")
    , @NamedQuery(name = "Gochi.findByImage", query = "SELECT g FROM Gochi g WHERE g.image = :image")
    , @NamedQuery(name = "Gochi.findByEating", query = "SELECT g FROM Gochi g WHERE g.eating = :eating")
    , @NamedQuery(name = "Gochi.findByDrinking", query = "SELECT g FROM Gochi g WHERE g.drinking = :drinking")
    , @NamedQuery(name = "Gochi.findBySocialize", query = "SELECT g FROM Gochi g WHERE g.socialize = :socialize")
    , @NamedQuery(name = "Gochi.findByFunny", query = "SELECT g FROM Gochi g WHERE g.funny = :funny")
    , @NamedQuery(name = "Gochi.findByAlive", query = "SELECT g FROM Gochi g WHERE g.alive = :alive")
    , @NamedQuery(name = "Gochi.findByBirthdate", query = "SELECT g FROM Gochi g WHERE g.birthdate = :birthdate")
    , @NamedQuery(name = "Gochi.findByDecease", query = "SELECT g FROM Gochi g WHERE g.decease = :decease")})
public class Gochi implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @Column(name = "image")
    private String image;
    @Basic(optional = false)
    @Column(name = "eating")
    private int eating;
    @Basic(optional = false)
    @Column(name = "drinking")
    private int drinking;
    @Basic(optional = false)
    @Column(name = "socialize")
    private int socialize;
    @Basic(optional = false)
    @Column(name = "funny")
    private int funny;
    @Basic(optional = false)
    @Column(name = "alive")
    private short alive;
    @Basic(optional = false)
    @Column(name = "birthdate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date birthdate;
    @Column(name = "decease")
    @Temporal(TemporalType.TIMESTAMP)
    private Date decease;
    
    
    @JoinTable(name = "gochi_has_gochi_friends", joinColumns = {
        @JoinColumn(name = "my_gochi", referencedColumnName = "id")}, inverseJoinColumns = {
        @JoinColumn(name = "friend", referencedColumnName = "id")})
    @ManyToMany
    private List<GochiFriends> gochiFriendsList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "myGochi")
    private List<Whatsapp> whatsappList;
    @JoinColumn(name = "owner", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private User owner;

    public Gochi() {
    }

    public Gochi(Integer id) {
        this.id = id;
    }

    public Gochi(Integer id, String name, String image, int eating, int drinking, int socialize, int funny, short alive, Date birthdate) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.eating = eating;
        this.drinking = drinking;
        this.socialize = socialize;
        this.funny = funny;
        this.alive = alive;
        this.birthdate = birthdate;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getEating() {
        return eating;
    }

    public void setEating(int eating) {
        this.eating = eating;
    }

    public int getDrinking() {
        return drinking;
    }

    public void setDrinking(int drinking) {
        this.drinking = drinking;
    }

    public int getSocialize() {
        return socialize;
    }

    public void setSocialize(int socialize) {
        this.socialize = socialize;
    }

    public int getFunny() {
        return funny;
    }

    public void setFunny(int funny) {
        this.funny = funny;
    }

    public short getAlive() {
        return alive;
    }

    public void setAlive(short alive) {
        this.alive = alive;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public Date getDecease() {
        return decease;
    }

    public void setDecease(Date decease) {
        this.decease = decease;
    }

    @XmlTransient
    public List<GochiFriends> getGochiFriendsList() {
        return gochiFriendsList;
    }

    public void setGochiFriendsList(List<GochiFriends> gochiFriendsList) {
        this.gochiFriendsList = gochiFriendsList;
    }

    @XmlTransient
    public List<Whatsapp> getWhatsappList() {
        return whatsappList;
    }

    public void setWhatsappList(List<Whatsapp> whatsappList) {
        this.whatsappList = whatsappList;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
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
        if (!(object instanceof Gochi)) {
            return false;
        }
        Gochi other = (Gochi) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "es.iti.spring.model.entities.Gochi[ id=" + id + " ]";
    }
    
}
