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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="plantilladetalle")
public class plantillaDetalle implements Serializable{
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE,generator="SEQ_ID_PLANTILLADETALLE")
    @SequenceGenerator(name="SEQ_ID_PLANTILLADETALLE",sequenceName="seq_idplantilladetalle",allocationSize=1)
    private int id;
    @Column(name="idconcepto")
    private int idconcepto;
    @ManyToOne
    @JoinColumn(name="idplantilla")
    private Plantilla plantilla;
   
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdconcepto() {
        return idconcepto;
    }

    public void setIdconcepto(int idconcepto) {
        this.idconcepto = idconcepto;
    }

    public Plantilla getPlantilla() {
        return plantilla;
    }

    public void setPlantilla(Plantilla plantilla) {
        this.plantilla = plantilla;
    }
    
}
