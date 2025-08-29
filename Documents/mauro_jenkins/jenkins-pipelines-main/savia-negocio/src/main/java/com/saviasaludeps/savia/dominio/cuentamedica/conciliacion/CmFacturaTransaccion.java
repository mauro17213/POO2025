/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.saviasaludeps.savia.dominio.cuentamedica.conciliacion;

import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.util.Date;

/**
 *
 * @author jeperez
 */

public class CmFacturaTransaccion  extends Auditoria {

    private static final long serialVersionUID = 1L;
    public static final int TAMANIO_OBSERVACION = 20;
    public static final int ESTADO_ENVIADA   = 0;
    public static final int ESTADO_RESPUESTA = 1;
    
    public static final int TIPO_MOMENTO0 = 0;
    public static final int TIPO_MOMENTO1 = 1;
    public static final int TIPO_MOMENTO2 = 2;
    public static final int TIPO_MOMENTO3 = 3;
    public static final int TIPO_MOMENTO4 = 4;
    public static final int TIPO_MOMENTO5 = 5;
    public static final int TIPO_MOMENTO6 = 6;




    private Integer id;
    private int tipo;
    private int estado;
    private int estadoAnterior;
    private Date fechaHoraInicio;
    private Date fechaHoraFin;
    private Integer respuestaCodigo;
    private String respuestaDescripcion;
    private CmFactura cmFactura;

    public CmFacturaTransaccion() {
    }

    public CmFacturaTransaccion(Integer id) {
        this.id = id;
    }

    public CmFacturaTransaccion(Integer id, int tipo, int estado, Date fechaHoraInicio) {
        this.id = id;
        this.tipo = tipo;
        this.estado = estado;
        this.fechaHoraInicio = fechaHoraInicio;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
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

    public Integer getRespuestaCodigo() {
        return respuestaCodigo;
    }

    public void setRespuestaCodigo(Integer respuestaCodigo) {
        this.respuestaCodigo = respuestaCodigo;
    }

    public String getRespuestaDescripcion() {
        return respuestaDescripcion;
    }

    public void setRespuestaDescripcion(String respuestaDescripcion) {
        this.respuestaDescripcion = respuestaDescripcion;
    }

    public CmFactura getCmFactura() {
        if(cmFactura==null){
            cmFactura = new CmFactura();
        }
        return cmFactura;
    }

    public void setCmFactura(CmFactura cmFactura) {
        this.cmFactura = cmFactura;
    }

 
     public String getRespuestaDescripcionCorto (){
        if (respuestaDescripcion != null && respuestaDescripcion.length() >= TAMANIO_OBSERVACION) {
            return respuestaDescripcion.substring(0, TAMANIO_OBSERVACION) + "...   ";
        } else {
            return respuestaDescripcion;
        }   
    }

    public int getEstadoAnterior() {
        return estadoAnterior;
    }

    public void setEstadoAnterior(int estadoAnterior) {
        this.estadoAnterior = estadoAnterior;
    }
     
    public String getEstadoStr() {
        return CmFacturaTransaccion.getEstadoStr(getEstado());
    }
    
    public static String getEstadoStr(int estado) {
        String str;
        switch (estado) {
            case ESTADO_ENVIADA:
                str = "Enviada";
                break;
            case ESTADO_RESPUESTA:
                str = "Respuesta";
                break;
            default:
                str = "";
                break;
        }
        return str;
    }
   
    public String getTipoStr() {
        return CmFacturaTransaccion.getTipoStr(getTipo());
    }
    
    public static String getTipoStr(int tipo) {
        String str;
        switch (tipo) {
            case TIPO_MOMENTO0:
                str = "Devolucion";
                break;
            case TIPO_MOMENTO1:
                str = "Momento1(Provision)";
                break;
            case TIPO_MOMENTO2:
                str = "Momento2(Auditoria)";
                break;
            case TIPO_MOMENTO3:
                str = "Momento3(respuesta glosa)";
                break;
            case TIPO_MOMENTO4:
                str = "Momento4(Conciliacion)";
                break;
            case TIPO_MOMENTO5:
                str = "Momento5(Recobro)";
                break;
            case TIPO_MOMENTO6:
                str = "Momento6(Pago)";
                break;
            default:
                str = "";
                break;
        }
        return str;
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
        if (!(object instanceof CmFacturaTransaccion)) {
            return false;
        }
        CmFacturaTransaccion other = (CmFacturaTransaccion) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CmFacturaTransaccion{" + "id=" + id + ", tipo=" + tipo + ", estado=" + estado + ", fechaHoraInicio=" + fechaHoraInicio + ", fechaHoraFin=" + fechaHoraFin + ", respuestaCodigo=" + respuestaCodigo + ", respuestaDescripcion=" + respuestaDescripcion + ", cmFactura id =" + cmFactura.getId() + '}';
    }

    
    
}
