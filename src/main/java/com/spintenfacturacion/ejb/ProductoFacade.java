/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spintenfacturacion.ejb;

import com.spintenfacturacion.model.Producto;
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
public class ProductoFacade extends AbstractFacade<Producto> implements ProductoFacadeLocal {

    @PersistenceContext(unitName = "com.servipinten_spintenfacturacion_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProductoFacade() {
        super(Producto.class);
    }
    
    @Override
    public String obtConceptoProd(int idproducto){
       String concepto="";
       
       String sql="select p.concepto from Producto p where p.id = ?1 ";
       try{
        Query query=em.createQuery(sql);
        query.setParameter(1,idproducto);
        concepto=(String)query.getSingleResult();
        return concepto;
       }catch(Exception e){
        System.out.println(e.getMessage());
       }
       return concepto;
    }
    
    
}
