/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.tutela;

import com.saviasaludeps.savia.dominio.administracion.Usuario;
import com.saviasaludeps.savia.dominio.autorizacion.AuAnexo4;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestadorSede;
import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author pavacca
 */
public class TuTutelaItem extends Auditoria{
    
    public static String ESTADO_ITEM_NO_ASIGNADO = "0";
    public static String ESTADO_ITEM_ASIGNADO = "1";
    public static String ESTADO_ITEM_GESTION = "2";
    public static String ESTADO_ITEM_CERRADO = "3";
    public static String ESTADO_ITEM_RECHAZADO = "4";
    
    private Integer id;
    private int cantidad;
    private Integer maeCausaTutelaId;
    private String maeCausaTutelaCodigo;
    private String maeCausaTutelaValor;
    private Date fechaEnvio;
    private Date fechaCita;
    private Date fechaRespuesta;
    private Date fechaCumplimiento;
    private String correoDestinatario;
    private Integer tipoServicioId;
    private CntPrestadorSede destinoCntPrestadorSedeId;
    private String destinoCntPrestadorSedeNombre;
    private Boolean notificadaAIps;
    private Boolean respuestaIps;
    private Integer parametroIpsId;
    private CntPrestadorSede prescripcionCntPrestadorSedeId;
    private String prescripcionCntPrestadorSedeNombre;
    private Integer tipoServicio;
    private Integer maTecnologiaId;
    private String maTecnologiaCodigo;
    private String maTecnologiaValor;
    private Integer maePresentacionId;
    private String maePresentacionCodigo;
    private String maePresentacionValor;
    private Integer maeTipoPrestacionId;
    private String maeTipoPrestacionCodigo;
    private String maeTipoPrestacionValor;
    private String observacion;
    private String observacionIps;
    private Boolean solicitarFechaCita;
    private Integer consultarAutorizacion;
    private int pos;
    //2024-05-30 jyperez nuevos campos
    private Integer maeTipoServicioId;
    private String maeTipoServicioCodigo;
    private String maeTipoServicioValor;
    private TuPersona tuPersonasId;
    private TuTutelaEstado tuTutelaEstadosId;
    private TuTutela tuTutelasId;
    //2024-09-23 jyperez nuevos campos
    private boolean aplicaDestino;
    //2025-06-27 jyperez nuevos campos
    private Integer maeTipoAsignacionId;
    private String maeTipoAsignacionCodigo;
    private String maeTipoAsignacionValor;
    private Integer maeEstadoItemId;
    private String maeEstadoItemCodigo;
    private String maeEstadoItemValor;
    private Usuario gnUsuarioAsignadoId;
    private boolean aplicaAsignacion;
    private List<TuTutelaItemGestion> tuTutelaItemGestionesList;
    
    private List<TuDiagnostico> tuTutelaDiagnosticosId;
    private List<AuAnexo4> registrosAuditoriaAutorizacion;
    private List<TuAdjunto> tuAdjuntosList;
    
    public TuTutelaItem(){
        
    }
    
