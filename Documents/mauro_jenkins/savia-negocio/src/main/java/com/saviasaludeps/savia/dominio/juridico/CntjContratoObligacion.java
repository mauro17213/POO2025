package com.saviasaludeps.savia.dominio.juridico;

import com.saviasaludeps.savia.dominio.generico.Auditoria;

/**
 *
 * @author idbohorquez
 */
public class CntjContratoObligacion extends Auditoria{
    
    private Integer id;
    private Integer numeroObligacion;
    private String descripcion;    
    private CntjContrato cntjContratosId;
    
    //Auxiliares
    private int idTemporal;

    public CntjContratoObligacion() {
    }

    public CntjContratoObligacion(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNumeroObligacion() {
        return numeroObligacion;
    }

    public void setNumeroObligacion(Integer numeroObligacion) {
        this.numeroObligacion = numeroObligacion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public CntjContrato getCntjContratosId() {
        return cntjContratosId;
    }

    public void setCntjContratosId(CntjContrato cntjContratosId) {
        this.cntjContratosId = cntjContratosId;
    }
    
    //Auxiliares

    public int getIdTemporal() {
        return idTemporal;
    }

    public void setIdTemporal(int idTemporal) {
        this.idTemporal = idTemporal;
    }
    

    @Override
    public String toString() {
        return "CntjContratoObligacion{" + "id=" + id + ", numeroObligacion=" + numeroObligacion + ", descripcion=" + descripcion + ", cntjContratosId=" + cntjContratosId + '}';
    }
        
}
