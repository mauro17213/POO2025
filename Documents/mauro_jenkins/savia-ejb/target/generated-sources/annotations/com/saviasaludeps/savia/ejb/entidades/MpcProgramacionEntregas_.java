package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(MpcProgramacionEntregas.class)
public abstract class MpcProgramacionEntregas_ {

	public static volatile SingularAttribute<MpcProgramacionEntregas, Short> estado;
	public static volatile SingularAttribute<MpcProgramacionEntregas, Boolean> entregaTotal;
	public static volatile SingularAttribute<MpcProgramacionEntregas, Short> numeroEntregaTotal;
	public static volatile SingularAttribute<MpcProgramacionEntregas, MpcPrescripciones> mpcPrescripcionesId;
	public static volatile SingularAttribute<MpcProgramacionEntregas, Short> causaNoEntrega;
	public static volatile SingularAttribute<MpcProgramacionEntregas, String> terminalModifica;
	public static volatile SingularAttribute<MpcProgramacionEntregas, Short> numeroEntrega;
	public static volatile SingularAttribute<MpcProgramacionEntregas, String> usuarioCrea;
	public static volatile SingularAttribute<MpcProgramacionEntregas, String> terminalCrea;
	public static volatile SingularAttribute<MpcProgramacionEntregas, Date> fechaEntrega;
	public static volatile SingularAttribute<MpcProgramacionEntregas, Date> fechaHoraCrea;
	public static volatile SingularAttribute<MpcProgramacionEntregas, Date> fechaHoraModifica;
	public static volatile SingularAttribute<MpcProgramacionEntregas, Integer> id;
	public static volatile SingularAttribute<MpcProgramacionEntregas, Integer> cantidad;
	public static volatile SingularAttribute<MpcProgramacionEntregas, String> usuarioModifica;
	public static volatile SingularAttribute<MpcProgramacionEntregas, GnEmpresas> gnEmpresasId;

	public static final String ESTADO = "estado";
	public static final String ENTREGA_TOTAL = "entregaTotal";
	public static final String NUMERO_ENTREGA_TOTAL = "numeroEntregaTotal";
	public static final String MPC_PRESCRIPCIONES_ID = "mpcPrescripcionesId";
	public static final String CAUSA_NO_ENTREGA = "causaNoEntrega";
	public static final String TERMINAL_MODIFICA = "terminalModifica";
	public static final String NUMERO_ENTREGA = "numeroEntrega";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String FECHA_ENTREGA = "fechaEntrega";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String FECHA_HORA_MODIFICA = "fechaHoraModifica";
	public static final String ID = "id";
	public static final String CANTIDAD = "cantidad";
	public static final String USUARIO_MODIFICA = "usuarioModifica";
	public static final String GN_EMPRESAS_ID = "gnEmpresasId";

}