    public TuTutelaItem(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        String tutelaEstadoId = "null";
        String tutelaId = "null";
        String personaId = "null";
        if (tuTutelaEstadosId != null) {
            tutelaEstadoId = tuTutelaEstadosId.getId().toString();
        }
        if (tuTutelasId != null) {
            tutelaId = tuTutelasId.getId().toString();
        }
        if (tuPersonasId != null) {
            personaId = tuPersonasId.getId().toString();
        }
        return "TuTutelaItem{" + "id=" + id + ", cantidad=" + cantidad + ", maeCausaTutelaId=" + maeCausaTutelaId + ", maeCausaTutelaCodigo=" + maeCausaTutelaCodigo + ", maeCausaTutelaValor=" + maeCausaTutelaValor + ", fechaEnvio=" + fechaEnvio + ", fechaCita=" + fechaCita + ", fechaRespuesta=" + fechaRespuesta + ", fechaCumplimiento=" + fechaCumplimiento + ", correoDestinatario=" + correoDestinatario + ", tipoServicioId=" + tipoServicioId + ", destinoCntPrestadorSedeId=" + destinoCntPrestadorSedeId + ", destinoCntPrestadorSedeNombre=" + destinoCntPrestadorSedeNombre + ", notificadaAIps=" + notificadaAIps + ", respuestaIps=" + respuestaIps + ", parametroIpsId=" + parametroIpsId + ", prescripcionCntPrestadorSedeId=" + prescripcionCntPrestadorSedeId + ", prescripcionCntPrestadorSedeNombre=" + prescripcionCntPrestadorSedeNombre + ", tipoServicio=" + tipoServicio + ", maTecnologiaId=" + maTecnologiaId + ", maTecnologiaCodigo=" + maTecnologiaCodigo + ", maTecnologiaValor=" + maTecnologiaValor + ", maePresentacionId=" + maePresentacionId + ", maePresentacionCodigo=" + maePresentacionCodigo + ", maePresentacionValor=" + maePresentacionValor + ", maeTipoPrestacionId=" + maeTipoPrestacionId + ", maeTipoPrestacionCodigo=" + maeTipoPrestacionCodigo + ", maeTipoPrestacionValor=" + maeTipoPrestacionValor + ", observacion=" + observacion + ", observacionIps=" + observacionIps + ", solicitarFechaCita=" + solicitarFechaCita + ", consultarAutorizacion=" + consultarAutorizacion + ", pos=" + pos + ", maeTipoServicioId=" + maeTipoServicioId + ", maeTipoServicioCodigo=" + maeTipoServicioCodigo + ", maeTipoServicioValor=" + maeTipoServicioValor + ", tuPersonasId=" + personaId + ", tuTutelaEstadosId=" + 
                tutelaEstadoId + ", tuTutelasId=" + tutelaId + ", aplicaDestino=" + aplicaDestino + '}';
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Integer getMaeCausaTutelaId() {
        return maeCausaTutelaId;
    }

    public void setMaeCausaTutelaId(Integer maeCausaTutelaId) {
        this.maeCausaTutelaId = maeCausaTutelaId;
    }

    public String getMaeCausaTutelaCodigo() {
        return maeCausaTutelaCodigo;
    }

    public void setMaeCausaTutelaCodigo(String maeCausaTutelaCodigo) {
        this.maeCausaTutelaCodigo = maeCausaTutelaCodigo;
    }

    public String getMaeCausaTutelaValor() {
        return maeCausaTutelaValor;
    }

    public void setMaeCausaTutelaValor(String maeCausaTutelaValor) {
        this.maeCausaTutelaValor = maeCausaTutelaValor;
    }

    public Date getFechaEnvio() {
        return fechaEnvio;
    }

    public void setFechaEnvio(Date fechaEnvio) {
        this.fechaEnvio = fechaEnvio;
    }

    public Date getFechaCita() {
        return fechaCita;
    }

    public void setFechaCita(Date fechaCita) {
        this.fechaCita = fechaCita;
    }

    public Date getFechaRespuesta() {
        return fechaRespuesta;
    }

    public void setFechaRespuesta(Date fechaRespuesta) {
        this.fechaRespuesta = fechaRespuesta;
    }

    public Date getFechaCumplimiento() {
        return fechaCumplimiento;
    }

    public void setFechaCumplimiento(Date fechaCumplimiento) {
        this.fechaCumplimiento = fechaCumplimiento;
    }

    public String getCorreoDestinatario() {
        return correoDestinatario;
    }

    public void setCorreoDestinatario(String correoDestinatario) {
        this.correoDestinatario = correoDestinatario;
    }

    public Integer getTipoServicioId() {
        return tipoServicioId;
    }

    public void setTipoServicioId(Integer tipoServicioId) {
        this.tipoServicioId = tipoServicioId;
    }

    public CntPrestadorSede getDestinoCntPrestadorSedeId() {
        return destinoCntPrestadorSedeId;
    }

    public void setDestinoCntPrestadorSedeId(CntPrestadorSede destinoCntPrestadorSedeId) {
        this.destinoCntPrestadorSedeId = destinoCntPrestadorSedeId;
    }

    public String getDestinoCntPrestadorSedeNombre() {
        return destinoCntPrestadorSedeNombre;
    }

    public void setDestinoCntPrestadorSedeNombre(String destinoCntPrestadorSedeNombre) {
        this.destinoCntPrestadorSedeNombre = destinoCntPrestadorSedeNombre;
    }
    
    public Boolean getNotificadaAIps() {
        return notificadaAIps;
    }

    public void setNotificadaAIps(Boolean notificadaAIps) {
        this.notificadaAIps = notificadaAIps;
    }

    public Boolean getRespuestaIps() {
        return respuestaIps;
    }

    public void setRespuestaIps(Boolean respuestaIps) {
        this.respuestaIps = respuestaIps;
    }

    public Integer getParametroIpsId() {
        return parametroIpsId;
    }

    public void setParametroIpsId(Integer parametroIpsId) {
        this.parametroIpsId = parametroIpsId;
    }

    public CntPrestadorSede getPrescripcionCntPrestadorSedeId() {
        return prescripcionCntPrestadorSedeId;
    }

    public void setPrescripcionCntPrestadorSedeId(CntPrestadorSede prescripcionCntPrestadorSedeId) {
        this.prescripcionCntPrestadorSedeId = prescripcionCntPrestadorSedeId;
    }

    public String getPrescripcionCntPrestadorSedeNombre() {
        return prescripcionCntPrestadorSedeNombre;
    }

    public void setPrescripcionCntPrestadorSedeNombre(String prescripcionCntPrestadorSedeNombre) {
        this.prescripcionCntPrestadorSedeNombre = prescripcionCntPrestadorSedeNombre;
    }
    
    public Integer getTipoServicio() {
        return tipoServicio;
    }

    public void setTipoServicio(Integer tipoServicio) {
        this.tipoServicio = tipoServicio;
    }

    public Integer getMaTecnologiaId() {
        return maTecnologiaId;
    }

    public void setMaTecnologiaId(Integer maTecnologiaId) {
        this.maTecnologiaId = maTecnologiaId;
    }

    public String getMaTecnologiaCodigo() {
        return maTecnologiaCodigo;
    }

    public void setMaTecnologiaCodigo(String maTecnologiaCodigo) {
        this.maTecnologiaCodigo = maTecnologiaCodigo;
    }

    public String getMaTecnologiaValor() {
        return maTecnologiaValor;
    }

    public void setMaTecnologiaValor(String maTecnologiaValor) {
        this.maTecnologiaValor = maTecnologiaValor;
    }

    public Integer getMaePresentacionId() {
        return maePresentacionId;
    }

    public void setMaePresentacionId(Integer maePresentacionId) {
        this.maePresentacionId = maePresentacionId;
    }

    public String getMaePresentacionCodigo() {
        return maePresentacionCodigo;
    }

    public void setMaePresentacionCodigo(String maePresentacionCodigo) {
        this.maePresentacionCodigo = maePresentacionCodigo;
    }

    public String getMaePresentacionValor() {
        return maePresentacionValor;
    }

    public void setMaePresentacionValor(String maePresentacionValor) {
        this.maePresentacionValor = maePresentacionValor;
    }

    public Integer getMaeTipoPrestacionId() {
        return maeTipoPrestacionId;
    }

    public void setMaeTipoPrestacionId(Integer maeTipoPrestacionId) {
        this.maeTipoPrestacionId = maeTipoPrestacionId;
    }

    public String getMaeTipoPrestacionCodigo() {
        return maeTipoPrestacionCodigo;
    }

    public void setMaeTipoPrestacionCodigo(String maeTipoPrestacionCodigo) {
        this.maeTipoPrestacionCodigo = maeTipoPrestacionCodigo;
    }

    public String getMaeTipoPrestacionValor() {
        return maeTipoPrestacionValor;
    }

    public void setMaeTipoPrestacionValor(String maeTipoPrestacionValor) {
        this.maeTipoPrestacionValor = maeTipoPrestacionValor;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public String getObservacionIps() {
        return observacionIps;
    }

    public void setObservacionIps(String observacionIps) {
        this.observacionIps = observacionIps;
    }

    public Boolean getSolicitarFechaCita() {
        return solicitarFechaCita;
    }

    public void setSolicitarFechaCita(Boolean solicitarFechaCita) {
        this.solicitarFechaCita = solicitarFechaCita;
    }

    public Integer getConsultarAutorizacion() {
        return consultarAutorizacion;
    }

    public void setConsultarAutorizacion(Integer consultarAutorizacion) {
        this.consultarAutorizacion = consultarAutorizacion;
    }
    
    public int getPos() {
        return pos;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }
    
    public TuPersona getTuPersonasId() {
        return tuPersonasId;
    }

    public void setTuPersonasId(TuPersona tuPersonasId) {
        this.tuPersonasId = tuPersonasId;
    }

    public List<TuDiagnostico> getTuTutelaDiagnosticosId() {
        if(tuTutelaDiagnosticosId == null){
            tuTutelaDiagnosticosId = new ArrayList<>();
        }
        return tuTutelaDiagnosticosId;
    }

    public void setTuTutelaDiagnosticosId(List<TuDiagnostico> tuTutelaDiagnosticosId) {
        this.tuTutelaDiagnosticosId = tuTutelaDiagnosticosId;
    }

    public List<AuAnexo4> getRegistrosAuditoriaAutorizacion() {
        if(registrosAuditoriaAutorizacion == null)
            registrosAuditoriaAutorizacion = new ArrayList<>();
        return registrosAuditoriaAutorizacion;
    }

    public void setRegistrosAuditoriaAutorizacion(List<AuAnexo4> registrosAuditoriaAutorizacion) {
        this.registrosAuditoriaAutorizacion = registrosAuditoriaAutorizacion;
    }
    
    public TuTutelaEstado getTuTutelaEstadosId() {
        return tuTutelaEstadosId;
    }

    public void setTuTutelaEstadosId(TuTutelaEstado tuTutelaEstadosId) {
        this.tuTutelaEstadosId = tuTutelaEstadosId;
    }

    public TuTutela getTuTutelasId() {
        return tuTutelasId;
    }

    public void setTuTutelasId(TuTutela tuTutelasId) {
        this.tuTutelasId = tuTutelasId;
    }

    public List<TuAdjunto> getTuAdjuntosList() {
        if (tuAdjuntosList == null) {
            tuAdjuntosList = new ArrayList<>();
        }
        return tuAdjuntosList;
    }

    public void setTuAdjuntosList(List<TuAdjunto> tuAdjuntosList) {
        this.tuAdjuntosList = tuAdjuntosList;
    }

    /**
     * @return the maeTipoServicioId
     */
    public Integer getMaeTipoServicioId() {
        return maeTipoServicioId;
    }

    /**
     * @param maeTipoServicioId the maeTipoServicioId to set
     */
    public void setMaeTipoServicioId(Integer maeTipoServicioId) {
        this.maeTipoServicioId = maeTipoServicioId;
    }

    /**
     * @return the maeTipoServicioCodigo
     */
    public String getMaeTipoServicioCodigo() {
        return maeTipoServicioCodigo;
    }

    /**
     * @param maeTipoServicioCodigo the maeTipoServicioCodigo to set
     */
    public void setMaeTipoServicioCodigo(String maeTipoServicioCodigo) {
        this.maeTipoServicioCodigo = maeTipoServicioCodigo;
    }

    /**
     * @return the maeTipoServicioValor
     */
    public String getMaeTipoServicioValor() {
        return maeTipoServicioValor;
    }

    /**
     * @param maeTipoServicioValor the maeTipoServicioValor to set
     */
    public void setMaeTipoServicioValor(String maeTipoServicioValor) {
        this.maeTipoServicioValor = maeTipoServicioValor;
    }

    /**
     * @return the aplicaDestino
     */
    public boolean isAplicaDestino() {
        return aplicaDestino;
    }
    
    /**
     * @return the aplicaDestino
     */
    public String getAplicaDestinoStr() {
        if (aplicaDestino) {return "Si";}
        return "No";
    }

    /**
     * @param aplicaDestino the aplicaDestino to set
     */
    public void setAplicaDestino(boolean aplicaDestino) {
        this.aplicaDestino = aplicaDestino;
    }

    /**
     * @return the maeTipoAsignacionId
     */
    public Integer getMaeTipoAsignacionId() {
        return maeTipoAsignacionId;
    }

    /**
     * @param maeTipoAsignacionId the maeTipoAsignacionId to set
     */
    public void setMaeTipoAsignacionId(Integer maeTipoAsignacionId) {
        this.maeTipoAsignacionId = maeTipoAsignacionId;
    }

    /**
     * @return the maeTipoAsignacionCodigo
     */
    public String getMaeTipoAsignacionCodigo() {
        return maeTipoAsignacionCodigo;
    }

    /**
     * @param maeTipoAsignacionCodigo the maeTipoAsignacionCodigo to set
     */
    public void setMaeTipoAsignacionCodigo(String maeTipoAsignacionCodigo) {
        this.maeTipoAsignacionCodigo = maeTipoAsignacionCodigo;
    }

    /**
     * @return the maeTipoAsignacionValor
     */
    public String getMaeTipoAsignacionValor() {
        return maeTipoAsignacionValor;
    }

    /**
     * @param maeTipoAsignacionValor the maeTipoAsignacionValor to set
     */
    public void setMaeTipoAsignacionValor(String maeTipoAsignacionValor) {
        this.maeTipoAsignacionValor = maeTipoAsignacionValor;
    }

    /**
     * @return the maeEstadoItemId
     */
    public Integer getMaeEstadoItemId() {
        return maeEstadoItemId;
    }

    /**
     * @param maeEstadoItemId the maeEstadoItemId to set
     */
    public void setMaeEstadoItemId(Integer maeEstadoItemId) {
        this.maeEstadoItemId = maeEstadoItemId;
    }

    /**
     * @return the maeEstadoItemCodigo
     */
    public String getMaeEstadoItemCodigo() {
        return maeEstadoItemCodigo;
    }

    /**
     * @param maeEstadoItemCodigo the maeEstadoItemCodigo to set
     */
    public void setMaeEstadoItemCodigo(String maeEstadoItemCodigo) {
        this.maeEstadoItemCodigo = maeEstadoItemCodigo;
    }

    /**
     * @return the maeEstadoItemValor
     */
    public String getMaeEstadoItemValor() {
        return maeEstadoItemValor;
    }

    /**
     * @param maeEstadoItemValor the maeEstadoItemValor to set
     */
    public void setMaeEstadoItemValor(String maeEstadoItemValor) {
        this.maeEstadoItemValor = maeEstadoItemValor;
    }

    /**
     * @return the gnUsuarioAsignadoId
     */
    public Usuario getGnUsuarioAsignadoId() {
        return gnUsuarioAsignadoId;
    }

    /**
     * @param gnUsuarioAsignadoId the gnUsuarioAsignadoId to set
     */
    public void setGnUsuarioAsignadoId(Usuario gnUsuarioAsignadoId) {
        this.gnUsuarioAsignadoId = gnUsuarioAsignadoId;
    }

    /**
     * @return the aplicaAsignacion
     */
    public boolean isAplicaAsignacion() {
        return aplicaAsignacion;
    }
    
    public String getAplicaAsignacionStr() {
        if (aplicaAsignacion) {return "Si";}
        return "No";
    }

    /**
     * @param aplicaAsignacion the aplicaAsignacion to set
     */
    public void setAplicaAsignacion(boolean aplicaAsignacion) {
        this.aplicaAsignacion = aplicaAsignacion;
    }

    /**
     * @return the tuTutelaItemGestionesList
     */
    public List<TuTutelaItemGestion> getTuTutelaItemGestionesList() {
        return tuTutelaItemGestionesList;
    }

    /**
     * @param tuTutelaItemGestionesList the tuTutelaItemGestionesList to set
     */
    public void setTuTutelaItemGestionesList(List<TuTutelaItemGestion> tuTutelaItemGestionesList) {
        this.tuTutelaItemGestionesList = tuTutelaItemGestionesList;
    }
}
