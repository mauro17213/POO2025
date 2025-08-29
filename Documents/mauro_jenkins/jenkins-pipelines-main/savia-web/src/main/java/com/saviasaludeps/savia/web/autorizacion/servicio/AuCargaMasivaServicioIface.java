/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.autorizacion.servicio;

import com.saviasaludeps.savia.dominio.autorizacion.AuAnexo3Carga;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestadorSede;
import com.saviasaludeps.savia.web.autorizacion.bean.AuCargaMasivaBean;
import java.util.List;

/**
 *
 * @author Stiven Giraldo
 */
public interface AuCargaMasivaServicioIface {

    /**
     * Método de acciones central
     *
     * @param bean
     */
    void Accion(AuCargaMasivaBean bean);
    
    AuAnexo3Carga consultarCarga(AuCargaMasivaBean bean);

}
