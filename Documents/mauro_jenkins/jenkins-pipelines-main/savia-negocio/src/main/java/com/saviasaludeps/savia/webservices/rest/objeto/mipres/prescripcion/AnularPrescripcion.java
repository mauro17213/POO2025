
package com.saviasaludeps.savia.webservices.rest.objeto.mipres.prescripcion;

import java.util.Date;

public class AnularPrescripcion {
    
    private String NoPrescripcion;
    private Integer TipoAnulacion;
    private String Justificacion;
    private String Observacion;
    private Date FSolicitud;
    private String Usuario_Solicita;
    private Integer EstAnulacion;
    private Date FAnulacion;
    private String Usuario_Anula;

    public String getNoPrescripcion() {
        return NoPrescripcion;
    }

    public void setNoPrescripcion(String NoPrescripcion) {
        this.NoPrescripcion = NoPrescripcion;
    }

    public String getJustificacion() {
        return Justificacion;
    }

    public void setJustificacion(String Justificacion) {
        this.Justificacion = Justificacion;
    }

    public String getObservacion() {
        return Observacion;
    }

    public void setObservacion(String Observacion) {
        this.Observacion = Observacion;
    }

    public Date getFSolicitud() {
        return FSolicitud;
    }

    public void setFSolicitud(Date FSolicitud) {
        this.FSolicitud = FSolicitud;
    }

    public String getUsuario_Solicita() {
        return Usuario_Solicita;
    }

    public void setUsuario_Solicita(String Usuario_Solicita) {
        this.Usuario_Solicita = Usuario_Solicita;
    }

    public Integer getEstAnulacion() {
        return EstAnulacion;
    }

    public void setEstAnulacion(Integer EstAnulacion) {
        this.EstAnulacion = EstAnulacion;
    }

    public Date getFAnulacion() {
        return FAnulacion;
    }

    public void setFAnulacion(Date FAnulacion) {
        this.FAnulacion = FAnulacion;
    }

    public String getUsuario_Anula() {
        return Usuario_Anula;
    }

    public void setUsuario_Anula(String Usuario_Anula) {
        this.Usuario_Anula = Usuario_Anula;
    }

    public Integer getTipoAnulacion() {
        return TipoAnulacion;
    }

    public void setTipoAnulacion(Integer TipoAnulacion) {
        this.TipoAnulacion = TipoAnulacion;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("AnularPrescripcion{NoPrescripcion=").append(NoPrescripcion);
        sb.append(", TipoAnulacion=").append(TipoAnulacion);
        sb.append(", Justificacion=").append(Justificacion);
        sb.append(", Observacion=").append(Observacion);
        sb.append(", FSolicitud=").append(FSolicitud);
        sb.append(", Usuario_Solicita=").append(Usuario_Solicita);
        sb.append(", EstAnulacion=").append(EstAnulacion);
        sb.append(", FAnulacion=").append(FAnulacion);
        sb.append(", Usuario_Anula=").append(Usuario_Anula);
        sb.append('}');
        return sb.toString();
    }
    
}
