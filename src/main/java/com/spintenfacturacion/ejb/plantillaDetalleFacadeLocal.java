/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spintenfacturacion.ejb;

import com.spintenfacturacion.model.Producto;
import com.spintenfacturacion.model.plantillaDetalle;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author willian
 */
@Local
public interface plantillaDetalleFacadeLocal {

    void create(plantillaDetalle plantillaDetalle);

    void edit(plantillaDetalle plantillaDetalle);

    void remove(plantillaDetalle plantillaDetalle);

    plantillaDetalle find(Object id);

    List<plantillaDetalle> findAll();

    List<plantillaDetalle> findRange(int[] range);

    int count();
    
    public List<plantillaDetalle> listaXplantilla(int idplantilla);
    
    public List<Producto> productosordenados();
    
}
