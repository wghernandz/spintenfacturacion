/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spintenfacturacion.controller;

import com.spintenfacturacion.ejb.ClienteFacadeLocal;
import com.spintenfacturacion.ejb.DepartamentoFacadeLocal;
import com.spintenfacturacion.ejb.FacturaFacadeLocal;
import com.spintenfacturacion.ejb.MunicipioFacadeLocal;
import com.spintenfacturacion.ejb.PersonaFacadeLocal;
import com.spintenfacturacion.ejb.ProductoFacadeLocal;
import com.spintenfacturacion.ejb.correlativoDocFacadeLocal;
import com.spintenfacturacion.ejb.detalleFacturaFacadeLocal;
import com.spintenfacturacion.ejb.marcaVehiculoFacadeLocal;
import com.spintenfacturacion.ejb.tipoDocumentoFacadeLocal;
import com.spintenfacturacion.ejb.vehiculoModeloFacadeLocal;
import com.spintenfacturacion.model.Cliente;
import com.spintenfacturacion.model.Departamento;
import com.spintenfacturacion.model.Factura;
import com.spintenfacturacion.model.Municipio;
import com.spintenfacturacion.model.Persona;
import com.spintenfacturacion.model.Producto;
import com.spintenfacturacion.model.correlativoDoc;
import com.spintenfacturacion.model.detalleFactura;
import com.spintenfacturacion.model.marcaVehiculo;
import com.spintenfacturacion.model.tipoDocumento;
import com.spintenfacturacion.model.vehiculoModelo;
import com.spintenfacturacion.utileria.NumeroLetras;
//import java.awt.Event;
//import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import javax.faces.event.ActionEvent;
import org.primefaces.context.RequestContext;


@Named
@ViewScoped
public class nuevaFacturaController implements Serializable {
    //EJBS
    @EJB
    private FacturaFacadeLocal facturaEJB;
    @EJB
    private detalleFacturaFacadeLocal detallefacturaEJB;
    @EJB
    private vehiculoModeloFacadeLocal vehiculomodeloEJB;
    @EJB
    private tipoDocumentoFacadeLocal tipodocumentoEJB;
    @EJB
    private correlativoDocFacadeLocal correlativodocEJB;
    @EJB
    private DepartamentoFacadeLocal departamentoEJB;
    @EJB
    private MunicipioFacadeLocal municipioEJB;
    @EJB
    private ClienteFacadeLocal clienteEJB;
    @EJB
    private marcaVehiculoFacadeLocal marcavehiculoEJB;
    @EJB
    private ProductoFacadeLocal productoEJB;
    @EJB
    private PersonaFacadeLocal personaEJB;
    
    //ENTIDADES
    private Factura factura;
    private Factura facturaaimprimir;
    private detalleFactura detallefactura;
    private vehiculoModelo vehiculomodelo;
    private tipoDocumento tipodocumento;
    private correlativoDoc correlativodoc;
    private Departamento departamento;
    private Municipio municipio;
    private Cliente cliente;
    private marcaVehiculo marcavehiculo;
    private Producto producto;
    private Persona persona;
    private vehiculoModelo vehiculomodelodet;
    private marcaVehiculo marcavehiculodet;
    
    //Listas
    private List<detalleFactura> detallesfactura;
    private List<detalleFactura> detallesfacturaaimprimir;
    private List<vehiculoModelo> vehiculosmodelo;
    private List<tipoDocumento> tiposdocumento;
    private List<Departamento> departamentos;
    private List<Municipio> municipios;
    private List<Cliente> clientes;
    private List<Cliente> filteredclientes;
    private List<marcaVehiculo> marcas;
    private List<Producto> productos;
    private List<vehiculoModelo> vehiculosmodelodet;
    private List<marcaVehiculo> marcasdet;
    
    private BigDecimal acumulatorVentans= new BigDecimal(0);
    private BigDecimal acumulatorVentaex= new BigDecimal(0);
    private BigDecimal acumulatorVentagra= new BigDecimal(0);
    
    private BigDecimal iva=new BigDecimal(0);
    private BigDecimal subtotal=new BigDecimal(0);
    private BigDecimal vafectafactura=new BigDecimal(0);
    
    private boolean agregar;
    private boolean modificar;
    private boolean finalizar;
    private boolean modificarencabezado;
    private boolean guardarcambios;
    private boolean imprimir;
    private boolean enmodificacion;
    
    private int tipoventa=0;
    
