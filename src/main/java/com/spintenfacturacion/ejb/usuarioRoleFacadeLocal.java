/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spintenfacturacion.ejb;

import com.spintenfacturacion.model.usuarioRole;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author willian
 */
@Local
public interface usuarioRoleFacadeLocal {

    void create(usuarioRole usuarioRole);

    void edit(usuarioRole usuarioRole);

    void remove(usuarioRole usuarioRole);

    usuarioRole find(Object id);

    List<usuarioRole> findAll();

    List<usuarioRole> findRange(int[] range);

    int count();
    
}
