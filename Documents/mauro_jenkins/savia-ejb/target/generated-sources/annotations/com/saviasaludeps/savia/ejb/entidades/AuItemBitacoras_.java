package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(AuItemBitacoras.class)
public abstract class AuItemBitacoras_ {

	public static volatile SingularAttribute<AuItemBitacoras, String> descripcion;
	public static volatile SingularAttribute<AuItemBitacoras, Integer> tipo;
	public static volatile SingularAttribute<AuItemBitacoras, Integer> estado;
	public static volatile SingularAttribute<AuItemBitacoras, String> usuarioCrea;
	public static volatile SingularAttribute<AuItemBitacoras, String> terminalCrea;
	public static volatile SingularAttribute<AuItemBitacoras, AuAnexo3Items> auAnexo3ItemsId;
	public static volatile SingularAttribute<AuItemBitacoras, Date> fechaHoraCrea;
	public static volatile SingularAttribute<AuItemBitacoras, Integer> id;

	public static final String DESCRIPCION = "descripcion";
	public static final String TIPO = "tipo";
	public static final String ESTADO = "estado";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String AU_ANEXO3_ITEMS_ID = "auAnexo3ItemsId";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String ID = "id";

}