    @PostConstruct
    public void init(){
        //SI HAY FACTURA EN PROCESO SE DEBE INICIALIZAR FACTURA A IMPRIMIR.PENDIENTE
        //verificar si hay una factura en proceso.
        if(facturaEJB.facturaEnproceso()!=null){
            factura=facturaEJB.facturaEnproceso();
            detallesfactura=detallefacturaEJB.detalleDeFactura(factura.getId());
            persona=factura.getPersona();
            cliente=factura.getCliente();
            correlativodoc=factura.getCorrelativodoc();
            tipodocumento=correlativodoc.getTipodocumento();
            if(factura.getVehiculomodelo()!=null){
                vehiculomodelo=factura.getVehiculomodelo();
                marcavehiculo=vehiculomodelo.getMarcavehiculo();
            }else{
                vehiculomodelo=new vehiculoModelo();
                marcavehiculo=new marcaVehiculo(); 
            }
            if(detallefacturaEJB.detalleDeFactura(factura.getId()).isEmpty()){
                //inicializar acumuladores
                //System.out.println("INICIALIZANDO ACUM");
                this.factura.setAcumventaex(new BigDecimal(0));
                this.factura.setAcumventagra(new BigDecimal(0));
                this.factura.setAcumventans(new BigDecimal(0));
            }
                this.modificarencabezado=true;
        }else{
            factura=new Factura();
            vehiculomodelo= new vehiculoModelo();
            marcavehiculo=new marcaVehiculo();
            tipodocumento= new tipoDocumento();
            correlativodoc= new correlativoDoc();
            cliente= new Cliente();
            persona= new Persona();
            //Inicializar acumuladores
            this.factura.setAcumventaex(new BigDecimal(0));
            this.factura.setAcumventagra(new BigDecimal(0));
            this.factura.setAcumventans(new BigDecimal(0));
        }
        marcavehiculodet=new marcaVehiculo();
        vehiculomodelodet= new vehiculoModelo();
        detallefactura= new detalleFactura();
        departamento= new Departamento();
        municipio= new Municipio();
        producto= new Producto();
        vehiculosmodelodet=vehiculomodeloEJB.findAll();
        marcasdet=marcavehiculoEJB.findAll();
        vehiculosmodelo=vehiculomodeloEJB.findAll();
        tiposdocumento=tipodocumentoEJB.findAll();
        departamentos=departamentoEJB.findAll();
        municipios=municipioEJB.findAll();
        marcas=marcavehiculoEJB.findAll();
        productos=productoEJB.findAll();
        clientes=clienteEJB.findAll();
        tipoventa=0;
        modificar=true;
    }

    public FacturaFacadeLocal getFacturaEJB() {
        return facturaEJB;
    }

    public void setFacturaEJB(FacturaFacadeLocal facturaEJB) {
        this.facturaEJB = facturaEJB;
    }

    public detalleFacturaFacadeLocal getDetallefacturaEJB() {
        return detallefacturaEJB;
    }

    public void setDetallefacturaEJB(detalleFacturaFacadeLocal detallefacturaEJB) {
        this.detallefacturaEJB = detallefacturaEJB;
    }

    public vehiculoModeloFacadeLocal getVehiculomodeloEJB() {
        return vehiculomodeloEJB;
    }

    public void setVehiculomodeloEJB(vehiculoModeloFacadeLocal vehiculomodeloEJB) {
        this.vehiculomodeloEJB = vehiculomodeloEJB;
    }

    public tipoDocumentoFacadeLocal getTipodocumentoEJB() {
        return tipodocumentoEJB;
    }

    public void setTipodocumentoEJB(tipoDocumentoFacadeLocal tipodocumentoEJB) {
        this.tipodocumentoEJB = tipodocumentoEJB;
    }

    public correlativoDocFacadeLocal getCorrelativodocEJB() {
        return correlativodocEJB;
    }

    public void setCorrelativodocEJB(correlativoDocFacadeLocal correlativodocEJB) {
        this.correlativodocEJB = correlativodocEJB;
    }

    public DepartamentoFacadeLocal getDepartamentoEJB() {
        return departamentoEJB;
    }

    public void setDepartamentoEJB(DepartamentoFacadeLocal departamentoEJB) {
        this.departamentoEJB = departamentoEJB;
    }

    public MunicipioFacadeLocal getMunicipioEJB() {
        return municipioEJB;
    }

    public void setMunicipioEJB(MunicipioFacadeLocal municipioEJB) {
        this.municipioEJB = municipioEJB;
    }

    public ClienteFacadeLocal getClienteEJB() {
        return clienteEJB;
    }

    public void setClienteEJB(ClienteFacadeLocal clienteEJB) {
        this.clienteEJB = clienteEJB;
    }

    public marcaVehiculoFacadeLocal getMarcavehiculoEJB() {
        return marcavehiculoEJB;
    }

    public void setMarcavehiculoEJB(marcaVehiculoFacadeLocal marcavehiculoEJB) {
        this.marcavehiculoEJB = marcavehiculoEJB;
    }

    public ProductoFacadeLocal getProductoEJB() {
        return productoEJB;
    }

    public void setProductoEJB(ProductoFacadeLocal productoEJB) {
        this.productoEJB = productoEJB;
    }

    public PersonaFacadeLocal getPersonaEJB() {
        return personaEJB;
    }

    public void setPersonaEJB(PersonaFacadeLocal personaEJB) {
        this.personaEJB = personaEJB;
    }

    public Factura getFactura() {
        return factura;
    }

    public void setFactura(Factura factura) {
        this.factura = factura;
    }

    public detalleFactura getDetallefactura() {
        return detallefactura;
    }

    public void setDetallefactura(detalleFactura detallefactura) {
        this.detallefactura = detallefactura;
    }

    public vehiculoModelo getVehiculomodelo() {
        return vehiculomodelo;
    }

    public void setVehiculomodelo(vehiculoModelo vehiculomodelo) {
        this.vehiculomodelo = vehiculomodelo;
    }

