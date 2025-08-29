///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.saviasaludeps.savia.web.autorizacion.utilidades;
//
//import com.saviasaludeps.savia.dominio.administracion.Maestro;
//import com.saviasaludeps.savia.dominio.autorizacion.AuAnexo3;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//
///**
// *
// * @author Stiven Giraldo
// */
//public class AuAnexo3Validacion {
//    
//    //Identificadores tipo de validacion
//    public final static int TIPO_VALIDACION_CARGA_MASIVA = 1;
//    
//    //Variables
//    private List<String> errores = new ArrayList();
//    
//    //Listas necesarias
//    private HashMap<String,Maestro> hashTiposDocumentos;
//    private HashMap<String,Maestro> hashAmbitos;
//    private HashMap<String,Maestro> hashEspecialidades;
//    private HashMap<String,Maestro> hashOrgienesAtencion;
//    private HashMap<String,Maestro> hashServiciosSolicitados;
//    private HashMap<String,Maestro> hashUbicacionesPacientes;
//    private HashMap<String,Maestro> hashTiposDiagnosticos;
//    private HashMap<String,Maestro> hashTiposTecnologias;
//    private HashMap<String,Maestro> hashTiposServiciosTecnologias;
//    private HashMap<String,Maestro> hashTiposFrecuenciasTecnologias;
//    private HashMap<String,Maestro> hashViasTecnologias;
//
//    public List<String> getErrores() {
//        return errores;
//    }
//
//    public void setErrores(List<String> errores) {
//        this.errores = errores;
//    }
//
//    public HashMap<String, Maestro> getHashTiposDocumentos() {
//        return hashTiposDocumentos;
//    }
//
//    public void setHashTiposDocumentos(HashMap<String, Maestro> hashTiposDocumentos) {
//        this.hashTiposDocumentos = hashTiposDocumentos;
//    }
//
//    public HashMap<String, Maestro> getHashAmbitos() {
//        return hashAmbitos;
//    }
//
//    public void setHashAmbitos(HashMap<String, Maestro> hashAmbitos) {
//        this.hashAmbitos = hashAmbitos;
//    }
//
//    public HashMap<String, Maestro> getHashEspecialidades() {
//        return hashEspecialidades;
//    }
//
//    public void setHashEspecialidades(HashMap<String, Maestro> hashEspecialidades) {
//        this.hashEspecialidades = hashEspecialidades;
//    }
//
//    public HashMap<String, Maestro> getHashOrgienesAtencion() {
//        return hashOrgienesAtencion;
//    }
//
//    public void setHashOrgienesAtencion(HashMap<String, Maestro> hashOrgienesAtencion) {
//        this.hashOrgienesAtencion = hashOrgienesAtencion;
//    }
//
//    public HashMap<String, Maestro> getHashServiciosSolicitados() {
//        return hashServiciosSolicitados;
//    }
//
//    public void setHashServiciosSolicitados(HashMap<String, Maestro> hashServiciosSolicitados) {
//        this.hashServiciosSolicitados = hashServiciosSolicitados;
//    }
//
//    public HashMap<String, Maestro> getHashUbicacionesPacientes() {
//        return hashUbicacionesPacientes;
//    }
//
//    public void setHashUbicacionesPacientes(HashMap<String, Maestro> hashUbicacionesPacientes) {
//        this.hashUbicacionesPacientes = hashUbicacionesPacientes;
//    }
//
//    public HashMap<String, Maestro> getHashTiposDiagnosticos() {
//        return hashTiposDiagnosticos;
//    }
//
//    public void setHashTiposDiagnosticos(HashMap<String, Maestro> hashTiposDiagnosticos) {
//        this.hashTiposDiagnosticos = hashTiposDiagnosticos;
//    }
//
//    public HashMap<String, Maestro> getHashTiposTecnologias() {
//        return hashTiposTecnologias;
//    }
//
//    public void setHashTiposTecnologias(HashMap<String, Maestro> hashTiposTecnologias) {
//        this.hashTiposTecnologias = hashTiposTecnologias;
//    }
//
//    public HashMap<String, Maestro> getHashTiposServiciosTecnologias() {
//        return hashTiposServiciosTecnologias;
//    }
//
//    public void setHashTiposServiciosTecnologias(HashMap<String, Maestro> hashTiposServiciosTecnologias) {
//        this.hashTiposServiciosTecnologias = hashTiposServiciosTecnologias;
//    }
//
//    public HashMap<String, Maestro> getHashTiposFrecuenciasTecnologias() {
//        return hashTiposFrecuenciasTecnologias;
//    }
//
//    public void setHashTiposFrecuenciasTecnologias(HashMap<String, Maestro> hashTiposFrecuenciasTecnologias) {
//        this.hashTiposFrecuenciasTecnologias = hashTiposFrecuenciasTecnologias;
//    }
//
//    public HashMap<String, Maestro> getHashViasTecnologias() {
//        return hashViasTecnologias;
//    }
//
//    public void setHashViasTecnologias(HashMap<String, Maestro> hashViasTecnologias) {
//        this.hashViasTecnologias = hashViasTecnologias;
//    }
//    
//    public boolean isError(){
//        return (!this.errores.isEmpty());
//    }
//    
//    public String getErroresStr() {
//        String strError = "";
//        strError = errores.stream().map(str -> str + AuConstantes.PUNTO + AuConstantes.ESPACIO).reduce(strError, String::concat);
//        return strError;
//    }
//    
//    public void validarCreacion(AuAnexo3 objeto, int tipoValidacion){
//        
//    }
//    
//    public boolean validarAmbito(String id){
//        return false;
//    }   
//
//    boolean validarServicioAtencion(int codigoServicioAtencion) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//
//    boolean validarCodigoHabilitacion(int codigoHabilitacion) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//
//    boolean validarOrigenAtencion(int origenAtencion) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//
//    boolean validarPrioridad(int prioridad) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//
//    boolean validarServicioSolicitado(int servicioSolicitado) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//
//    boolean validarUbicacionPaciente(int ubicacionPaciente) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//
//    Maestro obtenerMaestroAmbito(String ambito) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//
//    Maestro obtenerMaestroServicioAtencion(int codigoServicioAtencion) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//}
