/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.tutela;

import com.saviasaludeps.savia.dominio.administracion.Usuario;
import com.saviasaludeps.savia.dominio.generico.Auditoria;
//import com.saviasaludeps.savia.ejb.entidades.TuJuzgados;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author jramirer
 */
public class TuTutelaEstado extends Auditoria implements Serializable {

    private Integer id;
    private TuTutela tuTutelaId;
    private TuJuzgado tuJuzgadoId;
    private Integer maeEstadoId;
    private String maeEstadoCodigo;
    private String maeEstadoValor;
    private Integer maeProcesoId;
    private String maeProcesoCodigo;
    private String maeProcesoValor;
    private Usuario responsableGnUsuarioId;
    private Usuario gestorGnUsuarioId;
    private Usuario abogadoGnUsuarioId;
    private Usuario medicoGnUsuarioId;
    private Integer maeClaseSancionId;
    private String maeClaseSancionCodigo;
    private String maeClaseSancionValor;
    private Integer maeClaseArrestoId;
    private String maeClaseArrestoCodigo;
    private String maeClaseArrestoValor;
    private Integer cuantia;
    private Integer diasArresto;
    private BigDecimal uvt;
    private Integer tuGrupoId;
    private Integer tuGrupoAutomaticoId;
    private String emailGestorRemitente;
    private Boolean entregaSucesiva;
    private Boolean exoneracion;
    private Boolean impugnacion;
    private Boolean integralidad;
    private Boolean medidadProvisional;
    private Boolean tipoReparto;
    private Integer maeTipoFalloId;
    private String maeTipoFalloCodigo;
    private String maeTipoFalloValor;
    private Date fechaNotificacion;
    private Date fechaVencimiento;
    private String observacion;
    private int cantidadServicio;
    private int cantidadServicioCumplido;
    private int diasCambioEstado;
    private Integer maeSmlvId;
    private String maeSmlvCodigo;
    private String maeSmlvValor;
    private Boolean borrado;
    private String usuarioBorra;
    private String terminalBorra;
    private Date fechaHoraBorra;
    private int tipoAuditorInicial;
    //2025-07-17 jyperez nuevos campos;
    private Integer cantidadItems;
    private Integer cantidadItemsCerrados;
    
    private List<TuJuzgado> listaTuJuzgado;
    private List<TuTutelaItem> listaTuTutelaItem;
    private List<TuAdjunto> tuAdjuntosList;
    private List<TuSeguimiento> tuSeguimientosList;
    
    private Integer[] listaFiltrosRepresentanteDemandado;
    private Integer[] listaFiltrosJuzgado;
    private int pos;
    
    public final static String ESTADO_SEGUIMIENTO_TUTELA_NUEVA = "2";
    public final static String ESTADO_SEGUIMIENTO_REQUERIMIENTO = "5";
           
    public TuTutelaEstado() {
        
    }
    
    public TuTutelaEstado(Integer id) {
        this.id = id;
    }
    
