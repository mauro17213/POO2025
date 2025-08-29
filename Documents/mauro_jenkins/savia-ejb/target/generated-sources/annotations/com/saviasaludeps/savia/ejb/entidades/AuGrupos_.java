package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(AuGrupos.class)
public abstract class AuGrupos_ {

	public static volatile SingularAttribute<AuGrupos, String> descripcion;
	public static volatile SingularAttribute<AuGrupos, Boolean> pbs;
	public static volatile SingularAttribute<AuGrupos, Integer> ultimoUsuarioMedicoId;
	public static volatile ListAttribute<AuGrupos, AuGrupoUsuarios> auGrupoUsuariosList;
	public static volatile SingularAttribute<AuGrupos, Boolean> soloGrupo;
	public static volatile ListAttribute<AuGrupos, AuSeguimientos> auSeguimientosList;
	public static volatile SingularAttribute<AuGrupos, Boolean> esTecnologia;
	public static volatile SingularAttribute<AuGrupos, String> nombre;
	public static volatile SingularAttribute<AuGrupos, String> maeAmbitoValor;
	public static volatile SingularAttribute<AuGrupos, Boolean> generico;
	public static volatile SingularAttribute<AuGrupos, String> terminalModifica;
	public static volatile SingularAttribute<AuGrupos, String> usuarioCrea;
	public static volatile SingularAttribute<AuGrupos, String> maeAmbitoCodigo;
	public static volatile SingularAttribute<AuGrupos, String> terminalCrea;
	public static volatile SingularAttribute<AuGrupos, Integer> ultimoUsuarioAuxiliarId;
	public static volatile SingularAttribute<AuGrupos, Boolean> aplicaSeguimiento;
	public static volatile ListAttribute<AuGrupos, AuGrupoRegiones> auGrupoRegionesList;
	public static volatile SingularAttribute<AuGrupos, Date> fechaHoraCrea;
	public static volatile SingularAttribute<AuGrupos, Integer> id;
	public static volatile SingularAttribute<AuGrupos, Integer> orden;
	public static volatile ListAttribute<AuGrupos, GnUsuarios> gnUsuariosList;
	public static volatile SingularAttribute<AuGrupos, Boolean> esPaquete;
	public static volatile ListAttribute<AuGrupos, AuAnexo3Items> auAnexo3ItemsList;
	public static volatile SingularAttribute<AuGrupos, Integer> ultimoUsuarioOdontologoId;
	public static volatile ListAttribute<AuGrupos, AuGrupoHistoricos> auGrupoHistoricosList;
	public static volatile ListAttribute<AuGrupos, AuGrupoProgramas> auGrupoProgramasList;
	public static volatile SingularAttribute<AuGrupos, Boolean> esInsumo;
	public static volatile ListAttribute<AuGrupos, AuGrupoSedes> auGrupoSedesList;
	public static volatile SingularAttribute<AuGrupos, Integer> ultimoUsuarioEnfermeroId;
	public static volatile SingularAttribute<AuGrupos, Integer> ultimoUsuarioAuxiliarOralId;
	public static volatile ListAttribute<AuGrupos, AuGrupoTecnologias> auGrupoTecnologiasList;
	public static volatile SingularAttribute<AuGrupos, Integer> maeAmbitoId;
	public static volatile SingularAttribute<AuGrupos, Date> fechaHoraModifica;
	public static volatile ListAttribute<AuGrupos, AuGrupoDiagnosticos> auGrupoDiagnosticosList;
	public static volatile SingularAttribute<AuGrupos, Boolean> tutela;
	public static volatile SingularAttribute<AuGrupos, String> usuarioModifica;
	public static volatile SingularAttribute<AuGrupos, Boolean> activo;
	public static volatile SingularAttribute<AuGrupos, Boolean> esMedicamento;

	public static final String DESCRIPCION = "descripcion";
	public static final String PBS = "pbs";
	public static final String ULTIMO_USUARIO_MEDICO_ID = "ultimoUsuarioMedicoId";
	public static final String AU_GRUPO_USUARIOS_LIST = "auGrupoUsuariosList";
	public static final String SOLO_GRUPO = "soloGrupo";
	public static final String AU_SEGUIMIENTOS_LIST = "auSeguimientosList";
	public static final String ES_TECNOLOGIA = "esTecnologia";
	public static final String NOMBRE = "nombre";
	public static final String MAE_AMBITO_VALOR = "maeAmbitoValor";
	public static final String GENERICO = "generico";
	public static final String TERMINAL_MODIFICA = "terminalModifica";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String MAE_AMBITO_CODIGO = "maeAmbitoCodigo";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String ULTIMO_USUARIO_AUXILIAR_ID = "ultimoUsuarioAuxiliarId";
	public static final String APLICA_SEGUIMIENTO = "aplicaSeguimiento";
	public static final String AU_GRUPO_REGIONES_LIST = "auGrupoRegionesList";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String ID = "id";
	public static final String ORDEN = "orden";
	public static final String GN_USUARIOS_LIST = "gnUsuariosList";
	public static final String ES_PAQUETE = "esPaquete";
	public static final String AU_ANEXO3_ITEMS_LIST = "auAnexo3ItemsList";
	public static final String ULTIMO_USUARIO_ODONTOLOGO_ID = "ultimoUsuarioOdontologoId";
	public static final String AU_GRUPO_HISTORICOS_LIST = "auGrupoHistoricosList";
	public static final String AU_GRUPO_PROGRAMAS_LIST = "auGrupoProgramasList";
	public static final String ES_INSUMO = "esInsumo";
	public static final String AU_GRUPO_SEDES_LIST = "auGrupoSedesList";
	public static final String ULTIMO_USUARIO_ENFERMERO_ID = "ultimoUsuarioEnfermeroId";
	public static final String ULTIMO_USUARIO_AUXILIAR_ORAL_ID = "ultimoUsuarioAuxiliarOralId";
	public static final String AU_GRUPO_TECNOLOGIAS_LIST = "auGrupoTecnologiasList";
	public static final String MAE_AMBITO_ID = "maeAmbitoId";
	public static final String FECHA_HORA_MODIFICA = "fechaHoraModifica";
	public static final String AU_GRUPO_DIAGNOSTICOS_LIST = "auGrupoDiagnosticosList";
	public static final String TUTELA = "tutela";
	public static final String USUARIO_MODIFICA = "usuarioModifica";
	public static final String ACTIVO = "activo";
	public static final String ES_MEDICAMENTO = "esMedicamento";

}

