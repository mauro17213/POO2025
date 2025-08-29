package com.saviasaludeps.savia.ejb.entidades;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(MaDiagnosticoMedicamentos.class)
public abstract class MaDiagnosticoMedicamentos_ {

	public static volatile SingularAttribute<MaDiagnosticoMedicamentos, MaMedicamentos> maMedicamentosId;
	public static volatile SingularAttribute<MaDiagnosticoMedicamentos, MaDiagnosticos> maDiagniosticosId;
	public static volatile SingularAttribute<MaDiagnosticoMedicamentos, Integer> id;
	public static volatile SingularAttribute<MaDiagnosticoMedicamentos, Boolean> activo;

	public static final String MA_MEDICAMENTOS_ID = "maMedicamentosId";
	public static final String MA_DIAGNIOSTICOS_ID = "maDiagniosticosId";
	public static final String ID = "id";
	public static final String ACTIVO = "activo";

}

