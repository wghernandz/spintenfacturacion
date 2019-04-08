/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spintenfacturacion.ejb;

import com.spintenfacturacion.model.correlativoDoc;
import com.spintenfacturacion.model.tipoDocumento;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author willian
 */
@Local
public interface tipoDocumentoFacadeLocal {

    void create(tipoDocumento tipoDocumento);

    void edit(tipoDocumento tipoDocumento);

    void remove(tipoDocumento tipoDocumento);

    tipoDocumento find(Object id);

    List<tipoDocumento> findAll();

    List<tipoDocumento> findRange(int[] range);

    int count();
    
    correlativoDoc correlativoDocUso(int tipodoc);
    
    List<tipoDocumento> docXsucursal(int idsucursal);
    
    public List<tipoDocumento> userServi();
}
