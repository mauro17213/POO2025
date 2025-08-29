package com.saviasaludeps.savia.dominio.cuentamedica.auditoria;

import com.saviasaludeps.savia.dominio.generico.Auditoria;

public class CmGrupoHistorico extends Auditoria {
 private Integer id;
    private String toString;
    private CmGrupo cmGrupo;    

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

    public CmGrupo getCmGrupo() {
        return cmGrupo;
    }

    public void setCmGrupo(CmGrupo cmGrupo) {
        this.cmGrupo = cmGrupo;
    }
    
}
