/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.webservices;

import com.saviasaludeps.savia.dominio.autorizacion.AuAnexo3;
import com.saviasaludeps.savia.dominio.autorizacion.AuAnexo3Item;
import com.saviasaludeps.savia.dominio.autorizacion.ConsultaSolicitud;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestadorSede;
import com.saviasaludeps.savia.webservices.rest.objeto.autorizacion.AfiliadoAutorizacionDTO;
import com.saviasaludeps.savia.webservices.rest.objeto.autorizacion.DetalleRespuestaAutorizacionImpresionDTO;
import com.saviasaludeps.savia.webservices.rest.objeto.autorizacion.RespuestaAutorizacionPrestador1DTO;
import com.saviasaludeps.savia.webservices.rest.objeto.autorizacion.RespuestaAutorizacionPrestador2DTO;
import com.saviasaludeps.savia.webservices.rest.objeto.autorizacion.RespuestaEntregaServiciosDTO;
import com.saviasaludeps.savia.webservices.rest.objeto.autorizacion.RespuestaSolicitudAnexo3DTO;
import com.saviasaludeps.savia.webservices.rest.objeto.autorizacion.SolicitudAnexo3DTO;
import com.saviasaludeps.savia.webservices.rest.objeto.autorizacion.SolicitudAutorizacionDTO;
import com.saviasaludeps.savia.webservices.rest.objeto.autorizacion.SolicitudAutorizacionImpresionDTO;
import com.saviasaludeps.savia.webservices.rest.objeto.autorizacion.SolicitudEntregaServiciosDTO;
import com.saviasaludeps.savia.webservices.rest.objeto.autorizacion.SolicitudAutorizacionPrestadorDTO;
import java.util.List;

/**
 *
 * @author rpalacios
 */
public interface AutorizacionRemoto {

    /**
     * Servicio que trae las autorizaciones del afiliado segun los parametros
     *
     * @param solicitud
     * @return
     * @throws Exception
     */
    AfiliadoAutorizacionDTO consultarAutorizacion(SolicitudAutorizacionDTO solicitud) throws Exception;

    /**
     * Servicio que trae las autorizaciones del afiliado segun los parametros
     *
     * @param solicitud
     * @return
     * @throws Exception
     */
    AuAnexo3 consultarEstadoAutorizacion(ConsultaSolicitud solicitud) throws Exception;

    /**
     *
     * @param id
     * @return
     * @throws Exception
     */
    List<AuAnexo3Item> consultarPorAnexo3Id(int id) throws Exception;

    /**
     * Servicio que imprime el pdf de la autorizacion
     *
     * @param solicitud
     * @param rutaA2
     * @param rutaA3
     * @return
     * @throws Exception
     */
    DetalleRespuestaAutorizacionImpresionDTO autorizacionImpresion(SolicitudAutorizacionImpresionDTO solicitud, String rutaA2, String rutaA3) throws Exception;

    /**
     * Servicio que crea una solicitud de anexo3
     *
     * @param solicitud
     * @param rutaInforme
     * @param usuario
     * @return
     * @throws Exception
     */
    RespuestaSolicitudAnexo3DTO crearSolicitudAnexo3(SolicitudAnexo3DTO solicitud, String rutaInforme, String usuario) throws Exception;

    /**
     * Entrega de servicios
     *
     * @param solicitud
     * @param usuario
     * @return
     * @throws Exception
     */
    RespuestaEntregaServiciosDTO entregaServicios(SolicitudEntregaServiciosDTO solicitud, String usuario) throws Exception;

    /**
     * Consultar prestador por id
     *
     * @param id
     * @return
     * @throws Exception
     */
    CntPrestadorSede consultarPrestadorPorId(int id) throws Exception;
    /**
     * Servicio que trae las autorizaciones del afiliado segun los parametros
     *
     * @param solicitud
     * @return
     * @throws Exception
     */
    RespuestaAutorizacionPrestador2DTO consultarAutorizacionPrestador(SolicitudAutorizacionPrestadorDTO solicitud) throws Exception;

}
