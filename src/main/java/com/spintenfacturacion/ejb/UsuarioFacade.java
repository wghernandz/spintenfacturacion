/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spintenfacturacion.ejb;

import com.spintenfacturacion.model.Usuario;
import com.spintenfacturacion.model.usuarioRole;
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
public class UsuarioFacade extends AbstractFacade<Usuario> implements UsuarioFacadeLocal {

    @PersistenceContext(unitName = "com.servipinten_spintenfacturacion_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioFacade() {
        super(Usuario.class);
    }
    
     //metodo para validar usuario(login)
    @Override
    public Usuario Login(Usuario user){
      Usuario usuario=null;
      String consulta;
      
      try{
        consulta="SELECT u FROM Usuario u WHERE u.username = ?1 AND u.password = ?2 ";
        Query query=em.createQuery(consulta);
        query.setParameter(1, user.getUsername());
        query.setParameter(2, user.getPassword());
      
        List<Usuario> lista= query.getResultList();
        if (!lista.isEmpty()){
          usuario=lista.get(0);
          
      }
      
      }catch (Exception e){
          
      }
      
      return usuario;
    
    }
    
     //conocer q role tiene el usuario
    @Override
    public usuarioRole usuarioRole(Usuario user){
      usuarioRole usrole=null;
      String consulta;
      
      try{
        consulta="SELECT ur FROM usuarioRole ur WHERE ur.usuario.persona.id = ?1 ";
        Query query=em.createQuery(consulta);
        query.setParameter(1, user.getPersona().getId());
      
        List<usuarioRole> lista= query.getResultList();
        if (!lista.isEmpty()){
          usrole=lista.get(0);
          
      }
      
      }catch (Exception e){
          
      }
      
      return usrole;
    
    }
    
    
}
