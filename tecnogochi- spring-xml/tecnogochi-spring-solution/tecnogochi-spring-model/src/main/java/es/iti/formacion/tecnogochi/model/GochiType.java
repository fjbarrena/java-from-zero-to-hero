/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.iti.formacion.tecnogochi.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Salvador Morera i Soler <smorera@iti.es>
 */
@Entity
@Table(name = "gochi_type")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GochiType.findAll", query = "SELECT g FROM GochiType g"),
    @NamedQuery(name = "GochiType.findById", query = "SELECT g FROM GochiType g WHERE g.id = :id"),
    @NamedQuery(name = "GochiType.findByName", query = "SELECT g FROM GochiType g WHERE g.name = :name")})
public class GochiType implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "name", nullable = false, length = 255)
    private String name;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "gochiType")
    private List<MyGochis> myGochisList;

    public GochiType() {
    }

    public GochiType(Integer id) {
        this.id = id;
    }

    public GochiType(Integer id, String name) {
        this.id = id;
        this.name = name;
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

    @XmlTransient
    public List<MyGochis> getMyGochisList() {
        return myGochisList;
    }

    public void setMyGochisList(List<MyGochis> myGochisList) {
        this.myGochisList = myGochisList;
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
        if (!(object instanceof GochiType)) {
            return false;
        }
        GochiType other = (GochiType) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "es.iti.formacion.tecnogochi.model.GochiType[ id=" + id + " ]";
    }
    
}
