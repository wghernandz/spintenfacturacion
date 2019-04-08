/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spintenfacturacion.ejb;

import com.spintenfacturacion.model.Departamento;
import com.spintenfacturacion.model.Municipio;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author willian
 */
@Local
public interface DepartamentoFacadeLocal {

    void create(Departamento departamento);

    void edit(Departamento departamento);

    void remove(Departamento departamento);

    Departamento find(Object id);

    List<Departamento> findAll();

    List<Departamento> findRange(int[] range);

    int count();
    
    List<Municipio> deptoXmunicipio(Departamento depto);
}
