/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spintenfacturacion.ejb;

import com.spintenfacturacion.model.Factura;
import com.spintenfacturacion.model.correlativoDoc;
import com.spintenfacturacion.model.detalleFactura;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TemporalType;

/**
 *
 * @author willian
 */
@Stateless
public class FacturaFacade extends AbstractFacade<Factura> implements FacturaFacadeLocal {

    @PersistenceContext(unitName = "com.servipinten_spintenfacturacion_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FacturaFacade() {
        super(Factura.class);
    }
    
     //Obtener correlativo segun tipo de documento
    @Override
    public int obtenerUltimocorr(int idtipodoc){
      String consulta;
      String consulta2;
      List<correlativoDoc> lista;
      correlativoDoc correlativodoc;
      int ultimocorr=0;
      int corr=0;
      
      try{
        //verificar exista un correlativo en uso para el tipo de documento seleccionado
        consulta="SELECT c FROM correlativoDoc c WHERE c.tipodocumento.id = ?1 AND c.estado = 'en uso'";
        Query query=em.createQuery(consulta);
        query.setParameter(1,idtipodoc);
        if(query.getResultList().isEmpty()==false){
            lista=query.getResultList();
            correlativodoc=lista.get(0);
            corr=correlativodoc.getId();
            System.out.println("VALOR DE CORRELATIVO"+corr);
        }else{
             ultimocorr = -1;//NO EXISTE
            }
                //si existe el correlativo, entonces buscar en factura el ultimo utilizado
                if(ultimocorr!=-1){  
                    consulta2="SELECT MAX(f.correlativo) FROM Factura f WHERE f.correlativodoc.id = ?1";
                    Query query2=em.createQuery(consulta2);
                    query2.setParameter(1,corr);
        
                if(query2.getResultList().isEmpty()==false){
                    ultimocorr=(int)query2.getSingleResult();
                    //correlativodoc=lista.get(0);
                    //ultimocorr=correlativodoc.getId();
                }
            }
      }catch (Exception e){
            System.out.println(e.getMessage());
      }
      return ultimocorr;
    }
     
    //PARA IMPRIMIR FACTURA
    @Override
    public List<detalleFactura> imprimirDetalle(int id){
      List<detalleFactura> lista = null;
      try{
        String consulta;
        consulta="SELECT d  FROM detalleFactura d WHERE d.factura.id = ?1 ";
        Query query=em.createQuery(consulta);
        query.setParameter(1,id);
        
        lista = query.getResultList();
        System.out.println("Valor de LISTA "+lista.get(0).getId());
      }catch (Exception e){
       System.out.println(e.getMessage());
      }
      return lista;
    } 
    
    //PARA IMPRIMIR FACTURA
    @Override
    public Factura facturaEnproceso(){
      Factura factura = null;
      List<Factura> lista=null;
      try{
        String consulta;
        consulta="SELECT f  FROM Factura f WHERE f.estado = 'En proceso' ";
        Query query=em.createQuery(consulta);
        
        lista = query.getResultList();
        factura=(Factura)lista.get(0);
        System.out.println("Valor de LISTA "+lista.get(0).getId());
      }catch (Exception e){
       System.out.println(e.getMessage());
      }
      return factura;
    }
      
    //obtener listado de facturas segun rango de fechas.
    @Override
    public List<Factura> facturaRangofecha(Date fini,Date ffin){
      List<Factura> lista = null;
      try{
        String consulta;
        consulta="SELECT f FROM Factura f WHERE f.fecha >= ?1 AND f.fecha <= ?2 ORDER BY f.correlativodoc.tipodocumento.id,f.fecha,f.correlativo";
        Query query=em.createQuery(consulta);
        query.setParameter(1,fini,TemporalType.DATE);
        query.setParameter(2,ffin,TemporalType.DATE);
        
        lista = query.getResultList();
      }catch (Exception e){
       System.out.println(e.getMessage());
      }
      return lista;
    } 
    
    
}
