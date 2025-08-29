/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.sincronizasap;

/**
 *
 * @author jeperez
 */
public interface EnvioTransaccionesSapRemoto {
    
    /**
     * Método que permite enviar una transaccion sincronizada a sap
     * @param idCmRadicado
     * @throws Exception 
     */
    public void enviarTransaccionSap(int idCmRadicado) throws Exception;

}
