
package com.saviasaludeps.savia.dominio.juridico;

import com.saviasaludeps.savia.dominio.generico.Auditoria;

/**
 *
 * @author idbohorquez
 */
public class CntjEstadoGrupo extends Auditoria{
    
    private Integer id;
    private CntjEstado estadoId;
    private CntjGrupo grupoId;
    private boolean ejecucion;

    public CntjEstadoGrupo() {
        this.estadoId = new CntjEstado();
        this.grupoId = new CntjGrupo();
    }

    public CntjEstadoGrupo(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public CntjEstado getEstadoId() {
        return estadoId;
    }

    public void setEstadoId(CntjEstado estadoId) {
        this.estadoId = estadoId;
    }

    public CntjGrupo getGrupoId() {
        return grupoId;
    }

    public void setGrupoId(CntjGrupo grupoId) {
        this.grupoId = grupoId;
    }

    public boolean getEjecucion() {
        return ejecucion;
    }

    public void setEjecucion(boolean ejecucion) {
        this.ejecucion = ejecucion;
    }

    public String getEjecucionStr(){
        if (this.ejecucion) {
            return "Si";
        } else {
            return "No";
        }
    }
    
    @Override
    public String toString() {
        return "CntjEstadoGrupo{" + "id=" + id + ", estadoId=" + estadoId + ", grupoId=" + grupoId + ", ejecucion=" + ejecucion + '}';
    }

    
    
}
