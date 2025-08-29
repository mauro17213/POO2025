package com.saviasaludeps.savia.negocio.contratacion;

import com.saviasaludeps.savia.dominio.contratacion.CntContratoSede;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import java.util.List;

public interface CntContratoSedeRemoto {

    List<CntContratoSede> consultarLista(ParamConsulta paramConsulta) throws Exception;
    
    int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception;
    
    /**
     * Método para consultar un registro por ID
     *
     * @param id
     * @return (CntContratoSede) cargado
     * @throws java.lang.Exception
     */
    CntContratoSede consultar(int id) throws Exception;

    /**
     * Método para crear una nuevo registro
     *
     * @param obj (CntContratoSede)
     * @return (int) id del registro generado
     * @throws java.lang.Exception
     */
    int insertar(CntContratoSede obj) throws Exception;

    /**
     * Método para actualizar la información de un registro
     *
     * @param obj (CntContratoSede)
     * @throws java.lang.Exception
     */
    void actualizar(CntContratoSede obj) throws Exception;

    /**
     * Método para eliminar un registro
     *
     * @param id
     * @return (CntContratoSede) La persistencia del objeto eliminado
     * @throws java.lang.Exception
     */
    CntContratoSede eliminar(int id) throws Exception;
    
    /**
     * Consultar todos los registros
     *
     * @return
     * @throws Exception
     */
    List<CntContratoSede> consultarTodos() throws Exception;

    /**
     * Método para contar si existe un contrato sede asociado al contrato y codigo de Habilitacion ingresados
     * @param contrato
     * @param codigoHabilitacion
     * @return
     * @throws Exception 
     */
    int consultarCantidadPorContratoYCodigoHabilitacionPrestador(String contrato, String codigoHabilitacion) throws Exception;

    /**
     * Método para contar si existe un contrato sede asociado al contrato y codigo de Habilitacion ingresados
     * @param contrato
     * @param codigoHabilitacion
     * @param modalidad
     * @return
     * @throws Exception 
     */
    CntContratoSede consultarPorContratoCodigoHabilitacionPrestadorYModalidad(String contrato, String codigoHabilitacion, String modalidad) throws Exception;
    
    /**
     * Método para obtener un contrato sede asociado al contrato y codigo de Habilitacion ingresados
     * @param contrato
     * @param codigoHabilitacion
     * @return
     * @throws Exception 
     */
    CntContratoSede consultarPorContratoYCodigoHabilitacionPrestador(String contrato, String codigoHabilitacion) throws Exception;
    
    /**
     * Método para obtener un contrato sede asociado al contrato , la modalidad y el codigo de Habilitacion ingresados
     * @param contrato
     * @param modalidad
     * @param codigoHabilitacion
     * @return
     * @throws Exception 
     */
    CntContratoSede consultarPorContratoModalidadYCodigoHabilitacionPrestador(String contrato, String modalidad, String codigoHabilitacion) throws Exception;
    
    List<CntContratoSede> consultarListaBuscador(ParamConsulta paramConsulta) throws Exception;
    
    int consultarCantidadListaBuscador(ParamConsulta paramConsulta) throws Exception;
}
