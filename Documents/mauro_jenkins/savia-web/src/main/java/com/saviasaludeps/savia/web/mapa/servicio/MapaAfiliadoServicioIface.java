/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.mapa.servicio;

import com.saviasaludeps.savia.web.mapa.bean.MapaAfiliadoBean;

/**
 *
 * @author raul-palacios
 */
public interface MapaAfiliadoServicioIface {
    
    public void Accion(MapaAfiliadoBean bean);
    
    public void cargasInicial(MapaAfiliadoBean bean);
}
