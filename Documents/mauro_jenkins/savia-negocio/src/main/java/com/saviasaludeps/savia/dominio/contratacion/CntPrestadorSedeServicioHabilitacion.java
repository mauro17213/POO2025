package com.saviasaludeps.savia.dominio.contratacion;

import com.saviasaludeps.savia.dominio.generico.Auditoria;
import com.saviasaludeps.savia.dominio.maestro.MaServicioHabilitacion;
import java.util.Date;

public class CntPrestadorSedeServicioHabilitacion extends Auditoria {

    private Integer id;
    private String maServiciosHabilitacionCodigo;
    private String maServiciosHabilitacionValor;
    private boolean activo;
    private boolean aplicaUrgencia;
    private boolean aplicaDomiciliaria;
    private boolean aplicaCirugia;
    private boolean aplicaHospitalizacion;
    private boolean aplicaLaboratorio;
    private boolean aplicaImagenes;
    private boolean aplicaOdontologia;
    private boolean aplicaTransporte;
    private boolean aplicaTrasplante;
    private boolean aplicaConsulta;
    private boolean aplicaAlternativa;
    private boolean aplicaOncologia;
    private boolean aplicaTerapia;
    private boolean aplicaProceso;
    private boolean aplicaPedt;
    private boolean aplicaAmbulatorio;
    private boolean aplicaHospitalario;
    private boolean aplicaUnidadMovil;
    private boolean aplicaOtrasExtramural;
    private boolean aplicaCentroReferencia;
    private boolean aplicaInstitucionRemisora;
    private boolean aplicaComplejidadBaja;
    private boolean aplicaComplejidadMedia;
    private boolean aplicaComplejidadAlta;
    private Date fechaApertura;
    private CntPrestadorSede cntPrestadorSede;
    private MaServicioHabilitacion maServicioHabilitacion;
    //2021-09-01 nuevos campos
    private boolean aplicaIntramural;
    private boolean aplicaJornadaSalud;
    private boolean aplicaTelemedicina;
    private boolean aplicaRefTelemedInteractiva;
    private boolean aplicaRefTelemedNoInteractiva;
    private boolean aplicaReferenciaTeleExperticia;
    private boolean aplicaReferenciaTeleMonitoreo;
    private boolean aplicapRemisorTeleExperticia;
    private boolean aplicaRemisorTeleMonitoreo;
    private boolean aplicaSinComplejidad;
    private boolean aplicaTrasplanteOsteomuscular;
    private boolean aplicaTrasplantePiel;
    private boolean aplicaTrasplanteCardiovascular;
    private boolean aplicaTrasplanteTejidoOcular;
    private boolean aplicaAtencionPacienteQuemado;
    private boolean aplicaSaludMental;
    private boolean aplicaSpa;
    private boolean aplicaOtrasPatologias;
    private boolean aplicaTxCelProgeHematopoy;
    private boolean aplicaProcQuirurAmbulatorios;
    private boolean aplicaOrganoRinon;
    private boolean aplicaOrganoHigado;
    private boolean aplicaOrganoPancreas;
    private boolean aplicaOrganoIntestino;
    private boolean aplicaOrganoMultivisceral;
    private boolean aplicaOrganoCorazon;
    private boolean aplicaOrganoPulmon;
    private boolean aplicaSustanciasPsicoactivas;
    private boolean aplicaTrasplanteRenal;

    //2024-02-19 jyperez campos carga masiva
    private String errorCarga;
    private boolean actualizar;
    private String registroArchivo;
    
    public CntPrestadorSedeServicioHabilitacion() {
    }

