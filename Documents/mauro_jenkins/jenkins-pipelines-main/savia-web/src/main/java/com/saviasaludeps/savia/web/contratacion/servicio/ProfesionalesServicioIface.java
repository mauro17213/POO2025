/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.contratacion.servicio;

import com.saviasaludeps.savia.web.contratacion.bean.ProfesionalesBean;

/**
 *
 * @author Jaime Andres Olarte
 */
public interface ProfesionalesServicioIface {
    /**
     * Método de acciones central
     * @param bean 
     */
    void Accion(ProfesionalesBean bean);
    
    /**
     * Método para carga inicial de variables
     * @param bean 
     */
    void cargaInicial(ProfesionalesBean bean);
    
    /**
     * Método para consultar el profesional
     * @param bean 
     */
    void consultarProfesional(ProfesionalesBean bean);
    
    /**
     * Metodo que consulta la ubicacion del prestador y el cnt_profesional_prestadores
     * @param bean 
     */
    void cerrarDialogoSelPrestador(ProfesionalesBean bean);
    
    void agregarProfesionalPrestador(ProfesionalesBean bean);
}
