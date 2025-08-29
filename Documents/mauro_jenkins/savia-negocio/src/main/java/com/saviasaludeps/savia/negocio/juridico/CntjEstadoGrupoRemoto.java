
package com.saviasaludeps.savia.negocio.juridico;

import com.saviasaludeps.savia.dominio.juridico.CntjEstadoGrupo;
import java.util.List;

/**
 *
 * @author idbohorquez
 */
public interface CntjEstadoGrupoRemoto {
    
    int insertar(CntjEstadoGrupo objeto) throws java.lang.Exception;
    
    List<CntjEstadoGrupo> gruposEstado(int idEstado) throws Exception;
    
    CntjEstadoGrupo eliminar(int id) throws Exception;
    
    CntjEstadoGrupo consultar(int idestado) throws java.lang.Exception;
    
}
