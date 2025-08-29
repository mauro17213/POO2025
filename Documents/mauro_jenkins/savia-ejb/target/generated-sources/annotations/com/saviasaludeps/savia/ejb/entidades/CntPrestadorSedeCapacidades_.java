package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(CntPrestadorSedeCapacidades.class)
public abstract class CntPrestadorSedeCapacidades_ {

	public static volatile SingularAttribute<CntPrestadorSedeCapacidades, String> maeUnidadCapacidadCodigo;
	public static volatile SingularAttribute<CntPrestadorSedeCapacidades, String> informaNit;
	public static volatile SingularAttribute<CntPrestadorSedeCapacidades, Integer> informaNivel;
	public static volatile SingularAttribute<CntPrestadorSedeCapacidades, String> terminalModifica;
	public static volatile SingularAttribute<CntPrestadorSedeCapacidades, CntPrestadorSedes> cntPrestadorSedesId;
	public static volatile SingularAttribute<CntPrestadorSedeCapacidades, Boolean> aplicaEse;
	public static volatile SingularAttribute<CntPrestadorSedeCapacidades, String> usuarioCrea;
	public static volatile SingularAttribute<CntPrestadorSedeCapacidades, String> maeUnidadCapacidadValor;
	public static volatile SingularAttribute<CntPrestadorSedeCapacidades, String> informaCorreoElectronico;
	public static volatile SingularAttribute<CntPrestadorSedeCapacidades, String> maeGrupoCapacidadCodigo;
	public static volatile SingularAttribute<CntPrestadorSedeCapacidades, String> terminalCrea;
	public static volatile SingularAttribute<CntPrestadorSedeCapacidades, String> informaNombreSede;
	public static volatile SingularAttribute<CntPrestadorSedeCapacidades, Integer> maeGrupoCapacidadId;
	public static volatile SingularAttribute<CntPrestadorSedeCapacidades, String> informaTelefono;
	public static volatile SingularAttribute<CntPrestadorSedeCapacidades, Date> fechaHoraCrea;
	public static volatile SingularAttribute<CntPrestadorSedeCapacidades, Date> fechaHoraModifica;
	public static volatile SingularAttribute<CntPrestadorSedeCapacidades, Integer> id;
	public static volatile SingularAttribute<CntPrestadorSedeCapacidades, Integer> cantidad;
	public static volatile SingularAttribute<CntPrestadorSedeCapacidades, String> informaCodigoHabilitacion;
	public static volatile SingularAttribute<CntPrestadorSedeCapacidades, String> informaDireccion;
	public static volatile SingularAttribute<CntPrestadorSedeCapacidades, String> maeGrupoCapacidadValor;
	public static volatile SingularAttribute<CntPrestadorSedeCapacidades, String> usuarioModifica;
	public static volatile SingularAttribute<CntPrestadorSedeCapacidades, Integer> maeUnidadCapacidadId;

	public static final String MAE_UNIDAD_CAPACIDAD_CODIGO = "maeUnidadCapacidadCodigo";
	public static final String INFORMA_NIT = "informaNit";
	public static final String INFORMA_NIVEL = "informaNivel";
	public static final String TERMINAL_MODIFICA = "terminalModifica";
	public static final String CNT_PRESTADOR_SEDES_ID = "cntPrestadorSedesId";
	public static final String APLICA_ESE = "aplicaEse";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String MAE_UNIDAD_CAPACIDAD_VALOR = "maeUnidadCapacidadValor";
	public static final String INFORMA_CORREO_ELECTRONICO = "informaCorreoElectronico";
	public static final String MAE_GRUPO_CAPACIDAD_CODIGO = "maeGrupoCapacidadCodigo";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String INFORMA_NOMBRE_SEDE = "informaNombreSede";
	public static final String MAE_GRUPO_CAPACIDAD_ID = "maeGrupoCapacidadId";
	public static final String INFORMA_TELEFONO = "informaTelefono";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String FECHA_HORA_MODIFICA = "fechaHoraModifica";
	public static final String ID = "id";
	public static final String CANTIDAD = "cantidad";
	public static final String INFORMA_CODIGO_HABILITACION = "informaCodigoHabilitacion";
	public static final String INFORMA_DIRECCION = "informaDireccion";
	public static final String MAE_GRUPO_CAPACIDAD_VALOR = "maeGrupoCapacidadValor";
	public static final String USUARIO_MODIFICA = "usuarioModifica";
	public static final String MAE_UNIDAD_CAPACIDAD_ID = "maeUnidadCapacidadId";

}

