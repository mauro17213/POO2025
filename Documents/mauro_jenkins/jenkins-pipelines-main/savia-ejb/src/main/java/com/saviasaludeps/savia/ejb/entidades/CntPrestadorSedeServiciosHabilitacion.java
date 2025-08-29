/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.saviasaludeps.savia.ejb.entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author stive
 */
@Entity
@Table(name = "cnt_prestador_sede_servicios_habilitacion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CntPrestadorSedeServiciosHabilitacion.findAll", query = "SELECT c FROM CntPrestadorSedeServiciosHabilitacion c"),
    @NamedQuery(name = "CntPrestadorSedeServiciosHabilitacion.findById", query = "SELECT c FROM CntPrestadorSedeServiciosHabilitacion c WHERE c.id = :id"),
    @NamedQuery(name = "CntPrestadorSedeServiciosHabilitacion.findByMaServiciosHabilitacionCodigo", query = "SELECT c FROM CntPrestadorSedeServiciosHabilitacion c WHERE c.maServiciosHabilitacionCodigo = :maServiciosHabilitacionCodigo"),
    @NamedQuery(name = "CntPrestadorSedeServiciosHabilitacion.findByMaServiciosHabilitacionValor", query = "SELECT c FROM CntPrestadorSedeServiciosHabilitacion c WHERE c.maServiciosHabilitacionValor = :maServiciosHabilitacionValor"),
    @NamedQuery(name = "CntPrestadorSedeServiciosHabilitacion.findByActivo", query = "SELECT c FROM CntPrestadorSedeServiciosHabilitacion c WHERE c.activo = :activo"),
    @NamedQuery(name = "CntPrestadorSedeServiciosHabilitacion.findByAplicaUrgencia", query = "SELECT c FROM CntPrestadorSedeServiciosHabilitacion c WHERE c.aplicaUrgencia = :aplicaUrgencia"),
    @NamedQuery(name = "CntPrestadorSedeServiciosHabilitacion.findByAplicaDomiciliaria", query = "SELECT c FROM CntPrestadorSedeServiciosHabilitacion c WHERE c.aplicaDomiciliaria = :aplicaDomiciliaria"),
    @NamedQuery(name = "CntPrestadorSedeServiciosHabilitacion.findByAplicaCirugia", query = "SELECT c FROM CntPrestadorSedeServiciosHabilitacion c WHERE c.aplicaCirugia = :aplicaCirugia"),
    @NamedQuery(name = "CntPrestadorSedeServiciosHabilitacion.findByAplicaHospitalizacion", query = "SELECT c FROM CntPrestadorSedeServiciosHabilitacion c WHERE c.aplicaHospitalizacion = :aplicaHospitalizacion"),
    @NamedQuery(name = "CntPrestadorSedeServiciosHabilitacion.findByAplicaLaboratorio", query = "SELECT c FROM CntPrestadorSedeServiciosHabilitacion c WHERE c.aplicaLaboratorio = :aplicaLaboratorio"),
    @NamedQuery(name = "CntPrestadorSedeServiciosHabilitacion.findByAplicaImagenes", query = "SELECT c FROM CntPrestadorSedeServiciosHabilitacion c WHERE c.aplicaImagenes = :aplicaImagenes"),
    @NamedQuery(name = "CntPrestadorSedeServiciosHabilitacion.findByAplicaOdontologia", query = "SELECT c FROM CntPrestadorSedeServiciosHabilitacion c WHERE c.aplicaOdontologia = :aplicaOdontologia"),
    @NamedQuery(name = "CntPrestadorSedeServiciosHabilitacion.findByAplicaTransporte", query = "SELECT c FROM CntPrestadorSedeServiciosHabilitacion c WHERE c.aplicaTransporte = :aplicaTransporte"),
    @NamedQuery(name = "CntPrestadorSedeServiciosHabilitacion.findByAplicaTrasplante", query = "SELECT c FROM CntPrestadorSedeServiciosHabilitacion c WHERE c.aplicaTrasplante = :aplicaTrasplante"),
    @NamedQuery(name = "CntPrestadorSedeServiciosHabilitacion.findByAplicaConsulta", query = "SELECT c FROM CntPrestadorSedeServiciosHabilitacion c WHERE c.aplicaConsulta = :aplicaConsulta"),
    @NamedQuery(name = "CntPrestadorSedeServiciosHabilitacion.findByAplicaAlternativa", query = "SELECT c FROM CntPrestadorSedeServiciosHabilitacion c WHERE c.aplicaAlternativa = :aplicaAlternativa"),
    @NamedQuery(name = "CntPrestadorSedeServiciosHabilitacion.findByAplicaOncologia", query = "SELECT c FROM CntPrestadorSedeServiciosHabilitacion c WHERE c.aplicaOncologia = :aplicaOncologia"),
    @NamedQuery(name = "CntPrestadorSedeServiciosHabilitacion.findByAplicaTerapia", query = "SELECT c FROM CntPrestadorSedeServiciosHabilitacion c WHERE c.aplicaTerapia = :aplicaTerapia"),
    @NamedQuery(name = "CntPrestadorSedeServiciosHabilitacion.findByAplicaProceso", query = "SELECT c FROM CntPrestadorSedeServiciosHabilitacion c WHERE c.aplicaProceso = :aplicaProceso"),
    @NamedQuery(name = "CntPrestadorSedeServiciosHabilitacion.findByAplicaPedt", query = "SELECT c FROM CntPrestadorSedeServiciosHabilitacion c WHERE c.aplicaPedt = :aplicaPedt"),
    @NamedQuery(name = "CntPrestadorSedeServiciosHabilitacion.findByAplicaAmbulatorio", query = "SELECT c FROM CntPrestadorSedeServiciosHabilitacion c WHERE c.aplicaAmbulatorio = :aplicaAmbulatorio"),
    @NamedQuery(name = "CntPrestadorSedeServiciosHabilitacion.findByAplicaHospitalario", query = "SELECT c FROM CntPrestadorSedeServiciosHabilitacion c WHERE c.aplicaHospitalario = :aplicaHospitalario"),
    @NamedQuery(name = "CntPrestadorSedeServiciosHabilitacion.findByAplicaUnidadMovil", query = "SELECT c FROM CntPrestadorSedeServiciosHabilitacion c WHERE c.aplicaUnidadMovil = :aplicaUnidadMovil"),
    @NamedQuery(name = "CntPrestadorSedeServiciosHabilitacion.findByAplicaOtrasExtramural", query = "SELECT c FROM CntPrestadorSedeServiciosHabilitacion c WHERE c.aplicaOtrasExtramural = :aplicaOtrasExtramural"),
    @NamedQuery(name = "CntPrestadorSedeServiciosHabilitacion.findByAplicaCentroReferencia", query = "SELECT c FROM CntPrestadorSedeServiciosHabilitacion c WHERE c.aplicaCentroReferencia = :aplicaCentroReferencia"),
    @NamedQuery(name = "CntPrestadorSedeServiciosHabilitacion.findByAplicaInstitucionRemisora", query = "SELECT c FROM CntPrestadorSedeServiciosHabilitacion c WHERE c.aplicaInstitucionRemisora = :aplicaInstitucionRemisora"),
    @NamedQuery(name = "CntPrestadorSedeServiciosHabilitacion.findByAplicaComplejidadBaja", query = "SELECT c FROM CntPrestadorSedeServiciosHabilitacion c WHERE c.aplicaComplejidadBaja = :aplicaComplejidadBaja"),
    @NamedQuery(name = "CntPrestadorSedeServiciosHabilitacion.findByAplicaComplejidadMedia", query = "SELECT c FROM CntPrestadorSedeServiciosHabilitacion c WHERE c.aplicaComplejidadMedia = :aplicaComplejidadMedia"),
    @NamedQuery(name = "CntPrestadorSedeServiciosHabilitacion.findByAplicaComplejidadAlta", query = "SELECT c FROM CntPrestadorSedeServiciosHabilitacion c WHERE c.aplicaComplejidadAlta = :aplicaComplejidadAlta"),
    @NamedQuery(name = "CntPrestadorSedeServiciosHabilitacion.findByAplicaIntramural", query = "SELECT c FROM CntPrestadorSedeServiciosHabilitacion c WHERE c.aplicaIntramural = :aplicaIntramural"),
    @NamedQuery(name = "CntPrestadorSedeServiciosHabilitacion.findByAplicaJornadaSalud", query = "SELECT c FROM CntPrestadorSedeServiciosHabilitacion c WHERE c.aplicaJornadaSalud = :aplicaJornadaSalud"),
    @NamedQuery(name = "CntPrestadorSedeServiciosHabilitacion.findByAplicaTelemedicina", query = "SELECT c FROM CntPrestadorSedeServiciosHabilitacion c WHERE c.aplicaTelemedicina = :aplicaTelemedicina"),
    @NamedQuery(name = "CntPrestadorSedeServiciosHabilitacion.findByAplicaRefTelemedInteractiva", query = "SELECT c FROM CntPrestadorSedeServiciosHabilitacion c WHERE c.aplicaRefTelemedInteractiva = :aplicaRefTelemedInteractiva"),
    @NamedQuery(name = "CntPrestadorSedeServiciosHabilitacion.findByAplicaRefTelemedNoInteractiva", query = "SELECT c FROM CntPrestadorSedeServiciosHabilitacion c WHERE c.aplicaRefTelemedNoInteractiva = :aplicaRefTelemedNoInteractiva"),
    @NamedQuery(name = "CntPrestadorSedeServiciosHabilitacion.findByAplicaReferenciaTeleExperticia", query = "SELECT c FROM CntPrestadorSedeServiciosHabilitacion c WHERE c.aplicaReferenciaTeleExperticia = :aplicaReferenciaTeleExperticia"),
    @NamedQuery(name = "CntPrestadorSedeServiciosHabilitacion.findByAplicaReferenciaTeleMonitoreo", query = "SELECT c FROM CntPrestadorSedeServiciosHabilitacion c WHERE c.aplicaReferenciaTeleMonitoreo = :aplicaReferenciaTeleMonitoreo"),
    @NamedQuery(name = "CntPrestadorSedeServiciosHabilitacion.findByAplicapRemisorTeleExperticia", query = "SELECT c FROM CntPrestadorSedeServiciosHabilitacion c WHERE c.aplicapRemisorTeleExperticia = :aplicapRemisorTeleExperticia"),
    @NamedQuery(name = "CntPrestadorSedeServiciosHabilitacion.findByAplicaRemisorTeleMonitoreo", query = "SELECT c FROM CntPrestadorSedeServiciosHabilitacion c WHERE c.aplicaRemisorTeleMonitoreo = :aplicaRemisorTeleMonitoreo"),
    @NamedQuery(name = "CntPrestadorSedeServiciosHabilitacion.findByAplicaSinComplejidad", query = "SELECT c FROM CntPrestadorSedeServiciosHabilitacion c WHERE c.aplicaSinComplejidad = :aplicaSinComplejidad"),
    @NamedQuery(name = "CntPrestadorSedeServiciosHabilitacion.findByAplicaTrasplanteOsteomuscular", query = "SELECT c FROM CntPrestadorSedeServiciosHabilitacion c WHERE c.aplicaTrasplanteOsteomuscular = :aplicaTrasplanteOsteomuscular"),
    @NamedQuery(name = "CntPrestadorSedeServiciosHabilitacion.findByAplicaTrasplantePiel", query = "SELECT c FROM CntPrestadorSedeServiciosHabilitacion c WHERE c.aplicaTrasplantePiel = :aplicaTrasplantePiel"),
    @NamedQuery(name = "CntPrestadorSedeServiciosHabilitacion.findByAplicaTrasplanteCardiovascular", query = "SELECT c FROM CntPrestadorSedeServiciosHabilitacion c WHERE c.aplicaTrasplanteCardiovascular = :aplicaTrasplanteCardiovascular"),
    @NamedQuery(name = "CntPrestadorSedeServiciosHabilitacion.findByAplicaTrasplanteTejidoOcular", query = "SELECT c FROM CntPrestadorSedeServiciosHabilitacion c WHERE c.aplicaTrasplanteTejidoOcular = :aplicaTrasplanteTejidoOcular"),
    @NamedQuery(name = "CntPrestadorSedeServiciosHabilitacion.findByAplicaAtencionPacienteQuemado", query = "SELECT c FROM CntPrestadorSedeServiciosHabilitacion c WHERE c.aplicaAtencionPacienteQuemado = :aplicaAtencionPacienteQuemado"),
    @NamedQuery(name = "CntPrestadorSedeServiciosHabilitacion.findByAplicaSaludMental", query = "SELECT c FROM CntPrestadorSedeServiciosHabilitacion c WHERE c.aplicaSaludMental = :aplicaSaludMental"),
    @NamedQuery(name = "CntPrestadorSedeServiciosHabilitacion.findByAplicaSpa", query = "SELECT c FROM CntPrestadorSedeServiciosHabilitacion c WHERE c.aplicaSpa = :aplicaSpa"),
    @NamedQuery(name = "CntPrestadorSedeServiciosHabilitacion.findByAplicaOtrasPatologias", query = "SELECT c FROM CntPrestadorSedeServiciosHabilitacion c WHERE c.aplicaOtrasPatologias = :aplicaOtrasPatologias"),
    @NamedQuery(name = "CntPrestadorSedeServiciosHabilitacion.findByAplicaTxCelProgeHematopoy", query = "SELECT c FROM CntPrestadorSedeServiciosHabilitacion c WHERE c.aplicaTxCelProgeHematopoy = :aplicaTxCelProgeHematopoy"),
    @NamedQuery(name = "CntPrestadorSedeServiciosHabilitacion.findByAplicaProcQuirurAmbulatorios", query = "SELECT c FROM CntPrestadorSedeServiciosHabilitacion c WHERE c.aplicaProcQuirurAmbulatorios = :aplicaProcQuirurAmbulatorios"),
    @NamedQuery(name = "CntPrestadorSedeServiciosHabilitacion.findByAplicaOrganoRinon", query = "SELECT c FROM CntPrestadorSedeServiciosHabilitacion c WHERE c.aplicaOrganoRinon = :aplicaOrganoRinon"),
    @NamedQuery(name = "CntPrestadorSedeServiciosHabilitacion.findByAplicaOrganoHigado", query = "SELECT c FROM CntPrestadorSedeServiciosHabilitacion c WHERE c.aplicaOrganoHigado = :aplicaOrganoHigado"),
    @NamedQuery(name = "CntPrestadorSedeServiciosHabilitacion.findByAplicaOrganoPancreas", query = "SELECT c FROM CntPrestadorSedeServiciosHabilitacion c WHERE c.aplicaOrganoPancreas = :aplicaOrganoPancreas"),
    @NamedQuery(name = "CntPrestadorSedeServiciosHabilitacion.findByAplicaOrganoIntestino", query = "SELECT c FROM CntPrestadorSedeServiciosHabilitacion c WHERE c.aplicaOrganoIntestino = :aplicaOrganoIntestino"),
    @NamedQuery(name = "CntPrestadorSedeServiciosHabilitacion.findByAplicaOrganoMultivisceral", query = "SELECT c FROM CntPrestadorSedeServiciosHabilitacion c WHERE c.aplicaOrganoMultivisceral = :aplicaOrganoMultivisceral"),
    @NamedQuery(name = "CntPrestadorSedeServiciosHabilitacion.findByAplicaOrganoCorazon", query = "SELECT c FROM CntPrestadorSedeServiciosHabilitacion c WHERE c.aplicaOrganoCorazon = :aplicaOrganoCorazon"),
    @NamedQuery(name = "CntPrestadorSedeServiciosHabilitacion.findByAplicaOrganoPulmon", query = "SELECT c FROM CntPrestadorSedeServiciosHabilitacion c WHERE c.aplicaOrganoPulmon = :aplicaOrganoPulmon"),
    @NamedQuery(name = "CntPrestadorSedeServiciosHabilitacion.findByAplicaSustanciasPsicoactivas", query = "SELECT c FROM CntPrestadorSedeServiciosHabilitacion c WHERE c.aplicaSustanciasPsicoactivas = :aplicaSustanciasPsicoactivas"),
    @NamedQuery(name = "CntPrestadorSedeServiciosHabilitacion.findByAplicaTrasplanteRenal", query = "SELECT c FROM CntPrestadorSedeServiciosHabilitacion c WHERE c.aplicaTrasplanteRenal = :aplicaTrasplanteRenal"),
    @NamedQuery(name = "CntPrestadorSedeServiciosHabilitacion.findByFechaApertura", query = "SELECT c FROM CntPrestadorSedeServiciosHabilitacion c WHERE c.fechaApertura = :fechaApertura"),
    @NamedQuery(name = "CntPrestadorSedeServiciosHabilitacion.findByUsuarioCrea", query = "SELECT c FROM CntPrestadorSedeServiciosHabilitacion c WHERE c.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "CntPrestadorSedeServiciosHabilitacion.findByTerminalCrea", query = "SELECT c FROM CntPrestadorSedeServiciosHabilitacion c WHERE c.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "CntPrestadorSedeServiciosHabilitacion.findByFechaHoraCrea", query = "SELECT c FROM CntPrestadorSedeServiciosHabilitacion c WHERE c.fechaHoraCrea = :fechaHoraCrea"),
    @NamedQuery(name = "CntPrestadorSedeServiciosHabilitacion.findByUsuarioModifica", query = "SELECT c FROM CntPrestadorSedeServiciosHabilitacion c WHERE c.usuarioModifica = :usuarioModifica"),
    @NamedQuery(name = "CntPrestadorSedeServiciosHabilitacion.findByTerminalModifica", query = "SELECT c FROM CntPrestadorSedeServiciosHabilitacion c WHERE c.terminalModifica = :terminalModifica"),
    @NamedQuery(name = "CntPrestadorSedeServiciosHabilitacion.findByFechaHoraModifica", query = "SELECT c FROM CntPrestadorSedeServiciosHabilitacion c WHERE c.fechaHoraModifica = :fechaHoraModifica")})
public class CntPrestadorSedeServiciosHabilitacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(name = "ma_servicios_habilitacion_codigo")
    private String maServiciosHabilitacionCodigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 512)
    @Column(name = "ma_servicios_habilitacion_valor")
    private String maServiciosHabilitacionValor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "activo")
    private boolean activo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "aplica_urgencia")
    private boolean aplicaUrgencia;
    @Basic(optional = false)
    @NotNull
    @Column(name = "aplica_domiciliaria")
    private boolean aplicaDomiciliaria;
    @Basic(optional = false)
    @NotNull
    @Column(name = "aplica_cirugia")
    private boolean aplicaCirugia;
    @Basic(optional = false)
    @NotNull
    @Column(name = "aplica_hospitalizacion")
    private boolean aplicaHospitalizacion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "aplica_laboratorio")
    private boolean aplicaLaboratorio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "aplica_imagenes")
    private boolean aplicaImagenes;
    @Basic(optional = false)
    @NotNull
    @Column(name = "aplica_odontologia")
    private boolean aplicaOdontologia;
    @Basic(optional = false)
    @NotNull
    @Column(name = "aplica_transporte")
    private boolean aplicaTransporte;
    @Basic(optional = false)
    @NotNull
    @Column(name = "aplica_trasplante")
    private boolean aplicaTrasplante;
    @Basic(optional = false)
    @NotNull
    @Column(name = "aplica_consulta")
    private boolean aplicaConsulta;
    @Basic(optional = false)
    @NotNull
    @Column(name = "aplica_alternativa")
    private boolean aplicaAlternativa;
    @Basic(optional = false)
    @NotNull
    @Column(name = "aplica_oncologia")
    private boolean aplicaOncologia;
    @Basic(optional = false)
    @NotNull
    @Column(name = "aplica_terapia")
    private boolean aplicaTerapia;
    @Basic(optional = false)
    @NotNull
    @Column(name = "aplica_proceso")
    private boolean aplicaProceso;
    @Basic(optional = false)
    @NotNull
    @Column(name = "aplica_pedt")
    private boolean aplicaPedt;
    @Basic(optional = false)
    @NotNull
    @Column(name = "aplica_ambulatorio")
    private boolean aplicaAmbulatorio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "aplica_hospitalario")
    private boolean aplicaHospitalario;
    @Basic(optional = false)
    @NotNull
    @Column(name = "aplica_unidad_movil")
    private boolean aplicaUnidadMovil;
    @Basic(optional = false)
    @NotNull
    @Column(name = "aplica_otras_extramural")
    private boolean aplicaOtrasExtramural;
    @Basic(optional = false)
    @NotNull
    @Column(name = "aplica_centro_referencia")
    private boolean aplicaCentroReferencia;
    @Basic(optional = false)
    @NotNull
    @Column(name = "aplica_institucion_remisora")
    private boolean aplicaInstitucionRemisora;
    @Basic(optional = false)
    @NotNull
    @Column(name = "aplica_complejidad_baja")
    private boolean aplicaComplejidadBaja;
    @Basic(optional = false)
    @NotNull
    @Column(name = "aplica_complejidad_media")
    private boolean aplicaComplejidadMedia;
    @Basic(optional = false)
    @NotNull
    @Column(name = "aplica_complejidad_alta")
    private boolean aplicaComplejidadAlta;
    @Basic(optional = false)
    @NotNull
    @Column(name = "aplica_intramural")
    private boolean aplicaIntramural;
    @Basic(optional = false)
    @NotNull
    @Column(name = "aplica_jornada_salud")
    private boolean aplicaJornadaSalud;
    @Basic(optional = false)
    @NotNull
    @Column(name = "aplica_telemedicina")
    private boolean aplicaTelemedicina;
    @Basic(optional = false)
    @NotNull
    @Column(name = "aplica_ref_telemed_interactiva")
    private boolean aplicaRefTelemedInteractiva;
    @Basic(optional = false)
    @NotNull
    @Column(name = "aplica_ref_telemed_no_interactiva")
    private boolean aplicaRefTelemedNoInteractiva;
    @Basic(optional = false)
    @NotNull
    @Column(name = "aplica_referencia_tele_experticia")
    private boolean aplicaReferenciaTeleExperticia;
    @Basic(optional = false)
    @NotNull
    @Column(name = "aplica_referencia_tele_monitoreo")
    private boolean aplicaReferenciaTeleMonitoreo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "aplicap_remisor_tele_experticia")
    private boolean aplicapRemisorTeleExperticia;
    @Basic(optional = false)
    @NotNull
    @Column(name = "aplica_remisor_tele_monitoreo")
    private boolean aplicaRemisorTeleMonitoreo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "aplica_sin_complejidad")
    private boolean aplicaSinComplejidad;
    @Basic(optional = false)
    @NotNull
    @Column(name = "aplica_trasplante_osteomuscular")
    private boolean aplicaTrasplanteOsteomuscular;
    @Basic(optional = false)
    @NotNull
    @Column(name = "aplica_trasplante_piel")
    private boolean aplicaTrasplantePiel;
    @Basic(optional = false)
    @NotNull
    @Column(name = "aplica_trasplante_cardiovascular")
    private boolean aplicaTrasplanteCardiovascular;
    @Basic(optional = false)
    @NotNull
    @Column(name = "aplica_trasplante_tejido_ocular")
    private boolean aplicaTrasplanteTejidoOcular;
    @Basic(optional = false)
    @NotNull
    @Column(name = "aplica_atencion_paciente_quemado")
    private boolean aplicaAtencionPacienteQuemado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "aplica_salud_mental")
    private boolean aplicaSaludMental;
    @Basic(optional = false)
    @NotNull
    @Column(name = "aplica_spa")
    private boolean aplicaSpa;
    @Basic(optional = false)
    @NotNull
    @Column(name = "aplica_otras_patologias")
    private boolean aplicaOtrasPatologias;
    @Basic(optional = false)
    @NotNull
    @Column(name = "aplica_tx_cel_proge_hematopoy")
    private boolean aplicaTxCelProgeHematopoy;
    @Basic(optional = false)
    @NotNull
    @Column(name = "aplica_proc_quirur_ambulatorios")
    private boolean aplicaProcQuirurAmbulatorios;
    @Basic(optional = false)
    @NotNull
    @Column(name = "aplica_organo_rinon")
    private boolean aplicaOrganoRinon;
    @Basic(optional = false)
    @NotNull
    @Column(name = "aplica_organo_higado")
    private boolean aplicaOrganoHigado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "aplica_organo_pancreas")
    private boolean aplicaOrganoPancreas;
    @Basic(optional = false)
    @NotNull
    @Column(name = "aplica_organo_intestino")
    private boolean aplicaOrganoIntestino;
    @Basic(optional = false)
    @NotNull
    @Column(name = "aplica_organo_multivisceral")
    private boolean aplicaOrganoMultivisceral;
    @Basic(optional = false)
    @NotNull
    @Column(name = "aplica_organo_corazon")
    private boolean aplicaOrganoCorazon;
    @Basic(optional = false)
    @NotNull
    @Column(name = "aplica_organo_pulmon")
    private boolean aplicaOrganoPulmon;
    @Basic(optional = false)
    @NotNull
    @Column(name = "aplica_sustancias_psicoactivas")
    private boolean aplicaSustanciasPsicoactivas;
    @Basic(optional = false)
    @NotNull
    @Column(name = "aplica_trasplante_renal")
    private boolean aplicaTrasplanteRenal;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_apertura")
    @Temporal(TemporalType.DATE)
    private Date fechaApertura;
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
    @JoinColumn(name = "cnt_prestador_sedes_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private CntPrestadorSedes cntPrestadorSedesId;
    @JoinColumn(name = "ma_servicios_habilitacion_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private MaServiciosHabilitacion maServiciosHabilitacionId;

    public CntPrestadorSedeServiciosHabilitacion() {
    }

    public CntPrestadorSedeServiciosHabilitacion(Integer id) {
        this.id = id;
    }

    public CntPrestadorSedeServiciosHabilitacion(Integer id, String maServiciosHabilitacionCodigo, String maServiciosHabilitacionValor, boolean activo, boolean aplicaUrgencia, boolean aplicaDomiciliaria, boolean aplicaCirugia, boolean aplicaHospitalizacion, boolean aplicaLaboratorio, boolean aplicaImagenes, boolean aplicaOdontologia, boolean aplicaTransporte, boolean aplicaTrasplante, boolean aplicaConsulta, boolean aplicaAlternativa, boolean aplicaOncologia, boolean aplicaTerapia, boolean aplicaProceso, boolean aplicaPedt, boolean aplicaAmbulatorio, boolean aplicaHospitalario, boolean aplicaUnidadMovil, boolean aplicaOtrasExtramural, boolean aplicaCentroReferencia, boolean aplicaInstitucionRemisora, boolean aplicaComplejidadBaja, boolean aplicaComplejidadMedia, boolean aplicaComplejidadAlta, boolean aplicaIntramural, boolean aplicaJornadaSalud, boolean aplicaTelemedicina, boolean aplicaRefTelemedInteractiva, boolean aplicaRefTelemedNoInteractiva, boolean aplicaReferenciaTeleExperticia, boolean aplicaReferenciaTeleMonitoreo, boolean aplicapRemisorTeleExperticia, boolean aplicaRemisorTeleMonitoreo, boolean aplicaSinComplejidad, boolean aplicaTrasplanteOsteomuscular, boolean aplicaTrasplantePiel, boolean aplicaTrasplanteCardiovascular, boolean aplicaTrasplanteTejidoOcular, boolean aplicaAtencionPacienteQuemado, boolean aplicaSaludMental, boolean aplicaSpa, boolean aplicaOtrasPatologias, boolean aplicaTxCelProgeHematopoy, boolean aplicaProcQuirurAmbulatorios, boolean aplicaOrganoRinon, boolean aplicaOrganoHigado, boolean aplicaOrganoPancreas, boolean aplicaOrganoIntestino, boolean aplicaOrganoMultivisceral, boolean aplicaOrganoCorazon, boolean aplicaOrganoPulmon, boolean aplicaSustanciasPsicoactivas, boolean aplicaTrasplanteRenal, Date fechaApertura, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.maServiciosHabilitacionCodigo = maServiciosHabilitacionCodigo;
        this.maServiciosHabilitacionValor = maServiciosHabilitacionValor;
        this.activo = activo;
        this.aplicaUrgencia = aplicaUrgencia;
        this.aplicaDomiciliaria = aplicaDomiciliaria;
        this.aplicaCirugia = aplicaCirugia;
        this.aplicaHospitalizacion = aplicaHospitalizacion;
        this.aplicaLaboratorio = aplicaLaboratorio;
        this.aplicaImagenes = aplicaImagenes;
        this.aplicaOdontologia = aplicaOdontologia;
        this.aplicaTransporte = aplicaTransporte;
        this.aplicaTrasplante = aplicaTrasplante;
        this.aplicaConsulta = aplicaConsulta;
        this.aplicaAlternativa = aplicaAlternativa;
        this.aplicaOncologia = aplicaOncologia;
        this.aplicaTerapia = aplicaTerapia;
        this.aplicaProceso = aplicaProceso;
        this.aplicaPedt = aplicaPedt;
        this.aplicaAmbulatorio = aplicaAmbulatorio;
        this.aplicaHospitalario = aplicaHospitalario;
        this.aplicaUnidadMovil = aplicaUnidadMovil;
        this.aplicaOtrasExtramural = aplicaOtrasExtramural;
        this.aplicaCentroReferencia = aplicaCentroReferencia;
        this.aplicaInstitucionRemisora = aplicaInstitucionRemisora;
        this.aplicaComplejidadBaja = aplicaComplejidadBaja;
        this.aplicaComplejidadMedia = aplicaComplejidadMedia;
        this.aplicaComplejidadAlta = aplicaComplejidadAlta;
        this.aplicaIntramural = aplicaIntramural;
        this.aplicaJornadaSalud = aplicaJornadaSalud;
        this.aplicaTelemedicina = aplicaTelemedicina;
        this.aplicaRefTelemedInteractiva = aplicaRefTelemedInteractiva;
        this.aplicaRefTelemedNoInteractiva = aplicaRefTelemedNoInteractiva;
        this.aplicaReferenciaTeleExperticia = aplicaReferenciaTeleExperticia;
        this.aplicaReferenciaTeleMonitoreo = aplicaReferenciaTeleMonitoreo;
        this.aplicapRemisorTeleExperticia = aplicapRemisorTeleExperticia;
        this.aplicaRemisorTeleMonitoreo = aplicaRemisorTeleMonitoreo;
        this.aplicaSinComplejidad = aplicaSinComplejidad;
        this.aplicaTrasplanteOsteomuscular = aplicaTrasplanteOsteomuscular;
        this.aplicaTrasplantePiel = aplicaTrasplantePiel;
        this.aplicaTrasplanteCardiovascular = aplicaTrasplanteCardiovascular;
        this.aplicaTrasplanteTejidoOcular = aplicaTrasplanteTejidoOcular;
        this.aplicaAtencionPacienteQuemado = aplicaAtencionPacienteQuemado;
        this.aplicaSaludMental = aplicaSaludMental;
        this.aplicaSpa = aplicaSpa;
        this.aplicaOtrasPatologias = aplicaOtrasPatologias;
        this.aplicaTxCelProgeHematopoy = aplicaTxCelProgeHematopoy;
        this.aplicaProcQuirurAmbulatorios = aplicaProcQuirurAmbulatorios;
        this.aplicaOrganoRinon = aplicaOrganoRinon;
        this.aplicaOrganoHigado = aplicaOrganoHigado;
        this.aplicaOrganoPancreas = aplicaOrganoPancreas;
        this.aplicaOrganoIntestino = aplicaOrganoIntestino;
        this.aplicaOrganoMultivisceral = aplicaOrganoMultivisceral;
        this.aplicaOrganoCorazon = aplicaOrganoCorazon;
        this.aplicaOrganoPulmon = aplicaOrganoPulmon;
        this.aplicaSustanciasPsicoactivas = aplicaSustanciasPsicoactivas;
        this.aplicaTrasplanteRenal = aplicaTrasplanteRenal;
        this.fechaApertura = fechaApertura;
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

    public String getMaServiciosHabilitacionCodigo() {
        return maServiciosHabilitacionCodigo;
    }

    public void setMaServiciosHabilitacionCodigo(String maServiciosHabilitacionCodigo) {
        this.maServiciosHabilitacionCodigo = maServiciosHabilitacionCodigo;
    }

    public String getMaServiciosHabilitacionValor() {
        return maServiciosHabilitacionValor;
    }

    public void setMaServiciosHabilitacionValor(String maServiciosHabilitacionValor) {
        this.maServiciosHabilitacionValor = maServiciosHabilitacionValor;
    }

    public boolean getActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public boolean getAplicaUrgencia() {
        return aplicaUrgencia;
    }

    public void setAplicaUrgencia(boolean aplicaUrgencia) {
        this.aplicaUrgencia = aplicaUrgencia;
    }

    public boolean getAplicaDomiciliaria() {
        return aplicaDomiciliaria;
    }

    public void setAplicaDomiciliaria(boolean aplicaDomiciliaria) {
        this.aplicaDomiciliaria = aplicaDomiciliaria;
    }

    public boolean getAplicaCirugia() {
        return aplicaCirugia;
    }

    public void setAplicaCirugia(boolean aplicaCirugia) {
        this.aplicaCirugia = aplicaCirugia;
    }

    public boolean getAplicaHospitalizacion() {
        return aplicaHospitalizacion;
    }

    public void setAplicaHospitalizacion(boolean aplicaHospitalizacion) {
        this.aplicaHospitalizacion = aplicaHospitalizacion;
    }

    public boolean getAplicaLaboratorio() {
        return aplicaLaboratorio;
    }

    public void setAplicaLaboratorio(boolean aplicaLaboratorio) {
        this.aplicaLaboratorio = aplicaLaboratorio;
    }

    public boolean getAplicaImagenes() {
        return aplicaImagenes;
    }

    public void setAplicaImagenes(boolean aplicaImagenes) {
        this.aplicaImagenes = aplicaImagenes;
    }

    public boolean getAplicaOdontologia() {
        return aplicaOdontologia;
    }

    public void setAplicaOdontologia(boolean aplicaOdontologia) {
        this.aplicaOdontologia = aplicaOdontologia;
    }

    public boolean getAplicaTransporte() {
        return aplicaTransporte;
    }

    public void setAplicaTransporte(boolean aplicaTransporte) {
        this.aplicaTransporte = aplicaTransporte;
    }

    public boolean getAplicaTrasplante() {
        return aplicaTrasplante;
    }

    public void setAplicaTrasplante(boolean aplicaTrasplante) {
        this.aplicaTrasplante = aplicaTrasplante;
    }

    public boolean getAplicaConsulta() {
        return aplicaConsulta;
    }

    public void setAplicaConsulta(boolean aplicaConsulta) {
        this.aplicaConsulta = aplicaConsulta;
    }

    public boolean getAplicaAlternativa() {
        return aplicaAlternativa;
    }

    public void setAplicaAlternativa(boolean aplicaAlternativa) {
        this.aplicaAlternativa = aplicaAlternativa;
    }

    public boolean getAplicaOncologia() {
        return aplicaOncologia;
    }

    public void setAplicaOncologia(boolean aplicaOncologia) {
        this.aplicaOncologia = aplicaOncologia;
    }

    public boolean getAplicaTerapia() {
        return aplicaTerapia;
    }

    public void setAplicaTerapia(boolean aplicaTerapia) {
        this.aplicaTerapia = aplicaTerapia;
    }

    public boolean getAplicaProceso() {
        return aplicaProceso;
    }

    public void setAplicaProceso(boolean aplicaProceso) {
        this.aplicaProceso = aplicaProceso;
    }

    public boolean getAplicaPedt() {
        return aplicaPedt;
    }

    public void setAplicaPedt(boolean aplicaPedt) {
        this.aplicaPedt = aplicaPedt;
    }

    public boolean getAplicaAmbulatorio() {
        return aplicaAmbulatorio;
    }

    public void setAplicaAmbulatorio(boolean aplicaAmbulatorio) {
        this.aplicaAmbulatorio = aplicaAmbulatorio;
    }

    public boolean getAplicaHospitalario() {
        return aplicaHospitalario;
    }

    public void setAplicaHospitalario(boolean aplicaHospitalario) {
        this.aplicaHospitalario = aplicaHospitalario;
    }

    public boolean getAplicaUnidadMovil() {
        return aplicaUnidadMovil;
    }

    public void setAplicaUnidadMovil(boolean aplicaUnidadMovil) {
        this.aplicaUnidadMovil = aplicaUnidadMovil;
    }

    public boolean getAplicaOtrasExtramural() {
        return aplicaOtrasExtramural;
    }

    public void setAplicaOtrasExtramural(boolean aplicaOtrasExtramural) {
        this.aplicaOtrasExtramural = aplicaOtrasExtramural;
    }

    public boolean getAplicaCentroReferencia() {
        return aplicaCentroReferencia;
    }

    public void setAplicaCentroReferencia(boolean aplicaCentroReferencia) {
        this.aplicaCentroReferencia = aplicaCentroReferencia;
    }

    public boolean getAplicaInstitucionRemisora() {
        return aplicaInstitucionRemisora;
    }

    public void setAplicaInstitucionRemisora(boolean aplicaInstitucionRemisora) {
        this.aplicaInstitucionRemisora = aplicaInstitucionRemisora;
    }

    public boolean getAplicaComplejidadBaja() {
        return aplicaComplejidadBaja;
    }

    public void setAplicaComplejidadBaja(boolean aplicaComplejidadBaja) {
        this.aplicaComplejidadBaja = aplicaComplejidadBaja;
    }

    public boolean getAplicaComplejidadMedia() {
        return aplicaComplejidadMedia;
    }

    public void setAplicaComplejidadMedia(boolean aplicaComplejidadMedia) {
        this.aplicaComplejidadMedia = aplicaComplejidadMedia;
    }

    public boolean getAplicaComplejidadAlta() {
        return aplicaComplejidadAlta;
    }

    public void setAplicaComplejidadAlta(boolean aplicaComplejidadAlta) {
        this.aplicaComplejidadAlta = aplicaComplejidadAlta;
    }

    public boolean getAplicaIntramural() {
        return aplicaIntramural;
    }

    public void setAplicaIntramural(boolean aplicaIntramural) {
        this.aplicaIntramural = aplicaIntramural;
    }

    public boolean getAplicaJornadaSalud() {
        return aplicaJornadaSalud;
    }

    public void setAplicaJornadaSalud(boolean aplicaJornadaSalud) {
        this.aplicaJornadaSalud = aplicaJornadaSalud;
    }

    public boolean getAplicaTelemedicina() {
        return aplicaTelemedicina;
    }

    public void setAplicaTelemedicina(boolean aplicaTelemedicina) {
        this.aplicaTelemedicina = aplicaTelemedicina;
    }

    public boolean getAplicaRefTelemedInteractiva() {
        return aplicaRefTelemedInteractiva;
    }

    public void setAplicaRefTelemedInteractiva(boolean aplicaRefTelemedInteractiva) {
        this.aplicaRefTelemedInteractiva = aplicaRefTelemedInteractiva;
    }

    public boolean getAplicaRefTelemedNoInteractiva() {
        return aplicaRefTelemedNoInteractiva;
    }

    public void setAplicaRefTelemedNoInteractiva(boolean aplicaRefTelemedNoInteractiva) {
        this.aplicaRefTelemedNoInteractiva = aplicaRefTelemedNoInteractiva;
    }

    public boolean getAplicaReferenciaTeleExperticia() {
        return aplicaReferenciaTeleExperticia;
    }

    public void setAplicaReferenciaTeleExperticia(boolean aplicaReferenciaTeleExperticia) {
        this.aplicaReferenciaTeleExperticia = aplicaReferenciaTeleExperticia;
    }

    public boolean getAplicaReferenciaTeleMonitoreo() {
        return aplicaReferenciaTeleMonitoreo;
    }

    public void setAplicaReferenciaTeleMonitoreo(boolean aplicaReferenciaTeleMonitoreo) {
        this.aplicaReferenciaTeleMonitoreo = aplicaReferenciaTeleMonitoreo;
    }

    public boolean getAplicapRemisorTeleExperticia() {
        return aplicapRemisorTeleExperticia;
    }

    public void setAplicapRemisorTeleExperticia(boolean aplicapRemisorTeleExperticia) {
        this.aplicapRemisorTeleExperticia = aplicapRemisorTeleExperticia;
    }

    public boolean getAplicaRemisorTeleMonitoreo() {
        return aplicaRemisorTeleMonitoreo;
    }

    public void setAplicaRemisorTeleMonitoreo(boolean aplicaRemisorTeleMonitoreo) {
        this.aplicaRemisorTeleMonitoreo = aplicaRemisorTeleMonitoreo;
    }

    public boolean getAplicaSinComplejidad() {
        return aplicaSinComplejidad;
    }

    public void setAplicaSinComplejidad(boolean aplicaSinComplejidad) {
        this.aplicaSinComplejidad = aplicaSinComplejidad;
    }

    public boolean getAplicaTrasplanteOsteomuscular() {
        return aplicaTrasplanteOsteomuscular;
    }

    public void setAplicaTrasplanteOsteomuscular(boolean aplicaTrasplanteOsteomuscular) {
        this.aplicaTrasplanteOsteomuscular = aplicaTrasplanteOsteomuscular;
    }

    public boolean getAplicaTrasplantePiel() {
        return aplicaTrasplantePiel;
    }

    public void setAplicaTrasplantePiel(boolean aplicaTrasplantePiel) {
        this.aplicaTrasplantePiel = aplicaTrasplantePiel;
    }

    public boolean getAplicaTrasplanteCardiovascular() {
        return aplicaTrasplanteCardiovascular;
    }

    public void setAplicaTrasplanteCardiovascular(boolean aplicaTrasplanteCardiovascular) {
        this.aplicaTrasplanteCardiovascular = aplicaTrasplanteCardiovascular;
    }

    public boolean getAplicaTrasplanteTejidoOcular() {
        return aplicaTrasplanteTejidoOcular;
    }

    public void setAplicaTrasplanteTejidoOcular(boolean aplicaTrasplanteTejidoOcular) {
        this.aplicaTrasplanteTejidoOcular = aplicaTrasplanteTejidoOcular;
    }

    public boolean getAplicaAtencionPacienteQuemado() {
        return aplicaAtencionPacienteQuemado;
    }

    public void setAplicaAtencionPacienteQuemado(boolean aplicaAtencionPacienteQuemado) {
        this.aplicaAtencionPacienteQuemado = aplicaAtencionPacienteQuemado;
    }

    public boolean getAplicaSaludMental() {
        return aplicaSaludMental;
    }

    public void setAplicaSaludMental(boolean aplicaSaludMental) {
        this.aplicaSaludMental = aplicaSaludMental;
    }

    public boolean getAplicaSpa() {
        return aplicaSpa;
    }

    public void setAplicaSpa(boolean aplicaSpa) {
        this.aplicaSpa = aplicaSpa;
    }

    public boolean getAplicaOtrasPatologias() {
        return aplicaOtrasPatologias;
    }

    public void setAplicaOtrasPatologias(boolean aplicaOtrasPatologias) {
        this.aplicaOtrasPatologias = aplicaOtrasPatologias;
    }

    public boolean getAplicaTxCelProgeHematopoy() {
        return aplicaTxCelProgeHematopoy;
    }

    public void setAplicaTxCelProgeHematopoy(boolean aplicaTxCelProgeHematopoy) {
        this.aplicaTxCelProgeHematopoy = aplicaTxCelProgeHematopoy;
    }

    public boolean getAplicaProcQuirurAmbulatorios() {
        return aplicaProcQuirurAmbulatorios;
    }

    public void setAplicaProcQuirurAmbulatorios(boolean aplicaProcQuirurAmbulatorios) {
        this.aplicaProcQuirurAmbulatorios = aplicaProcQuirurAmbulatorios;
    }

    public boolean getAplicaOrganoRinon() {
        return aplicaOrganoRinon;
    }

    public void setAplicaOrganoRinon(boolean aplicaOrganoRinon) {
        this.aplicaOrganoRinon = aplicaOrganoRinon;
    }

    public boolean getAplicaOrganoHigado() {
        return aplicaOrganoHigado;
    }

    public void setAplicaOrganoHigado(boolean aplicaOrganoHigado) {
        this.aplicaOrganoHigado = aplicaOrganoHigado;
    }

    public boolean getAplicaOrganoPancreas() {
        return aplicaOrganoPancreas;
    }

    public void setAplicaOrganoPancreas(boolean aplicaOrganoPancreas) {
        this.aplicaOrganoPancreas = aplicaOrganoPancreas;
    }

    public boolean getAplicaOrganoIntestino() {
        return aplicaOrganoIntestino;
    }

    public void setAplicaOrganoIntestino(boolean aplicaOrganoIntestino) {
        this.aplicaOrganoIntestino = aplicaOrganoIntestino;
    }

    public boolean getAplicaOrganoMultivisceral() {
        return aplicaOrganoMultivisceral;
    }

    public void setAplicaOrganoMultivisceral(boolean aplicaOrganoMultivisceral) {
        this.aplicaOrganoMultivisceral = aplicaOrganoMultivisceral;
    }

    public boolean getAplicaOrganoCorazon() {
        return aplicaOrganoCorazon;
    }

    public void setAplicaOrganoCorazon(boolean aplicaOrganoCorazon) {
        this.aplicaOrganoCorazon = aplicaOrganoCorazon;
    }

    public boolean getAplicaOrganoPulmon() {
        return aplicaOrganoPulmon;
    }

    public void setAplicaOrganoPulmon(boolean aplicaOrganoPulmon) {
        this.aplicaOrganoPulmon = aplicaOrganoPulmon;
    }

    public boolean getAplicaSustanciasPsicoactivas() {
        return aplicaSustanciasPsicoactivas;
    }

    public void setAplicaSustanciasPsicoactivas(boolean aplicaSustanciasPsicoactivas) {
        this.aplicaSustanciasPsicoactivas = aplicaSustanciasPsicoactivas;
    }

    public boolean getAplicaTrasplanteRenal() {
        return aplicaTrasplanteRenal;
    }

    public void setAplicaTrasplanteRenal(boolean aplicaTrasplanteRenal) {
        this.aplicaTrasplanteRenal = aplicaTrasplanteRenal;
    }

    public Date getFechaApertura() {
        return fechaApertura;
    }

    public void setFechaApertura(Date fechaApertura) {
        this.fechaApertura = fechaApertura;
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

    public CntPrestadorSedes getCntPrestadorSedesId() {
        return cntPrestadorSedesId;
    }

    public void setCntPrestadorSedesId(CntPrestadorSedes cntPrestadorSedesId) {
        this.cntPrestadorSedesId = cntPrestadorSedesId;
    }

    public MaServiciosHabilitacion getMaServiciosHabilitacionId() {
        return maServiciosHabilitacionId;
    }

    public void setMaServiciosHabilitacionId(MaServiciosHabilitacion maServiciosHabilitacionId) {
        this.maServiciosHabilitacionId = maServiciosHabilitacionId;
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
        if (!(object instanceof CntPrestadorSedeServiciosHabilitacion)) {
            return false;
        }
        CntPrestadorSedeServiciosHabilitacion other = (CntPrestadorSedeServiciosHabilitacion) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.CntPrestadorSedeServiciosHabilitacion[ id=" + id + " ]";
    }
    
}
