/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.solicitud.negocio;

import com.saviasaludeps.savia.dominio.autorizacion.AuAnexo3;

/**
 *
 * @author idbohorquez
 */
public interface PeDireccionadoSolicitudRemoto {

    /**
     * Método para crear registros de Direccionados desde anexo3
     *
     * @author idbohorquez
     * @param auAnexo3
     * @return 
     * @throws Exception
     */
    AuAnexo3 realizarSolicitudDireccionados(AuAnexo3 auAnexo3) throws Exception;

    /**
     * Método para validar si la solicitud anexo 3 aplica para Direccionado
     *
     * @author idbohorquez
     * @fechaCreacion 15/09/2022
     * @param AuAnexo3
     * @return boolean
     */
    boolean aplicaDireccionado(AuAnexo3 auAnexo3) throws Exception;
    
    /**
     * Método para validar si la solicitud anexo 3 aplica para Direccionado sin
     * tener en cuenta si la sede es sin especificar
     *
     * @author idbohorquez
     * @fechaCreacion 15/09/2022
     * @param AuAnexo3
     * @return boolean
     */
    boolean aplicaDireccionadoAllSede(AuAnexo3 auAnexo3) throws Exception;

}
