/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.administracion;

import com.saviasaludeps.savia.dominio.atencionusuario.AusCaso;
import com.saviasaludeps.savia.dominio.generico.Auditoria;
import com.saviasaludeps.savia.dominio.gestionAtencion.GatAtencion;
import com.saviasaludeps.savia.dominio.gestionAtencion.GatSedeConfiguracion;
import com.saviasaludeps.savia.dominio.gestionAtencion.GatSedeFuncionario;
import com.saviasaludeps.savia.dominio.gestionAtencion.GatSedeTaquilla;
import com.saviasaludeps.savia.dominio.gestionAtencion.GatTiquete;
import java.util.List;

/**
 *
 * @author acuartas
 */
public class GnSede extends Auditoria {

    private Integer id;
    private String nombre;
    private String descripcion;
    private String direccion;
    private String telefono;
    private boolean activo;
    private Integer maeTipoId;
    private String maeTipoCodigo;
    private String maeTipoValor;
    private Empresa gnEmpresaId;
    private Ubicacion gnUbicacionId;
    private List<GatAtencion> gatAtencionList;
    private List<GatSedeFuncionario> gatSedeFuncionarioList;
    private List<GatSedeConfiguracion> gatSedeConfiguracionList;
    private List<GatSedeTaquilla> gatSedeTaquillaList;
    private List<GatTiquete> gatTiqueteList;
    private List<GnSedeHorario> gnSedeHorarioList;
    private List<AusCaso> ausCasoList;
    
    //Variables auxiliares
    private GatSedeConfiguracion configuracion;
    private boolean vacia;

    public GnSede() {
    }

    public GnSede(Integer id) {
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public Integer getMaeTipoId() {
        return maeTipoId;
    }

    public void setMaeTipoId(Integer maeTipoId) {
        this.maeTipoId = maeTipoId;
    }

    public String getMaeTipoCodigo() {
        return maeTipoCodigo;
    }

    public void setMaeTipoCodigo(String maeTipoCodigo) {
        this.maeTipoCodigo = maeTipoCodigo;
    }

    public String getMaeTipoValor() {
        return maeTipoValor;
    }

    public void setMaeTipoValor(String maeTipoValor) {
        this.maeTipoValor = maeTipoValor;
    }

    public Empresa getGnEmpresaId() {
        return gnEmpresaId;
    }

    public void setGnEmpresaId(Empresa gnEmpresaId) {
        this.gnEmpresaId = gnEmpresaId;
    }

    public Ubicacion getGnUbicacionId() {
        return gnUbicacionId;
    }

    public void setGnUbicacionId(Ubicacion gnUbicacionId) {
        this.gnUbicacionId = gnUbicacionId;
    }

    public List<GatAtencion> getGatAtencionList() {
        return gatAtencionList;
    }

    public void setGatAtencionList(List<GatAtencion> gatAtencionList) {
        this.gatAtencionList = gatAtencionList;
    }

    public List<GatSedeFuncionario> getGatSedeFuncionarioList() {
        return gatSedeFuncionarioList;
    }

    public void setGatSedeFuncionarioList(List<GatSedeFuncionario> gatSedeFuncionarioList) {
        this.gatSedeFuncionarioList = gatSedeFuncionarioList;
    }

    public List<GatSedeConfiguracion> getGatSedeConfiguracionList() {
        return gatSedeConfiguracionList;
    }

    public void setGatSedeConfiguracionList(List<GatSedeConfiguracion> gatSedeConfiguracionList) {
        this.gatSedeConfiguracionList = gatSedeConfiguracionList;
    }

    public List<GatSedeTaquilla> getGatSedeTaquillaList() {
        return gatSedeTaquillaList;
    }

    public void setGatSedeTaquillaList(List<GatSedeTaquilla> gatSedeTaquillaList) {
        this.gatSedeTaquillaList = gatSedeTaquillaList;
    }

    public List<GatTiquete> getGatTiqueteList() {
        return gatTiqueteList;
    }

    public void setGatTiqueteList(List<GatTiquete> gatTiqueteList) {
        this.gatTiqueteList = gatTiqueteList;
    }

    public List<GnSedeHorario> getGnSedeHorarioList() {
        return gnSedeHorarioList;
    }

    public void setGnSedeHorarioList(List<GnSedeHorario> gnSedeHorarioList) {
        this.gnSedeHorarioList = gnSedeHorarioList;
    }

    public List<AusCaso> getAusCasoList() {
        return ausCasoList;
    }

    public void setAusCasoList(List<AusCaso> ausCasoList) {
        this.ausCasoList = ausCasoList;
    }
    
    //Getter auxiliares
    public String getActivoStr() {
        if (isActivo()) {
            return "Sí";
        } else {
            return "No";
        }
    }

    public GatSedeConfiguracion getConfiguracion() {
        return configuracion;
    }

    public void setConfiguracion(GatSedeConfiguracion configuracion) {
        this.configuracion = configuracion;
    }
    
    public boolean getTieneTurnero() {
        return getConfiguracion() != null && getConfiguracion().getTurnero() > 0;
    }

    public boolean isVacia() {
        return vacia;
    }

    public void setVacia(boolean vacia) {
        this.vacia = vacia;
    }

    @Override
    public String toString() {
        return "GnSede{" + "id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + ", direccion=" + direccion + ", telefono=" + telefono + ", activo=" + activo + ", maeTipoId=" + maeTipoId + ", maeTipoCodigo=" + maeTipoCodigo + ", maeTipoValor=" + maeTipoValor + '}';
    }
    
}
