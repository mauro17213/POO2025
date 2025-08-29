/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.contratacion;

import com.saviasaludeps.savia.dominio.contratacion.CntPrestadorSede;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import java.util.List;

public interface CntPrestadorSedeRemoto {

   /**
     * Consulta de cantidad de registros en una lista
     *
     * @param paramConsulta
     * @return
     * @throws Exception
     */
    int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception;

    /**
     * Consultar lista de registros
     *
     * @param paramConsulta
     * @return
     * @throws Exception
     */
    List<CntPrestadorSede> consultarLista(ParamConsulta paramConsulta) throws Exception;

    /**
     * Método para consultar un registro por ID
     *
     * @param id
     * @return (ReConciliacionRecobros) cargado
     * @throws java.lang.Exception
     */
    CntPrestadorSede consultar(int id) throws Exception;

    /**
     * Método para crear una nuevo registro
     *
     * @param obj (ReConciliacionRecobros)
     * @return (int) id del registro generado
     * @throws java.lang.Exception
     */
    int insertar(CntPrestadorSede obj) throws Exception;

    /**
     * Método para actualizar la información de un registro
     *
     * @param obj (AfAfiliados)
     * @throws java.lang.Exception
     */
    void actualizar(CntPrestadorSede obj) throws Exception;

    /**
     * Método para eliminar un registro
     *
     * @param id
     * @return (AfAfiliados) La persistencia del objeto eliminado
     * @throws java.lang.Exception
     */
    CntPrestadorSede eliminar(int id) throws Exception;

    /**
     * Consultar todos los registros
     *
     * @return
     * @throws Exception
     */
    List<CntPrestadorSede> consultarTodos() throws Exception;
    
    /**
     * Consultar todos por nombre
     *
     * @param nombre nombre de la ips
     * @return (CntPrestadorSedes)
     * @throws Exception
     */
    List<CntPrestadorSede> consultarPorNombre(String nombre) throws Exception;
    
    /**
     * Consulta las sedes de un prestador.
     * @param cntPrestadorId
     * @return
     * @throws java.lang.Exception 
     */
    List<CntPrestadorSede> consultarPorPrestador(int cntPrestadorId) throws java.lang.Exception;
    
    /**
     * Consultar una sede por codigo habilitacion
     * @param codigoHabilitacion
     * @return 
     */
    CntPrestadorSede consultarPorCodigoHabilitacion(String codigoHabilitacion);
    
    /**
     * Método para consultar un registro de CntPrestadorSede por codigo
     * habilitacion Sede
     *
     * @param codigo
     * @return (ReConciliacionRecobros) cargado
     * @throws java.lang.Exception
     */
    CntPrestadorSede consultarSedePorCodigoHabilitacionYEstado(String codigo) throws Exception;
    
    /**
     * Consulta de cantidad de registros en una lista
     *
     * @param paramConsulta
     * @return
     * @throws Exception
     */
    int consultarCantidadListaGestionar(ParamConsulta paramConsulta) throws Exception;

    /**
     * Consultar lista de registros
     *
     * @param paramConsulta
     * @return
     * @throws Exception
     */
    List<CntPrestadorSede> consultarListaGestionar(ParamConsulta paramConsulta) throws Exception;
    
    
    /**
     * Consulta la cantidad de registros con contrato valido
     * @param paramConsulta
     * @return
     * @throws Exception 
     */
    int consultarCantidadListaConContrato(ParamConsulta paramConsulta) throws Exception;
    
    /**
     * Consulta  las sedes que tengan un contrato existente
     * @param paramConsulta
     * @return
     * @throws Exception 
     */
    List<CntPrestadorSede> consultarListaConContrato(ParamConsulta paramConsulta) throws Exception;

}
