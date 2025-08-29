/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.auditoria.concurrente;

import com.saviasaludeps.savia.dominio.administracion.Usuario;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestador;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestadorSede;
import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.util.List;

/**
 *
 * @author sgiraldov
 */
public class AucAuditor extends Auditoria {
    
    private Integer id;
    private Usuario gnUsuarioId;
    private CntPrestador cntPrestadorId;
    private CntPrestadorSede cntPrestadorSedeId;
    private boolean activo;
    private List<AucAuditorHistorico> aucAuditorHistoricoList;

    public AucAuditor() {
    }

    public AucAuditor(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Usuario getGnUsuarioId() {
        return gnUsuarioId;
    }

    public void setGnUsuarioId(Usuario gnUsuarioId) {
        this.gnUsuarioId = gnUsuarioId;
    }

    public CntPrestador getCntPrestadorId() {
        return cntPrestadorId;
    }

    public void setCntPrestadorId(CntPrestador cntPrestadorId) {
        this.cntPrestadorId = cntPrestadorId;
    }

    public CntPrestadorSede getCntPrestadorSedeId() {
        return cntPrestadorSedeId;
    }

    public void setCntPrestadorSedeId(CntPrestadorSede cntPrestadorSedeId) {
        this.cntPrestadorSedeId = cntPrestadorSedeId;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public List<AucAuditorHistorico> getAucAuditorHistoricoList() {
        return aucAuditorHistoricoList;
    }

    public void setAucAuditorHistoricoList(List<AucAuditorHistorico> aucAuditorHistoricoList) {
        this.aucAuditorHistoricoList = aucAuditorHistoricoList;
    }

    @Override
    public String toString() {
        return "AucAuditor{" + "id=" + id + ", gnUsuarioId=" + gnUsuarioId + ", cntPrestadorId=" + cntPrestadorId + ", cntPrestadorSedeId=" + cntPrestadorSedeId + ", activo=" + activo + ", aucAuditorHistoricoList=" + aucAuditorHistoricoList + '}';
    }

}
