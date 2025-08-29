/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.anticipo.servicio;

import com.saviasaludeps.savia.dominio.anticipo.AntAnticipo;
import com.saviasaludeps.savia.web.anticipo.bean.AnticipoBean;



/**
 *
 * @author raul-palacios
 */
public interface AnticipoIface {

    void Accion(AnticipoBean bean);
    
    void cargaInicial(AnticipoBean bean);
    
    void listarAfiliado(AnticipoBean bean);
    
    void listarPrestadores(AnticipoBean bean);
    
    boolean validarEstadoAfiliado(int maeEstadoAfiliacion, AnticipoBean bean);
    
    AntAnticipo consultarAnticipoId(int idAnticipo, AnticipoBean bean);
}
