package com.saviasaludeps.savia.web.servicios.webservices.mipres;

import com.saviasaludeps.savia.dominio.mipres.MpConsumoFallo;
import java.io.Serializable;

public class DireccionamientoBaseDTO implements Serializable {

    private Integer ID;
    private Integer IDDireccionamiento;
    private String NoPrescripcion;
    private String TipoTec;
    private Integer ConTec;
    private String TipoIDPaciente;
    private String NoIDPaciente;
    private Integer NoEntrega;
    private Integer NoSubEntrega;
    private String TipoIDProv;
    private String NoIDProv;
    private String CodMunEnt;
    private String FecMaxEnt;
    private String CantTotAEntregar;
    private String DirPaciente;
    private String CodSerTecAEntregar;
    private String NoIDEPS;
    private String CodEPS;
    private String FecDireccionamiento;
    private String EstDireccionamiento;
    private String FecAnulacion;
    private MpConsumoFallo fallo;

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public Integer getIDDireccionamiento() {
        return IDDireccionamiento;
    }

    public void setIDDireccionamiento(Integer IDDireccionamiento) {
        this.IDDireccionamiento = IDDireccionamiento;
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

    public Integer getNoEntrega() {
        return NoEntrega;
    }

    public void setNoEntrega(Integer NoEntrega) {
        this.NoEntrega = NoEntrega;
    }

    public Integer getNoSubEntrega() {
        return NoSubEntrega;
    }

    public void setNoSubEntrega(Integer NoSubEntrega) {
        this.NoSubEntrega = NoSubEntrega;
    }

    public String getTipoIDProv() {
        return TipoIDProv;
    }

    public void setTipoIDProv(String TipoIDProv) {
        this.TipoIDProv = TipoIDProv;
    }

    public String getNoIDProv() {
        return NoIDProv;
    }

    public void setNoIDProv(String NoIDProv) {
        this.NoIDProv = NoIDProv;
    }

    public String getCodMunEnt() {
        return CodMunEnt;
    }

    public void setCodMunEnt(String CodMunEnt) {
        this.CodMunEnt = CodMunEnt;
    }

    public String getFecMaxEnt() {
        return FecMaxEnt;
    }

    public void setFecMaxEnt(String FecMaxEnt) {
        this.FecMaxEnt = FecMaxEnt;
    }

    public String getCantTotAEntregar() {
        return CantTotAEntregar;
    }

    public void setCantTotAEntregar(String CantTotAEntregar) {
        this.CantTotAEntregar = CantTotAEntregar;
    }

    public String getDirPaciente() {
        return DirPaciente;
    }

    public void setDirPaciente(String DirPaciente) {
        this.DirPaciente = DirPaciente;
    }

    public String getCodSerTecAEntregar() {
        return CodSerTecAEntregar;
    }

    public void setCodSerTecAEntregar(String CodSerTecAEntregar) {
        this.CodSerTecAEntregar = CodSerTecAEntregar;
    }

    public String getNoIDEPS() {
        return NoIDEPS;
    }

    public void setNoIDEPS(String NoIDEPS) {
        this.NoIDEPS = NoIDEPS;
    }

    public String getCodEPS() {
        return CodEPS;
    }

    public void setCodEPS(String CodEPS) {
        this.CodEPS = CodEPS;
    }

    public String getFecDireccionamiento() {
        return FecDireccionamiento;
    }

    public void setFecDireccionamiento(String FecDireccionamiento) {
        this.FecDireccionamiento = FecDireccionamiento;
    }

    public String getEstDireccionamiento() {
        return EstDireccionamiento;
    }

    public void setEstDireccionamiento(String EstDireccionamiento) {
        this.EstDireccionamiento = EstDireccionamiento;
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
