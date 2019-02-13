/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.iti.formacion.tecnogochi.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Salvador Morera i Soler <smorera@iti.es>
 */
@Entity
@Table(name = "message")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Message.findAll", query = "SELECT m FROM Message m"),
    @NamedQuery(name = "Message.findById", query = "SELECT m FROM Message m WHERE m.id = :id"),
    @NamedQuery(name = "Message.findByFromIP", query = "SELECT m FROM Message m WHERE m.fromIP = :fromIP"),
    @NamedQuery(name = "Message.findByFromName", query = "SELECT m FROM Message m WHERE m.fromName = :fromName"),
    @NamedQuery(name = "Message.findByReaded", query = "SELECT m FROM Message m WHERE m.readed = :readed")})
public class Message implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "fromIP", nullable = false, length = 255)
    private String fromIP;
    @Basic(optional = false)
    @Column(name = "fromName", nullable = false, length = 255)
    private String fromName;
    @Basic(optional = false)
    @Lob
    @Column(name = "message", nullable = false, length = 16777215)
    private String message;
    @Basic(optional = false)
    @Column(name = "readed", nullable = false)
    private boolean readed;
    @JoinColumn(name = "toGochi", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private MyGochis toGochi;

    public Message() {
    }

    public Message(Integer id) {
        this.id = id;
    }

    public Message(Integer id, String fromIP, String fromName, String message, boolean readed) {
        this.id = id;
        this.fromIP = fromIP;
        this.fromName = fromName;
        this.message = message;
        this.readed = readed;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFromIP() {
        return fromIP;
    }

    public void setFromIP(String fromIP) {
        this.fromIP = fromIP;
    }

    public String getFromName() {
        return fromName;
    }

    public void setFromName(String fromName) {
        this.fromName = fromName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean getReaded() {
        return readed;
    }

    public void setReaded(boolean readed) {
        this.readed = readed;
    }

    public MyGochis getToGochi() {
        return toGochi;
    }

    public void setToGochi(MyGochis toGochi) {
        this.toGochi = toGochi;
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
        if (!(object instanceof Message)) {
            return false;
        }
        Message other = (Message) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "es.iti.formacion.tecnogochi.model.Message[ id=" + id + " ]";
    }
    
}
