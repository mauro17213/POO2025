package com.saviasaludeps.savia.web.recobro.servicio;
import com.saviasaludeps.savia.dominio.recobro.RcoConciliacion;
import com.saviasaludeps.savia.dominio.recobro.RcoConciliacionGestion;
import com.saviasaludeps.savia.dominio.recobro.RcoFacturaDetalle;
import com.saviasaludeps.savia.web.recobro.bean.RecobroConciliacionBean;
import java.util.List;

/**
 *
 * @author sgiraldov
 */
public interface RecobroConciliacionServicioIface {

    /**
     * Método de acciones central
     *
     * @author Stiven Giraldo
     * @param bean
     */
    void Accion(RecobroConciliacionBean bean);
    
    void listarFacturas(RecobroConciliacionBean bean);
    
    void listarFacturaDetalles(RecobroConciliacionBean bean);
    
    void cargaInicial(RecobroConciliacionBean bean);
    
    RcoConciliacionGestion consultarConciliacionesGestion(int conciliacionId, RecobroConciliacionBean bean);
    
    RcoConciliacion consultarConciliacion(int conciliacionId, RecobroConciliacionBean bean);
    
    List<RcoConciliacionGestion> consultarListaConciliacionesGestion(int conciliacionId, RecobroConciliacionBean bean);
    
    List<RcoFacturaDetalle> consultarListaFacturasDetalles(int conciliacionId, RecobroConciliacionBean bean);

}
