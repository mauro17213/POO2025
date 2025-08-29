/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.saviasaludeps.savia.ejb.entidades;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "mp_prescripcion_medicamentos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MpPrescripcionMedicamentos.findAll", query = "SELECT m FROM MpPrescripcionMedicamentos m"),
    @NamedQuery(name = "MpPrescripcionMedicamentos.findById", query = "SELECT m FROM MpPrescripcionMedicamentos m WHERE m.id = :id"),
    @NamedQuery(name = "MpPrescripcionMedicamentos.findByIdTransaccion", query = "SELECT m FROM MpPrescripcionMedicamentos m WHERE m.idTransaccion = :idTransaccion"),
    @NamedQuery(name = "MpPrescripcionMedicamentos.findByIdDireccionamiento", query = "SELECT m FROM MpPrescripcionMedicamentos m WHERE m.idDireccionamiento = :idDireccionamiento"),
    @NamedQuery(name = "MpPrescripcionMedicamentos.findByEstado", query = "SELECT m FROM MpPrescripcionMedicamentos m WHERE m.estado = :estado"),
    @NamedQuery(name = "MpPrescripcionMedicamentos.findByEstadoJuntaProfesionales", query = "SELECT m FROM MpPrescripcionMedicamentos m WHERE m.estadoJuntaProfesionales = :estadoJuntaProfesionales"),
    @NamedQuery(name = "MpPrescripcionMedicamentos.findByConsecutivoOrden", query = "SELECT m FROM MpPrescripcionMedicamentos m WHERE m.consecutivoOrden = :consecutivoOrden"),
    @NamedQuery(name = "MpPrescripcionMedicamentos.findByTipoTecnologia", query = "SELECT m FROM MpPrescripcionMedicamentos m WHERE m.tipoTecnologia = :tipoTecnologia"),
    @NamedQuery(name = "MpPrescripcionMedicamentos.findByTipoMedicamento", query = "SELECT m FROM MpPrescripcionMedicamentos m WHERE m.tipoMedicamento = :tipoMedicamento"),
    @NamedQuery(name = "MpPrescripcionMedicamentos.findByTipoPrestacion", query = "SELECT m FROM MpPrescripcionMedicamentos m WHERE m.tipoPrestacion = :tipoPrestacion"),
    @NamedQuery(name = "MpPrescripcionMedicamentos.findByMedPbsUtilizado", query = "SELECT m FROM MpPrescripcionMedicamentos m WHERE m.medPbsUtilizado = :medPbsUtilizado"),
    @NamedQuery(name = "MpPrescripcionMedicamentos.findByCausaSolicitud1", query = "SELECT m FROM MpPrescripcionMedicamentos m WHERE m.causaSolicitud1 = :causaSolicitud1"),
    @NamedQuery(name = "MpPrescripcionMedicamentos.findByCausaSolicitud2", query = "SELECT m FROM MpPrescripcionMedicamentos m WHERE m.causaSolicitud2 = :causaSolicitud2"),
    @NamedQuery(name = "MpPrescripcionMedicamentos.findByCausaSolicitud3", query = "SELECT m FROM MpPrescripcionMedicamentos m WHERE m.causaSolicitud3 = :causaSolicitud3"),
    @NamedQuery(name = "MpPrescripcionMedicamentos.findByCausaSolicitud4", query = "SELECT m FROM MpPrescripcionMedicamentos m WHERE m.causaSolicitud4 = :causaSolicitud4"),
    @NamedQuery(name = "MpPrescripcionMedicamentos.findByCausaSolicitud5", query = "SELECT m FROM MpPrescripcionMedicamentos m WHERE m.causaSolicitud5 = :causaSolicitud5"),
    @NamedQuery(name = "MpPrescripcionMedicamentos.findByCausaSolicitud6", query = "SELECT m FROM MpPrescripcionMedicamentos m WHERE m.causaSolicitud6 = :causaSolicitud6"),
    @NamedQuery(name = "MpPrescripcionMedicamentos.findByRazonCausaSolicitud31", query = "SELECT m FROM MpPrescripcionMedicamentos m WHERE m.razonCausaSolicitud31 = :razonCausaSolicitud31"),
    @NamedQuery(name = "MpPrescripcionMedicamentos.findByDescripcionRazon31", query = "SELECT m FROM MpPrescripcionMedicamentos m WHERE m.descripcionRazon31 = :descripcionRazon31"),
    @NamedQuery(name = "MpPrescripcionMedicamentos.findByRazonCausaSolicitud32", query = "SELECT m FROM MpPrescripcionMedicamentos m WHERE m.razonCausaSolicitud32 = :razonCausaSolicitud32"),
    @NamedQuery(name = "MpPrescripcionMedicamentos.findByDescripcionRazon32", query = "SELECT m FROM MpPrescripcionMedicamentos m WHERE m.descripcionRazon32 = :descripcionRazon32"),
    @NamedQuery(name = "MpPrescripcionMedicamentos.findByRazonCausaSolicitud41", query = "SELECT m FROM MpPrescripcionMedicamentos m WHERE m.razonCausaSolicitud41 = :razonCausaSolicitud41"),
    @NamedQuery(name = "MpPrescripcionMedicamentos.findByDescripcionRazon41", query = "SELECT m FROM MpPrescripcionMedicamentos m WHERE m.descripcionRazon41 = :descripcionRazon41"),
    @NamedQuery(name = "MpPrescripcionMedicamentos.findByRazonCausaSolicitud42", query = "SELECT m FROM MpPrescripcionMedicamentos m WHERE m.razonCausaSolicitud42 = :razonCausaSolicitud42"),
    @NamedQuery(name = "MpPrescripcionMedicamentos.findByDescripcionRazon42", query = "SELECT m FROM MpPrescripcionMedicamentos m WHERE m.descripcionRazon42 = :descripcionRazon42"),
    @NamedQuery(name = "MpPrescripcionMedicamentos.findByRazonCausaSolicitud43", query = "SELECT m FROM MpPrescripcionMedicamentos m WHERE m.razonCausaSolicitud43 = :razonCausaSolicitud43"),
    @NamedQuery(name = "MpPrescripcionMedicamentos.findByDescripcionRazon43", query = "SELECT m FROM MpPrescripcionMedicamentos m WHERE m.descripcionRazon43 = :descripcionRazon43"),
    @NamedQuery(name = "MpPrescripcionMedicamentos.findByRazonCausaSolicitud44", query = "SELECT m FROM MpPrescripcionMedicamentos m WHERE m.razonCausaSolicitud44 = :razonCausaSolicitud44"),
    @NamedQuery(name = "MpPrescripcionMedicamentos.findByDescripcionRazon44", query = "SELECT m FROM MpPrescripcionMedicamentos m WHERE m.descripcionRazon44 = :descripcionRazon44"),
    @NamedQuery(name = "MpPrescripcionMedicamentos.findByRazonCausaSolicitud51", query = "SELECT m FROM MpPrescripcionMedicamentos m WHERE m.razonCausaSolicitud51 = :razonCausaSolicitud51"),
    @NamedQuery(name = "MpPrescripcionMedicamentos.findByDescripcionRazon51", query = "SELECT m FROM MpPrescripcionMedicamentos m WHERE m.descripcionRazon51 = :descripcionRazon51"),
    @NamedQuery(name = "MpPrescripcionMedicamentos.findByRazonCausaSolicitud52", query = "SELECT m FROM MpPrescripcionMedicamentos m WHERE m.razonCausaSolicitud52 = :razonCausaSolicitud52"),
    @NamedQuery(name = "MpPrescripcionMedicamentos.findByDescripcionRazon52", query = "SELECT m FROM MpPrescripcionMedicamentos m WHERE m.descripcionRazon52 = :descripcionRazon52"),
    @NamedQuery(name = "MpPrescripcionMedicamentos.findByRazonCausaSolicitud53", query = "SELECT m FROM MpPrescripcionMedicamentos m WHERE m.razonCausaSolicitud53 = :razonCausaSolicitud53"),
    @NamedQuery(name = "MpPrescripcionMedicamentos.findByDescripcionRazon53", query = "SELECT m FROM MpPrescripcionMedicamentos m WHERE m.descripcionRazon53 = :descripcionRazon53"),
    @NamedQuery(name = "MpPrescripcionMedicamentos.findByRazonCausaSolicitud54", query = "SELECT m FROM MpPrescripcionMedicamentos m WHERE m.razonCausaSolicitud54 = :razonCausaSolicitud54"),
    @NamedQuery(name = "MpPrescripcionMedicamentos.findByDescripcionRazon54", query = "SELECT m FROM MpPrescripcionMedicamentos m WHERE m.descripcionRazon54 = :descripcionRazon54"),
    @NamedQuery(name = "MpPrescripcionMedicamentos.findByRazonCausaSolicitud5", query = "SELECT m FROM MpPrescripcionMedicamentos m WHERE m.razonCausaSolicitud5 = :razonCausaSolicitud5"),
    @NamedQuery(name = "MpPrescripcionMedicamentos.findByDescripcionRazon5", query = "SELECT m FROM MpPrescripcionMedicamentos m WHERE m.descripcionRazon5 = :descripcionRazon5"),
    @NamedQuery(name = "MpPrescripcionMedicamentos.findByDescripcionMedicamentoPrincipioActivo", query = "SELECT m FROM MpPrescripcionMedicamentos m WHERE m.descripcionMedicamentoPrincipioActivo = :descripcionMedicamentoPrincipioActivo"),
    @NamedQuery(name = "MpPrescripcionMedicamentos.findByCodigoFormulaFarmaceutica", query = "SELECT m FROM MpPrescripcionMedicamentos m WHERE m.codigoFormulaFarmaceutica = :codigoFormulaFarmaceutica"),
    @NamedQuery(name = "MpPrescripcionMedicamentos.findByJustificacionNoPbs", query = "SELECT m FROM MpPrescripcionMedicamentos m WHERE m.justificacionNoPbs = :justificacionNoPbs"),
    @NamedQuery(name = "MpPrescripcionMedicamentos.findByDosis", query = "SELECT m FROM MpPrescripcionMedicamentos m WHERE m.dosis = :dosis"),
    @NamedQuery(name = "MpPrescripcionMedicamentos.findByNumeroFrecuenciaAdministracion", query = "SELECT m FROM MpPrescripcionMedicamentos m WHERE m.numeroFrecuenciaAdministracion = :numeroFrecuenciaAdministracion"),
    @NamedQuery(name = "MpPrescripcionMedicamentos.findByCodigoFrecuenciaAdministracion", query = "SELECT m FROM MpPrescripcionMedicamentos m WHERE m.codigoFrecuenciaAdministracion = :codigoFrecuenciaAdministracion"),
    @NamedQuery(name = "MpPrescripcionMedicamentos.findByIndicacionesEspeciales", query = "SELECT m FROM MpPrescripcionMedicamentos m WHERE m.indicacionesEspeciales = :indicacionesEspeciales"),
    @NamedQuery(name = "MpPrescripcionMedicamentos.findByCantidadTratamiento", query = "SELECT m FROM MpPrescripcionMedicamentos m WHERE m.cantidadTratamiento = :cantidadTratamiento"),
    @NamedQuery(name = "MpPrescripcionMedicamentos.findByDuracionTratamiento", query = "SELECT m FROM MpPrescripcionMedicamentos m WHERE m.duracionTratamiento = :duracionTratamiento"),
    @NamedQuery(name = "MpPrescripcionMedicamentos.findByCantidadTotalFormulada", query = "SELECT m FROM MpPrescripcionMedicamentos m WHERE m.cantidadTotalFormulada = :cantidadTotalFormulada"),
    @NamedQuery(name = "MpPrescripcionMedicamentos.findByResultSatisPrev", query = "SELECT m FROM MpPrescripcionMedicamentos m WHERE m.resultSatisPrev = :resultSatisPrev"),
    @NamedQuery(name = "MpPrescripcionMedicamentos.findByRegistroInvima", query = "SELECT m FROM MpPrescripcionMedicamentos m WHERE m.registroInvima = :registroInvima"),
    @NamedQuery(name = "MpPrescripcionMedicamentos.findByNoUsoSanitarioUnirs", query = "SELECT m FROM MpPrescripcionMedicamentos m WHERE m.noUsoSanitarioUnirs = :noUsoSanitarioUnirs"),
    @NamedQuery(name = "MpPrescripcionMedicamentos.findByIndicacionRecibida", query = "SELECT m FROM MpPrescripcionMedicamentos m WHERE m.indicacionRecibida = :indicacionRecibida"),
    @NamedQuery(name = "MpPrescripcionMedicamentos.findByEsDiagnosticoVih", query = "SELECT m FROM MpPrescripcionMedicamentos m WHERE m.esDiagnosticoVih = :esDiagnosticoVih"),
    @NamedQuery(name = "MpPrescripcionMedicamentos.findByEsDiagnosticoCancer", query = "SELECT m FROM MpPrescripcionMedicamentos m WHERE m.esDiagnosticoCancer = :esDiagnosticoCancer"),
    @NamedQuery(name = "MpPrescripcionMedicamentos.findByEsDiagnosticoEnfermedadRenal", query = "SELECT m FROM MpPrescripcionMedicamentos m WHERE m.esDiagnosticoEnfermedadRenal = :esDiagnosticoEnfermedadRenal"),
    @NamedQuery(name = "MpPrescripcionMedicamentos.findByEsDiagnosticoDesnutricion", query = "SELECT m FROM MpPrescripcionMedicamentos m WHERE m.esDiagnosticoDesnutricion = :esDiagnosticoDesnutricion"),
    @NamedQuery(name = "MpPrescripcionMedicamentos.findByTipoProductoNutricional", query = "SELECT m FROM MpPrescripcionMedicamentos m WHERE m.tipoProductoNutricional = :tipoProductoNutricional"),
    @NamedQuery(name = "MpPrescripcionMedicamentos.findByDescripcionProductoNutricional", query = "SELECT m FROM MpPrescripcionMedicamentos m WHERE m.descripcionProductoNutricional = :descripcionProductoNutricional"),
    @NamedQuery(name = "MpPrescripcionMedicamentos.findByCantidadTotalEntrega", query = "SELECT m FROM MpPrescripcionMedicamentos m WHERE m.cantidadTotalEntrega = :cantidadTotalEntrega"),
    @NamedQuery(name = "MpPrescripcionMedicamentos.findByEntregados", query = "SELECT m FROM MpPrescripcionMedicamentos m WHERE m.entregados = :entregados"),
    @NamedQuery(name = "MpPrescripcionMedicamentos.findByPendientes", query = "SELECT m FROM MpPrescripcionMedicamentos m WHERE m.pendientes = :pendientes"),
    @NamedQuery(name = "MpPrescripcionMedicamentos.findByFechaDireccionamiento", query = "SELECT m FROM MpPrescripcionMedicamentos m WHERE m.fechaDireccionamiento = :fechaDireccionamiento"),
    @NamedQuery(name = "MpPrescripcionMedicamentos.findByFechaMaximaEntrega", query = "SELECT m FROM MpPrescripcionMedicamentos m WHERE m.fechaMaximaEntrega = :fechaMaximaEntrega"),
    @NamedQuery(name = "MpPrescripcionMedicamentos.findByMaeProductosNutricionalesId", query = "SELECT m FROM MpPrescripcionMedicamentos m WHERE m.maeProductosNutricionalesId = :maeProductosNutricionalesId"),
    @NamedQuery(name = "MpPrescripcionMedicamentos.findByMaeProductosNutricionalesCodigo", query = "SELECT m FROM MpPrescripcionMedicamentos m WHERE m.maeProductosNutricionalesCodigo = :maeProductosNutricionalesCodigo"),
    @NamedQuery(name = "MpPrescripcionMedicamentos.findByMaeProductosNutricionalesValor", query = "SELECT m FROM MpPrescripcionMedicamentos m WHERE m.maeProductosNutricionalesValor = :maeProductosNutricionalesValor"),
    @NamedQuery(name = "MpPrescripcionMedicamentos.findByValorUnitario", query = "SELECT m FROM MpPrescripcionMedicamentos m WHERE m.valorUnitario = :valorUnitario"),
    @NamedQuery(name = "MpPrescripcionMedicamentos.findByDireccionado", query = "SELECT m FROM MpPrescripcionMedicamentos m WHERE m.direccionado = :direccionado"),
    @NamedQuery(name = "MpPrescripcionMedicamentos.findByCicloFacturacion", query = "SELECT m FROM MpPrescripcionMedicamentos m WHERE m.cicloFacturacion = :cicloFacturacion"),
    @NamedQuery(name = "MpPrescripcionMedicamentos.findByCodFacturaIps", query = "SELECT m FROM MpPrescripcionMedicamentos m WHERE m.codFacturaIps = :codFacturaIps"),
    @NamedQuery(name = "MpPrescripcionMedicamentos.findByJustificacionTecJunta", query = "SELECT m FROM MpPrescripcionMedicamentos m WHERE m.justificacionTecJunta = :justificacionTecJunta"),
    @NamedQuery(name = "MpPrescripcionMedicamentos.findByModJunta", query = "SELECT m FROM MpPrescripcionMedicamentos m WHERE m.modJunta = :modJunta"),
    @NamedQuery(name = "MpPrescripcionMedicamentos.findByNumActaJunta", query = "SELECT m FROM MpPrescripcionMedicamentos m WHERE m.numActaJunta = :numActaJunta"),
    @NamedQuery(name = "MpPrescripcionMedicamentos.findByReaccionesAdversasPaciente", query = "SELECT m FROM MpPrescripcionMedicamentos m WHERE m.reaccionesAdversasPaciente = :reaccionesAdversasPaciente"),
    @NamedQuery(name = "MpPrescripcionMedicamentos.findByCantidadDireccionada", query = "SELECT m FROM MpPrescripcionMedicamentos m WHERE m.cantidadDireccionada = :cantidadDireccionada"),
    @NamedQuery(name = "MpPrescripcionMedicamentos.findByCantidadMinimaDispensada", query = "SELECT m FROM MpPrescripcionMedicamentos m WHERE m.cantidadMinimaDispensada = :cantidadMinimaDispensada"),
    @NamedQuery(name = "MpPrescripcionMedicamentos.findByCantidadPrescrita", query = "SELECT m FROM MpPrescripcionMedicamentos m WHERE m.cantidadPrescrita = :cantidadPrescrita"),
    @NamedQuery(name = "MpPrescripcionMedicamentos.findByCodigoMipresEntregar", query = "SELECT m FROM MpPrescripcionMedicamentos m WHERE m.codigoMipresEntregar = :codigoMipresEntregar"),
    @NamedQuery(name = "MpPrescripcionMedicamentos.findByCodFormulada", query = "SELECT m FROM MpPrescripcionMedicamentos m WHERE m.codFormulada = :codFormulada"),
    @NamedQuery(name = "MpPrescripcionMedicamentos.findByEstadoAuditoria", query = "SELECT m FROM MpPrescripcionMedicamentos m WHERE m.estadoAuditoria = :estadoAuditoria"),
    @NamedQuery(name = "MpPrescripcionMedicamentos.findByConsecutivoJuntaTecnologia", query = "SELECT m FROM MpPrescripcionMedicamentos m WHERE m.consecutivoJuntaTecnologia = :consecutivoJuntaTecnologia"),
    @NamedQuery(name = "MpPrescripcionMedicamentos.findByFechaActaJunta", query = "SELECT m FROM MpPrescripcionMedicamentos m WHERE m.fechaActaJunta = :fechaActaJunta"),
    @NamedQuery(name = "MpPrescripcionMedicamentos.findByEstadoPrescripcion", query = "SELECT m FROM MpPrescripcionMedicamentos m WHERE m.estadoPrescripcion = :estadoPrescripcion"),
    @NamedQuery(name = "MpPrescripcionMedicamentos.findByIndicacionUnirs", query = "SELECT m FROM MpPrescripcionMedicamentos m WHERE m.indicacionUnirs = :indicacionUnirs"),
    @NamedQuery(name = "MpPrescripcionMedicamentos.findByFormaFarmaceutica", query = "SELECT m FROM MpPrescripcionMedicamentos m WHERE m.formaFarmaceutica = :formaFarmaceutica"),
    @NamedQuery(name = "MpPrescripcionMedicamentos.findByConsecOrdenPrincActivo", query = "SELECT m FROM MpPrescripcionMedicamentos m WHERE m.consecOrdenPrincActivo = :consecOrdenPrincActivo"),
    @NamedQuery(name = "MpPrescripcionMedicamentos.findByEnFaseExperimental", query = "SELECT m FROM MpPrescripcionMedicamentos m WHERE m.enFaseExperimental = :enFaseExperimental"),
    @NamedQuery(name = "MpPrescripcionMedicamentos.findByCantidadMedPrincipioActivo", query = "SELECT m FROM MpPrescripcionMedicamentos m WHERE m.cantidadMedPrincipioActivo = :cantidadMedPrincipioActivo"),
    @NamedQuery(name = "MpPrescripcionMedicamentos.findByDescMedPbsUpc", query = "SELECT m FROM MpPrescripcionMedicamentos m WHERE m.descMedPbsUpc = :descMedPbsUpc"),
    @NamedQuery(name = "MpPrescripcionMedicamentos.findByDescartoNoExistePbs", query = "SELECT m FROM MpPrescripcionMedicamentos m WHERE m.descartoNoExistePbs = :descartoNoExistePbs"),
    @NamedQuery(name = "MpPrescripcionMedicamentos.findByListaNoUsoSanitarioUnirs", query = "SELECT m FROM MpPrescripcionMedicamentos m WHERE m.listaNoUsoSanitarioUnirs = :listaNoUsoSanitarioUnirs"),
    @NamedQuery(name = "MpPrescripcionMedicamentos.findByExistentePbsCargoUpc", query = "SELECT m FROM MpPrescripcionMedicamentos m WHERE m.existentePbsCargoUpc = :existentePbsCargoUpc"),
    @NamedQuery(name = "MpPrescripcionMedicamentos.findByFinancPbsCargoUpc", query = "SELECT m FROM MpPrescripcionMedicamentos m WHERE m.financPbsCargoUpc = :financPbsCargoUpc"),
    @NamedQuery(name = "MpPrescripcionMedicamentos.findByUtilizoNutriMedExistentePbsUpc", query = "SELECT m FROM MpPrescripcionMedicamentos m WHERE m.utilizoNutriMedExistentePbsUpc = :utilizoNutriMedExistentePbsUpc"),
    @NamedQuery(name = "MpPrescripcionMedicamentos.findByEvidenciaEfiEfecClinica", query = "SELECT m FROM MpPrescripcionMedicamentos m WHERE m.evidenciaEfiEfecClinica = :evidenciaEfiEfecClinica"),
    @NamedQuery(name = "MpPrescripcionMedicamentos.findByExistePbsCargoUpc", query = "SELECT m FROM MpPrescripcionMedicamentos m WHERE m.existePbsCargoUpc = :existePbsCargoUpc"),
    @NamedQuery(name = "MpPrescripcionMedicamentos.findByObsReaccPbsCargoUpc", query = "SELECT m FROM MpPrescripcionMedicamentos m WHERE m.obsReaccPbsCargoUpc = :obsReaccPbsCargoUpc"),
    @NamedQuery(name = "MpPrescripcionMedicamentos.findByDescartoEvidenciaEfiEfecClinica", query = "SELECT m FROM MpPrescripcionMedicamentos m WHERE m.descartoEvidenciaEfiEfecClinica = :descartoEvidenciaEfiEfecClinica"),
    @NamedQuery(name = "MpPrescripcionMedicamentos.findByRegAprobAutClin", query = "SELECT m FROM MpPrescripcionMedicamentos m WHERE m.regAprobAutClin = :regAprobAutClin"),
    @NamedQuery(name = "MpPrescripcionMedicamentos.findByDescartoAlternativa", query = "SELECT m FROM MpPrescripcionMedicamentos m WHERE m.descartoAlternativa = :descartoAlternativa"),
    @NamedQuery(name = "MpPrescripcionMedicamentos.findByAtendido", query = "SELECT m FROM MpPrescripcionMedicamentos m WHERE m.atendido = :atendido"),
    @NamedQuery(name = "MpPrescripcionMedicamentos.findByTipoTutela", query = "SELECT m FROM MpPrescripcionMedicamentos m WHERE m.tipoTutela = :tipoTutela"),
    @NamedQuery(name = "MpPrescripcionMedicamentos.findByBanderaAtencion", query = "SELECT m FROM MpPrescripcionMedicamentos m WHERE m.banderaAtencion = :banderaAtencion"),
    @NamedQuery(name = "MpPrescripcionMedicamentos.findByDescartoReaccionesAdversas", query = "SELECT m FROM MpPrescripcionMedicamentos m WHERE m.descartoReaccionesAdversas = :descartoReaccionesAdversas"),
    @NamedQuery(name = "MpPrescripcionMedicamentos.findByCubiertoPbsCargoUpc", query = "SELECT m FROM MpPrescripcionMedicamentos m WHERE m.cubiertoPbsCargoUpc = :cubiertoPbsCargoUpc"),
    @NamedQuery(name = "MpPrescripcionMedicamentos.findByPbsUtilizado", query = "SELECT m FROM MpPrescripcionMedicamentos m WHERE m.pbsUtilizado = :pbsUtilizado"),
    @NamedQuery(name = "MpPrescripcionMedicamentos.findByCodigoViaAdministracion", query = "SELECT m FROM MpPrescripcionMedicamentos m WHERE m.codigoViaAdministracion = :codigoViaAdministracion"),
    @NamedQuery(name = "MpPrescripcionMedicamentos.findByDosisUnidadMedida", query = "SELECT m FROM MpPrescripcionMedicamentos m WHERE m.dosisUnidadMedida = :dosisUnidadMedida"),
    @NamedQuery(name = "MpPrescripcionMedicamentos.findByUnidadFarmaceuticaCantidadTotal", query = "SELECT m FROM MpPrescripcionMedicamentos m WHERE m.unidadFarmaceuticaCantidadTotal = :unidadFarmaceuticaCantidadTotal"),
    @NamedQuery(name = "MpPrescripcionMedicamentos.findByUsuarioCrea", query = "SELECT m FROM MpPrescripcionMedicamentos m WHERE m.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "MpPrescripcionMedicamentos.findByTerminalCrea", query = "SELECT m FROM MpPrescripcionMedicamentos m WHERE m.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "MpPrescripcionMedicamentos.findByFechaHoraCrea", query = "SELECT m FROM MpPrescripcionMedicamentos m WHERE m.fechaHoraCrea = :fechaHoraCrea"),
    @NamedQuery(name = "MpPrescripcionMedicamentos.findByUsuarioModifica", query = "SELECT m FROM MpPrescripcionMedicamentos m WHERE m.usuarioModifica = :usuarioModifica"),
    @NamedQuery(name = "MpPrescripcionMedicamentos.findByTerminalModifica", query = "SELECT m FROM MpPrescripcionMedicamentos m WHERE m.terminalModifica = :terminalModifica"),
    @NamedQuery(name = "MpPrescripcionMedicamentos.findByFechaHoraModifica", query = "SELECT m FROM MpPrescripcionMedicamentos m WHERE m.fechaHoraModifica = :fechaHoraModifica"),
    @NamedQuery(name = "MpPrescripcionMedicamentos.findByUsuarioAtiende", query = "SELECT m FROM MpPrescripcionMedicamentos m WHERE m.usuarioAtiende = :usuarioAtiende"),
    @NamedQuery(name = "MpPrescripcionMedicamentos.findByTerminalAtiende", query = "SELECT m FROM MpPrescripcionMedicamentos m WHERE m.terminalAtiende = :terminalAtiende"),
    @NamedQuery(name = "MpPrescripcionMedicamentos.findByFechaHoraAtiende", query = "SELECT m FROM MpPrescripcionMedicamentos m WHERE m.fechaHoraAtiende = :fechaHoraAtiende")})
