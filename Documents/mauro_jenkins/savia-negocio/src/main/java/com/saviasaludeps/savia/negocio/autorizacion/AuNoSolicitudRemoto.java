package com.saviasaludeps.savia.negocio.autorizacion;

import com.saviasaludeps.savia.dominio.autorizacion.AuNoSolicitud;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import java.util.List;

/**
 *
 * @author rpalacic
 */
public interface AuNoSolicitudRemoto {

    /**
     * Consulta de cantidad de registros en una lista
     *
     * @param paramConsulta
     * @return
     * @throws Exception
     */
    int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception;

    /**
     * Consultar lista de registros
     *
     * @param paramConsulta
     * @return
     * @throws Exception
     */
    List<AuNoSolicitud> consultarLista(ParamConsulta paramConsulta) throws Exception;

    /**
     * Método para consultar un anticipo por ID
     *
     * @param id
     * @return (Anticipos) cargado
     * @throws java.lang.Exception
     */
    AuNoSolicitud consultar(int id) throws Exception;
    
    /**
     * Método para consultar un anticipo por ID
     *
     * @param id
     * @return (Anticipos) cargado
     * @throws java.lang.Exception
     */
    AuNoSolicitud consultarCorto(int id) throws Exception;

    /**
     * Método para crear una nuevo anticipo
     *
     * @param per (AntAnticipo)
     * @return (int) id del registro generado
     * @throws java.lang.Exception
     */
    int insertar(AuNoSolicitud per) throws Exception;

    /**
     * Método para actualizar la información de una anticipo
     *
     * @param per (AntAnticipo)
     * @throws java.lang.Exception
     */
    void actualizar(AuNoSolicitud per) throws Exception;

    /**
     * Método para actualizar la información de una anticipo
     *
     * @param per (AntAnticipo)
     * @throws java.lang.Exception
     */
    void actualizarEstado(AuNoSolicitud per) throws Exception;

    /**
     * Consultar lista de registros
     *
     * @param listaAuNoSolicitud
     * @param idCarga
     * @return
     * @throws Exception
     */
    boolean insertarCargaMasivaSinAutoizacion(List<AuNoSolicitud> listaAuNoSolicitud, int idCarga) throws Exception;

    /**
     * Consultar lista de registros para obtener los id
     *
     * @param listaAuNoSolicitud
     * @param idCarga
     * @return
     * @throws Exception
     * *
     */
    List<AuNoSolicitud> obtenerIdSolicitudes(List<AuNoSolicitud> listaAuNoSolicitud, int idCarga) throws Exception;
}
