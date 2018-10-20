/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spintenfacturacion.ejb;

import com.spintenfacturacion.model.marcaVehiculo;
import com.spintenfacturacion.model.vehiculoModelo;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author willian
 */
@Local
public interface marcaVehiculoFacadeLocal {

    void create(marcaVehiculo marcaVehiculo);

    void edit(marcaVehiculo marcaVehiculo);

    void remove(marcaVehiculo marcaVehiculo);

    marcaVehiculo find(Object id);

    List<marcaVehiculo> findAll();

    List<marcaVehiculo> findRange(int[] range);

    int count();
    
    List<vehiculoModelo> modelosXmarca(marcaVehiculo marca);
    
}
