/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.tutela;

import com.saviasaludeps.savia.dominio.administracion.Usuario;
import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author pavacca
 */
public class TuSeguimiento extends Auditoria{
    private Integer id;
    private int maeTipoSeguimientoId;
    private String maeTipoSeguimientoCodigo;
    private String maeTipoSeguimientoValor;
    private Usuario gestorGnUsuarioId;
    private Date fechaSeguimiento;
    private Usuario notificadoGnUsuarioId;
    private String observacion;
    private List<TuAdjunto> tuAdjuntosList;
    private TuTutelaEstado tuTutelaEstadosId;
    private int pos;
    private int tipoSeguimientoEnviarAsignacion;
    private int idUsuarioSeguimientoEnviarAsignacion;
    
    public TuSeguimiento(){
    
    }
    
    public TuSeguimiento(Integer id) {
        this.id = id;
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getMaeTipoSeguimientoId() {
        return maeTipoSeguimientoId;
    }

    public void setMaeTipoSeguimientoId(int maeTipoSeguimientoId) {
        this.maeTipoSeguimientoId = maeTipoSeguimientoId;
    }

    public String getMaeTipoSeguimientoCodigo() {
        return maeTipoSeguimientoCodigo;
    }

    public void setMaeTipoSeguimientoCodigo(String maeTipoSeguimientoCodigo) {
        this.maeTipoSeguimientoCodigo = maeTipoSeguimientoCodigo;
    }

    public String getMaeTipoSeguimientoValor() {
        return maeTipoSeguimientoValor;
    }

    public void setMaeTipoSeguimientoValor(String maeTipoSeguimientoValor) {
        this.maeTipoSeguimientoValor = maeTipoSeguimientoValor;
    }

    public Usuario getGestorGnUsuarioId() {
        return gestorGnUsuarioId;
    }

    public void setGestorGnUsuarioId(Usuario gestorGnUsuarioId) {
        this.gestorGnUsuarioId = gestorGnUsuarioId;
    }

    public Date getFechaSeguimiento() {
        return fechaSeguimiento;
    }

    public void setFechaSeguimiento(Date fechaSeguimiento) {
        this.fechaSeguimiento = fechaSeguimiento;
    }

    public Usuario getNotificadoGnUsuarioId() {
        return notificadoGnUsuarioId;
    }

    public void setNotificadoGnUsuarioId(Usuario notificadoGnUsuarioId) {
        this.notificadoGnUsuarioId = notificadoGnUsuarioId;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
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

    public TuTutelaEstado getTuTutelaEstadosId() {
        return tuTutelaEstadosId;
    }

    public void setTuTutelaEstadosId(TuTutelaEstado tuTutelaEstadosId) {
        this.tuTutelaEstadosId = tuTutelaEstadosId;
    }

    public int getPos() {
        return pos;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }

    public int getTipoSeguimientoEnviarAsignacion() {
        return tipoSeguimientoEnviarAsignacion;
    }

    public void setTipoSeguimientoEnviarAsignacion(int tipoSeguimientoEnviarAsignacion) {
        this.tipoSeguimientoEnviarAsignacion = tipoSeguimientoEnviarAsignacion;
    }

    public int getIdUsuarioSeguimientoEnviarAsignacion() {
        return idUsuarioSeguimientoEnviarAsignacion;
    }

    public void setIdUsuarioSeguimientoEnviarAsignacion(int idUsuarioSeguimientoEnviarAsignacion) {
        this.idUsuarioSeguimientoEnviarAsignacion = idUsuarioSeguimientoEnviarAsignacion;
    }
    
}
