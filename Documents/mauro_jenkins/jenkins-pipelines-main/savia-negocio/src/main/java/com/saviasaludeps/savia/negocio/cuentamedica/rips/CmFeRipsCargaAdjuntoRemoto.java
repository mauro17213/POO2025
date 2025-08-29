package com.saviasaludeps.savia.negocio.cuentamedica.rips;

import com.saviasaludeps.savia.dominio.cuentamedica.rips.CmFeRipsCargaAdjunto;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import java.util.List;

public interface CmFeRipsCargaAdjuntoRemoto {

    /**
     * Insertar Carga.
     *
     * @param obj CmRipsCarga
     * @return valor incremental
     * @throws Exception
     */
    int insertar(CmFeRipsCargaAdjunto obj) throws Exception;

    /**
     * Actualizar carga solo cambia el estado
     *
     * @param obj CmRipsCarga
     * @throws Exception
     */
    void actualizar(CmFeRipsCargaAdjunto obj) throws Exception;

   
    /**
     * Eliminar una carga
     *
     * @param id de CmRipsCarga
     * @return 
     * @throws Exception
     */
    CmFeRipsCargaAdjunto eliminar(int id) throws Exception;

    /**
     * Consultar una carga
     *
     * @param id de CmRipsCarga
     * @return CmRipsCarga
     * @throws Exception
     */
    CmFeRipsCargaAdjunto consultar(int id) throws Exception;

    /**
     * Consultar cantidad de registros para la lista de Cargas
     *
     * @param paramConsulta de CmRipsCarga
     * @return CmRipsCarga
     * @throws Exception
     */
    int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception;

    /**
     * Consultar lista de cargas
     *
     * @param paramConsulta de CmRipsCarga
     * @return CmRipsCarga
     * @throws Exception
     */
    List<CmFeRipsCargaAdjunto> consultarLista(ParamConsulta paramConsulta) throws Exception;

    /**
     * consultar todos
     * @param id
     * @return
     * @throws Exception 
     */
    List<CmFeRipsCargaAdjunto> consultarTodos(int id) throws Exception;

}
