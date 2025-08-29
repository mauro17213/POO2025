package com.saviasaludeps.savia.web.servicios.webservices.mipres;

import com.saviasaludeps.savia.dominio.mipres.MpConsumoFallo;
import java.io.Serializable;

public class NoDireccionamientoBaseDTO implements Serializable {

    private Integer ID;
    private Integer IDNODireccionamiento;
    private String NoPrescripcion;
    private String TipoTec;
    private Integer ConTec;
    private String TipoIDPaciente;
    private String NoIDPaciente;
    private String NoPrescripcionAsociada;
    private String ConTecAsociada;
    private Integer CausaNoEntrega;
    private String FecNODireccionamiento;
    private Integer EstNODireccionamiento;
    private String FecAnulacion;
    private MpConsumoFallo fallo;

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public Integer getIDNODireccionamiento() {
        return IDNODireccionamiento;
    }

    public void setIDNODireccionamiento(Integer IDNODireccionamiento) {
        this.IDNODireccionamiento = IDNODireccionamiento;
    }

    public String getNoPrescripcion() {
        return NoPrescripcion;
    }

    public void setNoPrescripcion(String NoPrescripcion) {
        this.NoPrescripcion = NoPrescripcion;
    }

    public String getTipoTec() {
        return TipoTec;
    }

    public void setTipoTec(String TipoTec) {
        this.TipoTec = TipoTec;
    }

    public Integer getConTec() {
        return ConTec;
    }

    public void setConTec(Integer ConTec) {
        this.ConTec = ConTec;
    }

    public String getTipoIDPaciente() {
        return TipoIDPaciente;
    }

    public void setTipoIDPaciente(String TipoIDPaciente) {
        this.TipoIDPaciente = TipoIDPaciente;
    }

    public String getNoIDPaciente() {
        return NoIDPaciente;
    }

    public void setNoIDPaciente(String NoIDPaciente) {
        this.NoIDPaciente = NoIDPaciente;
    }

    public String getNoPrescripcionAsociada() {
        return NoPrescripcionAsociada;
    }

    public void setNoPrescripcionAsociada(String NoPrescripcionAsociada) {
        this.NoPrescripcionAsociada = NoPrescripcionAsociada;
    }

    public String getConTecAsociada() {
        return ConTecAsociada;
    }

    public void setConTecAsociada(String ConTecAsociada) {
        this.ConTecAsociada = ConTecAsociada;
    }

    public Integer getCausaNoEntrega() {
        return CausaNoEntrega;
    }

    public void setCausaNoEntrega(Integer CausaNoEntrega) {
        this.CausaNoEntrega = CausaNoEntrega;
    }

    public String getFecNODireccionamiento() {
        return FecNODireccionamiento;
    }

    public void setFecNODireccionamiento(String FecNODireccionamiento) {
        this.FecNODireccionamiento = FecNODireccionamiento;
    }

    public Integer getEstNODireccionamiento() {
        return EstNODireccionamiento;
    }

    public void setEstNODireccionamiento(Integer EstNODireccionamiento) {
        this.EstNODireccionamiento = EstNODireccionamiento;
    }

    public String getFecAnulacion() {
        return FecAnulacion;
    }

    public void setFecAnulacion(String FecAnulacion) {
        this.FecAnulacion = FecAnulacion;
    }

    public MpConsumoFallo getFallo() {
        return fallo;
    }

    public void setFallo(MpConsumoFallo fallo) {
        this.fallo = fallo;
    }

}
