/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.crue;

import com.saviasaludeps.savia.dominio.administracion.Empresa;
import com.saviasaludeps.savia.dominio.contratacion.CntProfesional;
import com.saviasaludeps.savia.dominio.aseguramiento.AsegAfiliado;
import com.saviasaludeps.savia.dominio.autorizacion.AuSolicitudAdjunto;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestadorSede;
import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 *
 * @author AlexanderDiaz
 */
public class AuAnexo2 extends Auditoria {

    public final static String URGENCIA_MEDICA = "890701";
    public final static String URGENCIA_ODONTOLOGICA = "890703";

    public final static String SERVICIO_URGENCIAS = "URGENCIAS POR MEDICINA GENERAL";
    public final static String SERVICIO_URGENCIAS_ODONTOLOGIA = "ODONTOLOG";

    public final static int TIPO_URGENCIA = 0;
    public final static int TIPO_ODONTOLOGICA = 1;

    public final static int ESTADO_PENDIENTE_AUDITORIA = 1;
    public final static int ESTADO_RECHAZADA_AUDITORIA = 2;
    public final static int ESTADO_AUTORIZADA = 3;
    public final static int ESTADO_ANULADA = 4;
    public final static int ESTADO_AUTORIZADA_AUTOMATICA = 5;

    public final static int FUENTE_ORIGEN_MANUAL = 1;
    public final static int FUENTE_ORIGEN_CARGA_MASIVA = 2;
    public final static int FUENTE_ORIGEN_IVR = 3;
    public final static int FUENTE_ORIGEN_WEBSERVICE = 4;
    
    public static final int VERSION_0 = 0;
    public static final int VERSION_1 = 1;
    
    // ley 2335
    public static final String LABEL_ORIGEN_ATENCION = "Origen";
    public static final String LABEL_CAUSA_MOTIVA_ATENCION = "Causa que motiva la atención";
    
    public static final String LABEL_DESTINO = "Destino";
    public static final String LABEL_CONDICION_DESTINO_PERSONA = "Condicion y destino de la persona";

    private Integer id;

    private Empresa gnEmpresa;
    private Integer estado;
    private Integer maeEstadoId;
    private String maeEstadoCodigo;
    private String maeEstadoValor;

    private Integer maeOrigenAtencionId;
    private String maeOrigenAtencionCodigo;
    private String maeOrigenAtencionValor;
    private Integer maeViaIngresoId;
    private String maeViaIngresoCodigo;
    private String maeViaIngresoValor;
    private Integer maeDestinoPacienteId;
    private Integer maeCondicionDestinoId;
    private String maeCondicionDestinoCodigo;
    private String maeCondicionDestinoValor;
    private String maeDestinoPacienteCodigo;
    private String maeDestinoPacienteValor;
    private String codigoAtencionIps;
    private Date fechaHoraAtencion;
    private Date fechaHoraReporte;
    private String motivo;
    private boolean remitido;
    private String remiteNit;
    private String remiteNombre;
    private Integer triage;
    private String informaNombre;
    private String informaCargo;
    private String informaTelefono;
    private Integer fuenteOrigen;
    private Integer version;
    private String consecutivo;
    private AsegAfiliado asegAfiliado;
    private CntPrestadorSede cntPrestadorSede;
    private CntProfesional cntProfesionales;
    private boolean newProfesional;
    private String direccionAlternativa;

    private List<AuAnexo2Gestion> listaAuAnexo2Gestion;
    private List<AuAnexo2Diagnostico> listaAuAnexo2Diagnostico;
    private List<AuAnexo2Estado> listaAuAnexo2Estado;
    private List<AuAnexo2DatoAtencion> listaAuAnexo2DatosAtencion;
    private List<AuAnexo2Adjunto> listaAuAnexo2Adjunto;
    private List<AuAnexo2Item> listaAuAnexo2Item;
    private List<AuSolicitudAdjunto> listaAuSolicitudAdjunto;
    private List<AuAnexo2Rescate> listaAuAnexo2Rescate;

    private String accionEvento;

    private String comentarioEstado;

    private Integer tipo;
    
