/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ceti.terceramano;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author USUARIO
 */
@Entity
@Table(name = "object")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Object1.findAll", query = "SELECT o FROM Object1 o")
    , @NamedQuery(name = "Object1.findByIdobject", query = "SELECT o FROM Object1 o WHERE o.idobject = :idobject")
    , @NamedQuery(name = "Object1.findByNameObject", query = "SELECT o FROM Object1 o WHERE o.nameObject = :nameObject")
    , @NamedQuery(name = "Object1.findByNewOwner", query = "SELECT o FROM Object1 o WHERE o.newOwner = :newOwner")
    , @NamedQuery(name = "Object1.findByOrgOwner", query = "SELECT o FROM Object1 o WHERE o.orgOwner = :orgOwner")})
public class Object1 implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Integer idobject;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "name_object")
    private String nameObject;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    private String description;
    @Column(name = "new_owner")
    private Integer newOwner;
    @Basic(optional = false)
    @NotNull
    @Column(name = "org_owner")
    private int orgOwner;

    public Object1() {
    }

    public Object1(Integer idobject) {
        this.idobject = idobject;
    }

    public Object1(Integer idobject, String nameObject, String description, int orgOwner) {
        this.idobject = idobject;
        this.nameObject = nameObject;
        this.description = description;
        this.orgOwner = orgOwner;
    }

    public Integer getIdobject() {
        return idobject;
    }

    public void setIdobject(Integer idobject) {
        this.idobject = idobject;
    }

    public String getNameObject() {
        return nameObject;
    }

    public void setNameObject(String nameObject) {
        this.nameObject = nameObject;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getNewOwner() {
        return newOwner;
    }

    public void setNewOwner(Integer newOwner) {
        this.newOwner = newOwner;
    }

    public int getOrgOwner() {
        return orgOwner;
    }

    public void setOrgOwner(int orgOwner) {
        this.orgOwner = orgOwner;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idobject != null ? idobject.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Object1)) {
            return false;
        }
        Object1 other = (Object1) object;
        if ((this.idobject == null && other.idobject != null) || (this.idobject != null && !this.idobject.equals(other.idobject))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ceti.terceramano.Object1[ idobject=" + idobject + " ]";
    }
    
}
