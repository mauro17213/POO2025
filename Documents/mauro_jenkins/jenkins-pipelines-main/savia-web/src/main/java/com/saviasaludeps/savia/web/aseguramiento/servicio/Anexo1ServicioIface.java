/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.aseguramiento.servicio;

import com.saviasaludeps.savia.web.aseguramiento.bean.Anexo1Bean;

/**
 *
 * @author José Pérez
 */
public interface Anexo1ServicioIface {
    
    /**
     * Método de acciones central
     * @param bean 
     */
    void Accion(Anexo1Bean bean);
    
    /**
     * Método para carga inicial de variables
     * @param bean 
     */
    void cargaInicial(Anexo1Bean bean);
    
    /**
     * Método para obtener las sedes pertenecientes a un prestador
     * @param bean 
     */
    public void consultarSedesPrestador(Anexo1Bean bean);
    
}
