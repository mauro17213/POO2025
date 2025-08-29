/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.especial.servicio;

import com.saviasaludeps.savia.dominio.especial.PeVariableValor;
import com.saviasaludeps.savia.web.especial.bean.GestionAfiliadosPEBean;

/**
 *
 * @author Jaime Andres Olarte
 */
public interface GestionAfiliadosPEServicioIface {
    
    
    /**
     * Método de acciones central
     * @param bean 
     */
    void Accion(GestionAfiliadosPEBean bean);
    
    /**
     * Método para cargar inicial del Bean
     * @param bean
     */
    void cargaInicial(GestionAfiliadosPEBean bean);
    
    /**
     * Metodo que permite validar el estado de unafiliado
     * @author idbohorquez
     * @param maeEstadoAfiliacion
     * @return
     */
    boolean validarEstadoAfiliado(int maeEstadoAfiliacion);
    
    /**
     * Metodo que permite consultar un afiliado en especifico desde aseguramiento
     * @param bean
     */
    void consultarAfiliadoContactos(GestionAfiliadosPEBean bean);    
    
    /**
     * Metodo encargado de consultar el listado de afilaidos que se pueden matricular
     * en un programa
     * @param bean
     */
    void consultarAfiliado(GestionAfiliadosPEBean bean);
    
    /**
     * Metodo encargado de consultar el listado de uaurios para elegir el reponsable
     * del programa y consultar el listado de ips para seleccionar la ips programa
     * @param bean
     */
    void consultarResponsablesPrestadorPrograma(GestionAfiliadosPEBean bean);
   
    /**
     * Metodo encargado de consultar el listado de variables especificas asociadas
     * a un programa
     * @param idPrograma 
     * @param bean
     */
    void consultarVariablesEspecificas(int idPrograma, GestionAfiliadosPEBean bean);
    
    /**
     * Metodo encargado de consultar el listado de historico de valores de variables
     * asociadas a un afiliado, programa y variable
     * @param variableValor 
     * @param bean
     */
    void consultarValoresVariablesHistorico(PeVariableValor variableValor, GestionAfiliadosPEBean bean);
    
}
