/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.crue;

import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.io.Serializable;

/**
 *
 * @author Jaime Andres Olarte
 */
public class RefSolicitudNoAfiliado implements Serializable {
    
    private Integer id;

    public RefSolicitudNoAfiliado() {
    }

    public RefSolicitudNoAfiliado(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    @Override
    public String toString() {
        return "RefSolicitudNoAfiliado{" + "id=" + id + '}';
    }
    
}
