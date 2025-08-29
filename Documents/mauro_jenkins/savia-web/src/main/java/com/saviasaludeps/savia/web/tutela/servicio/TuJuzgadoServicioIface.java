package com.saviasaludeps.savia.web.tutela.servicio;

import com.saviasaludeps.savia.web.tutela.bean.TuJuzgadoBean;

public interface TuJuzgadoServicioIface {
    
    void Accion(TuJuzgadoBean bean);
    
    /**
     * Cargar lista de Juzgados
     * @param bean
     * @throws Exception 
     */
    void cargaInial(TuJuzgadoBean bean);
    
//    void buscarAfiliado(TuJuzgadoBean bean);
//    
//    void consultarPersona(TuJuzgadoBean bean);
    
}
