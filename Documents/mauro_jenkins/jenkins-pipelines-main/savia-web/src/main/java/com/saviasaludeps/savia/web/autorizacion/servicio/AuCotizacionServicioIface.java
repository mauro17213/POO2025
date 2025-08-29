package com.saviasaludeps.savia.web.autorizacion.servicio;

import com.saviasaludeps.savia.dominio.anticipo.AntAnticipoItem;
import com.saviasaludeps.savia.web.autorizacion.bean.AuCotizacionBean;
import java.math.BigDecimal;

public interface AuCotizacionServicioIface {

    void Accion(AuCotizacionBean bean);

    void cargaInicial(AuCotizacionBean bean);

    void listarIPS(AuCotizacionBean bean);
    
    void listarAnticipos(AuCotizacionBean bean);

    void borrarAdjunto(int idAdjunto, AuCotizacionBean bean);

    BigDecimal consultarValorSoat(int idTarifario);

    void consultarPosfechados(AuCotizacionBean bean);

    void completarAfiliado(AuCotizacionBean bean);
    
    void consultarMaestroParaListarAnticipos(AuCotizacionBean bean);
    
    int validarAfiliado(Integer idAsegAfiliado, AuCotizacionBean bean);
    
    int consultarAnticipoValorDisponible(Integer idAnticipo, AuCotizacionBean bean);
    
    AntAnticipoItem consultarAticipoItem(Integer idAnticipo, Integer idTecnologia, AuCotizacionBean bean);
}
