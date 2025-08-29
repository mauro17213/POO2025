/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.externo;

import com.saviasaludeps.savia.dominio.administracion.Ubicacion;
import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 * @author admin
 */
public class AfiliadoActualizacion extends Auditoria{
    
    private Integer id;
    private String tipoDocumento;
    private String numeroDocumento;
    private String primerApellido;
    private String segundoApellido;
    private String primerNombre;
    private String segundoNombre;
    private Date fechaNacimiento;
    private String descripcionSexo;
    private String departamentoAfiliacion;
    private String municipioAfiliacion;
    private String estadoAfiliacion;
    private String contratoAfiliado;
    private String departamentoResidencia;
    private String municipioResidencia;
    private String zona;
    private String direccionAfiliado;
    private String telefono;
    private String celular;
    private String email;
    private Date fechaExpedicionDocumento;
    private String observacion;
    private Date fechaHoraNotificacion;
    private String codigoSexo;
    private String codigoZona;
 
    public static final String ZONA_URBANA = "U";
    public static final String ZONA_RURAL  = "R";
    
    private String direccionVia;
    private String direccionNro;
    private String direccionOrd1;
    private String direccionOrientacion;
    private String direccionPlaca1;
    private String direccionOrd2;
    private String direccionPlaca2;
    private String direccionDescripcion;
    
    private Ubicacion ciudadResidencia = null;

    public AfiliadoActualizacion() {
           ciudadResidencia = new Ubicacion();
    }

    public AfiliadoActualizacion(Integer id) {
        this.id = id;
        ciudadResidencia = new Ubicacion();
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }


    public String getDepartamentoAfiliacion() {
        return departamentoAfiliacion;
    }

    public void setDepartamentoAfiliacion(String departamentoAfiliacion) {
        this.departamentoAfiliacion = departamentoAfiliacion;
    }

    public String getMunicipioAfiliacion() {
        return municipioAfiliacion;
    }

    public void setMunicipioAfiliacion(String municipioAfiliacion) {
        this.municipioAfiliacion = municipioAfiliacion;
    }

    public String getEstadoAfiliacion() {
        return estadoAfiliacion;
    }

    public void setEstadoAfiliacion(String estadoAfiliacion) {
        this.estadoAfiliacion = estadoAfiliacion;
    }

    public String getContratoAfiliado() {
        return contratoAfiliado;
    }

    public void setContratoAfiliado(String contratoAfiliado) {
        this.contratoAfiliado = contratoAfiliado;
    }

    public String getDepartamentoResidencia() {
        return departamentoResidencia;
    }

    public void setDepartamentoResidencia(String departamentoResidencia) {
        this.departamentoResidencia = departamentoResidencia;
    }

    public String getMunicipioResidencia() {
        return municipioResidencia;
    }

    public void setMunicipioResidencia(String municipioResidencia) {
        this.municipioResidencia = municipioResidencia;
    }
    
    public String getDireccionNro() {
        if (direccionNro == null) {
            direccionNro = "";
        }
        return direccionNro;
    }

    public void setDireccionNro(String direccionNro) {
        this.direccionNro = direccionNro;
    }

    public String getDireccionOrd1() {
        if(direccionOrd1 == null){
         direccionOrd1 = "";
        }
        return direccionOrd1;
    }

    public void setDireccionOrd1(String direccionOrd1) {
        this.direccionOrd1 = direccionOrd1;
    }

    public String getDireccionOrientacion() {
        if(direccionOrientacion == null){
            direccionOrientacion = "";
        }
        return direccionOrientacion;
    }

    public void setDireccionOrientacion(String direccionOrientacion) {
        this.direccionOrientacion = direccionOrientacion;
    }

    public String getDireccionPlaca1() {
        if(direccionPlaca1 == null){
           direccionPlaca1 = "";
        }
        return direccionPlaca1;
    }

    public void setDireccionPlaca1(String direccionPlaca1) {
        this.direccionPlaca1 = direccionPlaca1;
    }

    public String getDireccionOrd2() {
        if(direccionOrd2 == null){
            direccionOrd2 = "";
        }
        return direccionOrd2;
    }

    public void setDireccionOrd2(String direccionOrd2) {
        this.direccionOrd2 = direccionOrd2;
    }

    public String getDireccionPlaca2() {
        if (direccionPlaca2 == null) {
            direccionPlaca2 = "";
        }
        return direccionPlaca2;
    }

    public void setDireccionPlaca2(String direccionPlaca2) {
        this.direccionPlaca2 = direccionPlaca2;
    }

