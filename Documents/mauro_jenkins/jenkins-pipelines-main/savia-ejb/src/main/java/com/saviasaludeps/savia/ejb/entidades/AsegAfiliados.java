/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.saviasaludeps.savia.ejb.entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author stive
 */
@Entity
@Table(name = "aseg_afiliados")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AsegAfiliados.findAll", query = "SELECT a FROM AsegAfiliados a"),
    @NamedQuery(name = "AsegAfiliados.findById", query = "SELECT a FROM AsegAfiliados a WHERE a.id = :id"),
    @NamedQuery(name = "AsegAfiliados.findByIdAfiliado", query = "SELECT a FROM AsegAfiliados a WHERE a.idAfiliado = :idAfiliado"),
    @NamedQuery(name = "AsegAfiliados.findBySerialBdua", query = "SELECT a FROM AsegAfiliados a WHERE a.serialBdua = :serialBdua"),
    @NamedQuery(name = "AsegAfiliados.findByRegistroBdua", query = "SELECT a FROM AsegAfiliados a WHERE a.registroBdua = :registroBdua"),
    @NamedQuery(name = "AsegAfiliados.findByPrimerNombre", query = "SELECT a FROM AsegAfiliados a WHERE a.primerNombre = :primerNombre"),
    @NamedQuery(name = "AsegAfiliados.findBySegundoNombre", query = "SELECT a FROM AsegAfiliados a WHERE a.segundoNombre = :segundoNombre"),
    @NamedQuery(name = "AsegAfiliados.findByPrimerApellido", query = "SELECT a FROM AsegAfiliados a WHERE a.primerApellido = :primerApellido"),
    @NamedQuery(name = "AsegAfiliados.findBySegundoApellido", query = "SELECT a FROM AsegAfiliados a WHERE a.segundoApellido = :segundoApellido"),
    @NamedQuery(name = "AsegAfiliados.findByFechaNacimiento", query = "SELECT a FROM AsegAfiliados a WHERE a.fechaNacimiento = :fechaNacimiento"),
    @NamedQuery(name = "AsegAfiliados.findByGenero", query = "SELECT a FROM AsegAfiliados a WHERE a.genero = :genero"),
    @NamedQuery(name = "AsegAfiliados.findByMaeGeneroId", query = "SELECT a FROM AsegAfiliados a WHERE a.maeGeneroId = :maeGeneroId"),
    @NamedQuery(name = "AsegAfiliados.findByMaeGeneroCodigo", query = "SELECT a FROM AsegAfiliados a WHERE a.maeGeneroCodigo = :maeGeneroCodigo"),
    @NamedQuery(name = "AsegAfiliados.findByMaeGeneroValor", query = "SELECT a FROM AsegAfiliados a WHERE a.maeGeneroValor = :maeGeneroValor"),
    @NamedQuery(name = "AsegAfiliados.findByFechaExpedicionCedula", query = "SELECT a FROM AsegAfiliados a WHERE a.fechaExpedicionCedula = :fechaExpedicionCedula"),
    @NamedQuery(name = "AsegAfiliados.findByMaeTipoDocumentoId", query = "SELECT a FROM AsegAfiliados a WHERE a.maeTipoDocumentoId = :maeTipoDocumentoId"),
    @NamedQuery(name = "AsegAfiliados.findByMaeTipoDocumentoCodigo", query = "SELECT a FROM AsegAfiliados a WHERE a.maeTipoDocumentoCodigo = :maeTipoDocumentoCodigo"),
    @NamedQuery(name = "AsegAfiliados.findByMaeTipoDocumentoValor", query = "SELECT a FROM AsegAfiliados a WHERE a.maeTipoDocumentoValor = :maeTipoDocumentoValor"),
    @NamedQuery(name = "AsegAfiliados.findByNumeroDocumento", query = "SELECT a FROM AsegAfiliados a WHERE a.numeroDocumento = :numeroDocumento"),
    @NamedQuery(name = "AsegAfiliados.findByMaeTipoDocumentoCfId", query = "SELECT a FROM AsegAfiliados a WHERE a.maeTipoDocumentoCfId = :maeTipoDocumentoCfId"),
    @NamedQuery(name = "AsegAfiliados.findByMaeTipoDocumentoCfCodigo", query = "SELECT a FROM AsegAfiliados a WHERE a.maeTipoDocumentoCfCodigo = :maeTipoDocumentoCfCodigo"),
    @NamedQuery(name = "AsegAfiliados.findByMaeTipoDocumentoCfValor", query = "SELECT a FROM AsegAfiliados a WHERE a.maeTipoDocumentoCfValor = :maeTipoDocumentoCfValor"),
    @NamedQuery(name = "AsegAfiliados.findByNumeroDocumentoCf", query = "SELECT a FROM AsegAfiliados a WHERE a.numeroDocumentoCf = :numeroDocumentoCf"),
    @NamedQuery(name = "AsegAfiliados.findByFechaAfiliacionSgsss", query = "SELECT a FROM AsegAfiliados a WHERE a.fechaAfiliacionSgsss = :fechaAfiliacionSgsss"),
    @NamedQuery(name = "AsegAfiliados.findByFechaAfiliacionEps", query = "SELECT a FROM AsegAfiliados a WHERE a.fechaAfiliacionEps = :fechaAfiliacionEps"),
    @NamedQuery(name = "AsegAfiliados.findByAfiliacionLegalizada", query = "SELECT a FROM AsegAfiliados a WHERE a.afiliacionLegalizada = :afiliacionLegalizada"),
    @NamedQuery(name = "AsegAfiliados.findByFechaEgresoEps", query = "SELECT a FROM AsegAfiliados a WHERE a.fechaEgresoEps = :fechaEgresoEps"),
    @NamedQuery(name = "AsegAfiliados.findByFechaSuspensionEps", query = "SELECT a FROM AsegAfiliados a WHERE a.fechaSuspensionEps = :fechaSuspensionEps"),
    @NamedQuery(name = "AsegAfiliados.findByFechaCambioEstadoEps", query = "SELECT a FROM AsegAfiliados a WHERE a.fechaCambioEstadoEps = :fechaCambioEstadoEps"),
    @NamedQuery(name = "AsegAfiliados.findByTipoBeneficiario", query = "SELECT a FROM AsegAfiliados a WHERE a.tipoBeneficiario = :tipoBeneficiario"),
    @NamedQuery(name = "AsegAfiliados.findByMaeTipoAfiliadoId", query = "SELECT a FROM AsegAfiliados a WHERE a.maeTipoAfiliadoId = :maeTipoAfiliadoId"),
    @NamedQuery(name = "AsegAfiliados.findByMaeTipoAfiliadoCodigo", query = "SELECT a FROM AsegAfiliados a WHERE a.maeTipoAfiliadoCodigo = :maeTipoAfiliadoCodigo"),
    @NamedQuery(name = "AsegAfiliados.findByMaeTipoAfiliadoValor", query = "SELECT a FROM AsegAfiliados a WHERE a.maeTipoAfiliadoValor = :maeTipoAfiliadoValor"),
    @NamedQuery(name = "AsegAfiliados.findByMaeEstadoAfiliacionId", query = "SELECT a FROM AsegAfiliados a WHERE a.maeEstadoAfiliacionId = :maeEstadoAfiliacionId"),
    @NamedQuery(name = "AsegAfiliados.findByMaeEstadoAfiliacionCodigo", query = "SELECT a FROM AsegAfiliados a WHERE a.maeEstadoAfiliacionCodigo = :maeEstadoAfiliacionCodigo"),
    @NamedQuery(name = "AsegAfiliados.findByMaeEstadoAfiliacionValor", query = "SELECT a FROM AsegAfiliados a WHERE a.maeEstadoAfiliacionValor = :maeEstadoAfiliacionValor"),
    @NamedQuery(name = "AsegAfiliados.findByMaeOrigenAfiliadoId", query = "SELECT a FROM AsegAfiliados a WHERE a.maeOrigenAfiliadoId = :maeOrigenAfiliadoId"),
    @NamedQuery(name = "AsegAfiliados.findByMaeOrigenAfiliadoCodigo", query = "SELECT a FROM AsegAfiliados a WHERE a.maeOrigenAfiliadoCodigo = :maeOrigenAfiliadoCodigo"),
    @NamedQuery(name = "AsegAfiliados.findByMaeOrigenAfiliadoValor", query = "SELECT a FROM AsegAfiliados a WHERE a.maeOrigenAfiliadoValor = :maeOrigenAfiliadoValor"),
    @NamedQuery(name = "AsegAfiliados.findByParentescoCotizante", query = "SELECT a FROM AsegAfiliados a WHERE a.parentescoCotizante = :parentescoCotizante"),
    @NamedQuery(name = "AsegAfiliados.findByMaeParentescoCotizanteId", query = "SELECT a FROM AsegAfiliados a WHERE a.maeParentescoCotizanteId = :maeParentescoCotizanteId"),
    @NamedQuery(name = "AsegAfiliados.findByMaeParentescoCotizanteCodigo", query = "SELECT a FROM AsegAfiliados a WHERE a.maeParentescoCotizanteCodigo = :maeParentescoCotizanteCodigo"),
    @NamedQuery(name = "AsegAfiliados.findByMaeParentescoCotizanteValor", query = "SELECT a FROM AsegAfiliados a WHERE a.maeParentescoCotizanteValor = :maeParentescoCotizanteValor"),
    @NamedQuery(name = "AsegAfiliados.findByTratamientoDatosAutoriza", query = "SELECT a FROM AsegAfiliados a WHERE a.tratamientoDatosAutoriza = :tratamientoDatosAutoriza"),
    @NamedQuery(name = "AsegAfiliados.findByTratamientDatosFechaHora", query = "SELECT a FROM AsegAfiliados a WHERE a.tratamientDatosFechaHora = :tratamientDatosFechaHora"),
    @NamedQuery(name = "AsegAfiliados.findByAutorizaEnvioSms", query = "SELECT a FROM AsegAfiliados a WHERE a.autorizaEnvioSms = :autorizaEnvioSms"),
    @NamedQuery(name = "AsegAfiliados.findByAutorizaEnvioEmail", query = "SELECT a FROM AsegAfiliados a WHERE a.autorizaEnvioEmail = :autorizaEnvioEmail"),
    @NamedQuery(name = "AsegAfiliados.findByTelefonoFijo", query = "SELECT a FROM AsegAfiliados a WHERE a.telefonoFijo = :telefonoFijo"),
    @NamedQuery(name = "AsegAfiliados.findByTelefonoMovil", query = "SELECT a FROM AsegAfiliados a WHERE a.telefonoMovil = :telefonoMovil"),
    @NamedQuery(name = "AsegAfiliados.findByEmail", query = "SELECT a FROM AsegAfiliados a WHERE a.email = :email"),
    @NamedQuery(name = "AsegAfiliados.findByZona", query = "SELECT a FROM AsegAfiliados a WHERE a.zona = :zona"),
    @NamedQuery(name = "AsegAfiliados.findByMaeZonaId", query = "SELECT a FROM AsegAfiliados a WHERE a.maeZonaId = :maeZonaId"),
    @NamedQuery(name = "AsegAfiliados.findByMaeZonaCodigo", query = "SELECT a FROM AsegAfiliados a WHERE a.maeZonaCodigo = :maeZonaCodigo"),
    @NamedQuery(name = "AsegAfiliados.findByMaeZonaValor", query = "SELECT a FROM AsegAfiliados a WHERE a.maeZonaValor = :maeZonaValor"),
    @NamedQuery(name = "AsegAfiliados.findByDireccionResidencia", query = "SELECT a FROM AsegAfiliados a WHERE a.direccionResidencia = :direccionResidencia"),
    @NamedQuery(name = "AsegAfiliados.findByDireccionGeoreferenciada", query = "SELECT a FROM AsegAfiliados a WHERE a.direccionGeoreferenciada = :direccionGeoreferenciada"),
    @NamedQuery(name = "AsegAfiliados.findByDireccionGeorefLatitud", query = "SELECT a FROM AsegAfiliados a WHERE a.direccionGeorefLatitud = :direccionGeorefLatitud"),
    @NamedQuery(name = "AsegAfiliados.findByDireccionGeorefLongitud", query = "SELECT a FROM AsegAfiliados a WHERE a.direccionGeorefLongitud = :direccionGeorefLongitud"),
    @NamedQuery(name = "AsegAfiliados.findByBarrio", query = "SELECT a FROM AsegAfiliados a WHERE a.barrio = :barrio"),
    @NamedQuery(name = "AsegAfiliados.findByRegimen", query = "SELECT a FROM AsegAfiliados a WHERE a.regimen = :regimen"),
    @NamedQuery(name = "AsegAfiliados.findByMaeRegimenId", query = "SELECT a FROM AsegAfiliados a WHERE a.maeRegimenId = :maeRegimenId"),
    @NamedQuery(name = "AsegAfiliados.findByMaeRegimenCodigo", query = "SELECT a FROM AsegAfiliados a WHERE a.maeRegimenCodigo = :maeRegimenCodigo"),
    @NamedQuery(name = "AsegAfiliados.findByMaeRegimenValor", query = "SELECT a FROM AsegAfiliados a WHERE a.maeRegimenValor = :maeRegimenValor"),
    @NamedQuery(name = "AsegAfiliados.findByMaeGrupoSisbenId", query = "SELECT a FROM AsegAfiliados a WHERE a.maeGrupoSisbenId = :maeGrupoSisbenId"),
    @NamedQuery(name = "AsegAfiliados.findByMaeGrupoSisbenCodigo", query = "SELECT a FROM AsegAfiliados a WHERE a.maeGrupoSisbenCodigo = :maeGrupoSisbenCodigo"),
    @NamedQuery(name = "AsegAfiliados.findByMaeGrupoSisbenValor", query = "SELECT a FROM AsegAfiliados a WHERE a.maeGrupoSisbenValor = :maeGrupoSisbenValor"),
    @NamedQuery(name = "AsegAfiliados.findByMaeSubGrupoSisbenId", query = "SELECT a FROM AsegAfiliados a WHERE a.maeSubGrupoSisbenId = :maeSubGrupoSisbenId"),
    @NamedQuery(name = "AsegAfiliados.findByMaeSubGrupoSisbenCodigo", query = "SELECT a FROM AsegAfiliados a WHERE a.maeSubGrupoSisbenCodigo = :maeSubGrupoSisbenCodigo"),
    @NamedQuery(name = "AsegAfiliados.findByMaeSubGrupoSisbenValor", query = "SELECT a FROM AsegAfiliados a WHERE a.maeSubGrupoSisbenValor = :maeSubGrupoSisbenValor"),
    @NamedQuery(name = "AsegAfiliados.findByNivelSisben", query = "SELECT a FROM AsegAfiliados a WHERE a.nivelSisben = :nivelSisben"),
    @NamedQuery(name = "AsegAfiliados.findByMaeNivelSisbenId", query = "SELECT a FROM AsegAfiliados a WHERE a.maeNivelSisbenId = :maeNivelSisbenId"),
    @NamedQuery(name = "AsegAfiliados.findByMaeNivelSisbenCodigo", query = "SELECT a FROM AsegAfiliados a WHERE a.maeNivelSisbenCodigo = :maeNivelSisbenCodigo"),
    @NamedQuery(name = "AsegAfiliados.findByMaeNivelSisbenValor", query = "SELECT a FROM AsegAfiliados a WHERE a.maeNivelSisbenValor = :maeNivelSisbenValor"),
    @NamedQuery(name = "AsegAfiliados.findByPuntajeSisben", query = "SELECT a FROM AsegAfiliados a WHERE a.puntajeSisben = :puntajeSisben"),
    @NamedQuery(name = "AsegAfiliados.findByFichaSisben", query = "SELECT a FROM AsegAfiliados a WHERE a.fichaSisben = :fichaSisben"),
    @NamedQuery(name = "AsegAfiliados.findByFechaSisben", query = "SELECT a FROM AsegAfiliados a WHERE a.fechaSisben = :fechaSisben"),
    @NamedQuery(name = "AsegAfiliados.findByCategoriaIbc", query = "SELECT a FROM AsegAfiliados a WHERE a.categoriaIbc = :categoriaIbc"),
    @NamedQuery(name = "AsegAfiliados.findByMaeCategoriaIbcId", query = "SELECT a FROM AsegAfiliados a WHERE a.maeCategoriaIbcId = :maeCategoriaIbcId"),
    @NamedQuery(name = "AsegAfiliados.findByMaeCategoriaIbcCodigo", query = "SELECT a FROM AsegAfiliados a WHERE a.maeCategoriaIbcCodigo = :maeCategoriaIbcCodigo"),
    @NamedQuery(name = "AsegAfiliados.findByMaeCategoriaIbcValor", query = "SELECT a FROM AsegAfiliados a WHERE a.maeCategoriaIbcValor = :maeCategoriaIbcValor"),
    @NamedQuery(name = "AsegAfiliados.findByTienePortabilidad", query = "SELECT a FROM AsegAfiliados a WHERE a.tienePortabilidad = :tienePortabilidad"),
    @NamedQuery(name = "AsegAfiliados.findByFechaPortabilidad", query = "SELECT a FROM AsegAfiliados a WHERE a.fechaPortabilidad = :fechaPortabilidad"),
    @NamedQuery(name = "AsegAfiliados.findByTipoCotizante", query = "SELECT a FROM AsegAfiliados a WHERE a.tipoCotizante = :tipoCotizante"),
    @NamedQuery(name = "AsegAfiliados.findByMaeTipoCotizanteId", query = "SELECT a FROM AsegAfiliados a WHERE a.maeTipoCotizanteId = :maeTipoCotizanteId"),
    @NamedQuery(name = "AsegAfiliados.findByMaeTipoCotizanteCodigo", query = "SELECT a FROM AsegAfiliados a WHERE a.maeTipoCotizanteCodigo = :maeTipoCotizanteCodigo"),
    @NamedQuery(name = "AsegAfiliados.findByMaeTipoCotizanteValor", query = "SELECT a FROM AsegAfiliados a WHERE a.maeTipoCotizanteValor = :maeTipoCotizanteValor"),
    @NamedQuery(name = "AsegAfiliados.findByDiscapacidad", query = "SELECT a FROM AsegAfiliados a WHERE a.discapacidad = :discapacidad"),
    @NamedQuery(name = "AsegAfiliados.findByTipoDiscapacidad", query = "SELECT a FROM AsegAfiliados a WHERE a.tipoDiscapacidad = :tipoDiscapacidad"),
    @NamedQuery(name = "AsegAfiliados.findByMaeTipoDiscapacidadId", query = "SELECT a FROM AsegAfiliados a WHERE a.maeTipoDiscapacidadId = :maeTipoDiscapacidadId"),
    @NamedQuery(name = "AsegAfiliados.findByMaeTipoDiscapacidadCodigo", query = "SELECT a FROM AsegAfiliados a WHERE a.maeTipoDiscapacidadCodigo = :maeTipoDiscapacidadCodigo"),
    @NamedQuery(name = "AsegAfiliados.findByMaeTipoDiscapacidadValor", query = "SELECT a FROM AsegAfiliados a WHERE a.maeTipoDiscapacidadValor = :maeTipoDiscapacidadValor"),
    @NamedQuery(name = "AsegAfiliados.findByCondicionDiscapacidad", query = "SELECT a FROM AsegAfiliados a WHERE a.condicionDiscapacidad = :condicionDiscapacidad"),
    @NamedQuery(name = "AsegAfiliados.findByMaeDiscapacidadCondicionId", query = "SELECT a FROM AsegAfiliados a WHERE a.maeDiscapacidadCondicionId = :maeDiscapacidadCondicionId"),
    @NamedQuery(name = "AsegAfiliados.findByMaeDiscapacidadCondicionCodigo", query = "SELECT a FROM AsegAfiliados a WHERE a.maeDiscapacidadCondicionCodigo = :maeDiscapacidadCondicionCodigo"),
    @NamedQuery(name = "AsegAfiliados.findByMaeDiscapacidadCondicionValor", query = "SELECT a FROM AsegAfiliados a WHERE a.maeDiscapacidadCondicionValor = :maeDiscapacidadCondicionValor"),
    @NamedQuery(name = "AsegAfiliados.findByFechaInicioDiscapacidad", query = "SELECT a FROM AsegAfiliados a WHERE a.fechaInicioDiscapacidad = :fechaInicioDiscapacidad"),
    @NamedQuery(name = "AsegAfiliados.findByFechaFinDiscapacidad", query = "SELECT a FROM AsegAfiliados a WHERE a.fechaFinDiscapacidad = :fechaFinDiscapacidad"),
    @NamedQuery(name = "AsegAfiliados.findByMaeGrupoPoblacionalId", query = "SELECT a FROM AsegAfiliados a WHERE a.maeGrupoPoblacionalId = :maeGrupoPoblacionalId"),
    @NamedQuery(name = "AsegAfiliados.findByMaeGrupoPoblacionalCodigo", query = "SELECT a FROM AsegAfiliados a WHERE a.maeGrupoPoblacionalCodigo = :maeGrupoPoblacionalCodigo"),
    @NamedQuery(name = "AsegAfiliados.findByMaeGrupoPoblacionalValor", query = "SELECT a FROM AsegAfiliados a WHERE a.maeGrupoPoblacionalValor = :maeGrupoPoblacionalValor"),
    @NamedQuery(name = "AsegAfiliados.findByMaeMetodologiaGrupoPoblacionalId", query = "SELECT a FROM AsegAfiliados a WHERE a.maeMetodologiaGrupoPoblacionalId = :maeMetodologiaGrupoPoblacionalId"),
    @NamedQuery(name = "AsegAfiliados.findByMaeMetodologiaGrupoPoblacionalCodigo", query = "SELECT a FROM AsegAfiliados a WHERE a.maeMetodologiaGrupoPoblacionalCodigo = :maeMetodologiaGrupoPoblacionalCodigo"),
    @NamedQuery(name = "AsegAfiliados.findByMaeMetodologiaGrupoPoblacionalValor", query = "SELECT a FROM AsegAfiliados a WHERE a.maeMetodologiaGrupoPoblacionalValor = :maeMetodologiaGrupoPoblacionalValor"),
    @NamedQuery(name = "AsegAfiliados.findByVictima", query = "SELECT a FROM AsegAfiliados a WHERE a.victima = :victima"),
    @NamedQuery(name = "AsegAfiliados.findByEtnia", query = "SELECT a FROM AsegAfiliados a WHERE a.etnia = :etnia"),
    @NamedQuery(name = "AsegAfiliados.findByMaeEtniaId", query = "SELECT a FROM AsegAfiliados a WHERE a.maeEtniaId = :maeEtniaId"),
    @NamedQuery(name = "AsegAfiliados.findByMaeEtniaCodigo", query = "SELECT a FROM AsegAfiliados a WHERE a.maeEtniaCodigo = :maeEtniaCodigo"),
    @NamedQuery(name = "AsegAfiliados.findByMaeEtniaValor", query = "SELECT a FROM AsegAfiliados a WHERE a.maeEtniaValor = :maeEtniaValor"),
    @NamedQuery(name = "AsegAfiliados.findByMaeComunidadEtniaId", query = "SELECT a FROM AsegAfiliados a WHERE a.maeComunidadEtniaId = :maeComunidadEtniaId"),
    @NamedQuery(name = "AsegAfiliados.findByMaeComunidadEtniaCodigo", query = "SELECT a FROM AsegAfiliados a WHERE a.maeComunidadEtniaCodigo = :maeComunidadEtniaCodigo"),
    @NamedQuery(name = "AsegAfiliados.findByMaeComunidadEtniaValor", query = "SELECT a FROM AsegAfiliados a WHERE a.maeComunidadEtniaValor = :maeComunidadEtniaValor"),
    @NamedQuery(name = "AsegAfiliados.findByMaeCausaNovedadId", query = "SELECT a FROM AsegAfiliados a WHERE a.maeCausaNovedadId = :maeCausaNovedadId"),
    @NamedQuery(name = "AsegAfiliados.findByMaeCausaNovedadCodigo", query = "SELECT a FROM AsegAfiliados a WHERE a.maeCausaNovedadCodigo = :maeCausaNovedadCodigo"),
    @NamedQuery(name = "AsegAfiliados.findByMaeCausaNovedadValor", query = "SELECT a FROM AsegAfiliados a WHERE a.maeCausaNovedadValor = :maeCausaNovedadValor"),
    @NamedQuery(name = "AsegAfiliados.findByFechaReactivacion", query = "SELECT a FROM AsegAfiliados a WHERE a.fechaReactivacion = :fechaReactivacion"),
    @NamedQuery(name = "AsegAfiliados.findByEstadoCivil", query = "SELECT a FROM AsegAfiliados a WHERE a.estadoCivil = :estadoCivil"),
    @NamedQuery(name = "AsegAfiliados.findByMaeEstadoCivilId", query = "SELECT a FROM AsegAfiliados a WHERE a.maeEstadoCivilId = :maeEstadoCivilId"),
    @NamedQuery(name = "AsegAfiliados.findByMaeEstadoCivilCodigo", query = "SELECT a FROM AsegAfiliados a WHERE a.maeEstadoCivilCodigo = :maeEstadoCivilCodigo"),
    @NamedQuery(name = "AsegAfiliados.findByMaeEstadoCivilValor", query = "SELECT a FROM AsegAfiliados a WHERE a.maeEstadoCivilValor = :maeEstadoCivilValor"),
    @NamedQuery(name = "AsegAfiliados.findByFechaMovilidad", query = "SELECT a FROM AsegAfiliados a WHERE a.fechaMovilidad = :fechaMovilidad"),
    @NamedQuery(name = "AsegAfiliados.findByModeloLiquidacion", query = "SELECT a FROM AsegAfiliados a WHERE a.modeloLiquidacion = :modeloLiquidacion"),
    @NamedQuery(name = "AsegAfiliados.findByMaeModeloLiquidacionId", query = "SELECT a FROM AsegAfiliados a WHERE a.maeModeloLiquidacionId = :maeModeloLiquidacionId"),
    @NamedQuery(name = "AsegAfiliados.findByMaeModeloLiquidacionCodigo", query = "SELECT a FROM AsegAfiliados a WHERE a.maeModeloLiquidacionCodigo = :maeModeloLiquidacionCodigo"),
    @NamedQuery(name = "AsegAfiliados.findByMaeModeloLiquidacionValor", query = "SELECT a FROM AsegAfiliados a WHERE a.maeModeloLiquidacionValor = :maeModeloLiquidacionValor"),
    @NamedQuery(name = "AsegAfiliados.findByMaeTipoDocumentoAportanteId", query = "SELECT a FROM AsegAfiliados a WHERE a.maeTipoDocumentoAportanteId = :maeTipoDocumentoAportanteId"),
    @NamedQuery(name = "AsegAfiliados.findByMaeTipoDocumentoAportanteCodigo", query = "SELECT a FROM AsegAfiliados a WHERE a.maeTipoDocumentoAportanteCodigo = :maeTipoDocumentoAportanteCodigo"),
    @NamedQuery(name = "AsegAfiliados.findByMaeTipoDocumentoAportanteValor", query = "SELECT a FROM AsegAfiliados a WHERE a.maeTipoDocumentoAportanteValor = :maeTipoDocumentoAportanteValor"),
    @NamedQuery(name = "AsegAfiliados.findByNumeroDocumentoAportante", query = "SELECT a FROM AsegAfiliados a WHERE a.numeroDocumentoAportante = :numeroDocumentoAportante"),
    @NamedQuery(name = "AsegAfiliados.findByMaeActividadEconomicaId", query = "SELECT a FROM AsegAfiliados a WHERE a.maeActividadEconomicaId = :maeActividadEconomicaId"),
    @NamedQuery(name = "AsegAfiliados.findByMaeActividadEconomicaCodigo", query = "SELECT a FROM AsegAfiliados a WHERE a.maeActividadEconomicaCodigo = :maeActividadEconomicaCodigo"),
    @NamedQuery(name = "AsegAfiliados.findByMaeActividadEconomicaValor", query = "SELECT a FROM AsegAfiliados a WHERE a.maeActividadEconomicaValor = :maeActividadEconomicaValor"),
    @NamedQuery(name = "AsegAfiliados.findByMaeArlId", query = "SELECT a FROM AsegAfiliados a WHERE a.maeArlId = :maeArlId"),
    @NamedQuery(name = "AsegAfiliados.findByMaeArlCodigo", query = "SELECT a FROM AsegAfiliados a WHERE a.maeArlCodigo = :maeArlCodigo"),
    @NamedQuery(name = "AsegAfiliados.findByMaeArlValor", query = "SELECT a FROM AsegAfiliados a WHERE a.maeArlValor = :maeArlValor"),
    @NamedQuery(name = "AsegAfiliados.findByMaeAfpId", query = "SELECT a FROM AsegAfiliados a WHERE a.maeAfpId = :maeAfpId"),
    @NamedQuery(name = "AsegAfiliados.findByMaeAfpCodigo", query = "SELECT a FROM AsegAfiliados a WHERE a.maeAfpCodigo = :maeAfpCodigo"),
    @NamedQuery(name = "AsegAfiliados.findByMaeAfpValor", query = "SELECT a FROM AsegAfiliados a WHERE a.maeAfpValor = :maeAfpValor"),
    @NamedQuery(name = "AsegAfiliados.findByMaeCajaCompensacionId", query = "SELECT a FROM AsegAfiliados a WHERE a.maeCajaCompensacionId = :maeCajaCompensacionId"),
    @NamedQuery(name = "AsegAfiliados.findByMaeCajaCompensacionCodigo", query = "SELECT a FROM AsegAfiliados a WHERE a.maeCajaCompensacionCodigo = :maeCajaCompensacionCodigo"),
    @NamedQuery(name = "AsegAfiliados.findByMaeCajaCompensacionValor", query = "SELECT a FROM AsegAfiliados a WHERE a.maeCajaCompensacionValor = :maeCajaCompensacionValor"),
    @NamedQuery(name = "AsegAfiliados.findBySincronizado", query = "SELECT a FROM AsegAfiliados a WHERE a.sincronizado = :sincronizado"),
    @NamedQuery(name = "AsegAfiliados.findByObservacion", query = "SELECT a FROM AsegAfiliados a WHERE a.observacion = :observacion"),
    @NamedQuery(name = "AsegAfiliados.findByCodigoFonetico", query = "SELECT a FROM AsegAfiliados a WHERE a.codigoFonetico = :codigoFonetico"),
    @NamedQuery(name = "AsegAfiliados.findByOrigenUltimoRegistro", query = "SELECT a FROM AsegAfiliados a WHERE a.origenUltimoRegistro = :origenUltimoRegistro"),
    @NamedQuery(name = "AsegAfiliados.findByAceptaContribucionSolidaria", query = "SELECT a FROM AsegAfiliados a WHERE a.aceptaContribucionSolidaria = :aceptaContribucionSolidaria"),
    @NamedQuery(name = "AsegAfiliados.findByMaeSolidariaPorcentajeId", query = "SELECT a FROM AsegAfiliados a WHERE a.maeSolidariaPorcentajeId = :maeSolidariaPorcentajeId"),
    @NamedQuery(name = "AsegAfiliados.findByMaeSolidariaPorcentajeCodigo", query = "SELECT a FROM AsegAfiliados a WHERE a.maeSolidariaPorcentajeCodigo = :maeSolidariaPorcentajeCodigo"),
    @NamedQuery(name = "AsegAfiliados.findByMaeSolidariaPorcentajeValor", query = "SELECT a FROM AsegAfiliados a WHERE a.maeSolidariaPorcentajeValor = :maeSolidariaPorcentajeValor"),
    @NamedQuery(name = "AsegAfiliados.findByMaeGeneroIdentificacionId", query = "SELECT a FROM AsegAfiliados a WHERE a.maeGeneroIdentificacionId = :maeGeneroIdentificacionId"),
    @NamedQuery(name = "AsegAfiliados.findByMaeGeneroIdentificacionCodigo", query = "SELECT a FROM AsegAfiliados a WHERE a.maeGeneroIdentificacionCodigo = :maeGeneroIdentificacionCodigo"),
    @NamedQuery(name = "AsegAfiliados.findByMaeGeneroIdentificacionValor", query = "SELECT a FROM AsegAfiliados a WHERE a.maeGeneroIdentificacionValor = :maeGeneroIdentificacionValor"),
    @NamedQuery(name = "AsegAfiliados.findByIncapacidadProlongada", query = "SELECT a FROM AsegAfiliados a WHERE a.incapacidadProlongada = :incapacidadProlongada"),
    @NamedQuery(name = "AsegAfiliados.findByAcuerdoPago", query = "SELECT a FROM AsegAfiliados a WHERE a.acuerdoPago = :acuerdoPago"),
    @NamedQuery(name = "AsegAfiliados.findByFechaAcuerdoPago", query = "SELECT a FROM AsegAfiliados a WHERE a.fechaAcuerdoPago = :fechaAcuerdoPago"),
    @NamedQuery(name = "AsegAfiliados.findByUsuarioGestante", query = "SELECT a FROM AsegAfiliados a WHERE a.usuarioGestante = :usuarioGestante"),
    @NamedQuery(name = "AsegAfiliados.findByFechaFinPeriodoGestacion", query = "SELECT a FROM AsegAfiliados a WHERE a.fechaFinPeriodoGestacion = :fechaFinPeriodoGestacion"),
    @NamedQuery(name = "AsegAfiliados.findByDuplicado", query = "SELECT a FROM AsegAfiliados a WHERE a.duplicado = :duplicado"),
    @NamedQuery(name = "AsegAfiliados.findByTrasladoPreaprobado", query = "SELECT a FROM AsegAfiliados a WHERE a.trasladoPreaprobado = :trasladoPreaprobado"),
    @NamedQuery(name = "AsegAfiliados.findByDireccionAlternaContacto", query = "SELECT a FROM AsegAfiliados a WHERE a.direccionAlternaContacto = :direccionAlternaContacto"),
    @NamedQuery(name = "AsegAfiliados.findByNombreContactoEmergencia", query = "SELECT a FROM AsegAfiliados a WHERE a.nombreContactoEmergencia = :nombreContactoEmergencia"),
    @NamedQuery(name = "AsegAfiliados.findByTelefonoContactoEmergencia", query = "SELECT a FROM AsegAfiliados a WHERE a.telefonoContactoEmergencia = :telefonoContactoEmergencia"),
    @NamedQuery(name = "AsegAfiliados.findByMaeTipoPortabilidadId", query = "SELECT a FROM AsegAfiliados a WHERE a.maeTipoPortabilidadId = :maeTipoPortabilidadId"),
    @NamedQuery(name = "AsegAfiliados.findByMaeTipoPortabilidadCodigo", query = "SELECT a FROM AsegAfiliados a WHERE a.maeTipoPortabilidadCodigo = :maeTipoPortabilidadCodigo"),
    @NamedQuery(name = "AsegAfiliados.findByMaeTipoPortabilidadValor", query = "SELECT a FROM AsegAfiliados a WHERE a.maeTipoPortabilidadValor = :maeTipoPortabilidadValor"),
    @NamedQuery(name = "AsegAfiliados.findByMaeMotivoPortabilidadId", query = "SELECT a FROM AsegAfiliados a WHERE a.maeMotivoPortabilidadId = :maeMotivoPortabilidadId"),
    @NamedQuery(name = "AsegAfiliados.findByMaeMotivoPortabilidadCodigo", query = "SELECT a FROM AsegAfiliados a WHERE a.maeMotivoPortabilidadCodigo = :maeMotivoPortabilidadCodigo"),
    @NamedQuery(name = "AsegAfiliados.findByMaeMotivoPortabilidadValor", query = "SELECT a FROM AsegAfiliados a WHERE a.maeMotivoPortabilidadValor = :maeMotivoPortabilidadValor"),
    @NamedQuery(name = "AsegAfiliados.findByMaeOrigenSolicitudPortabilidadId", query = "SELECT a FROM AsegAfiliados a WHERE a.maeOrigenSolicitudPortabilidadId = :maeOrigenSolicitudPortabilidadId"),
    @NamedQuery(name = "AsegAfiliados.findByMaeOrigenSolicitudPortabilidadCodigo", query = "SELECT a FROM AsegAfiliados a WHERE a.maeOrigenSolicitudPortabilidadCodigo = :maeOrigenSolicitudPortabilidadCodigo"),
    @NamedQuery(name = "AsegAfiliados.findByMaeOrigenSolicitudPortabilidadValor", query = "SELECT a FROM AsegAfiliados a WHERE a.maeOrigenSolicitudPortabilidadValor = :maeOrigenSolicitudPortabilidadValor"),
    @NamedQuery(name = "AsegAfiliados.findByPeriodoInicialPortabilidad", query = "SELECT a FROM AsegAfiliados a WHERE a.periodoInicialPortabilidad = :periodoInicialPortabilidad"),
    @NamedQuery(name = "AsegAfiliados.findByPeriodoFinalPortabilidad", query = "SELECT a FROM AsegAfiliados a WHERE a.periodoFinalPortabilidad = :periodoFinalPortabilidad"),
    @NamedQuery(name = "AsegAfiliados.findByTelefonoContactoPortabilidad", query = "SELECT a FROM AsegAfiliados a WHERE a.telefonoContactoPortabilidad = :telefonoContactoPortabilidad"),
    @NamedQuery(name = "AsegAfiliados.findByDireccionPortabilidad", query = "SELECT a FROM AsegAfiliados a WHERE a.direccionPortabilidad = :direccionPortabilidad"),
    @NamedQuery(name = "AsegAfiliados.findByCorreoElectronicoPortabilidad", query = "SELECT a FROM AsegAfiliados a WHERE a.correoElectronicoPortabilidad = :correoElectronicoPortabilidad"),
    @NamedQuery(name = "AsegAfiliados.findByPoblacionEspecial", query = "SELECT a FROM AsegAfiliados a WHERE a.poblacionEspecial = :poblacionEspecial"),
    @NamedQuery(name = "AsegAfiliados.findByUsuarioCrea", query = "SELECT a FROM AsegAfiliados a WHERE a.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "AsegAfiliados.findByTerminalCrea", query = "SELECT a FROM AsegAfiliados a WHERE a.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "AsegAfiliados.findByFechaHoraCrea", query = "SELECT a FROM AsegAfiliados a WHERE a.fechaHoraCrea = :fechaHoraCrea"),
    @NamedQuery(name = "AsegAfiliados.findByUsuarioModifica", query = "SELECT a FROM AsegAfiliados a WHERE a.usuarioModifica = :usuarioModifica"),
    @NamedQuery(name = "AsegAfiliados.findByTerminalModifica", query = "SELECT a FROM AsegAfiliados a WHERE a.terminalModifica = :terminalModifica"),
    @NamedQuery(name = "AsegAfiliados.findByFechaHoraModifica", query = "SELECT a FROM AsegAfiliados a WHERE a.fechaHoraModifica = :fechaHoraModifica")})
