/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spintenfacturacion.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;

@Entity
@Table(name="factura")
public class Factura implements Serializable {
    
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE,generator="SEQ_ID_FACTURA")
    @SequenceGenerator(name="SEQ_ID_FACTURA",sequenceName="seq_idfactura", allocationSize=1)
    private int id;
    @ManyToOne
    @JoinColumn(name="idcorrelativo")
    private correlativoDoc correlativodoc;
    @ManyToOne
    @JoinColumn(name="idpersona")
    private Usuario usuario;
    @ManyToOne
    @JoinColumn(name="clienteidpersona")
    private Cliente cliente;
    @Temporal(javax.persistence.TemporalType.DATE)
    @Column(name="fecha")
    private Date fecha;
    @Column(name="condpago")
    private String condpago;
    @Column(name="totalventa")
    private BigDecimal totalventa;
    @Column(name="retencion")
    private BigDecimal retencion;
    @Column(name="ordencompra")
    private String ordencompra;
    @Column(name="numsiniestro")
    private String numsiniestro;
    @Column(name="tventaenletras")
    private String tventaenletras;
    @Column(name="placa")
    private String placa;
    @OneToOne
    @JoinColumn(name="idmodelo")
    private vehiculoModelo vehiculomodelo;
    @Column(name="correlativo")
    private int correlativo;
    @Column(name="estado")
    private String estado;
    @Column(name="acumventans")
    private BigDecimal acumventans;
    @Column(name="acumventaex")
    private BigDecimal acumventaex;
    @Column(name="acumventagra")
    private BigDecimal acumventagra;
    @Column(name="razanula")
    private String razanula;
    @Column(name="idccfajustado")
    private int idccfajustado;
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public correlativoDoc getCorrelativodoc() {
        return correlativodoc;
    }

    public void setCorrelativodoc(correlativoDoc correlativodoc) {
        this.correlativodoc = correlativodoc;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getCondpago() {
        return condpago;
    }

    public void setCondpago(String condpago) {
        this.condpago = condpago;
    }

    public BigDecimal getTotalventa() {
        return totalventa;
    }

    public void setTotalventa(BigDecimal totalventa) {
        this.totalventa = totalventa;
    }

    public BigDecimal getRetencion() {
        return retencion;
    }

    public void setRetencion(BigDecimal retencion) {
        this.retencion = retencion;
    }

    public String getOrdencompra() {
        return ordencompra;
    }

    public void setOrdencompra(String ordencompra) {
        this.ordencompra = ordencompra;
    }

    public String getNumsiniestro() {
        return numsiniestro;
    }

    public void setNumsiniestro(String numsiniestro) {
        this.numsiniestro = numsiniestro;
    }

    public String getTventaenletras() {
        return tventaenletras;
    }

    public void setTventaenletras(String tventaenletras) {
        this.tventaenletras = tventaenletras;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public vehiculoModelo getVehiculomodelo() {
        return vehiculomodelo;
    }

    public void setVehiculomodelo(vehiculoModelo vehiculomodelo) {
        this.vehiculomodelo = vehiculomodelo;
    }

    public int getCorrelativo() {
        return correlativo;
    }

    public void setCorrelativo(int correlativo) {
        this.correlativo = correlativo;
    }
    
    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public BigDecimal getAcumventans() {
        return acumventans;
    }

    public void setAcumventans(BigDecimal acumventans) {
        this.acumventans = acumventans;
    }

    public BigDecimal getAcumventaex() {
        return acumventaex;
    }

    public void setAcumventaex(BigDecimal acumventaex) {
        this.acumventaex = acumventaex;
    }

    public BigDecimal getAcumventagra() {
        return acumventagra;
    }

    public void setAcumventagra(BigDecimal acumventagra) {
        this.acumventagra = acumventagra;
    }

    public String getRazanula() {
        return razanula;
    }

    public void setRazanula(String razanula) {
        this.razanula = razanula;
    }

    public int getIdccfajustado() {
        return idccfajustado;
    }

    public void setIdccfajustado(int idccfajustado) {
        this.idccfajustado = idccfajustado;
    }
    
}
