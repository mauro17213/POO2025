package com.saviasaludeps.savia.solicitud.dominio;

import java.io.Serializable;

public class ValidaRespuestaGrupoAsignado implements Serializable {

    public final static int CODIGO_ESTADO_AUTORIZADO = 0;
    public final static int CODIGO_ESTADO_ITEM_PENDIENTE = 1;
    public final static int CODIGO_ESTADO_ITEM_RECHAZADO = 2;
    public final static int CODIGO_ESTADO_ERROR = 3;

    private int codigo;
    private String mensaje;
    private int auGrupoId;
    private int auGrupoUsuarioId;

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public int getAuGrupoId() {
        return auGrupoId;
    }

    public void setAuGrupoId(int auGrupoId) {
        this.auGrupoId = auGrupoId;
    }

    public int getAuGrupoUsuarioId() {
        return auGrupoUsuarioId;
    }

    public void setAuGrupoUsuarioId(int auGrupoUsuarioId) {
        this.auGrupoUsuarioId = auGrupoUsuarioId;
    }

    @Override
    public String toString() {
        return "ValidaRespuestaGrupoAsignado{" + "codigo=" + codigo + ", mensaje=" + mensaje + ", auGrupoId=" + auGrupoId + ", auGrupoUsuarioId=" + auGrupoUsuarioId + '}';
    }

}
