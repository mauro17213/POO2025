/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.atencionusuario.servicio;

import com.saviasaludeps.savia.dominio.atencionusuario.AusCasoServicio;
import com.saviasaludeps.savia.web.atencionusuario.bean.AusServicioGestionBean;

/**
 *
 * @author pavacca
 */
public interface AusServicioGestionServicioIface {
    void Accion(AusServicioGestionBean bean);
    AusCasoServicio consultarServicio(int id);
    void cargaInial(AusServicioGestionBean bean); 
}
