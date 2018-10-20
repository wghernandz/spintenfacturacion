/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spintenfacturacion.model;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="vehiculomodelo")
public class vehiculoModelo implements Serializable {
    
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE,generator="SEQ_ID_VEHICULOMODELO")
    @SequenceGenerator(name="SEQ_ID_VEHICULOMODELO",sequenceName="seq_idvehiculomodelo", allocationSize=1)
    private int id;
    @ManyToOne
    @JoinColumn(name="idmarca")
    private marcaVehiculo marcavehiculo;
    @Column(name="nombremodelo")
    private String nombremodelo;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public marcaVehiculo getMarcavehiculo() {
        return marcavehiculo;
    }

    public void setMarcavehiculo(marcaVehiculo marcavehiculo) {
        this.marcavehiculo = marcavehiculo;
    }

    public String getNombremodelo() {
        return nombremodelo;
    }

    public void setNombremodelo(String nombremodelo) {
        this.nombremodelo = nombremodelo;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + this.id;
        hash = 59 * hash + Objects.hashCode(this.marcavehiculo);
        hash = 59 * hash + Objects.hashCode(this.nombremodelo);
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
        final vehiculoModelo other = (vehiculoModelo) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.nombremodelo, other.nombremodelo)) {
            return false;
        }
        if (!Objects.equals(this.marcavehiculo, other.marcavehiculo)) {
            return false;
        }
        return true;
    }
    
}
