/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.autorizacion;

import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Stiven Giraldo
 */
public class AuAnexo4Entrega extends Auditoria {

    //Tipos
    public static final String ENTREGA_TOTAL = "Total";
    public static final String ENTREGA_PARCIAL = "Parcial";
    public static final String SIN_ENTREGA = "Sin Entrega";
    public static final String ENTREGA_ANULADA = "Anulada";
    public static final String NO_PRESTADO = "No Prestado";
    public static final int TIPO_TOTAL = 1;
    public static final int TIPO_PARCIAL = 2;
    public static final int TIPO_SIN = 3;
    public static final int TIPO_ANULADA = 4;
    public static final int TIPO_NO_PRESTADO = 5;
    
    //origen
    public static final String ORIGEN_MANUAL_STR = "Manual";
    public static final String ORIGEN_CARGA_MASIVA_STR = "Carga Masiva";
    public static final String ORIGEN_INTEROPERABILIDAD_STR = "Interoperabilidad";
    public static final String ORIGEN_RIPS_STR = "RIPS";
    public static final short ORIGEN_MANUAL = 1;
    public static final short ORIGEN_CARGA_MASIVA = 2;
    public static final short ORIGEN_INTEROPERABILIDAD = 3;
    public static final short ORIGEN_RIPS = 4;

    private Integer id;
    private boolean reclamaAfiliado;
    private Date fechaHoraEntrega;
    private int tipoEntrega;
    private int cantidadAutorizada;
    private int cantidadEntregada;
    private Integer cantidadPendiente;
    private String nombreReclama;
    private String telefonoReclama;
    private String celularReclama;
    private Integer maeCausaNoEntregaId;
    private String maeCausaNoEntregaCodigo;
    private String maeCausaNoEntregaValor;
    private AuAnexo4Item auAnexo4ItemId;
    private AuAnexo4 auAnexo4Id;
    private boolean anulada;
    private String anulaObservacion;
    private String noPrestadoObservacion;
    private short fuenteOrigen;

    public AuAnexo4Entrega() {

    }

