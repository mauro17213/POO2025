/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.gestionAtencion;

import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.gestionAtencion.GatAtencion;
import java.util.Date;
import java.util.List;

/**
 *
 * @author acuartas
 */
public interface GatAtencionRemoto {
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
    List<GatAtencion> consultarLista(ParamConsulta paramConsulta) throws Exception;

    /**
     * Consulta el objeto dado el id
     *
     * @param id
     * @return
     * @throws Exception
     */
    GatAtencion consultar(int id) throws Exception;

    /**
     * Inserta el objeto
     *
     * @param obj
     * @return
     * @throws Exception
     */
    int insertar(GatAtencion obj) throws Exception;

    /**
     * Actualiza el objeto
     *
     * @param obj
     * @throws Exception
     */
    void actualizar(GatAtencion obj) throws Exception;

    /**
     * Elimina el objeto
     *
     * @param id
     * @return
     * @throws Exception
     */
    GatAtencion eliminar(int id) throws Exception;
    
    /**
     * Consulta la atencion por tiquete y taquilla
     * @param idTiquete
     * @param idTaquilla
     * @return
     * @throws Exception 
     */
    GatAtencion consultarPorTiqueteYTaquilla(int idTiquete, int idTaquilla) throws Exception;
    
    /**
     * Consulta la lista de atenciones activas de un funcionario en una taquilla
     * @param idFuncionario
     * @param idTaquilla
     * @return
     * @throws Exception 
     */
    List<GatAtencion> listarActivasPorFuncionarioYTaquilla(int idFuncionario, int idTaquilla) throws Exception;
    
    /**
     * Consulta la cantidad de atenciones segun el estado y fecha especifica
     * @param estado
     * @param fecha
     * @param idUsuario
     * @return
     * @throws Exception
     */
    Integer consultarTipoFecha(int estado, String fecha, int idUsuario) throws Exception;
    
    /**
     * Consulta la cantidad de atenciones sobre atendidas en una fecha especifica
     * @param idUsuario
     * @param fecha
     * @return
     * @throws Exception
     */
    Integer consultarSobreAtendidos(String fecha, int idUsuario) throws Exception;
    
    /**
     * Consulta la cantidad de atencion del dia
     * @param idSede
     * @return
     * @throws Exception 
     */
    int consultarTotalAtentidosHoy(int idSede) throws Exception;
    
    /**
     * Consulta la cantidad de abandonados del dia
     * @param idSede
     * @return
     * @throws Exception 
     */
    int consultarTotalAbandonadosHoy(int idSede) throws Exception;
    
    /**
     * Consulta la cantidad de sobreatendidos del dia
     * @param idSede
     * @return
     * @throws Exception 
     */
    int consultarTotalSobreAtendidosHoy(int idSede) throws Exception;
    
    /**
     * Consultar todas las atendidas por taquilla
     * @param idTaquilla
     * @return
     * @throws Exception 
     */
    int consultarTotalAtendidoTaquilla(int idTaquilla) throws Exception;
    
    /**
     * Consultar total usuarios atendidos hoy por taquilla
     * @param idTaquilla
     * @return
     * @throws Exception 
     */
    int consultaTotalUsuariosTaquilla(int idTaquilla) throws Exception;
    
    /**
     * Consultar total abandonos hoy por taquilla
     * @param idTaquilla
     * @return
     * @throws Exception 
     */
    int consultarTotalAbandonosTaquilla(int idTaquilla) throws Exception;
    
    /**
     * Consultar el promedio en minutos de atencion hoy
     * @param idSede
     * @param idTaquilla
     * @return
     * @throws Exception 
     */
    int calcularPromedioHoy(Integer idSede, Integer idTaquilla) throws Exception;
    
    /**
     * Consulta la cantidad de atenciones por estado y sede de hoy
     * @param estado
     * @param idSede
     * @return
     * @throws Exception 
     */
    int consultarTotalAtencionesPorEstadoYSedeHoy(int estado, int idSede) throws Exception;
    
    /**
     * Consulta la cantidad sobreatendidos dada una fecha
     * @param idSede
     * @param fecha
     * @return
     * @throws Exception 
     */
    int consultarTotalSobreatendidoPorFecha(int idSede, Date fecha) throws Exception;
    
    /**
     * Consulta el promedio de atencion por fecha
     * @param idSede
     * @param fecha
     * @return
     * @throws Exception 
     */
    int calcularPromedioPorFecha(Integer idSede, Date fecha) throws Exception;
    
   
}
