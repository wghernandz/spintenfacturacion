/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spintenfacturacion.controller;

import com.spintenfacturacion.ejb.PlantillaFacadeLocal;
import com.spintenfacturacion.ejb.ProductoFacadeLocal;
import com.spintenfacturacion.ejb.plantillaDetalleFacadeLocal;
import com.spintenfacturacion.model.Plantilla;
import com.spintenfacturacion.model.Producto;
import com.spintenfacturacion.model.plantillaDetalle;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class plantillaDetalleController implements Serializable {
    @EJB
    private ProductoFacadeLocal productoEJB;
    @EJB
    private plantillaDetalleFacadeLocal plantillaDetalleEJB;
    @EJB
    private PlantillaFacadeLocal plantillaEJB;
    
    private Producto producto;
    private plantillaDetalle plantilladetalle;
    private Plantilla plantilla;
    
    private List<Producto> productos;
    private List<Plantilla> plantillas;
    private List<plantillaDetalle> plantillasdetalle;
    
    private boolean mostrardetalle;
    private boolean agregar;
    @PostConstruct
    public void init(){
        producto = new Producto();
        plantilla = new Plantilla();
        productos = plantillaDetalleEJB.productosordenados();
        plantillas = plantillaEJB.findAll();
        plantilladetalle = new plantillaDetalle();
        mostrardetalle=false;
    }

    public ProductoFacadeLocal getProductoEJB() {
        return productoEJB;
    }

    public void setProductoEJB(ProductoFacadeLocal productoEJB) {
        this.productoEJB = productoEJB;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

    public plantillaDetalleFacadeLocal getPlantillaDetalleEJB() {
        return plantillaDetalleEJB;
    }

    public void setPlantillaDetalleEJB(plantillaDetalleFacadeLocal plantillaDetalleEJB) {
        this.plantillaDetalleEJB = plantillaDetalleEJB;
    }

    public plantillaDetalle getPlantilladetalle() {
        return plantilladetalle;
    }

    public void setPlantilladetalle(plantillaDetalle plantilladetalle) {
        this.plantilladetalle = plantilladetalle;
    }

    public PlantillaFacadeLocal getPlantillaEJB() {
        return plantillaEJB;
    }

    public void setPlantillaEJB(PlantillaFacadeLocal plantillaEJB) {
        this.plantillaEJB = plantillaEJB;
    }

    public Plantilla getPlantilla() {
        return plantilla;
    }

    public void setPlantilla(Plantilla plantilla) {
        this.plantilla = plantilla;
        //tambien obtener el detalle de la plantilla
        this.plantillasdetalle=plantillaDetalleEJB.listaXplantilla(this.plantilla.getId());
        this.productos=plantillaDetalleEJB.productosordenados();
        //producto=new Producto();
        this.mostrardetalle=true;
    }

    public List<Plantilla> getPlantillas() {
        return plantillas;
    }

    public void setPlantillas(List<Plantilla> plantillas) {
        this.plantillas = plantillas;
    }

    public List<plantillaDetalle> getPlantillasdetalle() {
        return plantillasdetalle;
    }

    public void setPlantillasdetalle(List<plantillaDetalle> plantillasdetalle) {
        this.plantillasdetalle = plantillasdetalle;
    }

    public boolean isMostrardetalle() {
        return mostrardetalle;
    }

    public void setMostrardetalle(boolean mostrardetalle) {
        this.mostrardetalle = mostrardetalle;
    }

    public boolean isAgregar() {
        return agregar;
    }

    public void setAgregar(boolean agregar) {
        this.agregar = agregar;
    }
    
    //guardar nombre de servicio si no existe
     public void guardarServicio(){
         productoEJB.create(producto);
         productos=plantillaDetalleEJB.productosordenados();
         producto=new Producto();
     }
     //guardar nombre de plantilla, al inicio vacia.
     public void guardarNombreplantilla(){
         plantillaEJB.create(plantilla);
         this.plantillas=plantillaEJB.findAll();
         this.plantilla=new Plantilla();
     }
     
     public void eliminarPlantilla(Plantilla plantillae){
         plantillaEJB.remove(plantillae);
         this.plantillas=plantillaEJB.findAll();
     }
     
     public void agregarConcepto(){
         this.plantilladetalle.setPlantilla(this.plantilla);
         this.plantilladetalle.setIdconcepto(this.producto.getId());
         plantillaDetalleEJB.create(this.plantilladetalle);
         plantilladetalle=new plantillaDetalle();
         this.plantillasdetalle=plantillaDetalleEJB.listaXplantilla(this.plantilla.getId());
         this.agregar=true;
         this.producto=new Producto();
    }
     
    public void nuevoproducto(){
        producto=new Producto();
    }
     
    public void eliminarDetalle(plantillaDetalle pd){
        plantillaDetalleEJB.remove(pd);
        this.plantillasdetalle=plantillaDetalleEJB.listaXplantilla(pd.getPlantilla().getId());
    }
    
    public void obtenerListadetalle(){
        this.plantillasdetalle=plantillaDetalleEJB.listaXplantilla(plantilla.getId());
    }
    
}
