/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spintenfacturacion.controller;

import com.spintenfacturacion.ejb.detalleFacturaFacadeLocal;
import com.spintenfacturacion.ejb.tipoDocumentoFacadeLocal;
import com.spintenfacturacion.model.detalleFactura;
import com.spintenfacturacion.model.tipoDocumento;
import java.io.Serializable;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class detalleController implements Serializable {
    @EJB
    private detalleFacturaFacadeLocal detallefacturaEJB;
    @EJB
    private tipoDocumentoFacadeLocal tipodocumentoEJB;
    
    private List<detalleFactura> detallesfactura;
    private List<detalleFactura> detallesfiltered;
    private List<tipoDocumento> listatipodocumento;
            
    private detalleFactura detallefactura;
    private tipoDocumento tipodocumento;
    
    @PostConstruct
    public void init(){
        detallefactura=new detalleFactura();
        detallesfactura=detallefacturaEJB.findAll();
        listatipodocumento=tipodocumentoEJB.findAll();  
    }

    public detalleFacturaFacadeLocal getDetallefacturaEJB() {
        return detallefacturaEJB;
    }

    public void setDetallefacturaEJB(detalleFacturaFacadeLocal detallefacturaEJB) {
        this.detallefacturaEJB = detallefacturaEJB;
    }

    public List<detalleFactura> getDetallesfactura() {
        return detallesfactura;
    }

    public void setDetallesfactura(List<detalleFactura> detallesfactura) {
        this.detallesfactura = detallesfactura;
    }

    public detalleFactura getDetallefactura() {
        return detallefactura;
    }

    public void setDetallefactura(detalleFactura detallefactura) {
        this.detallefactura = detallefactura;
    }

    public List<detalleFactura> getDetallesfiltered() {
        return detallesfiltered;
    }

    public void setDetallesfiltered(List<detalleFactura> detallesfiltered) {
        this.detallesfiltered = detallesfiltered;
    }

    public List<tipoDocumento> getListatipodocumento() {
        return listatipodocumento;
    }

    public void setListatipodocumento(List<tipoDocumento> listatipodocumento) {
        this.listatipodocumento = listatipodocumento;
    }

    public tipoDocumento getTipodocumento() {
        return tipodocumento;
    }

    public void setTipodocumento(tipoDocumento tipodocumento) {
        this.tipodocumento = tipodocumento;
    }
    
    public String getDateAsString(Date date) {
    Format formatter = new SimpleDateFormat("dd/MM/yyyy");
    return formatter.format(date);
    }
}