    public CntPrestadorSedeServicioHabilitacion(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the activo
     */
    public boolean isActivo() {
        return activo;
    }

    /**
     * @param activo the activo to set
     */
    public void setActivo(boolean activo) {
        this.activo = activo;
    }
    
    public String getActivoStr() {
        String mensaje = "NO";
        if (activo) {
            mensaje = "SI";
        }
        return mensaje;
    }

    /**
     * @return the maServiciosHabilitacionCodigo
     */
    public String getMaServiciosHabilitacionCodigo() {
        return maServiciosHabilitacionCodigo;
    }

    /**
     * @param maServiciosHabilitacionCodigo the maServiciosHabilitacionCodigo to set
     */
    public void setMaServiciosHabilitacionCodigo(String maServiciosHabilitacionCodigo) {
        this.maServiciosHabilitacionCodigo = maServiciosHabilitacionCodigo;
    }

    /**
     * @return the maServiciosHabilitacionValor
     */
    public String getMaServiciosHabilitacionValor() {
        return maServiciosHabilitacionValor;
    }

    /**
     * @param maServiciosHabilitacionValor the maServiciosHabilitacionValor to set
     */
    public void setMaServiciosHabilitacionValor(String maServiciosHabilitacionValor) {
        this.maServiciosHabilitacionValor = maServiciosHabilitacionValor;
    }

    /**
     * @return the aplicaUrgencia
     */
    public boolean isAplicaUrgencia() {
        return aplicaUrgencia;
    }

    /**
     * @param aplicaUrgencia the aplicaUrgencia to set
     */
    public void setAplicaUrgencia(boolean aplicaUrgencia) {
        this.aplicaUrgencia = aplicaUrgencia;
    }

    /**
     * @return the aplicaDomiciliaria
     */
    public boolean isAplicaDomiciliaria() {
        return aplicaDomiciliaria;
    }

    /**
     * @param aplicaDomiciliaria the aplicaDomiciliaria to set
     */
    public void setAplicaDomiciliaria(boolean aplicaDomiciliaria) {
        this.aplicaDomiciliaria = aplicaDomiciliaria;
    }

    /**
     * @return the aplicaCirugia
     */
    public boolean isAplicaCirugia() {
        return aplicaCirugia;
    }

    /**
     * @param aplicaCirugia the aplicaCirugia to set
     */
    public void setAplicaCirugia(boolean aplicaCirugia) {
        this.aplicaCirugia = aplicaCirugia;
    }

    /**
     * @return the aplicaHospitalizacion
     */
    public boolean isAplicaHospitalizacion() {
        return aplicaHospitalizacion;
    }

    /**
     * @param aplicaHospitalizacion the aplicaHospitalizacion to set
     */
    public void setAplicaHospitalizacion(boolean aplicaHospitalizacion) {
        this.aplicaHospitalizacion = aplicaHospitalizacion;
    }

    /**
     * @return the aplicaLaboratorio
     */
    public boolean isAplicaLaboratorio() {
        return aplicaLaboratorio;
    }

    /**
     * @param aplicaLaboratorio the aplicaLaboratorio to set
     */
    public void setAplicaLaboratorio(boolean aplicaLaboratorio) {
        this.aplicaLaboratorio = aplicaLaboratorio;
    }

    /**
     * @return the aplicaImagenes
     */
    public boolean isAplicaImagenes() {
        return aplicaImagenes;
    }

    /**
     * @param aplicaImagenes the aplicaImagenes to set
     */
    public void setAplicaImagenes(boolean aplicaImagenes) {
        this.aplicaImagenes = aplicaImagenes;
    }

    /**
     * @return the aplicaOdontologia
     */
    public boolean isAplicaOdontologia() {
        return aplicaOdontologia;
    }

    /**
     * @param aplicaOdontologia the aplicaOdontologia to set
     */
    public void setAplicaOdontologia(boolean aplicaOdontologia) {
        this.aplicaOdontologia = aplicaOdontologia;
    }

    /**
     * @return the aplicaTransporte
     */
    public boolean isAplicaTransporte() {
        return aplicaTransporte;
    }

    /**
     * @param aplicaTransporte the aplicaTransporte to set
     */
    public void setAplicaTransporte(boolean aplicaTransporte) {
        this.aplicaTransporte = aplicaTransporte;
    }

    /**
     * @return the aplicaTrasplante
     */
    public boolean isAplicaTrasplante() {
        return aplicaTrasplante;
    }

    /**
     * @param aplicaTrasplante the aplicaTrasplante to set
     */
    public void setAplicaTrasplante(boolean aplicaTrasplante) {
        this.aplicaTrasplante = aplicaTrasplante;
    }

    /**
     * @return the aplicaConsulta
     */
    public boolean isAplicaConsulta() {
        return aplicaConsulta;
    }

    /**
     * @param aplicaConsulta the aplicaConsulta to set
     */
    public void setAplicaConsulta(boolean aplicaConsulta) {
        this.aplicaConsulta = aplicaConsulta;
    }

    /**
     * @return the aplicaAlternativa
     */
    public boolean isAplicaAlternativa() {
        return aplicaAlternativa;
    }

    /**
     * @param aplicaAlternativa the aplicaAlternativa to set
     */
    public void setAplicaAlternativa(boolean aplicaAlternativa) {
        this.aplicaAlternativa = aplicaAlternativa;
    }

    /**
     * @return the aplicaOncologia
     */
    public boolean isAplicaOncologia() {
        return aplicaOncologia;
    }

    /**
     * @param aplicaOncologia the aplicaOncologia to set
     */
    public void setAplicaOncologia(boolean aplicaOncologia) {
        this.aplicaOncologia = aplicaOncologia;
    }

    /**
     * @return the aplicaTerapia
     */
    public boolean isAplicaTerapia() {
        return aplicaTerapia;
    }

    /**
     * @param aplicaTerapia the aplicaTerapia to set
     */
    public void setAplicaTerapia(boolean aplicaTerapia) {
        this.aplicaTerapia = aplicaTerapia;
    }

    /**
     * @return the aplicaProceso
     */
    public boolean isAplicaProceso() {
        return aplicaProceso;
    }

    /**
     * @param aplicaProceso the aplicaProceso to set
     */
    public void setAplicaProceso(boolean aplicaProceso) {
        this.aplicaProceso = aplicaProceso;
    }

    /**
     * @return the aplicaPedt
     */
    public boolean isAplicaPedt() {
        return aplicaPedt;
    }

    /**
     * @param aplicaPedt the aplicaPedt to set
     */
    public void setAplicaPedt(boolean aplicaPedt) {
        this.aplicaPedt = aplicaPedt;
    }

    /**
     * @return the aplicaAmbulatorio
     */
    public boolean isAplicaAmbulatorio() {
        return aplicaAmbulatorio;
    }

    /**
     * @param aplicaAmbulatorio the aplicaAmbulatorio to set
     */
    public void setAplicaAmbulatorio(boolean aplicaAmbulatorio) {
        this.aplicaAmbulatorio = aplicaAmbulatorio;
    }

    /**
     * @return the aplicaHospitalario
     */
    public boolean isAplicaHospitalario() {
        return aplicaHospitalario;
    }

    /**
     * @param aplicaHospitalario the aplicaHospitalario to set
     */
    public void setAplicaHospitalario(boolean aplicaHospitalario) {
        this.aplicaHospitalario = aplicaHospitalario;
    }

    /**
     * @return the aplicaUnidadMovil
     */
    public boolean isAplicaUnidadMovil() {
        return aplicaUnidadMovil;
    }

    /**
     * @param aplicaUnidadMovil the aplicaUnidadMovil to set
     */
    public void setAplicaUnidadMovil(boolean aplicaUnidadMovil) {
        this.aplicaUnidadMovil = aplicaUnidadMovil;
    }

    /**
     * @return the aplicaOtrasExtramural
     */
    public boolean isAplicaOtrasExtramural() {
        return aplicaOtrasExtramural;
    }

    /**
     * @param aplicaOtrasExtramural the aplicaOtrasExtramural to set
     */
    public void setAplicaOtrasExtramural(boolean aplicaOtrasExtramural) {
        this.aplicaOtrasExtramural = aplicaOtrasExtramural;
    }

    /**
     * @return the aplicaCentroReferencia
     */
    public boolean isAplicaCentroReferencia() {
        return aplicaCentroReferencia;
    }

    /**
     * @param aplicaCentroReferencia the aplicaCentroReferencia to set
     */
    public void setAplicaCentroReferencia(boolean aplicaCentroReferencia) {
        this.aplicaCentroReferencia = aplicaCentroReferencia;
    }

    /**
     * @return the aplicaInstitucionRemisora
     */
    public boolean isAplicaInstitucionRemisora() {
        return aplicaInstitucionRemisora;
    }

    /**
     * @param aplicaInstitucionRemisora the aplicaInstitucionRemisora to set
     */
    public void setAplicaInstitucionRemisora(boolean aplicaInstitucionRemisora) {
        this.aplicaInstitucionRemisora = aplicaInstitucionRemisora;
    }

    /**
     * @return the aplicaComplejidadBaja
     */
    public boolean isAplicaComplejidadBaja() {
        return aplicaComplejidadBaja;
    }

    /**
     * @param aplicaComplejidadBaja the aplicaComplejidadBaja to set
     */
    public void setAplicaComplejidadBaja(boolean aplicaComplejidadBaja) {
        this.aplicaComplejidadBaja = aplicaComplejidadBaja;
    }

    /**
     * @return the aplicaComplejidadMedia
     */
    public boolean isAplicaComplejidadMedia() {
        return aplicaComplejidadMedia;
    }

    /**
     * @param aplicaComplejidadMedia the aplicaComplejidadMedia to set
     */
    public void setAplicaComplejidadMedia(boolean aplicaComplejidadMedia) {
        this.aplicaComplejidadMedia = aplicaComplejidadMedia;
    }

    /**
     * @return the aplicaComplejidadAlta
     */
    public boolean isAplicaComplejidadAlta() {
        return aplicaComplejidadAlta;
    }

    /**
     * @param aplicaComplejidadAlta the aplicaComplejidadAlta to set
     */
    public void setAplicaComplejidadAlta(boolean aplicaComplejidadAlta) {
        this.aplicaComplejidadAlta = aplicaComplejidadAlta;
    }

    /**
     * @return the fechaApertura
     */
    public Date getFechaApertura() {
        return fechaApertura;
    }

    /**
     * @param fechaApertura the fechaApertura to set
     */
    public void setFechaApertura(Date fechaApertura) {
        this.fechaApertura = fechaApertura;
    }

    /**
     * @return the cntPrestadorSede
     */
    public CntPrestadorSede getCntPrestadorSede() {
        return cntPrestadorSede;
    }

    /**
     * @param cntPrestadorSede the cntPrestadorSede to set
     */
    public void setCntPrestadorSede(CntPrestadorSede cntPrestadorSede) {
        this.cntPrestadorSede = cntPrestadorSede;
    }

    /**
     * @return the maServicioHabilitacion
     */
    public MaServicioHabilitacion getMaServicioHabilitacion() {
        return maServicioHabilitacion;
    }

    /**
     * @param maServicioHabilitacion the maServicioHabilitacion to set
     */
    public void setMaServicioHabilitacion(MaServicioHabilitacion maServicioHabilitacion) {
        this.maServicioHabilitacion = maServicioHabilitacion;
    }

    @Override
    public String toString() {
        return "CntPrestadorSedeServicioHabilitacion{" + "id=" + id + ", maServiciosHabilitacionCodigo=" + maServiciosHabilitacionCodigo + ", maServiciosHabilitacionValor=" + maServiciosHabilitacionValor + ", activo=" + activo + ", aplicaUrgencia=" + aplicaUrgencia + ", aplicaDomiciliaria=" + aplicaDomiciliaria + ", aplicaCirugia=" + aplicaCirugia + ", aplicaHospitalizacion=" + aplicaHospitalizacion + ", aplicaLaboratorio=" + aplicaLaboratorio + ", aplicaImagenes=" + aplicaImagenes + ", aplicaOdontologia=" + aplicaOdontologia + ", aplicaTransporte=" + aplicaTransporte + ", aplicaTrasplante=" + aplicaTrasplante + ", aplicaConsulta=" + aplicaConsulta + ", aplicaAlternativa=" + aplicaAlternativa + ", aplicaOncologia=" + aplicaOncologia + ", aplicaTerapia=" + aplicaTerapia + ", aplicaProceso=" + aplicaProceso + ", aplicaPedt=" + aplicaPedt + ", aplicaAmbulatorio=" + aplicaAmbulatorio + ", aplicaHospitalario=" + aplicaHospitalario + ", aplicaUnidadMovil=" + aplicaUnidadMovil + ", aplicaOtrasExtramural=" + aplicaOtrasExtramural + ", aplicaCentroReferencia=" + aplicaCentroReferencia + ", aplicaInstitucionRemisora=" + aplicaInstitucionRemisora + ", aplicaComplejidadBaja=" + aplicaComplejidadBaja + ", aplicaComplejidadMedia=" + aplicaComplejidadMedia + ", aplicaComplejidadAlta=" + aplicaComplejidadAlta + ", fechaApertura=" + fechaApertura + ", cntPrestadorSede=" + cntPrestadorSede + ", maServicioHabilitacion=" + maServicioHabilitacion + '}';
    }

    /**
     * @return the aplicaIntramural
     */
    public boolean isAplicaIntramural() {
        return aplicaIntramural;
    }

    /**
     * @param aplicaIntramural the aplicaIntramural to set
     */
    public void setAplicaIntramural(boolean aplicaIntramural) {
        this.aplicaIntramural = aplicaIntramural;
    }

    /**
     * @return the aplicaJornadaSalud
     */
    public boolean isAplicaJornadaSalud() {
        return aplicaJornadaSalud;
    }

    /**
     * @param aplicaJornadaSalud the aplicaJornadaSalud to set
     */
    public void setAplicaJornadaSalud(boolean aplicaJornadaSalud) {
        this.aplicaJornadaSalud = aplicaJornadaSalud;
    }

    /**
     * @return the aplicaTelemedicina
     */
    public boolean isAplicaTelemedicina() {
        return aplicaTelemedicina;
    }

    /**
     * @param aplicaTelemedicina the aplicaTelemedicina to set
     */
    public void setAplicaTelemedicina(boolean aplicaTelemedicina) {
        this.aplicaTelemedicina = aplicaTelemedicina;
    }

    /**
     * @return the aplicaRefTelemedInteractiva
     */
    public boolean isAplicaRefTelemedInteractiva() {
        return aplicaRefTelemedInteractiva;
    }

    /**
     * @param aplicaRefTelemedInteractiva the aplicaRefTelemedInteractiva to set
     */
    public void setAplicaRefTelemedInteractiva(boolean aplicaRefTelemedInteractiva) {
        this.aplicaRefTelemedInteractiva = aplicaRefTelemedInteractiva;
    }

    /**
     * @return the aplicaRefTelemedNoInteractiva
     */
    public boolean isAplicaRefTelemedNoInteractiva() {
        return aplicaRefTelemedNoInteractiva;
    }

    /**
     * @param aplicaRefTelemedNoInteractiva the aplicaRefTelemedNoInteractiva to set
     */
    public void setAplicaRefTelemedNoInteractiva(boolean aplicaRefTelemedNoInteractiva) {
        this.aplicaRefTelemedNoInteractiva = aplicaRefTelemedNoInteractiva;
    }

    /**
     * @return the aplicaReferenciaTeleExperticia
     */
    public boolean isAplicaReferenciaTeleExperticia() {
        return aplicaReferenciaTeleExperticia;
    }

    /**
     * @param aplicaReferenciaTeleExperticia the aplicaReferenciaTeleExperticia to set
     */
    public void setAplicaReferenciaTeleExperticia(boolean aplicaReferenciaTeleExperticia) {
        this.aplicaReferenciaTeleExperticia = aplicaReferenciaTeleExperticia;
    }

    /**
     * @return the aplicaReferenciaTeleMonitoreo
     */
    public boolean isAplicaReferenciaTeleMonitoreo() {
        return aplicaReferenciaTeleMonitoreo;
    }

    /**
     * @param aplicaReferenciaTeleMonitoreo the aplicaReferenciaTeleMonitoreo to set
     */
    public void setAplicaReferenciaTeleMonitoreo(boolean aplicaReferenciaTeleMonitoreo) {
        this.aplicaReferenciaTeleMonitoreo = aplicaReferenciaTeleMonitoreo;
    }

    /**
     * @return the aplicapRemisorTeleExperticia
     */
    public boolean isAplicapRemisorTeleExperticia() {
        return aplicapRemisorTeleExperticia;
    }

    /**
     * @param aplicapRemisorTeleExperticia the aplicapRemisorTeleExperticia to set
     */
    public void setAplicapRemisorTeleExperticia(boolean aplicapRemisorTeleExperticia) {
        this.aplicapRemisorTeleExperticia = aplicapRemisorTeleExperticia;
    }

    /**
     * @return the aplicaRemisorTeleMonitoreo
     */
    public boolean isAplicaRemisorTeleMonitoreo() {
        return aplicaRemisorTeleMonitoreo;
    }

    /**
     * @param aplicaRemisorTeleMonitoreo the aplicaRemisorTeleMonitoreo to set
     */
    public void setAplicaRemisorTeleMonitoreo(boolean aplicaRemisorTeleMonitoreo) {
        this.aplicaRemisorTeleMonitoreo = aplicaRemisorTeleMonitoreo;
    }

    /**
     * @return the aplicaSinComplejidad
     */
    public boolean isAplicaSinComplejidad() {
        return aplicaSinComplejidad;
    }

    /**
     * @param aplicaSinComplejidad the aplicaSinComplejidad to set
     */
    public void setAplicaSinComplejidad(boolean aplicaSinComplejidad) {
        this.aplicaSinComplejidad = aplicaSinComplejidad;
    }

    /**
     * @return the aplicaTrasplanteOsteomuscular
     */
    public boolean isAplicaTrasplanteOsteomuscular() {
        return aplicaTrasplanteOsteomuscular;
    }

    /**
     * @param aplicaTrasplanteOsteomuscular the aplicaTrasplanteOsteomuscular to set
     */
    public void setAplicaTrasplanteOsteomuscular(boolean aplicaTrasplanteOsteomuscular) {
        this.aplicaTrasplanteOsteomuscular = aplicaTrasplanteOsteomuscular;
    }

    /**
     * @return the aplicaTrasplantePiel
     */
    public boolean isAplicaTrasplantePiel() {
        return aplicaTrasplantePiel;
    }

    /**
     * @param aplicaTrasplantePiel the aplicaTrasplantePiel to set
     */
    public void setAplicaTrasplantePiel(boolean aplicaTrasplantePiel) {
        this.aplicaTrasplantePiel = aplicaTrasplantePiel;
    }

    /**
     * @return the aplicaTrasplanteCardiovascular
     */
    public boolean isAplicaTrasplanteCardiovascular() {
        return aplicaTrasplanteCardiovascular;
    }

    /**
     * @param aplicaTrasplanteCardiovascular the aplicaTrasplanteCardiovascular to set
     */
    public void setAplicaTrasplanteCardiovascular(boolean aplicaTrasplanteCardiovascular) {
        this.aplicaTrasplanteCardiovascular = aplicaTrasplanteCardiovascular;
    }

    /**
     * @return the aplicaTrasplanteTejidoOcular
     */
    public boolean isAplicaTrasplanteTejidoOcular() {
        return aplicaTrasplanteTejidoOcular;
    }

    /**
     * @param aplicaTrasplanteTejidoOcular the aplicaTrasplanteTejidoOcular to set
     */
    public void setAplicaTrasplanteTejidoOcular(boolean aplicaTrasplanteTejidoOcular) {
        this.aplicaTrasplanteTejidoOcular = aplicaTrasplanteTejidoOcular;
    }

    /**
     * @return the aplicaAtencionPacienteQuemado
     */
    public boolean isAplicaAtencionPacienteQuemado() {
        return aplicaAtencionPacienteQuemado;
    }

    /**
     * @param aplicaAtencionPacienteQuemado the aplicaAtencionPacienteQuemado to set
     */
    public void setAplicaAtencionPacienteQuemado(boolean aplicaAtencionPacienteQuemado) {
        this.aplicaAtencionPacienteQuemado = aplicaAtencionPacienteQuemado;
    }

    /**
     * @return the aplicaSaludMental
     */
    public boolean isAplicaSaludMental() {
        return aplicaSaludMental;
    }

    /**
     * @param aplicaSaludMental the aplicaSaludMental to set
     */
    public void setAplicaSaludMental(boolean aplicaSaludMental) {
        this.aplicaSaludMental = aplicaSaludMental;
    }

    /**
     * @return the aplicaSpa
     */
    public boolean isAplicaSpa() {
        return aplicaSpa;
    }

    /**
     * @param aplicaSpa the aplicaSpa to set
     */
    public void setAplicaSpa(boolean aplicaSpa) {
        this.aplicaSpa = aplicaSpa;
    }

    /**
     * @return the aplicaOtrasPatologias
     */
    public boolean isAplicaOtrasPatologias() {
        return aplicaOtrasPatologias;
    }

    /**
     * @param aplicaOtrasPatologias the aplicaOtrasPatologias to set
     */
    public void setAplicaOtrasPatologias(boolean aplicaOtrasPatologias) {
        this.aplicaOtrasPatologias = aplicaOtrasPatologias;
    }

    /**
     * @return the aplicaTxCelProgeHematopoy
     */
    public boolean isAplicaTxCelProgeHematopoy() {
        return aplicaTxCelProgeHematopoy;
    }

    /**
     * @param aplicaTxCelProgeHematopoy the aplicaTxCelProgeHematopoy to set
     */
    public void setAplicaTxCelProgeHematopoy(boolean aplicaTxCelProgeHematopoy) {
        this.aplicaTxCelProgeHematopoy = aplicaTxCelProgeHematopoy;
    }

    /**
     * @return the aplicaProcQuirurAmbulatorios
     */
    public boolean isAplicaProcQuirurAmbulatorios() {
        return aplicaProcQuirurAmbulatorios;
    }

    /**
     * @param aplicaProcQuirurAmbulatorios the aplicaProcQuirurAmbulatorios to set
     */
    public void setAplicaProcQuirurAmbulatorios(boolean aplicaProcQuirurAmbulatorios) {
        this.aplicaProcQuirurAmbulatorios = aplicaProcQuirurAmbulatorios;
    }

    /**
     * @return the aplicaOrganoRinon
     */
    public boolean isAplicaOrganoRinon() {
        return aplicaOrganoRinon;
    }

    /**
     * @param aplicaOrganoRinon the aplicaOrganoRinon to set
     */
    public void setAplicaOrganoRinon(boolean aplicaOrganoRinon) {
        this.aplicaOrganoRinon = aplicaOrganoRinon;
    }

    /**
     * @return the aplicaOrganoHigado
     */
    public boolean isAplicaOrganoHigado() {
        return aplicaOrganoHigado;
    }

    /**
     * @param aplicaOrganoHigado the aplicaOrganoHigado to set
     */
    public void setAplicaOrganoHigado(boolean aplicaOrganoHigado) {
        this.aplicaOrganoHigado = aplicaOrganoHigado;
    }

    /**
     * @return the aplicaOrganoPancreas
     */
    public boolean isAplicaOrganoPancreas() {
        return aplicaOrganoPancreas;
    }

    /**
     * @param aplicaOrganoPancreas the aplicaOrganoPancreas to set
     */
    public void setAplicaOrganoPancreas(boolean aplicaOrganoPancreas) {
        this.aplicaOrganoPancreas = aplicaOrganoPancreas;
    }

    /**
     * @return the aplicaOrganoIntestino
     */
    public boolean isAplicaOrganoIntestino() {
        return aplicaOrganoIntestino;
    }

    /**
     * @param aplicaOrganoIntestino the aplicaOrganoIntestino to set
     */
    public void setAplicaOrganoIntestino(boolean aplicaOrganoIntestino) {
        this.aplicaOrganoIntestino = aplicaOrganoIntestino;
    }

    /**
     * @return the aplicaOrganoMultivisceral
     */
    public boolean isAplicaOrganoMultivisceral() {
        return aplicaOrganoMultivisceral;
    }

    /**
     * @param aplicaOrganoMultivisceral the aplicaOrganoMultivisceral to set
     */
    public void setAplicaOrganoMultivisceral(boolean aplicaOrganoMultivisceral) {
        this.aplicaOrganoMultivisceral = aplicaOrganoMultivisceral;
    }

    /**
     * @return the aplicaOrganoCorazon
     */
    public boolean isAplicaOrganoCorazon() {
        return aplicaOrganoCorazon;
    }

    /**
     * @param aplicaOrganoCorazon the aplicaOrganoCorazon to set
     */
    public void setAplicaOrganoCorazon(boolean aplicaOrganoCorazon) {
        this.aplicaOrganoCorazon = aplicaOrganoCorazon;
    }

    /**
     * @return the aplicaOrganoPulmon
     */
    public boolean isAplicaOrganoPulmon() {
        return aplicaOrganoPulmon;
    }

    /**
     * @param aplicaOrganoPulmon the aplicaOrganoPulmon to set
     */
    public void setAplicaOrganoPulmon(boolean aplicaOrganoPulmon) {
        this.aplicaOrganoPulmon = aplicaOrganoPulmon;
    }

    /**
     * @return the aplicaSustanciasPsicoactivas
     */
    public boolean isAplicaSustanciasPsicoactivas() {
        return aplicaSustanciasPsicoactivas;
    }

    /**
     * @param aplicaSustanciasPsicoactivas the aplicaSustanciasPsicoactivas to set
     */
    public void setAplicaSustanciasPsicoactivas(boolean aplicaSustanciasPsicoactivas) {
        this.aplicaSustanciasPsicoactivas = aplicaSustanciasPsicoactivas;
    }

    /**
     * @return the aplicaTrasplanteRenal
     */
    public boolean isAplicaTrasplanteRenal() {
        return aplicaTrasplanteRenal;
    }

    /**
     * @param aplicaTrasplanteRenal the aplicaTrasplanteRenal to set
     */
    public void setAplicaTrasplanteRenal(boolean aplicaTrasplanteRenal) {
        this.aplicaTrasplanteRenal = aplicaTrasplanteRenal;
    }

    /**
     * @return the errorCarga
     */
    public String getErrorCarga() {
        return errorCarga;
    }

    /**
     * @param errorCarga the errorCarga to set
     */
    public void setErrorCarga(String errorCarga) {
        this.errorCarga = errorCarga;
    }

    /**
     * @return the actualizar
     */
    public boolean isActualizar() {
        return actualizar;
    }

    /**
     * @param actualizar the actualizar to set
     */
    public void setActualizar(boolean actualizar) {
        this.actualizar = actualizar;
    }

    /**
     * @return the registroArchivo
     */
    public String getRegistroArchivo() {
        return registroArchivo;
    }

    /**
     * @param registroArchivo the registroArchivo to set
     */
    public void setRegistroArchivo(String registroArchivo) {
        this.registroArchivo = registroArchivo;
    }

    
}
