/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spintenfacturacion.ejb;

import com.spintenfacturacion.model.detalleFactura;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author willian
 */
@Local
public interface detalleFacturaFacadeLocal {

    void create(detalleFactura detalleFactura);

    void edit(detalleFactura detalleFactura);

    void remove(detalleFactura detalleFactura);

    detalleFactura find(Object id);

    List<detalleFactura> findAll();

    List<detalleFactura> findRange(int[] range);

    int count();
    
    List<detalleFactura> detalleDeFactura(int idfactura);
    
}
