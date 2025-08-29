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
public class GatSedeTaquilla extends Auditoria {

    private static final String SI = "Sí";
    private static final String NO = "No";

    private Integer id;
    private String nombre;
    private boolean activo;
    private boolean disponible;
    private List<GatAtencion> gatAtencionList;
    private List<GatTiketeLlamado> gatTiketeLlamadoList;
    private List<GatTaquillaServicio> gatTaquillaServicioList;
    private GnSede gnSedeId;
    private Usuario UsuarioId;
    private GatTiempo reposoActual;
    private List<GatTaquillaFuncionario> gatTaquillaFuncionarioList;

    //Auxiliares
    private boolean modificado;
    private boolean seleccionada;

    public GatSedeTaquilla() {
        this.activo = true;
        this.disponible = true;
    }

    public GatSedeTaquilla(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public List<GatAtencion> getGatAtencionList() {
        return gatAtencionList;
    }

    public void setGatAtencionList(List<GatAtencion> gatAtencionList) {
        this.gatAtencionList = gatAtencionList;
    }

    public String getGatAtencionHTML() {
        String str = "";
        for (GatAtencion ate : gatAtencionList) {
            if (!str.equals("")) {
                str += "<br/>";
            }
            str += "" + ate.getGatTiquete().getNumero() + " (" + ate.getGatTiquete().getMaeTipoServicioValor() + ") - " + ate.getEstadoStr() + " - " + ate.getTiempoStr();
        }
        return str;
    }

    public List<GatTiketeLlamado> getGatTiketeLlamadoList() {
        return gatTiketeLlamadoList;
    }

    public void setGatTiketeLlamadoList(List<GatTiketeLlamado> gatTiketeLlamadoList) {
        this.gatTiketeLlamadoList = gatTiketeLlamadoList;
    }

    public List<GatTaquillaServicio> getGatTaquillaServicioList() {
        return gatTaquillaServicioList;
    }

    public void setGatTaquillaServicioList(List<GatTaquillaServicio> gatTaquillaServicioList) {
        this.gatTaquillaServicioList = gatTaquillaServicioList;
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

    public GatTiempo getReposoActual() {
        return reposoActual;
    }

    public String getEstadoStr() {
        String _est;
        if (isActivo()) {
            if (getUsuarioId() == null) {
                _est = "SIN FUNCIONARIO";
            } else {
                if (getReposoActual() == null) {
                    _est = "En Operación";
                } else {
                    _est = "Reposo - " + getReposoActual().getTipoStr();
                }
            }
        } else {
            _est = "Inactivo";
        }
        return _est;
    }

    public void setReposoActual(GatTiempo reposoActual) {
        this.reposoActual = reposoActual;
    }

    public List<GatTaquillaFuncionario> getGatTaquillaFuncionarioList() {
        return gatTaquillaFuncionarioList;
    }

    public void setGatTaquillaFuncionarioList(List<GatTaquillaFuncionario> gatTaquillaFuncionarioList) {
        this.gatTaquillaFuncionarioList = gatTaquillaFuncionarioList;
    }

    //Metodos Auxiliares
    public String getDisponibleStr() {
        if (isDisponible()) {
            return SI;
        } else {
            return NO;
        }
    }

    public String getActivoStr() {
        if (isActivo()) {
            return SI;
        } else {
            return NO;
        }
    }

    public boolean isModificado() {
        return modificado;
    }

    public void setModificado(boolean modificado) {
        this.modificado = modificado;
    }

    public String getNombreServicios() {
        String servicios = "";
        if (getGatTaquillaServicioList() != null) {
            for (GatTaquillaServicio servicio : getGatTaquillaServicioList()) {
                if (!servicios.isEmpty()) {
                    servicios += ",";
                }
                servicios += servicio.getMaeTipoServicioValor();
            }
        }
        return servicios;
    }

    public boolean isSeleccionada() {
        return seleccionada;
    }

    public void setSeleccionada(boolean seleccionada) {
        this.seleccionada = seleccionada;
    }

    @Override
    public String toString() {
        return "GatSedeTaquilla{" + "id=" + id + ", nombre=" + nombre + ", activo=" + activo + ", disponible=" + disponible + ", usuarioCrea=" + usuarioCrea + ", terminalCrea=" + terminalCrea + ", fechaHoraCrea=" + fechaHoraCrea + ", usuarioModifica=" + usuarioModifica + ", terminalModifica=" + terminalModifica + ", fechaHoraModifica=" + fechaHoraModifica + ", gnSedeId=" + gnSedeId + ", UsuarioId=" + UsuarioId + '}';
    }
}
