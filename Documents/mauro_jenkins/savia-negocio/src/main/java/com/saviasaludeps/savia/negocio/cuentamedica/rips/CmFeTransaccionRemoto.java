package com.saviasaludeps.savia.negocio.cuentamedica.rips;

import com.saviasaludeps.savia.dominio.cuentamedica.rips.CmFeTransaccion;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import java.util.List;

public interface CmFeTransaccionRemoto {

    /**
     * Insertar Carga.
     *
     * @param obj CmRipsCarga
     * @return valor incremental
     * @throws Exception
     */
    int insertar(CmFeTransaccion obj) throws Exception;

    /**
     * Actualizar carga solo cambia el estado
     *
     * @param obj CmRipsCarga
     * @throws Exception
     */
    void actualizar(CmFeTransaccion obj) throws Exception;

   
    /**
     * Eliminar una carga
     *
     * @param id de CmRipsCarga
     * @return 
     * @throws Exception
     */
    CmFeTransaccion eliminar(int id) throws Exception;

    /**
     * Consultar una carga
     *
     * @param id de CmRipsCarga
     * @return CmRipsCarga
     * @throws Exception
     */
    CmFeTransaccion consultar(int id) throws Exception;

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
    List<CmFeTransaccion> consultarLista(ParamConsulta paramConsulta) throws Exception;


}