    public tipoDocumento getTipodocumento() {
        return tipodocumento;
    }

    public void setTipodocumento(tipoDocumento tipodocumento) {
        this.tipodocumento = tipodocumento;
    }

    public correlativoDoc getCorrelativodoc() {
        return correlativodoc;
    }

    public void setCorrelativodoc(correlativoDoc correlativodoc) {
        this.correlativodoc = correlativodoc;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    public Municipio getMunicipio() {
        return municipio;
    }

    public void setMunicipio(Municipio municipio) {
        this.municipio = municipio;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public marcaVehiculo getMarcavehiculo() {
        return marcavehiculo;
    }

    public void setMarcavehiculo(marcaVehiculo marcavehiculo) {
        this.marcavehiculo = marcavehiculo;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public List<detalleFactura> getDetallesfactura() {
        return detallesfactura;
    }

    public void setDetallesfactura(List<detalleFactura> detallesfactura) {
        this.detallesfactura = detallesfactura;
    }

    public List<vehiculoModelo> getVehiculosmodelo() {
        return vehiculosmodelo;
    }

    public void setVehiculosmodelo(List<vehiculoModelo> vehiculosmodelo) {
        this.vehiculosmodelo = vehiculosmodelo;
    }

    public List<tipoDocumento> getTiposdocumento() {
        return tiposdocumento;
    }

    public void setTiposdocumento(List<tipoDocumento> tiposdocumento) {
        this.tiposdocumento = tiposdocumento;
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

    public List<marcaVehiculo> getMarcas() {
        return marcas;
    }

    public void setMarcas(List<marcaVehiculo> marcas) {
        this.marcas = marcas;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

    public BigDecimal getAcumulatorVentans() {
        return acumulatorVentans;
    }

    public void setAcumulatorVentans(BigDecimal acumulatorVentans) {
        this.acumulatorVentans = acumulatorVentans;
    }

    public BigDecimal getAcumulatorVentaex() {
        return acumulatorVentaex;
    }

    public void setAcumulatorVentaex(BigDecimal acumulatorVentaex) {
        this.acumulatorVentaex = acumulatorVentaex;
    }

    public BigDecimal getAcumulatorVentagra() {
        return acumulatorVentagra;
    }

    public void setAcumulatorVentagra(BigDecimal acumulatorVentagra) {
        this.acumulatorVentagra = acumulatorVentagra;
    }

    public BigDecimal getIva() {
        return iva;
    }

    public void setIva(BigDecimal iva) {
        this.iva = iva;
    }

    public int getTipoventa() {
        return tipoventa;
    }

    public void setTipoventa(int tipoventa) {
        this.tipoventa = tipoventa;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    public boolean isAgregar() {
        return agregar;
    }

    public void setAgregar(boolean agregar) {
        this.agregar = agregar;
    }

    public boolean isModificar() {
        return modificar;
    }

    public void setModificar(boolean modificar) {
        this.modificar = modificar;
    }

    public boolean isFinalizar() {
        return finalizar;
    }

    public void setFinalizar(boolean finalizar) {
        this.finalizar = finalizar;
    }

    public boolean isModificarencabezado() {
        return modificarencabezado;
    }

    public void setModificarencabezado(boolean modificarencabezado) {
        this.modificarencabezado = modificarencabezado;
        //modificar estado para guardar cambios
        this.guardarcambios=true;
        this.finalizar=true;
        this.agregar=true;  
    }

    public boolean isGuardarcambios() {
        return guardarcambios;
    }

    public void setGuardarcambios(boolean guardarcambios) {
        this.guardarcambios = guardarcambios;
    }

    public BigDecimal getVafectafactura() {
        return vafectafactura;
    }

    public void setVafectafactura(BigDecimal vafectafactura) {
        this.vafectafactura = vafectafactura;
    }

    public boolean isImprimir() {
        return imprimir;
    }

    public void setImprimir(boolean imprimir) {
        this.imprimir = imprimir;
    }

    public boolean isEnmodificacion() {
        return enmodificacion;
    }

    public void setEnmodificacion(boolean enmodificacion) {
        this.enmodificacion = enmodificacion;
    }

    public List<Cliente> getFilteredclientes() {
        return filteredclientes;
    }

    public void setFilteredclientes(List<Cliente> filteredclientes) {
        this.filteredclientes = filteredclientes;
    }

    public Factura getFacturaaimprimir() {
        return facturaaimprimir;
    }

    public void setFacturaaimprimir(Factura facturaaimprimir) {
        this.facturaaimprimir = facturaaimprimir;
    }

    public List<detalleFactura> getDetallesfacturaaimprimir() {
        return detallesfacturaaimprimir;
    }

    public void setDetallesfacturaaimprimir(List<detalleFactura> detallesfacturaaimprimir) {
        this.detallesfacturaaimprimir = detallesfacturaaimprimir;
    }

    public List<vehiculoModelo> getVehiculosmodelodet() {
        return vehiculosmodelodet;
    }

    public void setVehiculosmodelodet(List<vehiculoModelo> vehiculosmodelodet) {
        this.vehiculosmodelodet = vehiculosmodelodet;
    }

    public List<marcaVehiculo> getMarcasdet() {
        return marcasdet;
    }

    public void setMarcasdet(List<marcaVehiculo> marcasdet) {
        this.marcasdet = marcasdet;
    }

    public vehiculoModelo getVehiculomodelodet() {
        return vehiculomodelodet;
    }

    public void setVehiculomodelodet(vehiculoModelo vehiculomodelodet) {
        this.vehiculomodelodet = vehiculomodelodet;
    }

    public marcaVehiculo getMarcavehiculodet() {
        return marcavehiculodet;
    }

    public void setMarcavehiculodet(marcaVehiculo marcavehiculodet) {
        this.marcavehiculodet = marcavehiculodet;
    }
    //establecer el correlativo a utilizar en la facturacion
    public void obtenerUltimocorr(){
        int correlativo=facturaEJB.obtenerUltimocorr(this.tipodocumento.getId());
        //BigDecimal correlativo=new BigDecimal(corr);
        switch (correlativo){
            case 0://existe correlativo pero aun no se a utilizado
                int cinicial=correlativodocEJB.obtenerCorrInicial(this.tipodocumento.getId());//obtener correlativoinicial
                if(cinicial!=-1){
                    if(cinicial!=1){
                        factura.setCorrelativo(cinicial);
                    }else{
                        correlativo=1;
                        factura.setCorrelativo(correlativo);
                    }
                }else{
                     FacesContext.getCurrentInstance().addMessage(
                        null, new FacesMessage("ERROR AL OBTENER CORRELATIVO INICIAL"));
                        FacesContext.getCurrentInstance()
                        .getExternalContext()
                        .getFlash().setKeepMessages(true);
                }
                break;
            case -1://No existe correlativo
                FacesContext.getCurrentInstance().addMessage(
                        null, new FacesMessage("NO HAY CORRELATIVO DISPONIBLE"));
                FacesContext.getCurrentInstance()
                        .getExternalContext()
                        .getFlash().setKeepMessages(true);
                factura.setCorrelativo(0);
                break;
            default://existe y ya se estan utilizando
                correlativo=correlativo+1;
                factura.setCorrelativo(correlativo);
                break;
        }
    }
        public void obtenerDatosCliente(){
            this.cliente=clienteEJB.clienteSegunnrc(cliente.getNrc());
            this.municipio=municipioEJB.find(this.cliente.getPersona().getMunicipio().getId());
            this.departamento=departamentoEJB.find(this.cliente.getPersona().getMunicipio().getDepartamento().getId());
        }
        
        public void operacionesAgregarModificar(){
            String tipodoc=factura.getCorrelativodoc().getTipodocumento().getCodigo();
            //guardar detalles, tipo venta 0 si es gravada, 1 si es exento, 2 otro caso
            if(this.tipoventa==0){ 
                //Verificar si es factura consumidor final, si es asi, modificar calculos  
                if(!"FCF".equals(tipodoc)){
                    System.out.println("AQUI"+this.tipodocumento.getCodigo());
                    //cuando no es factura consumidor final el acumuladorVentagra posee valor neto
                    this.detallefactura.setVneto(this.detallefactura.getCantidad().multiply(this.detallefactura.getPreciounitario()));//establecer valores netos
                    //this.acumulatorVentagra=this.acumulatorVentagra.add(this.detallefactura.getVneto());//acumular suma valor neto y mostrar
                    
                    this.factura.setAcumventagra(factura.getAcumventagra().add(this.detallefactura.getVneto()));
                    System.out.println("VALOR ACUMGRA"+this.factura.getAcumventagra());
                    //this.setIva(this.acumulatorVentagra.multiply(new BigDecimal("0.13")));
                    this.setIva(factura.getAcumventagra().multiply(new BigDecimal("0.13")));
                    this.factura.setRetencion(factura.getAcumventagra().multiply(new BigDecimal("0.01")));
                    this.setSubtotal(factura.getAcumventagra().multiply(new BigDecimal("1.13")));
                    }else{
                        //cuando es factura consumidor final el acumuladorVentagra tiene un valor total, se debe obtener el valor neto para guardarlo
                        this.detallefactura.setVneto(this.detallefactura.getCantidad().multiply(this.detallefactura.getPreciounitario()));//es vtotal
                        this.vafectafactura=this.vafectafactura.add(this.detallefactura.getVneto());//solo para efecto de mostrar
                        //this.acumulatorVentagra=this.acumulatorVentagra.add(this.detallefactura.getVneto().divide(new BigDecimal("1.13"),2,RoundingMode.HALF_UP));//acumular suma valor neto y mostrar
                        factura.setAcumventagra(factura.getAcumventagra().add(this.detallefactura.getVneto().divide(new BigDecimal("1.13"),2,RoundingMode.HALF_UP)));
                        this.setIva(factura.getAcumventagra().multiply(new BigDecimal("0.13")));
                        this.factura.setRetencion(factura.getAcumventagra().multiply(new BigDecimal("0.01")));
                        this.setSubtotal(factura.getAcumventagra().multiply(new BigDecimal("1.13")).subtract(this.factura.getRetencion()));
                     }
                this.detallefactura.setVexento(new BigDecimal(0));
                this.detallefactura.setVnogravado(new BigDecimal(0));
            }else if(this.tipoventa==1){
                            this.detallefactura.setVexento(this.detallefactura.getCantidad().multiply(this.detallefactura.getPreciounitario()));
                            //this.acumulatorVentaex=this.acumulatorVentaex.add(this.detallefactura.getVexento());
                            factura.setAcumventaex(factura.getAcumventaex().add(this.detallefactura.getVexento()));
                            this.detallefactura.setVneto(new BigDecimal(0));
                            this.detallefactura.setVnogravado(new BigDecimal(0));
                        }else{
                            this.detallefactura.setVnogravado(this.detallefactura.getCantidad().multiply(this.detallefactura.getPreciounitario()));
                            //this.acumulatorVentans=this.acumulatorVentans.add(this.detallefactura.getVnogravado());
                            factura.setAcumventans(factura.getAcumventans().add(this.detallefactura.getVnogravado()));
                            this.detallefactura.setVneto(new BigDecimal(0));
                            this.detallefactura.setVexento(new BigDecimal(0));
                          }       
            //validar si el subtotal y retencion no sean igual a null
            if(this.subtotal==null){this.subtotal=new BigDecimal(0);}
            if(this.factura.getRetencion()==null){this.factura.setRetencion(new BigDecimal(0));}
            
            //obtener sumatoria total, cuando sea factura y cuando sea CCF or NC or ND
            if(!"FCF".equals(tipodoc)){
                System.out.println("VALOR SUBTOTAL"+subtotal);
                this.factura.setTotalventa(this.subtotal.subtract(this.factura.getRetencion()).add(factura.getAcumventaex()).add(factura.getAcumventans()));
                }else{
                   this.factura.setTotalventa(this.subtotal.add(factura.getAcumventaex()).add(factura.getAcumventans()));
                }
            //Pasar sumatoria total a letras
            this.factura.setTventaenletras(NumeroLetras.cantidadConLetra(this.factura.getTotalventa().toString()));
            //this.factura.setTotalventa(this.acumulatorVentagra.add(this.iva));
        }
        
        public void agregarDetalle(){
            //validar que el correlativo no sea igual 0
            if (this.factura.getCorrelativo()!=0){
            //Obtengo el tipo de documento
            this.correlativodoc=tipodocumentoEJB.correlativoDocUso(this.tipodocumento.getId());
            //guardar factura operaciones y calculos
            if(facturaEJB.find(factura.getId())==null){
              this.factura.setPersona(personaEJB.find(cliente.getPersona().getId()));
              if(vehiculomodelo.getId()!=0){
                this.factura.setVehiculomodelo(vehiculomodelo);
              }
              this.factura.setCliente(cliente);
              this.factura.setCorrelativodoc(this.correlativodoc);
              this.factura.setCondpago("cred");
              this.factura.setTotalventa(new BigDecimal(0));
              this.factura.setEstado("En proceso");
              this.factura.setTventaenletras("Cero");
              facturaEJB.create(factura);
              this.modificarencabezado=true;
              //objeto a imprimir
              this.setFacturaaimprimir(factura);
            }
            //metodo comun para operaciones de agregar y modificar detalle;
            operacionesAgregarModificar();
            if(this.vehiculomodelodet.getId()!=0){
                this.detallefactura.setVehiculomodelo(this.vehiculomodelodet);
            }
            this.detallefactura.setFactura(factura);
            this.detallefactura.setProducto(this.producto);
            detallefacturaEJB.create(detallefactura);
            //actualizar acumuladores de factura
             this.factura.setAcumventagra(factura.getAcumventagra());
             this.factura.setAcumventaex(factura.getAcumventaex());
             this.factura.setAcumventans(factura.getAcumventans());
             facturaEJB.edit(this.factura);
             
            //inicializar objetos para agregar sig. detalle de factura
            marcavehiculodet=new marcaVehiculo();
            vehiculomodelodet= new vehiculoModelo();
            this.detallesfactura=detallefacturaEJB.detalleDeFactura(this.factura.getId());
            detallefactura=new detalleFactura();
            this.producto=new Producto();
            }else{
                 FacesContext.getCurrentInstance().addMessage(
                 null, new FacesMessage("CORRELATIVO NO PUEDE SER IGUAL A CERO"));
 
                FacesContext.getCurrentInstance()
                    .getExternalContext()
                    .getFlash().setKeepMessages(true);
            }
        }
        
        public void modificarDetalle(){
            
            operacionesAgregarModificar();
            if(this.vehiculomodelodet.getId()!=0){
                System.out.println("Pase aqui"+vehiculomodelodet.getNombremodelo());
                this.detallefactura.setVehiculomodelo(vehiculomodelodet);
            }else{
                this.detallefactura.setVehiculomodelo(null);
            }
            //this.setProducto(producto);
            this.detallefactura.setFactura(factura);
            this.detallefactura.setProducto(this.producto);
            detallefacturaEJB.edit(detallefactura);
            this.detallesfactura=detallefacturaEJB.detalleDeFactura(this.factura.getId());
            detallefactura=new detalleFactura();
            producto=new Producto();
            marcavehiculodet= new marcaVehiculo();
            vehiculomodelodet=new vehiculoModelo();
            this.agregar=false;
            this.enmodificacion=false;
            this.imprimir=false;
            this.modificar=true;  
        }
        
         public void onRowEdit(ActionEvent event,detalleFactura detalle) {
             //detallefactura=this.detallefactura;
             this.detallefactura=detallefacturaEJB.find(detalle.getId());
             this.factura=this.detallefactura.getFactura();
             //Eliminar suma acumulada para este detalle
             //Si es factura vneto tiene almacenado un valor total, se debe obterner el valor neto
              if(!"FCF".equals(this.detallefactura.getFactura().getCorrelativodoc().getTipodocumento().getCodigo())){
                     //this.acumulatorVentagra=this.acumulatorVentagra.subtract(this.detallefactura.getVneto());
                     factura.setAcumventagra(factura.getAcumventagra().subtract(this.detallefactura.getVneto()));
                }else{
                     //this.acumulatorVentagra=this.acumulatorVentagra.subtract(this.detallefactura.getVneto().divide(new BigDecimal("1.13"),2,RoundingMode.HALF_UP));
                     factura.setAcumventagra(factura.getAcumventagra().subtract(this.detallefactura.getVneto().divide(new BigDecimal("1.13"),2,RoundingMode.HALF_UP)));
                     this.vafectafactura=this.vafectafactura.subtract(this.detallefactura.getVneto());
                    }
            //this.acumulatorVentaex=this.acumulatorVentaex.subtract(this.detallefactura.getVexento());
            factura.setAcumventaex(factura.getAcumventaex().subtract(this.detallefactura.getVexento()));
            //this.acumulatorVentans=this.acumulatorVentans.subtract(this.detallefactura.getVnogravado());
            factura.setAcumventans(factura.getAcumventans().subtract(this.detallefactura.getVnogravado()));
            
            BigDecimal gravado=this.detallefactura.getVneto();
            BigDecimal exento=this.detallefactura.getVexento();
            BigDecimal nosujeto=this.detallefactura.getVnogravado();
            
            if (!gravado.equals(new BigDecimal(0))){
                this.tipoventa=0;
            }
            if(!exento.equals(new BigDecimal(0))){
                this.tipoventa=1;
            }
            if(!nosujeto.equals(new BigDecimal(0))){
                this.tipoventa=2;
            }
            this.modificar=false;
            
            this.producto=this.detallefactura.getProducto();
            if (this.detallefactura.getVehiculomodelo() != null){
                this.marcasdet=marcavehiculoEJB.findAll();   
                this.marcavehiculodet=this.detallefactura.getVehiculomodelo().getMarcavehiculo();
                this.vehiculosmodelodet=marcavehiculoEJB.modelosXmarca(marcavehiculodet);
                this.vehiculomodelodet=this.detallefactura.getVehiculomodelo();
                this.setMarcavehiculodet(marcavehiculodet);
                this.setVehiculomodelodet(vehiculomodelodet);
            }
            this.agregar=true;
            this.imprimir=true;
            this.enmodificacion=true;
        }
     
        public void onRowDelete(ActionEvent event, detalleFactura detalle) {
            this.detallefactura=detalle;
            //Eliminar suma acumulada para proceder a eliminar item de detalle
            //Si es factura vneto tiene almacenado un valor total, se debe obterner el valor neto
            if(!"FCF".equals(this.detallefactura.getFactura().getCorrelativodoc().getTipodocumento().getCodigo())){
                     //this.acumulatorVentagra=this.acumulatorVentagra.subtract(this.detallefactura.getVneto());
                     factura.setAcumventagra(factura.getAcumventagra().subtract(this.detallefactura.getVneto()));
                }else{
                     //this.acumulatorVentagra=this.acumulatorVentagra.subtract(this.detallefactura.getVneto().divide(new BigDecimal("1.13"),2,RoundingMode.HALF_UP));
                     factura.setAcumventagra(factura.getAcumventagra().subtract(this.detallefactura.getVneto().divide(new BigDecimal("1.13"),2,RoundingMode.HALF_UP)));
                     this.vafectafactura=this.vafectafactura.subtract(this.detallefactura.getVneto());
                    }
            //this.acumulatorVentaex=this.acumulatorVentaex.subtract(this.detallefactura.getVexento());
            factura.setAcumventaex(factura.getAcumventaex().subtract(this.detallefactura.getVexento()));
            //this.acumulatorVentans=this.acumulatorVentans.subtract(this.detallefactura.getVnogravado());
            factura.setAcumventans(factura.getAcumventans().subtract(this.detallefactura.getVnogravado()));
            //recalcular subtotal y retenciones
            
            //verificar tipo de documento
            if(!"FCF".equals(this.detallefactura.getFactura().getCorrelativodoc().getTipodocumento().getCodigo())){
                this.setIva(factura.getAcumventagra().multiply(new BigDecimal("0.13")));
                this.factura.setRetencion(factura.getAcumventagra().multiply(new BigDecimal("0.01")));
                this.setSubtotal(factura.getAcumventagra().multiply(new BigDecimal("1.13")));
                this.factura.setTotalventa(this.subtotal.subtract(this.factura.getRetencion()).add(factura.getAcumventaex()).add(factura.getAcumventans()));
                this.factura.setTventaenletras(NumeroLetras.cantidadConLetra(this.factura.getTotalventa().toString()));
                
            }else{
                BigDecimal vneto=factura.getAcumventagra();
                this.setIva(vneto.multiply(new BigDecimal("0.13")));
                this.factura.setRetencion(vneto.multiply(new BigDecimal("0.01")));
                this.setSubtotal(vneto.multiply(new BigDecimal("1.13")).subtract(this.factura.getRetencion()));
                this.factura.setTotalventa(this.subtotal.add(factura.getAcumventaex()).add(factura.getAcumventans()));
                this.factura.setTventaenletras(NumeroLetras.cantidadConLetra(this.factura.getTotalventa().toString()));
            }
            //ACTUALIZAR ACUM. EN FACTURA
            facturaEJB.edit(factura);
            
                //buscar detalle y eliminar
            detallefactura=detallefacturaEJB.find(this.detallefactura.getId());
            detallefacturaEJB.remove(this.detallefactura);
            detallefactura=new detalleFactura();
            //actualizar lista de detalles
            this.detallesfactura=detallefacturaEJB.detalleDeFactura(this.factura.getId());
            if(this.detallesfactura.isEmpty()==true){
                this.detallesfactura=null;
            }
            
            FacesMessage msg = new FacesMessage("Item Eliminado");
            FacesContext.getCurrentInstance().addMessage(null, msg);        
    }
        //acciones a realizar al finalizar una factura
        public String finalizarFactura(){  
              BigDecimal retencion=factura.getRetencion();
              BigDecimal totalventa=factura.getTotalventa();
              String tventaenletras=factura.getTventaenletras();
              this.factura=facturaEJB.find(factura.getId());
              this.factura.setPersona(personaEJB.find(cliente.getPersona().getId()));
              if(vehiculomodelo.getId()!=0){
                this.factura.setVehiculomodelo(vehiculomodelo);
              }
              this.factura.setRetencion(retencion);
              this.factura.setTotalventa(totalventa);
              this.factura.setTventaenletras(tventaenletras);
              this.factura.setCliente(cliente);
              this.factura.setCorrelativodoc(this.correlativodoc);
              this.factura.setCondpago("cred");
              this.factura.setEstado("Finalizado");
                facturaEJB.edit(factura);
                this.setFacturaaimprimir(factura);
                cliente= new Cliente();
                detallefactura=new detalleFactura();
                producto= new Producto();
                this.setDetallesfactura(null);
                this.modificarencabezado=false;
                factura= new Factura();
                factura.setAcumventaex(new BigDecimal(0));
                factura.setAcumventagra(new BigDecimal(0));
                factura.setAcumventans(new BigDecimal(0));
                this.iva=new BigDecimal(0);
                this.subtotal=new BigDecimal(0);
                
        FacesContext.getCurrentInstance().addMessage(
        null, new FacesMessage("FACTURA INGRESADA CON EXITO"));
 
        FacesContext.getCurrentInstance()
            .getExternalContext()
            .getFlash().setKeepMessages(true);
        
            UIViewRoot view = FacesContext.getCurrentInstance().getViewRoot();
            return view.getViewId() + "?faces-redirect=true&includeViewParams=true"; 
        }
        //modificar datos generales de una factura
        
        public void modificarEncabezadofact(){
            
              BigDecimal retencion=factura.getRetencion();
              BigDecimal totalventa=factura.getTotalventa();
              String tventaenletras=factura.getTventaenletras();
              
              this.setCorrelativodoc(tipodocumentoEJB.correlativoDocUso(this.tipodocumento.getId()));
              //this.factura=facturaEJB.find(factura.getId());
              this.factura.setPersona(personaEJB.find(cliente.getPersona().getId()));
              //if(this.vehiculomodelo.getId()!=0){
              if(vehiculomodelo.getId()==0){
                this.factura.setVehiculomodelo(null);
              }else{this.factura.setVehiculomodelo(this.vehiculomodelo);}
              
              this.factura.setRetencion(retencion);
              this.factura.setTotalventa(totalventa);
              this.factura.setTventaenletras(tventaenletras);
              this.factura.setCliente(this.cliente);
              this.factura.setCorrelativodoc(this.correlativodoc);
              this.factura.setCondpago("cred");
              //Si se cambia de ccf a consumidor final recalcular acumuladores que posean iva
              
              if(detallefacturaEJB.detalleDeFactura(this.factura.getId()).isEmpty()==false){
                if ("CCF".equals(facturaEJB.find(this.factura.getId()).getCorrelativodoc().getTipodocumento().getCodigo()) && "FCF".equals(correlativodoc.getTipodocumento().getCodigo())){
                    factura.setAcumventagra(totalventa.add(retencion).divide(new BigDecimal("1.13")));
                    //acumulatorVentagra=totalventa.add(retencion).divide(new BigDecimal("1.13"));
                    this.vafectafactura=factura.getAcumventagra();
                    this.factura.setRetencion(this.vafectafactura.divide(new BigDecimal("1.13"),2,RoundingMode.HALF_UP).multiply(new BigDecimal("0.01")));
                    this.subtotal=this.vafectafactura.subtract(this.factura.getRetencion());
                    this.factura.setTotalventa(this.subtotal.add(factura.getAcumventaex()).add(factura.getAcumventans()));
                }
                if("FCF".equals(facturaEJB.find(this.factura.getId()).getCorrelativodoc().getTipodocumento().getCodigo()) && "CCF".equals(correlativodoc.getTipodocumento().getCodigo())){
                    factura.setAcumventagra(this.vafectafactura);
                    //acumulatorVentagra=this.vafectafactura;
                    this.vafectafactura=new BigDecimal(0);
                    this.iva=factura.getAcumventagra().multiply(new BigDecimal("0.13"));
                    this.subtotal=factura.getAcumventagra().add(iva);
                    this.factura.setRetencion(factura.getAcumventagra().multiply(new BigDecimal("0.01")));
                    this.factura.setTotalventa(this.subtotal.subtract(retencion).add(factura.getAcumventaex()).add(factura.getAcumventans()));
                }
              }  
              facturaEJB.edit(this.factura);
              this.modificarencabezado=true;
              this.guardarcambios=false;
              this.agregar=false;
              this.finalizar=false;
              
        }
        
       public void imprimirFactura(ActionEvent actionEvent) throws JRException, IOException{
        
        //master   
        //factura=planillamoEJB.imprimirPlanillagen(factura.getId());  
        //hijo
        detallesfacturaaimprimir=facturaEJB.imprimirDetalle(this.facturaaimprimir.getId());
        //validar q exista al menos un detalle en la factura
        if(detallesfacturaaimprimir.isEmpty()==false){
        //para table(hijo)
        //JRBeanCollectionDataSource descuentosdatos = new JRBeanCollectionDataSource(detallesfacturaaimprimir);
        
        //MOSTRAR REPORTE DE ACUERDO A TIPO DE DOCUMENTO
        String ruta;
        if(facturaaimprimir.getCorrelativodoc().getTipodocumento().getIdsucursal()==1){
            //if(null != facturaaimprimir.getCorrelativodoc().getTipodocumento().getCodigo()) //serviradiadores
            switch (facturaaimprimir.getCorrelativodoc().getTipodocumento().getCodigo()) {
                case "FCF":
                    ruta="/reportes/fcf_pinten.jasper";//consumidor final servirad
                    break;
                case "CCF":
                    ruta="/reportes/ccf_servirad.jasper";//ccf servirad
                    break;
                case "NC":
                    ruta="/reportes/ccf_servirad.jasper";//nota credito servirad
                    break;
                default:
                    ruta="/reportes/ccf_servirad.jasper";//nota debito servirad
                    break;
            }
        
        
        }else{
            //if(null != facturaaimprimir.getCorrelativodoc().getTipodocumento().getCodigo()) //servipinten
            switch (facturaaimprimir.getCorrelativodoc().getTipodocumento().getCodigo()) {
                case "FCF":
                    ruta="/reportes/fcf_pinten.jasper";//consumidor final servirad
                    break;
                case "CCF":
                    ruta="/reportes/ccf_servirad.jasper";//ccf servirad
                    break;
                case "NC":
                    ruta="/reportes/ccf_servirad.jasper";//nota credito servirad
                    break;
                default:
                    ruta="/reportes/ccf_servirad.jasper";//nota debito servirad
                    break;
            }
        }
        
        File jasper= new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath(ruta));
        
        //JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(),new HashMap(),new JRBeanCollectionDataSource(elementosComprobantepago,false));
        
        HashMap params = new HashMap();
        //params.put("detallesfacturaaimprimir",detallesfacturaaimprimir);
        byte[] bytes=JasperRunManager.runReportToPdf(jasper.getPath(),params,new JRBeanCollectionDataSource(detallesfacturaaimprimir,false));
        
        
        HttpServletResponse response=(HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.setContentType("application/pdf");
        response.setContentLength(bytes.length);
        
        //response.addHeader("Content-disposition", "attachment; filename=" +this.factura.getCorrelativo());
        //reinicar elementos factura
        
        try (ServletOutputStream stream = response.getOutputStream()) {
            stream.write(bytes,0,bytes.length);
            stream.flush();
        }
        FacesContext.getCurrentInstance().responseComplete();
        
        }else{
                FacesContext.getCurrentInstance().addMessage(
                null, new FacesMessage("NO SE PUEDE IMPRIMIR FACTURA SIN ITEMS!!!"));
 
                FacesContext.getCurrentInstance()
                    .getExternalContext()
                    .getFlash().setKeepMessages(true);
        }
        
    }
       
     public void guardarServicio(){
         productoEJB.create(producto);
         productos=productoEJB.findAll();
         producto=new Producto();
     }
     
      public void cambiarModelos(){
        vehiculosmodelo=marcavehiculoEJB.modelosXmarca(marcavehiculo);  
    }
      
      public void cambiarModelosdet(){
        this.vehiculosmodelodet=marcavehiculoEJB.modelosXmarca(marcavehiculodet);
    }
      
    public void crearNuevo(){
            producto=new Producto(); 
    }
   
    public void recargarSelectOneMenu(){
        this.productos=productoEJB.findAll();
        producto=new Producto();
    }
}
//