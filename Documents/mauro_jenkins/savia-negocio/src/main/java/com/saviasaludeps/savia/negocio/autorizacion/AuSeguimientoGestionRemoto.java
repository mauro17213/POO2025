/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.autorizacion;

import com.saviasaludeps.savia.dominio.autorizacion.AuSeguimiento;
import com.saviasaludeps.savia.dominio.autorizacion.AuSeguimientoGestion;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import java.util.List;

/**
 *
 * @author iavenegas
 */
public interface AuSeguimientoGestionRemoto {

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
    List<AuSeguimientoGestion> consultarLista(ParamConsulta paramConsulta) throws Exception;

    /**
     *
     * @param id
     * @return
     * @throws Exception
     */
    AuSeguimientoGestion consultar(int id) throws Exception;

    /**
     *
     * @param obj
     * @return
     * @throws Exception
     */
    int insertar(AuSeguimientoGestion obj) throws Exception;
    
    /**
     * Actualiza el objeto
     *
     * @param obj
     * @throws Exception
     */
    void actualizar(AuSeguimientoGestion obj) throws Exception;

}
