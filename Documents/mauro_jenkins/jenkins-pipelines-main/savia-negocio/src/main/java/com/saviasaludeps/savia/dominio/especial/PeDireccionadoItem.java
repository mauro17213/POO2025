
package com.saviasaludeps.savia.dominio.especial;

import com.saviasaludeps.savia.dominio.autorizacion.AuAnexo3Item;
import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author idbohorquez
 */
public class PeDireccionadoItem extends Auditoria implements Serializable{
    
    private Integer id;
    private AuAnexo3Item auAnexo3ItemsId;
    private PeDireccionado peDireccionadosId;
    private int estado;
    private Date fechaPrestacion;
    private String observacion;

    public PeDireccionadoItem() {
    }

    public PeDireccionadoItem(Integer id) {
        this.id = id;
    }    
    

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public AuAnexo3Item getAuAnexo3ItemsId() {
        return auAnexo3ItemsId;
    }

    public void setAuAnexo3ItemsId(AuAnexo3Item auAnexo3ItemsId) {
        this.auAnexo3ItemsId = auAnexo3ItemsId;
    }

    public PeDireccionado getPeDireccionadosId() {
        return peDireccionadosId;
    }

    public void setPeDireccionadosId(PeDireccionado peDireccionadosId) {
        this.peDireccionadosId = peDireccionadosId;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public Date getFechaPrestacion() {
        return fechaPrestacion;
    }

    public void setFechaPrestacion(Date fechaPrestacion) {
        this.fechaPrestacion = fechaPrestacion;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }
    
    
    
    
}
