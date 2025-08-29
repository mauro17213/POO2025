/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.auditoria.concurrente;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author pavacca
 */
public class ReporteHospitalizacion implements Serializable{
     
    private String strFechaDescarga;
    private String strNombres;
    private String strApellidos;
    private String strTipoDocumento;
    private String strNumeroDocumento;
    private String strEdad;
    private String strSede;
    private String strFechaIngreso;
    private String strAuditor;
    private String strSeguimientoDescripcion;
    private String strSeguimientoUsuario;
    private String strSeguimientoFecha;
    private String strInstanciaCausa;
    private String strInstanciaPropuesta;
    private String strInstanciaResumen;
    private String strInstanciaFecha;
    private String strFechaEgreso;
    private String strDestino;
    private String strDiasEstancia;
    private String strEstadoHospitalizacion;
    private List<AucDiagnostico> listaDiagnosticosIngreso;
    private List<AucHospitalizacionEstancia> listaEstancias;
    private List<AucDiagnostico> listaDiagnosticosEgreso;
    
    public ReporteHospitalizacion(){
        
    }

    public String getStrFechaDescarga() {
        return strFechaDescarga;
    }

    public void setStrFechaDescarga(String strFechaDescarga) {
        this.strFechaDescarga = strFechaDescarga;
    }

    public String getStrNombres() {
        return strNombres;
    }

    public void setStrNombres(String strNombres) {
        this.strNombres = strNombres;
    }

    public String getStrApellidos() {
        return strApellidos;
    }

    public void setStrApellidos(String strApellidos) {
        this.strApellidos = strApellidos;
    }

    public String getStrTipoDocumento() {
        return strTipoDocumento;
    }

    public void setStrTipoDocumento(String strTipoDocumento) {
        this.strTipoDocumento = strTipoDocumento;
    }

    public String getStrNumeroDocumento() {
        return strNumeroDocumento;
    }

    public void setStrNumeroDocumento(String strNumeroDocumento) {
        this.strNumeroDocumento = strNumeroDocumento;
    }

    public String getStrEdad() {
        return strEdad;
    }

    public void setStrEdad(String strEdad) {
        this.strEdad = strEdad;
    }

    public String getStrSede() {
        return strSede;
    }

    public void setStrSede(String strSede) {
        this.strSede = strSede;
    }

    public String getStrFechaIngreso() {
        return strFechaIngreso;
    }

    public void setStrFechaIngreso(String strFechaIngreso) {
        this.strFechaIngreso = strFechaIngreso;
    }

    public String getStrAuditor() {
        return strAuditor;
    }

    public void setStrAuditor(String strAuditor) {
        this.strAuditor = strAuditor;
    }

    public String getStrSeguimientoDescripcion() {
        return strSeguimientoDescripcion;
    }

    public void setStrSeguimientoDescripcion(String strSeguimientoDescripcion) {
        this.strSeguimientoDescripcion = strSeguimientoDescripcion;
    }

    public String getStrSeguimientoUsuario() {
        return strSeguimientoUsuario;
    }

    public void setStrSeguimientoUsuario(String strSeguimientoUsuario) {
        this.strSeguimientoUsuario = strSeguimientoUsuario;
    }

    public String getStrSeguimientoFecha() {
        return strSeguimientoFecha;
    }

    public void setStrSeguimientoFecha(String strSeguimientoFecha) {
        this.strSeguimientoFecha = strSeguimientoFecha;
    }

    public String getStrInstanciaCausa() {
        return strInstanciaCausa;
    }

    public void setStrInstanciaCausa(String strInstanciaCausa) {
        this.strInstanciaCausa = strInstanciaCausa;
    }

    public String getStrInstanciaPropuesta() {
        return strInstanciaPropuesta;
    }

    public void setStrInstanciaPropuesta(String strInstanciaPropuesta) {
        this.strInstanciaPropuesta = strInstanciaPropuesta;
    }

    public String getStrInstanciaResumen() {
        return strInstanciaResumen;
    }

    public void setStrInstanciaResumen(String strInstanciaResumen) {
        this.strInstanciaResumen = strInstanciaResumen;
    }

    public String getStrInstanciaFecha() {
        return strInstanciaFecha;
    }

    public void setStrInstanciaFecha(String strInstanciaFecha) {
        this.strInstanciaFecha = strInstanciaFecha;
    }

    public String getStrFechaEgreso() {
        return strFechaEgreso;
    }

    public void setStrFechaEgreso(String strFechaEgreso) {
        this.strFechaEgreso = strFechaEgreso;
    }

    public String getStrDestino() {
        return strDestino;
    }

    public void setStrDestino(String strDestino) {
        this.strDestino = strDestino;
    }

    public String getStrDiasEstancia() {
        return strDiasEstancia;
    }

    public void setStrDiasEstancia(String strDiasEstancia) {
        this.strDiasEstancia = strDiasEstancia;
    }

    public String getStrEstadoHospitalizacion() {
        return strEstadoHospitalizacion;
    }

    public void setStrEstadoHospitalizacion(String strEstadoHospitalizacion) {
        this.strEstadoHospitalizacion = strEstadoHospitalizacion;
    }

    public List<AucDiagnostico> getListaDiagnosticosIngreso() {
        return listaDiagnosticosIngreso;
    }

    public void setListaDiagnosticosIngreso(List<AucDiagnostico> listaDiagnosticosIngreso) {
        this.listaDiagnosticosIngreso = listaDiagnosticosIngreso;
    }

    public List<AucHospitalizacionEstancia> getListaEstancias() {
        return listaEstancias;
    }

    public void setListaEstancias(List<AucHospitalizacionEstancia> listaEstancias) {
        this.listaEstancias = listaEstancias;
    }

    public List<AucDiagnostico> getListaDiagnosticosEgreso() {
        return listaDiagnosticosEgreso;
    }

    public void setListaDiagnosticosEgreso(List<AucDiagnostico> listaDiagnosticosEgreso) {
        this.listaDiagnosticosEgreso = listaDiagnosticosEgreso;
    }

    @Override
    public String toString() {
        return "ReporteHospitalizacion{" + "strFechaDescarga=" + strFechaDescarga + ", strNombres=" + strNombres + ", strApellidos=" + strApellidos + ", strTipoDocumento=" + strTipoDocumento + ", strNumeroDocumento=" + strNumeroDocumento + ", strEdad=" + strEdad + ", strSede=" + strSede + ", strFechaIngreso=" + strFechaIngreso + ", strAuditor=" + strAuditor + ", strSeguimientoDescripcion=" + strSeguimientoDescripcion + ", strSeguimientoUsuario=" + strSeguimientoUsuario + ", strSeguimientoFecha=" + strSeguimientoFecha + ", strInstanciaCausa=" + strInstanciaCausa + ", strInstanciaPropuesta=" + strInstanciaPropuesta + ", strInstanciaResumen=" + strInstanciaResumen + ", strInstanciaFecha=" + strInstanciaFecha + ", strFechaEgreso=" + strFechaEgreso + ", strDestino=" + strDestino + ", strDiasEstancia=" + strDiasEstancia + ", strEstadoHospitalizacion=" + strEstadoHospitalizacion + ", listaDiagnosticosIngreso=" + listaDiagnosticosIngreso + ", listaEstancias=" + listaEstancias + ", listaDiagnosticosEgreso=" + listaDiagnosticosEgreso + '}';
    }
    
}
