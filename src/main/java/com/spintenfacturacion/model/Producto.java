/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spintenfacturacion.model;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="producto")
public class Producto implements Serializable {
    
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE,generator="SEQ_ID_PRODUCTO")
    @SequenceGenerator(name="SEQ_ID_PRODUCTO",sequenceName="seq_idproducto", allocationSize=1)
    private int id;
    @Column(name="concepto")
    private String concepto;
    //@OneToMany(mappedBy="producto")
    //private Set<detalleFactura> detallefacturalist;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

   /* public Set<detalleFactura> getDetallefacturalist() {
        return detallefacturalist;
    }

    public void setDetallefacturalist(Set<detalleFactura> detallefacturalist) {
        this.detallefacturalist = detallefacturalist;
    }*/

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + this.id;
        hash = 79 * hash + Objects.hashCode(this.concepto);
        //hash = 79 * hash + Objects.hashCode(this.detallefacturalist);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Producto other = (Producto) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    } 
}
