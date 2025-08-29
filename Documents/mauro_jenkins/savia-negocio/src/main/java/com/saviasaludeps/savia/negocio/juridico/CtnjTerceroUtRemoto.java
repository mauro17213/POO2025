
package com.saviasaludeps.savia.negocio.juridico;

import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.juridico.CntjTerceroUt;
import java.util.List;

/**
 *
 * @author idbohorquez
 */
public interface CtnjTerceroUtRemoto {
    
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
     * @return List<CntjTerceroUt>
     * @throws Exception 
     */
    List<CntjTerceroUt> consultarLista(ParamConsulta paramConsulta) throws Exception;

    /**
     * Guardar nuevo proceso
     * @author idbohorquez
     * @return int
     * @creacion 08/07/2024
     * @param objeto
     * @throws Exception 
     */
    int insertar(CntjTerceroUt objeto) throws Exception;

    /**
     * Consultar proceso por id
     * @author idbohorquez
     * @param id
     * @return int
     * @creacion 08/07/2024
     * @throws Exception 
     */
    CntjTerceroUt consultar(int id) throws Exception;
    
    /**
     * Actualizar proceso
     * @author idbohorquez
     * @param objeto
     * @creacion 08/07/2024
     * @throws Exception 
     */
    void actualizar(CntjTerceroUt objeto) throws Exception;
    
    /**
     * Actualizar proceso
     * @author idbohorquez
     * @param objeto
     * @creacion 08/07/2024
     * @throws Exception 
     */
    void borrar(CntjTerceroUt objeto) throws Exception;
    
    /**
     * Consultar lista por tercero
     * @author idbohorquez
     * @param idTercero
     * @return 
     * @creacion 13/08/2025
     * @throws Exception 
     */
    List<CntjTerceroUt> consultarPorTercero(Integer idTercero) throws java.lang.Exception;
    
}
