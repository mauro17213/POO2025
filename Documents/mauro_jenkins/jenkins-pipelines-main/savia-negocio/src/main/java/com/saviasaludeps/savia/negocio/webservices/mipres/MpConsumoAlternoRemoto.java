/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.webservices.mipres;

import com.saviasaludeps.savia.dominio.mipres.MpConsumo;
import java.util.Date;

/**
 *
 * @author jramirez
 */
public interface MpConsumoAlternoRemoto {

    public Integer insertarMpConsumo(MpConsumo mpConsumo) throws Exception;

    public void actualizarMpConsumo(MpConsumo obj) throws Exception;
    
    public MpConsumo consultarIdConsumo(Integer id) throws Exception;

    public String obtenerUltimaFechaEjecucionServicio(String servicio) throws Exception;

    public String obtenerUltimaFechaEjecucionServicio(String servicio, String fechaInicio, String fechaFin) throws Exception;

    public MpConsumo consultarUltimoConsumo(String servicio) throws Exception;

    public MpConsumo consultarUltimoConsumo(String servicio, String fecha) throws Exception;

}
