/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spintenfacturacion.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table
public class Plantilla implements Serializable{
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE,generator="SEQ_ID_PLANTILLA")
    @SequenceGenerator(name="SEQ_ID_PLANTILLA",sequenceName="seq_idplantilla", allocationSize=1)
    private int id;
    @Column(name="nombreplantilla")
    private String nombreplantilla;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreplantilla() {
        return nombreplantilla;
    }

    public void setNombreplantilla(String nombreplantilla) {
        this.nombreplantilla = nombreplantilla;
    }
    
}
