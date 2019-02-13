/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.iti.spring.model.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author fjbarrena
 */
@Entity
@Table(name = "gochi_friends")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GochiFriends.findAll", query = "SELECT g FROM GochiFriends g")
    , @NamedQuery(name = "GochiFriends.findById", query = "SELECT g FROM GochiFriends g WHERE g.id = :id")
    , @NamedQuery(name = "GochiFriends.findByName", query = "SELECT g FROM GochiFriends g WHERE g.name = :name")
    , @NamedQuery(name = "GochiFriends.findByLocationIp", query = "SELECT g FROM GochiFriends g WHERE g.locationIp = :locationIp")
    , @NamedQuery(name = "GochiFriends.findByLocationPort", query = "SELECT g FROM GochiFriends g WHERE g.locationPort = :locationPort")})
public class GochiFriends implements Serializable {

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
    @Column(name = "location_ip")
    private String locationIp;
    @Basic(optional = false)
    @Column(name = "location_port")
    private int locationPort;
    @ManyToMany(mappedBy = "gochiFriendsList")
    private List<Gochi> gochiList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "friend")
    private List<Whatsapp> whatsappList;

    public GochiFriends() {
    }

    public GochiFriends(Integer id) {
        this.id = id;
    }

    public GochiFriends(Integer id, String name, String locationIp, int locationPort) {
        this.id = id;
        this.name = name;
        this.locationIp = locationIp;
        this.locationPort = locationPort;
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

    public String getLocationIp() {
        return locationIp;
    }

    public void setLocationIp(String locationIp) {
        this.locationIp = locationIp;
    }

    public int getLocationPort() {
        return locationPort;
    }

    public void setLocationPort(int locationPort) {
        this.locationPort = locationPort;
    }

    @XmlTransient
    public List<Gochi> getGochiList() {
        return gochiList;
    }

    public void setGochiList(List<Gochi> gochiList) {
        this.gochiList = gochiList;
    }

    @XmlTransient
    public List<Whatsapp> getWhatsappList() {
        return whatsappList;
    }

    public void setWhatsappList(List<Whatsapp> whatsappList) {
        this.whatsappList = whatsappList;
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
        if (!(object instanceof GochiFriends)) {
            return false;
        }
        GochiFriends other = (GochiFriends) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "es.iti.spring.model.entities.GochiFriends[ id=" + id + " ]";
    }
    
}
