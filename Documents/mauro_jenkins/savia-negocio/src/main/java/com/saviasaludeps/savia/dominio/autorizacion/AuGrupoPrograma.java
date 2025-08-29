/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.autorizacion;

import com.saviasaludeps.savia.dominio.especial.PePrograma;
import com.saviasaludeps.savia.dominio.generico.Auditoria;

/**
 *
 * @author sgiraldo
 */
public class AuGrupoPrograma extends Auditoria {
    
    private Integer id;
    private AuGrupo auGrupoId;
    private PePrograma peProgramaId;

    public AuGrupoPrograma() {
    }

    public AuGrupoPrograma(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public AuGrupo getAuGrupoId() {
        return auGrupoId;
    }

    public void setAuGrupoId(AuGrupo auGrupoId) {
        this.auGrupoId = auGrupoId;
    }

    public PePrograma getPeProgramaId() {
        return peProgramaId;
    }

    public void setPeProgramaId(PePrograma peProgramaId) {
        this.peProgramaId = peProgramaId;
    }

    @Override
    public String toString() {
        return "AuGrupoPrograma{" + "id=" + id + ", auGrupoId=" + auGrupoId + ", peProgramaId=" + peProgramaId + '}';
    }
    
}
