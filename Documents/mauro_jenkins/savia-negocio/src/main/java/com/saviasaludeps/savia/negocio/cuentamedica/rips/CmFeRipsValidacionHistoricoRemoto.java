package com.saviasaludeps.savia.negocio.cuentamedica.rips;

import com.saviasaludeps.savia.dominio.cuentamedica.rips.CmFeRipsValidacionHistorico;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import java.util.List;

public interface CmFeRipsValidacionHistoricoRemoto {

    /**
     * Insertar Historico.
     *
     * @param obj CmRipsCarga
     * @return valor incremental
     * @throws Exception
     */
    int insertar(CmFeRipsValidacionHistorico obj) throws Exception;

    /**
     * Actualizar Historico solo cambia el estado
     *
     * @param obj CmRipsCarga
     * @throws Exception
     */
    void actualizar(CmFeRipsValidacionHistorico obj) throws Exception;

   
    /**
     * Eliminar un Historico
     *
     * @param id de CmRipsCarga
     * @return 
     * @throws Exception
     */
    CmFeRipsValidacionHistorico eliminar(int id) throws Exception;

    /**
     * Consultar un Historico
     *
     * @param id de CmRipsCarga
     * @return CmRipsCarga
     * @throws Exception
     */
    CmFeRipsValidacionHistorico consultar(int id) throws Exception;

    /**
     * Consultar cantidad de registros para la lista de Historicos
     *
     * @param paramConsulta de CmRipsCarga
     * @return CmRipsCarga
     * @throws Exception
     */
    int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception;

    /**
     * Consultar lista de Historicos
     *
     * @param paramConsulta de CmRipsCarga
     * @return CmRipsCarga
     * @throws Exception
     */
    List<CmFeRipsValidacionHistorico> consultarLista(ParamConsulta paramConsulta) throws Exception;

  
}
