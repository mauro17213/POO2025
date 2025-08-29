package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(AuAnexo3CargaAnuladas.class)
public abstract class AuAnexo3CargaAnuladas_ {

	public static volatile SingularAttribute<AuAnexo3CargaAnuladas, Integer> exitosos;
	public static volatile SingularAttribute<AuAnexo3CargaAnuladas, Integer> estado;
	public static volatile SingularAttribute<AuAnexo3CargaAnuladas, String> archivo;
	public static volatile SingularAttribute<AuAnexo3CargaAnuladas, String> ruta;
	public static volatile SingularAttribute<AuAnexo3CargaAnuladas, Integer> registros;
	public static volatile SingularAttribute<AuAnexo3CargaAnuladas, Date> fechaHoraInicio;
	public static volatile SingularAttribute<AuAnexo3CargaAnuladas, Boolean> existe;
	public static volatile SingularAttribute<AuAnexo3CargaAnuladas, String> nombre;
	public static volatile ListAttribute<AuAnexo3CargaAnuladas, AuAnexo3CargaAnuladaSucesos> auAnexo3CargaAnuladaSucesosList;
	public static volatile SingularAttribute<AuAnexo3CargaAnuladas, String> usuarioCrea;
	public static volatile SingularAttribute<AuAnexo3CargaAnuladas, String> terminalCrea;
	public static volatile SingularAttribute<AuAnexo3CargaAnuladas, Integer> fallidos;
	public static volatile SingularAttribute<AuAnexo3CargaAnuladas, Date> fechaHoraCrea;
	public static volatile SingularAttribute<AuAnexo3CargaAnuladas, Integer> id;
	public static volatile SingularAttribute<AuAnexo3CargaAnuladas, Date> fechaHoraFin;
	public static volatile SingularAttribute<AuAnexo3CargaAnuladas, GnEmpresas> gnEmpresasId;
	public static volatile ListAttribute<AuAnexo3CargaAnuladas, AuAnexos3> auAnexos3List;

	public static final String EXITOSOS = "exitosos";
	public static final String ESTADO = "estado";
	public static final String ARCHIVO = "archivo";
	public static final String RUTA = "ruta";
	public static final String REGISTROS = "registros";
	public static final String FECHA_HORA_INICIO = "fechaHoraInicio";
	public static final String EXISTE = "existe";
	public static final String NOMBRE = "nombre";
	public static final String AU_ANEXO3_CARGA_ANULADA_SUCESOS_LIST = "auAnexo3CargaAnuladaSucesosList";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String FALLIDOS = "fallidos";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String ID = "id";
	public static final String FECHA_HORA_FIN = "fechaHoraFin";
	public static final String GN_EMPRESAS_ID = "gnEmpresasId";
	public static final String AU_ANEXOS3_LIST = "auAnexos3List";

}

