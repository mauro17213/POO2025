/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.gestionAtencion;

import com.saviasaludeps.savia.dominio.administracion.GnSede;
import com.saviasaludeps.savia.dominio.administracion.Usuario;
import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.util.Date;
import java.util.List;

/**
 *
 * @author acuartas
 */
public class GatAtencion extends Auditoria {

    public static final int ESTADO_LLAMADO = 0;
    public static final int ESTADO_EN_GESTION = 1;
    public static final int ESTADO_FINALIZADO = 2;
    public static final int ESTADO_ABANDONO = 3;

    private Integer id;

    private GatSedeFuncionario gatSedeFuncionario;
    private GatSedeTaquilla gatTaquilla;
    private GatTiquete gatTiquete;
    private GatUsuario gatUsuario;
    private GnSede gnSede;
    private Usuario usuario;

    private Date fechaHoraTiquete;
    private Date fechaHoraInicio;
    private Date fechaHoraCancela;
    private Date fechaHoraFin;
    private Date fechaHoraCalificacion;
    private Integer estado;
    private List<GatAtencionComentario> gatListaComentarios;
    private List<GatAtencionHistorico> gatListaHistoricos;
    private int tiempoServicio;

    //Auxiliares
    private boolean enLlamado;
    private int llamados;
    private boolean finalizo;
    private boolean fueraTiempo;

    public GatAtencion() {
        this.estado = ESTADO_LLAMADO;
    }

