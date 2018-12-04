/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spintenfacturacion.controller;

import com.spintenfacturacion.ejb.marcaVehiculoFacadeLocal;
import com.spintenfacturacion.ejb.vehiculoModeloFacadeLocal;
import com.spintenfacturacion.model.marcaVehiculo;
import com.spintenfacturacion.model.vehiculoModelo;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class vehiculoController implements Serializable {
    @EJB
    private marcaVehiculoFacadeLocal marcavehiculoEJB;
    @EJB
    private vehiculoModeloFacadeLocal modelovehiculoEJB;
    
    //entidades
    private marcaVehiculo marcavehiculo;
    private vehiculoModelo vehiculomodelo;
    
    //listas
    private List<marcaVehiculo> marcasvehiculo;
    private List<vehiculoModelo> vehiculosmodelo;
    private List<vehiculoModelo> filteredmodelos;
    
    @PostConstruct
    public void init(){
        marcavehiculo= new marcaVehiculo();
        vehiculomodelo= new vehiculoModelo();
        marcasvehiculo= marcavehiculoEJB.findAll();  
        vehiculosmodelo= modelovehiculoEJB.findAll();
    }

    public marcaVehiculoFacadeLocal getMarcavehiculoEJB() {
        return marcavehiculoEJB;
    }

    public void setMarcavehiculoEJB(marcaVehiculoFacadeLocal marcavehiculoEJB) {
        this.marcavehiculoEJB = marcavehiculoEJB;
    }

    public vehiculoModeloFacadeLocal getModelovehiculoEJB() {
        return modelovehiculoEJB;
    }

    public void setModelovehiculoEJB(vehiculoModeloFacadeLocal modelovehiculoEJB) {
        this.modelovehiculoEJB = modelovehiculoEJB;
    }

    public marcaVehiculo getMarcavehiculo() {
        return marcavehiculo;
    }

    public void setMarcavehiculo(marcaVehiculo marcavehiculo) {
        this.marcavehiculo = marcavehiculo;
    }

    public vehiculoModelo getVehiculomodelo() {
        return vehiculomodelo;
    }

    public void setVehiculomodelo(vehiculoModelo vehiculomodelo) {
        this.vehiculomodelo = vehiculomodelo;
        this.marcavehiculo = vehiculomodelo.getMarcavehiculo();
    }

    public List<marcaVehiculo> getMarcasvehiculo() {
        return marcasvehiculo;
    }

    public void setMarcasvehiculo(List<marcaVehiculo> marcasvehiculo) {
        this.marcasvehiculo = marcasvehiculo;
    }

    public List<vehiculoModelo> getVehiculosmodelo() {
        return vehiculosmodelo;
    }

    public void setVehiculosmodelo(List<vehiculoModelo> vehiculosmodelo) {
        this.vehiculosmodelo = vehiculosmodelo;
    }

    public List<vehiculoModelo> getFilteredmodelos() {
        return filteredmodelos;
    }

    public void setFilteredmodelos(List<vehiculoModelo> filteredmodelos) {
        this.filteredmodelos = filteredmodelos;
    }
    
      
    public void guardarMarca(){
        marcavehiculoEJB.create(this.marcavehiculo);
         
        FacesContext.getCurrentInstance().addMessage(null,
         new FacesMessage("Se Guardo la marca "+this.marcavehiculo.getNombremarca())); 
        
        init();
    }
    
    public void guardarModelo(){
        if(this.marcavehiculo==null){
            FacesContext.getCurrentInstance().addMessage(null,
            new FacesMessage("Selecciona la marca"));
        
        }else{
        this.vehiculomodelo.setMarcavehiculo(this.marcavehiculo);
        
        modelovehiculoEJB.create(this.vehiculomodelo);
     
        FacesContext.getCurrentInstance().addMessage(null,
        new FacesMessage("Se Guardo el Modelo "+this.vehiculomodelo.getNombremodelo()));
        
        init();
        }
    }
    
      public void modModelo(){
        marcavehiculo=marcavehiculoEJB.find(this.marcavehiculo.getId());
        this.vehiculomodelo.setMarcavehiculo(marcavehiculo);
            modelovehiculoEJB.edit(this.vehiculomodelo);
            init();
        FacesContext.getCurrentInstance().addMessage(null,
        new FacesMessage("Modelo modificado "));

     
    }
    
    public void cancelar(){
        init();
    }
    
}
