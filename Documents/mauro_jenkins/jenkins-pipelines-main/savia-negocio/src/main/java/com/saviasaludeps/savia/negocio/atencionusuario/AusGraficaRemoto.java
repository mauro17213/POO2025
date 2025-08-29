/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.atencionusuario;

import com.saviasaludeps.savia.dominio.generico.ParamConsulta;

/**
 *
 * @author pavacca
 */
public interface AusGraficaRemoto {
    int contarCasos(ParamConsulta paramConsulta, int tipoConsulta, int estadoCerrado, int estadoRechazado)throws Exception;
    
    int contarServicios(ParamConsulta paramConsulta, int tipoConsulta, int estadoCerrado, int estadoRechazado)throws Exception;
}