    public TuTutelaEstado(Integer id, int maeEstadoId, String maeEstadoCodigo, String maeEstadoValor, int maeProcesoId, String maeProcesoCodigo, String maeProcesoValor, Usuario responsableGnUsuarioId, Usuario gestorGnUsuarioId, Usuario abogadoGnUsuarioId, Usuario medicoGnUsuarioId, Integer maeClaseSancionId, String maeClaseSancionCodigo, String maeClaseSancionValor, Integer maeClaseArrestoId, String maeClaseArrestoCodigo, String maeClaseArrestoValor, BigDecimal cuantia, Integer diasArresto, BigDecimal uvt, String emailGestorRemitente, Boolean entregaSucesiva, Boolean exoneracion, Boolean impugnacion, Boolean integralidad, Boolean medidaProvisional, Integer maeTipoFalloId, String maeTipoFalloCodigo, String maeTipoFalloValor, Date fechaNotificacion, Date fechaVencimiento, String observacion, Integer cantidadServicio, Integer cantidadServicioCumplido, Integer diasCambioEstado) {
        this.id = id;
        this.maeEstadoId = maeEstadoId;
        this.maeEstadoCodigo = maeEstadoCodigo;
        this.maeEstadoValor = maeEstadoValor;
        this.maeProcesoId = maeProcesoId;
        this.maeProcesoCodigo = maeProcesoCodigo;
        this.maeProcesoValor = maeProcesoValor;
        this.responsableGnUsuarioId = responsableGnUsuarioId;
        this.gestorGnUsuarioId = gestorGnUsuarioId;
        this.abogadoGnUsuarioId = abogadoGnUsuarioId;
        this.medicoGnUsuarioId = medicoGnUsuarioId;
        this.maeClaseSancionId = maeClaseSancionId;
        this.maeClaseSancionCodigo = maeClaseSancionCodigo;
        this.maeClaseSancionValor = maeClaseSancionValor;
        this.maeClaseArrestoId = maeClaseArrestoId;
        this.maeClaseArrestoCodigo = maeClaseArrestoCodigo;
        this.maeClaseArrestoValor = maeClaseArrestoValor;
//        this.cuantia = cuantia;
        this.diasArresto = diasArresto;
        this.uvt = uvt;
        this.emailGestorRemitente = emailGestorRemitente;
        this.entregaSucesiva = entregaSucesiva;
        this.exoneracion = exoneracion;
        this.impugnacion = impugnacion;
        this.integralidad = integralidad;
        this.medidadProvisional = medidadProvisional;
        this.maeTipoFalloId = maeTipoFalloId;
        this.maeTipoFalloCodigo = maeTipoFalloCodigo;
        this.maeTipoFalloValor = maeTipoFalloValor;
        this.fechaNotificacion = fechaNotificacion;
        this.fechaVencimiento = fechaVencimiento;
        this.observacion = observacion;
        this.cantidadServicio = cantidadServicio;
        this.cantidadServicioCumplido = cantidadServicioCumplido;
        this.diasCambioEstado = diasCambioEstado;

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public TuTutela getTuTutelaId() {
        return tuTutelaId;
    }

    public void setTuTutelaId(TuTutela tuTutelaId) {
        this.tuTutelaId = tuTutelaId;
    }

    public TuJuzgado getTuJuzgadoId() {
        if(tuJuzgadoId == null){
            tuJuzgadoId = new TuJuzgado();
        }
        return tuJuzgadoId;
    }

    public void setTuJuzgadoId(TuJuzgado tuJuzgadoId) {
        this.tuJuzgadoId = tuJuzgadoId;
    }

    public Integer getMaeEstadoId() {
        return maeEstadoId;
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

    public void setMaeEstadoValor(String maeEstadoValor) {
        this.maeEstadoValor = maeEstadoValor;
    }

    public Integer getMaeProcesoId() {
        return maeProcesoId;
    }

    public void setMaeProcesoId(Integer maeProcesoId) {
        this.maeProcesoId = maeProcesoId;
    }

    public String getMaeProcesoCodigo() {
        return maeProcesoCodigo;
    }

    public void setMaeProcesoCodigo(String maeProcesoCodigo) {
        this.maeProcesoCodigo = maeProcesoCodigo;
    }

    public String getMaeProcesoValor() {
        return maeProcesoValor;
    }

    public void setMaeProcesoValor(String maeProcesoValor) {
        this.maeProcesoValor = maeProcesoValor;
    }

    public Usuario getResponsableGnUsuarioId() {
        if(responsableGnUsuarioId == null){
            responsableGnUsuarioId = new Usuario();
        }
        return responsableGnUsuarioId;
    }

    public void setResponsableGnUsuarioId(Usuario responsableGnUsuarioId) {
        this.responsableGnUsuarioId = responsableGnUsuarioId;
    }

 
    public Usuario getGestorGnUsuarioId() {
        if(gestorGnUsuarioId == null){
            gestorGnUsuarioId = new Usuario();
        }
        return gestorGnUsuarioId;
    }

    public void setGestorGnUsuarioId(Usuario gestorGnUsuarioId) {
        this.gestorGnUsuarioId = gestorGnUsuarioId;
    }

    public Usuario getAbogadoGnUsuarioId() {
        if(abogadoGnUsuarioId == null){
            abogadoGnUsuarioId = new Usuario();
        }
        return abogadoGnUsuarioId;
    }

    public void setAbogadoGnUsuarioId(Usuario abogadoGnUsuarioId) {
        this.abogadoGnUsuarioId = abogadoGnUsuarioId;
    }

    public Usuario getMedicoGnUsuarioId() {
        if(medicoGnUsuarioId == null){
            medicoGnUsuarioId = new Usuario();
        }
        return medicoGnUsuarioId;
    }

    public void setMedicoGnUsuarioId(Usuario medicoGnUsuarioId) {
        this.medicoGnUsuarioId = medicoGnUsuarioId;
    }

    public Integer getMaeClaseSancionId() {
        return maeClaseSancionId;
    }

    public void setMaeClaseSancionId(Integer maeClaseSancionId) {
        this.maeClaseSancionId = maeClaseSancionId;
    }

    public String getMaeClaseSancionCodigo() {
        return maeClaseSancionCodigo;
    }

    public void setMaeClaseSancionCodigo(String maeClaseSancionCodigo) {
        this.maeClaseSancionCodigo = maeClaseSancionCodigo;
    }

    public String getMaeClaseSancionValor() {
        return maeClaseSancionValor;
    }

    public void setMaeClaseSancionValor(String maeClaseSancionValor) {
        this.maeClaseSancionValor = maeClaseSancionValor;
    }

    public Integer getMaeClaseArrestoId() {
        return maeClaseArrestoId;
    }

    public void setMaeClaseArrestoId(Integer maeClaseArrestoId) {
        this.maeClaseArrestoId = maeClaseArrestoId;
    }

    public String getMaeClaseArrestoCodigo() {
        return maeClaseArrestoCodigo;
    }

    public void setMaeClaseArrestoCodigo(String maeClaseArrestoCodigo) {
        this.maeClaseArrestoCodigo = maeClaseArrestoCodigo;
    }

    public String getMaeClaseArrestoValor() {
        return maeClaseArrestoValor;
    }

    public void setMaeClaseArrestoValor(String maeClaseArrestoValor) {
        this.maeClaseArrestoValor = maeClaseArrestoValor;
    }

    public Integer getCuantia() {
        return cuantia;
    }

    public void setCuantia(Integer cuantia) {
        this.cuantia = cuantia;
    }

    public Integer getTuGrupoId() {
        return tuGrupoId;
    }

    public void setTuGrupoId(Integer tuGrupoId) {
        this.tuGrupoId = tuGrupoId;
    }

    public Integer getTuGrupoAutomaticoId() {
        return tuGrupoAutomaticoId;
    }

    public void setTuGrupoAutomaticoId(Integer tuGrupoAutomaticoId) {
        this.tuGrupoAutomaticoId = tuGrupoAutomaticoId;
    }
    
    public Integer getDiasArresto() {
        return diasArresto;
    }

    public void setDiasArresto(Integer diasArresto) {
        this.diasArresto = diasArresto;
    }

    public BigDecimal getUvt() {
        return uvt;
    }

    public void setUvt(BigDecimal uvt) {
        this.uvt = uvt;
    }

    public String getEmailGestorRemitente() {
        return emailGestorRemitente;
    }

    public void setEmailGestorRemitente(String emailGestorRemitente) {
        this.emailGestorRemitente = emailGestorRemitente;
    }
    
    public Boolean getEntregaSucesiva() {
        return entregaSucesiva;
    }
    
    public Boolean isEntregaSucesiva() {
        return entregaSucesiva;
    }

    public void setEntregaSucesiva(Boolean entregaSucesiva) {
        this.entregaSucesiva = entregaSucesiva;
    }
    
    public String getEntregaSucesivaStr() {
        String entregaSucesivas = "N/A";
        if (getEntregaSucesiva() != null) {
            entregaSucesivas = (getEntregaSucesiva()) ? "SI" : "NO";
        }
        return entregaSucesivas;
    }
    
    public Boolean getExoneracion() {
        return exoneracion;
    }

    public Boolean isExoneracion() {
        return exoneracion;
    }

    public void setExoneracion(Boolean exoneracion) {
        this.exoneracion = exoneracion;
    }

    public String getExoneracionStr() {
        String exoneracion = "N/A";
        if (getExoneracion() != null) {
            exoneracion = (getExoneracion()) ? "SI" : "NO";
        }
        return exoneracion;
    }

    public Boolean getImpugnacion() {
        return impugnacion;
    }

    public Boolean isImpugnacion() {
        return impugnacion;
    }

    public void setImpugnacion(Boolean impugnacion) {
        this.impugnacion = impugnacion;
    }

    public String getImpugnacionStr() {
        String impugnacion = "N/A";
        if (getImpugnacion() != null) {
            impugnacion = (getImpugnacion()) ? "SI" : "NO";
        }
        return impugnacion;
    }

    public Boolean getIntegralidad() {
        return integralidad;
    }

    public Boolean isIntegralidad() {
        return integralidad;
    }

    public void setIntegralidad(Boolean integralidad) {
        this.integralidad = integralidad;
    }

    public String getIntegralidadStr() {
        String integralidad = "N/A";
        if (getIntegralidad() != null) {
            integralidad = (getIntegralidad()) ? "SI" : "NO";
        }
        return integralidad;
    }

    public Boolean getMedidadProvisional() {
        return medidadProvisional;
    }

    public Boolean isMedidadProvisional() {
        return medidadProvisional;
    }

    public void setMedidadProvisional(Boolean medidadProvisional) {
        this.medidadProvisional = medidadProvisional;
    }

    public String getMedidadProvisionalStr() {
        String medidaProvisional = "N/A";
        if (getMedidadProvisional() != null) {
            medidaProvisional = (getMedidadProvisional()) ? "SI" : "NO";
        }
        return medidaProvisional;
    }

    public Integer getMaeTipoFalloId() {
        return maeTipoFalloId;
    }

    public void setMaeTipoFalloId(Integer maeTipoFalloId) {
        this.maeTipoFalloId = maeTipoFalloId;
    }

    public String getMaeTipoFalloCodigo() {
        return maeTipoFalloCodigo;
    }

    public void setMaeTipoFalloCodigo(String maeTipoFalloCodigo) {
        this.maeTipoFalloCodigo = maeTipoFalloCodigo;
    }

    public String getMaeTipoFalloValor() {
        return maeTipoFalloValor;
    }

    public void setMaeTipoFalloValor(String maeTipoFalloValor) {
        this.maeTipoFalloValor = maeTipoFalloValor;
    }

    public Date getFechaNotificacion() {
        return fechaNotificacion;
    }

    public void setFechaNotificacion(Date fechaNotificacion) {
        this.fechaNotificacion = fechaNotificacion;
    }

    public Date getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(Date fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public int getCantidadServicio() {
        return cantidadServicio;
    }

    public void setCantidadServicio(int cantidadServicio) {
        this.cantidadServicio = cantidadServicio;
    }

    public int getCantidadServicioCumplido() {
        return cantidadServicioCumplido;
    }

    public void setCantidadServicioCumplido(int cantidadServicioCumplido) {
        this.cantidadServicioCumplido = cantidadServicioCumplido;
    }

    public Boolean getTipoReparto() {
        return tipoReparto;
    }

    public void setTipoReparto(Boolean tipoReparto) {
        this.tipoReparto = tipoReparto;
    }
    
    public int getDiasCambioEstado() {
        return diasCambioEstado;
    }

    public Integer getMaeSmlvId() {
        return maeSmlvId;
    }

    public void setMaeSmlvId(Integer maeSmlvId) {
        this.maeSmlvId = maeSmlvId;
    }

    public String getMaeSmlvCodigo() {
        return maeSmlvCodigo;
    }

    public void setMaeSmlvCodigo(String maeSmlvCodigo) {
        this.maeSmlvCodigo = maeSmlvCodigo;
    }

    public String getMaeSmlvValor() {
        return maeSmlvValor;
    }

    public void setMaeSmlvValor(String maeSmlvValor) {
        this.maeSmlvValor = maeSmlvValor;
    }

    public void setDiasCambioEstado(int diasCambioEstado) {
        this.diasCambioEstado = diasCambioEstado;
    }

    public List<TuJuzgado> getListaTuJuzgado() {
        if(listaTuJuzgado == null){
            listaTuJuzgado = new ArrayList<>();
        }
        return listaTuJuzgado;
    }

    public void setListaTuJuzgado(List<TuJuzgado> listaTuJuzgado) {
        this.listaTuJuzgado = listaTuJuzgado;
    }

    public List<TuTutelaItem> getListaTuTutelaItem() {
        if(listaTuTutelaItem == null){
            listaTuTutelaItem = new ArrayList<>();
        }
        return listaTuTutelaItem;
    }

    public void setListaTuTutelaItem(List<TuTutelaItem> listaTuTutelaItem) {
        this.listaTuTutelaItem = listaTuTutelaItem;
    }

    public List<TuAdjunto> getTuAdjuntosList() {
        if(tuAdjuntosList == null){
            tuAdjuntosList = new ArrayList<>();
        }
        return tuAdjuntosList;
    }

    public void setTuAdjuntosList(List<TuAdjunto> tuAdjuntosList) {
        this.tuAdjuntosList = tuAdjuntosList;
    }

    public List<TuSeguimiento> getTuSeguimientosList() {
        if(tuSeguimientosList == null){
            tuSeguimientosList = new ArrayList<>();
        }
        return tuSeguimientosList;
    }

    public void setTuSeguimientosList(List<TuSeguimiento> tuSeguimientosList) {
        this.tuSeguimientosList = tuSeguimientosList;
    }

    public Integer[] getListaFiltrosRepresentanteDemandado() {
        return listaFiltrosRepresentanteDemandado;
    }

    public void setListaFiltrosRepresentanteDemandado(Integer[] listaFiltrosRepresentanteDemandado) {
        this.listaFiltrosRepresentanteDemandado = listaFiltrosRepresentanteDemandado;
    }

    public Integer[] getListaFiltrosJuzgado() {
        return listaFiltrosJuzgado;
    }

    public void setListaFiltrosJuzgado(Integer[] listaFiltrosJuzgado) {
        this.listaFiltrosJuzgado = listaFiltrosJuzgado;
    }
    
    public Boolean getBorrado() {
        return borrado;
    }

    public void setBorrado(Boolean borrado) {
        this.borrado = borrado;
    }

    public String getUsuarioBorra() {
        return usuarioBorra;
    }

    public void setUsuarioBorra(String usuarioBorra) {
        this.usuarioBorra = usuarioBorra;
    }

    public String getTerminalBorra() {
        return terminalBorra;
    }

    public void setTerminalBorra(String terminalBorra) {
        this.terminalBorra = terminalBorra;
    }

    public Date getFechaHoraBorra() {
        return fechaHoraBorra;
    }

    public void setFechaHoraBorra(Date fechaHoraBorra) {
        this.fechaHoraBorra = fechaHoraBorra;
    }

    public int getTipoAuditorInicial() {
        return tipoAuditorInicial;
    }

    public void setTipoAuditorInicial(int tipoAuditorInicial) {
        this.tipoAuditorInicial = tipoAuditorInicial;
    }
    
    public int getPos() {
        return pos;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }

    @Override
    public String toString() {
        return "TuTutelaEstado{" + "id=" + id + ", tuTutelaId=" + tuTutelaId + ", tuJuzgadoId=" + tuJuzgadoId + ", maeEstadoId=" + maeEstadoId + ", maeEstadoCodigo=" + maeEstadoCodigo + ", maeEstadoValor=" + maeEstadoValor + ", maeProcesoId=" + maeProcesoId + ", maeProcesoCodigo=" + maeProcesoCodigo + ", maeProcesoValor=" + maeProcesoValor + ", responsableGnUsuarioId=" + responsableGnUsuarioId + ", gestorGnUsuarioId=" + gestorGnUsuarioId + ", abogadoGnUsuarioId=" + abogadoGnUsuarioId + ", medicoGnUsuarioId=" + medicoGnUsuarioId + ", maeClaseSancionId=" + maeClaseSancionId + ", maeClaseSancionCodigo=" + maeClaseSancionCodigo + ", maeClaseSancionValor=" + maeClaseSancionValor + ", maeClaseArrestoId=" + maeClaseArrestoId + ", maeClaseArrestoCodigo=" + maeClaseArrestoCodigo + ", maeClaseArrestoValor=" + maeClaseArrestoValor + ", cuantia=" + cuantia + ", diasArresto=" + diasArresto + ", uvt=" + uvt + ", emailGestorRemitente=" + emailGestorRemitente + ", entregaSucesiva=" + entregaSucesiva + ", exoneracion=" + exoneracion + ", impugnacion=" + impugnacion + ", integralidad=" + integralidad + ", medidadProvisional=" + medidadProvisional + ", maeTipoFalloId=" + maeTipoFalloId + ", maeTipoFalloCodigo=" + maeTipoFalloCodigo + ", maeTipoFalloValor=" + maeTipoFalloValor + ", fechaNotificacion=" + fechaNotificacion + ", fechaVencimiento=" + fechaVencimiento + ", observacion=" + observacion + ", cantidadServicio=" + cantidadServicio + ", cantidadServicioCumplido=" + cantidadServicioCumplido + ", diasCambioEstado=" + diasCambioEstado + ", listaTuJuzgado=" + listaTuJuzgado + ", listaTuTutelaItem=" + listaTuTutelaItem + '}';
    }
    
    public String establecerColorItemsCerrados() {
        String color = "white";
        try{
            if (getCantidadItems() != null && getCantidadItemsCerrados() != null) {
                if (getCantidadItems() == getCantidadItemsCerrados()) {
                    color = "green";
                } else {
                    color = "red";
                }
            }
        } catch (Exception e) {
            
        }
        return color;
    }

    /**
     * @return the cantidadItems
     */
    public Integer getCantidadItems() {
        return cantidadItems;
    }

    /**
     * @param cantidadItems the cantidadItems to set
     */
    public void setCantidadItems(Integer cantidadItems) {
        this.cantidadItems = cantidadItems;
    }

    /**
     * @return the cantidadItemCerrados
     */
    public Integer getCantidadItemsCerrados() {
        return cantidadItemsCerrados;
    }

    /**
     * @param cantidadItemCerrados the cantidadItemCerrados to set
     */
    public void setCantidadItemsCerrados(Integer cantidadItemsCerrados) {
        this.cantidadItemsCerrados = cantidadItemsCerrados;
    }
    

}
