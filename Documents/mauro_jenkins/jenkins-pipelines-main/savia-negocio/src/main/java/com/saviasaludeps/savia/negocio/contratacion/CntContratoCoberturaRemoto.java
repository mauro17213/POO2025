package com.saviasaludeps.savia.negocio.contratacion;

import com.saviasaludeps.savia.dominio.contratacion.CntContratoCobertura;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import java.util.List;

public interface CntContratoCoberturaRemoto {

    List<CntContratoCobertura> consultarLista(ParamConsulta paramConsulta) throws Exception;
    
    int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception;
    
    /**
     * Método para consultar un registro por ID
     *
     * @param id
     * @return (CntContratoCobertura) cargado
     * @throws java.lang.Exception
     */
    CntContratoCobertura consultar(int id) throws Exception;

    /**
     * Método para crear una nuevo registro
     *
     * @param obj (CntContratoCobertura)
     * @return (int) id del registro generado
     * @throws java.lang.Exception
     */
    int insertar(CntContratoCobertura obj) throws Exception;

    /**
     * Método para actualizar la información de un registro
     *
     * @param obj (CntContratoCobertura)
     * @throws java.lang.Exception
     */
    void actualizar(CntContratoCobertura obj) throws Exception;

    /**
     * Método para eliminar un registro
     *
     * @param id
     * @return (CntContratoCobertura) La persistencia del objeto eliminado
     * @throws java.lang.Exception
     */
    CntContratoCobertura eliminar(int id) throws Exception;
    
    /**
     * Consultar todos los registros
     *
     * @return
     * @throws Exception
     */
    List<CntContratoCobertura> consultarTodos() throws Exception;
    
    /**
     * Función para eliminar los registros de CntContratoCoberturas asociados a CntContratoSede.
     * @param CntContratoSedeId
     * @return
     * @throws Exception 
     */
    boolean eliminarRegistrosAsociados(int CntContratoSedeId) throws Exception;
    
}
