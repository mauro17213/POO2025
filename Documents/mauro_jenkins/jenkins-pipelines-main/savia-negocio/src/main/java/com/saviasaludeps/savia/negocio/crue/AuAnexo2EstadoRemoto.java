/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.crue;

import com.saviasaludeps.savia.dominio.crue.AuAnexo2Estado;

/**
 *
 * @author AlexanderDiaz
 */
public interface AuAnexo2EstadoRemoto {
    
    int insertar(AuAnexo2Estado obj) throws Exception;
    
    AuAnexo2Estado consultarPorAuAnexo2(int id) throws Exception;
    
}
