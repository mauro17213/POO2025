/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.cuentamedica.conciliacion;

import com.saviasaludeps.savia.dominio.cuentamedica.auditoria.CmAuditoriaCierre;
import com.saviasaludeps.savia.dominio.cuentamedica.auditoria.CmAuditoriaDevolucion;
import com.saviasaludeps.savia.dominio.cuentamedica.auditoria.CmAuditoriaMasivaN;
import com.saviasaludeps.savia.dominio.cuentamedica.auditoria.CmDevolucionMasivaN;
import com.saviasaludeps.savia.dominio.cuentamedica.rips.CmRipsCarga;
import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.util.Date;
import java.util.Objects;

/**
 *
 * @author Stiven Giraldo
 */
public class CmReintento extends Auditoria {
    
    private int id;
    private int radicado;
    private String numeroFacturas;
    private String estado;
    private String usuario;
    private Date fechaCreacion;
    private String proveedor;
    private String ips;
    private Integer idConciliacionMasiva;
    private Integer idGlosaRespuesta;
    private Integer idAuditoriaDevolucion;
    private Integer idAuditoriaCierre;
    private Integer idFactura;
    private Integer idAuditoriaDevolucionMasiva;
    private Integer idAuditoriaCierreMasivo;
    private Integer idRipCarga;
    private CmConciliacion cmConciliacion;
    private CmGlosaRespuesta cmGlosaRespuesta;
    private CmAuditoriaDevolucion cmAuditoriaDevolucion;
    private CmAuditoriaCierre cmAuditoriaCierre;
    private CmFactura cmFactura;
    private CmAuditoriaMasivaN cmAuditoriaMasiva;
    private CmDevolucionMasivaN cmDevolucionMasiva;
    private CmRipsCarga  CmRipsCarga;
    private CmGlosaRespuesta cmGlosaRespuestaConcialiacionIndividual;
    private Short tipoRelacion;
    private short tipoTransaccion;
    private Boolean estadoRadicado;

    public final static int TIPO_REINTENTO_RESPUESTA_GLOSA = 1;
    public final static int TIPO_REINTENTO_RESPUESTA_CONCILIACION = 2;
    public final static int TIPO_REINTENTO_CIERRE_AUDITORIA = 3;
    public final static int TIPO_REINTENTO_DEVOLUCION_FACTURA = 4;
    public final static int TIPO_REINTENTO_NOTIFICACION_FACTURA = 5;
    public final static int TIPO_REINTENTO_CIERRE_AUDITORIA_MASIVA = 6;
    public final static int TIPO_REINTENTO_DEVOLUCION_FACTURA_MASIVA = 7;

    public int tipoReintento;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRadicado() {
        return radicado;
    }

