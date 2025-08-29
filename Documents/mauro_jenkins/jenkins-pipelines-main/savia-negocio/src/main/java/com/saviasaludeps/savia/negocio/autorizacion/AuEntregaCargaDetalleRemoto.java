/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.autorizacion;

import com.saviasaludeps.savia.dominio.autorizacion.AuEntregaCargaDetalle;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import java.util.List;

/**
 *
 * @author iavenegas
 */
public interface AuEntregaCargaDetalleRemoto {

    int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception;

    List<AuEntregaCargaDetalle> consultarLista(ParamConsulta paramConsulta) throws Exception;

    AuEntregaCargaDetalle consultar(int id) throws Exception;

    /**
     *
     * @param obj
     * @return
     * @throws Exception
     */
    int insertar(AuEntregaCargaDetalle obj) throws Exception;
}
