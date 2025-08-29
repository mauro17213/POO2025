/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.autorizacion;

import com.saviasaludeps.savia.dominio.autorizacion.AuSeguimiento;
import com.saviasaludeps.savia.dominio.autorizacion.AuSeguimientoPrestadorAsignado;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import java.util.List;

/**
 *
 * @author iavenegas
 */
public interface AuSeguimientoPrestadorAsignadoRemoto {

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
    List<AuSeguimientoPrestadorAsignado> consultarLista(ParamConsulta paramConsulta) throws Exception;

    /**
     * Consulta el objeto
     *
     * @param id
     * @return
     * @throws Exception
     */
    AuSeguimientoPrestadorAsignado consultar(int id) throws Exception;

    /**
     * Inserta el objeto
     *
     * @param obj
     * @return
     * @throws Exception
     */
    int insertar(AuSeguimientoPrestadorAsignado obj) throws Exception;

    /**
     * Actualiza el objeto
     *
     * @param obj
     * @throws Exception
     */
    void actualizar(AuSeguimientoPrestadorAsignado obj) throws Exception;
    
    /**
     * prestador por seguimiento y prestado
     * @param seguimiento
     * @param prestador
     * @return
     * @throws Exception 
     */
    AuSeguimientoPrestadorAsignado prestadorPorSeguimientoPorPrestador(int seguimiento, int prestador) throws Exception ;

}
