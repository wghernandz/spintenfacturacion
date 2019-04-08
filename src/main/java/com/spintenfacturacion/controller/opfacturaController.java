/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spintenfacturacion.controller;

import com.spintenfacturacion.ejb.FacturaFacadeLocal;
import com.spintenfacturacion.ejb.tipoDocumentoFacadeLocal;
import com.spintenfacturacion.model.Factura;
import com.spintenfacturacion.model.tipoDocumento;
import com.spintenfacturacion.utileria.restarAnio;
import java.io.Serializable;
import java.math.BigDecimal;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class opfacturaController implements Serializable {
    @EJB
    private FacturaFacadeLocal facturaEJB;
    @EJB
    private tipoDocumentoFacadeLocal tipodocumentoEJB;
    
    private Factura factura;
    private List<Factura> facturas;
    private List<Factura> facturasFiltered;
    private List<tipoDocumento> listatipodocumento;
    
    private BigDecimal iva;
    private String estado;
    @PostConstruct
    public void init(){
        factura = new Factura();
        listatipodocumento=tipodocumentoEJB.findAll();
        //obtiene listado de facturas maximo un año atras
        facturas= facturaEJB.facturaRangofecha(restarAnio.restarAnio(new Date(), -1), new Date());
        iva = new BigDecimal("0.13");
    }

    public FacturaFacadeLocal getFacturaEJB() {
        return facturaEJB;
    }

    public void setFacturaEJB(FacturaFacadeLocal facturaEJB) {
        this.facturaEJB = facturaEJB;
    }

    public Factura getFactura() {
        return factura;
    }

    public void setFactura(Factura factura) {
        this.factura = factura;
        this.estado= this.factura.getEstado();
        System.out.println("ESTADO "+this.estado);
    }

    public List<Factura> getFacturas() {
        return facturas;
    }

    public void setFacturas(List<Factura> facturas) {
        this.facturas = facturas;
    }

    public List<Factura> getFacturasFiltered() {
        return facturasFiltered;
    }

    public void setFacturasFiltered(List<Factura> facturasFiltered) {
        this.facturasFiltered = facturasFiltered;
    }

    public BigDecimal getIva() {
        return iva;
    }

    public void setIva(BigDecimal iva) {
        this.iva = iva;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public tipoDocumentoFacadeLocal getTipodocumentoEJB() {
        return tipodocumentoEJB;
    }

    public void setTipodocumentoEJB(tipoDocumentoFacadeLocal tipodocumentoEJB) {
        this.tipodocumentoEJB = tipodocumentoEJB;
    }

    public List<tipoDocumento> getListatipodocumento() {
        return listatipodocumento;
    }

    public void setListatipodocumento(List<tipoDocumento> listatipodocumento) {
        this.listatipodocumento = listatipodocumento;
    }
    
    public void anularFactura(){
        this.factura.setEstado("Anulada");
        facturaEJB.edit(factura);
        
        FacesContext.getCurrentInstance().addMessage(null,
        new FacesMessage("Se actualizo documento "+this.factura.getCorrelativo() ));
        init();
    }
    
    public void desanularFactura(){
        this.factura.setEstado("Finalizado");
        this.factura.setRazanula(null);
        facturaEJB.edit(factura);
        
        FacesContext.getCurrentInstance().addMessage(null,
        new FacesMessage("Se actualizo documento "+this.factura.getCorrelativo() ));
        init();
    }
    
    public String getDateAsString(Date date) {
        Format formatter = new SimpleDateFormat("dd/MM/yyyy");
        return formatter.format(date);
    }
    
    public String ncnd(int idfact){

        Factura fact;
        fact=facturaEJB.find(idfact);
        String sucursal;

        if(fact.getCorrelativodoc().getTipodocumento().getIdsucursal()==1){
            sucursal="Servirad";
        }else{
            sucursal="Servipint";
        }
        return  sucursal+" "+fact.getCorrelativodoc().getTipodocumento().getCodigo()+" "+fact.getCorrelativo();
    }
    
    
     public String factNCND(int idfact){
        Factura fact;
        fact=facturaEJB.facturaConncnd(idfact);
        String sucursal;
        if (fact!=null){
            if(fact.getCorrelativodoc().getTipodocumento().getIdsucursal()==1){
                sucursal="Servirad";
            }else{
                sucursal="Servipint";
            }
            return  sucursal+" "+fact.getCorrelativodoc().getTipodocumento().getCodigo()+" "+fact.getCorrelativo();
        }else{
            return "";
        }
        
    }
     
}
