/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spintenfacturacion.ejb;

import com.spintenfacturacion.model.vehiculoModelo;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author willian
 */
@Local
public interface vehiculoModeloFacadeLocal {

    void create(vehiculoModelo vehiculoModelo);

    void edit(vehiculoModelo vehiculoModelo);

    void remove(vehiculoModelo vehiculoModelo);

    vehiculoModelo find(Object id);

    List<vehiculoModelo> findAll();

    List<vehiculoModelo> findRange(int[] range);

    int count();
    
}
