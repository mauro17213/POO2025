/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.crue;

import com.saviasaludeps.savia.dominio.crue.AuAnexo2Adjunto;
import java.util.List;

/**
 *
 * @author AlexanderDiaz
 */
public interface AuAnexo2AdjuntoRemoto {

    int insertar(AuAnexo2Adjunto obj) throws Exception;
    
    List<AuAnexo2Adjunto> consultarPorAuAnexo2(int idAuAnexo2) throws Exception;
    
}
