package com.spintenfacturacion.model;

import com.spintenfacturacion.model.tipoDocumento;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-10-20T10:40:24")
@StaticMetamodel(correlativoDoc.class)
public class correlativoDoc_ { 

    public static volatile SingularAttribute<correlativoDoc, Integer> id;
    public static volatile SingularAttribute<correlativoDoc, Integer> cfinal;
    public static volatile SingularAttribute<correlativoDoc, tipoDocumento> tipodocumento;
    public static volatile SingularAttribute<correlativoDoc, String> estado;
    public static volatile SingularAttribute<correlativoDoc, String> serieautini;
    public static volatile SingularAttribute<correlativoDoc, String> serieautfin;
    public static volatile SingularAttribute<correlativoDoc, Date> fautorizacion;
    public static volatile SingularAttribute<correlativoDoc, String> nresolucion;
    public static volatile SingularAttribute<correlativoDoc, Integer> cinicial;

}