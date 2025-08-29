/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.autorizacion;

import com.saviasaludeps.savia.dominio.generico.Auditoria;

/**
 *
 * @author Stiven Giraldo
 */
public class AuGrupoHistorico extends Auditoria {
    
    private Integer id;
    private String toString;
    private AuGrupo auGruposId;

    public AuGrupoHistorico() {
    }

    public AuGrupoHistorico(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getToString() {
        return toString;
    }

    public void setToString(String toString) {
        this.toString = toString;
    }

    public AuGrupo getAuGruposId() {
        return auGruposId;
    }

    public void setAuGruposId(AuGrupo auGruposId) {
        this.auGruposId = auGruposId;
    }

    @Override
    public String toString() {
        return "AuGrupoHistorico{" + "id=" + id + ", toString=" + toString + ", auGruposId=" + auGruposId + '}';
    }
    

}