package com.saviasaludeps.savia.dominio.cuentamedica.rips;

import com.saviasaludeps.savia.dominio.generico.Auditoria;

public class CmRipsEstructuraError extends Auditoria {

    private Integer id;
    private String archivoNombre;
    private String archivoFila;
    private String descripcionError;
    private CmRipsCarga cmRipsCarga;

    public CmRipsEstructuraError(String archivoNombre, String archivoFila, String descripcionError) {
        this.archivoNombre = archivoNombre;
        this.archivoFila = archivoFila;
        this.descripcionError = descripcionError;
    }

    public CmRipsEstructuraError() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getArchivoNombre() {
        return archivoNombre;
    }

    public void setArchivoNombre(String archivoNombre) {
        this.archivoNombre = archivoNombre;
    }

    public String getArchivoFila() {
        return archivoFila;
    }

    public void setArchivoFila(String archivoFila) {
        this.archivoFila = archivoFila;
    }

    public String getDescripcionError() {
        return descripcionError;
    }

    public void setDescripcionError(String descripcionError) {
        this.descripcionError = descripcionError;
    }

    public CmRipsCarga getCmRipsCarga() {
        return cmRipsCarga;
    }

    public void setCmRipsCarga(CmRipsCarga cmRipsCarga) {
        this.cmRipsCarga = cmRipsCarga;
    }

    @Override
    public String toString() {
        return "CmRipsEstructuraError{" + "id=" + id + ", archivoNombre=" + archivoNombre + ", archivoFila=" + archivoFila + ", cmRipsCargas=" + cmRipsCarga + '}';
    }
    
}
