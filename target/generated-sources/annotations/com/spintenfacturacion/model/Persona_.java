package com.spintenfacturacion.model;

import com.spintenfacturacion.model.Municipio;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-08-31T12:02:16")
@StaticMetamodel(Persona.class)
public class Persona_ { 

    public static volatile SingularAttribute<Persona, String> direccion;
    public static volatile SingularAttribute<Persona, String> segundoapellido;
    public static volatile SingularAttribute<Persona, String> primernombre;
    public static volatile SingularAttribute<Persona, String> pnit;
    public static volatile SingularAttribute<Persona, Date> fechaingreso;
    public static volatile SingularAttribute<Persona, String> primerapellido;
    public static volatile SingularAttribute<Persona, Integer> id;
    public static volatile SingularAttribute<Persona, Municipio> municipio;
    public static volatile SingularAttribute<Persona, String> email;
    public static volatile SingularAttribute<Persona, String> pdui;
    public static volatile SingularAttribute<Persona, String> segundonombre;
    public static volatile SingularAttribute<Persona, String> ptelefono1;
    public static volatile SingularAttribute<Persona, String> ptelefono2;
    public static volatile SingularAttribute<Persona, String> nombresociedad;

}