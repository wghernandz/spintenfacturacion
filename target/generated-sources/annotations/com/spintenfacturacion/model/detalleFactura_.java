package com.spintenfacturacion.model;

import com.spintenfacturacion.model.Factura;
import com.spintenfacturacion.model.Producto;
import com.spintenfacturacion.model.vehiculoModelo;
import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-08-31T12:02:16")
@StaticMetamodel(detalleFactura.class)
public class detalleFactura_ { 

    public static volatile SingularAttribute<detalleFactura, Integer> id;
    public static volatile SingularAttribute<detalleFactura, BigDecimal> vexento;
    public static volatile SingularAttribute<detalleFactura, BigDecimal> preciounitario;
    public static volatile SingularAttribute<detalleFactura, vehiculoModelo> vehiculomodelo;
    public static volatile SingularAttribute<detalleFactura, Factura> factura;
    public static volatile SingularAttribute<detalleFactura, BigDecimal> vneto;
    public static volatile SingularAttribute<detalleFactura, BigDecimal> cantidad;
    public static volatile SingularAttribute<detalleFactura, String> placa;
    public static volatile SingularAttribute<detalleFactura, Producto> producto;
    public static volatile SingularAttribute<detalleFactura, String> numequipo;
    public static volatile SingularAttribute<detalleFactura, BigDecimal> vnogravado;

}