/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.crue.servicio;

import com.saviasaludeps.savia.web.crue.bean.RescateBean;

/**
 *
 * @author iavenegas
 */
public interface RescateIface {

    void Accion(RescateBean bean);

    void cargaInicial(RescateBean bean);
    
    void verAnexo3(RescateBean bean);
    
    void verAnexo2(RescateBean bean);
    
    void verHospitalizacion(RescateBean bean);
}
