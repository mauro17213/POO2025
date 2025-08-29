/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.autorizacion;

import com.saviasaludeps.savia.dominio.administracion.Empresa;
import com.saviasaludeps.savia.dominio.aseguramiento.AsegAfiliado;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestador;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestadorSede;
import com.saviasaludeps.savia.dominio.contratacion.CntProfesional;
import com.saviasaludeps.savia.dominio.generico.Auditoria;
import com.saviasaludeps.savia.dominio.maestro.MaEspecialidad;
import java.util.Date;
import java.util.List;

/**
 *
 * @author NEXOS
 */
public class AuNoSolicitud extends Auditoria{
    
    //estados
    public static final int ESTADO_PENDIENTE = 1;
    public static final int ESTADO_EN_GESTION = 2;
    public static final int ESTADO_GESTIONADO = 3;
    public static final int ESTADO_ANULADO = 4;
    
    //fuene origen
    public static final int FUENTE_ORIGEN_MANUAL = 1;
    public static final int FUENTE_ORIGEN_CARGA_MASIVA = 2;
    public static final int FUENTE_ORIGEN_INTEROPERABILIDAD = 3; 
    
    //tipo formula
    public static final int TIPO_FORMULA_MIPRES = 1;
    public static final int TIPO_FORMULA = 2;
    public static final int TIPO_FORMULA_ANEXO3 = 3;
    
    private Integer id;
    private Empresa gnEmpresasId;
    private AuNoSolicitudCarga AuNoSolicitudCargasId;
    private int fuenteOrigen;
    private int estado;
    private String estadoJustificacion;
    private AsegAfiliado asegAfiliadosId;
    private CntPrestador cntPrestadorId;
    private CntPrestadorSede cntPrestadorSedesId;
    private Date fechaOrdenMedica;
    private int maeAmbitoAtencionId;
    private String maeAmbitoAtencionCodigo;
    private String maeAmbitoAtencionValor;
    private String maeAmbitoAtencionTipo;
    private Integer maServicioHabilitacionId;
    private String maServicioHabilitacionCodigo;
    private String maServicioHabilitacionValor;
    private CntProfesional cntProfesionalesId;
    private Integer maEspecialidadId;
    private String maEspecialidadCodigo;
    private String maEspecialidadValor;
    private String justificacionClinica;
    private CntPrestador cntPrestadorEntregaId;
    private CntPrestadorSede cntPrestadorSedeEntregaId;
    private String consecutivoOrden;
    private String consecutivo;
    private boolean tutela; 
    private int tipoFormula;
    private int maeMotivoSinAutorizacionId;
    private String maeMotivoSinAutorizacionCodigo;
    private String maeMotivoSinAutorizacionValor;
    private String maeMotivoSinAutorizacionTipo;
    
    // listas
    private List<AuNoSolicitudDiagnostico> listAuNoSolicitudDiagnostico;
    private List<AuNoSolicitudItem> listAuNoSolicitudItem;
    private List<AuSolicitudAdjunto> listAuSolicitudAdjunto;
    private List<AuNoSolicitudHistorico> listaAuNoSolicitudHistorico;
    
     //variable auxiliar
    private int cargaFila;
    //variable auxiliar para profesional
    private boolean newProfesional;
    private String nombreProfesional;
    private String cargoProfesional;
    private Integer maeEspecialidadProfesionalId;
    private MaEspecialidad objetoEspecialidad;
    
    private List<String> errores;
    
    public AuNoSolicitud(){
        
    }
    
