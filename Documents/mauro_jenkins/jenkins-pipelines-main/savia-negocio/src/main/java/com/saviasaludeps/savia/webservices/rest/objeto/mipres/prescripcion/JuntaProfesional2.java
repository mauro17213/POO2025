package com.saviasaludeps.savia.webservices.rest.objeto.mipres.prescripcion;

import com.saviasaludeps.savia.dominio.mipres.MpConsumoFallo;
import java.io.Serializable;

public class JuntaProfesional2 implements Serializable {

    private String NoPrescripcion;
    private String FPrescripcion;
    private String TipoTecnologia;
    private String Consecutivo;
    private String EstJM;
    private String CodEntProc;
    private String Observaciones;
    private String JustificacionTecnica;
    private String Modalidad;
    private String NoActa;
    private String FechaActa;
    private String FProceso;
    private String TipoIDPaciente;
    private String NroIDPaciente;
    private String CodEntJM;

    private MpConsumoFallo fallo;

    public String getNoPrescripcion() {
        return NoPrescripcion;
    }

    public void setNoPrescripcion(String NoPrescripcion) {
        this.NoPrescripcion = NoPrescripcion;
    }

    public String getFPrescripcion() {
        return FPrescripcion;
    }

    public void setFPrescripcion(String FPrescripcion) {
        this.FPrescripcion = FPrescripcion;
    }

    public String getTipoTecnologia() {
        return TipoTecnologia;
    }

    public void setTipoTecnologia(String TipoTecnologia) {
        this.TipoTecnologia = TipoTecnologia;
    }

    public String getConsecutivo() {
        return Consecutivo;
    }

    public void setConsecutivo(String Consecutivo) {
        this.Consecutivo = Consecutivo;
    }

    public String getEstJM() {
        return EstJM;
    }

    public void setEstJM(String EstJM) {
        this.EstJM = EstJM;
    }

    public String getCodEntProc() {
        return CodEntProc;
    }

    public void setCodEntProc(String CodEntProc) {
        this.CodEntProc = CodEntProc;
    }

    public String getObservaciones() {
        return Observaciones;
    }

    public void setObservaciones(String Observaciones) {
        this.Observaciones = Observaciones;
    }

    public String getJustificacionTecnica() {
        return JustificacionTecnica;
    }

    public void setJustificacionTecnica(String JustificacionTecnica) {
        this.JustificacionTecnica = JustificacionTecnica;
    }

    public String getModalidad() {
        return Modalidad;
    }

    public void setModalidad(String Modalidad) {
        this.Modalidad = Modalidad;
    }

    public String getNoActa() {
        return NoActa;
    }

    public void setNoActa(String NoActa) {
        this.NoActa = NoActa;
    }

    public String getFechaActa() {
        return FechaActa;
    }

    public void setFechaActa(String FechaActa) {
        this.FechaActa = FechaActa;
    }

    public String getFProceso() {
        return FProceso;
    }

    public void setFProceso(String FProceso) {
        this.FProceso = FProceso;
    }

    public String getTipoIDPaciente() {
        return TipoIDPaciente;
    }

    public void setTipoIDPaciente(String TipoIDPaciente) {
        this.TipoIDPaciente = TipoIDPaciente;
    }

    public String getNroIDPaciente() {
        return NroIDPaciente;
    }

    public void setNroIDPaciente(String NroIDPaciente) {
        this.NroIDPaciente = NroIDPaciente;
    }

    public String getCodEntJM() {
        return CodEntJM;
    }

    public void setCodEntJM(String CodEntJM) {
        this.CodEntJM = CodEntJM;
    }

    public MpConsumoFallo getFallo() {
        return fallo;
    }

    public void setFallo(MpConsumoFallo fallo) {
        this.fallo = fallo;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("JuntaProfesional{NoPrescripcion=").append(NoPrescripcion);
        sb.append(", FPrescripcion=").append(FPrescripcion);
        sb.append(", TipoTecnologia=").append(TipoTecnologia);
        sb.append(", Consecutivo=").append(Consecutivo);
        sb.append(", EstJM=").append(EstJM);
        sb.append(", CodEntProc=").append(CodEntProc);
        sb.append(", Observaciones=").append(Observaciones);
        sb.append(", JustificacionTecnica=").append(JustificacionTecnica);
        sb.append(", Modalidad=").append(Modalidad);
        sb.append(", NoActa=").append(NoActa);
        sb.append(", FechaActa=").append(FechaActa);
        sb.append(", FProceso=").append(FProceso);
        sb.append(", TipoIDPaciente=").append(TipoIDPaciente);
        sb.append(", NroIDPaciente=").append(NroIDPaciente);
        sb.append(", CodEntJM=").append(CodEntJM);
        sb.append(", fallo=").append(fallo);
        sb.append('}');
        return sb.toString();
    }

}
