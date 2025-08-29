package com.saviasaludeps.savia.dominio.administracion;

import com.saviasaludeps.savia.dominio.generico.Auditoria;

/**
 *
 * @author Raúl Palacios
 */
public class UsuarioRol extends Auditoria{

    private Integer id;
    private Rol rol;
    private Usuario usuario;

    public UsuarioRol() {
    }

    public UsuarioRol(Rol rol) {
        this.rol = rol;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public String toString() {
        return "UsuarioRol{" + "id=" + id + ", rol=" + rol + ", usuario=" + usuario + '}';
    }

}
