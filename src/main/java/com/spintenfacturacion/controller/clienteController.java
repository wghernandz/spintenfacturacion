/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spintenfacturacion.controller;

import com.spintenfacturacion.ejb.ClienteFacadeLocal;
import com.spintenfacturacion.ejb.DepartamentoFacadeLocal;
import com.spintenfacturacion.ejb.MunicipioFacadeLocal;
import com.spintenfacturacion.ejb.PersonaFacadeLocal;
import com.spintenfacturacion.model.Cliente;
import com.spintenfacturacion.model.Departamento;
import com.spintenfacturacion.model.Municipio;
import com.spintenfacturacion.model.Persona;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class clienteController implements Serializable {
    @EJB
    private PersonaFacadeLocal personaEJB;
    @EJB
    private ClienteFacadeLocal clienteEJB;
    @EJB
    private MunicipioFacadeLocal municipioEJB;
    @EJB
    private DepartamentoFacadeLocal departamentoEJB;
    
    //entidades
    private Persona persona;
    private Cliente cliente;
    private Municipio municipio;
    private Departamento departamento;
    
    private List<Departamento> departamentos;
    private List<Municipio> municipios;
    private List<Cliente> clientes;
    private List<Cliente> filteredclientes;
    
    private boolean guardar;
    private boolean buscar;
    private boolean modificar;
    private boolean eliminar;
    private boolean natural;
    private boolean juridica;
    private String tipoempresa;
    
    @PostConstruct
    public void init(){
        persona=new Persona();
        cliente=new Cliente();
        municipio=new Municipio();
        departamento=new Departamento();
        departamentos=departamentoEJB.findAll();
        municipios=municipioEJB.findAll();
        clientes=clienteEJB.findAll();
        tipoempresa="Juridica";
        this.juridica=false;
        this.natural=true;
        guardar=false;
        buscar=false;
        modificar=true;
        eliminar=true;
    }

    public PersonaFacadeLocal getPersonaEJB() {
        return personaEJB;
    }

    public void setPersonaEJB(PersonaFacadeLocal personaEJB) {
        this.personaEJB = personaEJB;
    }

    public ClienteFacadeLocal getClienteEJB() {
        return clienteEJB;
    }

    public void setClienteEJB(ClienteFacadeLocal clienteEJB) {
        this.clienteEJB = clienteEJB;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public MunicipioFacadeLocal getMunicipioEJB() {
        return municipioEJB;
    }

    public void setMunicipioEJB(MunicipioFacadeLocal municipioEJB) {
        this.municipioEJB = municipioEJB;
    }

    public DepartamentoFacadeLocal getDepartamentoEJB() {
        return departamentoEJB;
    }

    public void setDepartamentoEJB(DepartamentoFacadeLocal departamentoEJB) {
        this.departamentoEJB = departamentoEJB;
    }

    public Municipio getMunicipio() {
        return municipio;
    }

    public void setMunicipio(Municipio municipio) {
        this.municipio = municipio;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    public List<Departamento> getDepartamentos() {
        return departamentos;
    }

    public void setDepartamentos(List<Departamento> departamentos) {
        this.departamentos = departamentos;
    }

    public List<Municipio> getMunicipios() {
        return municipios;
    }

    public void setMunicipios(List<Municipio> municipios) {
        this.municipios = municipios;
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }

    public boolean isGuardar() {
        return guardar;
    }

    public void setGuardar(boolean guardar) {
        this.guardar = guardar;
    }

    public boolean isBuscar() {
        return buscar;
    }

    public void setBuscar(boolean buscar) {
        this.buscar = buscar;
    }

    public boolean isModificar() {
        return modificar;
    }

    public void setModificar(boolean modificar) {
        this.modificar = modificar;
    }

    public boolean isEliminar() {
        return eliminar;
    }

    public void setEliminar(boolean eliminar) {
        this.eliminar = eliminar;
    }

    public List<Cliente> getFilteredclientes() {
        return filteredclientes;
    }

    public void setFilteredclientes(List<Cliente> filteredclientes) {
        this.filteredclientes = filteredclientes;
    }

    public String getTipoempresa() {
        return tipoempresa;
    }

    public void setTipoempresa(String tipoempresa) {
        this.tipoempresa = tipoempresa;
    }

    public boolean isNatural() {
        return natural;
    }

    public void setNatural(boolean natural) {
        this.natural = natural;
    }

    public boolean isJuridica() {
        return juridica;
    }

    public void setJuridica(boolean juridica) {
        this.juridica = juridica;
    }
  
    public String guardarCliente(){
        if (this.departamento.getId()==0){
             this.municipio.setDepartamento(null);
             this.setMunicipio(null);
        }else{
            this.municipio.setDepartamento(departamento);  
        }
        this.persona.setMunicipio(municipio);
        this.cliente.setPersona(persona);
        personaEJB.create(persona);
        clienteEJB.create(cliente);
       
        
        FacesContext.getCurrentInstance().addMessage(
        null, new FacesMessage("CLIENTE INGRESADO"));
 
        FacesContext.getCurrentInstance()
            .getExternalContext()
            .getFlash().setKeepMessages(true);
        
            UIViewRoot view = FacesContext.getCurrentInstance().getViewRoot();
            return view.getViewId() + "?faces-redirect=true&includeViewParams=true"; 
    }
    
    public void establecerClientePersona(){
        this.setDepartamento(this.cliente.getPersona().getMunicipio().getDepartamento());
        this.setMunicipio(this.cliente.getPersona().getMunicipio());
        
        if(this.cliente.getPersona().getNombresociedad()==null)
        {
            this.setCliente(this.cliente);
            this.setPersona(this.cliente.getPersona());
            this.tipoempresa="Natural";
            this.natural=false;
            //bloquear datos de juridica
            this.juridica=true;
        }else{
            this.setPersona(this.cliente.getPersona());
            this.setCliente(this.cliente);
            this.tipoempresa="Juridica";
            this.juridica=false;
            //bloquear datos de natural
            this.natural=true;
        }
        
        this.guardar=true;
        this.modificar=false;
        this.eliminar=false;
        this.buscar=true;
        
    }
    
    public String modificarCliente(){
        
    if (this.departamento.getId()==0){
             this.municipio.setDepartamento(null);
             this.setMunicipio(null);
        }else{
            this.municipio.setDepartamento(departamento);  
        }
        this.persona.setMunicipio(municipio);
        this.cliente.setPersona(persona);
        personaEJB.edit(persona);
        clienteEJB.edit(cliente);
        
        this.modificar=true;
        this.eliminar=true;
        this.guardar=false;
        this.buscar=false;
        
         FacesContext.getCurrentInstance().addMessage(
        null, new FacesMessage("CLIENTE MODIFICADO"));
 
        FacesContext.getCurrentInstance()
            .getExternalContext()
            .getFlash().setKeepMessages(true);
        
            UIViewRoot view = FacesContext.getCurrentInstance().getViewRoot();
            return view.getViewId() + "?faces-redirect=true&includeViewParams=true"; 
    }
    
    public void cambiarTipopersona(){
         if("Juridica".equals(tipoempresa)){
             this.natural=true;
             this.juridica=false;
         }else{
             this.juridica=true;
             this.natural=false;
         }
    }
    
    public String eliminarCliente(){
        persona=personaEJB.find(this.cliente.getPersona().getId());
        cliente=clienteEJB.find(this.cliente.getPersona().getId());
        clienteEJB.remove(cliente);
        personaEJB.remove(persona);
        
          FacesContext.getCurrentInstance().addMessage(
        null, new FacesMessage("CLIENTE ELIMINADO "+persona.getNombresociedad()));
 
        FacesContext.getCurrentInstance()
            .getExternalContext()
            .getFlash().setKeepMessages(true);
        
            UIViewRoot view = FacesContext.getCurrentInstance().getViewRoot();
            return view.getViewId() + "?faces-redirect=true&includeViewParams=true"; 
    }
    
    public void municipioXdepartamento(){
        this.municipios=departamentoEJB.deptoXmunicipio(this.departamento);
    }
}