    //variable auxliares para la ley 2335
    private boolean habilitarCampoViaIngresoPersonaServicioSalud;
    private boolean habilitarCampoDireccionAlternativa;
    private String labelOrigenAtencion;
    private String labelDestino;

    public AuAnexo2() {
    }

    public AuAnexo2(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Empresa getGnEmpresa() {
        return gnEmpresa;
    }

    public void setGnEmpresa(Empresa gnEmpresa) {
        this.gnEmpresa = gnEmpresa;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public Integer getMaeEstadoId() {
        return maeEstadoId;
    }

    public String getDireccionAlternativa() {
        return direccionAlternativa;
    }

    public void setDireccionAlternativa(String direccionAlternativa) {
        this.direccionAlternativa = direccionAlternativa;
    }

    public void setMaeEstadoId(Integer maeEstadoId) {
        this.maeEstadoId = maeEstadoId;
    }

    public String getMaeEstadoCodigo() {
        return maeEstadoCodigo;
    }

    public void setMaeEstadoCodigo(String maeEstadoCodigo) {
        this.maeEstadoCodigo = maeEstadoCodigo;
    }

    public String getMaeEstadoValor() {
        return maeEstadoValor;
    }

    public String getConsecutivo() {
        return consecutivo;
    }

    public void setConsecutivo(String consecutivo) {
        this.consecutivo = consecutivo;
    }

    public void setMaeEstadoValor(String maeEstadoValor) {
        this.maeEstadoValor = maeEstadoValor;
    }

    public Integer getMaeOrigenAtencionId() {
        return maeOrigenAtencionId;
    }

    public void setMaeOrigenAtencionId(Integer maeOrigenAtencionId) {
        this.maeOrigenAtencionId = maeOrigenAtencionId;
    }

    public String getMaeOrigenAtencionCodigo() {
        return maeOrigenAtencionCodigo;
    }

    public void setMaeOrigenAtencionCodigo(String maeOrigenAtencionCodigo) {
        this.maeOrigenAtencionCodigo = maeOrigenAtencionCodigo;
    }

    public String getMaeOrigenAtencionValor() {
        return maeOrigenAtencionValor;
    }

    public void setMaeOrigenAtencionValor(String maeOrigenAtencionValor) {
        this.maeOrigenAtencionValor = maeOrigenAtencionValor;
    }

    public Integer getMaeViaIngresoId() {
        return maeViaIngresoId;
    }

    public void setMaeViaIngresoId(Integer maeViaIngresoId) {
        this.maeViaIngresoId = maeViaIngresoId;
    }

    public String getMaeViaIngresoCodigo() {
        return maeViaIngresoCodigo;
    }

    public void setMaeViaIngresoCodigo(String maeViaIngresoCodigo) {
        this.maeViaIngresoCodigo = maeViaIngresoCodigo;
    }

    public String getMaeViaIngresoValor() {
        return maeViaIngresoValor;
    }

    public void setMaeViaIngresoValor(String maeViaIngresoValor) {
        this.maeViaIngresoValor = maeViaIngresoValor;
    }

    public Integer getMaeCondicionDestinoId() {
        return maeCondicionDestinoId;
    }

    public void setMaeCondicionDestinoId(Integer maeCondicionDestinoId) {
        this.maeCondicionDestinoId = maeCondicionDestinoId;
    }

    public String getMaeCondicionDestinoCodigo() {
        return maeCondicionDestinoCodigo;
    }

    public void setMaeCondicionDestinoCodigo(String maeCondicionDestinoCodigo) {
        this.maeCondicionDestinoCodigo = maeCondicionDestinoCodigo;
    }

    public String getMaeCondicionDestinoValor() {
        return maeCondicionDestinoValor;
    }

    public void setMaeCondicionDestinoValor(String maeCondicionDestinoValor) {
        this.maeCondicionDestinoValor = maeCondicionDestinoValor;
    }

    public Integer getMaeDestinoPacienteId() {
        return maeDestinoPacienteId;
    }

    public void setMaeDestinoPacienteId(Integer maeDestinoPacienteId) {
        this.maeDestinoPacienteId = maeDestinoPacienteId;
    }

    public String getMaeDestinoPacienteCodigo() {
        return maeDestinoPacienteCodigo;
    }

    public void setMaeDestinoPacienteCodigo(String maeDestinoPacienteCodigo) {
        this.maeDestinoPacienteCodigo = maeDestinoPacienteCodigo;
    }

    public String getMaeDestinoPacienteValor() {
        return maeDestinoPacienteValor;
    }

    public void setMaeDestinoPacienteValor(String maeDestinoPacienteValor) {
        this.maeDestinoPacienteValor = maeDestinoPacienteValor;
    }

    public String getCodigoAtencionIps() {
        return codigoAtencionIps;
    }

    public void setCodigoAtencionIps(String codigoAtencionIps) {
        this.codigoAtencionIps = codigoAtencionIps;
    }

    public Date getFechaHoraAtencion() {
        return fechaHoraAtencion;
    }

    public void setFechaHoraAtencion(Date fechaHoraAtencion) {
        this.fechaHoraAtencion = fechaHoraAtencion;
    }

    public Date getFechaHoraReporte() {
        return fechaHoraReporte;
    }

    public void setFechaHoraReporte(Date fechaHoraReporte) {
        this.fechaHoraReporte = fechaHoraReporte;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public boolean isRemitido() {
        return remitido;
    }

    public void setRemitido(boolean remitido) {
        this.remitido = remitido;
    }

    public String getRemiteNit() {
        return remiteNit;
    }

    public void setRemiteNit(String remiteNit) {
        this.remiteNit = remiteNit;
    }

    public String getRemiteNombre() {
        return remiteNombre;
    }

    public void setRemiteNombre(String remiteNombre) {
        this.remiteNombre = remiteNombre;
    }

    public Integer getTriage() {
        return triage;
    }

    public void setTriage(Integer triage) {
        this.triage = triage;
    }

    public String getInformaNombre() {
        return informaNombre;
    }

    public void setInformaNombre(String informaNombre) {
        this.informaNombre = informaNombre;
    }

    public String getInformaCargo() {
        return informaCargo;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public void setInformaCargo(String informaCargo) {
        this.informaCargo = informaCargo;
    }

    public String getInformaTelefono() {
        return informaTelefono;
    }

    public void setInformaTelefono(String informaTelefono) {
        this.informaTelefono = informaTelefono;
    }

    public AsegAfiliado getAsegAfiliado() {
        return asegAfiliado;
    }

    public void setAsegAfiliado(AsegAfiliado asegAfiliado) {
        this.asegAfiliado = asegAfiliado;
    }

    public CntPrestadorSede getCntPrestadorSede() {
        return cntPrestadorSede;
    }

    public void setCntPrestadorSede(CntPrestadorSede cntPrestadorSede) {
        this.cntPrestadorSede = cntPrestadorSede;
    }

    public CntProfesional getCntProfesionales() {
        return cntProfesionales;
    }

    public void setCntProfesionales(CntProfesional cntProfesionales) {
        this.cntProfesionales = cntProfesionales;
    }

    public List<AuAnexo2Gestion> getListaAuAnexo2Gestion() {
        return listaAuAnexo2Gestion;
    }

    public void setListaAuAnexo2Gestion(List<AuAnexo2Gestion> listaAuAnexo2Gestion) {
        this.listaAuAnexo2Gestion = listaAuAnexo2Gestion;
    }

    public List<AuAnexo2Diagnostico> getListaAuAnexo2Diagnostico() {
        return listaAuAnexo2Diagnostico;
    }

    public void setListaAuAnexo2Diagnostico(List<AuAnexo2Diagnostico> listaAuAnexo2Diagnostico) {
        this.listaAuAnexo2Diagnostico = listaAuAnexo2Diagnostico;
    }

    public List<AuAnexo2Item> getListaAuAnexo2Item() {
        return listaAuAnexo2Item;
    }

    public void setListaAuAnexo2Item(List<AuAnexo2Item> listaAuAnexo2Item) {
        this.listaAuAnexo2Item = listaAuAnexo2Item;
    }

    public List<AuAnexo2Estado> getListaAuAnexo2Estado() {
        return listaAuAnexo2Estado;
    }

    public void setListaAuAnexo2Estado(List<AuAnexo2Estado> listaAuAnexo2Estado) {
        this.listaAuAnexo2Estado = listaAuAnexo2Estado;
    }

    public List<AuAnexo2DatoAtencion> getListaAuAnexo2DatosAtencion() {
        return listaAuAnexo2DatosAtencion;
    }

    public void setListaAuAnexo2DatosAtencion(List<AuAnexo2DatoAtencion> listaAuAnexo2DatosAtencion) {
        this.listaAuAnexo2DatosAtencion = listaAuAnexo2DatosAtencion;
    }

    public List<AuAnexo2Adjunto> getListaAuAnexo2Adjunto() {
        return listaAuAnexo2Adjunto;
    }

    public void setListaAuAnexo2Adjunto(List<AuAnexo2Adjunto> listaAuAnexo2Adjunto) {
        this.listaAuAnexo2Adjunto = listaAuAnexo2Adjunto;
    }

    public int getTiempoCrea() {
        long ini = getFechaHoraCrea().getTime();
        long fin = (new Date()).getTime();
        return (int) ((fin - ini) / 60000);
    }

    public String getAccionEvento() {
        return accionEvento;
    }

    public void setAccionEvento(String accionEvento) {
        this.accionEvento = accionEvento;
    }

    public Integer getTipo() {
        return tipo;
    }

    public void setTipo(Integer tipo) {
        this.tipo = tipo;
    }

    public String getComentarioEstado() {
        return comentarioEstado;
    }

    public void setComentarioEstado(String comentarioEstado) {
        this.comentarioEstado = comentarioEstado;
    }

    public boolean isNewProfesional() {
        return newProfesional;
    }

    public void setNewProfesional(boolean newProfesional) {
        this.newProfesional = newProfesional;
    }

    public List<AuSolicitudAdjunto> getListaAuSolicitudAdjunto() {
        return listaAuSolicitudAdjunto;
    }

    public void setListaAuSolicitudAdjunto(List<AuSolicitudAdjunto> listaAuSolicitudAdjunto) {
        this.listaAuSolicitudAdjunto = listaAuSolicitudAdjunto;
    }

    public Integer getFuenteOrigen() {
        return fuenteOrigen;
    }

    public void setFuenteOrigen(Integer fuenteOrigen) {
        this.fuenteOrigen = fuenteOrigen;
    }

    public List<AuAnexo2Rescate> getListaAuAnexo2Rescate() {
        return listaAuAnexo2Rescate;
    }

    public void setListaAuAnexo2Rescate(List<AuAnexo2Rescate> listaAuAnexo2Rescate) {
        this.listaAuAnexo2Rescate = listaAuAnexo2Rescate;
    }

    public boolean isHabilitarCampoViaIngresoPersonaServicioSalud() {
        return habilitarCampoViaIngresoPersonaServicioSalud;
    }

    public void setHabilitarCampoViaIngresoPersonaServicioSalud(boolean habilitarCampoViaIngresoPersonaServicioSalud) {
        this.habilitarCampoViaIngresoPersonaServicioSalud = habilitarCampoViaIngresoPersonaServicioSalud;
    }

    public boolean isHabilitarCampoDireccionAlternativa() {
        return habilitarCampoDireccionAlternativa;
    }

    public void setHabilitarCampoDireccionAlternativa(boolean habilitarCampoDireccionAlternativa) {
        this.habilitarCampoDireccionAlternativa = habilitarCampoDireccionAlternativa;
    }

    public String getLabelDestino() {
        return labelDestino;
    }

    public void setLabelDestino(String labelDestino) {
        this.labelDestino = labelDestino;
    }

    public String getLabelOrigenAtencion() {
        return labelOrigenAtencion;
    }

    public void setLabelOrigenAtencion(String labelOrigenAtencion) {
        this.labelOrigenAtencion = labelOrigenAtencion;
    }

    public String getEstadoStr() {
        String srt;
        switch (this.estado) {
            case ESTADO_PENDIENTE_AUDITORIA:
                srt = "Pendiente Auditoría";
                break;
            case ESTADO_RECHAZADA_AUDITORIA:
                srt = "Rechazada Auditoría";
                break;
            case ESTADO_AUTORIZADA:
                srt = "Autorizada";
                break;
            case ESTADO_ANULADA:
                srt = "Anulada";
                break;
            case ESTADO_AUTORIZADA_AUTOMATICA:
                srt = "Autorizada Automática";
                break;
            default:
                srt = "";
                break;
        }
        return srt;
    }

    public String getFuenteOrigenStr() {
        String srt;
        switch (this.fuenteOrigen) {
            case FUENTE_ORIGEN_MANUAL:
                srt = "Manual";
                break;
            case FUENTE_ORIGEN_CARGA_MASIVA:
                srt = "Carga Masiva";
                break;
            case FUENTE_ORIGEN_IVR:
                srt = "IVR";
                break;
            default:
                srt = "";
                break;
        }
        return srt;
    }

    public String getTipoStr() {
        String srt;
        switch (this.tipo) {
            case TIPO_URGENCIA:
                srt = "Urgencia Medica";
                break;
            case TIPO_ODONTOLOGICA:
                srt = "Urgencia Odotologica";
                break;
            default:
                srt = "";
                break;
        }
        return srt;
    }

    public String getTriageStr() {
        String ruta;
        switch (triage) {
            case 0:
                ruta = "Triage IVR";
                break;
            case 1:
                ruta = "Triage Nivel 1";
                break;
            case 2:
                ruta = "Triage Nivel 2";
                break;
            case 3:
                ruta = "Triage Nivel 3";
                break;
            case 4:
                ruta = "Triage Nivel 4";
                break;
            case 5:
                ruta = "Triage Nivel 5";
                break;
            default:
                ruta = "";
                break;
        }

        return ruta;
    }
    
    public String getConsecutivoGen() {
        SimpleDateFormat formato = new SimpleDateFormat("yyyyMMdd");
        StringBuilder consecutivoStr = new StringBuilder();
        formato.format(fechaHoraCrea);
        consecutivoStr.append(formato.format(fechaHoraCrea).replace("-", "")).append(id);
        return consecutivoStr.toString();
    }
    
    @Override
    public String toString() {
        return "AuAnexo2{" + "id=" + id + ", gnEmpresa=" + (gnEmpresa != null ? gnEmpresa.getId() : gnEmpresa) + ", maeEstadoId=" + maeEstadoId + ", maeEstadoCodigo=" + maeEstadoCodigo + ", maeEstadoValor=" + maeEstadoValor + ", maeOrigenAtencionId=" + maeOrigenAtencionId + ", maeOrigenAtencionCodigo=" + maeOrigenAtencionCodigo + ", maeOrigenAtencionValor=" + maeOrigenAtencionValor + ", maeDestinoPacienteId=" + maeDestinoPacienteId + ", maeDestinoPacienteCodigo=" + maeDestinoPacienteCodigo + ", maeDestinoPacienteValor=" + maeDestinoPacienteValor + ", codigoAtencionIps=" + codigoAtencionIps + ", fechaHoraAtencion=" + fechaHoraAtencion + ", fechaHoraReporte=" + fechaHoraReporte + ", motivo=" + motivo + ", remitido=" + remitido + ", remiteNit=" + remiteNit + ", remiteNombre=" + remiteNombre + ", triage=" + triage + ", informaNombre=" + informaNombre + ", informaCargo=" + informaCargo + ", informaTelefono=" + informaTelefono + ", asegAfiliado=" + (asegAfiliado != null ? asegAfiliado.getId() : asegAfiliado) + ", cntPrestadorSede=" + (cntPrestadorSede != null ? cntPrestadorSede.getId() : cntPrestadorSede) + ", cntProfesionales=" + (cntProfesionales != null ? cntProfesionales.getId() : cntProfesionales) + ", listaAuAnexo2Diagnostico=" + listaAuAnexo2Diagnostico + ", comentarioEstado=" + comentarioEstado + ", tipo=" + tipo + '}';
    }

}
