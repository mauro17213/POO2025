package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(CntContratoCoberturas.class)
public abstract class CntContratoCoberturas_ {

	public static volatile SingularAttribute<CntContratoCoberturas, String> terminalModifica;
	public static volatile SingularAttribute<CntContratoCoberturas, CntPrestadorSedes> cntPrestadorSedesId;
	public static volatile SingularAttribute<CntContratoCoberturas, String> usuarioCrea;
	public static volatile SingularAttribute<CntContratoCoberturas, GnUbicaciones> gnUbicacionesId;
	public static volatile SingularAttribute<CntContratoCoberturas, String> terminalCrea;
	public static volatile SingularAttribute<CntContratoCoberturas, CntContratoSedes> cntContratoSedesId;
	public static volatile SingularAttribute<CntContratoCoberturas, Date> fechaHoraCrea;
	public static volatile SingularAttribute<CntContratoCoberturas, Date> fechaHoraModifica;
	public static volatile SingularAttribute<CntContratoCoberturas, Integer> id;
	public static volatile SingularAttribute<CntContratoCoberturas, String> usuarioModifica;
	public static volatile SingularAttribute<CntContratoCoberturas, Boolean> activo;
	public static volatile SingularAttribute<CntContratoCoberturas, CntContratos> cntContratosId;

	public static final String TERMINAL_MODIFICA = "terminalModifica";
	public static final String CNT_PRESTADOR_SEDES_ID = "cntPrestadorSedesId";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String GN_UBICACIONES_ID = "gnUbicacionesId";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String CNT_CONTRATO_SEDES_ID = "cntContratoSedesId";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String FECHA_HORA_MODIFICA = "fechaHoraModifica";
	public static final String ID = "id";
	public static final String USUARIO_MODIFICA = "usuarioModifica";
	public static final String ACTIVO = "activo";
	public static final String CNT_CONTRATOS_ID = "cntContratosId";

}

