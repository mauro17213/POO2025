/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.mipres.contingencia.servicio;

import com.saviasaludeps.savia.web.mipres.contingencia.bean.PrescripcionBean;

/**
 *
 * @author raul-palacios
 */
public interface PrescripcionServicioIface {

    void Accion(PrescripcionBean bean);
    
    void cargasInicial(PrescripcionBean bean);
    
    boolean conultarHistorico(int id);
    
}