    public void setRadicado(int radicado) {
        this.radicado = radicado;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getProveedor() {
        return proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }

    public Integer getIdConciliacionMasiva() {
        return idConciliacionMasiva;
    }

    public void setIdConciliacionMasiva(Integer idConciliacionMasiva) {
        this.idConciliacionMasiva = idConciliacionMasiva;
    }

    public Integer getIdGlosaRespuesta() {
        return idGlosaRespuesta;
    }

    public void setIdGlosaRespuesta(Integer idGlosaRespuesta) {
        this.idGlosaRespuesta = idGlosaRespuesta;
    }

    public Integer getIdAuditoriaDevolucionMasiva() {
        return idAuditoriaDevolucionMasiva;
    }

    public void setIdAuditoriaDevolucionMasiva(Integer idAuditoriaDevolucionMasiva) {
        this.idAuditoriaDevolucionMasiva = idAuditoriaDevolucionMasiva;
    }

    public Integer getIdAuditoriaCierreMasivo() {
        return idAuditoriaCierreMasivo;
    }

    public void setIdAuditoriaCierreMasivo(Integer idAuditoriaCierreMasivo) {
        this.idAuditoriaCierreMasivo = idAuditoriaCierreMasivo;
    }

    public Integer getIdRipCarga() {
        return idRipCarga;
    }

    public void setIdRipCarga(Integer idRipCarga) {
        this.idRipCarga = idRipCarga;
    }
    

    public CmConciliacion getCmConciliacion() {
        if (cmConciliacion == null) {
            cmConciliacion = new CmConciliacion();
        }
        return cmConciliacion;
    }

    public void setCmConciliacion(CmConciliacion cmConciliacion) {
        this.cmConciliacion = cmConciliacion;
    }

    public CmGlosaRespuesta getCmGlosaRespuesta() {
        if (cmGlosaRespuesta == null) {
            cmGlosaRespuesta = new CmGlosaRespuesta();
        }
        return cmGlosaRespuesta;
    }

    public void setCmGlosaRespuesta(CmGlosaRespuesta cmGlosaRespuesta) {
        this.cmGlosaRespuesta = cmGlosaRespuesta;
    }

    public CmAuditoriaDevolucion getCmAuditoriaDevolucion() {
        if (cmAuditoriaDevolucion == null) {
            cmAuditoriaDevolucion = new CmAuditoriaDevolucion();
        }
        return cmAuditoriaDevolucion;
    }

    public void setCmAuditoriaDevolucion(CmAuditoriaDevolucion cmAuditoriaDevolucion) {
        this.cmAuditoriaDevolucion = cmAuditoriaDevolucion;
    }

    public CmAuditoriaCierre getCmAuditoriaCierre() {
        if (cmAuditoriaCierre == null) {
            cmAuditoriaCierre = new CmAuditoriaCierre();
        }
        return cmAuditoriaCierre;
    }

    public void setCmAuditoriaCierre(CmAuditoriaCierre cmAuditoriaCierre) {
        this.cmAuditoriaCierre = cmAuditoriaCierre;
    }

    public CmAuditoriaMasivaN getCmAuditoriaMasiva() {
        if (cmAuditoriaMasiva == null) {
            cmAuditoriaMasiva = new CmAuditoriaMasivaN();
        }
        return cmAuditoriaMasiva;
    }

    public void setCmAuditoriaMasiva(CmAuditoriaMasivaN cmAuditoriaMasiva) {
        this.cmAuditoriaMasiva = cmAuditoriaMasiva;
    }

    public CmDevolucionMasivaN getCmDevolucionMasiva() {
        if (cmDevolucionMasiva == null) {
            cmDevolucionMasiva = new CmDevolucionMasivaN();
        }
        return cmDevolucionMasiva;
    }

    public void setCmDevolucionMasiva(CmDevolucionMasivaN cmDevolucionMasiva) {
        this.cmDevolucionMasiva = cmDevolucionMasiva;
    }

    public CmRipsCarga getCmRipsCarga() {
        if (CmRipsCarga == null) {
            CmRipsCarga = new CmRipsCarga();
        }
        return CmRipsCarga;
    }

    public void setCmRipsCarga(CmRipsCarga CmRipsCarga) {
        this.CmRipsCarga = CmRipsCarga;
    }

    public CmGlosaRespuesta getCmGlosaRespuestaConcialiacionIndividual() {
        if (cmGlosaRespuestaConcialiacionIndividual == null) {
            cmGlosaRespuestaConcialiacionIndividual = new CmGlosaRespuesta();
        }
        return cmGlosaRespuestaConcialiacionIndividual;
    }

    public void setCmGlosaRespuestaConcialiacionIndividual(CmGlosaRespuesta cmGlosaRespuestaConcialiacionIndividual) {
        this.cmGlosaRespuestaConcialiacionIndividual = cmGlosaRespuestaConcialiacionIndividual;
    }
    
    
    public int getTipoReintento() {
        return tipoReintento;
    }

    public void setTipoReintento(int tipoReintento) {
        this.tipoReintento = tipoReintento;
    }

    public Integer getIdAuditoriaDevolucion() {
        return idAuditoriaDevolucion;
    }

    public void setIdAuditoriaDevolucion(Integer idAuditoriaDevolucion) {
        this.idAuditoriaDevolucion = idAuditoriaDevolucion;
    }

    public Integer getIdAuditoriaCierre() {
        return idAuditoriaCierre;
    }

    public void setIdAuditoriaCierre(Integer idAuditoriaCierre) {
        this.idAuditoriaCierre = idAuditoriaCierre;
    }

    public String getTipoReintentoStr() {
        return CmReintento.getTipoReintentoStr(getTipoReintento());
    }

    public static String getTipoReintentoStr(int tipoReintento) {
        switch (tipoReintento) {
            case TIPO_REINTENTO_RESPUESTA_GLOSA:
                return "Respuesta Glosa";
            case TIPO_REINTENTO_RESPUESTA_CONCILIACION:
                return "Conciliación";
            case TIPO_REINTENTO_DEVOLUCION_FACTURA:
                return "Devolución Factura";
            case TIPO_REINTENTO_CIERRE_AUDITORIA:
                return "Cierre Auditoría";
            case TIPO_REINTENTO_NOTIFICACION_FACTURA:
                return "Notificación factura";
            case TIPO_REINTENTO_CIERRE_AUDITORIA_MASIVA:
                return "Cierre Auditoria Masiva";
            case TIPO_REINTENTO_DEVOLUCION_FACTURA_MASIVA:
                return "Devolución Factura Masiva";
            default:
                return "";
        }
    }
    
    public String getTipoRelacionStr() {
        return CmRadicado.getTipoRelacionStr(getTipoRelacion());
    }
    
    public String getTipoTransaccionStr() {
        return CmRadicado.getTipoTransaccionStr(getTipoTransaccion());
    }
    
    public String getEstadoStr() {
        return CmRadicado.getEstadoStr(Short.parseShort(getEstado()));
    }

    public Integer getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(Integer idFactura) {
        this.idFactura = idFactura;
    }

    public CmFactura getCmFactura() {
        return cmFactura;
    }

    public void setCmFactura(CmFactura cmFactura) {
        this.cmFactura = cmFactura;
    }
    
    public Short getTipoRelacion() {
        if (Objects.isNull(tipoRelacion)) {
            tipoRelacion = 0;
        }
        return tipoRelacion;
    }

    public void setTipoRelacion(Short tipoRelacion) {
        this.tipoRelacion = tipoRelacion;
    }

    public short getTipoTransaccion() {
        if (Objects.isNull(tipoTransaccion)) {
            tipoTransaccion = 0;
        }
        return tipoTransaccion;
    }

    public void setTipoTransaccion(short tipoTransaccion) {
        this.tipoTransaccion = tipoTransaccion;
    } 

    public String getIps() {
        return ips;
    }

    public void setIps(String ips) {
        this.ips = ips;
    }

    public Boolean getEstadoRadicado() {
        return estadoRadicado;
    }

    public void setEstadoRadicado(Boolean estadoRadicado) {
        this.estadoRadicado = estadoRadicado;
    }

    public String getNumeroFacturas() {
        return numeroFacturas;
    }

    public void setNumeroFacturas(String numeroFacturas) {
        this.numeroFacturas = numeroFacturas;
    }
    
    public String getEstadoRadicadoStr() {
        return estadoRadicado ? "Activo" : "Inactivo";
    }
    

    @Override
    public String toString() {
        return "CmReintento{" + "id=" + id + ", radicado=" + radicado + ", estado=" + estado + ", usuario=" + usuario + ", fechaCreacion=" + fechaCreacion + ", proveedor=" + proveedor + ", idConciliacionMasiva=" + idConciliacionMasiva + ", idGlosaRespuesta=" + idGlosaRespuesta + '}';
    }

}
