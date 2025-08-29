package com.saviasaludeps.savia.negocio.juridico;

import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.juridico.CntjLinea;
import java.util.List;

/**
 *
 * @author idbohorquez
 */
public interface CtnjLineaRemoto {
    
    /**
     * Consulta de cantidad de registros 
     * @author idbohorquez
     * @creacion 23/09/2024
     * @param paramConsulta
     * @return int
     * @throws Exception 
     */
    int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception;
    
    /**
     * Consultar lista de registros
     * @author idbohorquez
     * @creacion 23/09/2024
     * @param paramConsulta
     * @return List<CntjLinea>
     * @throws Exception 
     */
    List<CntjLinea> consultarLista(ParamConsulta paramConsulta) throws Exception;

    /**
     * Guardar nuevo registros
     * @author idbohorquez
     * @return int
     * @creacion 23/09/2024
     * @param objeto
     * @throws Exception 
     */
    int insertar(CntjLinea objeto) throws Exception;

    /**
     * Consultar registro por id
     * @author idbohorquez
     * @param id
     * @return int
     * @creacion 23/09/2024
     * @throws Exception 
     */
    CntjLinea consultar(int id) throws Exception;
    
    /**
     * Actualizar registro
     * @author idbohorquez
     * @param objeto
     * @creacion 23/09/2024
     * @throws Exception 
     */
    void actualizar(CntjLinea objeto) throws Exception;
    
    /**
     * Consultar cantidad registros
     * @author idbohorquez
     * @param idcomite
     * @return 
     * @creacion 23/09/2024
     * @throws Exception 
     */
    int cantidadLineaComite(int idcomite) throws java.lang.Exception;
    
}
