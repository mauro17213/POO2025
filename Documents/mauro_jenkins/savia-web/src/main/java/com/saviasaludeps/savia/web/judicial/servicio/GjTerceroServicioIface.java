/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.judicial.servicio;

import com.saviasaludeps.savia.web.judicial.bean.GjTerceroBean;

/**
 *
 * @author raul-palacios
 */
public interface GjTerceroServicioIface {

    void Accion(GjTerceroBean bean);

    void cargasInicial(GjTerceroBean bean);

    void consultarUbicaciones(GjTerceroBean bean);

//    void consultarUsuaroEditar(GjTerceroBean bean);

    void consultarPersonaAfiliada(GjTerceroBean bean);

    void consultarPrestadorAfiliado(GjTerceroBean bean);

    void consultarPersona(GjTerceroBean bean);
    
    void consultarTerCero (GjTerceroBean bean);
    
    

}
