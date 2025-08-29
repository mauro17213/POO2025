package com.saviasaludeps.savia.negocio.cuentamedica.rips;

import com.saviasaludeps.savia.dominio.cuentamedica.rips.CmFeRipsValidacion;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import java.util.List;

public interface CmFeRipsValidacionRemoto {

    /**
     * Insertar Validacion.
     *
     * @param obj Validacion
     * @return valor incremental
     * @throws Exception
     */
    int insertar(CmFeRipsValidacion obj) throws Exception;

    /**
     * Actualizar Validacion solo cambia el estado
     *
     * @param obj CmRipsCarga
     * @throws Exception
     */
    void actualizar(CmFeRipsValidacion obj) throws Exception;

   
    /**
     * Eliminar una Validacion
     *
     * @param id de Validacion
     * @return 
     * @throws Exception
     */
    CmFeRipsValidacion eliminar(int id) throws Exception;

    /**
     * Consultar una Validacion
     *
     * @param id de Validacion
     * @return CmRipsCarga
     * @throws Exception
     */
    CmFeRipsValidacion consultar(int id) throws Exception;

    /**
     * Consultar cantidad de registros para la lista de Validacion
     *
     * @param paramConsulta de CmRipsCarga
     * @return Validacion
     * @throws Exception
     */
    int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception;

    /**
     * Consultar lista de cargas
     *
     * @param paramConsulta de Validacion
     * @return Validacion
     * @throws Exception
     */
    List<CmFeRipsValidacion> consultarLista(ParamConsulta paramConsulta) throws Exception;


}
