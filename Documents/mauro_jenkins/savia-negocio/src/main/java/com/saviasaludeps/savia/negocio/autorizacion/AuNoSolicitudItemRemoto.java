package com.saviasaludeps.savia.negocio.autorizacion;

import com.saviasaludeps.savia.dominio.autorizacion.AuNoSolicitud;
import com.saviasaludeps.savia.dominio.autorizacion.AuNoSolicitudItem;
import com.saviasaludeps.savia.solicitud.dominio.ValidaRespuestaDTO;
import java.util.List;

/**
 *
 * @author rpalacic
 */
public interface AuNoSolicitudItemRemoto {

    /**
     * Método para consultar un AuNoSolicitudItem por ID
     *
     * @param id
     * @return (AuNoSolicitudItem) cargado
     * @throws java.lang.Exception
     */
    AuNoSolicitudItem consultar(int id) throws Exception;

    /**
     * Método para crear una nuevo AuNoSolicitudItem
     *
     * @param per (AuNoSolicitudItem)
     * @return (int) id del registro generado
     * @throws java.lang.Exception
     */
    int insertar(AuNoSolicitudItem per) throws Exception;

    /**
     * Método para actualizar la información de una AuNoSolicitudDiagnostico
     *
     * @param per (AuNoSolicitudItem)
     * @throws java.lang.Exception
     */
    void actualizar(AuNoSolicitudItem per) throws Exception;

    /**
     * Método para actualizar la información de una AuNoSolicitudDiagnostico
     *
     * @param per (AuNoSolicitudItem)
     * @throws java.lang.Exception
     */
    void actualizarTipoEntrega(AuNoSolicitudItem per) throws Exception;

    /**
     * Método para actualizar la información de una AuNoSolicitudItems
     *
     * @param per (AuNoSolicitudItem)
     * @throws java.lang.Exception
     */
    void actualizarAnularSinEntrega(AuNoSolicitudItem per) throws Exception;
    
    /**
     * Método para actualizar la información de una AuNoSolicitudItems
     *
     * @param per (AuNoSolicitudItem)
     * @throws java.lang.Exception
     */
    void actualizarBorradoLogico(AuNoSolicitudItem per) throws Exception;
    
    /**
     * Consultar lista de registros
     *
     * @param idAuNoSolicitudItems
     * @return
     * @throws Exception
     */
    List<AuNoSolicitudItem> consultarItemPorAuNosolicitudesId(int idAuNoSolicitudItems) throws Exception;

    /**
     * Función para validar la cantidad de tecnologías autorizadas
     *
     * @param tipoTecnologia
     * @param cantidad
     * @param codigoTecnologia
     * @return
     */
    ValidaRespuestaDTO validarAuCantidadTecnologia(String tipoTecnologia, String cantidad, String codigoTecnologia);

    /**
     * Método para actualizar la información de una AuNoSolicitudItems
     *
     * @param listaNegocio
     * @param exitosos
     * @param fallidos
     * @param idCarga
     * @throws java.lang.Exception
     */
    void insertarItems(List<AuNoSolicitud> listaNegocio, int exitosos, int fallidos, int idCarga) throws Exception;

    /**
     * consulta AuNoSolicitudItem por id sin solicitud y codigo tecnologia
     *
     * @param idSinAutorizacion
     * @param codigo
     * @return
     * @throws Exception
     */
    AuNoSolicitudItem consultarPorIdSinAutorizonPorCodigo(int idSinAutorizacion, String codigo) throws Exception;
    
    /**
     * consulta AuNoSolicitudItem por id sin solicitud y codigo tecnologia
     * @param idSinAutorizacion
     * @param codigo
     * @return
     * @throws Exception
     */
    AuNoSolicitudItem consultarPorIdSinAutorizonPorMedicamento(int idSinAutorizacion, String codigo) throws Exception;
    
    /**
     * Consultar lista de registros para obtener los id
     *
     * @param listaAuNoSolicitud
     * @param idSolicitud
     * @return
     * @throws Exception
     * *
     */
    List<AuNoSolicitud> obtenerIdSolicitudesItem(List<AuNoSolicitud> listaAuNoSolicitud) throws Exception;
}
