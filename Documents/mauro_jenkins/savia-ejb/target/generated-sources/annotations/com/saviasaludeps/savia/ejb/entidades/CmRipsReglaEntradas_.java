package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(CmRipsReglaEntradas.class)
public abstract class CmRipsReglaEntradas_ {

	public static volatile SingularAttribute<CmRipsReglaEntradas, Integer> tipo;
	public static volatile SingularAttribute<CmRipsReglaEntradas, String> archivo;
	public static volatile SingularAttribute<CmRipsReglaEntradas, String> campo;
	public static volatile SingularAttribute<CmRipsReglaEntradas, Integer> posicion;
	public static volatile SingularAttribute<CmRipsReglaEntradas, String> terminalModifica;
	public static volatile SingularAttribute<CmRipsReglaEntradas, String> usuarioCrea;
	public static volatile SingularAttribute<CmRipsReglaEntradas, CmRipsReglas> cmRipsReglasId;
	public static volatile SingularAttribute<CmRipsReglaEntradas, String> terminalCrea;
	public static volatile SingularAttribute<CmRipsReglaEntradas, Date> fechaHoraCrea;
	public static volatile SingularAttribute<CmRipsReglaEntradas, Date> fechaHoraModifica;
	public static volatile SingularAttribute<CmRipsReglaEntradas, Integer> id;
	public static volatile SingularAttribute<CmRipsReglaEntradas, Integer> orden;
	public static volatile SingularAttribute<CmRipsReglaEntradas, String> usuarioModifica;

	public static final String TIPO = "tipo";
	public static final String ARCHIVO = "archivo";
	public static final String CAMPO = "campo";
	public static final String POSICION = "posicion";
	public static final String TERMINAL_MODIFICA = "terminalModifica";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String CM_RIPS_REGLAS_ID = "cmRipsReglasId";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String FECHA_HORA_MODIFICA = "fechaHoraModifica";
	public static final String ID = "id";
	public static final String ORDEN = "orden";
	public static final String USUARIO_MODIFICA = "usuarioModifica";

}

