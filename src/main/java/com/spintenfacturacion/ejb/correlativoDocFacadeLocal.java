/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spintenfacturacion.ejb;

import com.spintenfacturacion.model.correlativoDoc;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author willian
 */
@Local
public interface correlativoDocFacadeLocal {

    void create(correlativoDoc correlativoDoc);

    void edit(correlativoDoc correlativoDoc);

    void remove(correlativoDoc correlativoDoc);

    correlativoDoc find(Object id);

    List<correlativoDoc> findAll();

    List<correlativoDoc> findRange(int[] range);

    int count();
    
}