    public GatAtencion(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public GatSedeFuncionario getGatSedeFuncionario() {
        return gatSedeFuncionario;
    }

    public void setGatSedeFuncionario(GatSedeFuncionario gatSedeFuncionario) {
        this.gatSedeFuncionario = gatSedeFuncionario;
    }

    public GatSedeTaquilla getGatTaquilla() {
        return gatTaquilla;
    }

    public void setGatTaquilla(GatSedeTaquilla gatTaquilla) {
        this.gatTaquilla = gatTaquilla;
    }

    public GatTiquete getGatTiquete() {
        return gatTiquete;
    }

    public void setGatTiquete(GatTiquete gatTiquete) {
        this.gatTiquete = gatTiquete;
    }

    public GatUsuario getGatUsuario() {
        return gatUsuario;
    }

    public void setGatUsuario(GatUsuario gatUsuario) {
        this.gatUsuario = gatUsuario;
    }

    public GnSede getGnSede() {
        return gnSede;
    }

    public void setGnSede(GnSede gnSede) {
        this.gnSede = gnSede;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Date getFechaHoraTiquete() {
        return fechaHoraTiquete;
    }

    public void setFechaHoraTiquete(Date fechaHoraTiquete) {
        this.fechaHoraTiquete = fechaHoraTiquete;
    }

    public Date getFechaHoraInicio() {
        return fechaHoraInicio;
    }

    public void setFechaHoraInicio(Date fechaHoraInicio) {
        this.fechaHoraInicio = fechaHoraInicio;
    }

    public Date getFechaHoraCancela() {
        return fechaHoraCancela;
    }

    public void setFechaHoraCancela(Date fechaHoraCancela) {
        this.fechaHoraCancela = fechaHoraCancela;
    }

    public Date getFechaHoraFin() {
        return fechaHoraFin;
    }

    public void setFechaHoraFin(Date fechaHoraFin) {
        this.fechaHoraFin = fechaHoraFin;
    }

    public Date getFechaHoraCalificacion() {
        return fechaHoraCalificacion;
    }

    public void setFechaHoraCalificacion(Date fechaHoraCalificacion) {
        this.fechaHoraCalificacion = fechaHoraCalificacion;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public List<GatAtencionComentario> getGatListaComentarios() {
        return gatListaComentarios;
    }

    public void setGatListaComentarios(List<GatAtencionComentario> gatListaComentarios) {
        this.gatListaComentarios = gatListaComentarios;
    }

    public List<GatAtencionHistorico> getGatListaHistoricos() {
        return gatListaHistoricos;
    }

    public void setGatListaHistoricos(List<GatAtencionHistorico> gatListaHistoricos) {
        this.gatListaHistoricos = gatListaHistoricos;
    }

    //Metodo auxiliares
//    public String getTiempoStr() {
//        Date fechaActual = new Date();
//        long diff = fechaActual.getTime() - getFechaHoraInicio().getTime();
//        Long minutos = TimeUnit.MILLISECONDS.toMinutes(diff);
//        Long segundos = TimeUnit.MILLISECONDS.toSeconds(diff);
//        String tiempo = "";
//        if (minutos.intValue() < 10) {
//            tiempo += "0"+minutos.intValue();
//        } else {
//            tiempo += minutos.intValue();
//        }
//        if (segundos.intValue() < 10) {
//            tiempo += ":0"+segundos.intValue();
//        } else {
//            tiempo += ":"+segundos;
//        }
//        return tiempo;
//    }    
    public String getTiempoStr() {
        Date fechaInicio;
        Date fechaFin;
        if (getEstado() == null) {
            return "0 min 0 seg";
        } else {
            switch (getEstado()) {
                case ESTADO_LLAMADO:
                    fechaInicio = getFechaHoraTiquete();
                    fechaFin = new Date();
                    break;
                case ESTADO_EN_GESTION:
                    fechaInicio = getFechaHoraInicio();
                    fechaFin = new Date();
                    break;
                case ESTADO_FINALIZADO:
                    fechaInicio = getFechaHoraInicio();
                    fechaFin = getFechaHoraFin();
                    break;
                case ESTADO_ABANDONO:
                    fechaInicio = getFechaHoraTiquete();
                    fechaFin = getFechaHoraCancela();
                    break;
                default:
                    fechaInicio = new Date();
                    fechaFin = new Date();
                    break;
            }
        }
        if (fechaInicio != null) {
//        Date fechaActual = new Date();
            long diferencia = fechaFin.getTime() - fechaInicio.getTime();
            long segundosEnMilisegundos = 1000;
            long minutosEnMilisegundos = segundosEnMilisegundos * 60;
            long tiempoEnMinutos = diferencia / minutosEnMilisegundos;
            diferencia %= minutosEnMilisegundos;
            long tiempoEnSegundos = diferencia / segundosEnMilisegundos;
            return tiempoEnMinutos + " min " + tiempoEnSegundos + " seg";
        } else {
            return "0 min 0 seg";
        }

    }

    public boolean isEnLlamado() {
        return enLlamado;
    }

    public void setEnLlamado(boolean enLlamado) {
        this.enLlamado = enLlamado;
    }

    public String getEstadoStr() {
        if (getEstado() != null) {
            switch (getEstado()) {
                case ESTADO_LLAMADO:
                    return "Llamado";
                case ESTADO_EN_GESTION:
                    return "En Gestión";
                case ESTADO_FINALIZADO:
                    return "Finalizo";
                case ESTADO_ABANDONO:
                    return "Abandono";
                default:
                    return "";
            }
        }
        return "";
    }

    public int getLlamados() {
        return llamados;
    }

    public void setLlamados(int llamados) {
        this.llamados = llamados;
    }

    public boolean getFinalizo() {
        return finalizo;
    }

    public void setFinalizo(boolean finalizo) {
        this.finalizo = finalizo;
    }

    public boolean isFueraTiempo() {
        return fueraTiempo;
    }

    public void setFueraTiempo(boolean fueraTiempo) {
        this.fueraTiempo = fueraTiempo;
    }

    public int getTiempoServicio() {
        return tiempoServicio;
    }

    public void setTiempoServicio(int tiempoServicio) {
        this.tiempoServicio = tiempoServicio;
    }

    @Override
    public String toString() {
        return "GatAtencion{" + "id=" + id + ", gatSedeFuncionario=" + gatSedeFuncionario + ", gatTaquilla=" + gatTaquilla + ", gatTiquete=" + gatTiquete + ", gatUsuario=" + gatUsuario + ", gnSede=" + gnSede + ", usuario=" + usuario + ", fechaHoraTiquete=" + fechaHoraTiquete + ", fechaHoraInicio=" + fechaHoraInicio + ", fechaHoraCancela=" + fechaHoraCancela + ", fechaHoraFin=" + fechaHoraFin + ", fechaHoraCalificacion=" + fechaHoraCalificacion + ", estado=" + estado + ", gatListaComentarios=" + gatListaComentarios + ", gatListaHistoricos=" + gatListaHistoricos + '}';
    }

}
