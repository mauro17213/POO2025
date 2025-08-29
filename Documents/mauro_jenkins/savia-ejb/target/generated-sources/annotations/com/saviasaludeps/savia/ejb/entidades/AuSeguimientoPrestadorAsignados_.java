package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(AuSeguimientoPrestadorAsignados.class)
public abstract class AuSeguimientoPrestadorAsignados_ {

	public static volatile SingularAttribute<AuSeguimientoPrestadorAsignados, Integer> estado;
	public static volatile SingularAttribute<AuSeguimientoPrestadorAsignados, Date> fechaHoraBorra;
	public static volatile SingularAttribute<AuSeguimientoPrestadorAsignados, String> usuarioBorra;
	public static volatile SingularAttribute<AuSeguimientoPrestadorAsignados, String> terminalModifica;
	public static volatile SingularAttribute<AuSeguimientoPrestadorAsignados, CntPrestadorSedes> cntPrestadorSedesId;
	public static volatile SingularAttribute<AuSeguimientoPrestadorAsignados, String> usuarioCrea;
	public static volatile SingularAttribute<AuSeguimientoPrestadorAsignados, String> terminalCrea;
	public static volatile SingularAttribute<AuSeguimientoPrestadorAsignados, Boolean> borrado;
	public static volatile SingularAttribute<AuSeguimientoPrestadorAsignados, Date> fechaHoraCrea;
	public static volatile SingularAttribute<AuSeguimientoPrestadorAsignados, Date> fechaHoraModifica;
	public static volatile SingularAttribute<AuSeguimientoPrestadorAsignados, String> terminalBorra;
	public static volatile SingularAttribute<AuSeguimientoPrestadorAsignados, Integer> id;
	public static volatile SingularAttribute<AuSeguimientoPrestadorAsignados, AuSeguimientos> auSeguimientosId;
	public static volatile SingularAttribute<AuSeguimientoPrestadorAsignados, String> usuarioModifica;

	public static final String ESTADO = "estado";
	public static final String FECHA_HORA_BORRA = "fechaHoraBorra";
	public static final String USUARIO_BORRA = "usuarioBorra";
	public static final String TERMINAL_MODIFICA = "terminalModifica";
	public static final String CNT_PRESTADOR_SEDES_ID = "cntPrestadorSedesId";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String BORRADO = "borrado";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String FECHA_HORA_MODIFICA = "fechaHoraModifica";
	public static final String TERMINAL_BORRA = "terminalBorra";
	public static final String ID = "id";
	public static final String AU_SEGUIMIENTOS_ID = "auSeguimientosId";
	public static final String USUARIO_MODIFICA = "usuarioModifica";

}

