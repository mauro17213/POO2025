package com.saviasaludeps.savia.dominio.cuentamedica.auditoria;

import com.saviasaludeps.savia.dominio.administracion.Usuario;
import com.saviasaludeps.savia.dominio.generico.Auditoria;

public class CmGrupoUsuario extends Auditoria {

    private Integer id;
    private int tipo;
    private boolean activo;
    private CmGrupo cmGrupo;
    private Usuario gnUsuario;
    private Integer idInsertar;
    private String tipoStr;
    private String nombreUsuario;

    public final static int TIPO_NA = 0;
    public final static int TIPO_LIDER = 1;
    public final static int TIPO_TECNICO = 2;
    public final static int TIPO_MEDICO = 3;
    public final static int TIPO_USUARIO_GESTIONA = 4;
    public final static int TIPO_AUXILIAR_RADICACION = 5;

    public CmGrupoUsuario() {
    }

    public CmGrupoUsuario(Integer id) {
        this.id = id;
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

    public boolean isActivo() {
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

    public Usuario getGnUsuario() {
        return gnUsuario;
    }

    public void setGnUsuario(Usuario gnUsuario) {
        this.gnUsuario = gnUsuario;
    }

    public Integer getIdInsertar() {
        return idInsertar;
    }

    public void setIdInsertar(Integer idInsertar) {
        this.idInsertar = idInsertar;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getTipoStr() {
        String str;
           switch (this.tipo) {
            case (TIPO_NA):
                str = "N/A";
                break;
            case (TIPO_LIDER):
                str = "LIDER";
                break;
            case (TIPO_TECNICO):
                str = "TÉCNICO";
                break;
            case (TIPO_MEDICO):
                str = "MÉDICO";
                break;
            case (TIPO_USUARIO_GESTIONA):
                str = "USUARIO GESTIONA";
                break;
             case (TIPO_AUXILIAR_RADICACION):
                str = "AUXILIAR RADICACIÓN";
                break;
            default:
                str = "";
                break;
        }
        return str;
    }

    @Override
    public String toString() {
        return "CmGrupoUsuario{" + "id=" + id + ", tipo=" + tipo + ", activo=" + activo + '}';
    }

}
