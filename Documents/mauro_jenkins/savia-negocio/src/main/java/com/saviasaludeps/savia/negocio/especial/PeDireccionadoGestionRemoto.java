/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.especial;

import com.saviasaludeps.savia.dominio.especial.PeDireccionado;
import com.saviasaludeps.savia.dominio.especial.PeDireccionadoGestion;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import java.util.List;

/**
 *
 * @author idbohorquez
 */
public interface PeDireccionadoGestionRemoto {
    
    /**
     * Consulta listado de direccionado gestiones mediante el id de direccionado
     * @author idbohorquez
     * @creado 31/08/2022
     * @param idDireccionado
     * @return List<PeDireccionadoGestion>
     * @throws Exception 
     */
    List<PeDireccionadoGestion> listaGestionesDireccion(Integer idDireccionado) throws Exception;
    
    /**
     * Funcion que permite guardar nuevo registro de DireccionGestiones
     * @author idbohorquez
     * @creado 01/09/2022
     * @param PeDireccionadoGestion
     * @return int
     * @throws Exception 
     */
    public int insertar(PeDireccionadoGestion obj) throws Exception;
    
    
}
