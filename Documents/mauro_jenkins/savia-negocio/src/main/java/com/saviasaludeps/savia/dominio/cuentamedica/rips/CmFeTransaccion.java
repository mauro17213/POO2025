/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.saviasaludeps.savia.dominio.cuentamedica.rips;

import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.util.Date;
import java.util.Optional;


public class CmFeTransaccion extends Auditoria {

    public static final int ESTADO_EXITORO = 1;
    public static final int ESTADO_FALLIDO = 2;
    
    private Integer id;
    private byte[] jsonEnvio;
    private Date fechaHoraEnvio;
    private byte[] jsonRespuesta;
    private Date fechaHoraRespuesta;
    private Integer estado;
    private CmFeRipsCarga cmFeRipsCarga;

    public CmFeTransaccion() {
    }

    public CmFeTransaccion(Integer id) {
        this.id = id;
    }

    public CmFeTransaccion(Integer id, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.usuarioCrea = usuarioCrea;
        this.terminalCrea = terminalCrea;
        this.fechaHoraCrea = fechaHoraCrea;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public CmFeRipsCarga getCmFeRipsCarga() {
        return cmFeRipsCarga;
    }

    public void setCmFeRipsCarga(CmFeRipsCarga cmFeRipsCarga) {
        this.cmFeRipsCarga = cmFeRipsCarga;
    } 

    public String getEstadoStr() {
        return CmFeTransaccion.getEstadoStr(getEstado());
    }
     
   public static String getEstadoStr( Integer estado ) {
         String strEstado = "";
         estado = Optional.ofNullable(estado).orElse(-1);
          switch (estado) {
             case CmFeTransaccion.ESTADO_EXITORO:
                 strEstado = "Exitoso";
                 break;
            case CmFeTransaccion.ESTADO_FALLIDO:
                 strEstado = "Fallido";
                 break;
          }
          
          return strEstado;
   }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }
    
     public String getJsonEnvioStr() {
        String cadena = "";
        if (jsonEnvio != null) {
            cadena =  new String(jsonEnvio);
        } 
       return cadena;
    }
     
    public String getJsonRespuestaStr() {
        String cadena = "";
        if (jsonRespuesta != null) {
            cadena =  new String(jsonRespuesta);
        }
        return cadena;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CmFeTransaccion)) {
            return false;
        }
        CmFeTransaccion other = (CmFeTransaccion) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.CmFeTransacciones[ id=" + id + " ]";
    }
    
}
