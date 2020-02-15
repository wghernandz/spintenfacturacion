package com.spintenfacturacion.model;

import com.spintenfacturacion.model.Persona;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-08-31T12:02:16")
@StaticMetamodel(Usuario.class)
public class Usuario_ { 

    public static volatile SingularAttribute<Usuario, String> username;
    public static volatile SingularAttribute<Usuario, String> password;
    public static volatile SingularAttribute<Usuario, Persona> persona;

}