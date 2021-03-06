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
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.StoredProcedureQuery;

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
    
    //OBTENER correlativo segun tipo de documento y estado
    @Override
    public correlativoDoc corrdocTipoDoc(int iddoc){
       List<correlativoDoc> correlativos;
       correlativoDoc correlativo=null;
       
       try{
        String consulta;
        consulta=" SELECT c FROM correlativoDoc c where c.tipodocumento.id = ?1 and c.estado='en uso' ";
        Query query=em.createQuery(consulta);
        query.setParameter(1,iddoc);
        
        correlativos = query.getResultList();
        correlativo=(correlativoDoc)correlativos.get(0);
        
      }catch (Exception e){
       System.out.println(e.getMessage());
      }
       return correlativo;
    }
    
       //actualizar estado de correlativo documento
    @Override
    public correlativoDoc actEstadoCorr(int idcorr){
      //boolean resultado;
      correlativoDoc correlativo = null;
      
      try{
      StoredProcedureQuery storedProcedure=em.createStoredProcedureQuery("actEstadocorr");
      storedProcedure.registerStoredProcedureParameter("idcorr",int.class ,ParameterMode.IN);
      storedProcedure.setParameter("idcorr", idcorr);
      storedProcedure.execute();
        //resultado=true;
      System.out.println("procedimiento ejecutado");
      }catch(Exception e){
        //resultado=false;
      }
      
      
        try{
        String consulta="SELECT c FROM correlativoDoc c WHERE c.id = ?1 ";
        Query query=em.createQuery(consulta);
        query.setParameter(1,idcorr);
        
        correlativo = (correlativoDoc)query.getSingleResult();
    
      }catch (Exception e){
        System.out.println(e.getMessage());
      }
      return correlativo;
        //return resultado;
    }
    
       //VERIFICAR SI UN CORRELATIVO TIENE FACTURAS ASOCIADAS
    @Override
    public long corrFactura(int idcorr){
       long cantidad=0;
       
       try{
        String consulta;
        consulta=" SELECT count(f) FROM Factura f where f.correlativodoc.id = ?1";
        Query query=em.createQuery(consulta);
        query.setParameter(1,idcorr);
        
        cantidad = (long)query.getSingleResult();
        
      }catch (Exception e){
       System.out.println(e.getMessage());
      }
       return cantidad;
    }
}
