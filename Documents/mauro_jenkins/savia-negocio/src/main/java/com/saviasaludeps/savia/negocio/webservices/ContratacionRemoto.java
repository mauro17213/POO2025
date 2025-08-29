/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.webservices;

import com.saviasaludeps.savia.webservices.rest.objeto.contratacion.IpsDTO;

/**
 *
 * @author rpalacios
 */
public interface ContratacionRemoto {

    /**
     * Función para consultar ips
     *
     * @param codigoHabilitacionPrestador
     * @param documentoPrestador
     * @param codigoSedePrestador
     * @return
     * @throws Exception
     */
    IpsDTO consultarIps(String codigoHabilitacionPrestador, String documentoPrestador, String codigoSedePrestador) throws java.lang.Exception;

}
