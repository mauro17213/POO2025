package com.saviasaludeps.savia.dominio.recobro;

import com.saviasaludeps.savia.dominio.administracion.Usuario;
import com.saviasaludeps.savia.dominio.generico.Auditoria;

/**
 *
 * @author Stiven Giraldo
 */
public class RcoGrupoUsuario extends Auditoria {

    public static final int Capita = 0;
    public static final int PGP = 1;

    private Integer id;
    private boolean activo;
    private Usuario gnUsuarioId;
    private RcoGrupo rcoGrupoId;
  

    public RcoGrupoUsuario() {
    }

    public RcoGrupoUsuario(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public Usuario getGnUsuarioId() {
        return gnUsuarioId;
    }

    public void setGnUsuarioId(Usuario gnUsuarioId) {
        this.gnUsuarioId = gnUsuarioId;
    }

    public RcoGrupo getRcoGrupoId() {
        return rcoGrupoId;
    }

    public void setRcoGrupoId(RcoGrupo rcoGrupoId) {
        this.rcoGrupoId = rcoGrupoId;
    }

    @Override
    public String toString() {
        return "RcoGrupoUsuario{" + "id=" + id + ", activo=" + activo + '}';
    }
}
