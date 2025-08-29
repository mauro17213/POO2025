package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(CntjPlantillas.class)
public abstract class CntjPlantillas_ {

	public static volatile SingularAttribute<CntjPlantillas, String> descripcion;
	public static volatile SingularAttribute<CntjPlantillas, CntjProcesoDocumentos> cntjProcesoDocumentosId;
	public static volatile SingularAttribute<CntjPlantillas, String> nombre;
	public static volatile SingularAttribute<CntjPlantillas, String> version;
	public static volatile ListAttribute<CntjPlantillas, CntjEstadoPlantillas> cntjEstadoPlantillasList;
	public static volatile SingularAttribute<CntjPlantillas, String> terminalModifica;
	public static volatile SingularAttribute<CntjPlantillas, String> usuarioCrea;
	public static volatile SingularAttribute<CntjPlantillas, String> terminalCrea;
	public static volatile ListAttribute<CntjPlantillas, CntjDocumentos> cntjDocumentosList;
	public static volatile ListAttribute<CntjPlantillas, CntjPlantillaCampos> cntjPlantillaCamposList;
	public static volatile SingularAttribute<CntjPlantillas, Date> fechaHoraCrea;
	public static volatile SingularAttribute<CntjPlantillas, Date> fechaHoraModifica;
	public static volatile SingularAttribute<CntjPlantillas, Integer> id;
	public static volatile SingularAttribute<CntjPlantillas, String> estructura;
	public static volatile SingularAttribute<CntjPlantillas, String> usuarioModifica;
	public static volatile SingularAttribute<CntjPlantillas, Boolean> activo;

	public static final String DESCRIPCION = "descripcion";
	public static final String CNTJ_PROCESO_DOCUMENTOS_ID = "cntjProcesoDocumentosId";
	public static final String NOMBRE = "nombre";
	public static final String VERSION = "version";
	public static final String CNTJ_ESTADO_PLANTILLAS_LIST = "cntjEstadoPlantillasList";
	public static final String TERMINAL_MODIFICA = "terminalModifica";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String CNTJ_DOCUMENTOS_LIST = "cntjDocumentosList";
	public static final String CNTJ_PLANTILLA_CAMPOS_LIST = "cntjPlantillaCamposList";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String FECHA_HORA_MODIFICA = "fechaHoraModifica";
	public static final String ID = "id";
	public static final String ESTRUCTURA = "estructura";
	public static final String USUARIO_MODIFICA = "usuarioModifica";
	public static final String ACTIVO = "activo";

}