    public AuNoSolicitud(Integer id){
         this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Empresa getGnEmpresasId() {
        return gnEmpresasId;
    }

    public void setGnEmpresasId(Empresa gnEmpresasId) {
        this.gnEmpresasId = gnEmpresasId;
    }

    public AuNoSolicitudCarga getAuNoSolicitudCargasId() {
        return AuNoSolicitudCargasId;
    }

    public void setAuNoSolicitudCargasId(AuNoSolicitudCarga AuNoSolicitudCargasId) {
        this.AuNoSolicitudCargasId = AuNoSolicitudCargasId;
    }

    public int getFuenteOrigen() {
        return fuenteOrigen;
    }

    public void setFuenteOrigen(int fuenteOrigen) {
        this.fuenteOrigen = fuenteOrigen;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public String getEstadoJustificacion() {
        return estadoJustificacion;
    }

    public void setEstadoJustificacion(String estadoJustificacion) {
        this.estadoJustificacion = estadoJustificacion;
    }

    public AsegAfiliado getAsegAfiliadosId() {
        return asegAfiliadosId;
    }

    public void setAsegAfiliadosId(AsegAfiliado asegAfiliadosId) {
        this.asegAfiliadosId = asegAfiliadosId;
    }

    public CntPrestador getCntPrestadorId() {
        return cntPrestadorId;
    }

    public void setCntPrestadorId(CntPrestador cntPrestadorId) {
        this.cntPrestadorId = cntPrestadorId;
    }

    public CntPrestadorSede getCntPrestadorSedesId() {
        return cntPrestadorSedesId;
    }

    public void setCntPrestadorSedesId(CntPrestadorSede cntPrestadorSedesId) {
        this.cntPrestadorSedesId = cntPrestadorSedesId;
    }

    public Date getFechaOrdenMedica() {
        return fechaOrdenMedica;
    }

    public void setFechaOrdenMedica(Date fechaOrdenMedica) {
        this.fechaOrdenMedica = fechaOrdenMedica;
    }

    public int getMaeAmbitoAtencionId() {
        return maeAmbitoAtencionId;
    }

    public void setMaeAmbitoAtencionId(int maeAmbitoAtencionId) {
        this.maeAmbitoAtencionId = maeAmbitoAtencionId;
    }

    public String getMaeAmbitoAtencionCodigo() {
        return maeAmbitoAtencionCodigo;
    }

    public void setMaeAmbitoAtencionCodigo(String maeAmbitoAtencionCodigo) {
        this.maeAmbitoAtencionCodigo = maeAmbitoAtencionCodigo;
    }

    public String getMaeAmbitoAtencionValor() {
        return maeAmbitoAtencionValor;
    }

    public void setMaeAmbitoAtencionValor(String maeAmbitoAtencionValor) {
        this.maeAmbitoAtencionValor = maeAmbitoAtencionValor;
    }

    public String getMaeAmbitoAtencionTipo() {
        return maeAmbitoAtencionTipo;
    }

    public void setMaeAmbitoAtencionTipo(String maeAmbitoAtencionTipo) {
        this.maeAmbitoAtencionTipo = maeAmbitoAtencionTipo;
    }

    public Integer getMaServicioHabilitacionId() {
        return maServicioHabilitacionId;
    }

    public void setMaServicioHabilitacionId(Integer maServicioHabilitacionId) {
        this.maServicioHabilitacionId = maServicioHabilitacionId;
    }

    public String getMaServicioHabilitacionCodigo() {
        return maServicioHabilitacionCodigo;
    }

    public void setMaServicioHabilitacionCodigo(String maServicioHabilitacionCodigo) {
        this.maServicioHabilitacionCodigo = maServicioHabilitacionCodigo;
    }

    public String getMaServicioHabilitacionValor() {
        return maServicioHabilitacionValor;
    }

    public void setMaServicioHabilitacionValor(String maServicioHabilitacionValor) {
        this.maServicioHabilitacionValor = maServicioHabilitacionValor;
    }

    public CntProfesional getCntProfesionalesId() {
        return cntProfesionalesId;
    }

    public void setCntProfesionalesId(CntProfesional cntProfesionalesId) {
        this.cntProfesionalesId = cntProfesionalesId;
    }

    public Integer getMaEspecialidadId() {
        return maEspecialidadId;
    }

    public void setMaEspecialidadId(Integer maEspecialidadId) {
        this.maEspecialidadId = maEspecialidadId;
    }

    public String getMaEspecialidadCodigo() {
        return maEspecialidadCodigo;
    }

    public void setMaEspecialidadCodigo(String maEspecialidadCodigo) {
        this.maEspecialidadCodigo = maEspecialidadCodigo;
    }

    public String getMaEspecialidadValor() {
        return maEspecialidadValor;
    }

    public void setMaEspecialidadValor(String maEspecialidadValor) {
        this.maEspecialidadValor = maEspecialidadValor;
    }

    public String getJustificacionClinica() {
        return justificacionClinica;
    }

    public void setJustificacionClinica(String justificacionClinica) {
        this.justificacionClinica = justificacionClinica;
    }

    public CntPrestador getCntPrestadorEntregaId() {
        return cntPrestadorEntregaId;
    }

    public void setCntPrestadorEntregaId(CntPrestador cntPrestadorEntregaId) {
        this.cntPrestadorEntregaId = cntPrestadorEntregaId;
    }

    public CntPrestadorSede getCntPrestadorSedeEntregaId() {
        return cntPrestadorSedeEntregaId;
    }

    public void setCntPrestadorSedeEntregaId(CntPrestadorSede cntPrestadorSedeEntregaId) {
        this.cntPrestadorSedeEntregaId = cntPrestadorSedeEntregaId;
    }

    public String getConsecutivoOrden() {
        return consecutivoOrden;
    }

    public void setConsecutivoOrden(String consecutivoOrden) {
        this.consecutivoOrden = consecutivoOrden;
    }

    public String getConsecutivo() {
        return consecutivo;
    }

    public void setConsecutivo(String consecutivo) {
        this.consecutivo = consecutivo;
    }

    public boolean isTutela() {
        return tutela;
    }

    public void setTutela(boolean tutela) {
        this.tutela = tutela;
    }

    public int getTipoFormula() {
        return tipoFormula;
    }

    public void setTipoFormula(int tipoFormula) {
        this.tipoFormula = tipoFormula;
    }

    public int getMaeMotivoSinAutorizacionId() {
        return maeMotivoSinAutorizacionId;
    }

    public void setMaeMotivoSinAutorizacionId(int maeMotivoSinAutorizacionId) {
        this.maeMotivoSinAutorizacionId = maeMotivoSinAutorizacionId;
    }

    public String getMaeMotivoSinAutorizacionCodigo() {
        return maeMotivoSinAutorizacionCodigo;
    }

    public void setMaeMotivoSinAutorizacionCodigo(String maeMotivoSinAutorizacionCodigo) {
        this.maeMotivoSinAutorizacionCodigo = maeMotivoSinAutorizacionCodigo;
    }

    public String getMaeMotivoSinAutorizacionValor() {
        return maeMotivoSinAutorizacionValor;
    }

    public void setMaeMotivoSinAutorizacionValor(String maeMotivoSinAutorizacionValor) {
        this.maeMotivoSinAutorizacionValor = maeMotivoSinAutorizacionValor;
    }

    public String getMaeMotivoSinAutorizacionTipo() {
        return maeMotivoSinAutorizacionTipo;
    }

    public void setMaeMotivoSinAutorizacionTipo(String maeMotivoSinAutorizacionTipo) {
        this.maeMotivoSinAutorizacionTipo = maeMotivoSinAutorizacionTipo;
    }


    public int getCargaFila() {
        return cargaFila;
    }

    public void setCargaFila(int cargaFila) {
        this.cargaFila = cargaFila;
    }

    public boolean isNewProfesional() {
        return newProfesional;
    }

    public void setNewProfesional(boolean newProfesional) {
        this.newProfesional = newProfesional;
    }

    public String getNombreProfesional() {
        return nombreProfesional;
    }

    public void setNombreProfesional(String nombreProfesional) {
        this.nombreProfesional = nombreProfesional;
    }

    public String getCargoProfesional() {
        return cargoProfesional;
    }

    public void setCargoProfesional(String cargoProfesional) {
        this.cargoProfesional = cargoProfesional;
    }

    public Integer getMaeEspecialidadProfesionalId() {
        return maeEspecialidadProfesionalId;
    }

    public void setMaeEspecialidadProfesionalId(Integer maeEspecialidadProfesionalId) {
        this.maeEspecialidadProfesionalId = maeEspecialidadProfesionalId;
    }

    public MaEspecialidad getObjetoEspecialidad() {
        return objetoEspecialidad;
    }

    public void setObjetoEspecialidad(MaEspecialidad objetoEspecialidad) {
        this.objetoEspecialidad = objetoEspecialidad;
    }

    public List<String> getErrores() {
        return errores;
    }

    public void setErrores(List<String> errores) {
        this.errores = errores;
    }

    public List<AuNoSolicitudDiagnostico> getListAuNoSolicitudDiagnostico() {
        return listAuNoSolicitudDiagnostico;
    }

    public void setListAuNoSolicitudDiagnostico(List<AuNoSolicitudDiagnostico> listAuNoSolicitudDiagnostico) {
        this.listAuNoSolicitudDiagnostico = listAuNoSolicitudDiagnostico;
    }

    public List<AuNoSolicitudItem> getListAuNoSolicitudItem() {
        return listAuNoSolicitudItem;
    }

    public void setListAuNoSolicitudItem(List<AuNoSolicitudItem> listAuNoSolicitudItem) {
        this.listAuNoSolicitudItem = listAuNoSolicitudItem;
    }

    public List<AuSolicitudAdjunto> getListAuSolicitudAdjunto() {
        return listAuSolicitudAdjunto;
    }

    public void setListAuSolicitudAdjunto(List<AuSolicitudAdjunto> listAuSolicitudAdjunto) {
        this.listAuSolicitudAdjunto = listAuSolicitudAdjunto;
    }

    public List<AuNoSolicitudHistorico> getListaAuNoSolicitudHistorico() {
        return listaAuNoSolicitudHistorico;
    }

    public void setListaAuNoSolicitudHistorico(List<AuNoSolicitudHistorico> listaAuNoSolicitudHistorico) {
        this.listaAuNoSolicitudHistorico = listaAuNoSolicitudHistorico;
    }
    
    // Metodo adcionales
    public String getEstadoStr(){
        String nombreEstado = "";
        switch(estado){
            case ESTADO_PENDIENTE:
                nombreEstado = "Pendiente";
                break;
            case ESTADO_EN_GESTION:
                nombreEstado = "En Gestión";
                break;
            case ESTADO_GESTIONADO:
                nombreEstado = "Gestionado";
                break;
            case ESTADO_ANULADO:
                nombreEstado = "Anulado";
                break;
             default:
                break;
        }     
        return nombreEstado;
    }
    
    public String getFuenteOrigenStr(){
        String fuenteOrigenStr = "";
        switch(fuenteOrigen){
            case FUENTE_ORIGEN_MANUAL:
                fuenteOrigenStr = "Manual";
                break;
            case FUENTE_ORIGEN_CARGA_MASIVA:
                fuenteOrigenStr = "Carga Masiva";
                break;
            case FUENTE_ORIGEN_INTEROPERABILIDAD:
                fuenteOrigenStr = "Interoperabilidad";
                break;
             default:
                break;
        }     
        return fuenteOrigenStr;
    }
    
    public String getTutelaStr(){
        String tutelaStr = "No";
        if(tutela){
            tutelaStr = "Si";
        }
        return tutelaStr;
    }
    
    public String getTipoFormulaStr(){
        String tipoFormulaStr = "";
        switch(tipoFormula){
            case TIPO_FORMULA_MIPRES:
                tipoFormulaStr = "Mipres";
                break;
            case TIPO_FORMULA:
                tipoFormulaStr = "Formula";
                break;
            case TIPO_FORMULA_ANEXO3:
                tipoFormulaStr = "Anexo3";
                break;
             default:
                break;
        }     
        return tipoFormulaStr;
    }
    
    @Override
    public String toString() {
        return "AuNoSolicitud{" + "id=" + id + ", gnEmpresasId=" + gnEmpresasId + ", fuenteOrigen=" + fuenteOrigen + ", estado=" + estado + ", estadoJustificacion=" + estadoJustificacion + ", asegAfiliadosId=" + asegAfiliadosId + ", cntPrestadorId=" + cntPrestadorId + ", cntPrestadorSedesId=" + cntPrestadorSedesId + ", fechaOrdenMedica=" + fechaOrdenMedica + ", maeAmbitoAtencionId=" + maeAmbitoAtencionId + ", maeAmbitoAtencionCodigo=" + maeAmbitoAtencionCodigo + ", maeAmbitoAtencionValor=" + maeAmbitoAtencionValor + ", maeAmbitoAtencionTipo=" + maeAmbitoAtencionTipo + ", maServicioHabilitacionId=" + maServicioHabilitacionId + ", maServicioHabilitacionCodigo=" + maServicioHabilitacionCodigo + ", maServicioHabilitacionValor=" + maServicioHabilitacionValor + ", cntProfesionalesId=" + cntProfesionalesId + ", maEspecialidadId=" + maEspecialidadId + ", maEspecialidadCodigo=" + maEspecialidadCodigo + ", maEspecialidadValor=" + maEspecialidadValor + ", justificacionClinica=" + justificacionClinica + '}';
    }
    
}
