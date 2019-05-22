/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spintenfacturacion.ejb;

import com.spintenfacturacion.model.Plantilla;
import com.spintenfacturacion.model.detalleFactura;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author willian
 */
@Local
public interface PlantillaFacadeLocal {

    void create(Plantilla plantilla);

    void edit(Plantilla plantilla);

    void remove(Plantilla plantilla);

    Plantilla find(Object id);

    List<Plantilla> findAll();

    List<Plantilla> findRange(int[] range);

    int count();
    
    List<detalleFactura> plantillaDetalles(int idfact, int idplantilla);
    
}
