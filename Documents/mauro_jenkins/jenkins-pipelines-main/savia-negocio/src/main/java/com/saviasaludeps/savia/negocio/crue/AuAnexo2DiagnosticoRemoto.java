/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.crue;

import com.saviasaludeps.savia.dominio.crue.AuAnexo2Diagnostico;
import java.util.List;

/**
 *
 * @author AlexanderDiaz
 */
public interface AuAnexo2DiagnosticoRemoto {
    
    int insertar(AuAnexo2Diagnostico obj) throws Exception;
    
    void actualizar(AuAnexo2Diagnostico obj) throws Exception;
    
    List<AuAnexo2Diagnostico> consultarPorAuAnexo2(int idAuAnexo2) throws Exception;
}
