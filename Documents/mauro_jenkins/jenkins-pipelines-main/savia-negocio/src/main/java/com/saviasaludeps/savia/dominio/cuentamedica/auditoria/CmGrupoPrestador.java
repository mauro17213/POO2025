package com.saviasaludeps.savia.dominio.cuentamedica.auditoria;

import com.saviasaludeps.savia.dominio.contratacion.CntPrestador;
import com.saviasaludeps.savia.dominio.generico.Auditoria;

public class CmGrupoPrestador extends Auditoria {

    private Integer id;
    private boolean activo;
    private CmGrupo cmGrupo;
    private CntPrestador cntPrestador;
    private Integer idInsertar;
    private String razonSocial;

    public CmGrupoPrestador() {
    }

    public CmGrupoPrestador(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean getActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public CmGrupo getCmGrupo() {
        return cmGrupo;
    }

    public void setCmGrupo(CmGrupo cmGrupo) {
        this.cmGrupo = cmGrupo;
    }

    public CntPrestador getCntPrestador() {
        return cntPrestador;
    }

    public void setCntPrestador(CntPrestador cntPrestador) {
        this.cntPrestador = cntPrestador;
    }

    public Integer getIdInsertar() {
        return idInsertar;
    }

    public void setIdInsertar(Integer idInsertar) {
        this.idInsertar = idInsertar;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }
    
    @Override
    public String toString() {
        return "CmGrupoPrestador{" + "id=" + id + ", activo=" + activo + ", cmGrupo=" + cmGrupo.getId() + ", cntPrestador=" + cntPrestador.getId() + '}';
    }

}