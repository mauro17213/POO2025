/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.cuentamedica.auditoria;

import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmFactura;
import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.util.List;

public class CmGrupo extends Auditoria {
    
    public final static int CATEGORIA_PBS    = 0;
    public final static int CATEGORIA_NO_PBS = 1;
    public final static int CATEGORIA_PGP = 2;
    
    public final static boolean TIPO_GRUPO_AUDITORIA  = false;
    public final static boolean TIPO_GRUPO_RADICACION = true;

    private Integer id;
    private String nombre;
    private String descripcion;
    private boolean activo;
    private List<CmGrupoHistorico> listaCmGrupoHistoricos;
    private List<CmGrupoUsuario> listaCmGrupoUsuarios;
    private List<CmGrupoPrestador> listaCmGrupoPrestadores;
    private List<CmFactura> listaCmFacturas;
    private boolean pbs;
    private boolean camaFija;
    private boolean tipoGrupo;
    private Integer categoria;
    private Integer usuariosIdAsignacion;

    public CmGrupo() {
    }

    public CmGrupo(Integer id) {
        this.id = id;
    }

    public CmGrupo(Integer id, String nombre, String descripcion) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
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

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public List<CmGrupoHistorico> getListaCmGrupoHistoricos() {
        return listaCmGrupoHistoricos;
    }

    public void setListaCmGrupoHistoricos(List<CmGrupoHistorico> listaCmGrupoHistoricos) {
        this.listaCmGrupoHistoricos = listaCmGrupoHistoricos;
    }

    public List<CmGrupoUsuario> getListaCmGrupoUsuarios() {
        return listaCmGrupoUsuarios;
    }

    public void setListaCmGrupoUsuarios(List<CmGrupoUsuario> listaCmGrupoUsuarios) {
        this.listaCmGrupoUsuarios = listaCmGrupoUsuarios;
    }

    public List<CmGrupoPrestador> getListaCmGrupoPrestadores() {
        return listaCmGrupoPrestadores;
    }

    public void setListaCmGrupoPrestadores(List<CmGrupoPrestador> listaCmGrupoPrestadores) {
        this.listaCmGrupoPrestadores = listaCmGrupoPrestadores;
    }

    public List<CmFactura> getListaCmFacturas() {
        return listaCmFacturas;
    }

    public void setListaCmFacturas(List<CmFactura> listaCmFacturas) {
        this.listaCmFacturas = listaCmFacturas;
    }

    public String getActivoStr() {
        if (this.activo) {
            return "Activo";
        } else {
            return "Inactivo";
        }
    }

    public String getCamaFijaStr() {
        if (this.camaFija == true) {
            return "Si";
        } else {
            return "No";
        }
    }

    public String getPbsStr() {

        if (this.pbs == true) {
            return "Si";
        } else {
            return "No";
        }
    }

    public boolean getPbs() {
        return pbs;
    }

    public void setPbs(boolean pbs) {
        this.pbs = pbs;
    }

    public boolean getCamaFija() {
        return camaFija;
    }

    public void setCamaFija(boolean camaFija) {
        this.camaFija = camaFija;
    }

    public boolean isTipoGrupo() {
        return tipoGrupo;
    }

    public void setTipoGrupo(boolean tipoGrupo) {
        this.tipoGrupo = tipoGrupo;
    }
    
     public boolean getTipoGrupo() {
        return tipoGrupo;
    }
     
    public static String getTipoGrupoStr(boolean tipoGrupo) {
        return tipoGrupo ? "Radicación" :"Auditoría";
    }
    
    public String getTipoGrupoStr() {
        return CmGrupo.getTipoGrupoStr(getTipoGrupo());
    }

    public Integer getCategoria() {
        return categoria;
    }

    public void setCategoria(Integer categoria) {
        this.categoria = categoria;
    }
    
    public static String getCategoriaStr(Integer categoria) {
        if (categoria == null) {
            return "";
        }
        if (CATEGORIA_PBS == categoria) {
            return "PBS";
        }
        if (CATEGORIA_NO_PBS == categoria) {
            return "NO PBS";
        }
        if (CATEGORIA_PGP == categoria) {
            return "PGP";
        }
        return "";
    }
    
    public String getCategoriaStr() {
        return CmGrupo.getCategoriaStr(getCategoria());
    }

    public Integer getUsuariosIdAsignacion() {
        return usuariosIdAsignacion;
    }

    public void setUsuariosIdAsignacion(Integer usuariosIdAsignacion) {
        this.usuariosIdAsignacion = usuariosIdAsignacion;
    }
    
    @Override
    public String toString() {
        return "CmGrupo{" + "id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + ", activo=" + activo + '}';
    }

}
