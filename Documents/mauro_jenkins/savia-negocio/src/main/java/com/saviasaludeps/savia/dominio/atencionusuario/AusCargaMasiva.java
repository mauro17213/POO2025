/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.atencionusuario;

import com.saviasaludeps.savia.dominio.administracion.Empresa;
import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AusCargaMasiva extends Auditoria implements Serializable {
    // ENCABEZADO DE ARCHIVO RESULTADO
    public static final String ENCABEZADO_CREAR_CASO = "consecutivo|pqr_codigo|pqr_canal|fecha_creacion|pet_tipodoc|pet_numdoc|pet_nombres|pet_apellidos|pet_genero|pet_ubicacion|pet_telefono|pet_mail|afec_tipodo|afec_numdoc|afec_nombres|afec_apellidos|afec_sexo|afec_mpio|afec_telefono|afec_mail|afec_parentesco|pqr_tipopeticion|ente_control|riesgo_vida|responsable|ambito|patologia1|observacion|mae_tecnologia_alto_costo_codigo|mae_motivo_especifico_codigo|mae_tipo_motivo_especifico_codigo|mae_subtipo_motivo_especifico_codigo|fecha_creacion_caso|proteccion_datos|observacion";
    public static final String ENCABEZADO_CERRAR_CASO = "consecutivo|numero_caso|motivo_cerrado|descripcion_caso|descripcion_servicio|observacion";
    public static final String ENCABEZADO_REABRIR_CASO = "consecutivo|numero_caso|motivo|comentario|observacion";
    // Parametros para carga masiva
    public static final int TIPO_CARGA_MASIVA_CASO = 0;
    public static final int TIPO_CARGA_MASIVA_CIERRE_CASO = 1;
    public static final int TIPO_CARGA_MASIVA_REVERTIR_CASO = 2;
    public static final int ESTADO_CARGA_EN_COLA = 0;
    
    
    public static final int ESTADO_EN_PROCESO = 1;
    public static final int ESTADO_TERMINADA = 2;
    public static final int ESTADO_SUSPENDIDA_POR_ERROR = 3;

    private static final long serialVersionUID = 1L;
    private Integer id;
    private String nombre;
    private String ruta;
    private String archivo;
    private Integer tipo;
    private Integer cantidadRegistros;
    private Integer exitosos;
    private Integer fallidos;
    private int estado;
    private Date fechaHoraInicio;
    private Date fechaHoraFin;
    private String observacion;
    private List<AusCaso> listAusCaso;
    private AusCaso caso;
    private AusCasoServicio servicio;
    private AusSeguimiento seguimiento;
    private Empresa empresa;
    private Boolean existe;
    private String respNombre;
    private String respRuta;
    private String respArchivo;
    private Boolean respExiste;
    
    private transient InputStream adjuntoStream;

    // variables para carga masiva caso hilo
    private String ausCargaMasivaCaso;
    private String errorCarga;

    public AusCargaMasiva() {
    }

    public AusCargaMasiva(Integer id) {
        this.id = id;
    }

    public AusCargaMasiva(Integer id, int estado, Date fechaHoraInicio, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.estado = estado;
        this.fechaHoraInicio = fechaHoraInicio;
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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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

    public Integer getTipo() {
        return tipo;
    }

    public void setTipo(Integer tipo) {
        this.tipo = tipo;
    }

    public Integer getCantidadRegistros() {
        return cantidadRegistros;
    }

    public void setCantidadRegistros(Integer cantidadRegistros) {
        this.cantidadRegistros = cantidadRegistros;
    }

    public Integer getExitosos() {
        return exitosos;
    }

    public void setExitosos(Integer exitosos) {
        this.exitosos = exitosos;
    }

    public Integer getFallidos() {
        return fallidos;
    }

    public void setFallidos(Integer fallidos) {
        this.fallidos = fallidos;
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

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public List<AusCaso> getListAusCaso() {
        if (listAusCaso == null) {
            listAusCaso = new ArrayList<>();
        }
        return listAusCaso;
    }

    public void setListAusCaso(List<AusCaso> listAusCaso) {
        this.listAusCaso = listAusCaso;
    }

    public AusCaso getCaso() {
        return caso;
    }

    public void setCaso(AusCaso caso) {
        this.caso = caso;
    }

    public InputStream getAdjuntoStream() {
        return adjuntoStream;
    }

    public void setAdjuntoStream(InputStream adjuntoStream) {
        this.adjuntoStream = adjuntoStream;
    }

    public AusCasoServicio getServicio() {
        return servicio;
    }

    public void setServicio(AusCasoServicio servicio) {
        this.servicio = servicio;
    }

    public AusSeguimiento getSeguimiento() {
        return seguimiento;
    }

    public void setSeguimiento(AusSeguimiento seguimiento) {
        this.seguimiento = seguimiento;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public Boolean getExiste() {
        return existe;
    }

    public void setExiste(Boolean existe) {
        this.existe = existe;
    }

    public String getRespNombre() {
        return respNombre;
    }

    public void setRespNombre(String respNombre) {
        this.respNombre = respNombre;
    }

    public String getRespRuta() {
        return respRuta;
    }

    public void setRespRuta(String respRuta) {
        this.respRuta = respRuta;
    }

    public String getRespArchivo() {
        return respArchivo;
    }

    public void setRespArchivo(String respArchivo) {
        this.respArchivo = respArchivo;
    }

    public Boolean getRespExiste() {
        return respExiste;
    }

    public void setRespExiste(Boolean respExiste) {
        this.respExiste = respExiste;
    }
    
    public String getAusCargaMasivaCaso() {
        return ausCargaMasivaCaso;
    }

    public void setAusCargaMasivaCaso(String ausCargaMasivaCaso) {
        this.ausCargaMasivaCaso = ausCargaMasivaCaso;
    }

    public String getErrorCarga() {
        return errorCarga;
    }

    public void setErrorCarga(String errorCarga) {
        this.errorCarga = errorCarga;
    }

    public String getEstdoStr() {
        return AusCargaMasiva.getEstadoStr(getEstado());
    }

    public Integer getRegistrosPendientes() {
        int cantidad = (exitosos != null && fallidos != null) ? exitosos + fallidos: 0;
        return cantidad;
    }

    public static String getTipoStr(Integer estadoProceso) {
        String _str = "";
        if (estadoProceso != null) {
            switch (estadoProceso) {
                case TIPO_CARGA_MASIVA_CASO:
                    _str = "Carga masiva caso";
                    break;
                case TIPO_CARGA_MASIVA_CIERRE_CASO:
                    _str = "Carga masiva cierre caso";
                    break;
                case TIPO_CARGA_MASIVA_REVERTIR_CASO:
                    _str = "Carga masiva revertir caso";
                    break;
                default:
                    _str = "";
                    break;
            }
        }
        return _str;
    }

    public static String getEstadoStr(Integer estadoProceso) {
        String _str = "";
        if (estadoProceso != null) {
            switch (estadoProceso) {
                case ESTADO_CARGA_EN_COLA:
                    _str = "En cola";
                    break;
                case ESTADO_EN_PROCESO:
                    _str = "En proceso";
                    break;
                case ESTADO_TERMINADA:
                    _str = "Terminada";
                    break;
                case ESTADO_SUSPENDIDA_POR_ERROR:
                    _str = "Suspendida por error";
                    break;
                default:
                    _str = "";
                    break;
            }
        }
        return _str;
    }

    @Override
    public String toString() {
        return "AusCargaMasiva{" + "id = " + id + ", cantidadRegistros=" + cantidadRegistros + ", estado=" + estado + ", fechaHoraInicio=" + fechaHoraInicio + ", fechaHoraFin=" + fechaHoraFin + ", observacion=" + observacion + ", usuarioCrea=" + usuarioCrea + ", terminalCrea=" + terminalCrea + ", fechaHoraCrea=" + fechaHoraCrea + '}';
    }

}
