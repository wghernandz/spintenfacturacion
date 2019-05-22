/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spintenfacturacion.ejb;

import com.spintenfacturacion.model.Producto;
import com.spintenfacturacion.model.plantillaDetalle;
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
public class plantillaDetalleFacade extends AbstractFacade<plantillaDetalle> implements plantillaDetalleFacadeLocal {

    @PersistenceContext(unitName = "com.servipinten_spintenfacturacion_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public plantillaDetalleFacade() {
        super(plantillaDetalle.class);
    }
    
    @Override
    public List<plantillaDetalle> listaXplantilla(int idplantilla){
       List<plantillaDetalle> lista=null;
       
       String sql="select pd from plantillaDetalle pd where pd.plantilla.id = ?1 order by pd.id ASC";
       try{
        Query query=em.createQuery(sql);
        query.setParameter(1,idplantilla);
        lista=query.getResultList();
        return lista;
       }catch(Exception e){
        System.out.println(e.getMessage());
       }
       return lista;
    }
    
    @Override
    public List<Producto> productosordenados(){
       List<Producto> lista=null;
       
       String sql="select p from Producto p order by p.id DESC";
       try{
        Query query=em.createQuery(sql);
        lista=query.getResultList();
        return lista;
       }catch(Exception e){
        System.out.println(e.getMessage());
       }
       return lista;
    }
    
}
