package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(PeProgramasTraslados.class)
public abstract class PeProgramasTraslados_ {

	public static volatile SingularAttribute<PeProgramasTraslados, AsegAfiliados> asegAfiliadosId;
	public static volatile SingularAttribute<PeProgramasTraslados, PeProgramas> peProgramaIdOrigen;
	public static volatile SingularAttribute<PeProgramasTraslados, String> usuarioCrea;
	public static volatile SingularAttribute<PeProgramasTraslados, String> terminalCrea;
	public static volatile SingularAttribute<PeProgramasTraslados, Date> fechaHoraCrea;
	public static volatile SingularAttribute<PeProgramasTraslados, PeAfiliadosProgramas> peAfiliadosProgramasId;
	public static volatile SingularAttribute<PeProgramasTraslados, Integer> id;
	public static volatile SingularAttribute<PeProgramasTraslados, String> observacion;
	public static volatile SingularAttribute<PeProgramasTraslados, PeProgramas> peProgramaIdDestino;

	public static final String ASEG_AFILIADOS_ID = "asegAfiliadosId";
	public static final String PE_PROGRAMA_ID_ORIGEN = "peProgramaIdOrigen";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String PE_AFILIADOS_PROGRAMAS_ID = "peAfiliadosProgramasId";
	public static final String ID = "id";
	public static final String OBSERVACION = "observacion";
	public static final String PE_PROGRAMA_ID_DESTINO = "peProgramaIdDestino";

}