public class MpPrescripcionMedicamentos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "id_transaccion")
    private Integer idTransaccion;
    @Size(max = 32)
    @Column(name = "id_direccionamiento")
    private String idDireccionamiento;
    @Basic(optional = false)
    @NotNull
    @Column(name = "estado")
    private int estado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "estado_junta_profesionales")
    private int estadoJuntaProfesionales;
    @Basic(optional = false)
    @NotNull
    @Column(name = "consecutivo_orden")
    private int consecutivoOrden;
    @Basic(optional = false)
    @NotNull
    @Column(name = "tipo_tecnologia")
    private int tipoTecnologia;
    @Column(name = "tipo_medicamento")
    private Integer tipoMedicamento;
    @Basic(optional = false)
    @NotNull
    @Column(name = "tipo_prestacion")
    private int tipoPrestacion;
    @Size(max = 1024)
    @Column(name = "med_pbs_utilizado")
    private String medPbsUtilizado;
    @Column(name = "causa_solicitud_1")
    private Boolean causaSolicitud1;
    @Column(name = "causa_solicitud_2")
    private Boolean causaSolicitud2;
    @Column(name = "causa_solicitud_3")
    private Boolean causaSolicitud3;
    @Column(name = "causa_solicitud_4")
    private Boolean causaSolicitud4;
    @Column(name = "causa_solicitud_5")
    private Boolean causaSolicitud5;
    @Column(name = "causa_solicitud_6")
    private Boolean causaSolicitud6;
    @Basic(optional = false)
    @NotNull
    @Column(name = "razon_causa_solicitud_31")
    private boolean razonCausaSolicitud31;
    @Size(max = 256)
    @Column(name = "descripcion_razon_31")
    private String descripcionRazon31;
    @Basic(optional = false)
    @NotNull
    @Column(name = "razon_causa_solicitud_32")
    private boolean razonCausaSolicitud32;
    @Size(max = 256)
    @Column(name = "descripcion_razon_32")
    private String descripcionRazon32;
    @Basic(optional = false)
    @NotNull
    @Column(name = "razon_causa_solicitud_41")
    private boolean razonCausaSolicitud41;
    @Size(max = 256)
    @Column(name = "descripcion_razon_41")
    private String descripcionRazon41;
    @Basic(optional = false)
    @NotNull
    @Column(name = "razon_causa_solicitud_42")
    private boolean razonCausaSolicitud42;
    @Size(max = 256)
    @Column(name = "descripcion_razon_42")
    private String descripcionRazon42;
    @Basic(optional = false)
    @NotNull
    @Column(name = "razon_causa_solicitud_43")
    private boolean razonCausaSolicitud43;
    @Size(max = 256)
    @Column(name = "descripcion_razon_43")
    private String descripcionRazon43;
    @Basic(optional = false)
    @NotNull
    @Column(name = "razon_causa_solicitud_44")
    private boolean razonCausaSolicitud44;
    @Size(max = 256)
    @Column(name = "descripcion_razon_44")
    private String descripcionRazon44;
    @Column(name = "razon_causa_solicitud_51")
    private Boolean razonCausaSolicitud51;
    @Size(max = 256)
    @Column(name = "descripcion_razon_51")
    private String descripcionRazon51;
    @Column(name = "razon_causa_solicitud_52")
    private Boolean razonCausaSolicitud52;
    @Size(max = 256)
    @Column(name = "descripcion_razon_52")
    private String descripcionRazon52;
    @Column(name = "razon_causa_solicitud_53")
    private Boolean razonCausaSolicitud53;
    @Size(max = 256)
    @Column(name = "descripcion_razon_53")
    private String descripcionRazon53;
    @Column(name = "razon_causa_solicitud_54")
    private Boolean razonCausaSolicitud54;
    @Size(max = 256)
    @Column(name = "descripcion_razon_54")
    private String descripcionRazon54;
    @Column(name = "razon_causa_solicitud_5")
    private Boolean razonCausaSolicitud5;
    @Size(max = 256)
    @Column(name = "descripcion_razon_5")
    private String descripcionRazon5;
    @Size(max = 1024)
    @Column(name = "descripcion_medicamento_principio_activo")
    private String descripcionMedicamentoPrincipioActivo;
    @Size(max = 8)
    @Column(name = "codigo_formula_farmaceutica")
    private String codigoFormulaFarmaceutica;
    @Size(max = 4096)
    @Column(name = "justificacion_no_pbs")
    private String justificacionNoPbs;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "dosis")
    private BigDecimal dosis;
    @Size(max = 4)
    @Column(name = "numero_frecuencia_administracion")
    private String numeroFrecuenciaAdministracion;
    @Column(name = "codigo_frecuencia_administracion")
    private Integer codigoFrecuenciaAdministracion;
    @Column(name = "indicaciones_especiales")
    private Integer indicacionesEspeciales;
    @Column(name = "cantidad_tratamiento")
    private Integer cantidadTratamiento;
    @Column(name = "duracion_tratamiento")
    private Integer duracionTratamiento;
    @Column(name = "cantidad_total_formulada")
    private BigDecimal cantidadTotalFormulada;
    @Column(name = "result_satis_prev")
    private Boolean resultSatisPrev;
    @Column(name = "registro_invima")
    private Boolean registroInvima;
    @Column(name = "no_uso_sanitario_unirs")
    private Boolean noUsoSanitarioUnirs;
    @Size(max = 2048)
    @Column(name = "indicacion_recibida")
    private String indicacionRecibida;
    @Column(name = "es_diagnostico_vih")
    private Boolean esDiagnosticoVih;
    @Column(name = "es_diagnostico_cancer")
    private Boolean esDiagnosticoCancer;
    @Column(name = "es_diagnostico_enfermedad_renal")
    private Boolean esDiagnosticoEnfermedadRenal;
    @Column(name = "es_diagnostico_desnutricion")
    private Boolean esDiagnosticoDesnutricion;
    @Size(max = 4)
    @Column(name = "tipo_producto_nutricional")
    private String tipoProductoNutricional;
    @Size(max = 8)
    @Column(name = "descripcion_producto_nutricional")
    private String descripcionProductoNutricional;
    @Column(name = "cantidad_total_entrega")
    private BigDecimal cantidadTotalEntrega;
    @Column(name = "entregados")
    private BigDecimal entregados;
    @Column(name = "pendientes")
    private BigDecimal pendientes;
    @Column(name = "fecha_direccionamiento")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaDireccionamiento;
    @Column(name = "fecha_maxima_entrega")
    @Temporal(TemporalType.DATE)
    private Date fechaMaximaEntrega;
    @Basic(optional = false)
    @NotNull
    @Column(name = "mae_productos_nutricionales_id")
    private int maeProductosNutricionalesId;
    @Size(max = 8)
    @Column(name = "mae_productos_nutricionales_codigo")
    private String maeProductosNutricionalesCodigo;
    @Size(max = 128)
    @Column(name = "mae_productos_nutricionales_valor")
    private String maeProductosNutricionalesValor;
    @Column(name = "valor_unitario")
    private BigDecimal valorUnitario;
    @Size(max = 128)
    @Column(name = "direccionado")
    private String direccionado;
    @Column(name = "ciclo_facturacion")
    private Integer cicloFacturacion;
    @Column(name = "cod_factura_ips")
    private Integer codFacturaIps;
    @Size(max = 2048)
    @Column(name = "justificacion_tec_junta")
    private String justificacionTecJunta;
    @Size(max = 128)
    @Column(name = "mod_junta")
    private String modJunta;
    @Size(max = 48)
    @Column(name = "num_acta_junta")
    private String numActaJunta;
    @Column(name = "reacciones_adversas_paciente")
    private Boolean reaccionesAdversasPaciente;
    @Column(name = "cantidad_direccionada")
    private Integer cantidadDireccionada;
    @Column(name = "cantidad_minima_dispensada")
    private Integer cantidadMinimaDispensada;
    @Column(name = "cantidad_prescrita")
    private Integer cantidadPrescrita;
    @Size(max = 48)
    @Column(name = "codigo_mipres_entregar")
    private String codigoMipresEntregar;
    @Size(max = 48)
    @Column(name = "cod_formulada")
    private String codFormulada;
    @Column(name = "estado_auditoria")
    private Integer estadoAuditoria;
    @Column(name = "consecutivo_junta_tecnologia")
    private Integer consecutivoJuntaTecnologia;
    @Column(name = "fecha_acta_junta")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaActaJunta;
    @Column(name = "estado_prescripcion")
    private Integer estadoPrescripcion;
    @Column(name = "indicacion_unirs")
    private Boolean indicacionUnirs;
    @Size(max = 128)
    @Column(name = "forma_farmaceutica")
    private String formaFarmaceutica;
    @Column(name = "consec_orden_princ_activo")
    private Integer consecOrdenPrincActivo;
    @Column(name = "en_fase_experimental")
    private Boolean enFaseExperimental;
    @Size(max = 128)
    @Column(name = "cantidad_med_principio_activo")
    private String cantidadMedPrincipioActivo;
    @Column(name = "desc_med_pbs_upc")
    private Boolean descMedPbsUpc;
    @Column(name = "descarto_no_existe_pbs")
    private Boolean descartoNoExistePbs;
    @Column(name = "lista_no_uso_sanitario_unirs")
    private Boolean listaNoUsoSanitarioUnirs;
    @Column(name = "existente_pbs_cargo_upc")
    private Boolean existentePbsCargoUpc;
    @Column(name = "financ_pbs_cargo_upc")
    private Boolean financPbsCargoUpc;
    @Column(name = "utilizo_nutri_med_existente_pbs_upc")
    private Boolean utilizoNutriMedExistentePbsUpc;
    @Column(name = "evidencia_efi_efec_clinica")
    private Boolean evidenciaEfiEfecClinica;
    @Column(name = "existe_pbs_cargo_upc")
    private Boolean existePbsCargoUpc;
    @Column(name = "obs_reacc_pbs_cargo_upc")
    private Boolean obsReaccPbsCargoUpc;
    @Column(name = "descarto_evidencia_efi_efec_clinica")
    private Boolean descartoEvidenciaEfiEfecClinica;
    @Column(name = "reg_aprob_aut_clin")
    private Boolean regAprobAutClin;
    @Column(name = "descarto_alternativa")
    private Boolean descartoAlternativa;
    @Basic(optional = false)
    @NotNull
    @Column(name = "atendido")
    private boolean atendido;
    @Column(name = "tipo_tutela")
    private Integer tipoTutela;
    @Basic(optional = false)
    @NotNull
    @Column(name = "bandera_atencion")
    private boolean banderaAtencion;
    @Column(name = "descarto_reacciones_adversas")
    private Boolean descartoReaccionesAdversas;
    @Column(name = "cubierto_pbs_cargo_upc")
    private Boolean cubiertoPbsCargoUpc;
    @Column(name = "pbs_utilizado")
    private Boolean pbsUtilizado;
    @Size(max = 8)
    @Column(name = "codigo_via_administracion")
    private String codigoViaAdministracion;
    @Size(max = 8)
    @Column(name = "dosis_unidad_medida")
    private String dosisUnidadMedida;
    @Size(max = 8)
    @Column(name = "unidad_farmaceutica_cantidad_total")
    private String unidadFarmaceuticaCantidadTotal;
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
    @Size(max = 128)
    @Column(name = "usuario_atiende")
    private String usuarioAtiende;
    @Size(max = 16)
    @Column(name = "terminal_atiende")
    private String terminalAtiende;
    @Column(name = "fecha_hora_atiende")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraAtiende;
    @OneToMany(mappedBy = "mpPrescripcionMedicamentosId", fetch = FetchType.LAZY)
    private List<MpProgramadaEntregas> mpProgramadaEntregasList;
    @OneToMany(mappedBy = "mpPrescripcionMedicamentosId", fetch = FetchType.LAZY)
    private List<MpNoDireccionados> mpNoDireccionadosList;
    @OneToMany(mappedBy = "mpPrescripcionMedicamentoId", fetch = FetchType.LAZY)
    private List<MpPrescripcionItemAuditoria> mpPrescripcionItemAuditoriaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mpPrescripcionMedicamentosId", fetch = FetchType.LAZY)
    private List<MpMedicamentoIndicacionesUnirs> mpMedicamentoIndicacionesUnirsList;
    @OneToMany(mappedBy = "mpPrescripcionMedicamentoId", fetch = FetchType.LAZY)
    private List<MpNotificacionesHistoricos> mpNotificacionesHistoricosList;
    @OneToMany(mappedBy = "mpPrescripcionMedicamentosId", fetch = FetchType.LAZY)
    private List<MpPrescripcionProgramadas> mpPrescripcionProgramadasList;
    @OneToMany(mappedBy = "mpPrescripcionMedicamentosId", fetch = FetchType.LAZY)
    private List<MpCotizaciones> mpCotizacionesList;
    @JoinColumn(name = "mp_prescripciones_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private MpPrescripciones mpPrescripcionesId;
    @OneToMany(mappedBy = "mpPrescripcionMedicamentosId", fetch = FetchType.LAZY)
    private List<MpDireccionamientos> mpDireccionamientosList;
    @OneToMany(mappedBy = "mpPrescripicionMedicamentosId", fetch = FetchType.LAZY)
    private List<MpDireccionamientoEntregados> mpDireccionamientoEntregadosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mpPrescripcionMedicamentosId", fetch = FetchType.LAZY)
    private List<MpMedicamentoPrincipiosActivos> mpMedicamentoPrincipiosActivosList;

    public MpPrescripcionMedicamentos() {
    }

    public MpPrescripcionMedicamentos(Integer id) {
        this.id = id;
    }

    public MpPrescripcionMedicamentos(Integer id, int estado, int estadoJuntaProfesionales, int consecutivoOrden, int tipoTecnologia, int tipoPrestacion, boolean razonCausaSolicitud31, boolean razonCausaSolicitud32, boolean razonCausaSolicitud41, boolean razonCausaSolicitud42, boolean razonCausaSolicitud43, boolean razonCausaSolicitud44, int maeProductosNutricionalesId, boolean atendido, boolean banderaAtencion, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.estado = estado;
        this.estadoJuntaProfesionales = estadoJuntaProfesionales;
        this.consecutivoOrden = consecutivoOrden;
        this.tipoTecnologia = tipoTecnologia;
        this.tipoPrestacion = tipoPrestacion;
        this.razonCausaSolicitud31 = razonCausaSolicitud31;
        this.razonCausaSolicitud32 = razonCausaSolicitud32;
        this.razonCausaSolicitud41 = razonCausaSolicitud41;
        this.razonCausaSolicitud42 = razonCausaSolicitud42;
        this.razonCausaSolicitud43 = razonCausaSolicitud43;
        this.razonCausaSolicitud44 = razonCausaSolicitud44;
        this.maeProductosNutricionalesId = maeProductosNutricionalesId;
        this.atendido = atendido;
        this.banderaAtencion = banderaAtencion;
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

    public Integer getIdTransaccion() {
        return idTransaccion;
    }

    public void setIdTransaccion(Integer idTransaccion) {
        this.idTransaccion = idTransaccion;
    }

    public String getIdDireccionamiento() {
        return idDireccionamiento;
    }

    public void setIdDireccionamiento(String idDireccionamiento) {
        this.idDireccionamiento = idDireccionamiento;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public int getEstadoJuntaProfesionales() {
        return estadoJuntaProfesionales;
    }

    public void setEstadoJuntaProfesionales(int estadoJuntaProfesionales) {
        this.estadoJuntaProfesionales = estadoJuntaProfesionales;
    }

    public int getConsecutivoOrden() {
        return consecutivoOrden;
    }

    public void setConsecutivoOrden(int consecutivoOrden) {
        this.consecutivoOrden = consecutivoOrden;
    }

    public int getTipoTecnologia() {
        return tipoTecnologia;
    }

    public void setTipoTecnologia(int tipoTecnologia) {
        this.tipoTecnologia = tipoTecnologia;
    }

    public Integer getTipoMedicamento() {
        return tipoMedicamento;
    }

    public void setTipoMedicamento(Integer tipoMedicamento) {
        this.tipoMedicamento = tipoMedicamento;
    }

    public int getTipoPrestacion() {
        return tipoPrestacion;
    }

    public void setTipoPrestacion(int tipoPrestacion) {
        this.tipoPrestacion = tipoPrestacion;
    }

    public String getMedPbsUtilizado() {
        return medPbsUtilizado;
    }

    public void setMedPbsUtilizado(String medPbsUtilizado) {
        this.medPbsUtilizado = medPbsUtilizado;
    }

    public Boolean getCausaSolicitud1() {
        return causaSolicitud1;
    }

    public void setCausaSolicitud1(Boolean causaSolicitud1) {
        this.causaSolicitud1 = causaSolicitud1;
    }

    public Boolean getCausaSolicitud2() {
        return causaSolicitud2;
    }

    public void setCausaSolicitud2(Boolean causaSolicitud2) {
        this.causaSolicitud2 = causaSolicitud2;
    }

    public Boolean getCausaSolicitud3() {
        return causaSolicitud3;
    }

    public void setCausaSolicitud3(Boolean causaSolicitud3) {
        this.causaSolicitud3 = causaSolicitud3;
    }

    public Boolean getCausaSolicitud4() {
        return causaSolicitud4;
    }

    public void setCausaSolicitud4(Boolean causaSolicitud4) {
        this.causaSolicitud4 = causaSolicitud4;
    }

    public Boolean getCausaSolicitud5() {
        return causaSolicitud5;
    }

    public void setCausaSolicitud5(Boolean causaSolicitud5) {
        this.causaSolicitud5 = causaSolicitud5;
    }

    public Boolean getCausaSolicitud6() {
        return causaSolicitud6;
    }

    public void setCausaSolicitud6(Boolean causaSolicitud6) {
        this.causaSolicitud6 = causaSolicitud6;
    }

    public boolean getRazonCausaSolicitud31() {
        return razonCausaSolicitud31;
    }

    public void setRazonCausaSolicitud31(boolean razonCausaSolicitud31) {
        this.razonCausaSolicitud31 = razonCausaSolicitud31;
    }

    public String getDescripcionRazon31() {
        return descripcionRazon31;
    }

    public void setDescripcionRazon31(String descripcionRazon31) {
        this.descripcionRazon31 = descripcionRazon31;
    }

    public boolean getRazonCausaSolicitud32() {
        return razonCausaSolicitud32;
    }

    public void setRazonCausaSolicitud32(boolean razonCausaSolicitud32) {
        this.razonCausaSolicitud32 = razonCausaSolicitud32;
    }

    public String getDescripcionRazon32() {
        return descripcionRazon32;
    }

    public void setDescripcionRazon32(String descripcionRazon32) {
        this.descripcionRazon32 = descripcionRazon32;
    }

    public boolean getRazonCausaSolicitud41() {
        return razonCausaSolicitud41;
    }

    public void setRazonCausaSolicitud41(boolean razonCausaSolicitud41) {
        this.razonCausaSolicitud41 = razonCausaSolicitud41;
    }

    public String getDescripcionRazon41() {
        return descripcionRazon41;
    }

    public void setDescripcionRazon41(String descripcionRazon41) {
        this.descripcionRazon41 = descripcionRazon41;
    }

    public boolean getRazonCausaSolicitud42() {
        return razonCausaSolicitud42;
    }

    public void setRazonCausaSolicitud42(boolean razonCausaSolicitud42) {
        this.razonCausaSolicitud42 = razonCausaSolicitud42;
    }

    public String getDescripcionRazon42() {
        return descripcionRazon42;
    }

    public void setDescripcionRazon42(String descripcionRazon42) {
        this.descripcionRazon42 = descripcionRazon42;
    }

    public boolean getRazonCausaSolicitud43() {
        return razonCausaSolicitud43;
    }

    public void setRazonCausaSolicitud43(boolean razonCausaSolicitud43) {
        this.razonCausaSolicitud43 = razonCausaSolicitud43;
    }

    public String getDescripcionRazon43() {
        return descripcionRazon43;
    }

    public void setDescripcionRazon43(String descripcionRazon43) {
        this.descripcionRazon43 = descripcionRazon43;
    }

    public boolean getRazonCausaSolicitud44() {
        return razonCausaSolicitud44;
    }

    public void setRazonCausaSolicitud44(boolean razonCausaSolicitud44) {
        this.razonCausaSolicitud44 = razonCausaSolicitud44;
    }

    public String getDescripcionRazon44() {
        return descripcionRazon44;
    }

    public void setDescripcionRazon44(String descripcionRazon44) {
        this.descripcionRazon44 = descripcionRazon44;
    }

    public Boolean getRazonCausaSolicitud51() {
        return razonCausaSolicitud51;
    }

    public void setRazonCausaSolicitud51(Boolean razonCausaSolicitud51) {
        this.razonCausaSolicitud51 = razonCausaSolicitud51;
    }

    public String getDescripcionRazon51() {
        return descripcionRazon51;
    }

    public void setDescripcionRazon51(String descripcionRazon51) {
        this.descripcionRazon51 = descripcionRazon51;
    }

    public Boolean getRazonCausaSolicitud52() {
        return razonCausaSolicitud52;
    }

    public void setRazonCausaSolicitud52(Boolean razonCausaSolicitud52) {
        this.razonCausaSolicitud52 = razonCausaSolicitud52;
    }

    public String getDescripcionRazon52() {
        return descripcionRazon52;
    }

    public void setDescripcionRazon52(String descripcionRazon52) {
        this.descripcionRazon52 = descripcionRazon52;
    }

    public Boolean getRazonCausaSolicitud53() {
        return razonCausaSolicitud53;
    }

    public void setRazonCausaSolicitud53(Boolean razonCausaSolicitud53) {
        this.razonCausaSolicitud53 = razonCausaSolicitud53;
    }

    public String getDescripcionRazon53() {
        return descripcionRazon53;
    }

    public void setDescripcionRazon53(String descripcionRazon53) {
        this.descripcionRazon53 = descripcionRazon53;
    }

    public Boolean getRazonCausaSolicitud54() {
        return razonCausaSolicitud54;
    }

    public void setRazonCausaSolicitud54(Boolean razonCausaSolicitud54) {
        this.razonCausaSolicitud54 = razonCausaSolicitud54;
    }

    public String getDescripcionRazon54() {
        return descripcionRazon54;
    }

    public void setDescripcionRazon54(String descripcionRazon54) {
        this.descripcionRazon54 = descripcionRazon54;
    }

    public Boolean getRazonCausaSolicitud5() {
        return razonCausaSolicitud5;
    }

    public void setRazonCausaSolicitud5(Boolean razonCausaSolicitud5) {
        this.razonCausaSolicitud5 = razonCausaSolicitud5;
    }

    public String getDescripcionRazon5() {
        return descripcionRazon5;
    }

    public void setDescripcionRazon5(String descripcionRazon5) {
        this.descripcionRazon5 = descripcionRazon5;
    }

    public String getDescripcionMedicamentoPrincipioActivo() {
        return descripcionMedicamentoPrincipioActivo;
    }

    public void setDescripcionMedicamentoPrincipioActivo(String descripcionMedicamentoPrincipioActivo) {
        this.descripcionMedicamentoPrincipioActivo = descripcionMedicamentoPrincipioActivo;
    }

    public String getCodigoFormulaFarmaceutica() {
        return codigoFormulaFarmaceutica;
    }

    public void setCodigoFormulaFarmaceutica(String codigoFormulaFarmaceutica) {
        this.codigoFormulaFarmaceutica = codigoFormulaFarmaceutica;
    }

    public String getJustificacionNoPbs() {
        return justificacionNoPbs;
    }

    public void setJustificacionNoPbs(String justificacionNoPbs) {
        this.justificacionNoPbs = justificacionNoPbs;
    }

    public BigDecimal getDosis() {
        return dosis;
    }

    public void setDosis(BigDecimal dosis) {
        this.dosis = dosis;
    }

    public String getNumeroFrecuenciaAdministracion() {
        return numeroFrecuenciaAdministracion;
    }

    public void setNumeroFrecuenciaAdministracion(String numeroFrecuenciaAdministracion) {
        this.numeroFrecuenciaAdministracion = numeroFrecuenciaAdministracion;
    }

    public Integer getCodigoFrecuenciaAdministracion() {
        return codigoFrecuenciaAdministracion;
    }

    public void setCodigoFrecuenciaAdministracion(Integer codigoFrecuenciaAdministracion) {
        this.codigoFrecuenciaAdministracion = codigoFrecuenciaAdministracion;
    }

    public Integer getIndicacionesEspeciales() {
        return indicacionesEspeciales;
    }

    public void setIndicacionesEspeciales(Integer indicacionesEspeciales) {
        this.indicacionesEspeciales = indicacionesEspeciales;
    }

    public Integer getCantidadTratamiento() {
        return cantidadTratamiento;
    }

    public void setCantidadTratamiento(Integer cantidadTratamiento) {
        this.cantidadTratamiento = cantidadTratamiento;
    }

    public Integer getDuracionTratamiento() {
        return duracionTratamiento;
    }

    public void setDuracionTratamiento(Integer duracionTratamiento) {
        this.duracionTratamiento = duracionTratamiento;
    }

    public BigDecimal getCantidadTotalFormulada() {
        return cantidadTotalFormulada;
    }

    public void setCantidadTotalFormulada(BigDecimal cantidadTotalFormulada) {
        this.cantidadTotalFormulada = cantidadTotalFormulada;
    }

    public Boolean getResultSatisPrev() {
        return resultSatisPrev;
    }

    public void setResultSatisPrev(Boolean resultSatisPrev) {
        this.resultSatisPrev = resultSatisPrev;
    }

    public Boolean getRegistroInvima() {
        return registroInvima;
    }

    public void setRegistroInvima(Boolean registroInvima) {
        this.registroInvima = registroInvima;
    }

    public Boolean getNoUsoSanitarioUnirs() {
        return noUsoSanitarioUnirs;
    }

    public void setNoUsoSanitarioUnirs(Boolean noUsoSanitarioUnirs) {
        this.noUsoSanitarioUnirs = noUsoSanitarioUnirs;
    }

    public String getIndicacionRecibida() {
        return indicacionRecibida;
    }

    public void setIndicacionRecibida(String indicacionRecibida) {
        this.indicacionRecibida = indicacionRecibida;
    }

    public Boolean getEsDiagnosticoVih() {
        return esDiagnosticoVih;
    }

    public void setEsDiagnosticoVih(Boolean esDiagnosticoVih) {
        this.esDiagnosticoVih = esDiagnosticoVih;
    }

    public Boolean getEsDiagnosticoCancer() {
        return esDiagnosticoCancer;
    }

    public void setEsDiagnosticoCancer(Boolean esDiagnosticoCancer) {
        this.esDiagnosticoCancer = esDiagnosticoCancer;
    }

    public Boolean getEsDiagnosticoEnfermedadRenal() {
        return esDiagnosticoEnfermedadRenal;
    }

    public void setEsDiagnosticoEnfermedadRenal(Boolean esDiagnosticoEnfermedadRenal) {
        this.esDiagnosticoEnfermedadRenal = esDiagnosticoEnfermedadRenal;
    }

    public Boolean getEsDiagnosticoDesnutricion() {
        return esDiagnosticoDesnutricion;
    }

    public void setEsDiagnosticoDesnutricion(Boolean esDiagnosticoDesnutricion) {
        this.esDiagnosticoDesnutricion = esDiagnosticoDesnutricion;
    }

    public String getTipoProductoNutricional() {
        return tipoProductoNutricional;
    }

    public void setTipoProductoNutricional(String tipoProductoNutricional) {
        this.tipoProductoNutricional = tipoProductoNutricional;
    }

    public String getDescripcionProductoNutricional() {
        return descripcionProductoNutricional;
    }

    public void setDescripcionProductoNutricional(String descripcionProductoNutricional) {
        this.descripcionProductoNutricional = descripcionProductoNutricional;
    }

    public BigDecimal getCantidadTotalEntrega() {
        return cantidadTotalEntrega;
    }

    public void setCantidadTotalEntrega(BigDecimal cantidadTotalEntrega) {
        this.cantidadTotalEntrega = cantidadTotalEntrega;
    }

    public BigDecimal getEntregados() {
        return entregados;
    }

    public void setEntregados(BigDecimal entregados) {
        this.entregados = entregados;
    }

    public BigDecimal getPendientes() {
        return pendientes;
    }

    public void setPendientes(BigDecimal pendientes) {
        this.pendientes = pendientes;
    }

    public Date getFechaDireccionamiento() {
        return fechaDireccionamiento;
    }

    public void setFechaDireccionamiento(Date fechaDireccionamiento) {
        this.fechaDireccionamiento = fechaDireccionamiento;
    }

    public Date getFechaMaximaEntrega() {
        return fechaMaximaEntrega;
    }

    public void setFechaMaximaEntrega(Date fechaMaximaEntrega) {
        this.fechaMaximaEntrega = fechaMaximaEntrega;
    }

    public int getMaeProductosNutricionalesId() {
        return maeProductosNutricionalesId;
    }

    public void setMaeProductosNutricionalesId(int maeProductosNutricionalesId) {
        this.maeProductosNutricionalesId = maeProductosNutricionalesId;
    }

    public String getMaeProductosNutricionalesCodigo() {
        return maeProductosNutricionalesCodigo;
    }

    public void setMaeProductosNutricionalesCodigo(String maeProductosNutricionalesCodigo) {
        this.maeProductosNutricionalesCodigo = maeProductosNutricionalesCodigo;
    }

    public String getMaeProductosNutricionalesValor() {
        return maeProductosNutricionalesValor;
    }

    public void setMaeProductosNutricionalesValor(String maeProductosNutricionalesValor) {
        this.maeProductosNutricionalesValor = maeProductosNutricionalesValor;
    }

    public BigDecimal getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(BigDecimal valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public String getDireccionado() {
        return direccionado;
    }

    public void setDireccionado(String direccionado) {
        this.direccionado = direccionado;
    }

    public Integer getCicloFacturacion() {
        return cicloFacturacion;
    }

    public void setCicloFacturacion(Integer cicloFacturacion) {
        this.cicloFacturacion = cicloFacturacion;
    }

    public Integer getCodFacturaIps() {
        return codFacturaIps;
    }

    public void setCodFacturaIps(Integer codFacturaIps) {
        this.codFacturaIps = codFacturaIps;
    }

    public String getJustificacionTecJunta() {
        return justificacionTecJunta;
    }

    public void setJustificacionTecJunta(String justificacionTecJunta) {
        this.justificacionTecJunta = justificacionTecJunta;
    }

    public String getModJunta() {
        return modJunta;
    }

    public void setModJunta(String modJunta) {
        this.modJunta = modJunta;
    }

    public String getNumActaJunta() {
        return numActaJunta;
    }

    public void setNumActaJunta(String numActaJunta) {
        this.numActaJunta = numActaJunta;
    }

    public Boolean getReaccionesAdversasPaciente() {
        return reaccionesAdversasPaciente;
    }

    public void setReaccionesAdversasPaciente(Boolean reaccionesAdversasPaciente) {
        this.reaccionesAdversasPaciente = reaccionesAdversasPaciente;
    }

    public Integer getCantidadDireccionada() {
        return cantidadDireccionada;
    }

    public void setCantidadDireccionada(Integer cantidadDireccionada) {
        this.cantidadDireccionada = cantidadDireccionada;
    }

    public Integer getCantidadMinimaDispensada() {
        return cantidadMinimaDispensada;
    }

    public void setCantidadMinimaDispensada(Integer cantidadMinimaDispensada) {
        this.cantidadMinimaDispensada = cantidadMinimaDispensada;
    }

    public Integer getCantidadPrescrita() {
        return cantidadPrescrita;
    }

    public void setCantidadPrescrita(Integer cantidadPrescrita) {
        this.cantidadPrescrita = cantidadPrescrita;
    }

    public String getCodigoMipresEntregar() {
        return codigoMipresEntregar;
    }

    public void setCodigoMipresEntregar(String codigoMipresEntregar) {
        this.codigoMipresEntregar = codigoMipresEntregar;
    }

    public String getCodFormulada() {
        return codFormulada;
    }

    public void setCodFormulada(String codFormulada) {
        this.codFormulada = codFormulada;
    }

    public Integer getEstadoAuditoria() {
        return estadoAuditoria;
    }

    public void setEstadoAuditoria(Integer estadoAuditoria) {
        this.estadoAuditoria = estadoAuditoria;
    }

    public Integer getConsecutivoJuntaTecnologia() {
        return consecutivoJuntaTecnologia;
    }

    public void setConsecutivoJuntaTecnologia(Integer consecutivoJuntaTecnologia) {
        this.consecutivoJuntaTecnologia = consecutivoJuntaTecnologia;
    }

    public Date getFechaActaJunta() {
        return fechaActaJunta;
    }

    public void setFechaActaJunta(Date fechaActaJunta) {
        this.fechaActaJunta = fechaActaJunta;
    }

    public Integer getEstadoPrescripcion() {
        return estadoPrescripcion;
    }

    public void setEstadoPrescripcion(Integer estadoPrescripcion) {
        this.estadoPrescripcion = estadoPrescripcion;
    }

    public Boolean getIndicacionUnirs() {
        return indicacionUnirs;
    }

    public void setIndicacionUnirs(Boolean indicacionUnirs) {
        this.indicacionUnirs = indicacionUnirs;
    }

    public String getFormaFarmaceutica() {
        return formaFarmaceutica;
    }

    public void setFormaFarmaceutica(String formaFarmaceutica) {
        this.formaFarmaceutica = formaFarmaceutica;
    }

    public Integer getConsecOrdenPrincActivo() {
        return consecOrdenPrincActivo;
    }

    public void setConsecOrdenPrincActivo(Integer consecOrdenPrincActivo) {
        this.consecOrdenPrincActivo = consecOrdenPrincActivo;
    }

    public Boolean getEnFaseExperimental() {
        return enFaseExperimental;
    }

    public void setEnFaseExperimental(Boolean enFaseExperimental) {
        this.enFaseExperimental = enFaseExperimental;
    }

    public String getCantidadMedPrincipioActivo() {
        return cantidadMedPrincipioActivo;
    }

    public void setCantidadMedPrincipioActivo(String cantidadMedPrincipioActivo) {
        this.cantidadMedPrincipioActivo = cantidadMedPrincipioActivo;
    }

    public Boolean getDescMedPbsUpc() {
        return descMedPbsUpc;
    }

    public void setDescMedPbsUpc(Boolean descMedPbsUpc) {
        this.descMedPbsUpc = descMedPbsUpc;
    }

    public Boolean getDescartoNoExistePbs() {
        return descartoNoExistePbs;
    }

    public void setDescartoNoExistePbs(Boolean descartoNoExistePbs) {
        this.descartoNoExistePbs = descartoNoExistePbs;
    }

    public Boolean getListaNoUsoSanitarioUnirs() {
        return listaNoUsoSanitarioUnirs;
    }

    public void setListaNoUsoSanitarioUnirs(Boolean listaNoUsoSanitarioUnirs) {
        this.listaNoUsoSanitarioUnirs = listaNoUsoSanitarioUnirs;
    }

    public Boolean getExistentePbsCargoUpc() {
        return existentePbsCargoUpc;
    }

    public void setExistentePbsCargoUpc(Boolean existentePbsCargoUpc) {
        this.existentePbsCargoUpc = existentePbsCargoUpc;
    }

    public Boolean getFinancPbsCargoUpc() {
        return financPbsCargoUpc;
    }

    public void setFinancPbsCargoUpc(Boolean financPbsCargoUpc) {
        this.financPbsCargoUpc = financPbsCargoUpc;
    }

    public Boolean getUtilizoNutriMedExistentePbsUpc() {
        return utilizoNutriMedExistentePbsUpc;
    }

    public void setUtilizoNutriMedExistentePbsUpc(Boolean utilizoNutriMedExistentePbsUpc) {
        this.utilizoNutriMedExistentePbsUpc = utilizoNutriMedExistentePbsUpc;
    }

    public Boolean getEvidenciaEfiEfecClinica() {
        return evidenciaEfiEfecClinica;
    }

    public void setEvidenciaEfiEfecClinica(Boolean evidenciaEfiEfecClinica) {
        this.evidenciaEfiEfecClinica = evidenciaEfiEfecClinica;
    }

    public Boolean getExistePbsCargoUpc() {
        return existePbsCargoUpc;
    }

    public void setExistePbsCargoUpc(Boolean existePbsCargoUpc) {
        this.existePbsCargoUpc = existePbsCargoUpc;
    }

    public Boolean getObsReaccPbsCargoUpc() {
        return obsReaccPbsCargoUpc;
    }

    public void setObsReaccPbsCargoUpc(Boolean obsReaccPbsCargoUpc) {
        this.obsReaccPbsCargoUpc = obsReaccPbsCargoUpc;
    }

    public Boolean getDescartoEvidenciaEfiEfecClinica() {
        return descartoEvidenciaEfiEfecClinica;
    }

    public void setDescartoEvidenciaEfiEfecClinica(Boolean descartoEvidenciaEfiEfecClinica) {
        this.descartoEvidenciaEfiEfecClinica = descartoEvidenciaEfiEfecClinica;
    }

    public Boolean getRegAprobAutClin() {
        return regAprobAutClin;
    }

    public void setRegAprobAutClin(Boolean regAprobAutClin) {
        this.regAprobAutClin = regAprobAutClin;
    }

    public Boolean getDescartoAlternativa() {
        return descartoAlternativa;
    }

    public void setDescartoAlternativa(Boolean descartoAlternativa) {
        this.descartoAlternativa = descartoAlternativa;
    }

    public boolean getAtendido() {
        return atendido;
    }

    public void setAtendido(boolean atendido) {
        this.atendido = atendido;
    }

    public Integer getTipoTutela() {
        return tipoTutela;
    }

    public void setTipoTutela(Integer tipoTutela) {
        this.tipoTutela = tipoTutela;
    }

    public boolean getBanderaAtencion() {
        return banderaAtencion;
    }

    public void setBanderaAtencion(boolean banderaAtencion) {
        this.banderaAtencion = banderaAtencion;
    }

    public Boolean getDescartoReaccionesAdversas() {
        return descartoReaccionesAdversas;
    }

    public void setDescartoReaccionesAdversas(Boolean descartoReaccionesAdversas) {
        this.descartoReaccionesAdversas = descartoReaccionesAdversas;
    }

    public Boolean getCubiertoPbsCargoUpc() {
        return cubiertoPbsCargoUpc;
    }

    public void setCubiertoPbsCargoUpc(Boolean cubiertoPbsCargoUpc) {
        this.cubiertoPbsCargoUpc = cubiertoPbsCargoUpc;
    }

    public Boolean getPbsUtilizado() {
        return pbsUtilizado;
    }

    public void setPbsUtilizado(Boolean pbsUtilizado) {
        this.pbsUtilizado = pbsUtilizado;
    }

    public String getCodigoViaAdministracion() {
        return codigoViaAdministracion;
    }

    public void setCodigoViaAdministracion(String codigoViaAdministracion) {
        this.codigoViaAdministracion = codigoViaAdministracion;
    }

    public String getDosisUnidadMedida() {
        return dosisUnidadMedida;
    }

    public void setDosisUnidadMedida(String dosisUnidadMedida) {
        this.dosisUnidadMedida = dosisUnidadMedida;
    }

    public String getUnidadFarmaceuticaCantidadTotal() {
        return unidadFarmaceuticaCantidadTotal;
    }

    public void setUnidadFarmaceuticaCantidadTotal(String unidadFarmaceuticaCantidadTotal) {
        this.unidadFarmaceuticaCantidadTotal = unidadFarmaceuticaCantidadTotal;
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

    public String getUsuarioAtiende() {
        return usuarioAtiende;
    }

    public void setUsuarioAtiende(String usuarioAtiende) {
        this.usuarioAtiende = usuarioAtiende;
    }

    public String getTerminalAtiende() {
        return terminalAtiende;
    }

    public void setTerminalAtiende(String terminalAtiende) {
        this.terminalAtiende = terminalAtiende;
    }

    public Date getFechaHoraAtiende() {
        return fechaHoraAtiende;
    }

    public void setFechaHoraAtiende(Date fechaHoraAtiende) {
        this.fechaHoraAtiende = fechaHoraAtiende;
    }

    @XmlTransient
    public List<MpProgramadaEntregas> getMpProgramadaEntregasList() {
        return mpProgramadaEntregasList;
    }

    public void setMpProgramadaEntregasList(List<MpProgramadaEntregas> mpProgramadaEntregasList) {
        this.mpProgramadaEntregasList = mpProgramadaEntregasList;
    }

    @XmlTransient
    public List<MpNoDireccionados> getMpNoDireccionadosList() {
        return mpNoDireccionadosList;
    }

    public void setMpNoDireccionadosList(List<MpNoDireccionados> mpNoDireccionadosList) {
        this.mpNoDireccionadosList = mpNoDireccionadosList;
    }

    @XmlTransient
    public List<MpPrescripcionItemAuditoria> getMpPrescripcionItemAuditoriaList() {
        return mpPrescripcionItemAuditoriaList;
    }

    public void setMpPrescripcionItemAuditoriaList(List<MpPrescripcionItemAuditoria> mpPrescripcionItemAuditoriaList) {
        this.mpPrescripcionItemAuditoriaList = mpPrescripcionItemAuditoriaList;
    }

    @XmlTransient
    public List<MpMedicamentoIndicacionesUnirs> getMpMedicamentoIndicacionesUnirsList() {
        return mpMedicamentoIndicacionesUnirsList;
    }

    public void setMpMedicamentoIndicacionesUnirsList(List<MpMedicamentoIndicacionesUnirs> mpMedicamentoIndicacionesUnirsList) {
        this.mpMedicamentoIndicacionesUnirsList = mpMedicamentoIndicacionesUnirsList;
    }

    @XmlTransient
    public List<MpNotificacionesHistoricos> getMpNotificacionesHistoricosList() {
        return mpNotificacionesHistoricosList;
    }

    public void setMpNotificacionesHistoricosList(List<MpNotificacionesHistoricos> mpNotificacionesHistoricosList) {
        this.mpNotificacionesHistoricosList = mpNotificacionesHistoricosList;
    }

    @XmlTransient
    public List<MpPrescripcionProgramadas> getMpPrescripcionProgramadasList() {
        return mpPrescripcionProgramadasList;
    }

    public void setMpPrescripcionProgramadasList(List<MpPrescripcionProgramadas> mpPrescripcionProgramadasList) {
        this.mpPrescripcionProgramadasList = mpPrescripcionProgramadasList;
    }

    @XmlTransient
    public List<MpCotizaciones> getMpCotizacionesList() {
        return mpCotizacionesList;
    }

    public void setMpCotizacionesList(List<MpCotizaciones> mpCotizacionesList) {
        this.mpCotizacionesList = mpCotizacionesList;
    }

    public MpPrescripciones getMpPrescripcionesId() {
        return mpPrescripcionesId;
    }

    public void setMpPrescripcionesId(MpPrescripciones mpPrescripcionesId) {
        this.mpPrescripcionesId = mpPrescripcionesId;
    }

    @XmlTransient
    public List<MpDireccionamientos> getMpDireccionamientosList() {
        return mpDireccionamientosList;
    }

    public void setMpDireccionamientosList(List<MpDireccionamientos> mpDireccionamientosList) {
        this.mpDireccionamientosList = mpDireccionamientosList;
    }

    @XmlTransient
    public List<MpDireccionamientoEntregados> getMpDireccionamientoEntregadosList() {
        return mpDireccionamientoEntregadosList;
    }

    public void setMpDireccionamientoEntregadosList(List<MpDireccionamientoEntregados> mpDireccionamientoEntregadosList) {
        this.mpDireccionamientoEntregadosList = mpDireccionamientoEntregadosList;
    }

    @XmlTransient
    public List<MpMedicamentoPrincipiosActivos> getMpMedicamentoPrincipiosActivosList() {
        return mpMedicamentoPrincipiosActivosList;
    }

    public void setMpMedicamentoPrincipiosActivosList(List<MpMedicamentoPrincipiosActivos> mpMedicamentoPrincipiosActivosList) {
        this.mpMedicamentoPrincipiosActivosList = mpMedicamentoPrincipiosActivosList;
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
        if (!(object instanceof MpPrescripcionMedicamentos)) {
            return false;
        }
        MpPrescripcionMedicamentos other = (MpPrescripcionMedicamentos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.MpPrescripcionMedicamentos[ id=" + id + " ]";
    }
    
}
