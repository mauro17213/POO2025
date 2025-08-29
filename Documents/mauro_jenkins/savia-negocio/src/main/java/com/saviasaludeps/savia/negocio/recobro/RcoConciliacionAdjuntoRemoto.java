package com.saviasaludeps.savia.negocio.recobro;

import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.recobro.RcoConciliacionAdjunto;
import java.util.List;

/**
 *
 * @author Stiven Giraldo
 */
public interface RcoConciliacionAdjuntoRemoto {
    
    /**
     *
     * @param paramConsulta
     * @return
     * @throws Exception
     */
    int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception;

    /**
     *
     * @param paramConsulta
     * @return
     * @throws Exception
     */
    List<RcoConciliacionAdjunto> consultarLista(ParamConsulta paramConsulta) throws Exception;
    
    /**
     * 
     * @param conciliacionId
     * @return
     * @throws Exception 
     */
    List<RcoConciliacionAdjunto> consultarListaPorConciliacion(int conciliacionId) throws Exception;
    
    /**
     * Método para consultar un maestro por ID
     *
     * @param id
     * @return
     * @throws java.lang.Exception
     */
    RcoConciliacionAdjunto consultar(int id) throws Exception;

    /**
     * Método para crear un nuev maestro
     *
     * @param obj
     * @return (int) id del registro generado
     * @throws java.lang.Exception
     */
    int insertar(RcoConciliacionAdjunto obj) throws Exception;

    /**
     * Método para actualizar la información de un maestro
     *
     * @param obj
     * @throws java.lang.Exception
     */
    void actualizar(RcoConciliacionAdjunto obj) throws Exception;

    /**
     * Eliminar un maestro
     *
     * @param id
     * @return objeto eliminado
     * @throws java.lang.Exception
     */
    RcoConciliacionAdjunto eliminar(int id) throws Exception;
    
}
