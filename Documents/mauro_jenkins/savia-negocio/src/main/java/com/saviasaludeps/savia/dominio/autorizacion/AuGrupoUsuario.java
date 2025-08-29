/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.autorizacion;

import com.saviasaludeps.savia.dominio.administracion.Usuario;
import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.util.Objects;

/**
 *
 * @author Stiven Giraldo
 */
public class AuGrupoUsuario extends Auditoria {

    public static final int TIPO_GENERICO = 0;
    public static final int TIPO_MEDICO = 1;
    public static final int TIPO_ENFERMERO = 2;
    public static final int TIPO_AUXILIAR = 3;
    public static final int TIPO_ODONTOLOGO = 4;
    public static final int TIPO_ORAL = 5;

    private Integer id;
    private AuGrupo auGrupo;
    private Usuario usuario;
    private boolean activo;
    private int tipo;
    private Integer maeTipoAuditorId;
    private String maeTipoAuditorCodigo;
    private String maeTipoAuditorValor;

    public AuGrupoUsuario() {
    }

    public AuGrupoUsuario(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public AuGrupo getAuGrupo() {
        return auGrupo;
    }

    public void setAuGrupo(AuGrupo auGrupo) {
        this.auGrupo = auGrupo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public boolean isActivo() {
        return activo;
    }

    public String getActivoStr() {
        return (activo) ? "SI" : "NO";
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public int getTipo() {
        return tipo;
    }

    public Integer getMaeTipoAuditorId() {
        return maeTipoAuditorId;
    }

    public void setMaeTipoAuditorId(Integer maeTipoAuditorId) {
        this.maeTipoAuditorId = maeTipoAuditorId;
    }

    public String getMaeTipoAuditorCodigo() {
        return maeTipoAuditorCodigo;
    }

    public void setMaeTipoAuditorCodigo(String maeTipoAuditorCodigo) {
        this.maeTipoAuditorCodigo = maeTipoAuditorCodigo;
    }

    public String getMaeTipoAuditorValor() {
        return maeTipoAuditorValor;
    }

    public void setMaeTipoAuditorValor(String maeTipoAuditorValor) {
        this.maeTipoAuditorValor = maeTipoAuditorValor;
    }

    public String getTipoStr() {
        switch (tipo) {
            case TIPO_GENERICO:
                return "N/A";
            case TIPO_AUXILIAR:
                return "AUXILIAR ENFERMERIA";
            case TIPO_ENFERMERO:
                return "ENFERMERO";
            case TIPO_MEDICO:
                return "MÉDICO";
            case TIPO_ODONTOLOGO:
                return "ODONTOLOGO";
            case TIPO_ORAL:
                return "AUXILIAR ORAL";
            default:
                return "";
        }
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "AuGrupoUsuario{" + "id=" + id + ", auGrupo=" + auGrupo + ", usuario=" + usuario + ", activo=" + getActivoStr() + ", tipo=" + getTipoStr() + '}';
    }

}
