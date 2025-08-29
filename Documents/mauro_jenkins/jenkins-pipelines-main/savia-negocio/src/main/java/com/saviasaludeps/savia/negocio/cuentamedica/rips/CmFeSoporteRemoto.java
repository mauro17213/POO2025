package com.saviasaludeps.savia.negocio.cuentamedica.rips;

import com.saviasaludeps.savia.dominio.cuentamedica.rips.CmFeSoporte;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import java.util.List;

public interface CmFeSoporteRemoto {

    /**
     * Insertar Soporte.
     *
     * @param obj CmFeSoporte
     * @return valor incremental
     * @throws Exception
     */
    int insertar(CmFeSoporte obj) throws Exception;

    /**
     * Actualizar carga solo cambia el estado
     *
     * @param obj CmFeSoporte
     * @throws Exception
     */
    void actualizar(CmFeSoporte obj) throws Exception;
    /**
     * 
     * @param obj
     * @throws Exception 
     */
    void actualizarFacturaId(CmFeSoporte obj) throws Exception;

    /**
     * Permite consultar cantidad soportes
     * @param paramConsulta
     * @return
     * @throws Exception 
     */
    int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception;

    /**
     * Permite consultar lista soportes
     * @param paramConsulta
     * @return
     * @throws Exception 
     */
    List<CmFeSoporte> consultarLista(ParamConsulta paramConsulta) throws Exception;

    /**
     * Permite consultar soporte
     * @param id
     * @return
     * @throws Exception 
     */
    CmFeSoporte consultar(int id) throws Exception;
   

}
