/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.autorizacion;

import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.util.Date;
import java.util.List;

/**
 *
 * @author NEXOS
 */
public class AuNoSolicitudEntrega extends Auditoria{
    
    //fuene origen
    public static final int FUENTE_ORIGEN_MANUAL = 1;
    public static final int FUENTE_ORIGEN_CARGA_MASIVA = 2;
    public static final int FUENTE_ORIGEN_INTEROPERABILIDAD = 3; 
    public static final int FUENTE_ORIGEN_RIPS = 4;
    
    //fuene origen
    public static final int TIPO_NO_ENTREGADO = 0;
    public static final int TIPO_ENTREGA_TOTAL = 1;
    public static final int TIPO_ENTREGA_PARCIAL = 2; 
    public static final int TIPO_ENTREGA_RECLAMA_SIN_ENTREGAS = 3; 
    public static final int TIPO_ENTREGA_ANULADO = 4; 
    public static final int TIPO_ENTREGA_NO_PRESTADO = 5; 
    
    private Integer id;
    private AuNoSolicitud auNoSolicitudesId;
    private AuNoSolicitudItem auNoSolicitudItemsId;
    private AuNoSolicitudEntregaDetalle auNoSolicitudEntregaDetallesId;
    private int fuenteOrigen;
    private int tipoEntrega;
    private int cantidadPorEntregar;
    private Integer cantidadEntregada;
    private Integer cantidadPendiente;
    private boolean reclamaAfiliado;
    private String nombreReclama;
    private String telefonoReclama;
    private String celularReclama;
    private Integer maeCausaMoEntregaId;
    private String maeCausaMoEntregaCodigo;
    private String maeCausaMoEntregaValor;
    private String maeCausaMoEntregaTipo;
    private boolean anulada;
    private String anuladaObservacion;
    private String noPrestadoObservacion;
    private Date fechaHoraEntrega;
    private Integer numeroEntrega;
    private Integer faltantes;
    
    //listas
    private List<AuSolicitudAdjunto> listAuSolicitudAdjunto;
    private List<AuNoSolicitudEntregaDetalle> listAuNoSolicitudEntregaDetalle;
    
    //variable auciliares
    private int posicion;
    private boolean habilitarCausaNoEntrega;
    private int sumatoriaCantidadAEntregar;
    
    public AuNoSolicitudEntrega() {
    }

