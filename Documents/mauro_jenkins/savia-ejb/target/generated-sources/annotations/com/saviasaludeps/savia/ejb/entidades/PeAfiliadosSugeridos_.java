package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(PeAfiliadosSugeridos.class)
public abstract class PeAfiliadosSugeridos_ {

	public static volatile SingularAttribute<PeAfiliadosSugeridos, Short> estado;
	public static volatile SingularAttribute<PeAfiliadosSugeridos, Short> rechazoOrigen;
	public static volatile SingularAttribute<PeAfiliadosSugeridos, AucHospitalizaciones> aucHospitalizacionesId;
	public static volatile SingularAttribute<PeAfiliadosSugeridos, PeProgramas> peProgramasId;
	public static volatile ListAttribute<PeAfiliadosSugeridos, PeSugeridoAdjuntos> peSugeridoAdjuntosList;
	public static volatile SingularAttribute<PeAfiliadosSugeridos, String> rechazoObservacion;
	public static volatile SingularAttribute<PeAfiliadosSugeridos, Short> origen;
	public static volatile SingularAttribute<PeAfiliadosSugeridos, MpPrescripciones> mpPrescripcionesId;
	public static volatile ListAttribute<PeAfiliadosSugeridos, PeAfiliadosProgramas> peAfiliadosProgramasList;
	public static volatile SingularAttribute<PeAfiliadosSugeridos, AuAnexos3> auAnexos3Id;
	public static volatile SingularAttribute<PeAfiliadosSugeridos, String> terminalModifica;
	public static volatile SingularAttribute<PeAfiliadosSugeridos, AsegAfiliados> asegAfiliadosId;
	public static volatile SingularAttribute<PeAfiliadosSugeridos, String> usuarioCrea;
	public static volatile SingularAttribute<PeAfiliadosSugeridos, String> terminalCrea;
	public static volatile SingularAttribute<PeAfiliadosSugeridos, Date> fechaHoraCrea;
	public static volatile SingularAttribute<PeAfiliadosSugeridos, Date> fechaHoraModifica;
	public static volatile SingularAttribute<PeAfiliadosSugeridos, Integer> id;
	public static volatile SingularAttribute<PeAfiliadosSugeridos, RefAnexos9> refAnexos9Id;
	public static volatile SingularAttribute<PeAfiliadosSugeridos, String> observacion;
	public static volatile SingularAttribute<PeAfiliadosSugeridos, String> usuarioModifica;

	public static final String ESTADO = "estado";
	public static final String RECHAZO_ORIGEN = "rechazoOrigen";
	public static final String AUC_HOSPITALIZACIONES_ID = "aucHospitalizacionesId";
	public static final String PE_PROGRAMAS_ID = "peProgramasId";
	public static final String PE_SUGERIDO_ADJUNTOS_LIST = "peSugeridoAdjuntosList";
	public static final String RECHAZO_OBSERVACION = "rechazoObservacion";
	public static final String ORIGEN = "origen";
	public static final String MP_PRESCRIPCIONES_ID = "mpPrescripcionesId";
	public static final String PE_AFILIADOS_PROGRAMAS_LIST = "peAfiliadosProgramasList";
	public static final String AU_ANEXOS3_ID = "auAnexos3Id";
	public static final String TERMINAL_MODIFICA = "terminalModifica";
	public static final String ASEG_AFILIADOS_ID = "asegAfiliadosId";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String FECHA_HORA_MODIFICA = "fechaHoraModifica";
	public static final String ID = "id";
	public static final String REF_ANEXOS9_ID = "refAnexos9Id";
	public static final String OBSERVACION = "observacion";
	public static final String USUARIO_MODIFICA = "usuarioModifica";

}

