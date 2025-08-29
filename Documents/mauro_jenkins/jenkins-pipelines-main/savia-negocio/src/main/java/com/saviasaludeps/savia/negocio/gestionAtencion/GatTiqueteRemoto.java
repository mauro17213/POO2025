/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.gestionAtencion;

import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.gestionAtencion.GatTiquete;
import java.util.Date;
import java.util.List;

/**
 *
 * @author acuartas
 */
public interface GatTiqueteRemoto {
     /**
     * Consulta la cantidad de datos de parametros
     *
     * @param paramConsulta
     * @return
     * @throws Exception
     */
    int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception;

    /**
     * Consulta la lista dado los parametros
     *
     * @param paramConsulta
     * @return
     * @throws Exception
     */
    List<GatTiquete> consultarLista(ParamConsulta paramConsulta) throws Exception;

    /**
     * Consulta el objeto dado el id
     *
     * @param id
     * @return
     * @throws Exception
     */
    GatTiquete consultar(int id) throws Exception;

    /**
     * Inserta el objeto
     *
     * @param obj
     * @return
     * @throws Exception
     */
    int insertar(GatTiquete obj) throws Exception;

    /**
     * Actualiza el objeto
     *
     * @param obj
     * @throws Exception
     */
    void actualizar(GatTiquete obj) throws Exception;

    /**
     * Elimina el objeto
     *
     * @param id
     * @return
     * @throws Exception
     */
    GatTiquete eliminar(int id) throws Exception;
    
    /**
     * Consulta el siguiente tiquete
     * @param listaServicios
     * @param idSede
     * @return
     * @throws Exception 
     */
    GatTiquete consultarSiguiente(String listaServicios, int idSede) throws Exception;
    
    /**
     * Consulta la cantidad de tiquetes creados del dia
     * @param idSede
     * @return
     * @throws Exception 
     */
    int consultarTotalHoy(int idSede) throws Exception;
    
    /**
     * Consulta la cantidad de tiquetes creados del dia
     * @param idSede
     * @param idServicio
     * @return
     * @throws Exception 
     */
    int consultarTotalHoy(int idSede, int idServicio) throws Exception;
    
    /**
     * Consulta total tiquetes hoy por estado y sede
     * @param idSede
     * @param estado
     * @return
     * @throws Exception 
     */
    int consultarTotalHoyPorEstadoYSede(int idSede, int estado) throws Exception;
    
    /**
     * Consulta el total hoy prioritario
     * @param sede
     * @return
     * @throws Exception 
     */
    int consultarTotalHoyPrioritarios(int sede) throws Exception;
    
    /**
     * Consulta la cantidd por fecha
     * @param fecha
     * @return
     * @throws Exception 
     */
    int consultarTotalPorFecha(Date fecha) throws Exception;
    
    List<GatTiquete> consultarPorTipoYNumeroDocumento(int idTipoDocumento, String numeroDocumento) throws Exception;
    
    /**
     * Consulta la cantidad de ticket pendientes en una fecha especifica
     * @param fecha
     * @param idSede
     * @return
     * @throws Exception
     */
    int consultarTotalPendiente(Date fecha, Integer idSede) throws Exception;
    
    
    /**
     * Busca el total de tiquetes de la sede por fechas
     * @param idSede
     * @param fechaInicio
     * @param fechaFin
     * @return
     * @throws Exception 
     */
    List<GatTiquete> consultarPorFecha(int idSede, Date fechaInicio, Date fechaFin) throws Exception;
}
