
package com.saviasaludeps.savia.negocio.reserva;

import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.reserva.RtReservaArchivo;
import java.util.List;

/**
 *
 * @author idbohorquez
 */
public interface RtReservaArchivoRemoto {
    
    int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception;
    
    List<RtReservaArchivo> consultarLista(ParamConsulta paramConsulta) throws Exception;
    
    RtReservaArchivo consultar(int id) throws Exception;
    
    List<RtReservaArchivo> consultarListaTipo(Integer id, Integer tipo) throws Exception;
    
    void cambiarEstadoArchivo(RtReservaArchivo obj) throws Exception;
    
}
