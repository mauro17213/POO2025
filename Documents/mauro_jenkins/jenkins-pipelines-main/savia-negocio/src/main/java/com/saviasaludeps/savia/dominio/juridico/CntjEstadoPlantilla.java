
package com.saviasaludeps.savia.dominio.juridico;

import com.saviasaludeps.savia.dominio.generico.Auditoria;

/**
 *
 * @author idbohorquez
 */
public class CntjEstadoPlantilla extends Auditoria{
    
    private Integer id;
    private CntjEstado estadoId;
    private CntjPlantilla plantillaId;
    private boolean editable;

    public CntjEstadoPlantilla() {
        this.estadoId = new CntjEstado();
        this.plantillaId = new CntjPlantilla();
    }

    public CntjEstadoPlantilla(Integer id) {
        this.id = id;
        this.estadoId = new CntjEstado();
        this.plantillaId = new CntjPlantilla();
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

    public CntjPlantilla getPlantillaId() {
        return plantillaId;
    }

    public void setPlantillaId(CntjPlantilla plantillaId) {
        this.plantillaId = plantillaId;
    }

    public boolean isEditable() {
        return editable;
    }

    public void setEditable(boolean editable) {
        this.editable = editable;
    }

    @Override
    public String toString() {
        return "CntjEstadoPlantilla{" + "id=" + id + ", estadoId=" + estadoId + ", plantillaId=" + plantillaId + ", editable=" + editable + '}';
    }
      
}
