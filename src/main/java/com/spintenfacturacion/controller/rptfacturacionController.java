/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spintenfacturacion.controller;

import com.spintenfacturacion.ejb.FacturaFacadeLocal;
import com.spintenfacturacion.model.Factura;
import java.awt.event.ActionEvent;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import static java.lang.System.out;
import java.text.DateFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleXlsReportConfiguration;
import net.sf.jasperreports.export.SimpleXlsxReportConfiguration;

/**
 *
 * @author willian
 */
@Named
@ViewScoped
public class rptfacturacionController implements Serializable {
    
    @EJB
    private FacturaFacadeLocal facturaEJB;
    
    private Factura factura;
    
    private List<Factura> facturas;
    
    @PostConstruct
    public void init(){
        factura= new Factura();
    
    }

    public FacturaFacadeLocal getFacturaEJB() {
        return facturaEJB;
    }

    public void setFacturaEJB(FacturaFacadeLocal facturaEJB) {
        this.facturaEJB = facturaEJB;
    }

    public Factura getFactura() {
        return factura;
    }

    public void setFactura(Factura factura) {
        this.factura = factura;
    }

    public List<Factura> getFacturas() {
        return facturas;
    }

    public void setFacturas(List<Factura> facturas) {
        this.facturas = facturas;
    }
    
     public void generarPorFecha(ActionEvent actionEvent,Date fechainicial,Date fechafinal,String tiporeporte) throws JRException, IOException{
        File jasper=null;
        facturas=facturaEJB.facturaRangofechaASC(fechainicial, fechafinal);
        if ("pdf".equals(tiporeporte)){
            
                jasper= new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/reportes/ventasxfecha.jasper"));
           
            //JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(),new HashMap(),new JRBeanCollectionDataSource(elementosComprobantepago,false));
            byte[] bytes=JasperRunManager.runReportToPdf(jasper.getPath(),null,new JRBeanCollectionDataSource(facturas,false));
        
            HttpServletResponse response=(HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            response.setContentType("application/pdf");
            response.setContentLength(bytes.length);
        
            try (ServletOutputStream stream = response.getOutputStream()) {
                stream.write(bytes,0,bytes.length);
                stream.flush();
            }
            FacesContext.getCurrentInstance().responseComplete();
        }
        else{//excel
                    Format formater= new SimpleDateFormat("dd-MM-yyyy");
                    jasper= new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/reportes/ventasxfecha.jasper"));
                    JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(),new HashMap(),new JRBeanCollectionDataSource(facturas,false));
                    HttpServletResponse httpServletResponse=(HttpServletResponse)FacesContext.getCurrentInstance().getExternalContext().getResponse(); 
                    httpServletResponse.addHeader("Content-disposition", "attachment; filename=paraCXC"+formater.format(new Date())+".xlsx"); 
                    ServletOutputStream servletOutputStream=httpServletResponse.getOutputStream(); 
                    JRXlsxExporter docxExporter=new JRXlsxExporter();
                    docxExporter.setExporterInput(new SimpleExporterInput(jasperPrint));
                    docxExporter.setExporterOutput(new SimpleOutputStreamExporterOutput(servletOutputStream));
                    //docxExporter.getCurrentJasperPrint();
                    SimpleXlsxReportConfiguration configuration = new SimpleXlsxReportConfiguration();
                    configuration.setRemoveEmptySpaceBetweenRows(true);
                    configuration.setRemoveEmptySpaceBetweenColumns(true);
                    configuration.setDetectCellType(true);
                     
                    configuration.setWhitePageBackground(false);
                    docxExporter.setConfiguration(configuration);
                    docxExporter.exportReport(); 
                    FacesContext.getCurrentInstance().responseComplete();           
        }
        
     }
    
}
