
package com.saviasaludeps.savia.dominio.juridico;

import com.saviasaludeps.savia.dominio.administracion.Usuario;
import com.saviasaludeps.savia.dominio.generico.Auditoria;

/**
 *
 * @author idbohorquez
 */
public class CntjUsuarioGrupo extends Auditoria{
    
    private Integer id;
    private CntjGrupo cntjGruposId;
    private Usuario gnUsuarioId;

    public CntjUsuarioGrupo() {
    }

    public CntjUsuarioGrupo(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public CntjGrupo getCntjGruposId() {
        return cntjGruposId;
    }

    public void setCntjGruposId(CntjGrupo cntjGruposId) {
        this.cntjGruposId = cntjGruposId;
    }

    public Usuario getGnUsuarioId() {
        return gnUsuarioId;
    }

    public void setGnUsuarioId(Usuario gnUsuarioId) {
        this.gnUsuarioId = gnUsuarioId;
    }

    @Override
    public String toString() {
        return "CntjUsuarioGrupo{" + "id=" + id + ", cntjGruposId=" + cntjGruposId + ", gnUsuarioId=" + gnUsuarioId + '}';
    }

}
