package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(CntjDocumentos.class)
public abstract class CntjDocumentos_ {

	public static volatile SingularAttribute<CntjDocumentos, String> descripcion;
	public static volatile SingularAttribute<CntjDocumentos, CntjPlantillas> cntjPlantillasId;
	public static volatile SingularAttribute<CntjDocumentos, Short> tipo;
	public static volatile SingularAttribute<CntjDocumentos, Boolean> documentoExiste;
	public static volatile SingularAttribute<CntjDocumentos, CntjContratos> cntjContratosId;
	public static volatile SingularAttribute<CntjDocumentos, String> nombre;
	public static volatile ListAttribute<CntjDocumentos, CntjEstadoEjecuciones> cntjEstadoEjecucionesList;
	public static volatile SingularAttribute<CntjDocumentos, String> documentoArchivo;
	public static volatile SingularAttribute<CntjDocumentos, String> terminalModifica;
	public static volatile SingularAttribute<CntjDocumentos, String> usuarioCrea;
	public static volatile SingularAttribute<CntjDocumentos, String> terminalCrea;
	public static volatile SingularAttribute<CntjDocumentos, Integer> etapaContratacion;
	public static volatile SingularAttribute<CntjDocumentos, String> documentoRuta;
	public static volatile SingularAttribute<CntjDocumentos, CntjExpedientes> cntjExpedientesId;
	public static volatile SingularAttribute<CntjDocumentos, Date> fechaHoraCrea;
	public static volatile SingularAttribute<CntjDocumentos, Date> fechaHoraModifica;
	public static volatile SingularAttribute<CntjDocumentos, Integer> id;
	public static volatile SingularAttribute<CntjDocumentos, String> usuarioModifica;
	public static volatile SingularAttribute<CntjDocumentos, String> documentoNombre;

	public static final String DESCRIPCION = "descripcion";
	public static final String CNTJ_PLANTILLAS_ID = "cntjPlantillasId";
	public static final String TIPO = "tipo";
	public static final String DOCUMENTO_EXISTE = "documentoExiste";
	public static final String CNTJ_CONTRATOS_ID = "cntjContratosId";
	public static final String NOMBRE = "nombre";
	public static final String CNTJ_ESTADO_EJECUCIONES_LIST = "cntjEstadoEjecucionesList";
	public static final String DOCUMENTO_ARCHIVO = "documentoArchivo";
	public static final String TERMINAL_MODIFICA = "terminalModifica";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String ETAPA_CONTRATACION = "etapaContratacion";
	public static final String DOCUMENTO_RUTA = "documentoRuta";
	public static final String CNTJ_EXPEDIENTES_ID = "cntjExpedientesId";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String FECHA_HORA_MODIFICA = "fechaHoraModifica";
	public static final String ID = "id";
	public static final String USUARIO_MODIFICA = "usuarioModifica";
	public static final String DOCUMENTO_NOMBRE = "documentoNombre";

}

