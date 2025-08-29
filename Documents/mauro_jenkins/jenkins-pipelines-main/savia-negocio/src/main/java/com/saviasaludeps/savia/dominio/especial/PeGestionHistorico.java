
package com.saviasaludeps.savia.dominio.especial;

import com.saviasaludeps.savia.dominio.generico.Auditoria;

/**
 *
 * @author idbohorquez
 */
public class PeGestionHistorico extends Auditoria {
    
    private Integer id;
    private String descripcion;
    private PeGestion peGestionId;

    public PeGestionHistorico() {
    }

    public PeGestionHistorico(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public PeGestion getPeGestionId() {
        return peGestionId;
    }

    public void setPeGestionId(PeGestion peGestionId) {
        this.peGestionId = peGestionId;
    }
    
    
}
