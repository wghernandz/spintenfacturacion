/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spintenfacturacion.controller;

import com.spintenfacturacion.model.Usuario;
import com.spintenfacturacion.model.usuarioRole;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

@Named
@SessionScoped
public class plantillabaseController implements Serializable{
    
    private Usuario us;
    private usuarioRole role;

    public Usuario getUs() {
        return us;
    }

    public void setUs(Usuario us) {
        this.us = us;
    }

    public usuarioRole getRole() {
        return role;
    }

    public void setRole(usuarioRole role) {
        this.role = role;
    }
    
    public void verificarSession(){
    try{   
        FacesContext context = FacesContext.getCurrentInstance();
        us=(Usuario) context.getExternalContext().getSessionMap().get("usuarioactivo");
        if(us==null){
            context.getExternalContext().redirect("../index.xhtml");
        }
      }catch(Exception e){
        
    }   
      
    }
    
    public void cerrarSesion(){
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
    }
    
    public int obtenerRole(){
        FacesContext context = FacesContext.getCurrentInstance();
        this.role= (usuarioRole) context.getExternalContext().getSessionMap().get("mirol");
        return this.role.getRole().getId();
    }
}
    