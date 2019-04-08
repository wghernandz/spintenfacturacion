/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spintenfacturacion.ejb;

import com.spintenfacturacion.model.Departamento;
import com.spintenfacturacion.model.Municipio;
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
public class DepartamentoFacade extends AbstractFacade<Departamento> implements DepartamentoFacadeLocal {

    @PersistenceContext(unitName = "com.servipinten_spintenfacturacion_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DepartamentoFacade() {
        super(Departamento.class);
    }
    
     @Override
    public List<Municipio> deptoXmunicipio(Departamento depto){
        List<Municipio> municipios=null;
        String consulta;
        try{
            consulta= "SELECT m FROM Departamento d JOIN Municipio m "
                    + "WHERE m.departamento.id=d.id and d.id = "+depto.getId();
            Query query=em.createQuery(consulta);
            //query.setParameter(1,depto.getId());
        
            municipios=query.getResultList();
              
            }
        catch(Exception e){
            throw e;
        }finally{
            //em.close();
        }
        return municipios;
    }
    
}
