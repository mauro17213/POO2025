/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.atencionusuario.servicio;

import com.saviasaludeps.savia.web.atencionusuario.bean.AusGraficasBean;

/**
 *
 * @author pavacca
 */
public interface AusGraficasServicioIface {
    void Accion(AusGraficasBean bean) throws Exception;
    void cargaInicial(AusGraficasBean bean);
}
