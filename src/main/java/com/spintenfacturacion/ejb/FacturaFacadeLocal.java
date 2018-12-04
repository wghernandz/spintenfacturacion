/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spintenfacturacion.ejb;

import com.spintenfacturacion.model.Factura;
import com.spintenfacturacion.model.detalleFactura;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author willian
 */
@Local
public interface FacturaFacadeLocal {

    void create(Factura factura);

    void edit(Factura factura);

    void remove(Factura factura);

    Factura find(Object id);

    List<Factura> findAll();

    List<Factura> findRange(int[] range);

    int count();
    
    int obtenerUltimocorr(int idtipodoc);
    
    List<detalleFactura> imprimirDetalle(int idfactura);
    
    Factura facturaEnproceso();
    
    List<Factura> facturaRangofecha(Date finicial,Date ffinal);
    
}
