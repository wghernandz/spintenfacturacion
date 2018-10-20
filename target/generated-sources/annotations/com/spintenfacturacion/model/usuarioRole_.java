package com.spintenfacturacion.model;

import com.spintenfacturacion.model.Role;
import com.spintenfacturacion.model.Usuario;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-10-20T10:40:24")
@StaticMetamodel(usuarioRole.class)
public class usuarioRole_ { 

    public static volatile SingularAttribute<usuarioRole, Integer> id;
    public static volatile SingularAttribute<usuarioRole, Usuario> usuario;
    public static volatile SingularAttribute<usuarioRole, Role> role;

}