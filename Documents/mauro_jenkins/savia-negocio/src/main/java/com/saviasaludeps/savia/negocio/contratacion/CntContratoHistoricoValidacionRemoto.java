package com.saviasaludeps.savia.negocio.contratacion;

import com.saviasaludeps.savia.dominio.contratacion.CntContratoHistoricoValidacion;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import java.util.List;

public interface CntContratoHistoricoValidacionRemoto {

    List<CntContratoHistoricoValidacion> consultarLista(ParamConsulta paramConsulta) throws Exception;

    int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception;

    /**
     * Método para consultar un registro por ID
     *
     * @param id
     * @return (CntContratoHistorico) cargado
     * @throws java.lang.Exception
     */
    CntContratoHistoricoValidacion consultar(int id) throws Exception;

    /**
     * Método para crear una nuevo registro
     *
     * @param obj (CntContratoHistorico)
     * @return (int) id del registro generado
     * @throws java.lang.Exception
     */
    int insertar(CntContratoHistoricoValidacion obj) throws Exception;

    /**
     * Método para actualizar la información de un registro
     *
     * @param obj (CntContratoHistorico)
     * @throws java.lang.Exception
     */
    void actualizar(CntContratoHistoricoValidacion obj) throws Exception;
    /**
     * Método para eliminar un registro
     *
     * @param id
     * @return (CntContratoHistorico) La persistencia del objeto eliminado
     * @throws java.lang.Exception
     */
    CntContratoHistoricoValidacion eliminar(int id) throws Exception;

    /**
     * Consultar todos los registros
     *
     * @return
     * @throws Exception
     */
    List<CntContratoHistoricoValidacion> consultarTodos() throws Exception;

    /**
     * Validar si un contrato, o contrato detalle tienen registros previos en el
     * histórico
     *
     * @param tipo
     * @param contratoId
     * @param contratoSedeId
     * @param contratoDetalleId
     * @return
     * @throws Exception
     */
    boolean consultarHistoricoValidacionExistente(int tipo, int contratoId, int contratoSedeId, int contratoDetalleId) throws Exception;
    
    /**
     * Consulta el ultimo registro de historico validacion para posterior 
     * validacion
     *
     * @param tipo
     * @param contratoId
     * @param contratoSedeId
     * @param contratoDetalleId
     * @return
     * @throws Exception
     */
    CntContratoHistoricoValidacion consultarUltimoHistoricoValidacion(int tipo, int contratoId, int contratoSedeId, int contratoDetalleId) throws Exception;

}
