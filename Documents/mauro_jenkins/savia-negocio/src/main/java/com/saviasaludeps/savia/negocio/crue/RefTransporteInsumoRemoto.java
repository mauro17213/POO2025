/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.crue;

import com.saviasaludeps.savia.dominio.crue.RefTransporteInsumo;
import java.util.List;

/**
 *
 * @author Jaime Andres Olarte
 */
public interface RefTransporteInsumoRemoto {
    
    List<RefTransporteInsumo> consultarPorRefTransporteId(int id) throws Exception;
    
    int insertar(RefTransporteInsumo obj) throws Exception;
}
