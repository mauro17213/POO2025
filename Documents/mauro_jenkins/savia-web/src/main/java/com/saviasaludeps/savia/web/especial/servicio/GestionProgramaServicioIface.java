/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.especial.servicio;

import com.saviasaludeps.savia.dominio.especial.PeProgramaDiagnostico;
import com.saviasaludeps.savia.web.especial.bean.GestionProgramaBean;

/**
 *
 * @author Jaime Andres Olarte
 */
public interface GestionProgramaServicioIface {
    
    
    /**
     * Método de acciones central
     * @param bean 
     */
    void Accion(GestionProgramaBean bean);
    
    /**
     * Métodopara cargas inicial de variables
     * @param bean 
     */
    void cargaInicial(GestionProgramaBean bean);
    
    boolean existeDiagnosticoPrograma(PeProgramaDiagnostico diagnostico);
}
