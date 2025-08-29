package com.saviasaludeps.savia.negocio.contratacion;

import com.saviasaludeps.savia.dominio.contratacion.CntContratoAdjunto;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import java.util.List;

public interface CntContratoAdjuntoRemoto {

    List<CntContratoAdjunto> consultarLista(ParamConsulta paramConsulta) throws Exception;
    
    int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception;
    
    /**
     * Método para consultar un registro por ID
     *
     * @param id
     * @return (CntContratoAdjunto) cargado
     * @throws java.lang.Exception
     */
    CntContratoAdjunto consultar(int id) throws Exception;

    /**
     * Método para crear una nuevo registro
     *
     * @param obj (CntContratoAdjunto)
     * @return (int) id del registro generado
     * @throws java.lang.Exception
     */
    int insertar(CntContratoAdjunto obj) throws Exception;

    /**
     * Método para actualizar la información de un registro
     *
     * @param obj (CntContratoAdjunto)
     * @throws java.lang.Exception
     */
    void actualizar(CntContratoAdjunto obj) throws Exception;

    /**
     * Método para eliminar un registro
     *
     * @param id
     * @return (CntContratoAdjunto) La persistencia del objeto eliminado
     * @throws java.lang.Exception
     */
    CntContratoAdjunto eliminar(int id) throws Exception;
    
    /**
     * Consultar todos los registros
     *
     * @return
     * @throws Exception
     */
    List<CntContratoAdjunto> consultarTodos() throws Exception;
}
