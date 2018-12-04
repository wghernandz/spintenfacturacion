/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spintenfacturacion.model;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="cliente")
public class Cliente implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE,generator="SEQ_ID_CLIENTE")
    @SequenceGenerator(name="SEQ_ID_CLIENTE",sequenceName="seq_idcliente", allocationSize=1)
    @OneToOne
    @JoinColumn(name="idpersona")
    private Persona persona;
    @Column(name="nombrecomercial")
    private String nombrecomercial;
    @Column(name="nrc")
    private String nrc;
    @Column(name="giro")
    private String giro;
    @Column(name="diascredito")
    private BigDecimal diascredito;
    @Column(name="rangocontribuy")
    private String rangocontribuy;

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public String getNombrecomercial() {
        return nombrecomercial;
    }

    public void setNombrecomercial(String nombrecomercial) {
        this.nombrecomercial = nombrecomercial;
    }

    public String getNrc() {
        return nrc;
    }

    public void setNrc(String nrc) {
        this.nrc = nrc;
    }

    public String getGiro() {
        return giro;
    }

    public void setGiro(String giro) {
        this.giro = giro;
    }

    public BigDecimal getDiascredito() {
        return diascredito;
    }

    public void setDiascredito(BigDecimal diascredito) {
        this.diascredito = diascredito;
    }

    public String getRangocontribuy() {
        return rangocontribuy;
    }
    
    public void setRangocontribuy(String rangocontribuy) {
        this.rangocontribuy = rangocontribuy;
    }
}
