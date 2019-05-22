package com.spintenfacturacion.model;

import com.spintenfacturacion.model.Persona;
import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-05-22T12:00:58")
@StaticMetamodel(Cliente.class)
public class Cliente_ { 

    public static volatile SingularAttribute<Cliente, String> giro;
    public static volatile SingularAttribute<Cliente, String> nrc;
    public static volatile SingularAttribute<Cliente, String> rangocontribuy;
    public static volatile SingularAttribute<Cliente, BigDecimal> diascredito;
    public static volatile SingularAttribute<Cliente, String> nombrecomercial;
    public static volatile SingularAttribute<Cliente, Persona> persona;

}