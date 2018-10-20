/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spintenfacturacion.ejb;

import com.spintenfacturacion.model.correlativoDoc;
import com.spintenfacturacion.model.tipoDocumento;
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
public class tipoDocumentoFacade extends AbstractFacade<tipoDocumento> implements tipoDocumentoFacadeLocal {

    @PersistenceContext(unitName = "com.servipinten_spintenfacturacion_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public tipoDocumentoFacade() {
        super(tipoDocumento.class);
    }
    
    //Obtener idcorrelativo segun tipo de documento y estado en uso.
    @Override
    public correlativoDoc correlativoDocUso(int tipodoc){
      correlativoDoc corr=null;

      String consulta;
      try{
        consulta="SELECT c FROM correlativoDoc c WHERE c.tipodocumento.id = ?1 AND c.estado = 'en uso'";
        Query query=em.createQuery(consulta);
        query.setParameter(1, tipodoc);
      
        List<correlativoDoc> lista= query.getResultList();
      if (!lista.isEmpty()){
          corr=lista.get(0);
      }
      }catch (Exception e){
       System.out.println(e.getMessage());
      }
      return corr;
    }
    
}
