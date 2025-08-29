/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.autorizacion;

import com.saviasaludeps.savia.dominio.contratacion.CntPrestador;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestadorSede;
import com.saviasaludeps.savia.dominio.generico.Auditoria;

/**
 *
 * @author Stiven Giraldo
 */
public class AuGrupoSede extends Auditoria {
    
    private Integer id;
    private AuGrupo auGrupo;
    private CntPrestadorSede cntPrestadorSede;
    private CntPrestador cntPrestadoresId;

    public AuGrupoSede() {
    }

    public AuGrupoSede(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public AuGrupo getAuGrupo() {
        return auGrupo;
    }

    public void setAuGrupo(AuGrupo auGrupo) {
        this.auGrupo = auGrupo;
    }

    public CntPrestadorSede getCntPrestadorSede() {
        return cntPrestadorSede;
    }

    public void setCntPrestadorSede(CntPrestadorSede cntPrestadorSede) {
        this.cntPrestadorSede = cntPrestadorSede;
    }

    public CntPrestador getCntPrestadoresId() {
        return cntPrestadoresId;
    }

    public void setCntPrestadoresId(CntPrestador cntPrestadoresId) {
        this.cntPrestadoresId = cntPrestadoresId;
    }

    @Override
    public String toString() {
        return "AuGrupoSede{" + "id=" + id + ", auGrupo=" + auGrupo + ", cntPrestadorSede=" + cntPrestadorSede + ", cntPrestadoresId=" + cntPrestadoresId + '}';
    }
    
}