    public String getDireccionDescripcion() {
        if (direccionDescripcion == null) {
            direccionDescripcion = "";
        }
        return direccionDescripcion;
    }

    public void setDireccionDescripcion(String direccionDescripcion) {
        this.direccionDescripcion = direccionDescripcion;
    }

    public String getDireccionAfiliado() {
        if( direccionAfiliado == null || "".equals(direccionAfiliado) ){
            StringBuilder strBuffer = new StringBuilder();
            strBuffer.append(this.getDireccionVia());
            strBuffer.append(" ");
            strBuffer.append(this.getDireccionNro());
            strBuffer.append(" ");
            strBuffer.append(this.getDireccionOrd1());
            strBuffer.append(" ");
            strBuffer.append(this.getDireccionOrientacion());
            strBuffer.append(" ");
            strBuffer.append(this.getDireccionPlaca1());
            strBuffer.append(" ");
            strBuffer.append(this.getDireccionOrd2());
            strBuffer.append(" ");
            strBuffer.append(this.getDireccionPlaca2());
            strBuffer.append(" ");
            strBuffer.append(this.getDireccionDescripcion());
            direccionAfiliado = strBuffer.toString() ;
        }
        return direccionAfiliado;
    }

    public void setDireccionAfiliado(String direccionAfiliado) {
        this.direccionAfiliado = direccionAfiliado;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getFechaExpedicionDocumento() {
        return fechaExpedicionDocumento;
    }

    public void setFechaExpedicionDocumento(Date fechaExpedicionDocumento) {
        this.fechaExpedicionDocumento = fechaExpedicionDocumento;
    }

    public Date getFechaHoraNotificacion() {
        return fechaHoraNotificacion;
    }

    public void setFechaHoraNotificacion(Date fechaHoraNotificacion) {
        this.fechaHoraNotificacion = fechaHoraNotificacion;
    }
    
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPrimerNombre() {
        return primerNombre;
    }

    public void setPrimerNombre(String primerNombre) {
        this.primerNombre = primerNombre;
    }

    public String getSegundoNombre() {
        return segundoNombre;
    }

    public void setSegundoNombre(String segundoNombre) {
        this.segundoNombre = segundoNombre;
    }

    public String getPrimerApellido() {
        return primerApellido;
    }

    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }

    public String getSegundoApellido() {
        return segundoApellido;
    }

    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido = segundoApellido;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getZona() {
        return zona;
    }

    public void setZona(String zona) {
        this.zona = zona;
    }
   

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }
    
    public String getTipoEstadoStr() {
        return AfiliadoActualizacion.getZonaStr(getZona());
    }
    
    public String getObservacion() {
        return observacion;
    }
    
    public String getDescripcionSexo() {
        return descripcionSexo;
    }

    public void setDescripcionSexo(String descripcionSexo) {
        this.descripcionSexo = descripcionSexo;
    }

    public String getCodigoSexo() {
        return codigoSexo;
    }

    public void setCodigoSexo(String codigoSexo) {
        this.codigoSexo = codigoSexo;
    }
    
     public String getCodigoZona() {
        return codigoZona;
    }

    public void setCodigoZona(String codigoZona) {
        this.codigoZona = codigoZona;
    }
    
    public String getDireccionVia() {
        if(direccionVia == null){
            direccionVia = "";
        }
        return direccionVia;
    }

    public void setDireccionVia(String direccionVia) {
        this.direccionVia = direccionVia;
    }

    public Ubicacion getCiudadResidencia() {
        return ciudadResidencia;
    }

    public void setCiudadResidencia(Ubicacion ciudadResidencia) {
        this.ciudadResidencia = ciudadResidencia;
    }
    
    
       
    public static Map<String, String> getTipoZonas() {
        Map<String, String> tipos = new LinkedHashMap<>();
        tipos.put( AfiliadoActualizacion.getZonaStr(ZONA_URBANA), AfiliadoActualizacion.ZONA_URBANA);
        tipos.put( AfiliadoActualizacion.getZonaStr(ZONA_RURAL), AfiliadoActualizacion.ZONA_RURAL);
        return tipos;
    }
    
    public static String getZonaStr(String tipoEstado) {
        String str;
        switch (tipoEstado) {
            case ZONA_URBANA:
                str = "Urbana";
                break;
            case ZONA_RURAL:
                str = "Rural";
                break;
            default:
                str = "Ninguno";
                break;
        }
        return str;
    }

}
