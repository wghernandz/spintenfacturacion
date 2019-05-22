/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spintenfacturacion.ejb;

import com.spintenfacturacion.model.Plantilla;
import com.spintenfacturacion.model.detalleFactura;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.StoredProcedureQuery;

/**
 *
 * @author willian
 */
@Stateless
public class PlantillaFacade extends AbstractFacade<Plantilla> implements PlantillaFacadeLocal {

    @PersistenceContext(unitName = "com.servipinten_spintenfacturacion_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PlantillaFacade() {
        super(Plantilla.class);
    }
    
    //obtener plantilla de detalles facturas.
    @Override
    public List<detalleFactura> plantillaDetalles(int idfact, int idplantilla){
      List<detalleFactura> lista=null;
      String consulta;
      
      System.out.println("VALORES proc"+idfact+"  "+idplantilla);
      //EJECUTAR PROCEDIMIENTO ALMACENADO
      StoredProcedureQuery storedProcedure=em.createStoredProcedureQuery("plantillaFactura");
      storedProcedure.registerStoredProcedureParameter("idfact", Integer.class, ParameterMode.IN);
      storedProcedure.registerStoredProcedureParameter("idp", Integer.class, ParameterMode.IN);
      storedProcedure.setParameter("idfact", idfact);
      storedProcedure.setParameter("idp",idplantilla);
      storedProcedure.execute();
      //OBTENER DATOS DE TABLA GENERADA POR PROCEDIMIENTO ALMACENADO.
      try{
        consulta="SELECT d FROM detalleFactura d WHERE d.factura.id="+idfact+" ORDER BY d.id ASC";
        Query query=em.createQuery(consulta);
        
        lista = query.getResultList();
    
      }catch (Exception e){
        System.out.println(e.getMessage());
      }
      return lista;
    }  
}
