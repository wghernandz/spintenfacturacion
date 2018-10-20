/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spintenfacturacion.ejb;

import com.spintenfacturacion.model.correlativoDoc;
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
        consulta="SELECT d FROM detalleFactura d WHERE d.factura.id = ?1 ";
        Query query=em.createQuery(consulta);
        query.setParameter(1, idfactura);  
        lista = (List<detalleFactura>)query.getResultList();
    
      if(lista.size()>2){
            System.out.println("producto Origen0"+lista.get(0).getProducto().getId());
            System.out.println("producto Origen1"+lista.get(1).getProducto().getConcepto());
            System.out.println("producto preciounitario0 "+lista.get(0).getPreciounitario());
            System.out.println("producto preciounitario1 "+lista.get(1).getPreciounitario());
            }
        
      }catch (Exception e){
       System.out.println(e.getMessage());
      }
      return lista;
    }
    
}
