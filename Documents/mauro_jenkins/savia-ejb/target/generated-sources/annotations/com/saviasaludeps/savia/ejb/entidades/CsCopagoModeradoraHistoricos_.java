package com.saviasaludeps.savia.ejb.entidades;

import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(CsCopagoModeradoraHistoricos.class)
public abstract class CsCopagoModeradoraHistoricos_ {

	public static volatile SingularAttribute<CsCopagoModeradoraHistoricos, Integer> maeTipoAfiliadoId;
	public static volatile SingularAttribute<CsCopagoModeradoraHistoricos, String> maeNivelSisbenCodigo;
	public static volatile SingularAttribute<CsCopagoModeradoraHistoricos, Integer> agno;
	public static volatile SingularAttribute<CsCopagoModeradoraHistoricos, String> maeNivelSisbenValor;
	public static volatile SingularAttribute<CsCopagoModeradoraHistoricos, Boolean> moderadoraCopago;
	public static volatile SingularAttribute<CsCopagoModeradoraHistoricos, Integer> valorEjecutado;
	public static volatile SingularAttribute<CsCopagoModeradoraHistoricos, BigDecimal> porcentajeCopago;
	public static volatile SingularAttribute<CsCopagoModeradoraHistoricos, Integer> maeRegimenId;
	public static volatile SingularAttribute<CsCopagoModeradoraHistoricos, String> maeRegimenValor;
	public static volatile SingularAttribute<CsCopagoModeradoraHistoricos, String> maeTipoAfiliadoValor;
	public static volatile SingularAttribute<CsCopagoModeradoraHistoricos, String> maeRegimenCodigo;
	public static volatile SingularAttribute<CsCopagoModeradoraHistoricos, AsegAfiliados> asegAfiliadosId;
	public static volatile SingularAttribute<CsCopagoModeradoraHistoricos, AuAnexos4> auAnexos4Id;
	public static volatile SingularAttribute<CsCopagoModeradoraHistoricos, String> usuarioCrea;
	public static volatile SingularAttribute<CsCopagoModeradoraHistoricos, Integer> valorProyectado;
	public static volatile SingularAttribute<CsCopagoModeradoraHistoricos, String> idAfiliado;
	public static volatile SingularAttribute<CsCopagoModeradoraHistoricos, String> maeTipoAfiliadoCodigo;
	public static volatile SingularAttribute<CsCopagoModeradoraHistoricos, String> terminalCrea;
	public static volatile SingularAttribute<CsCopagoModeradoraHistoricos, Integer> maeNivelSisbenId;
	public static volatile SingularAttribute<CsCopagoModeradoraHistoricos, CmFacturas> cmFacturasId;
	public static volatile SingularAttribute<CsCopagoModeradoraHistoricos, Date> fechaHoraCrea;
	public static volatile SingularAttribute<CsCopagoModeradoraHistoricos, Integer> id;
	public static volatile SingularAttribute<CsCopagoModeradoraHistoricos, String> categoriaIbc;
	public static volatile SingularAttribute<CsCopagoModeradoraHistoricos, Integer> valorModeradora;

	public static final String MAE_TIPO_AFILIADO_ID = "maeTipoAfiliadoId";
	public static final String MAE_NIVEL_SISBEN_CODIGO = "maeNivelSisbenCodigo";
	public static final String AGNO = "agno";
	public static final String MAE_NIVEL_SISBEN_VALOR = "maeNivelSisbenValor";
	public static final String MODERADORA_COPAGO = "moderadoraCopago";
	public static final String VALOR_EJECUTADO = "valorEjecutado";
	public static final String PORCENTAJE_COPAGO = "porcentajeCopago";
	public static final String MAE_REGIMEN_ID = "maeRegimenId";
	public static final String MAE_REGIMEN_VALOR = "maeRegimenValor";
	public static final String MAE_TIPO_AFILIADO_VALOR = "maeTipoAfiliadoValor";
	public static final String MAE_REGIMEN_CODIGO = "maeRegimenCodigo";
	public static final String ASEG_AFILIADOS_ID = "asegAfiliadosId";
	public static final String AU_ANEXOS4_ID = "auAnexos4Id";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String VALOR_PROYECTADO = "valorProyectado";
	public static final String ID_AFILIADO = "idAfiliado";
	public static final String MAE_TIPO_AFILIADO_CODIGO = "maeTipoAfiliadoCodigo";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String MAE_NIVEL_SISBEN_ID = "maeNivelSisbenId";
	public static final String CM_FACTURAS_ID = "cmFacturasId";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String ID = "id";
	public static final String CATEGORIA_IBC = "categoriaIbc";
	public static final String VALOR_MODERADORA = "valorModeradora";

}

