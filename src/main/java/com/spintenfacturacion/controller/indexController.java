/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spintenfacturacion.controller;

import com.spintenfacturacion.ejb.RoleFacadeLocal;
import com.spintenfacturacion.ejb.UsuarioFacadeLocal;
import com.spintenfacturacion.ejb.usuarioRoleFacadeLocal;
import com.spintenfacturacion.model.Role;
import com.spintenfacturacion.model.Usuario;
import com.spintenfacturacion.model.usuarioRole;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;

import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class indexController implements Serializable {
    @EJB
    private usuarioRoleFacadeLocal usuarioroleEJB;
    @EJB
    private UsuarioFacadeLocal usuarioEJB;
    @EJB
    private RoleFacadeLocal roleEJB;
    
    private Usuario usuario;
    private Role role;
    private usuarioRole usuariorole;
    
    @PostConstruct
    public void init(){
        usuario=new Usuario();
        role= new Role();
        usuariorole= new usuarioRole();
    }

    public usuarioRoleFacadeLocal getUsuarioroleEJB() {
        return usuarioroleEJB;
    }

    public void setUsuarioroleEJB(usuarioRoleFacadeLocal usuarioroleEJB) {
        this.usuarioroleEJB = usuarioroleEJB;
    }

    public UsuarioFacadeLocal getUsuarioEJB() {
        return usuarioEJB;
    }

    public void setUsuarioEJB(UsuarioFacadeLocal usuarioEJB) {
        this.usuarioEJB = usuarioEJB;
    }

    public RoleFacadeLocal getRoleEJB() {
        return roleEJB;
    }

    public void setRoleEJB(RoleFacadeLocal roleEJB) {
        this.roleEJB = roleEJB;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public usuarioRole getUsuariorole() {
        return usuariorole;
    }

    public void setUsuariorole(usuarioRole usuariorole) {
        this.usuariorole = usuariorole;
    }
    
       //logueo de usuario  
    public String Login(){
        Usuario us;
        usuarioRole role;
        String redireccion=null;
        
        try{
            us=usuarioEJB.Login(usuario);
            if(us!=null){
                
                 redireccion="/facturador/nuevo.xhtml";
            /*    role=usuarioEJB.usuarioRole(us);
         
                switch(role.getRoleus().getId()){
                    case 1:
                        redireccion="/operativo/principal.xhtml";
                        break;
                    case 2:
                        redireccion="/operativo/principal.xhtml";
                        break;
                    case 3:
                        redireccion="/gerente/establecimientoCostosmo.xhtml";
                        break;
                    case 4:
                        redireccion="/jefetaller/listaOrdenesporasignar.xhtml";
                        break;
                    case 5:
                        redireccion="/contabilidad/ingresooperarios.xhtml";
                        break;
                    default:
                        redireccion="/operativo/principal.xhtml";
                        break;
                }
                //ABRIR VARIABLE DE SESION
               FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuarioactivo",us);
               FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("mirol", role);*/
            }else{
                FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_WARN,"Aviso","Credenciales Incorrectas :("));
            }
        }catch(Exception e){
            FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_FATAL,"Aviso","Error"));
        }
        return redireccion;
    }  
    
}
