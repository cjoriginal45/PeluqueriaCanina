package com.mycompany.peluqueriacaninca.logica;

import com.mycompany.peluqueriacaninca.logica.Mascotas;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2024-01-06T12:20:06", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(Duenio.class)
public class Duenio_ { 

    public static volatile ListAttribute<Duenio, Mascotas> masco;
    public static volatile SingularAttribute<Duenio, String> direccion;
    public static volatile SingularAttribute<Duenio, String> celular;
    public static volatile SingularAttribute<Duenio, String> nombre;
    public static volatile SingularAttribute<Duenio, Integer> id_duenio;

}