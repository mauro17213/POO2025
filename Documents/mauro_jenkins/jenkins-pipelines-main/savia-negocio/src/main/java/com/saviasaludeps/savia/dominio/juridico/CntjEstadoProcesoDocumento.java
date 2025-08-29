package com.saviasaludeps.savia.dominio.juridico;

import com.saviasaludeps.savia.dominio.generico.Auditoria;

/**
 *
 * @author idbohorquez
 */
public class CntjEstadoProcesoDocumento extends Auditoria {
        
    private Integer id;
    private CntjEstado estadoId;
    private CntjProcesoDocumento procesodocumentoId;

    public CntjEstadoProcesoDocumento() {
        this.estadoId = new CntjEstado();
        this.procesodocumentoId = new CntjProcesoDocumento();
    }

    public CntjEstadoProcesoDocumento(Integer id) {
        this.id = id;
        this.estadoId = new CntjEstado();
        this.procesodocumentoId = new CntjProcesoDocumento();
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

    public CntjProcesoDocumento getProcesodocumentoId() {
        return procesodocumentoId;
    }

    public void setProcesodocumentoId(CntjProcesoDocumento procesodocumentoId) {
        this.procesodocumentoId = procesodocumentoId;
    }

    @Override
    public String toString() {
        return "CntjEstadoProcesoDocumento{" + "id=" + id + ", estadoId=" + estadoId + ", procesodocumentoId=" + procesodocumentoId + '}';
    }
        
}
