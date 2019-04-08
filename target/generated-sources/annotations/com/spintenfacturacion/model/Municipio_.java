package com.spintenfacturacion.model;

import com.spintenfacturacion.model.Departamento;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-04-05T08:21:39")
@StaticMetamodel(Municipio.class)
public class Municipio_ { 

    public static volatile SingularAttribute<Municipio, Integer> id;
    public static volatile SingularAttribute<Municipio, String> nombremunicipio;
    public static volatile SingularAttribute<Municipio, Departamento> departamento;

}