
package com.saviasaludeps.savia.dominio.cuentamedica.rips;

import com.saviasaludeps.savia.dominio.generico.Auditoria;

public class CmRipsCargaEstado extends Auditoria {

    private Integer id;
    private int estado;
    private CmRipsCarga cmRipsCarga;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public CmRipsCarga getCmRipsCarga() {
        return cmRipsCarga;
    }

    public void setCmRipsCarga(CmRipsCarga cmRipsCarga) {
        this.cmRipsCarga = cmRipsCarga;
    }

    @Override
    public String toString() {
        return "CmRipsCargaEstado{" + "id=" + id + ", estado=" + estado + ", cmRipsCargaId=" + cmRipsCarga.getId() + '}';
    }
    
}
