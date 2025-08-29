
package com.saviasaludeps.savia.dominio.mipres;

import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.util.Date;

public class MpConsumoFallo extends Auditoria {

    //Estados Consumo Fallos
    public final static int ESTADO_CONSUMOFALLO_EXITOSO = 0;
    public final static int ESTADO_CONSUMOFALLO_FALLIDO = 1;
    public final static int ESTADO_CONSUMOFALLO_CORREGIDO = 2;
    public final static int ESTADO_CONSUMOFALLO_RESINCRONIZADO = 3;
    
    private Integer id;
    private int idProceso;
    private Date fechaProceso;
    private String json;
    private int estado;
    private Date fechaHoraFallo;
    private Date fechaHoraCorreccion;
    private Date fechaHoraResincronizacin;
    private String descripcion;
    private MpConsumo mpConsumoId;

    public MpConsumoFallo() {
    }

    public MpConsumoFallo(Integer id) {
        this.id = id;
    }

    public MpConsumoFallo(Integer id, int idProceso, Date fechaProceso, short estado, Date fechaHoraFallo) {
        this.id = id;
        this.idProceso = idProceso;
        this.fechaProceso = fechaProceso;
        this.estado = estado;
        this.fechaHoraFallo = fechaHoraFallo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getIdProceso() {
        return idProceso;
    }

    public void setIdProceso(int idProceso) {
        this.idProceso = idProceso;
    }

    public Date getFechaProceso() {
        return fechaProceso;
    }

    public void setFechaProceso(Date fechaProceso) {
        this.fechaProceso = fechaProceso;
    }

    public Date getFechaHoraFallo() {
        return fechaHoraFallo;
    }

    public void setFechaHoraFallo(Date fechaHoraFallo) {
        this.fechaHoraFallo = fechaHoraFallo;
    }

    public Date getFechaHoraCorreccion() {
        return fechaHoraCorreccion;
    }

    public void setFechaHoraCorreccion(Date fechaHoraCorreccion) {
        this.fechaHoraCorreccion = fechaHoraCorreccion;
    }

    public Date getFechaHoraResincronizacin() {
        return fechaHoraResincronizacin;
    }

    public void setFechaHoraResincronizacin(Date fechaHoraResincronizacin) {
        this.fechaHoraResincronizacin = fechaHoraResincronizacin;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }

    public MpConsumo getMpConsumoId() {
        return mpConsumoId;
    }

    public void setMpConsumoId(MpConsumo mpConsumoId) {
        this.mpConsumoId = mpConsumoId;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

}
