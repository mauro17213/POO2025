package com.saviasaludeps.savia.dominio.informe;

import com.saviasaludeps.savia.dominio.administracion.Usuario;
import com.saviasaludeps.savia.dominio.generico.Auditoria;

public class InfGrupoUsuario extends Auditoria {

    private Integer id;
    private Usuario usuario;
    private boolean activo;
    private InfGrupo grupo;

    public InfGrupoUsuario() {
        this.usuario = new Usuario();
    }

    public InfGrupoUsuario(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public InfGrupo getGrupo() {
        return grupo;
    }

    public void setGrupo(InfGrupo grupo) {
        this.grupo = grupo;
    }

    public boolean getActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public String getActivoStr() {
        String str;
        if (activo) {
            str = "SI";
        } else {
            str = "NO";
        }
        return str;
    }

    @Override
    public String toString() {
        return "InfGrupoUsuario{" + "id=" + id + ", usuarioCrea=" + usuarioCrea + ", usuario=" + usuario + ", grupo=" + grupo + '}';
    }

}
