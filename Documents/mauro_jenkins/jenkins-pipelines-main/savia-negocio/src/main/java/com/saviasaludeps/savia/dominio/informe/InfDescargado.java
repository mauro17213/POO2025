package com.saviasaludeps.savia.dominio.informe;

import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.util.Date;

public class InfDescargado extends Auditoria {

    private Integer id;
    private int infUsuarioId;
    private Date fechaDescarga;
    private InfGenerado infInformeGenerado;
    private InfGrupoUsuario infGrupoUsuario;
    private String empresaNombre;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getInfUsuarioId() {
        return infUsuarioId;
    }

    public void setInfUsuarioId(int infUsuarioId) {
        this.infUsuarioId = infUsuarioId;
    }

    public Date getFechaDescarga() {
        return fechaDescarga;
    }

    public void setFechaDescarga(Date fechaDescarga) {
        this.fechaDescarga = fechaDescarga;
    }

    public InfGenerado getInfInformeGenerado() {
        return infInformeGenerado;
    }

    public void setInfInformeGenerado(InfGenerado infInformeGenerado) {
        this.infInformeGenerado = infInformeGenerado;
    }

    public InfGrupoUsuario getInfGrupoUsuario() {
        return infGrupoUsuario;
    }

    public void setInfGrupoUsuario(InfGrupoUsuario infGrupoUsuario) {
        this.infGrupoUsuario = infGrupoUsuario;
    }

    public String getEmpresaNombre() {
        return empresaNombre;
    }

    public void setEmpresaNombre(String empresaNombre) {
        this.empresaNombre = empresaNombre;
    }

    @Override
    public String toString() {
        return "InfDescargado{" + "id=" + id + ", infUsuarioId=" + infUsuarioId + ", fechaDescarga=" + fechaDescarga + ", infInformeGenerado=" + infInformeGenerado + ", infGrupoUsuario=" + infGrupoUsuario + ", empresaNombre=" + empresaNombre + '}';
    }
    
}
