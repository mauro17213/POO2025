/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.administracion;

import com.saviasaludeps.savia.dominio.administracion.GnSedeHorario;
import java.util.List;

/**
 *
 * @author sgiraldov
 */
public interface GnSedeHorarioRemoto {
    
    /**
     * Inserta el objeto
     * @param obj
     * @return
     * @throws Exception 
     */
    int insertar(GnSedeHorario obj) throws Exception;
    
    /**
     * Actualiza el objeto
     * @param obj
     * @throws Exception 
     */
    void actualizar(GnSedeHorario obj) throws Exception;
    
    /**
     * Eliminar el objeto
     * @param id
     * @return
     * @throws Exception 
     */
    GnSedeHorario eliminar(int id) throws Exception;
    
    /**
     * Lista los horarios de una sede
     * @param idSede
     * @return
     * @throws Exception 
     */
    List<GnSedeHorario> listarPorIdSede(int idSede) throws Exception;
    
    /**
     * Valida si la sede esta en horario
     * @param idSede
     * @return
     * @throws Exception
     */
    boolean estaEnHorario(int idSede) throws Exception;
    
    /**
     * Valida que el horario de la sede este en el dia adecuado
     * @param idSede
     * @return
     * @throws Exception 
     */
    boolean estaEnHorarioDia(int idSede) throws Exception;
    
}
