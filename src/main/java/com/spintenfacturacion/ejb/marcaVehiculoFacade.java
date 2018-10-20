/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spintenfacturacion.ejb;

import com.spintenfacturacion.model.marcaVehiculo;
import com.spintenfacturacion.model.vehiculoModelo;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author willian
 */
@Stateless
public class marcaVehiculoFacade extends AbstractFacade<marcaVehiculo> implements marcaVehiculoFacadeLocal {

    @PersistenceContext(unitName = "com.servipinten_spintenfacturacion_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public marcaVehiculoFacade() {
        super(marcaVehiculo.class);
    }
    
    @Override
    public List<vehiculoModelo> modelosXmarca(marcaVehiculo marca){
        List<vehiculoModelo> modelos=null;
        String consulta;
        try{
            consulta= "SELECT m FROM vehiculoModelo m JOIN marcaVehiculo mar "
                    + "WHERE m.marcavehiculo.id=mar.id and mar.id= "+marca.getId();
            Query query=em.createQuery(consulta);
            //query.setParameter(1,depto.getId());
        
            modelos=query.getResultList();
              
            }
        catch(Exception e){
            throw e;
        }finally{
            //em.close();
        }
        return modelos;
    }
    
    
}
