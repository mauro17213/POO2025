package com.saviasaludeps.savia.web.mipres.servicio;

import com.saviasaludeps.savia.dominio.mipres.ReporteDireccionamiento;
import com.saviasaludeps.savia.web.mipres.bean.PrestadorMipresBean;

/**
 *
 * @author bsgomez
 */
public interface PrestadorMipresIface {

    /**
     * Método de acciones central
     *
     * @param bean
     */
    void Accion(PrestadorMipresBean bean);

    void CargaInicial(PrestadorMipresBean bean); 
    
    void verHistorico(PrestadorMipresBean bean, int id);
    
    ReporteDireccionamiento consultarDireccionamientoReporte(Integer id);

}
