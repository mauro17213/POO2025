/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.informe;

import com.saviasaludeps.savia.dominio.informe.InfRuta;
import java.util.List;

/**
 *
 * @author sgiraldov
 */
public interface InformeRutaRemoto {
    
    /**
     * Consulta todas las rutas existentes
     * @return
     * @throws Exception 
     */
    List<InfRuta> consultarTodas() throws Exception;
    
}
