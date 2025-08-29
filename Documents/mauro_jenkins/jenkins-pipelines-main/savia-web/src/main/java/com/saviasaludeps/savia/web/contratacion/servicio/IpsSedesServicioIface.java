/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.contratacion.servicio;
import com.saviasaludeps.savia.web.contratacion.bean.IpsSedesBean;

/**
 *
 * @author jyperez
 */
public interface IpsSedesServicioIface {
    void Accion(IpsSedesBean bean);
    
    /**
     * Cargar lista de Prestadores - Sedes
     * @param bean 
     */
    void cargaInicial(IpsSedesBean bean);
    
    //List<Persona> consultarPersonasPorNombre(int empresaId, Integer areaId, String nombre);
}
