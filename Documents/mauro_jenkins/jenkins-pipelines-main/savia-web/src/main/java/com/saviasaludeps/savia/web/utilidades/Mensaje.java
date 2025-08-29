/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.utilidades;

import com.saviasaludeps.savia.dominio.administracion.GnSmsEnvio;
import com.saviasaludeps.savia.negocio.administracion.GnSmsEnvioRemoto;
import java.util.Date;

/**
 *
 * @author sgiraldov
 */
public class Mensaje {
    
    private GnSmsEnvioRemoto getGnSmsEnvioRemoto() throws Exception {
        return (GnSmsEnvioRemoto) RemotoEJB.getEJBRemoto("GnSmsEnvioServicio", GnSmsEnvioRemoto.class.getName());
    }

    public void guardar(int origin, String celular, String mensaje) {
        GnSmsEnvio envio = new GnSmsEnvio();
        envio.setOrigen(origin);
        envio.setEstado(GnSmsEnvio.ESTADO_CREADO);
        envio.setCelulares(celular);
        envio.setTexto(mensaje);
        envio.setFechaHoraCrea(new Date());
        try {
            getGnSmsEnvioRemoto().insertar(envio);
        } catch (Exception e) {
        }        
    }
    
}
