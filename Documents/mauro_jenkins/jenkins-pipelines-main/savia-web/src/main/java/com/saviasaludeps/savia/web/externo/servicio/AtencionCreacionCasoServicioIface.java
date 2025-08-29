/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.externo.servicio;

import com.saviasaludeps.savia.web.atencionusuario.bean.AusPersonaBean;
import com.saviasaludeps.savia.web.externo.bean.AtencionCreacionCasoBean;
import java.util.Date;
import java.util.List;

/**
 *
 * @author pavacca
 */
public interface AtencionCreacionCasoServicioIface {
    void Accion(AtencionCreacionCasoBean bean);
    
    void cargaInicial(AtencionCreacionCasoBean bean);
    List<Date> obtenerFechas(Date fecha);
    void consultarPersona(AusPersonaBean bean);
    void consultarPersonaAnonima(AusPersonaBean bean);
    void consultarPersonaAfiliada(AusPersonaBean bean);
}
