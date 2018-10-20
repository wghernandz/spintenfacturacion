/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spintenfacturacion.controller;

import com.spintenfacturacion.ejb.correlativoDocFacadeLocal;
import com.spintenfacturacion.model.correlativoDoc;
import java.io.Serializable;
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
    
    private correlativoDoc correlativodoc;
    
    private List<correlativoDoc> correlativos;
    
    private boolean enmodificacion;
    
    @PostConstruct
    public void init(){
        correlativodoc= new correlativoDoc();
        correlativos=correlativodocEJB.findAll();
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
        correlativodoc.setEstado("en uso");
        correlativodocEJB.create(correlativodoc);
        
         FacesContext.getCurrentInstance().addMessage(
        null, new FacesMessage("CORRELATIVO INGRESADO CON EXITO"));
 
        FacesContext.getCurrentInstance()
            .getExternalContext()
            .getFlash().setKeepMessages(true);
        
            UIViewRoot view = FacesContext.getCurrentInstance().getViewRoot();
            return view.getViewId() + "?faces-redirect=true&includeViewParams=true"; 
    }
    
    public String modificarCorrelativo(){
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
        correlativodocEJB.remove(this.correlativodoc);
        
         FacesContext.getCurrentInstance().addMessage(
         null, new FacesMessage("CORRELATIVO ELIMINADO CON EXITO"));
 
         FacesContext.getCurrentInstance()
            .getExternalContext()
            .getFlash().setKeepMessages(true);
        
            UIViewRoot view = FacesContext.getCurrentInstance().getViewRoot();
            return view.getViewId() + "?faces-redirect=true&includeViewParams=true"; 
    }
    
    public void onRowEdit(Event event, correlativoDoc correlativo){
        this.setCorrelativodoc(correlativo);
        this.enmodificacion=true;
    }
}
