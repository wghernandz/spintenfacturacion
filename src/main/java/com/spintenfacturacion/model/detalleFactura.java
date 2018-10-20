/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spintenfacturacion.model;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="detallefactura")
public class detalleFactura implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE,generator="SEQ_ID_DETALLEFACTURA")
    @SequenceGenerator(name="SEQ_ID_DETALLEFACTURA",sequenceName="seq_iddetallefactura", allocationSize=1)
    private int id;
    @ManyToOne
    @JoinColumn(name="idproducto")
    private Producto producto;
    @ManyToOne
    @JoinColumn(name="idmodelo")
    private vehiculoModelo vehiculomodelo;
    @ManyToOne
    @JoinColumn(name="idfactura")
    private Factura factura;
    @Column(name="numequipo")
    private String numequipo;
    @Column(name="placa")
    private String placa;
    @Column(name="vneto")
    private BigDecimal vneto;
    @Column(name="vexento")
    private BigDecimal vexento;
    @Column(name="vnogravado")
    private BigDecimal vnogravado;
    @Column(name="cantidad")
    private BigDecimal cantidad;
    @Column(name="preciounitario")
    private BigDecimal preciounitario;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public Producto getProducto() {
        return producto;
    }
    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public vehiculoModelo getVehiculomodelo() {
        return vehiculomodelo;
    }

    public void setVehiculomodelo(vehiculoModelo vehiculomodelo) {
        this.vehiculomodelo = vehiculomodelo;
    }

    public Factura getFactura() {
        return factura;
    }
    public void setFactura(Factura factura) {
        this.factura = factura;
    }
    public String getNumequipo() {
        return numequipo;
    }
    public void setNumequipo(String numequipo) {
        this.numequipo = numequipo;
    }
    public String getPlaca() {
        return placa;
    }
    public void setPlaca(String placa) {
        this.placa = placa;
    }
    public BigDecimal getVneto() {
        return vneto;
    }
    public void setVneto(BigDecimal vneto) {
        this.vneto = vneto;
    }
    public BigDecimal getVexento() {
        return vexento;
    }
    public void setVexento(BigDecimal vexento) {
        this.vexento = vexento;
    }
    public BigDecimal getVnogravado() {
        return vnogravado;
    }
    public void setVnogravado(BigDecimal vnogravado) {
        this.vnogravado = vnogravado;
    }

    public BigDecimal getCantidad() {
        return cantidad;
    }

    public void setCantidad(BigDecimal cantidad) {
        this.cantidad = cantidad;
    }

    public BigDecimal getPreciounitario() {
        return preciounitario;
    }

    public void setPreciounitario(BigDecimal preciounitario) {
        this.preciounitario = preciounitario;
    }   
}
