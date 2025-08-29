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
public class AuRechazoItem extends Auditoria {
    
    private Integer id;
    private AuAnexo3Item auAnexo3ItemId;
    private AuRechazo auRechazoId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public AuAnexo3Item getAuAnexo3ItemId() {
        return auAnexo3ItemId;
    }

    public void setAuAnexo3ItemId(AuAnexo3Item auAnexo3ItemId) {
        this.auAnexo3ItemId = auAnexo3ItemId;
    }

    public AuRechazo getAuRechazoId() {
        return auRechazoId;
    }

    public void setAuRechazoId(AuRechazo auRechazoId) {
        this.auRechazoId = auRechazoId;
    }

    @Override
    public String toString() {
        return "AuRechazoItem{" + "id=" + id + '}';
    }
}
