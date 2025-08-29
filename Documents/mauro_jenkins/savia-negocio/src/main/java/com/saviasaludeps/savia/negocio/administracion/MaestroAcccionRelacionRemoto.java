/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.administracion;

import com.saviasaludeps.savia.dominio.administracion.MaestroAccionRelacion;
import java.util.List;

/**
 *
 * @author sgiraldov
 */
public interface MaestroAcccionRelacionRemoto {
    
    /**
     * Inserta la relacion entre maestro y maestro accion
     * @param obj
     * @return
     * @throws Exception 
     */
    int insertar(MaestroAccionRelacion obj) throws Exception;
    
    /**
     * Lista las acciones que tiene un maestro
     * @param id
     * @return
     * @throws Exception 
     */
    List<MaestroAccionRelacion> consultarPorMaestroId(int id) throws Exception;
    
    /**
     * Elimina la relacion
     * @param id
     * @return
     * @throws Exception 
     */
    MaestroAccionRelacion eliminar(int id) throws Exception;
    
}
