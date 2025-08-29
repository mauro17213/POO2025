package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(CmRipsAdServiciosAgrupados.class)
public abstract class CmRipsAdServiciosAgrupados_ {

	public static volatile SingularAttribute<CmRipsAdServiciosAgrupados, String> conceptoRips;
	public static volatile SingularAttribute<CmRipsAdServiciosAgrupados, String> archivoRuta;
	public static volatile SingularAttribute<CmRipsAdServiciosAgrupados, String> numeroFactura;
	public static volatile SingularAttribute<CmRipsAdServiciosAgrupados, String> archivoControl;
	public static volatile SingularAttribute<CmRipsAdServiciosAgrupados, String> archivoNombreOriginal;
	public static volatile SingularAttribute<CmRipsAdServiciosAgrupados, CmRipsCargas> cmRipsCargasId;
	public static volatile SingularAttribute<CmRipsAdServiciosAgrupados, String> cantidadServicios;
	public static volatile SingularAttribute<CmRipsAdServiciosAgrupados, String> valorUnitario;
	public static volatile SingularAttribute<CmRipsAdServiciosAgrupados, String> valorConcepto;
	public static volatile SingularAttribute<CmRipsAdServiciosAgrupados, String> usuarioCrea;
	public static volatile SingularAttribute<CmRipsAdServiciosAgrupados, String> codigoReps;
	public static volatile SingularAttribute<CmRipsAdServiciosAgrupados, String> archivoNombre;
	public static volatile SingularAttribute<CmRipsAdServiciosAgrupados, String> terminalCrea;
	public static volatile SingularAttribute<CmRipsAdServiciosAgrupados, Integer> fila;
	public static volatile SingularAttribute<CmRipsAdServiciosAgrupados, Date> fechaHoraCrea;
	public static volatile SingularAttribute<CmRipsAdServiciosAgrupados, Integer> id;

	public static final String CONCEPTO_RIPS = "conceptoRips";
	public static final String ARCHIVO_RUTA = "archivoRuta";
	public static final String NUMERO_FACTURA = "numeroFactura";
	public static final String ARCHIVO_CONTROL = "archivoControl";
	public static final String ARCHIVO_NOMBRE_ORIGINAL = "archivoNombreOriginal";
	public static final String CM_RIPS_CARGAS_ID = "cmRipsCargasId";
	public static final String CANTIDAD_SERVICIOS = "cantidadServicios";
	public static final String VALOR_UNITARIO = "valorUnitario";
	public static final String VALOR_CONCEPTO = "valorConcepto";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String CODIGO_REPS = "codigoReps";
	public static final String ARCHIVO_NOMBRE = "archivoNombre";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String FILA = "fila";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String ID = "id";

}

