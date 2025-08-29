/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.cuentamedica.auditoria;

import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmConciliacionRespuestaGlosaModulo;

/**
 *
 * @author jeperezn
 */
public interface ConciliacionGlosadoGenericoRemoto {
    
   /**
    * permite realizar conciliacion masiva
    * @param conciliacionRespuestaModulo
    * @return
    * @throws Exception 
    */
    boolean ejecutarConciliacionMasiva(CmConciliacionRespuestaGlosaModulo conciliacionRespuestaModulo) throws Exception; 
    
}
