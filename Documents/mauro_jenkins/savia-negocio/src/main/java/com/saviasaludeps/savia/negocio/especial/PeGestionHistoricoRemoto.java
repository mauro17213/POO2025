
package com.saviasaludeps.savia.negocio.especial;

import com.saviasaludeps.savia.dominio.especial.PeGestionHistorico;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import java.util.List;

/**
 *
 * @author idbohorquez
 */
public interface PeGestionHistoricoRemoto {
    
    
    int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception;
    
    List<PeGestionHistorico> consultarLista(ParamConsulta paramConsulta) throws Exception;
    
    List<PeGestionHistorico> consultarListaHistorico(Integer idGestion) throws Exception;
    
    int insertar(PeGestionHistorico obj) throws Exception;
    
    
}
