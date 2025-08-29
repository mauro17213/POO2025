/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.cuentamedica.auditoria.utilidades;

import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmDetalle;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.negocio.cuentamedica.conciliacion.CmDetalleRemoto;
import com.saviasaludeps.savia.web.utilidades.RemotoEJB;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 *
 * @author Jorge Eliecer Perez
 */
public class CmAuditoriaFacturaBeanUtil {
    public final static String CAMPO_FILTRO_RIP_ID = "cmRipCarga.id"; 
    public final static String CAMPO_FILTRO_NIT = "nit"; 
    public final static String CAMPO_FILTRO_NUM_FACTURADO = "numeroFacturado"; 
    public final static String CAMPO_FILTRO_IPS = "ips"; 

    public static CmDetalleRemoto getCmDetalleRemoto() throws Exception {
        return (CmDetalleRemoto) RemotoEJB.getEJBRemoto("CmDetalleServicio", CmDetalleRemoto.class.getName());
    }
    
    public static void asignarCodigoMipres(List<CmDetalle> detalles) throws Exception {

        if (detalles != null) {

            Map<String, String> mapMaestroTotal = new HashMap<>();
            mapMaestroTotal.putAll(CmAuditoriaFacturaBeanUtil.obtenerInsumosMipres(detalles));
            mapMaestroTotal.putAll(CmAuditoriaFacturaBeanUtil.obtenerProcedimientosMipres(detalles));

            if (!mapMaestroTotal.isEmpty()) {
                for (CmDetalle detalle : detalles) {
                    if (detalle.hayPosibleCodigoMiPres()) {
                        detalle.setCodigoMiPress(mapMaestroTotal.getOrDefault(detalle.getMaServicioCodigo(), ""));
                    }
                }
            }

            mapMaestroTotal.clear();
        }
    }

    public static Map<String, String> obtenerProcedimientosMipres(List<CmDetalle> detalles) throws Exception {
        Map<String, String> mapMaestroTotal = new HashMap<>();
        String parametrosProc = detalles.stream().filter(detalle -> detalle.hayPosibleCodigoMiPres()).map(CmDetalle::getMaServicioCodigo).distinct().map(codigo -> "'" + codigo + "'").collect(Collectors.joining(", "));
        if (!parametrosProc.isEmpty()) {
            ParamConsulta paramConsulta = new ParamConsulta();
            paramConsulta.setParametroConsulta1(parametrosProc);
            mapMaestroTotal = getCmDetalleRemoto().consultarInfoProcedimientosMiPres(paramConsulta);
        }

        return mapMaestroTotal;
    }

    public static Map<String, String> obtenerInsumosMipres(List<CmDetalle> detalles) throws Exception {
        Map<String, String> mapMaestroTotal = new HashMap<>();
        String parametrosInsumos = detalles.stream().filter(detalle -> detalle.hayPosibleCodigoMiPres()).map(CmDetalle::getMaServicioCodigo).distinct().map(codigo -> "'" + codigo + "'").collect(Collectors.joining(", "));
        if (!parametrosInsumos.isEmpty()) {
            ParamConsulta paramConsulta = new ParamConsulta();
            paramConsulta.setParametroConsulta1(parametrosInsumos);
            mapMaestroTotal = getCmDetalleRemoto().consultarInfoInsumosMiPres(paramConsulta);
        }
        return mapMaestroTotal;
    }
}
