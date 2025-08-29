package com.saviasaludeps.savia.web.mipres.servicio;

import com.saviasaludeps.savia.web.mipres.bean.CotizacionMipresBean;

/**
 *
 * @author bsgomez
 */
public interface MipresCotizacionIface {

    /**
     * Método de acciones central
     *
     * @param bean
     */
    void Accion(CotizacionMipresBean bean);

    void CargaInicial(CotizacionMipresBean bean);

    void completarAfiliado(CotizacionMipresBean bean);

    void listarPrestador(CotizacionMipresBean bean);

    void listarMaMedicamento(CotizacionMipresBean bean);

    void listarMaTecnologia(CotizacionMipresBean bean);

    void listarMaTecnologiaMipres(CotizacionMipresBean bean);
    
    void listarMaInsumoMipres(CotizacionMipresBean bean);
    
    void listarMaPaqueteMipres(CotizacionMipresBean bean);

}
