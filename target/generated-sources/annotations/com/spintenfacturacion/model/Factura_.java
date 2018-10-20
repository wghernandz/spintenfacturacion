package com.spintenfacturacion.model;

import com.spintenfacturacion.model.Cliente;
import com.spintenfacturacion.model.Persona;
import com.spintenfacturacion.model.correlativoDoc;
import com.spintenfacturacion.model.vehiculoModelo;
import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-10-20T10:40:24")
@StaticMetamodel(Factura.class)
public class Factura_ { 

    public static volatile SingularAttribute<Factura, vehiculoModelo> vehiculomodelo;
    public static volatile SingularAttribute<Factura, Date> fecha;
    public static volatile SingularAttribute<Factura, BigDecimal> retencion;
    public static volatile SingularAttribute<Factura, Integer> correlativo;
    public static volatile SingularAttribute<Factura, Integer> id;
    public static volatile SingularAttribute<Factura, String> numsiniestro;
    public static volatile SingularAttribute<Factura, String> condpago;
    public static volatile SingularAttribute<Factura, String> ordencompra;
    public static volatile SingularAttribute<Factura, Cliente> cliente;
    public static volatile SingularAttribute<Factura, BigDecimal> totalventa;
    public static volatile SingularAttribute<Factura, String> placa;
    public static volatile SingularAttribute<Factura, correlativoDoc> correlativodoc;
    public static volatile SingularAttribute<Factura, Persona> persona;
    public static volatile SingularAttribute<Factura, String> tventaenletras;

}