    public AuNoSolicitudEntrega(Integer id) {
        this.id = id;
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public AuNoSolicitud getAuNoSolicitudesId() {
        return auNoSolicitudesId;
    }

    public void setAuNoSolicitudesId(AuNoSolicitud auNoSolicitudesId) {
        this.auNoSolicitudesId = auNoSolicitudesId;
    }

    public AuNoSolicitudItem getAuNoSolicitudItemsId() {
        return auNoSolicitudItemsId;
    }

    public void setAuNoSolicitudItemsId(AuNoSolicitudItem auNoSolicitudItemsId) {
        this.auNoSolicitudItemsId = auNoSolicitudItemsId;
    }

    public int getFuenteOrigen() {
        return fuenteOrigen;
    }

    public void setFuenteOrigen(int fuenteOrigen) {
        this.fuenteOrigen = fuenteOrigen;
    }

    public int getTipoEntrega() {
        return tipoEntrega;
    }

    public void setTipoEntrega(int tipoEntrega) {
        this.tipoEntrega = tipoEntrega;
    }

    public int getCantidadPorEntregar() {
        return cantidadPorEntregar;
    }

    public void setCantidadPorEntregar(int cantidadPorEntregar) {
        this.cantidadPorEntregar = cantidadPorEntregar;
    }

    public Integer getCantidadEntregada() {
        return cantidadEntregada;
    }

    public void setCantidadEntregada(Integer cantidadEntregada) {
        this.cantidadEntregada = cantidadEntregada;
    }

    public Integer getCantidadPendiente() {
        return cantidadPendiente;
    }

    public void setCantidadPendiente(Integer cantidadPendiente) {
        this.cantidadPendiente = cantidadPendiente;
    }

    public boolean isReclamaAfiliado() {
        return reclamaAfiliado;
    }

    public void setReclamaAfiliado(boolean reclamaAfiliado) {
        this.reclamaAfiliado = reclamaAfiliado;
    }

    public String getNombreReclama() {
        return nombreReclama;
    }

    public void setNombreReclama(String nombreReclama) {
        this.nombreReclama = nombreReclama;
    }

    public String getTelefonoReclama() {
        return telefonoReclama;
    }

    public void setTelefonoReclama(String telefonoReclama) {
        this.telefonoReclama = telefonoReclama;
    }

    public String getCelularReclama() {
        return celularReclama;
    }

    public void setCelularReclama(String celularReclama) {
        this.celularReclama = celularReclama;
    }

    public Integer getMaeCausaMoEntregaId() {
        return maeCausaMoEntregaId;
    }

    public void setMaeCausaMoEntregaId(Integer maeCausaMoEntregaId) {
        this.maeCausaMoEntregaId = maeCausaMoEntregaId;
    }

    public String getMaeCausaMoEntregaCodigo() {
        return maeCausaMoEntregaCodigo;
    }

    public void setMaeCausaMoEntregaCodigo(String maeCausaMoEntregaCodigo) {
        this.maeCausaMoEntregaCodigo = maeCausaMoEntregaCodigo;
    }

    public String getMaeCausaMoEntregaValor() {
        return maeCausaMoEntregaValor;
    }

    public void setMaeCausaMoEntregaValor(String maeCausaMoEntregaValor) {
        this.maeCausaMoEntregaValor = maeCausaMoEntregaValor;
    }

    public String getMaeCausaMoEntregaTipo() {
        return maeCausaMoEntregaTipo;
    }

    public void setMaeCausaMoEntregaTipo(String maeCausaMoEntregaTipo) {
        this.maeCausaMoEntregaTipo = maeCausaMoEntregaTipo;
    }

    public boolean isAnulada() {
        return anulada;
    }

    public void setAnulada(boolean anulada) {
        this.anulada = anulada;
    }

    public String getAnuladaObservacion() {
        return anuladaObservacion;
    }

    public void setAnuladaObservacion(String anuladaObservacion) {
        this.anuladaObservacion = anuladaObservacion;
    }

    public String getNoPrestadoObservacion() {
        return noPrestadoObservacion;
    }

    public void setNoPrestadoObservacion(String noPrestadoObservacion) {
        this.noPrestadoObservacion = noPrestadoObservacion;
    }

    public Date getFechaHoraEntrega() {
        return fechaHoraEntrega;
    }

    public void setFechaHoraEntrega(Date fechaHoraEntrega) {
        this.fechaHoraEntrega = fechaHoraEntrega;
    }

    public Integer getNumeroEntrega() {
        return numeroEntrega;
    }

    public void setNumeroEntrega(Integer numeroEntrega) {
        this.numeroEntrega = numeroEntrega;
    }

    public Integer getFaltantes() {
        return faltantes;
    }

    public void setFaltantes(Integer faltantes) {
        this.faltantes = faltantes;
    }

    public List<AuSolicitudAdjunto> getListAuSolicitudAdjunto() {
        return listAuSolicitudAdjunto;
    }

    public void setListAuSolicitudAdjunto(List<AuSolicitudAdjunto> listAuSolicitudAdjunto) {
        this.listAuSolicitudAdjunto = listAuSolicitudAdjunto;
    }

    public AuNoSolicitudEntregaDetalle getAuNoSolicitudEntregaDetallesId() {
        return auNoSolicitudEntregaDetallesId;
    }

    public void setAuNoSolicitudEntregaDetallesId(AuNoSolicitudEntregaDetalle auNoSolicitudEntregaDetallesId) {
        this.auNoSolicitudEntregaDetallesId = auNoSolicitudEntregaDetallesId;
    }

    public List<AuNoSolicitudEntregaDetalle> getListAuNoSolicitudEntregaDetalle() {
        return listAuNoSolicitudEntregaDetalle;
    }

    public void setListAuNoSolicitudEntregaDetalle(List<AuNoSolicitudEntregaDetalle> listAuNoSolicitudEntregaDetalle) {
        this.listAuNoSolicitudEntregaDetalle = listAuNoSolicitudEntregaDetalle;
    }

    public int getPosicion() {
        return posicion;
    }

    public void setPosicion(int posicion) {
        this.posicion = posicion;
    }

    public boolean isHabilitarCausaNoEntrega() {
        return habilitarCausaNoEntrega;
    }

    public void setHabilitarCausaNoEntrega(boolean habilitarCausaNoEntrega) {
        this.habilitarCausaNoEntrega = habilitarCausaNoEntrega;
    }

    public int getSumatoriaCantidadAEntregar() {
        return sumatoriaCantidadAEntregar;
    }

    public void setSumatoriaCantidadAEntregar(int sumatoriaCantidadAEntregar) {
        this.sumatoriaCantidadAEntregar = sumatoriaCantidadAEntregar;
    }
    
    // Metodo adcionales
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
            case FUENTE_ORIGEN_RIPS:
                fuenteOrigenStr = "Rips";
                break;
            default:
                break;
        }     
        return fuenteOrigenStr;
    }
    
    public String getTipoEntregaStr(){
        String tipoEntregaStr = "";
        switch(tipoEntrega){
            case TIPO_NO_ENTREGADO:
                tipoEntregaStr = "No Entregado";
                break;
            case TIPO_ENTREGA_TOTAL:
                tipoEntregaStr = "Total";
                break;
            case TIPO_ENTREGA_PARCIAL:
                tipoEntregaStr = "Parcial";
                break;
            case TIPO_ENTREGA_RECLAMA_SIN_ENTREGAS:
                tipoEntregaStr = "Reclama sin entregas";
                break;
            case TIPO_ENTREGA_ANULADO:
                tipoEntregaStr = "Anulado";
                break;
            case TIPO_ENTREGA_NO_PRESTADO:
                tipoEntregaStr = "No Prestado";
                break;    
             default:
                break;
        }     
        return tipoEntregaStr;
    }
    
    public String getReclamaAfiliadoStr(){
        String reclamaAfiliadoStr = "No";
        if(reclamaAfiliado){
            reclamaAfiliadoStr = "Si";
        }
        return reclamaAfiliadoStr;
    }

    @Override
    public String toString() {
        return "AuNoSolicitudEntregas{" + "id=" + id + ", auNoSolicitudesId=" + auNoSolicitudesId + ", auNoSolicitudItemsId=" + auNoSolicitudItemsId + ", fuenteOrigen=" + fuenteOrigen + ", tipoEntrega=" + tipoEntrega + ", cantidadPorEntregar=" + cantidadPorEntregar + ", cantidadEntregada=" + cantidadEntregada + ", cantidadPendiente=" + cantidadPendiente + ", reclamaAfiliado=" + reclamaAfiliado + ", nombreReclama=" + nombreReclama + ", telefonoReclama=" + telefonoReclama + ", celularReclama=" + celularReclama + ", maeCausaMoEntregaId=" + maeCausaMoEntregaId + ", maeCausaMoEntregaCodigo=" + maeCausaMoEntregaCodigo + ", maeCausaMoEntregaValor=" + maeCausaMoEntregaValor + ", maeCausaMoEntregaTipo=" + maeCausaMoEntregaTipo + ", anulada=" + anulada + ", anuladaObservacion=" + anuladaObservacion + ", noPrestadoObservacion=" + noPrestadoObservacion + '}';
    }
    
}
