
package com.saviasaludeps.savia.negocio.juridico;

import com.saviasaludeps.savia.dominio.juridico.CntjProceso;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.juridico.CntjEstado;
import java.util.List;

/**
 *
 * @author idbohorquez
 */
public interface CtnjProcesoRemoto {
    
    /**
     * Consulta de cantidad de registros 
     * @author idbohorquez
     * @creacion 08/07/2024
     * @param paramConsulta
     * @return int
     * @throws Exception 
     */
    int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception;
    
    /**
     * Consultar lista de registros
     * @author idbohorquez
     * @creacion 08/07/2024
     * @param paramConsulta
     * @return List<CntjProceso>
     * @throws Exception 
     */
    List<CntjProceso> consultarLista(ParamConsulta paramConsulta) throws Exception;

    /**
     * Guardar nuevo proceso
     * @author idbohorquez
     * @return int
     * @creacion 08/07/2024
     * @param objeto
     * @throws Exception 
     */
    int insertar(CntjProceso objeto) throws Exception;

    /**
     * Consultar proceso por id
     * @author idbohorquez
     * @param idproceso
     * @return int
     * @creacion 08/07/2024
     * @throws Exception 
     */
    CntjProceso consultar(int idproceso) throws Exception;
    
    /**
     * Actualizar proceso
     * @author idbohorquez
     * @param objeto
     * @creacion 08/07/2024
     * @throws Exception 
     */
    void actualizar(CntjProceso objeto) throws Exception;
    
    /**
     * Consultar lista de registros
     * @author idbohorquez
     * @creacion 08/07/2024
     * @return List<CntjProceso>
     * @throws Exception 
     */
    List<CntjProceso> getProcesos() throws java.lang.Exception;
    
    /**
     * Consultar proceso por tipo
     * @author idbohorquez
     * @param tipoProceso
     * @creacion 02/07/2025
     * @return CntjProceso
     * @throws Exception 
     */
    CntjProceso getProcesoPorTipo(int tipoProceso) throws java.lang.Exception;
    
}
