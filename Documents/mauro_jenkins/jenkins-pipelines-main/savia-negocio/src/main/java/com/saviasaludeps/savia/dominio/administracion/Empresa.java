/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.administracion;

import com.saviasaludeps.savia.dominio.contratacion.CntPrestador;
import com.saviasaludeps.savia.dominio.generico.Auditoria;

/**
 *
 * @author rpalacic
 */
public class Empresa extends Auditoria {

    public Empresa() {
        ciudad = new Ubicacion();
    }
    public Empresa(Integer id) {
        ciudad = new Ubicacion();
        this.id = id;
    }
    public Empresa(Integer id, String nit, String razonSocial, String nombreComercial) {
        ciudad = new Ubicacion();
        this.id = id;
        this.nit = nit;
        this.razonSocial = razonSocial;
        this.nombreComercial = nombreComercial;
    }
    
    private Integer id = 0;
    private Ubicacion ciudad = null;
    private String nit = "";
    private String razonSocial = "";
    private String nombreComercial = "";
    private String descripcion = "";
    private String codigoHabilitacion;
    private boolean administradora = false;
    private boolean activa = true;
    private String receptorUsuario = "";
    private String receptorContrasena = "";
    private CntPrestador cntPrestador;
    private Short sesionesUsuario;
    

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Ubicacion getCiudad() {
        return ciudad;
    }

    public void setCiudad(Ubicacion ciudad) {
        this.ciudad = ciudad;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getNombreComercial() {
        return nombreComercial;
    }

    public void setNombreComercial(String nombreComercial) {
        this.nombreComercial = nombreComercial;
    }

    public String getDescripcion() {
        return descripcion;
    }
    public String getDescripcionCorta() {
        if(descripcion!=null && descripcion.length()>32){
            return descripcion.substring(0, 32);
        }else{
            return descripcion;
        }
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCodigoHabilitacion() {
        return codigoHabilitacion;
    }

    public void setCodigoHabilitacion(String codigoHabilitacion) {
        this.codigoHabilitacion = codigoHabilitacion;
    }

    public boolean isAdministradora() {
        return administradora;
    }
    
    public String getAdministradoraStr() {
        if(isAdministradora()) return "SI";
            else return "NO";
    }

    public void setAdministradora(boolean administradora) {
        this.administradora = administradora;
    }

    public boolean isActiva() {
        return activa;
    }
    
    public String getActivaStr() {
        if(isActiva()) return "SI";
            else return "NO";
    }

    public void setActiva(boolean activo) {
        this.activa = activo;
    }

    public String getReceptorUsuario() {
        return receptorUsuario;
    }

    public void setReceptorUsuario(String receptorUsuario) {
        this.receptorUsuario = receptorUsuario;
    }

    public String getReceptorContrasena() {
        return receptorContrasena;
    }

    public void setReceptorContrasena(String receptorContrasena) {
        this.receptorContrasena = receptorContrasena;
    }

    public CntPrestador getCntPrestador() {
        return cntPrestador;
    }

    public void setCntPrestador(CntPrestador cntPrestador) {
        this.cntPrestador = cntPrestador;
    }

    public Short getSesionesUsuario() {
        return sesionesUsuario;
    }

    public void setSesionesUsuario(Short sesionesUsuario) {
        this.sesionesUsuario = sesionesUsuario;
    }
    
    @Override
    public String toString() {
        return "Empresa{" + "id=" + id + ", ciudad=" + ciudad + ", nit=" + nit + ", razonSocial=" + razonSocial + ", nombreComercial=" + nombreComercial + ", descripcion=" + descripcion + ", codigoHabilitacion=" + codigoHabilitacion + ", administradora=" + getAdministradoraStr() + ", activa=" + getActivaStr() + ", receptorUsuario=" + receptorUsuario + ", receptorContrasena=" + receptorContrasena + '}';
    }
    
}
