/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.tutela;

import com.saviasaludeps.savia.dominio.administracion.Usuario;
import com.saviasaludeps.savia.dominio.generico.Auditoria;

/**
 *
 * @author jorge perez
 */

public class TuGrupoUsuario extends Auditoria {
    
    private static final long serialVersionUID = 1L;
    public static final int TIPO_ABOGADO = 1;
    public static final int TIPO_ASISTENTE_LEGAL = 2;
    public static final int TIPO_MEDICO = 3;
    public static final int TIPO_ENFERMERO = 4;
    public static final int TIPO_GESTOR = 5;
    public static final int TIPO_DIGITADOR = 6;

    private Integer id;
    private int tipo;
    private boolean activo;
    private Usuario usuario;
    private TuGrupo tuGrupo;
    private int idMaEstadoGrupo;

    public TuGrupoUsuario() {
    }

    public TuGrupoUsuario(Integer id) {
        this.id = id;
    }

    public TuGrupoUsuario(Integer id, int tipo, boolean activo) {
        this.id = id;
        this.tipo = tipo;
        this.activo = activo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public boolean getActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public Usuario getUsuario() {
        if (usuario == null) {
            usuario = new Usuario();
        }
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public TuGrupo getTuGrupo() {
        if (tuGrupo == null) {
            tuGrupo = new TuGrupo();
        }
        return tuGrupo;
    }

    public void setTuGrupo(TuGrupo tuGrupo) {
        this.tuGrupo = tuGrupo;
    }
    
    public String getTipoStr() {
        return TuGrupoUsuario.getTipoStr(getTipo());
    }

    public int getIdMaEstadoGrupo() {
        return idMaEstadoGrupo;
    }

    public void setIdMaEstadoGrupo(int idMaEstadoGrupo) {
        this.idMaEstadoGrupo = idMaEstadoGrupo;
    }

    
     public static String getTipoStr(int tipo) {
        switch (tipo) {
            case TIPO_ABOGADO:
                return "Abogado";
            case TIPO_ASISTENTE_LEGAL:
                return "Asistente Legal";
            case TIPO_ENFERMERO:
                return "Enfermero";
            case TIPO_MEDICO:
                return "Médico";
            case TIPO_GESTOR:
                return "Gestor";
            case TIPO_DIGITADOR:
                return "Digitador";
            default:
                return "";
        }
    }
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TuGrupoUsuario)) {
            return false;
        }
        TuGrupoUsuario other = (TuGrupoUsuario) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

//    @Override
//    public String toString() {
//        return "TuGrupoUsuario{" + "id=" + id + ", tipo=" + tipo + ", activo=" + activo + ", usuario=" + getUsuario().getId() + ", tuGrupo=" + getTuGrupo().getId() + '}';
//    }

    @Override
    public String toString() {
        return "TuGrupoUsuario{" + "id=" + id + ", tipo=" + tipo + ", activo=" + activo + ", usuario=" + usuario.toStringCorto() + '}';
    }

    
    
}
