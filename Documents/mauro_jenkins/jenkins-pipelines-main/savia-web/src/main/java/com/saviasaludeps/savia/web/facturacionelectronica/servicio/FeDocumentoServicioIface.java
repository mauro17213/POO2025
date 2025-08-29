/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.saviasaludeps.savia.web.facturacionelectronica.servicio;

import com.saviasaludeps.savia.web.facturacionelectronica.bean.FeDocumentoBean;

/**
 *
 * @author apuertav
 */
public interface FeDocumentoServicioIface {

    void Accion(FeDocumentoBean bean);

    void cargar(FeDocumentoBean bean);

    void cargaInicial(FeDocumentoBean bean);
}
