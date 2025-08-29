/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.webservices;

import com.saviasaludeps.savia.dominio.aseguramiento.AsegAfiliado;
import com.saviasaludeps.savia.webservices.rest.objeto.aseguramiento.DetalleRespuestaSolicitudPortabilidadDTO;
import com.saviasaludeps.savia.webservices.rest.objeto.aseguramiento.RespuestaAfiliadoInformacionSystemSaviaDTO;
import java.util.List;

/**
 *
 * @author rpalacios
 */
public interface AseguramientoRemoto {

    /**
     *
     * @param tipodocumento
     * @param numeroDocumento
     * @param fechaNacimiento
     * @param primerNombre
     * @param segundoNombre
     * @param primerApellido
     * @param segundoApellido
     * @return
     * @throws Exception
     */
    List<RespuestaAfiliadoInformacionSystemSaviaDTO> consultarAfiliado(String tipodocumento, String numeroDocumento, String fechaNacimiento, String primerNombre, String segundoNombre, String primerApellido, String segundoApellido) throws Exception;

    /**
     * Función para consultar la lista de Portabilidades asociadas a un afiliado
     *
     * @param id Id del Afiliado
     * @return
     * @throws Exception
     */
    List<DetalleRespuestaSolicitudPortabilidadDTO> consultarPortabilidadPorAfiliado(int id) throws Exception;

    /**
     * Función para consultar Portabilidad por id
     *
     * @param id Id de la portabilidad
     * @return
     * @throws Exception
     */
    DetalleRespuestaSolicitudPortabilidadDTO consultarPortabilidadPorId(int id) throws Exception;

    /**
     * Función para consultar Portabilidad por idAfiliado y IdPortabilidad
     *
     * @param idAfiliado
     * @param idPortabilidad
     * @return
     * @throws Exception
     */
    DetalleRespuestaSolicitudPortabilidadDTO consultarPortabilidadPorAfiliadoIdPortabilidad(int idAfiliado, int idPortabilidad) throws Exception;

    /**
     * Función para consultar afiliado por numero de documento
     *
     * @param documento
     * @return
     * @throws Exception
     */
    AsegAfiliado consultarAfiliadoId(String documento) throws Exception;
  
}