public class AsegAfiliados implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "id_afiliado")
    private String idAfiliado;
    @Column(name = "serial_bdua")
    private BigInteger serialBdua;
    @Basic(optional = false)
    @NotNull
    @Column(name = "registro_bdua")
    private boolean registroBdua;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "primer_nombre")
    private String primerNombre;
    @Size(max = 64)
    @Column(name = "segundo_nombre")
    private String segundoNombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "primer_apellido")
    private String primerApellido;
    @Size(max = 64)
    @Column(name = "segundo_apellido")
    private String segundoApellido;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_nacimiento")
    @Temporal(TemporalType.DATE)
    private Date fechaNacimiento;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "genero")
    private String genero;
    @Column(name = "mae_genero_id")
    private Integer maeGeneroId;
    @Size(max = 8)
    @Column(name = "mae_genero_codigo")
    private String maeGeneroCodigo;
    @Size(max = 128)
    @Column(name = "mae_genero_valor")
    private String maeGeneroValor;
    @Column(name = "fecha_expedicion_cedula")
    @Temporal(TemporalType.DATE)
    private Date fechaExpedicionCedula;
    @Basic(optional = false)
    @NotNull
    @Column(name = "mae_tipo_documento_id")
    private int maeTipoDocumentoId;
    @Size(max = 8)
    @Column(name = "mae_tipo_documento_codigo")
    private String maeTipoDocumentoCodigo;
    @Size(max = 128)
    @Column(name = "mae_tipo_documento_valor")
    private String maeTipoDocumentoValor;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 16)
    @Column(name = "numero_documento")
    private String numeroDocumento;
    @Column(name = "mae_tipo_documento_cf_id")
    private Integer maeTipoDocumentoCfId;
    @Size(max = 8)
    @Column(name = "mae_tipo_documento_cf_codigo")
    private String maeTipoDocumentoCfCodigo;
    @Size(max = 128)
    @Column(name = "mae_tipo_documento_cf_valor")
    private String maeTipoDocumentoCfValor;
    @Size(max = 32)
    @Column(name = "numero_documento_cf")
    private String numeroDocumentoCf;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_afiliacion_sgsss")
    @Temporal(TemporalType.DATE)
    private Date fechaAfiliacionSgsss;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_afiliacion_eps")
    @Temporal(TemporalType.DATE)
    private Date fechaAfiliacionEps;
    @Column(name = "afiliacion_legalizada")
    private Boolean afiliacionLegalizada;
    @Column(name = "fecha_egreso_eps")
    @Temporal(TemporalType.DATE)
    private Date fechaEgresoEps;
    @Column(name = "fecha_suspension_eps")
    @Temporal(TemporalType.DATE)
    private Date fechaSuspensionEps;
    @Column(name = "fecha_cambio_estado_eps")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCambioEstadoEps;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "tipo_beneficiario")
    private String tipoBeneficiario;
    @Column(name = "mae_tipo_afiliado_id")
    private Integer maeTipoAfiliadoId;
    @Size(max = 8)
    @Column(name = "mae_tipo_afiliado_codigo")
    private String maeTipoAfiliadoCodigo;
    @Size(max = 128)
    @Column(name = "mae_tipo_afiliado_valor")
    private String maeTipoAfiliadoValor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "mae_estado_afiliacion_id")
    private int maeEstadoAfiliacionId;
    @Size(max = 8)
    @Column(name = "mae_estado_afiliacion_codigo")
    private String maeEstadoAfiliacionCodigo;
    @Size(max = 128)
    @Column(name = "mae_estado_afiliacion_valor")
    private String maeEstadoAfiliacionValor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "mae_origen_afiliado_id")
    private int maeOrigenAfiliadoId;
    @Size(max = 8)
    @Column(name = "mae_origen_afiliado_codigo")
    private String maeOrigenAfiliadoCodigo;
    @Size(max = 128)
    @Column(name = "mae_origen_afiliado_valor")
    private String maeOrigenAfiliadoValor;
    @Size(max = 16)
    @Column(name = "parentesco_cotizante")
    private String parentescoCotizante;
    @Column(name = "mae_parentesco_cotizante_id")
    private Integer maeParentescoCotizanteId;
    @Size(max = 8)
    @Column(name = "mae_parentesco_cotizante_codigo")
    private String maeParentescoCotizanteCodigo;
    @Size(max = 128)
    @Column(name = "mae_parentesco_cotizante_valor")
    private String maeParentescoCotizanteValor;
    @Column(name = "tratamiento_datos_autoriza")
    private Boolean tratamientoDatosAutoriza;
    @Column(name = "tratamient_datos_fecha_hora")
    @Temporal(TemporalType.TIMESTAMP)
    private Date tratamientDatosFechaHora;
    @Column(name = "autoriza_envio_sms")
    private Boolean autorizaEnvioSms;
    @Column(name = "autoriza_envio_email")
    private Boolean autorizaEnvioEmail;
    @Size(max = 32)
    @Column(name = "telefono_fijo")
    private String telefonoFijo;
    @Size(max = 32)
    @Column(name = "telefono_movil")
    private String telefonoMovil;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 128)
    @Column(name = "email")
    private String email;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "zona")
    private String zona;
    @Column(name = "mae_zona_id")
    private Integer maeZonaId;
    @Size(max = 8)
    @Column(name = "mae_zona_codigo")
    private String maeZonaCodigo;
    @Size(max = 128)
    @Column(name = "mae_zona_valor")
    private String maeZonaValor;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 512)
    @Column(name = "direccion_residencia")
    private String direccionResidencia;
    @Basic(optional = false)
    @NotNull
    @Column(name = "direccion_georeferenciada")
    private boolean direccionGeoreferenciada;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "direccion_georef_latitud")
    private BigDecimal direccionGeorefLatitud;
    @Column(name = "direccion_georef_longitud")
    private BigDecimal direccionGeorefLongitud;
    @Size(max = 64)
    @Column(name = "barrio")
    private String barrio;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "regimen")
    private String regimen;
    @Basic(optional = false)
    @NotNull
    @Column(name = "mae_regimen_id")
    private int maeRegimenId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "mae_regimen_codigo")
    private String maeRegimenCodigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "mae_regimen_valor")
    private String maeRegimenValor;
    @Column(name = "mae_grupo_sisben_id")
    private Integer maeGrupoSisbenId;
    @Size(max = 8)
    @Column(name = "mae_grupo_sisben_codigo")
    private String maeGrupoSisbenCodigo;
    @Size(max = 128)
    @Column(name = "mae_grupo_sisben_valor")
    private String maeGrupoSisbenValor;
    @Column(name = "mae_sub_grupo_sisben_id")
    private Integer maeSubGrupoSisbenId;
    @Size(max = 8)
    @Column(name = "mae_sub_grupo_sisben_codigo")
    private String maeSubGrupoSisbenCodigo;
    @Size(max = 128)
    @Column(name = "mae_sub_grupo_sisben_valor")
    private String maeSubGrupoSisbenValor;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "nivel_sisben")
    private String nivelSisben;
    @Basic(optional = false)
    @NotNull
    @Column(name = "mae_nivel_sisben_id")
    private int maeNivelSisbenId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "mae_nivel_sisben_codigo")
    private String maeNivelSisbenCodigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "mae_nivel_sisben_valor")
    private String maeNivelSisbenValor;
    @Column(name = "puntaje_sisben")
    private BigDecimal puntajeSisben;
    @Size(max = 32)
    @Column(name = "ficha_sisben")
    private String fichaSisben;
    @Column(name = "fecha_sisben")
    @Temporal(TemporalType.DATE)
    private Date fechaSisben;
    @Size(max = 16)
    @Column(name = "categoria_ibc")
    private String categoriaIbc;
    @Column(name = "mae_categoria_ibc_id")
    private Integer maeCategoriaIbcId;
    @Size(max = 8)
    @Column(name = "mae_categoria_ibc_codigo")
    private String maeCategoriaIbcCodigo;
    @Size(max = 128)
    @Column(name = "mae_categoria_ibc_valor")
    private String maeCategoriaIbcValor;
    @Column(name = "tiene_portabilidad")
    private Boolean tienePortabilidad;
    @Column(name = "fecha_portabilidad")
    @Temporal(TemporalType.DATE)
    private Date fechaPortabilidad;
    @Size(max = 8)
    @Column(name = "tipo_cotizante")
    private String tipoCotizante;
    @Column(name = "mae_tipo_cotizante_id")
    private Integer maeTipoCotizanteId;
    @Size(max = 8)
    @Column(name = "mae_tipo_cotizante_codigo")
    private String maeTipoCotizanteCodigo;
    @Size(max = 128)
    @Column(name = "mae_tipo_cotizante_valor")
    private String maeTipoCotizanteValor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "discapacidad")
    private boolean discapacidad;
    @Size(max = 16)
    @Column(name = "tipo_discapacidad")
    private String tipoDiscapacidad;
    @Column(name = "mae_tipo_discapacidad_id")
    private Integer maeTipoDiscapacidadId;
    @Size(max = 8)
    @Column(name = "mae_tipo_discapacidad_codigo")
    private String maeTipoDiscapacidadCodigo;
    @Size(max = 128)
    @Column(name = "mae_tipo_discapacidad_valor")
    private String maeTipoDiscapacidadValor;
    @Size(max = 8)
    @Column(name = "condicion_discapacidad")
    private String condicionDiscapacidad;
    @Column(name = "mae_discapacidad_condicion_id")
    private Integer maeDiscapacidadCondicionId;
    @Size(max = 8)
    @Column(name = "mae_discapacidad_condicion_codigo")
    private String maeDiscapacidadCondicionCodigo;
    @Size(max = 128)
    @Column(name = "mae_discapacidad_condicion_valor")
    private String maeDiscapacidadCondicionValor;
    @Column(name = "fecha_inicio_discapacidad")
    @Temporal(TemporalType.DATE)
    private Date fechaInicioDiscapacidad;
    @Column(name = "fecha_fin_discapacidad")
    @Temporal(TemporalType.DATE)
    private Date fechaFinDiscapacidad;
    @Basic(optional = false)
    @NotNull
    @Column(name = "mae_grupo_poblacional_id")
    private int maeGrupoPoblacionalId;
    @Size(max = 8)
    @Column(name = "mae_grupo_poblacional_codigo")
    private String maeGrupoPoblacionalCodigo;
    @Size(max = 128)
    @Column(name = "mae_grupo_poblacional_valor")
    private String maeGrupoPoblacionalValor;
    @Column(name = "mae_metodologia_grupo_poblacional_id")
    private Integer maeMetodologiaGrupoPoblacionalId;
    @Size(max = 8)
    @Column(name = "mae_metodologia_grupo_poblacional_codigo")
    private String maeMetodologiaGrupoPoblacionalCodigo;
    @Size(max = 128)
    @Column(name = "mae_metodologia_grupo_poblacional_valor")
    private String maeMetodologiaGrupoPoblacionalValor;
    @Column(name = "victima")
    private Boolean victima;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(name = "etnia")
    private String etnia;
    @Column(name = "mae_etnia_id")
    private Integer maeEtniaId;
    @Size(max = 8)
    @Column(name = "mae_etnia_codigo")
    private String maeEtniaCodigo;
    @Size(max = 128)
    @Column(name = "mae_etnia_valor")
    private String maeEtniaValor;
    @Column(name = "mae_comunidad_etnia_id")
    private Integer maeComunidadEtniaId;
    @Size(max = 8)
    @Column(name = "mae_comunidad_etnia_codigo")
    private String maeComunidadEtniaCodigo;
    @Size(max = 128)
    @Column(name = "mae_comunidad_etnia_valor")
    private String maeComunidadEtniaValor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "mae_causa_novedad_id")
    private int maeCausaNovedadId;
    @Size(max = 8)
    @Column(name = "mae_causa_novedad_codigo")
    private String maeCausaNovedadCodigo;
    @Size(max = 128)
    @Column(name = "mae_causa_novedad_valor")
    private String maeCausaNovedadValor;
    @Column(name = "fecha_reactivacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaReactivacion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "estado_civil")
    private String estadoCivil;
    @Column(name = "mae_estado_civil_id")
    private Integer maeEstadoCivilId;
    @Size(max = 8)
    @Column(name = "mae_estado_civil_codigo")
    private String maeEstadoCivilCodigo;
    @Size(max = 128)
    @Column(name = "mae_estado_civil_valor")
    private String maeEstadoCivilValor;
    @Column(name = "fecha_movilidad")
    @Temporal(TemporalType.DATE)
    private Date fechaMovilidad;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "modelo_liquidacion")
    private String modeloLiquidacion;
    @Column(name = "mae_modelo_liquidacion_id")
    private Integer maeModeloLiquidacionId;
    @Size(max = 8)
    @Column(name = "mae_modelo_liquidacion_codigo")
    private String maeModeloLiquidacionCodigo;
    @Size(max = 128)
    @Column(name = "mae_modelo_liquidacion_valor")
    private String maeModeloLiquidacionValor;
    @Column(name = "mae_tipo_documento_aportante_id")
    private Integer maeTipoDocumentoAportanteId;
    @Size(max = 8)
    @Column(name = "mae_tipo_documento_aportante_codigo")
    private String maeTipoDocumentoAportanteCodigo;
    @Size(max = 128)
    @Column(name = "mae_tipo_documento_aportante_valor")
    private String maeTipoDocumentoAportanteValor;
    @Size(max = 32)
    @Column(name = "numero_documento_aportante")
    private String numeroDocumentoAportante;
    @Column(name = "mae_actividad_economica_id")
    private Integer maeActividadEconomicaId;
    @Size(max = 8)
    @Column(name = "mae_actividad_economica_codigo")
    private String maeActividadEconomicaCodigo;
    @Size(max = 128)
    @Column(name = "mae_actividad_economica_valor")
    private String maeActividadEconomicaValor;
    @Column(name = "mae_arl_id")
    private Integer maeArlId;
    @Size(max = 8)
    @Column(name = "mae_arl_codigo")
    private String maeArlCodigo;
    @Size(max = 128)
    @Column(name = "mae_arl_valor")
    private String maeArlValor;
    @Column(name = "mae_afp_id")
    private Integer maeAfpId;
    @Size(max = 8)
    @Column(name = "mae_afp_codigo")
    private String maeAfpCodigo;
    @Size(max = 128)
    @Column(name = "mae_afp_valor")
    private String maeAfpValor;
    @Column(name = "mae_caja_compensacion_id")
    private Integer maeCajaCompensacionId;
    @Size(max = 8)
    @Column(name = "mae_caja_compensacion_codigo")
    private String maeCajaCompensacionCodigo;
    @Size(max = 128)
    @Column(name = "mae_caja_compensacion_valor")
    private String maeCajaCompensacionValor;
    @Column(name = "sincronizado")
    private Integer sincronizado;
    @Size(max = 1024)
    @Column(name = "observacion")
    private String observacion;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "historico_afiliado")
    private String historicoAfiliado;
    @Size(max = 128)
    @Column(name = "codigo_fonetico")
    private String codigoFonetico;
    @Column(name = "origen_ultimo_registro")
    private Integer origenUltimoRegistro;
    @Column(name = "acepta_contribucion_solidaria")
    private Boolean aceptaContribucionSolidaria;
    @Column(name = "mae_solidaria_porcentaje_id")
    private Integer maeSolidariaPorcentajeId;
    @Size(max = 8)
    @Column(name = "mae_solidaria_porcentaje_codigo")
    private String maeSolidariaPorcentajeCodigo;
    @Size(max = 128)
    @Column(name = "mae_solidaria_porcentaje_valor")
    private String maeSolidariaPorcentajeValor;
    @Column(name = "mae_genero_identificacion_id")
    private Integer maeGeneroIdentificacionId;
    @Size(max = 8)
    @Column(name = "mae_genero_identificacion_codigo")
    private String maeGeneroIdentificacionCodigo;
    @Size(max = 128)
    @Column(name = "mae_genero_identificacion_valor")
    private String maeGeneroIdentificacionValor;
    @Column(name = "incapacidad_prolongada")
    private Boolean incapacidadProlongada;
    @Column(name = "acuerdo_pago")
    private Boolean acuerdoPago;
    @Column(name = "fecha_acuerdo_pago")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaAcuerdoPago;
    @Column(name = "usuario_gestante")
    private Boolean usuarioGestante;
    @Column(name = "fecha_fin_periodo_gestacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaFinPeriodoGestacion;
    @Column(name = "duplicado")
    private Boolean duplicado;
    @Column(name = "traslado_preaprobado")
    private Boolean trasladoPreaprobado;
    @Size(max = 512)
    @Column(name = "direccion_alterna_contacto")
    private String direccionAlternaContacto;
    @Size(max = 128)
    @Column(name = "nombre_contacto_emergencia")
    private String nombreContactoEmergencia;
    @Size(max = 16)
    @Column(name = "telefono_contacto_emergencia")
    private String telefonoContactoEmergencia;
    @Column(name = "mae_tipo_portabilidad_id")
    private Integer maeTipoPortabilidadId;
    @Size(max = 8)
    @Column(name = "mae_tipo_portabilidad_codigo")
    private String maeTipoPortabilidadCodigo;
    @Size(max = 128)
    @Column(name = "mae_tipo_portabilidad_valor")
    private String maeTipoPortabilidadValor;
    @Column(name = "mae_motivo_portabilidad_id")
    private Integer maeMotivoPortabilidadId;
    @Size(max = 8)
    @Column(name = "mae_motivo_portabilidad_codigo")
    private String maeMotivoPortabilidadCodigo;
    @Size(max = 128)
    @Column(name = "mae_motivo_portabilidad_valor")
    private String maeMotivoPortabilidadValor;
    @Column(name = "mae_origen_solicitud_portabilidad_id")
    private Integer maeOrigenSolicitudPortabilidadId;
    @Size(max = 8)
    @Column(name = "mae_origen_solicitud_portabilidad_codigo")
    private String maeOrigenSolicitudPortabilidadCodigo;
    @Size(max = 128)
    @Column(name = "mae_origen_solicitud_portabilidad_valor")
    private String maeOrigenSolicitudPortabilidadValor;
    @Column(name = "periodo_inicial_portabilidad")
    @Temporal(TemporalType.DATE)
    private Date periodoInicialPortabilidad;
    @Column(name = "periodo_final_portabilidad")
    @Temporal(TemporalType.DATE)
    private Date periodoFinalPortabilidad;
    @Size(max = 10)
    @Column(name = "telefono_contacto_portabilidad")
    private String telefonoContactoPortabilidad;
    @Size(max = 256)
    @Column(name = "direccion_portabilidad")
    private String direccionPortabilidad;
    @Size(max = 512)
    @Column(name = "correo_electronico_portabilidad")
    private String correoElectronicoPortabilidad;
    @Lob
    @Size(max = 65535)
    @Column(name = "observacion_portabilidad")
    private String observacionPortabilidad;
    @Basic(optional = false)
    @NotNull
    @Column(name = "poblacion_especial")
    private boolean poblacionEspecial;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "usuario_crea")
    private String usuarioCrea;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 16)
    @Column(name = "terminal_crea")
    private String terminalCrea;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_hora_crea")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraCrea;
    @Size(max = 128)
    @Column(name = "usuario_modifica")
    private String usuarioModifica;
    @Size(max = 16)
    @Column(name = "terminal_modifica")
    private String terminalModifica;
    @Column(name = "fecha_hora_modifica")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraModifica;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "asegAfiliadosId", fetch = FetchType.LAZY)
    private List<AsegPortabilidades> asegPortabilidadesList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "asegAfiliadosId", fetch = FetchType.LAZY)
    private List<AuAnexos2> auAnexos2List;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "asegAfiliadosId", fetch = FetchType.LAZY)
    private List<AuAnexos4> auAnexos4List;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "asegAfiliadosId", fetch = FetchType.LAZY)
    private List<AuAnexos3> auAnexos3List;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "asegAfiliadosId", fetch = FetchType.LAZY)
    private List<AsegAfiliadoHistoricos> asegAfiliadoHistoricosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "asegAfiliadosId", fetch = FetchType.LAZY)
    private List<AsegAfiliadoCertificados> asegAfiliadoCertificadosList;
    @OneToMany(mappedBy = "asegAfiliadosId", fetch = FetchType.LAZY)
    private List<AusPersonas> ausPersonasList;
    @OneToMany(mappedBy = "asegAfiliadosId", fetch = FetchType.LAZY)
    private List<MpPrescripciones> mpPrescripcionesList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "asegAfiliadosId", fetch = FetchType.LAZY)
    private List<PeAfiliadosSugeridos> peAfiliadosSugeridosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "aseAfiliadosId", fetch = FetchType.LAZY)
    private List<AuTopeAfiliados> auTopeAfiliadosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "asegAfiliadosId", fetch = FetchType.LAZY)
    private List<AuRechazos> auRechazosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "asegAfiliadosId", fetch = FetchType.LAZY)
    private List<MpcPrescripcionesHistoricos> mpcPrescripcionesHistoricosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "asegAfiliadosId", fetch = FetchType.LAZY)
    private List<AuNoSolicitudes> auNoSolicitudesList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "asegAfiliadosId", fetch = FetchType.LAZY)
    private List<AucAfiliados> aucAfiliadosList;
    @OneToMany(mappedBy = "asegAfiliadosId", fetch = FetchType.LAZY)
    private List<CmDetalles> cmDetallesList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "asegAfiliadosId", fetch = FetchType.LAZY)
    private List<AsegTraslados> asegTrasladosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "asegAfiliadosId", fetch = FetchType.LAZY)
    private List<PeProgramasTraslados> peProgramasTrasladosList;
    @OneToMany(mappedBy = "asegAfiliadosId", fetch = FetchType.LAZY)
    private List<AuCotizaciones> auCotizacionesList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "asegAfiliadosId", fetch = FetchType.LAZY)
    private List<AsegTabulacionEncuestas> asegTabulacionEncuestasList;
    @OneToMany(mappedBy = "asegAfiliadosId", fetch = FetchType.LAZY)
    private List<AsegGruposFamiliares> asegGruposFamiliaresList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "asegAfiliadosId", fetch = FetchType.LAZY)
    private List<AsegAfiliadoDocumentos> asegAfiliadoDocumentosList;
    @OneToMany(mappedBy = "asegAfiliadosId", fetch = FetchType.LAZY)
    private List<GjTerceros> gjTercerosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "asegAfiliadosId", fetch = FetchType.LAZY)
    private List<PeDireccionados> peDireccionadosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "asegAfiliadosId", fetch = FetchType.LAZY)
    private List<AsegAnexos1> asegAnexos1List;
    @OneToMany(mappedBy = "asegAfiliadosId", fetch = FetchType.LAZY)
    private List<GatUsuarios> gatUsuariosList;
    @OneToMany(mappedBy = "asegAfiliadosId", fetch = FetchType.LAZY)
    private List<MpAfiliados> mpAfiliadosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "asegAfiliadosId", fetch = FetchType.LAZY)
    private List<RefAnexos9> refAnexos9List;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "asegAfiliadosId", fetch = FetchType.LAZY)
    private List<CntContratoHistoricoPrestaciones> cntContratoHistoricoPrestacionesList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "asegAfiliadosId", fetch = FetchType.LAZY)
    private List<AsegAfiliadoContactos> asegAfiliadoContactosList;
    @OneToMany(mappedBy = "asegAfiliadosId", fetch = FetchType.LAZY)
    private List<AntAnticipos> antAnticiposList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "asegAfiliadosId", fetch = FetchType.LAZY)
    private List<CsCopagoModeradoraHistoricos> csCopagoModeradoraHistoricosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "asegAfiliadosId", fetch = FetchType.LAZY)
    private List<PeAfiliadosProgramas> peAfiliadosProgramasList;
    @JoinColumn(name = "portabilidad_cnt_prestador_sedes_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private CntPrestadorSedes portabilidadCntPrestadorSedesId;
    @JoinColumn(name = "aseg_grupos_familiares_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private AsegGruposFamiliares asegGruposFamiliaresId;
    @JoinColumn(name = "primaria_cnt_prestador_sedes_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private CntPrestadorSedes primariaCntPrestadorSedesId;
    @JoinColumn(name = "odontologia_cnt_prestador_sedes_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private CntPrestadorSedes odontologiaCntPrestadorSedesId;
    @JoinColumn(name = "gn_divisiones_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private GnDivisiones gnDivisionesId;
    @JoinColumn(name = "gn_ubicacion_actual_afiliado", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private GnUbicaciones gnUbicacionActualAfiliado;
    @JoinColumn(name = "gn_ubicaciones_lugar_nacimiento_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private GnUbicaciones gnUbicacionesLugarNacimientoId;
    @JoinColumn(name = "gn_ubicacion_barrios_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private GnUbicacionBarrios gnUbicacionBarriosId;
    @JoinColumn(name = "nacionalidad_ubicaciones_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private GnUbicaciones nacionalidadUbicacionesId;
    @JoinColumn(name = "nacimiento_ubicaciones_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private GnUbicaciones nacimientoUbicacionesId;
    @JoinColumn(name = "afiliacion_ubicaciones_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private GnUbicaciones afiliacionUbicacionesId;
    @JoinColumn(name = "residencia_ubicacion_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private GnUbicaciones residenciaUbicacionId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "asegAfiliadosId", fetch = FetchType.LAZY)
    private List<PeTelefonos> peTelefonosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "asegAfiliadosId", fetch = FetchType.LAZY)
    private List<AuSeguimientoAfiliados> auSeguimientoAfiliadosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "asegAfiliadosId", fetch = FetchType.LAZY)
    private List<AuAnexo2Rescates> auAnexo2RescatesList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "asegAfiliadosId", fetch = FetchType.LAZY)
    private List<AsegReporteNovedades> asegReporteNovedadesList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "asegAfiliadosId", fetch = FetchType.LAZY)
    private List<MpcPrescripciones> mpcPrescripcionesList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "asegAfiliadosId", fetch = FetchType.LAZY)
    private List<AsegRadicadoNovedades> asegRadicadoNovedadesList;

    public AsegAfiliados() {
    }

    public AsegAfiliados(Integer id) {
        this.id = id;
    }

    public AsegAfiliados(Integer id, String idAfiliado, boolean registroBdua, String primerNombre, String primerApellido, Date fechaNacimiento, String genero, int maeTipoDocumentoId, String numeroDocumento, Date fechaAfiliacionSgsss, Date fechaAfiliacionEps, String tipoBeneficiario, int maeEstadoAfiliacionId, int maeOrigenAfiliadoId, String zona, String direccionResidencia, boolean direccionGeoreferenciada, String regimen, int maeRegimenId, String maeRegimenCodigo, String maeRegimenValor, String nivelSisben, int maeNivelSisbenId, String maeNivelSisbenCodigo, String maeNivelSisbenValor, boolean discapacidad, int maeGrupoPoblacionalId, String etnia, int maeCausaNovedadId, String estadoCivil, String modeloLiquidacion, boolean poblacionEspecial, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.idAfiliado = idAfiliado;
        this.registroBdua = registroBdua;
        this.primerNombre = primerNombre;
        this.primerApellido = primerApellido;
        this.fechaNacimiento = fechaNacimiento;
        this.genero = genero;
        this.maeTipoDocumentoId = maeTipoDocumentoId;
        this.numeroDocumento = numeroDocumento;
        this.fechaAfiliacionSgsss = fechaAfiliacionSgsss;
        this.fechaAfiliacionEps = fechaAfiliacionEps;
        this.tipoBeneficiario = tipoBeneficiario;
        this.maeEstadoAfiliacionId = maeEstadoAfiliacionId;
        this.maeOrigenAfiliadoId = maeOrigenAfiliadoId;
        this.zona = zona;
        this.direccionResidencia = direccionResidencia;
        this.direccionGeoreferenciada = direccionGeoreferenciada;
        this.regimen = regimen;
        this.maeRegimenId = maeRegimenId;
        this.maeRegimenCodigo = maeRegimenCodigo;
        this.maeRegimenValor = maeRegimenValor;
        this.nivelSisben = nivelSisben;
        this.maeNivelSisbenId = maeNivelSisbenId;
        this.maeNivelSisbenCodigo = maeNivelSisbenCodigo;
        this.maeNivelSisbenValor = maeNivelSisbenValor;
        this.discapacidad = discapacidad;
        this.maeGrupoPoblacionalId = maeGrupoPoblacionalId;
        this.etnia = etnia;
        this.maeCausaNovedadId = maeCausaNovedadId;
        this.estadoCivil = estadoCivil;
        this.modeloLiquidacion = modeloLiquidacion;
        this.poblacionEspecial = poblacionEspecial;
        this.usuarioCrea = usuarioCrea;
        this.terminalCrea = terminalCrea;
        this.fechaHoraCrea = fechaHoraCrea;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIdAfiliado() {
        return idAfiliado;
    }

    public void setIdAfiliado(String idAfiliado) {
        this.idAfiliado = idAfiliado;
    }

    public BigInteger getSerialBdua() {
        return serialBdua;
    }

    public void setSerialBdua(BigInteger serialBdua) {
        this.serialBdua = serialBdua;
    }

    public boolean getRegistroBdua() {
        return registroBdua;
    }

    public void setRegistroBdua(boolean registroBdua) {
        this.registroBdua = registroBdua;
    }

    public String getPrimerNombre() {
        return primerNombre;
    }

    public void setPrimerNombre(String primerNombre) {
        this.primerNombre = primerNombre;
    }

    public String getSegundoNombre() {
        return segundoNombre;
    }

    public void setSegundoNombre(String segundoNombre) {
        this.segundoNombre = segundoNombre;
    }

    public String getPrimerApellido() {
        return primerApellido;
    }

    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }

    public String getSegundoApellido() {
        return segundoApellido;
    }

    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido = segundoApellido;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public Integer getMaeGeneroId() {
        return maeGeneroId;
    }

    public void setMaeGeneroId(Integer maeGeneroId) {
        this.maeGeneroId = maeGeneroId;
    }

    public String getMaeGeneroCodigo() {
        return maeGeneroCodigo;
    }

    public void setMaeGeneroCodigo(String maeGeneroCodigo) {
        this.maeGeneroCodigo = maeGeneroCodigo;
    }

    public String getMaeGeneroValor() {
        return maeGeneroValor;
    }

    public void setMaeGeneroValor(String maeGeneroValor) {
        this.maeGeneroValor = maeGeneroValor;
    }

    public Date getFechaExpedicionCedula() {
        return fechaExpedicionCedula;
    }

    public void setFechaExpedicionCedula(Date fechaExpedicionCedula) {
        this.fechaExpedicionCedula = fechaExpedicionCedula;
    }

    public int getMaeTipoDocumentoId() {
        return maeTipoDocumentoId;
    }

    public void setMaeTipoDocumentoId(int maeTipoDocumentoId) {
        this.maeTipoDocumentoId = maeTipoDocumentoId;
    }

    public String getMaeTipoDocumentoCodigo() {
        return maeTipoDocumentoCodigo;
    }

    public void setMaeTipoDocumentoCodigo(String maeTipoDocumentoCodigo) {
        this.maeTipoDocumentoCodigo = maeTipoDocumentoCodigo;
    }

    public String getMaeTipoDocumentoValor() {
        return maeTipoDocumentoValor;
    }

    public void setMaeTipoDocumentoValor(String maeTipoDocumentoValor) {
        this.maeTipoDocumentoValor = maeTipoDocumentoValor;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public Integer getMaeTipoDocumentoCfId() {
        return maeTipoDocumentoCfId;
    }

    public void setMaeTipoDocumentoCfId(Integer maeTipoDocumentoCfId) {
        this.maeTipoDocumentoCfId = maeTipoDocumentoCfId;
    }

    public String getMaeTipoDocumentoCfCodigo() {
        return maeTipoDocumentoCfCodigo;
    }

    public void setMaeTipoDocumentoCfCodigo(String maeTipoDocumentoCfCodigo) {
        this.maeTipoDocumentoCfCodigo = maeTipoDocumentoCfCodigo;
    }

    public String getMaeTipoDocumentoCfValor() {
        return maeTipoDocumentoCfValor;
    }

    public void setMaeTipoDocumentoCfValor(String maeTipoDocumentoCfValor) {
        this.maeTipoDocumentoCfValor = maeTipoDocumentoCfValor;
    }

    public String getNumeroDocumentoCf() {
        return numeroDocumentoCf;
    }

    public void setNumeroDocumentoCf(String numeroDocumentoCf) {
        this.numeroDocumentoCf = numeroDocumentoCf;
    }

    public Date getFechaAfiliacionSgsss() {
        return fechaAfiliacionSgsss;
    }

    public void setFechaAfiliacionSgsss(Date fechaAfiliacionSgsss) {
        this.fechaAfiliacionSgsss = fechaAfiliacionSgsss;
    }

    public Date getFechaAfiliacionEps() {
        return fechaAfiliacionEps;
    }

    public void setFechaAfiliacionEps(Date fechaAfiliacionEps) {
        this.fechaAfiliacionEps = fechaAfiliacionEps;
    }

    public Boolean getAfiliacionLegalizada() {
        return afiliacionLegalizada;
    }

    public void setAfiliacionLegalizada(Boolean afiliacionLegalizada) {
        this.afiliacionLegalizada = afiliacionLegalizada;
    }

    public Date getFechaEgresoEps() {
        return fechaEgresoEps;
    }

    public void setFechaEgresoEps(Date fechaEgresoEps) {
        this.fechaEgresoEps = fechaEgresoEps;
    }

    public Date getFechaSuspensionEps() {
        return fechaSuspensionEps;
    }

    public void setFechaSuspensionEps(Date fechaSuspensionEps) {
        this.fechaSuspensionEps = fechaSuspensionEps;
    }

    public Date getFechaCambioEstadoEps() {
        return fechaCambioEstadoEps;
    }

    public void setFechaCambioEstadoEps(Date fechaCambioEstadoEps) {
        this.fechaCambioEstadoEps = fechaCambioEstadoEps;
    }

    public String getTipoBeneficiario() {
        return tipoBeneficiario;
    }

    public void setTipoBeneficiario(String tipoBeneficiario) {
        this.tipoBeneficiario = tipoBeneficiario;
    }

    public Integer getMaeTipoAfiliadoId() {
        return maeTipoAfiliadoId;
    }

    public void setMaeTipoAfiliadoId(Integer maeTipoAfiliadoId) {
        this.maeTipoAfiliadoId = maeTipoAfiliadoId;
    }

    public String getMaeTipoAfiliadoCodigo() {
        return maeTipoAfiliadoCodigo;
    }

    public void setMaeTipoAfiliadoCodigo(String maeTipoAfiliadoCodigo) {
        this.maeTipoAfiliadoCodigo = maeTipoAfiliadoCodigo;
    }

    public String getMaeTipoAfiliadoValor() {
        return maeTipoAfiliadoValor;
    }

    public void setMaeTipoAfiliadoValor(String maeTipoAfiliadoValor) {
        this.maeTipoAfiliadoValor = maeTipoAfiliadoValor;
    }

    public int getMaeEstadoAfiliacionId() {
        return maeEstadoAfiliacionId;
    }

    public void setMaeEstadoAfiliacionId(int maeEstadoAfiliacionId) {
        this.maeEstadoAfiliacionId = maeEstadoAfiliacionId;
    }

    public String getMaeEstadoAfiliacionCodigo() {
        return maeEstadoAfiliacionCodigo;
    }

    public void setMaeEstadoAfiliacionCodigo(String maeEstadoAfiliacionCodigo) {
        this.maeEstadoAfiliacionCodigo = maeEstadoAfiliacionCodigo;
    }

    public String getMaeEstadoAfiliacionValor() {
        return maeEstadoAfiliacionValor;
    }

    public void setMaeEstadoAfiliacionValor(String maeEstadoAfiliacionValor) {
        this.maeEstadoAfiliacionValor = maeEstadoAfiliacionValor;
    }

    public int getMaeOrigenAfiliadoId() {
        return maeOrigenAfiliadoId;
    }

    public void setMaeOrigenAfiliadoId(int maeOrigenAfiliadoId) {
        this.maeOrigenAfiliadoId = maeOrigenAfiliadoId;
    }

    public String getMaeOrigenAfiliadoCodigo() {
        return maeOrigenAfiliadoCodigo;
    }

    public void setMaeOrigenAfiliadoCodigo(String maeOrigenAfiliadoCodigo) {
        this.maeOrigenAfiliadoCodigo = maeOrigenAfiliadoCodigo;
    }

    public String getMaeOrigenAfiliadoValor() {
        return maeOrigenAfiliadoValor;
    }

    public void setMaeOrigenAfiliadoValor(String maeOrigenAfiliadoValor) {
        this.maeOrigenAfiliadoValor = maeOrigenAfiliadoValor;
    }

    public String getParentescoCotizante() {
        return parentescoCotizante;
    }

    public void setParentescoCotizante(String parentescoCotizante) {
        this.parentescoCotizante = parentescoCotizante;
    }

    public Integer getMaeParentescoCotizanteId() {
        return maeParentescoCotizanteId;
    }

    public void setMaeParentescoCotizanteId(Integer maeParentescoCotizanteId) {
        this.maeParentescoCotizanteId = maeParentescoCotizanteId;
    }

    public String getMaeParentescoCotizanteCodigo() {
        return maeParentescoCotizanteCodigo;
    }

    public void setMaeParentescoCotizanteCodigo(String maeParentescoCotizanteCodigo) {
        this.maeParentescoCotizanteCodigo = maeParentescoCotizanteCodigo;
    }

    public String getMaeParentescoCotizanteValor() {
        return maeParentescoCotizanteValor;
    }

    public void setMaeParentescoCotizanteValor(String maeParentescoCotizanteValor) {
        this.maeParentescoCotizanteValor = maeParentescoCotizanteValor;
    }

    public Boolean getTratamientoDatosAutoriza() {
        return tratamientoDatosAutoriza;
    }

    public void setTratamientoDatosAutoriza(Boolean tratamientoDatosAutoriza) {
        this.tratamientoDatosAutoriza = tratamientoDatosAutoriza;
    }

    public Date getTratamientDatosFechaHora() {
        return tratamientDatosFechaHora;
    }

    public void setTratamientDatosFechaHora(Date tratamientDatosFechaHora) {
        this.tratamientDatosFechaHora = tratamientDatosFechaHora;
    }

    public Boolean getAutorizaEnvioSms() {
        return autorizaEnvioSms;
    }

    public void setAutorizaEnvioSms(Boolean autorizaEnvioSms) {
        this.autorizaEnvioSms = autorizaEnvioSms;
    }

    public Boolean getAutorizaEnvioEmail() {
        return autorizaEnvioEmail;
    }

    public void setAutorizaEnvioEmail(Boolean autorizaEnvioEmail) {
        this.autorizaEnvioEmail = autorizaEnvioEmail;
    }

    public String getTelefonoFijo() {
        return telefonoFijo;
    }

    public void setTelefonoFijo(String telefonoFijo) {
        this.telefonoFijo = telefonoFijo;
    }

    public String getTelefonoMovil() {
        return telefonoMovil;
    }

    public void setTelefonoMovil(String telefonoMovil) {
        this.telefonoMovil = telefonoMovil;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getZona() {
        return zona;
    }

    public void setZona(String zona) {
        this.zona = zona;
    }

    public Integer getMaeZonaId() {
        return maeZonaId;
    }

    public void setMaeZonaId(Integer maeZonaId) {
        this.maeZonaId = maeZonaId;
    }

    public String getMaeZonaCodigo() {
        return maeZonaCodigo;
    }

    public void setMaeZonaCodigo(String maeZonaCodigo) {
        this.maeZonaCodigo = maeZonaCodigo;
    }

    public String getMaeZonaValor() {
        return maeZonaValor;
    }

    public void setMaeZonaValor(String maeZonaValor) {
        this.maeZonaValor = maeZonaValor;
    }

    public String getDireccionResidencia() {
        return direccionResidencia;
    }

    public void setDireccionResidencia(String direccionResidencia) {
        this.direccionResidencia = direccionResidencia;
    }

    public boolean getDireccionGeoreferenciada() {
        return direccionGeoreferenciada;
    }

    public void setDireccionGeoreferenciada(boolean direccionGeoreferenciada) {
        this.direccionGeoreferenciada = direccionGeoreferenciada;
    }

    public BigDecimal getDireccionGeorefLatitud() {
        return direccionGeorefLatitud;
    }

    public void setDireccionGeorefLatitud(BigDecimal direccionGeorefLatitud) {
        this.direccionGeorefLatitud = direccionGeorefLatitud;
    }

    public BigDecimal getDireccionGeorefLongitud() {
        return direccionGeorefLongitud;
    }

    public void setDireccionGeorefLongitud(BigDecimal direccionGeorefLongitud) {
        this.direccionGeorefLongitud = direccionGeorefLongitud;
    }

    public String getBarrio() {
        return barrio;
    }

    public void setBarrio(String barrio) {
        this.barrio = barrio;
    }

    public String getRegimen() {
        return regimen;
    }

    public void setRegimen(String regimen) {
        this.regimen = regimen;
    }

    public int getMaeRegimenId() {
        return maeRegimenId;
    }

    public void setMaeRegimenId(int maeRegimenId) {
        this.maeRegimenId = maeRegimenId;
    }

    public String getMaeRegimenCodigo() {
        return maeRegimenCodigo;
    }

    public void setMaeRegimenCodigo(String maeRegimenCodigo) {
        this.maeRegimenCodigo = maeRegimenCodigo;
    }

    public String getMaeRegimenValor() {
        return maeRegimenValor;
    }

    public void setMaeRegimenValor(String maeRegimenValor) {
        this.maeRegimenValor = maeRegimenValor;
    }

    public Integer getMaeGrupoSisbenId() {
        return maeGrupoSisbenId;
    }

    public void setMaeGrupoSisbenId(Integer maeGrupoSisbenId) {
        this.maeGrupoSisbenId = maeGrupoSisbenId;
    }

    public String getMaeGrupoSisbenCodigo() {
        return maeGrupoSisbenCodigo;
    }

    public void setMaeGrupoSisbenCodigo(String maeGrupoSisbenCodigo) {
        this.maeGrupoSisbenCodigo = maeGrupoSisbenCodigo;
    }

    public String getMaeGrupoSisbenValor() {
        return maeGrupoSisbenValor;
    }

    public void setMaeGrupoSisbenValor(String maeGrupoSisbenValor) {
        this.maeGrupoSisbenValor = maeGrupoSisbenValor;
    }

    public Integer getMaeSubGrupoSisbenId() {
        return maeSubGrupoSisbenId;
    }

    public void setMaeSubGrupoSisbenId(Integer maeSubGrupoSisbenId) {
        this.maeSubGrupoSisbenId = maeSubGrupoSisbenId;
    }

    public String getMaeSubGrupoSisbenCodigo() {
        return maeSubGrupoSisbenCodigo;
    }

    public void setMaeSubGrupoSisbenCodigo(String maeSubGrupoSisbenCodigo) {
        this.maeSubGrupoSisbenCodigo = maeSubGrupoSisbenCodigo;
    }

    public String getMaeSubGrupoSisbenValor() {
        return maeSubGrupoSisbenValor;
    }

    public void setMaeSubGrupoSisbenValor(String maeSubGrupoSisbenValor) {
        this.maeSubGrupoSisbenValor = maeSubGrupoSisbenValor;
    }

    public String getNivelSisben() {
        return nivelSisben;
    }

    public void setNivelSisben(String nivelSisben) {
        this.nivelSisben = nivelSisben;
    }

    public int getMaeNivelSisbenId() {
        return maeNivelSisbenId;
    }

    public void setMaeNivelSisbenId(int maeNivelSisbenId) {
        this.maeNivelSisbenId = maeNivelSisbenId;
    }

    public String getMaeNivelSisbenCodigo() {
        return maeNivelSisbenCodigo;
    }

    public void setMaeNivelSisbenCodigo(String maeNivelSisbenCodigo) {
        this.maeNivelSisbenCodigo = maeNivelSisbenCodigo;
    }

    public String getMaeNivelSisbenValor() {
        return maeNivelSisbenValor;
    }

    public void setMaeNivelSisbenValor(String maeNivelSisbenValor) {
        this.maeNivelSisbenValor = maeNivelSisbenValor;
    }

    public BigDecimal getPuntajeSisben() {
        return puntajeSisben;
    }

    public void setPuntajeSisben(BigDecimal puntajeSisben) {
        this.puntajeSisben = puntajeSisben;
    }

    public String getFichaSisben() {
        return fichaSisben;
    }

    public void setFichaSisben(String fichaSisben) {
        this.fichaSisben = fichaSisben;
    }

    public Date getFechaSisben() {
        return fechaSisben;
    }

    public void setFechaSisben(Date fechaSisben) {
        this.fechaSisben = fechaSisben;
    }

    public String getCategoriaIbc() {
        return categoriaIbc;
    }

    public void setCategoriaIbc(String categoriaIbc) {
        this.categoriaIbc = categoriaIbc;
    }

    public Integer getMaeCategoriaIbcId() {
        return maeCategoriaIbcId;
    }

    public void setMaeCategoriaIbcId(Integer maeCategoriaIbcId) {
        this.maeCategoriaIbcId = maeCategoriaIbcId;
    }

    public String getMaeCategoriaIbcCodigo() {
        return maeCategoriaIbcCodigo;
    }

    public void setMaeCategoriaIbcCodigo(String maeCategoriaIbcCodigo) {
        this.maeCategoriaIbcCodigo = maeCategoriaIbcCodigo;
    }

    public String getMaeCategoriaIbcValor() {
        return maeCategoriaIbcValor;
    }

    public void setMaeCategoriaIbcValor(String maeCategoriaIbcValor) {
        this.maeCategoriaIbcValor = maeCategoriaIbcValor;
    }

    public Boolean getTienePortabilidad() {
        return tienePortabilidad;
    }

    public void setTienePortabilidad(Boolean tienePortabilidad) {
        this.tienePortabilidad = tienePortabilidad;
    }

    public Date getFechaPortabilidad() {
        return fechaPortabilidad;
    }

    public void setFechaPortabilidad(Date fechaPortabilidad) {
        this.fechaPortabilidad = fechaPortabilidad;
    }

    public String getTipoCotizante() {
        return tipoCotizante;
    }

    public void setTipoCotizante(String tipoCotizante) {
        this.tipoCotizante = tipoCotizante;
    }

    public Integer getMaeTipoCotizanteId() {
        return maeTipoCotizanteId;
    }

    public void setMaeTipoCotizanteId(Integer maeTipoCotizanteId) {
        this.maeTipoCotizanteId = maeTipoCotizanteId;
    }

    public String getMaeTipoCotizanteCodigo() {
        return maeTipoCotizanteCodigo;
    }

    public void setMaeTipoCotizanteCodigo(String maeTipoCotizanteCodigo) {
        this.maeTipoCotizanteCodigo = maeTipoCotizanteCodigo;
    }

    public String getMaeTipoCotizanteValor() {
        return maeTipoCotizanteValor;
    }

    public void setMaeTipoCotizanteValor(String maeTipoCotizanteValor) {
        this.maeTipoCotizanteValor = maeTipoCotizanteValor;
    }

    public boolean getDiscapacidad() {
        return discapacidad;
    }

    public void setDiscapacidad(boolean discapacidad) {
        this.discapacidad = discapacidad;
    }

    public String getTipoDiscapacidad() {
        return tipoDiscapacidad;
    }

    public void setTipoDiscapacidad(String tipoDiscapacidad) {
        this.tipoDiscapacidad = tipoDiscapacidad;
    }

    public Integer getMaeTipoDiscapacidadId() {
        return maeTipoDiscapacidadId;
    }

    public void setMaeTipoDiscapacidadId(Integer maeTipoDiscapacidadId) {
        this.maeTipoDiscapacidadId = maeTipoDiscapacidadId;
    }

    public String getMaeTipoDiscapacidadCodigo() {
        return maeTipoDiscapacidadCodigo;
    }

    public void setMaeTipoDiscapacidadCodigo(String maeTipoDiscapacidadCodigo) {
        this.maeTipoDiscapacidadCodigo = maeTipoDiscapacidadCodigo;
    }

    public String getMaeTipoDiscapacidadValor() {
        return maeTipoDiscapacidadValor;
    }

    public void setMaeTipoDiscapacidadValor(String maeTipoDiscapacidadValor) {
        this.maeTipoDiscapacidadValor = maeTipoDiscapacidadValor;
    }

    public String getCondicionDiscapacidad() {
        return condicionDiscapacidad;
    }

    public void setCondicionDiscapacidad(String condicionDiscapacidad) {
        this.condicionDiscapacidad = condicionDiscapacidad;
    }

    public Integer getMaeDiscapacidadCondicionId() {
        return maeDiscapacidadCondicionId;
    }

    public void setMaeDiscapacidadCondicionId(Integer maeDiscapacidadCondicionId) {
        this.maeDiscapacidadCondicionId = maeDiscapacidadCondicionId;
    }

    public String getMaeDiscapacidadCondicionCodigo() {
        return maeDiscapacidadCondicionCodigo;
    }

    public void setMaeDiscapacidadCondicionCodigo(String maeDiscapacidadCondicionCodigo) {
        this.maeDiscapacidadCondicionCodigo = maeDiscapacidadCondicionCodigo;
    }

    public String getMaeDiscapacidadCondicionValor() {
        return maeDiscapacidadCondicionValor;
    }

    public void setMaeDiscapacidadCondicionValor(String maeDiscapacidadCondicionValor) {
        this.maeDiscapacidadCondicionValor = maeDiscapacidadCondicionValor;
    }

    public Date getFechaInicioDiscapacidad() {
        return fechaInicioDiscapacidad;
    }

    public void setFechaInicioDiscapacidad(Date fechaInicioDiscapacidad) {
        this.fechaInicioDiscapacidad = fechaInicioDiscapacidad;
    }

    public Date getFechaFinDiscapacidad() {
        return fechaFinDiscapacidad;
    }

    public void setFechaFinDiscapacidad(Date fechaFinDiscapacidad) {
        this.fechaFinDiscapacidad = fechaFinDiscapacidad;
    }

    public int getMaeGrupoPoblacionalId() {
        return maeGrupoPoblacionalId;
    }

    public void setMaeGrupoPoblacionalId(int maeGrupoPoblacionalId) {
        this.maeGrupoPoblacionalId = maeGrupoPoblacionalId;
    }

    public String getMaeGrupoPoblacionalCodigo() {
        return maeGrupoPoblacionalCodigo;
    }

    public void setMaeGrupoPoblacionalCodigo(String maeGrupoPoblacionalCodigo) {
        this.maeGrupoPoblacionalCodigo = maeGrupoPoblacionalCodigo;
    }

    public String getMaeGrupoPoblacionalValor() {
        return maeGrupoPoblacionalValor;
    }

    public void setMaeGrupoPoblacionalValor(String maeGrupoPoblacionalValor) {
        this.maeGrupoPoblacionalValor = maeGrupoPoblacionalValor;
    }

    public Integer getMaeMetodologiaGrupoPoblacionalId() {
        return maeMetodologiaGrupoPoblacionalId;
    }

    public void setMaeMetodologiaGrupoPoblacionalId(Integer maeMetodologiaGrupoPoblacionalId) {
        this.maeMetodologiaGrupoPoblacionalId = maeMetodologiaGrupoPoblacionalId;
    }

    public String getMaeMetodologiaGrupoPoblacionalCodigo() {
        return maeMetodologiaGrupoPoblacionalCodigo;
    }

    public void setMaeMetodologiaGrupoPoblacionalCodigo(String maeMetodologiaGrupoPoblacionalCodigo) {
        this.maeMetodologiaGrupoPoblacionalCodigo = maeMetodologiaGrupoPoblacionalCodigo;
    }

    public String getMaeMetodologiaGrupoPoblacionalValor() {
        return maeMetodologiaGrupoPoblacionalValor;
    }

    public void setMaeMetodologiaGrupoPoblacionalValor(String maeMetodologiaGrupoPoblacionalValor) {
        this.maeMetodologiaGrupoPoblacionalValor = maeMetodologiaGrupoPoblacionalValor;
    }

    public Boolean getVictima() {
        return victima;
    }

    public void setVictima(Boolean victima) {
        this.victima = victima;
    }

    public String getEtnia() {
        return etnia;
    }

    public void setEtnia(String etnia) {
        this.etnia = etnia;
    }

    public Integer getMaeEtniaId() {
        return maeEtniaId;
    }

    public void setMaeEtniaId(Integer maeEtniaId) {
        this.maeEtniaId = maeEtniaId;
    }

    public String getMaeEtniaCodigo() {
        return maeEtniaCodigo;
    }

    public void setMaeEtniaCodigo(String maeEtniaCodigo) {
        this.maeEtniaCodigo = maeEtniaCodigo;
    }

    public String getMaeEtniaValor() {
        return maeEtniaValor;
    }

    public void setMaeEtniaValor(String maeEtniaValor) {
        this.maeEtniaValor = maeEtniaValor;
    }

    public Integer getMaeComunidadEtniaId() {
        return maeComunidadEtniaId;
    }

    public void setMaeComunidadEtniaId(Integer maeComunidadEtniaId) {
        this.maeComunidadEtniaId = maeComunidadEtniaId;
    }

    public String getMaeComunidadEtniaCodigo() {
        return maeComunidadEtniaCodigo;
    }

    public void setMaeComunidadEtniaCodigo(String maeComunidadEtniaCodigo) {
        this.maeComunidadEtniaCodigo = maeComunidadEtniaCodigo;
    }

    public String getMaeComunidadEtniaValor() {
        return maeComunidadEtniaValor;
    }

    public void setMaeComunidadEtniaValor(String maeComunidadEtniaValor) {
        this.maeComunidadEtniaValor = maeComunidadEtniaValor;
    }

    public int getMaeCausaNovedadId() {
        return maeCausaNovedadId;
    }

    public void setMaeCausaNovedadId(int maeCausaNovedadId) {
        this.maeCausaNovedadId = maeCausaNovedadId;
    }

    public String getMaeCausaNovedadCodigo() {
        return maeCausaNovedadCodigo;
    }

    public void setMaeCausaNovedadCodigo(String maeCausaNovedadCodigo) {
        this.maeCausaNovedadCodigo = maeCausaNovedadCodigo;
    }

    public String getMaeCausaNovedadValor() {
        return maeCausaNovedadValor;
    }

    public void setMaeCausaNovedadValor(String maeCausaNovedadValor) {
        this.maeCausaNovedadValor = maeCausaNovedadValor;
    }

    public Date getFechaReactivacion() {
        return fechaReactivacion;
    }

    public void setFechaReactivacion(Date fechaReactivacion) {
        this.fechaReactivacion = fechaReactivacion;
    }

    public String getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(String estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public Integer getMaeEstadoCivilId() {
        return maeEstadoCivilId;
    }

    public void setMaeEstadoCivilId(Integer maeEstadoCivilId) {
        this.maeEstadoCivilId = maeEstadoCivilId;
    }

    public String getMaeEstadoCivilCodigo() {
        return maeEstadoCivilCodigo;
    }

    public void setMaeEstadoCivilCodigo(String maeEstadoCivilCodigo) {
        this.maeEstadoCivilCodigo = maeEstadoCivilCodigo;
    }

    public String getMaeEstadoCivilValor() {
        return maeEstadoCivilValor;
    }

    public void setMaeEstadoCivilValor(String maeEstadoCivilValor) {
        this.maeEstadoCivilValor = maeEstadoCivilValor;
    }

    public Date getFechaMovilidad() {
        return fechaMovilidad;
    }

    public void setFechaMovilidad(Date fechaMovilidad) {
        this.fechaMovilidad = fechaMovilidad;
    }

    public String getModeloLiquidacion() {
        return modeloLiquidacion;
    }

    public void setModeloLiquidacion(String modeloLiquidacion) {
        this.modeloLiquidacion = modeloLiquidacion;
    }

    public Integer getMaeModeloLiquidacionId() {
        return maeModeloLiquidacionId;
    }

    public void setMaeModeloLiquidacionId(Integer maeModeloLiquidacionId) {
        this.maeModeloLiquidacionId = maeModeloLiquidacionId;
    }

    public String getMaeModeloLiquidacionCodigo() {
        return maeModeloLiquidacionCodigo;
    }

    public void setMaeModeloLiquidacionCodigo(String maeModeloLiquidacionCodigo) {
        this.maeModeloLiquidacionCodigo = maeModeloLiquidacionCodigo;
    }

    public String getMaeModeloLiquidacionValor() {
        return maeModeloLiquidacionValor;
    }

    public void setMaeModeloLiquidacionValor(String maeModeloLiquidacionValor) {
        this.maeModeloLiquidacionValor = maeModeloLiquidacionValor;
    }

    public Integer getMaeTipoDocumentoAportanteId() {
        return maeTipoDocumentoAportanteId;
    }

    public void setMaeTipoDocumentoAportanteId(Integer maeTipoDocumentoAportanteId) {
        this.maeTipoDocumentoAportanteId = maeTipoDocumentoAportanteId;
    }

    public String getMaeTipoDocumentoAportanteCodigo() {
        return maeTipoDocumentoAportanteCodigo;
    }

    public void setMaeTipoDocumentoAportanteCodigo(String maeTipoDocumentoAportanteCodigo) {
        this.maeTipoDocumentoAportanteCodigo = maeTipoDocumentoAportanteCodigo;
    }

    public String getMaeTipoDocumentoAportanteValor() {
        return maeTipoDocumentoAportanteValor;
    }

    public void setMaeTipoDocumentoAportanteValor(String maeTipoDocumentoAportanteValor) {
        this.maeTipoDocumentoAportanteValor = maeTipoDocumentoAportanteValor;
    }

    public String getNumeroDocumentoAportante() {
        return numeroDocumentoAportante;
    }

    public void setNumeroDocumentoAportante(String numeroDocumentoAportante) {
        this.numeroDocumentoAportante = numeroDocumentoAportante;
    }

    public Integer getMaeActividadEconomicaId() {
        return maeActividadEconomicaId;
    }

    public void setMaeActividadEconomicaId(Integer maeActividadEconomicaId) {
        this.maeActividadEconomicaId = maeActividadEconomicaId;
    }

    public String getMaeActividadEconomicaCodigo() {
        return maeActividadEconomicaCodigo;
    }

    public void setMaeActividadEconomicaCodigo(String maeActividadEconomicaCodigo) {
        this.maeActividadEconomicaCodigo = maeActividadEconomicaCodigo;
    }

    public String getMaeActividadEconomicaValor() {
        return maeActividadEconomicaValor;
    }

    public void setMaeActividadEconomicaValor(String maeActividadEconomicaValor) {
        this.maeActividadEconomicaValor = maeActividadEconomicaValor;
    }

    public Integer getMaeArlId() {
        return maeArlId;
    }

    public void setMaeArlId(Integer maeArlId) {
        this.maeArlId = maeArlId;
    }

    public String getMaeArlCodigo() {
        return maeArlCodigo;
    }

    public void setMaeArlCodigo(String maeArlCodigo) {
        this.maeArlCodigo = maeArlCodigo;
    }

    public String getMaeArlValor() {
        return maeArlValor;
    }

    public void setMaeArlValor(String maeArlValor) {
        this.maeArlValor = maeArlValor;
    }

    public Integer getMaeAfpId() {
        return maeAfpId;
    }

    public void setMaeAfpId(Integer maeAfpId) {
        this.maeAfpId = maeAfpId;
    }

    public String getMaeAfpCodigo() {
        return maeAfpCodigo;
    }

    public void setMaeAfpCodigo(String maeAfpCodigo) {
        this.maeAfpCodigo = maeAfpCodigo;
    }

    public String getMaeAfpValor() {
        return maeAfpValor;
    }

    public void setMaeAfpValor(String maeAfpValor) {
        this.maeAfpValor = maeAfpValor;
    }

    public Integer getMaeCajaCompensacionId() {
        return maeCajaCompensacionId;
    }

    public void setMaeCajaCompensacionId(Integer maeCajaCompensacionId) {
        this.maeCajaCompensacionId = maeCajaCompensacionId;
    }

    public String getMaeCajaCompensacionCodigo() {
        return maeCajaCompensacionCodigo;
    }

    public void setMaeCajaCompensacionCodigo(String maeCajaCompensacionCodigo) {
        this.maeCajaCompensacionCodigo = maeCajaCompensacionCodigo;
    }

    public String getMaeCajaCompensacionValor() {
        return maeCajaCompensacionValor;
    }

    public void setMaeCajaCompensacionValor(String maeCajaCompensacionValor) {
        this.maeCajaCompensacionValor = maeCajaCompensacionValor;
    }

    public Integer getSincronizado() {
        return sincronizado;
    }

    public void setSincronizado(Integer sincronizado) {
        this.sincronizado = sincronizado;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public String getHistoricoAfiliado() {
        return historicoAfiliado;
    }

    public void setHistoricoAfiliado(String historicoAfiliado) {
        this.historicoAfiliado = historicoAfiliado;
    }

    public String getCodigoFonetico() {
        return codigoFonetico;
    }

    public void setCodigoFonetico(String codigoFonetico) {
        this.codigoFonetico = codigoFonetico;
    }

    public Integer getOrigenUltimoRegistro() {
        return origenUltimoRegistro;
    }

    public void setOrigenUltimoRegistro(Integer origenUltimoRegistro) {
        this.origenUltimoRegistro = origenUltimoRegistro;
    }

    public Boolean getAceptaContribucionSolidaria() {
        return aceptaContribucionSolidaria;
    }

    public void setAceptaContribucionSolidaria(Boolean aceptaContribucionSolidaria) {
        this.aceptaContribucionSolidaria = aceptaContribucionSolidaria;
    }

    public Integer getMaeSolidariaPorcentajeId() {
        return maeSolidariaPorcentajeId;
    }

    public void setMaeSolidariaPorcentajeId(Integer maeSolidariaPorcentajeId) {
        this.maeSolidariaPorcentajeId = maeSolidariaPorcentajeId;
    }

    public String getMaeSolidariaPorcentajeCodigo() {
        return maeSolidariaPorcentajeCodigo;
    }

    public void setMaeSolidariaPorcentajeCodigo(String maeSolidariaPorcentajeCodigo) {
        this.maeSolidariaPorcentajeCodigo = maeSolidariaPorcentajeCodigo;
    }

    public String getMaeSolidariaPorcentajeValor() {
        return maeSolidariaPorcentajeValor;
    }

    public void setMaeSolidariaPorcentajeValor(String maeSolidariaPorcentajeValor) {
        this.maeSolidariaPorcentajeValor = maeSolidariaPorcentajeValor;
    }

    public Integer getMaeGeneroIdentificacionId() {
        return maeGeneroIdentificacionId;
    }

    public void setMaeGeneroIdentificacionId(Integer maeGeneroIdentificacionId) {
        this.maeGeneroIdentificacionId = maeGeneroIdentificacionId;
    }

    public String getMaeGeneroIdentificacionCodigo() {
        return maeGeneroIdentificacionCodigo;
    }

    public void setMaeGeneroIdentificacionCodigo(String maeGeneroIdentificacionCodigo) {
        this.maeGeneroIdentificacionCodigo = maeGeneroIdentificacionCodigo;
    }

    public String getMaeGeneroIdentificacionValor() {
        return maeGeneroIdentificacionValor;
    }

    public void setMaeGeneroIdentificacionValor(String maeGeneroIdentificacionValor) {
        this.maeGeneroIdentificacionValor = maeGeneroIdentificacionValor;
    }

    public Boolean getIncapacidadProlongada() {
        return incapacidadProlongada;
    }

    public void setIncapacidadProlongada(Boolean incapacidadProlongada) {
        this.incapacidadProlongada = incapacidadProlongada;
    }

    public Boolean getAcuerdoPago() {
        return acuerdoPago;
    }

    public void setAcuerdoPago(Boolean acuerdoPago) {
        this.acuerdoPago = acuerdoPago;
    }

    public Date getFechaAcuerdoPago() {
        return fechaAcuerdoPago;
    }

    public void setFechaAcuerdoPago(Date fechaAcuerdoPago) {
        this.fechaAcuerdoPago = fechaAcuerdoPago;
    }

    public Boolean getUsuarioGestante() {
        return usuarioGestante;
    }

    public void setUsuarioGestante(Boolean usuarioGestante) {
        this.usuarioGestante = usuarioGestante;
    }

    public Date getFechaFinPeriodoGestacion() {
        return fechaFinPeriodoGestacion;
    }

    public void setFechaFinPeriodoGestacion(Date fechaFinPeriodoGestacion) {
        this.fechaFinPeriodoGestacion = fechaFinPeriodoGestacion;
    }

    public Boolean getDuplicado() {
        return duplicado;
    }

    public void setDuplicado(Boolean duplicado) {
        this.duplicado = duplicado;
    }

    public Boolean getTrasladoPreaprobado() {
        return trasladoPreaprobado;
    }

    public void setTrasladoPreaprobado(Boolean trasladoPreaprobado) {
        this.trasladoPreaprobado = trasladoPreaprobado;
    }

    public String getDireccionAlternaContacto() {
        return direccionAlternaContacto;
    }

    public void setDireccionAlternaContacto(String direccionAlternaContacto) {
        this.direccionAlternaContacto = direccionAlternaContacto;
    }

    public String getNombreContactoEmergencia() {
        return nombreContactoEmergencia;
    }

    public void setNombreContactoEmergencia(String nombreContactoEmergencia) {
        this.nombreContactoEmergencia = nombreContactoEmergencia;
    }

    public String getTelefonoContactoEmergencia() {
        return telefonoContactoEmergencia;
    }

    public void setTelefonoContactoEmergencia(String telefonoContactoEmergencia) {
        this.telefonoContactoEmergencia = telefonoContactoEmergencia;
    }

    public Integer getMaeTipoPortabilidadId() {
        return maeTipoPortabilidadId;
    }

    public void setMaeTipoPortabilidadId(Integer maeTipoPortabilidadId) {
        this.maeTipoPortabilidadId = maeTipoPortabilidadId;
    }

    public String getMaeTipoPortabilidadCodigo() {
        return maeTipoPortabilidadCodigo;
    }

    public void setMaeTipoPortabilidadCodigo(String maeTipoPortabilidadCodigo) {
        this.maeTipoPortabilidadCodigo = maeTipoPortabilidadCodigo;
    }

    public String getMaeTipoPortabilidadValor() {
        return maeTipoPortabilidadValor;
    }

    public void setMaeTipoPortabilidadValor(String maeTipoPortabilidadValor) {
        this.maeTipoPortabilidadValor = maeTipoPortabilidadValor;
    }

    public Integer getMaeMotivoPortabilidadId() {
        return maeMotivoPortabilidadId;
    }

    public void setMaeMotivoPortabilidadId(Integer maeMotivoPortabilidadId) {
        this.maeMotivoPortabilidadId = maeMotivoPortabilidadId;
    }

    public String getMaeMotivoPortabilidadCodigo() {
        return maeMotivoPortabilidadCodigo;
    }

    public void setMaeMotivoPortabilidadCodigo(String maeMotivoPortabilidadCodigo) {
        this.maeMotivoPortabilidadCodigo = maeMotivoPortabilidadCodigo;
    }

    public String getMaeMotivoPortabilidadValor() {
        return maeMotivoPortabilidadValor;
    }

    public void setMaeMotivoPortabilidadValor(String maeMotivoPortabilidadValor) {
        this.maeMotivoPortabilidadValor = maeMotivoPortabilidadValor;
    }

    public Integer getMaeOrigenSolicitudPortabilidadId() {
        return maeOrigenSolicitudPortabilidadId;
    }

    public void setMaeOrigenSolicitudPortabilidadId(Integer maeOrigenSolicitudPortabilidadId) {
        this.maeOrigenSolicitudPortabilidadId = maeOrigenSolicitudPortabilidadId;
    }

    public String getMaeOrigenSolicitudPortabilidadCodigo() {
        return maeOrigenSolicitudPortabilidadCodigo;
    }

    public void setMaeOrigenSolicitudPortabilidadCodigo(String maeOrigenSolicitudPortabilidadCodigo) {
        this.maeOrigenSolicitudPortabilidadCodigo = maeOrigenSolicitudPortabilidadCodigo;
    }

    public String getMaeOrigenSolicitudPortabilidadValor() {
        return maeOrigenSolicitudPortabilidadValor;
    }

    public void setMaeOrigenSolicitudPortabilidadValor(String maeOrigenSolicitudPortabilidadValor) {
        this.maeOrigenSolicitudPortabilidadValor = maeOrigenSolicitudPortabilidadValor;
    }

    public Date getPeriodoInicialPortabilidad() {
        return periodoInicialPortabilidad;
    }

    public void setPeriodoInicialPortabilidad(Date periodoInicialPortabilidad) {
        this.periodoInicialPortabilidad = periodoInicialPortabilidad;
    }

    public Date getPeriodoFinalPortabilidad() {
        return periodoFinalPortabilidad;
    }

    public void setPeriodoFinalPortabilidad(Date periodoFinalPortabilidad) {
        this.periodoFinalPortabilidad = periodoFinalPortabilidad;
    }

    public String getTelefonoContactoPortabilidad() {
        return telefonoContactoPortabilidad;
    }

    public void setTelefonoContactoPortabilidad(String telefonoContactoPortabilidad) {
        this.telefonoContactoPortabilidad = telefonoContactoPortabilidad;
    }

    public String getDireccionPortabilidad() {
        return direccionPortabilidad;
    }

    public void setDireccionPortabilidad(String direccionPortabilidad) {
        this.direccionPortabilidad = direccionPortabilidad;
    }

    public String getCorreoElectronicoPortabilidad() {
        return correoElectronicoPortabilidad;
    }

    public void setCorreoElectronicoPortabilidad(String correoElectronicoPortabilidad) {
        this.correoElectronicoPortabilidad = correoElectronicoPortabilidad;
    }

    public String getObservacionPortabilidad() {
        return observacionPortabilidad;
    }

    public void setObservacionPortabilidad(String observacionPortabilidad) {
        this.observacionPortabilidad = observacionPortabilidad;
    }

    public boolean getPoblacionEspecial() {
        return poblacionEspecial;
    }

    public void setPoblacionEspecial(boolean poblacionEspecial) {
        this.poblacionEspecial = poblacionEspecial;
    }

    public String getUsuarioCrea() {
        return usuarioCrea;
    }

    public void setUsuarioCrea(String usuarioCrea) {
        this.usuarioCrea = usuarioCrea;
    }

    public String getTerminalCrea() {
        return terminalCrea;
    }

    public void setTerminalCrea(String terminalCrea) {
        this.terminalCrea = terminalCrea;
    }

    public Date getFechaHoraCrea() {
        return fechaHoraCrea;
    }

    public void setFechaHoraCrea(Date fechaHoraCrea) {
        this.fechaHoraCrea = fechaHoraCrea;
    }

    public String getUsuarioModifica() {
        return usuarioModifica;
    }

    public void setUsuarioModifica(String usuarioModifica) {
        this.usuarioModifica = usuarioModifica;
    }

    public String getTerminalModifica() {
        return terminalModifica;
    }

    public void setTerminalModifica(String terminalModifica) {
        this.terminalModifica = terminalModifica;
    }

    public Date getFechaHoraModifica() {
        return fechaHoraModifica;
    }

    public void setFechaHoraModifica(Date fechaHoraModifica) {
        this.fechaHoraModifica = fechaHoraModifica;
    }

    @XmlTransient
    public List<AsegPortabilidades> getAsegPortabilidadesList() {
        return asegPortabilidadesList;
    }

    public void setAsegPortabilidadesList(List<AsegPortabilidades> asegPortabilidadesList) {
        this.asegPortabilidadesList = asegPortabilidadesList;
    }

    @XmlTransient
    public List<AuAnexos2> getAuAnexos2List() {
        return auAnexos2List;
    }

    public void setAuAnexos2List(List<AuAnexos2> auAnexos2List) {
        this.auAnexos2List = auAnexos2List;
    }

    @XmlTransient
    public List<AuAnexos4> getAuAnexos4List() {
        return auAnexos4List;
    }

    public void setAuAnexos4List(List<AuAnexos4> auAnexos4List) {
        this.auAnexos4List = auAnexos4List;
    }

    @XmlTransient
    public List<AuAnexos3> getAuAnexos3List() {
        return auAnexos3List;
    }

    public void setAuAnexos3List(List<AuAnexos3> auAnexos3List) {
        this.auAnexos3List = auAnexos3List;
    }

    @XmlTransient
    public List<AsegAfiliadoHistoricos> getAsegAfiliadoHistoricosList() {
        return asegAfiliadoHistoricosList;
    }

    public void setAsegAfiliadoHistoricosList(List<AsegAfiliadoHistoricos> asegAfiliadoHistoricosList) {
        this.asegAfiliadoHistoricosList = asegAfiliadoHistoricosList;
    }

    @XmlTransient
    public List<AsegAfiliadoCertificados> getAsegAfiliadoCertificadosList() {
        return asegAfiliadoCertificadosList;
    }

    public void setAsegAfiliadoCertificadosList(List<AsegAfiliadoCertificados> asegAfiliadoCertificadosList) {
        this.asegAfiliadoCertificadosList = asegAfiliadoCertificadosList;
    }

    @XmlTransient
    public List<AusPersonas> getAusPersonasList() {
        return ausPersonasList;
    }

    public void setAusPersonasList(List<AusPersonas> ausPersonasList) {
        this.ausPersonasList = ausPersonasList;
    }

    @XmlTransient
    public List<MpPrescripciones> getMpPrescripcionesList() {
        return mpPrescripcionesList;
    }

    public void setMpPrescripcionesList(List<MpPrescripciones> mpPrescripcionesList) {
        this.mpPrescripcionesList = mpPrescripcionesList;
    }

    @XmlTransient
    public List<PeAfiliadosSugeridos> getPeAfiliadosSugeridosList() {
        return peAfiliadosSugeridosList;
    }

    public void setPeAfiliadosSugeridosList(List<PeAfiliadosSugeridos> peAfiliadosSugeridosList) {
        this.peAfiliadosSugeridosList = peAfiliadosSugeridosList;
    }

    @XmlTransient
    public List<AuTopeAfiliados> getAuTopeAfiliadosList() {
        return auTopeAfiliadosList;
    }

    public void setAuTopeAfiliadosList(List<AuTopeAfiliados> auTopeAfiliadosList) {
        this.auTopeAfiliadosList = auTopeAfiliadosList;
    }

    @XmlTransient
    public List<AuRechazos> getAuRechazosList() {
        return auRechazosList;
    }

    public void setAuRechazosList(List<AuRechazos> auRechazosList) {
        this.auRechazosList = auRechazosList;
    }

    @XmlTransient
    public List<MpcPrescripcionesHistoricos> getMpcPrescripcionesHistoricosList() {
        return mpcPrescripcionesHistoricosList;
    }

    public void setMpcPrescripcionesHistoricosList(List<MpcPrescripcionesHistoricos> mpcPrescripcionesHistoricosList) {
        this.mpcPrescripcionesHistoricosList = mpcPrescripcionesHistoricosList;
    }

    @XmlTransient
    public List<AuNoSolicitudes> getAuNoSolicitudesList() {
        return auNoSolicitudesList;
    }

    public void setAuNoSolicitudesList(List<AuNoSolicitudes> auNoSolicitudesList) {
        this.auNoSolicitudesList = auNoSolicitudesList;
    }

    @XmlTransient
    public List<AucAfiliados> getAucAfiliadosList() {
        return aucAfiliadosList;
    }

    public void setAucAfiliadosList(List<AucAfiliados> aucAfiliadosList) {
        this.aucAfiliadosList = aucAfiliadosList;
    }

    @XmlTransient
    public List<CmDetalles> getCmDetallesList() {
        return cmDetallesList;
    }

    public void setCmDetallesList(List<CmDetalles> cmDetallesList) {
        this.cmDetallesList = cmDetallesList;
    }

    @XmlTransient
    public List<AsegTraslados> getAsegTrasladosList() {
        return asegTrasladosList;
    }

    public void setAsegTrasladosList(List<AsegTraslados> asegTrasladosList) {
        this.asegTrasladosList = asegTrasladosList;
    }

    @XmlTransient
    public List<PeProgramasTraslados> getPeProgramasTrasladosList() {
        return peProgramasTrasladosList;
    }

    public void setPeProgramasTrasladosList(List<PeProgramasTraslados> peProgramasTrasladosList) {
        this.peProgramasTrasladosList = peProgramasTrasladosList;
    }

    @XmlTransient
    public List<AuCotizaciones> getAuCotizacionesList() {
        return auCotizacionesList;
    }

    public void setAuCotizacionesList(List<AuCotizaciones> auCotizacionesList) {
        this.auCotizacionesList = auCotizacionesList;
    }

    @XmlTransient
    public List<AsegTabulacionEncuestas> getAsegTabulacionEncuestasList() {
        return asegTabulacionEncuestasList;
    }

    public void setAsegTabulacionEncuestasList(List<AsegTabulacionEncuestas> asegTabulacionEncuestasList) {
        this.asegTabulacionEncuestasList = asegTabulacionEncuestasList;
    }

    @XmlTransient
    public List<AsegGruposFamiliares> getAsegGruposFamiliaresList() {
        return asegGruposFamiliaresList;
    }

    public void setAsegGruposFamiliaresList(List<AsegGruposFamiliares> asegGruposFamiliaresList) {
        this.asegGruposFamiliaresList = asegGruposFamiliaresList;
    }

    @XmlTransient
    public List<AsegAfiliadoDocumentos> getAsegAfiliadoDocumentosList() {
        return asegAfiliadoDocumentosList;
    }

    public void setAsegAfiliadoDocumentosList(List<AsegAfiliadoDocumentos> asegAfiliadoDocumentosList) {
        this.asegAfiliadoDocumentosList = asegAfiliadoDocumentosList;
    }

    @XmlTransient
    public List<GjTerceros> getGjTercerosList() {
        return gjTercerosList;
    }

    public void setGjTercerosList(List<GjTerceros> gjTercerosList) {
        this.gjTercerosList = gjTercerosList;
    }

    @XmlTransient
    public List<PeDireccionados> getPeDireccionadosList() {
        return peDireccionadosList;
    }

    public void setPeDireccionadosList(List<PeDireccionados> peDireccionadosList) {
        this.peDireccionadosList = peDireccionadosList;
    }

    @XmlTransient
    public List<AsegAnexos1> getAsegAnexos1List() {
        return asegAnexos1List;
    }

    public void setAsegAnexos1List(List<AsegAnexos1> asegAnexos1List) {
        this.asegAnexos1List = asegAnexos1List;
    }

    @XmlTransient
    public List<GatUsuarios> getGatUsuariosList() {
        return gatUsuariosList;
    }

    public void setGatUsuariosList(List<GatUsuarios> gatUsuariosList) {
        this.gatUsuariosList = gatUsuariosList;
    }

    @XmlTransient
    public List<MpAfiliados> getMpAfiliadosList() {
        return mpAfiliadosList;
    }

    public void setMpAfiliadosList(List<MpAfiliados> mpAfiliadosList) {
        this.mpAfiliadosList = mpAfiliadosList;
    }

    @XmlTransient
    public List<RefAnexos9> getRefAnexos9List() {
        return refAnexos9List;
    }

    public void setRefAnexos9List(List<RefAnexos9> refAnexos9List) {
        this.refAnexos9List = refAnexos9List;
    }

    @XmlTransient
    public List<CntContratoHistoricoPrestaciones> getCntContratoHistoricoPrestacionesList() {
        return cntContratoHistoricoPrestacionesList;
    }

    public void setCntContratoHistoricoPrestacionesList(List<CntContratoHistoricoPrestaciones> cntContratoHistoricoPrestacionesList) {
        this.cntContratoHistoricoPrestacionesList = cntContratoHistoricoPrestacionesList;
    }

    @XmlTransient
    public List<AsegAfiliadoContactos> getAsegAfiliadoContactosList() {
        return asegAfiliadoContactosList;
    }

    public void setAsegAfiliadoContactosList(List<AsegAfiliadoContactos> asegAfiliadoContactosList) {
        this.asegAfiliadoContactosList = asegAfiliadoContactosList;
    }

    @XmlTransient
    public List<AntAnticipos> getAntAnticiposList() {
        return antAnticiposList;
    }

    public void setAntAnticiposList(List<AntAnticipos> antAnticiposList) {
        this.antAnticiposList = antAnticiposList;
    }

    @XmlTransient
    public List<CsCopagoModeradoraHistoricos> getCsCopagoModeradoraHistoricosList() {
        return csCopagoModeradoraHistoricosList;
    }

    public void setCsCopagoModeradoraHistoricosList(List<CsCopagoModeradoraHistoricos> csCopagoModeradoraHistoricosList) {
        this.csCopagoModeradoraHistoricosList = csCopagoModeradoraHistoricosList;
    }

    @XmlTransient
    public List<PeAfiliadosProgramas> getPeAfiliadosProgramasList() {
        return peAfiliadosProgramasList;
    }

    public void setPeAfiliadosProgramasList(List<PeAfiliadosProgramas> peAfiliadosProgramasList) {
        this.peAfiliadosProgramasList = peAfiliadosProgramasList;
    }

    public CntPrestadorSedes getPortabilidadCntPrestadorSedesId() {
        return portabilidadCntPrestadorSedesId;
    }

    public void setPortabilidadCntPrestadorSedesId(CntPrestadorSedes portabilidadCntPrestadorSedesId) {
        this.portabilidadCntPrestadorSedesId = portabilidadCntPrestadorSedesId;
    }

    public AsegGruposFamiliares getAsegGruposFamiliaresId() {
        return asegGruposFamiliaresId;
    }

    public void setAsegGruposFamiliaresId(AsegGruposFamiliares asegGruposFamiliaresId) {
        this.asegGruposFamiliaresId = asegGruposFamiliaresId;
    }

    public CntPrestadorSedes getPrimariaCntPrestadorSedesId() {
        return primariaCntPrestadorSedesId;
    }

    public void setPrimariaCntPrestadorSedesId(CntPrestadorSedes primariaCntPrestadorSedesId) {
        this.primariaCntPrestadorSedesId = primariaCntPrestadorSedesId;
    }

    public CntPrestadorSedes getOdontologiaCntPrestadorSedesId() {
        return odontologiaCntPrestadorSedesId;
    }

    public void setOdontologiaCntPrestadorSedesId(CntPrestadorSedes odontologiaCntPrestadorSedesId) {
        this.odontologiaCntPrestadorSedesId = odontologiaCntPrestadorSedesId;
    }

    public GnDivisiones getGnDivisionesId() {
        return gnDivisionesId;
    }

    public void setGnDivisionesId(GnDivisiones gnDivisionesId) {
        this.gnDivisionesId = gnDivisionesId;
    }

    public GnUbicaciones getGnUbicacionActualAfiliado() {
        return gnUbicacionActualAfiliado;
    }

    public void setGnUbicacionActualAfiliado(GnUbicaciones gnUbicacionActualAfiliado) {
        this.gnUbicacionActualAfiliado = gnUbicacionActualAfiliado;
    }

    public GnUbicaciones getGnUbicacionesLugarNacimientoId() {
        return gnUbicacionesLugarNacimientoId;
    }

    public void setGnUbicacionesLugarNacimientoId(GnUbicaciones gnUbicacionesLugarNacimientoId) {
        this.gnUbicacionesLugarNacimientoId = gnUbicacionesLugarNacimientoId;
    }

    public GnUbicacionBarrios getGnUbicacionBarriosId() {
        return gnUbicacionBarriosId;
    }

    public void setGnUbicacionBarriosId(GnUbicacionBarrios gnUbicacionBarriosId) {
        this.gnUbicacionBarriosId = gnUbicacionBarriosId;
    }

    public GnUbicaciones getNacionalidadUbicacionesId() {
        return nacionalidadUbicacionesId;
    }

    public void setNacionalidadUbicacionesId(GnUbicaciones nacionalidadUbicacionesId) {
        this.nacionalidadUbicacionesId = nacionalidadUbicacionesId;
    }

    public GnUbicaciones getNacimientoUbicacionesId() {
        return nacimientoUbicacionesId;
    }

    public void setNacimientoUbicacionesId(GnUbicaciones nacimientoUbicacionesId) {
        this.nacimientoUbicacionesId = nacimientoUbicacionesId;
    }

    public GnUbicaciones getAfiliacionUbicacionesId() {
        return afiliacionUbicacionesId;
    }

    public void setAfiliacionUbicacionesId(GnUbicaciones afiliacionUbicacionesId) {
        this.afiliacionUbicacionesId = afiliacionUbicacionesId;
    }

    public GnUbicaciones getResidenciaUbicacionId() {
        return residenciaUbicacionId;
    }

    public void setResidenciaUbicacionId(GnUbicaciones residenciaUbicacionId) {
        this.residenciaUbicacionId = residenciaUbicacionId;
    }

    @XmlTransient
    public List<PeTelefonos> getPeTelefonosList() {
        return peTelefonosList;
    }

    public void setPeTelefonosList(List<PeTelefonos> peTelefonosList) {
        this.peTelefonosList = peTelefonosList;
    }

    @XmlTransient
    public List<AuSeguimientoAfiliados> getAuSeguimientoAfiliadosList() {
        return auSeguimientoAfiliadosList;
    }

    public void setAuSeguimientoAfiliadosList(List<AuSeguimientoAfiliados> auSeguimientoAfiliadosList) {
        this.auSeguimientoAfiliadosList = auSeguimientoAfiliadosList;
    }

    @XmlTransient
    public List<AuAnexo2Rescates> getAuAnexo2RescatesList() {
        return auAnexo2RescatesList;
    }

    public void setAuAnexo2RescatesList(List<AuAnexo2Rescates> auAnexo2RescatesList) {
        this.auAnexo2RescatesList = auAnexo2RescatesList;
    }

    @XmlTransient
    public List<AsegReporteNovedades> getAsegReporteNovedadesList() {
        return asegReporteNovedadesList;
    }

    public void setAsegReporteNovedadesList(List<AsegReporteNovedades> asegReporteNovedadesList) {
        this.asegReporteNovedadesList = asegReporteNovedadesList;
    }

    @XmlTransient
    public List<MpcPrescripciones> getMpcPrescripcionesList() {
        return mpcPrescripcionesList;
    }

    public void setMpcPrescripcionesList(List<MpcPrescripciones> mpcPrescripcionesList) {
        this.mpcPrescripcionesList = mpcPrescripcionesList;
    }

    @XmlTransient
    public List<AsegRadicadoNovedades> getAsegRadicadoNovedadesList() {
        return asegRadicadoNovedadesList;
    }

    public void setAsegRadicadoNovedadesList(List<AsegRadicadoNovedades> asegRadicadoNovedadesList) {
        this.asegRadicadoNovedadesList = asegRadicadoNovedadesList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AsegAfiliados)) {
            return false;
        }
        AsegAfiliados other = (AsegAfiliados) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.AsegAfiliados[ id=" + id + " ]";
    }
    
}
