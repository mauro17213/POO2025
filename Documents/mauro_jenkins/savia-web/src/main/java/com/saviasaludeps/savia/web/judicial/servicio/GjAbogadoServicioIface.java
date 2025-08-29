/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.judicial.servicio;

import com.saviasaludeps.savia.web.judicial.bean.GjAbogadoBean;



/**
 *
 * @author raul-palacios
 */
public interface GjAbogadoServicioIface {

    void Accion(GjAbogadoBean bean);

    void cargasInicial(GjAbogadoBean bean);
    
    void consultarAbogadoAfiliado(GjAbogadoBean bean);
    
    void consultarPersona(GjAbogadoBean bean);
    
    void consultarAbogado (GjAbogadoBean bean);


}
