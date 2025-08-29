package com.saviasaludeps.savia.negocio.contratacion;

import com.saviasaludeps.savia.dominio.contratacion.CntContrato;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import java.util.Date;
import java.util.List;

public interface CntContratoRemoto {

    List<CntContrato> consultarLista(ParamConsulta paramConsulta) throws Exception;
    
    int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception;
    
    int consultarCantidadListaConciliacion(ParamConsulta paramConsulta) throws Exception;
    
    List<CntContrato> consultarListaConciliacion(ParamConsulta paramConsulta) throws Exception;
    
    /**
     * Método para consultar un registro por ID
     *
     * @param id
     * @return (CntContrato) cargado
     * @throws java.lang.Exception
     */
    CntContrato consultar(int id) throws Exception;

    /**
     * Método para crear una nuevo registro
     *
     * @param obj (CntContrato)
     * @return (int) id del registro generado
     * @throws java.lang.Exception
     */
    int insertar(CntContrato obj) throws Exception;

    /**
     * Método para actualizar la información de un registro
     *
     * @param obj (CntContrato)
     * @throws java.lang.Exception
     */
    void actualizar(CntContrato obj) throws Exception;
    
    /**
     * Método para actualizar la información de un registro
     *
     * @param obj (CntContrato)
     * @throws java.lang.Exception
     */
    void actualizarEjecucionContratoAutorizado(CntContrato obj) throws Exception;

    /**
     * Método para eliminar un registro
     *
     * @param id
     * @return (CntContrato) La persistencia del objeto eliminado
     * @throws java.lang.Exception
     */
    CntContrato eliminar(int id) throws Exception;
    
    /**
     * Consultar todos los registros
     *
     * @return
     * @throws Exception
     */
    List<CntContrato> consultarTodos() throws Exception;
    
    /**
     * Método para consultar un registro por ID, que tambien obtendrá la información del Contrato y sus Sedes Asociadas
     *
     * @param id
     * @return (CntContrato) cargado
     * @throws java.lang.Exception
     */
    CntContrato consultarConSedes(int id) throws Exception;
    
    /**
     * Consultar la cantidad de contratos existente por el número de Contrato
     * @param contrato
     * @return
     * @throws Exception 
     */
    int consultarCantidadPorContrato(String contrato) throws Exception;
    
    /**
     * Método para consultar un registro por su contrato
     * @param contrato
     * @return
     * @throws Exception 
     */
    CntContrato consultarPorContrato(String contrato) throws Exception;
    
    /**
     * Método para consultar un registro por ID, obteniendo toda su información de sedes y de contrato detalles.
     *
     * @param id
     * @return (CntContrato) cargado
     * @throws java.lang.Exception
     */
    CntContrato consultarContratoCompleto(int id) throws Exception;
    
    /**
     * Método para consultar información básica de un contrato por ID
     * @param id
     * @return
     * @throws Exception 
     */
    CntContrato consultarDatosBasicos(int id) throws Exception;
    
    /**
     * Método para consultar una lista de contratos activos y vigentes por id de cntPrestadorId, tipoTecnologia
     * id de tecnologia, código de modalidad contrato, ubicacion id y que sean mayor o iguales que la fecha consultada.
     * @param cntPrestadorId
     * @param tipoTecnologia
     * @param idTecnologia
     * @param maeModalidadContratoCodigo
     * @param fecha
     * @param ubicacionId
     * @return
     * @throws Exception 
     */
    List<CntContrato> consultarPorPrestadorSede(int cntPrestadorId, int tipoTecnologia, int idTecnologia, String maeModalidadContratoCodigo, Date fecha, int ubicacionId) throws Exception;
    
}
