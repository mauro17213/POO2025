/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.gestionAtencion;

import com.saviasaludeps.savia.dominio.administracion.GnSede;
import com.saviasaludeps.savia.dominio.administracion.Usuario;
import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.util.List;

/**
 *
 * @author sgiraldov
 */
public class GatSedeFuncionario extends Auditoria {

    private Integer id;
    private boolean activo;
    private List<GatAtencion> gatAtencionList;
    private GnSede gnSedeId;
    private Usuario UsuarioId;
    
    //Variables auxiliares
    private boolean modificado;
    private Usuario usuarioAnterior;
    
    public GatSedeFuncionario() {
    }

    public GatSedeFuncionario(Integer id) {
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

    public List<GatAtencion> getGatAtencionList() {
        return gatAtencionList;
    }

    public void setGatAtencionList(List<GatAtencion> gatAtencionList) {
        this.gatAtencionList = gatAtencionList;
    }

    public GnSede getGnSedeId() {
        return gnSedeId;
    }

    public void setGnSedeId(GnSede gnSedeId) {
        this.gnSedeId = gnSedeId;
    }

    public Usuario getUsuarioId() {
        return UsuarioId;
    }

    public void setUsuarioId(Usuario UsuarioId) {
        this.UsuarioId = UsuarioId;
    }

    @Override
    public String toString() {
        return "GatSedeFuncionario{" + "id=" + id + ", activo=" + activo + ", usuarioCrea=" + usuarioCrea + ", terminalCrea=" + terminalCrea + ", fechaHoraCrea=" + fechaHoraCrea + ", usuarioModifica=" + usuarioModifica + ", terminalModifica=" + terminalModifica + ", fechaHoraModifica=" + fechaHoraModifica + ", gnSedeId=" + gnSedeId + ", UsuarioId=" + UsuarioId + '}';
    }
    
    //Metodos Auxiliares
    public String getActivoStr() {
        if (isActivo()) {
            return "Sí";
        } else {
            return "No";
        }
    }

    public boolean isModificado() {
        return modificado;
    }

    public void setModificado(boolean modificado) {
        this.modificado = modificado;
    }

    public Usuario getUsuarioAnterior() {
        return usuarioAnterior;
    }

    public void setUsuarioAnterior(Usuario usuarioAnterior) {
        this.usuarioAnterior = usuarioAnterior;
    }
    
    
}
