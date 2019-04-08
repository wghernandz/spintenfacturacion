/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spintenfacturacion.controller;

import com.spintenfacturacion.ejb.correlativoDocFacadeLocal;
import com.spintenfacturacion.ejb.tipoDocumentoFacadeLocal;
import com.spintenfacturacion.model.correlativoDoc;
import com.spintenfacturacion.model.tipoDocumento;
import java.io.Serializable;
import com.spintenfacturacion.utileria.metodosCadena;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.event.Event;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author willian
 */
@Named
@ViewScoped
public class correlativoController implements Serializable{
    @EJB
    private correlativoDocFacadeLocal correlativodocEJB;
    @EJB
    private tipoDocumentoFacadeLocal tipodocumentoEJB;
    
    private correlativoDoc correlativodoc;
    private tipoDocumento tipodocumento;
    private List<correlativoDoc> correlativos;
    private List<tipoDocumento> tiposdoc;
    private boolean enmodificacion;
    
    @PostConstruct
    public void init(){
        correlativodoc= new correlativoDoc();
        tipodocumento= new tipoDocumento();
        correlativos=correlativodocEJB.findAll();
        tiposdoc=tipodocumentoEJB.findAll();
    }

    public correlativoDocFacadeLocal getCorrelativodocEJB() {
        return correlativodocEJB;
    }

    public void setCorrelativodocEJB(correlativoDocFacadeLocal correlativodocEJB) {
        this.correlativodocEJB = correlativodocEJB;
    }

    public correlativoDoc getCorrelativodoc() {
        return correlativodoc;
    }

    public void setCorrelativodoc(correlativoDoc correlativodoc) {
        this.correlativodoc = correlativodoc;
    }

    public List<correlativoDoc> getCorrelativos() {
        return correlativos;
    }

    public void setCorrelativos(List<correlativoDoc> correlativos) {
        this.correlativos = correlativos;
    }

    public boolean isEnmodificacion() {
        return enmodificacion;
    }

    public void setEnmodificacion(boolean enmodificacion) {
        this.enmodificacion = enmodificacion;
    }
    
    
    public String guardarCorrelativo(){
        correlativodoc.setTipodocumento(tipodocumento);
        correlativodoc.setEstado("en uso");
        int corrini=metodosCadena.obtenerSoloCorrelativo(this.correlativodoc.getSerieautini());
        int corrfin=metodosCadena.obtenerSoloCorrelativo(this.correlativodoc.getSerieautfin());
        
        if (corrini==0 & corrfin==0){
                 FacesContext.getCurrentInstance().addMessage(
                null, new FacesMessage("VERIFIQUE SERIE AUTORIZADA"));
 
        FacesContext.getCurrentInstance()
            .getExternalContext()
            .getFlash().setKeepMessages(true);
        
            UIViewRoot view = FacesContext.getCurrentInstance().getViewRoot();
            return view.getViewId() + "?faces-redirect=true&includeViewParams=true"; 
    
        }else{
            correlativodoc.setCinicial(corrini);
            correlativodoc.setCfinal(corrfin);
        
            correlativodocEJB.create(this.correlativodoc);
        
            FacesContext.getCurrentInstance().addMessage(
            null, new FacesMessage("CORRELATIVO INGRESADO CON EXITO"));
 
            FacesContext.getCurrentInstance()
                .getExternalContext()
                .getFlash().setKeepMessages(true);
        
                UIViewRoot view = FacesContext.getCurrentInstance().getViewRoot();
                return view.getViewId() + "?faces-redirect=true&includeViewParams=true";
        }
    }
    
    public String modificarCorrelativo(){
        int corrini=metodosCadena.obtenerSoloCorrelativo(this.correlativodoc.getSerieautini());
        int corrfin=metodosCadena.obtenerSoloCorrelativo(this.correlativodoc.getSerieautfin());
        correlativodoc.setCinicial(corrini);
        correlativodoc.setCfinal(corrfin);
        correlativodoc.setTipodocumento(tipodocumento);
        correlativodoc.setEstado("en uso");
        
         correlativodocEJB.edit(correlativodoc);
         
         FacesContext.getCurrentInstance().addMessage(
         null, new FacesMessage("CORRELATIVO MODIFICADO CON EXITO"));
 
         FacesContext.getCurrentInstance()
            .getExternalContext()
            .getFlash().setKeepMessages(true);
        
            UIViewRoot view = FacesContext.getCurrentInstance().getViewRoot();
            return view.getViewId() + "?faces-redirect=true&includeViewParams=true";   
    }
    
    public String eliminarCorrelativo(){
        this.correlativodoc=correlativodocEJB.find(correlativodoc.getId());
        
        if (this.correlativodoc.getEstado().equals("en uso")==true){
             FacesContext.getCurrentInstance().addMessage(
             null, new FacesMessage("CORRELATIVO EN USO NO SE PUEDE ELIMINAR!!!"));
 
            FacesContext.getCurrentInstance()
            .getExternalContext()
            .getFlash().setKeepMessages(true);
        
            UIViewRoot view = FacesContext.getCurrentInstance().getViewRoot();
            return view.getViewId() + "?faces-redirect=true&includeViewParams=true"; 
            
        }else{
        
        correlativodocEJB.remove(this.correlativodoc);
        
         FacesContext.getCurrentInstance().addMessage(
         null, new FacesMessage("CORRELATIVO ELIMINADO CON EXITO"));
 
         FacesContext.getCurrentInstance()
            .getExternalContext()
            .getFlash().setKeepMessages(true);
        
            UIViewRoot view = FacesContext.getCurrentInstance().getViewRoot();
            return view.getViewId() + "?faces-redirect=true&includeViewParams=true";
        }
    }
    
    public void onRowEdit(Event event, correlativoDoc correlativo){
        this.setCorrelativodoc(correlativo);
        this.setTipodocumento(correlativo.getTipodocumento());
        this.enmodificacion=true;
    }

    public tipoDocumentoFacadeLocal getTipodocumentoEJB() {
        return tipodocumentoEJB;
    }

    public void setTipodocumentoEJB(tipoDocumentoFacadeLocal tipodocumentoEJB) {
        this.tipodocumentoEJB = tipodocumentoEJB;
    }

    public tipoDocumento getTipodocumento() {
        return tipodocumento;
    }

    public void setTipodocumento(tipoDocumento tipodocumento) {
        this.tipodocumento = tipodocumento;
    }

    public List<tipoDocumento> getTiposdoc() {
        return tiposdoc;
    }

    public void setTiposdoc(List<tipoDocumento> tiposdoc) {
        this.tiposdoc = tiposdoc;
    }
    
}