    public AuAnexo4Entrega(int id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean isReclamaAfiliado() {
        return reclamaAfiliado;
    }

    public void setReclamaAfiliado(boolean reclamaAfiliado) {
        this.reclamaAfiliado = reclamaAfiliado;
    }

    public Date getFechaHoraEntrega() {
        return fechaHoraEntrega;
    }

    public void setFechaHoraEntrega(Date fechaHoraEntrega) {
        this.fechaHoraEntrega = fechaHoraEntrega;
    }

    public int getTipoEntrega() {
        return tipoEntrega;
    }

    public void setTipoEntrega(int tipoEntrega) {
        this.tipoEntrega = tipoEntrega;
    }

    public int getCantidadAutorizada() {
        return cantidadAutorizada;
    }

    public void setCantidadAutorizada(int cantidadAutorizada) {
        this.cantidadAutorizada = cantidadAutorizada;
    }

    public int getCantidadEntregada() {
        return cantidadEntregada;
    }

    public void setCantidadEntregada(int cantidadEntregada) {
        this.cantidadEntregada = cantidadEntregada;
    }

    public Integer getCantidadPendiente() {
        return cantidadPendiente;
    }

    public void setCantidadPendiente(Integer cantidadPendiente) {
        this.cantidadPendiente = cantidadPendiente;
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

    public Integer getMaeCausaNoEntregaId() {
        return maeCausaNoEntregaId;
    }

    public void setMaeCausaNoEntregaId(Integer maeCausaNoEntregaId) {
        this.maeCausaNoEntregaId = maeCausaNoEntregaId;
    }

    public String getMaeCausaNoEntregaCodigo() {
        return maeCausaNoEntregaCodigo;
    }

    public void setMaeCausaNoEntregaCodigo(String maeCausaNoEntregaCodigo) {
        this.maeCausaNoEntregaCodigo = maeCausaNoEntregaCodigo;
    }

    public String getMaeCausaNoEntregaValor() {
        return maeCausaNoEntregaValor;
    }

    public void setMaeCausaNoEntregaValor(String maeCausaNoEntregaValor) {
        this.maeCausaNoEntregaValor = maeCausaNoEntregaValor;
    }

    public AuAnexo4Item getAuAnexo4ItemId() {
        return auAnexo4ItemId;
    }

    public void setAuAnexo4ItemId(AuAnexo4Item auAnexo4ItemId) {
        this.auAnexo4ItemId = auAnexo4ItemId;
    }

    public AuAnexo4 getAuAnexo4Id() {
        return auAnexo4Id;
    }

    public void setAuAnexo4Id(AuAnexo4 auAnexo4Id) {
        this.auAnexo4Id = auAnexo4Id;
    }

    public boolean isAnulada() {
        return anulada;
    }

    public void setAnulada(boolean anulada) {
        this.anulada = anulada;
    }

    public String getAnulaObservacion() {
        return anulaObservacion;
    }

    public void setAnulaObservacion(String anulaObservacion) {
        this.anulaObservacion = anulaObservacion;
    }

    public String getNoPrestadoObservacion() {
        return noPrestadoObservacion;
    }

    public void setNoPrestadoObservacion(String noPrestadoObservacion) {
        this.noPrestadoObservacion = noPrestadoObservacion;
    }
    
    public short getFuenteOrigen() {
        return fuenteOrigen;
    }

    public void setFuenteOrigen(short fuenteOrigen) {
        this.fuenteOrigen = fuenteOrigen;
    }

    public String obtenerTipoEntrega() {
        String texto = "";
        switch (tipoEntrega) {
            case TIPO_TOTAL:
                texto = ENTREGA_TOTAL;
                break;
            case TIPO_PARCIAL:
                texto = ENTREGA_PARCIAL;
                break;
            case TIPO_SIN:
                texto = SIN_ENTREGA;
                break;
            case TIPO_ANULADA:
                texto = ENTREGA_ANULADA;
                break;
            case TIPO_NO_PRESTADO:
                texto = NO_PRESTADO;
                break;
            default:
                break;
        }
        return texto;
    }

    public String getFuenteOrigenStr() {
        switch (fuenteOrigen) {
            case ORIGEN_MANUAL:
                return ORIGEN_MANUAL_STR;
            case ORIGEN_CARGA_MASIVA:
                return ORIGEN_CARGA_MASIVA_STR;
            case ORIGEN_INTEROPERABILIDAD:
                return ORIGEN_INTEROPERABILIDAD_STR;
            case ORIGEN_RIPS:
                return ORIGEN_RIPS_STR;
            default:
                return "";
        }
    }

    @Override
    public String toString() {
        return "AuAnexo4Entrega{" + "id=" + id + ", reclamaAfiliado=" + reclamaAfiliado + ", fechaHoraEntrega=" + fechaHoraEntrega + ", tipoEntrega=" + tipoEntrega + ", cantidadAutorizada=" + cantidadAutorizada + ", cantidadEntregada=" + cantidadEntregada + ", cantidadPendiente=" + cantidadPendiente + ", nombreReclama=" + nombreReclama + ", telefonoReclama=" + telefonoReclama + ", celularReclama=" + celularReclama + ", maeCausaNoEntregaId=" + maeCausaNoEntregaId + ", maeCausaNoEntregaCodigo=" + maeCausaNoEntregaCodigo + ", maeCausaNoEntregaValor=" + maeCausaNoEntregaValor + ", auAnexo4ItemId=" + auAnexo4ItemId + ", auAnexo4Id=" + auAnexo4Id + '}';
    }

    public String obtenerFechaEntrega() {
        Date fecha = getFechaHoraEntrega();
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        if (fecha != null) {
            return formato.format(fecha);
        } else {
            return "";
        }

    }

}
