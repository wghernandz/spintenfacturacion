/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spintenfacturacion.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;

@Entity
@Table(name="correlativodoc")
public class correlativoDoc implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE,generator="SEQ_ID_CORRELATIVODOC")
    @SequenceGenerator(name="SEQ_ID_CORRELATIVO",sequenceName="seq_idcorrelativo", allocationSize=1)
    private int id;
    @OneToOne
    @JoinColumn(name="iddoc")
    private tipoDocumento tipodocumento;
    @Column(name="cinicial")
    private int cinicial;
    @Column(name="cfinal")
    private int cfinal;
    @Column(name="fautorizacion")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fautorizacion;
    @Column(name="estado")
    private String estado;
    @Column(name="nresolucion")
    private String nresolucion;
    @Column(name="serieautini")
    private String serieautini;
    @Column(name="serieautfin")
    private String serieautfin;
    @Column(name="ultimo")
    private int ultimo;
    @Column(name="iniciaren")
    private int iniciaren;
 
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public tipoDocumento getTipodocumento() {
        return tipodocumento;
    }

    public void setTipodocumento(tipoDocumento tipodocumento) {
        this.tipodocumento = tipodocumento;
    }

    public int getCinicial() {
        return cinicial;
    }

    public void setCinicial(int cinicial) {
        this.cinicial = cinicial;
    }

    public int getCfinal() {
        return cfinal;
    }

    public void setCfinal(int cfinal) {
        this.cfinal = cfinal;
    }

    public Date getFautorizacion() {
        return fautorizacion;
    }

    public void setFautorizacion(Date fautorizacion) {
        this.fautorizacion = fautorizacion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getNresolucion() {
        return nresolucion;
    }

    public void setNresolucion(String nresolucion) {
        this.nresolucion = nresolucion;
    }

    public String getSerieautini() {
        return serieautini;
    }

    public void setSerieautini(String serieautini) {
        this.serieautini = serieautini;
    }

    public String getSerieautfin() {
        return serieautfin;
    }

    public void setSerieautfin(String serieautfin) {
        this.serieautfin = serieautfin;
    }

    public int getUltimo() {
        return ultimo;
    }

    public void setUltimo(int ultimo) {
        this.ultimo = ultimo;
    }

    public int getIniciaren() {
        return iniciaren;
    }

    public void setIniciaren(int iniciaren) {
        this.iniciaren = iniciaren;
    }
}
