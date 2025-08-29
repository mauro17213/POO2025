package com.saviasaludeps.savia.negocio.webservices;

import com.saviasaludeps.savia.webservices.rest.objeto.referencia.RespuestaSolicitudAnexo2DTO;
import com.saviasaludeps.savia.webservices.rest.objeto.referencia.RespuestaSolicitudAnexo9DTO;
import com.saviasaludeps.savia.webservices.rest.objeto.referencia.SolicitudAnexo2DTO;
import com.saviasaludeps.savia.webservices.rest.objeto.referencia.SolicitudAnexo9DTO;

/**
 *
 * @author yjimenez
 */
public interface ReferenciaRemoto {

    /**
     * Función para crear una solicitud de anexo 2
     *
     * @param solicitud
     * @param rutaInforme
     * @return
     * @throws Exception
     */
        RespuestaSolicitudAnexo2DTO crearSolicitudAnexo2(SolicitudAnexo2DTO solicitud, String rutaInforme) throws Exception;

    /**
     * Función para crear una solicitud de anexo 9
     *
     * @param solicitudes
     * @param usuario
     * @param rutaAdjunto
     * @param rutaAnexo
     * @return
     * @throws Exception
     */
    RespuestaSolicitudAnexo9DTO crearSolicitudAnexo9(SolicitudAnexo9DTO solicitudes, String usuario, String rutaAdjunto, String rutaAnexo) throws Exception;

    /**
     * Devuelve el detalle del estado de la solicitud
     *
     * @param id
     * @return
     * @throws Exception 2022-11-25|lguerreroh|se comenta metodo por retiro de
     * dto de ws de savia-negocio
     */
    //DetalleRespuestaSolicitudAnexo9DTO consultarEstadoAnexo9porId(Integer id) throws Exception;
}
