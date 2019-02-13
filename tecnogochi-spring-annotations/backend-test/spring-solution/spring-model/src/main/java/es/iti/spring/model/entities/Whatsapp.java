/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.iti.spring.model.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author fjbarrena
 */
@Entity
@Table(name = "whatsapp")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Whatsapp.findAll", query = "SELECT w FROM Whatsapp w")
    , @NamedQuery(name = "Whatsapp.findById", query = "SELECT w FROM Whatsapp w WHERE w.id = :id")
    , @NamedQuery(name = "Whatsapp.findByMessage", query = "SELECT w FROM Whatsapp w WHERE w.message = :message")
    , @NamedQuery(name = "Whatsapp.findByCreationDate", query = "SELECT w FROM Whatsapp w WHERE w.creationDate = :creationDate")})
public class Whatsapp implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "message")
    private String message;
    @Basic(optional = false)
    @Column(name = "creation_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;
    @JoinColumn(name = "my_gochi", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Gochi myGochi;
    @JoinColumn(name = "friend", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private GochiFriends friend;

    public Whatsapp() {
    }

    public Whatsapp(Integer id) {
        this.id = id;
    }

    public Whatsapp(Integer id, String message, Date creationDate) {
        this.id = id;
        this.message = message;
        this.creationDate = creationDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Gochi getMyGochi() {
        return myGochi;
    }

    public void setMyGochi(Gochi myGochi) {
        this.myGochi = myGochi;
    }

    public GochiFriends getFriend() {
        return friend;
    }

    public void setFriend(GochiFriends friend) {
        this.friend = friend;
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
        if (!(object instanceof Whatsapp)) {
            return false;
        }
        Whatsapp other = (Whatsapp) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "es.iti.spring.model.entities.Whatsapp[ id=" + id + " ]";
    }
    
}
