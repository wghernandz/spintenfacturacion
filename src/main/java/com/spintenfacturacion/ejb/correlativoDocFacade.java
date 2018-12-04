/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spintenfacturacion.ejb;

import com.spintenfacturacion.model.correlativoDoc;
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
public class correlativoDocFacade extends AbstractFacade<correlativoDoc> implements correlativoDocFacadeLocal {

    @PersistenceContext(unitName = "com.servipinten_spintenfacturacion_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public correlativoDocFacade() {
        super(correlativoDoc.class);
    }
    
     //OBTENER correlativo inicial.
    @Override
    public int obtenerCorrInicial(int iddoc){
        List<correlativoDoc> correlativos;
        correlativoDoc correlativo=null;
      try{
        String consulta;
        consulta="SELECT c FROM correlativoDoc c where c.tipodocumento.id = ?1 ";
        Query query=em.createQuery(consulta);
        query.setParameter(1,iddoc);
        
        correlativos = query.getResultList();
        correlativo=(correlativoDoc)correlativos.get(0);
        
      }catch (Exception e){
       System.out.println(e.getMessage());
      }
      if (correlativo==null){
          return -1;
      }else{
        return correlativo.getCinicial();
      }
      
    }
    
}
