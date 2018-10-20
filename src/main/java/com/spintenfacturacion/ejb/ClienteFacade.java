/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spintenfacturacion.ejb;

import com.spintenfacturacion.model.Cliente;
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
public class ClienteFacade extends AbstractFacade<Cliente> implements ClienteFacadeLocal {

    @PersistenceContext(unitName = "com.servipinten_spintenfacturacion_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ClienteFacade() {
        super(Cliente.class);
    }
    
    @Override
    public Cliente clienteSegunnrc(String nrc){
      Cliente cliente=null;
      String consulta;
      
      try{
        consulta="SELECT c FROM Cliente c WHERE c.nrc = ?1 ";
        Query query=em.createQuery(consulta);
        query.setParameter(1,nrc);
      
        List<Cliente> lista= query.getResultList();
        if (!lista.isEmpty()){
          cliente=lista.get(0);   
      }
      }catch (Exception e){
          
      }
      return cliente;
    }
    
}
