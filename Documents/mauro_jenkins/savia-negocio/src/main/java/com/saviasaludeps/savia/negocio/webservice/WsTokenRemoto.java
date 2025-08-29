/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.webservice;
import com.saviasaludeps.savia.dominio.webservice.WsToken;

import java.util.List;

/**
 *
 * @author Jaime Andres Olarte
 */
public interface WsTokenRemoto {
    
    List<WsToken> consultarByWsConexiones(int ws_conexiones_id) throws Exception;
}
