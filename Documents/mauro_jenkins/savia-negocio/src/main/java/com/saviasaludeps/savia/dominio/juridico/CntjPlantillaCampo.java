package com.saviasaludeps.savia.dominio.juridico;

import com.saviasaludeps.savia.dominio.generico.Auditoria;

/**
 *
 * @author idbohorquez
 */
public class CntjPlantillaCampo  extends Auditoria{
    
    private Integer id;
    private CntjCampo cntjCampoId;
    private CntjPlantilla cntjPlantillaId;

    public CntjPlantillaCampo() {
    }

    public CntjPlantillaCampo(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public CntjCampo getCntjCampoId() {
        return cntjCampoId;
    }

    public void setCntjCampoId(CntjCampo cntjCampoId) {
        this.cntjCampoId = cntjCampoId;
    }

    public CntjPlantilla getCntjPlantillaId() {
        return cntjPlantillaId;
    }

    public void setCntjPlantillaId(CntjPlantilla cntjPlantillaId) {
        this.cntjPlantillaId = cntjPlantillaId;
    }

    @Override
    public String toString() {
        return "CntjPlantillaCampo{" + "id=" + id + ", cntjCampoId=" + cntjCampoId + ", cntjPlantillasId=" + cntjPlantillaId + '}';
    }
    
}
