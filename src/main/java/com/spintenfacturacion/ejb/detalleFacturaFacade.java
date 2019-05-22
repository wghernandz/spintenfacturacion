/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spintenfacturacion.ejb;

import com.spintenfacturacion.model.detalleFactura;
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
public class detalleFacturaFacade extends AbstractFacade<detalleFactura> implements detalleFacturaFacadeLocal {

    @PersistenceContext(unitName = "com.servipinten_spintenfacturacion_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public detalleFacturaFacade() {
        super(detalleFactura.class);
    }
    
     //Obtener detalles de una factura en especifico
    @Override
    public List<detalleFactura> detalleDeFactura(int idfactura){
      List<detalleFactura> lista=null;
      String consulta;
      try{
        consulta="SELECT d FROM detalleFactura d WHERE d.factura.id = ?1 ORDER BY d.id ASC ";
        Query query=em.createQuery(consulta);
        query.setParameter(1, idfactura);  
        lista = (List<detalleFactura>)query.getResultList();
        
        for(int indice = 0;indice<lista.size();indice++)
        {
            System.out.println("VALOR "+indice+" "+lista.get(indice).getProducto().getConcepto());
            System.out.println("VALOR ID DE PRODUCTO "+indice+" "+lista.get(indice).getProducto().getId());
        }

        return lista;
      }catch (Exception e){
        System.out.println(e.getMessage());
        return null ;
      }
     
    }
    
}
