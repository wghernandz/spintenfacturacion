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
    
        //Obtener listado de documentos segun sucursal.
    @Override
    public List<tipoDocumento> docXsucursal(int idsucursal){
      List<tipoDocumento> lista=null;
      tipoDocumento tdoc=null;

      String consulta;
      try{
        consulta="SELECT t FROM tipoDocumento t WHERE t.idsucursal = ?1 ";
        Query query=em.createQuery(consulta);
        query.setParameter(1, idsucursal);
      
        lista= query.getResultList();
      if (!lista.isEmpty()){
          tdoc=lista.get(0);
      }
      }catch (Exception e){
       System.out.println(e.getMessage());
      }
      return lista;
    }
    
          //Obtener doc. para usuario serviradiadores
    @Override
    public List<tipoDocumento> userServi(){
      List<tipoDocumento> lista=null;
      tipoDocumento tdoc=null;

      String consulta;
      try{
        consulta="SELECT t FROM tipoDocumento t WHERE t.idsucursal=1 OR t.id=3 OR t.id=5 ";
        Query query=em.createQuery(consulta);
      
        lista= query.getResultList();
      if (!lista.isEmpty()){
          tdoc=lista.get(0);
      }
      }catch (Exception e){
       System.out.println(e.getMessage());
      }
      return lista;
    }
    
    
}
