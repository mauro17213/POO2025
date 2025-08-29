
package com.saviasaludeps.savia.dominio.cuentamedica.wstransaccion;

import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.util.Date;

public class WsCmTransaccionDetalle extends Auditoria {
    
    public static final short ESTADO_CREADA  = 1;
    public static final short ESTADO_ENVIADA = 2;
    public static final short ESTADO_RESPUESTA_EXITOSA = 3;
    public static final short ESTADO_RESPUESTA_FALLIDA = 4;
    
    public final static int LONGITUD_MENSAJE_RESPUESTA = 512;
    
    public static final short CODIGO_RESPUESTA_EXITOSO  = 1;
    public static final short CODIGO_RESPUESTA_FALLO    = 2;

    private Integer id;
    private short estado;
    private byte[] jsonEnvio;
    private Date fechaHoraEnvio;
    private byte[] jsonRespuesta;
    private Date fechaHoraRespuesta;
    private short codigoRetorno;
    private short codigoRespuesta;
    private String mensajeRespuesta;
    private WsCmTransaccion wsCmTransaccion;

    public WsCmTransaccionDetalle() {
        wsCmTransaccion = new WsCmTransaccion();
    }

    public WsCmTransaccionDetalle(Integer id) {
        this.id = id;
        wsCmTransaccion = new WsCmTransaccion();
    }

    public WsCmTransaccionDetalle(Integer id, short estado, byte[] jsonEnvio, Date fechaHoraEnvio) {
        this.id = id;
        this.estado = estado;
        this.jsonEnvio = jsonEnvio;
        this.fechaHoraEnvio = fechaHoraEnvio;
        wsCmTransaccion = new WsCmTransaccion();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public short getEstado() {
        return estado;
    }

    public void setEstado(short estado) {
        this.estado = estado;
    }

    public byte[] getJsonEnvio() {
        return jsonEnvio;
    }

    public void setJsonEnvio(byte[] jsonEnvio) {
        this.jsonEnvio = jsonEnvio;
    }

    public Date getFechaHoraEnvio() {
        return fechaHoraEnvio;
    }

    public void setFechaHoraEnvio(Date fechaHoraEnvio) {
        this.fechaHoraEnvio = fechaHoraEnvio;
    }

    public byte[] getJsonRespuesta() {
        return jsonRespuesta;
    }

    public void setJsonRespuesta(byte[] jsonRespuesta) {
        this.jsonRespuesta = jsonRespuesta;
    }

    public Date getFechaHoraRespuesta() {
        return fechaHoraRespuesta;
    }

    public void setFechaHoraRespuesta(Date fechaHoraRespuesta) {
        this.fechaHoraRespuesta = fechaHoraRespuesta;
    }

    public short getCodigoRetorno() {
        return codigoRetorno;
    }

    public void setCodigoRetorno(short codigoRetorno) {
        this.codigoRetorno = codigoRetorno;
    }

    public short getCodigoRespuesta() {
        return codigoRespuesta;
    }

    public void setCodigoRespuesta(short codigoRespuesta) {
        this.codigoRespuesta = codigoRespuesta;
    }

    public String getMensajeRespuesta() {
        return mensajeRespuesta;
    }

    public void setMensajeRespuesta(String mensajeRespuesta) {
        this.mensajeRespuesta = mensajeRespuesta;
    }
    
    public WsCmTransaccion getWsCmTransaccion() {
        return wsCmTransaccion;
    }

    public void setWsCmTransaccion(WsCmTransaccion wsCmTransaccion) {
        this.wsCmTransaccion = wsCmTransaccion;
    }
    
    public String getEstadoStr() {
        return WsCmTransaccionDetalle.getEstadoStr(getEstado());
    }
    
    public static String getEstadoStr(int estado) {
        String str;
        switch (estado) {
            case ESTADO_CREADA:
                str = "Creada";
                break;
            case ESTADO_ENVIADA:
                str = "Enviada";
                break;
             case ESTADO_RESPUESTA_EXITOSA:
                str = "Respuesta Exitosa";
                break; 
             case ESTADO_RESPUESTA_FALLIDA:
                str = "Respuesta Fallida";
                break; 
            default:
                str = "";
                break;
        }
        return str;
    }

    public String getCodigoRespuestaStr() {
        return WsCmTransaccionDetalle.getCodigoRespuestaStr(getCodigoRespuesta());
    }
    
    public static String getCodigoRespuestaStr(int codigoRespuesta) {
        String str;
        switch (codigoRespuesta) {
            case CODIGO_RESPUESTA_FALLO:
                str = "Paquete con fallos o novedades.";
                break;
            case CODIGO_RESPUESTA_EXITOSO:
                str = "Paquete procesado por sap exitosamente.";
                break;
            default:
                str = "";
                break;
        }
        return str;
    }
    
    public boolean existeWsCmTransaccionDetalle() {
        return this.getId() != null && this.getId() > 0;
    }
    
     public String getJsonEnvioStr() {
        String cadena = "";
        if (getJsonEnvio() != null) {
            cadena =  new String(getJsonEnvio());
        } 
       return cadena;
    }
     
      public String getJsonRespuestaStr() {
        String cadena = "";
        if (getJsonRespuesta() != null) {
            cadena =  new String(getJsonRespuesta());
        }
        return cadena;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof WsCmTransaccionDetalle)) {
            return false;
        }
        WsCmTransaccionDetalle other = (WsCmTransaccionDetalle) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "WsCmTransaccionDetalle{" + "id=" + id + ", estado=" + estado + ", jsonEnvio=" + jsonEnvio + ", fechaHoraEnvio=" + fechaHoraEnvio + ", jsonRespuesta=" + jsonRespuesta + ", fechaHoraRespuesta=" + fechaHoraRespuesta + ", codigoRetorno=" + codigoRetorno + ", codigoRespuesta=" + codigoRespuesta + ", mensajeRespuesta=" + mensajeRespuesta + '}';
    }

    
    
}
