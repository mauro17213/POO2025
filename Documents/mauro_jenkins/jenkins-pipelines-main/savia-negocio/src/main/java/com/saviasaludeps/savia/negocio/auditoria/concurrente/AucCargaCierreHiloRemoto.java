/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.saviasaludeps.savia.negocio.auditoria.concurrente;

import com.saviasaludeps.savia.dominio.auditoria.concurrente.AucCargaCierre;

/**
 *
 * @author idbohorquez
 */
public interface AucCargaCierreHiloRemoto {
    
    void iniciarCarga(AucCargaCierre carga) throws Exception;
    
}
