package com.saviasaludeps.savia.dominio.juridico;

import com.saviasaludeps.savia.dominio.generico.Auditoria;

/**
 *
 * @author idbohorquez
 */
public class CntjProcesoDocumento extends Auditoria {
        
    private Integer id;
    private CntjProceso procesoId;
    private CntjPlantilla plantillaId;
    private String nombre;
    private String descripcion;
    private boolean activo;
    private Integer etapaContratacion;
    private Integer tipoDocumento;

    public CntjProcesoDocumento() {
    }

    public CntjProcesoDocumento(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public CntjProceso getProcesoId() {
        return procesoId;
    }

    public void setProcesoId(CntjProceso procesoId) {
        this.procesoId = procesoId;
    }

    public CntjPlantilla getPlantillaId() {
        return plantillaId;
    }

    public void setPlantillaId(CntjPlantilla plantillaId) {
        this.plantillaId = plantillaId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean isActivo() {
        return activo;
    }
    
    public String getActivoStr(){
        if(this.activo){
            return "Si";
        }
        return "No";
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public Integer getEtapaContratacion() {
        return etapaContratacion;
    }

    public void setEtapaContratacion(Integer etapaContratacion) {
        this.etapaContratacion = etapaContratacion;
    }
   
    public Integer getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(Integer tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    @Override
    public String toString() {
        return "CntjProcesoDocumento{" + "id=" + id + ", procesoId=" + procesoId + ", plantillaId=" + plantillaId + ", nombre=" + nombre + ", descripcion=" + descripcion + ", activo=" + activo + ", etapaContratacion=" + etapaContratacion + ", tipoDocumento=" + tipoDocumento + '}';
    }

}
