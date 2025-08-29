/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.administracion;

import com.saviasaludeps.savia.dominio.administracion.DiaHabil;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import java.util.Date;
import java.util.List;

/**
 *
 * @author rpalacic
 */
public interface CalendarioRemoto {
    
    /**
     * Método para consultar todos los registros
     * @param paramConsulta
     * @return 
     * @throws java.lang.Exception 
     */
    public List<DiaHabil> consultarTodos(ParamConsulta paramConsulta) throws Exception;
    
    /**
     * Consulta un día en particular
     * @param fecha
     * @return
     * @throws Exception 
     */
    public DiaHabil consultarDia(Date fecha) throws Exception;
    
    /**
     * Consultar numero de días habiles en un rango de fechas
     * @param fechaDesde (Date)
     * @param fechaHasta (Date)
     * @return Número de días habiles
     * @throws Exception 
     */
    int consultarHabilies(Date fechaDesde, Date fechaHasta) throws Exception;
    
    /**
     * Valida si el día ingresado e hábil o no
     * @param fecha (Date) La dfecha a ser validada
     * @return (bollean) si es día habil o no
     * @throws Exception 
     */
    boolean esDiaHabil(Date fecha) throws Exception;
    
    /**
     * Método para crear una nuevo registro
     * @param obj (DiasHabiles)
     * @return (int) id del registro generado
     * @throws java.lang.Exception
     */
    public boolean actualizar(DiaHabil obj) throws Exception;
}
