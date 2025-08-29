
package com.saviasaludeps.savia.dominio.cuentamedica.rips;

import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.util.Date;


public class CmFeRipsValidacionHistorico extends Auditoria  {

    private Integer id;
    private boolean estado;
    private CmFeRipsValidacion cmFeRipsValidacion;

    public CmFeRipsValidacionHistorico() {
    }

    public CmFeRipsValidacionHistorico(Integer id) {
        this.id = id;
    }

    public CmFeRipsValidacionHistorico(Integer id, boolean estado, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.estado = estado;
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

    public boolean getEstado() {
        return estado;
    }
    
    public String getEstadoStr() {
        return estado ? "Activo" : "Inactivo";
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public CmFeRipsValidacion getCmFeRipsValidacion() {
        return cmFeRipsValidacion;
    }

    public void setCmFeRipsValidacion(CmFeRipsValidacion cmFeRipsValidacion) {
        this.cmFeRipsValidacion = cmFeRipsValidacion;
    }

    @Override
    public String toString() {
        return "CmFeRipsValidacionHistorico{" + "id=" + id + ", estado=" + estado + '}';
    }
    
}
