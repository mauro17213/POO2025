package com.saviasaludeps.savia.ejb.entidades;

import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(MpMedicamentoPrincipiosActivos.class)
public abstract class MpMedicamentoPrincipiosActivos_ {

	public static volatile SingularAttribute<MpMedicamentoPrincipiosActivos, BigDecimal> cantidadContenido;
	public static volatile SingularAttribute<MpMedicamentoPrincipiosActivos, String> usuarioCrea;
	public static volatile SingularAttribute<MpMedicamentoPrincipiosActivos, MpPrescripcionMedicamentos> mpPrescripcionMedicamentosId;
	public static volatile SingularAttribute<MpMedicamentoPrincipiosActivos, Integer> consecutivoOrden;
	public static volatile SingularAttribute<MpMedicamentoPrincipiosActivos, String> terminalCrea;
	public static volatile SingularAttribute<MpMedicamentoPrincipiosActivos, String> unidadMedidaConcentracion;
	public static volatile SingularAttribute<MpMedicamentoPrincipiosActivos, Date> fechaHoraCrea;
	public static volatile SingularAttribute<MpMedicamentoPrincipiosActivos, String> codigoPrincipioActivo;
	public static volatile SingularAttribute<MpMedicamentoPrincipiosActivos, String> unidadCantidadContenido;
	public static volatile SingularAttribute<MpMedicamentoPrincipiosActivos, Integer> id;
	public static volatile SingularAttribute<MpMedicamentoPrincipiosActivos, BigDecimal> concecutivoCantidad;

	public static final String CANTIDAD_CONTENIDO = "cantidadContenido";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String MP_PRESCRIPCION_MEDICAMENTOS_ID = "mpPrescripcionMedicamentosId";
	public static final String CONSECUTIVO_ORDEN = "consecutivoOrden";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String UNIDAD_MEDIDA_CONCENTRACION = "unidadMedidaConcentracion";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String CODIGO_PRINCIPIO_ACTIVO = "codigoPrincipioActivo";
	public static final String UNIDAD_CANTIDAD_CONTENIDO = "unidadCantidadContenido";
	public static final String ID = "id";
	public static final String CONCECUTIVO_CANTIDAD = "concecutivoCantidad";

}

