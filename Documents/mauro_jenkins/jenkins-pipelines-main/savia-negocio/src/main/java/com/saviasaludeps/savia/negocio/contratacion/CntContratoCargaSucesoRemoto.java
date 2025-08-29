package com.saviasaludeps.savia.negocio.contratacion;

import com.saviasaludeps.savia.dominio.contratacion.CntContratoCargaSuceso;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import java.util.List;

public interface CntContratoCargaSucesoRemoto {

    List<CntContratoCargaSuceso> consultarLista(ParamConsulta paramConsulta) throws Exception;
    
    int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception;
    
    /**
     * Método para consultar un registro por ID
     *
     * @param id
     * @return (CntContratoCargaSuceso) cargado
     * @throws java.lang.Exception
     */
    CntContratoCargaSuceso consultar(int id) throws Exception;

    /**
     * Método para crear una nuevo registro
     *
     * @param obj (CntContratoCargaSuceso)
     * @return (int) id del registro generado
     * @throws java.lang.Exception
     */
    int insertar(CntContratoCargaSuceso obj) throws Exception;

    /**
     * Método para actualizar la información de un registro
     *
     * @param obj (CntContratoCargaSuceso)
     * @throws java.lang.Exception
     */
    void actualizar(CntContratoCargaSuceso obj) throws Exception;

    /**
     * Método para eliminar un registro
     *
     * @param id
     * @return (CntContratoCargaSuceso) La persistencia del objeto eliminado
     * @throws java.lang.Exception
     */
    CntContratoCargaSuceso eliminar(int id) throws Exception;
    
    /**
     * Consultar todos los registros
     *
     * @return
     * @throws Exception
     */
    List<CntContratoCargaSuceso> consultarTodos() throws Exception;
    
    /**
     * Consultar la cantidad de contratos existente por el número de Contrato
     * @param idContratoCarga
     * @return
     * @throws Exception 
     */
    List<CntContratoCargaSuceso> consultarPorContratoCarga(int idContratoCarga) throws Exception;
}
