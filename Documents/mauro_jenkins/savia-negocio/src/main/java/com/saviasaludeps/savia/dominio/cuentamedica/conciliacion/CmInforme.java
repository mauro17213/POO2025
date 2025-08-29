/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.cuentamedica.conciliacion;

import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;


public class CmInforme extends Auditoria implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private Integer tipo;
    private int estado;
    private Date fechaDesde;
    private Date fechaHasta;
    private Date fechaHoraInicio;
    private Date fechaHoraFin;
    private int registros;
    private String ruta;
    private String archivo;
    private String observacion;
    
    public final static int TIPO_INFORME_MOTIVO = 0;
    public final static int TIPO_INFORME_RESPUESTA_DETALLES = 1;
    public final static int TIPO_INFORME_DETALLES_NO_RESP = 2;
    public final static int TIPO_INFORME_RESPUESTA_POR_DETALLES = 3;
    public final static int TIPO_INFORME_AUDITORIA = 4;
    public final static int TIPO_INFORME_GENERAL_CUENTAS_MEDICAS = 5;
    public final static int TIPO_INFORME_GENERAL_CUENTAS_MEDICAS_DETALLES = 6;
    public final static int TIPO_INFORME_DEVOLUCION_FACTURAS = 7;
    public final static int TIPO_INFORME_FACTURAS_CONCILIADAS = 8;
    public final static int TIPO_INFORME_FACTURAS_RECHAZADAS  = 9;
    public final static int TIPO_INFORME_DETALES_DESCUENTO_CAPITA = 10;
    
    
    public final static int ESTADO_PROCESO = 0;
    public final static int ESTADO_FINALIZADO = 1;
    public final static int ESTADO_ABORTADO = 2;
    public final static int ESTADO_ERROR = 3;
    
    private String nit;
    private String ips;
  
    public CmInforme() {
    }

    public CmInforme(Integer id) {
        this.id = id;
    }

    public CmInforme(Integer id, int tipo, int estado, Date fechaDesde, Date fechaHasta, Date fechaHoraInicio, Date fechaHoraFin, int registros) {
        this.id = id;
        this.tipo = tipo;
        this.estado = estado;
        this.fechaDesde = fechaDesde;
        this.fechaHasta = fechaHasta;
        this.fechaHoraInicio = fechaHoraInicio;
        this.fechaHoraFin = fechaHoraFin;
        this.registros = registros;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTipo() {
        return tipo;
    }

    public void setTipo(Integer tipo) {
        this.tipo = tipo;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public Date getFechaDesde() {
        return fechaDesde;
    }

    public void setFechaDesde(Date fechaDesde) {
        this.fechaDesde = fechaDesde;
    }

    public Date getFechaHasta() {
        return fechaHasta;
    }

    public void setFechaHasta(Date fechaHasta) {
        this.fechaHasta = fechaHasta;
    }

    public Date getFechaHoraInicio() {
        return fechaHoraInicio;
    }

    public void setFechaHoraInicio(Date fechaHoraInicio) {
        this.fechaHoraInicio = fechaHoraInicio;
    }

    public Date getFechaHoraFin() {
        return fechaHoraFin;
    }

    public void setFechaHoraFin(Date fechaHoraFin) {
        this.fechaHoraFin = fechaHoraFin;
    }

    public int getRegistros() {
        return registros;
    }

    public void setRegistros(int registros) {
        this.registros = registros;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public String getArchivo() {
        return archivo;
    }

    public void setArchivo(String archivo) {
        this.archivo = archivo;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }
    
    public String getTipoStr() {
         return CmInforme.getTipoStr(this.getTipo());
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getIps() {
        return ips;
    }

    public void setIps(String ips) {
        this.ips = ips;
    }
    
    public static String getTipoStr(int tipo) {
        String str;
        switch (tipo) {
            case TIPO_INFORME_MOTIVO:
                str = "Informe Motivos ";
                break;
            case TIPO_INFORME_RESPUESTA_DETALLES:
                str = "Informe Respuesta Detalles ";
                break;
            case TIPO_INFORME_DETALLES_NO_RESP:
                str = "Informe Detalles Sin Respuesta ";
                break;
            case TIPO_INFORME_RESPUESTA_POR_DETALLES:
                str = "Informe Respuesta Por Detalles ";
                break;
            case TIPO_INFORME_AUDITORIA:
                str = "Informe Auditoria ";
                break;
            case TIPO_INFORME_GENERAL_CUENTAS_MEDICAS:
                str = "Informe General Facturas CM ";
                break;
            case TIPO_INFORME_GENERAL_CUENTAS_MEDICAS_DETALLES:
                str = "Informe General Detalles CM ";
                break;
            case TIPO_INFORME_DEVOLUCION_FACTURAS:
                str = "Informe Devolucion Facturas ";
                break;
            case TIPO_INFORME_FACTURAS_CONCILIADAS:
                str = "Informe Facturas Conciliadas";
                break;
            case TIPO_INFORME_FACTURAS_RECHAZADAS:
                str = "Informe Facturas Rechazadas ";
                break;
            case TIPO_INFORME_DETALES_DESCUENTO_CAPITA:
                str = "Informe Detalles Descuento Capita ";
                break;
            default:
                str = "";
                break;
        }
        return str;
    }

    public String getEstadoStr() {
         return CmInforme.getEstadoStr(this.getEstado());
    }
    
    public static String getEstadoStr(int tipo) {
        String str;
        switch (tipo) {
            case ESTADO_PROCESO:
                str = "En proceso ";
                break;
            case ESTADO_FINALIZADO:
                str = "Finalizado ";
                break;
            case ESTADO_ABORTADO:
                str = "Cancelado ";
                break;
            case ESTADO_ERROR:
                str = "Error ";
                break;
            default:
                str = "";
                break;
        }
        return str;
    }
    
    public String getAsignarcionNombre(){
        String nombre;
        SimpleDateFormat sdf = new SimpleDateFormat("YYYYMMddHHmm");
        String postname = sdf.format(new Date());
        postname = getId() != null ? getId() + "_" + postname : postname;
        switch (this.getTipo()) {
            case TIPO_INFORME_MOTIVO:
                nombre = "Motivos_" + postname + ".txt";
                break;
            case TIPO_INFORME_RESPUESTA_POR_DETALLES:
                nombre = "RespuestaPorDetalles_" + postname + ".txt";
                break;
            case TIPO_INFORME_DETALLES_NO_RESP:
                nombre = "DetallesSinRespuesta_" + postname + ".txt";
                break;
            case TIPO_INFORME_RESPUESTA_DETALLES:
                nombre = "RespuestaDetalles_" + postname + ".txt";
                break;
            case TIPO_INFORME_AUDITORIA:
                nombre = "Auditoria_" + postname + ".txt";
                break;
            case TIPO_INFORME_GENERAL_CUENTAS_MEDICAS:
                nombre = "InformeGeneral_CM_" + postname + ".txt";
                break;
             case TIPO_INFORME_GENERAL_CUENTAS_MEDICAS_DETALLES:
                nombre = "InformeGeneral_CM_Detalles_" + postname + ".txt";
                break;
             case TIPO_INFORME_DEVOLUCION_FACTURAS:
                nombre = "InformeDevolucion_" + postname + ".txt";
                break;
            case TIPO_INFORME_FACTURAS_CONCILIADAS:
                nombre = "InformeConciliadas_" + postname + ".txt";
                break;
            case TIPO_INFORME_FACTURAS_RECHAZADAS:
                nombre = "InformeRechazadas_" + postname + ".txt";
                break;
            case TIPO_INFORME_DETALES_DESCUENTO_CAPITA:
                nombre = "InformeDescuentoCapita_" + postname + ".txt";
                break;
            default:
                nombre = "";
                break;
      }
      return nombre;
    } 
       

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof CmInforme)) {
            return false;
        }
        CmInforme other = (CmInforme) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CmInforme{" + "id=" + id + ", tipo=" + tipo + ", fechaDesde=" + fechaDesde + ", fechaHasta=" + fechaHasta + ", ruta=" + ruta + ", archivo=" + archivo + ", nit=" + nit + ", ips=" + ips + '}';
    }
 
}
