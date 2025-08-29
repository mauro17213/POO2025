/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.crue;

import com.saviasaludeps.savia.dominio.crue.AuAnexo2Gestion;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import java.util.List;

/**
 *
 * @author AlexanderDiaz
 */
public interface AuAnexo2GestionaRemoto {
    
    int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception;
    
    List<AuAnexo2Gestion> consultarLista(ParamConsulta paramConsulta) throws Exception;
    
    int insertar(AuAnexo2Gestion obj) throws Exception;
}
