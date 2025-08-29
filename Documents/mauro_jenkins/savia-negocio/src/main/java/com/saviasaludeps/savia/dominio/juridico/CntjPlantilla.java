package com.saviasaludeps.savia.dominio.juridico;

import com.saviasaludeps.savia.dominio.generico.Auditoria;

/**
 *
 * @author idbohorquez
 */
public class CntjPlantilla extends Auditoria{
    
    private Integer id;
    private String nombre;
    private String descripcion;
    private boolean activo;
    private String estructura;
    private String version;
    private CntjProcesoDocumento procesodocumentoId;
    private String estructuraVisor;

    public CntjPlantilla() {
        this.procesodocumentoId = new CntjProcesoDocumento();
    }

    public CntjPlantilla(Integer id) {
        this.id = id;
        this.procesodocumentoId = new CntjProcesoDocumento();
    }

    public CntjPlantilla(Integer id, String nombre) {
        this.id = id;
        this.nombre = nombre;
        this.procesodocumentoId = new CntjProcesoDocumento();
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getEstructura() {
        return estructura;
    }

    public void setEstructura(String estructura) {
        this.estructura = estructura;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }    

    public boolean getActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public String getEstructuraVisor() {
        return estructuraVisor;
    }

    public void setEstructuraVisor(String estructuraVisor) {
        this.estructuraVisor = estructuraVisor;
    }
    
    
    
    public String getActivoStr(){
        if (this.activo) {
            return "Si";
        } else {
            return "No";
        }
    }

    public CntjProcesoDocumento getProcesodocumentoId() {
        return procesodocumentoId;
    }

    public void setProcesodocumentoId(CntjProcesoDocumento procesodocumentoId) {
        this.procesodocumentoId = procesodocumentoId;
    }

    @Override
    public String toString() {
        return "CntjPlantilla{" + "id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + ", activo=" + activo + ", estructura=" + estructura + ", version=" + version + ", procesodocumentoId=" + procesodocumentoId + '}';
    }
    
    
}
