package com.saviasaludeps.savia.negocio.juridico;

import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.juridico.CntjOtrosiAdjunto;
import java.util.List;

/**
 *
 * @author idbohorquez
 */
public interface CtnjOtrosiAdjuntoRemoto {
    
    /**
     * Consulta de cantidad de registros 
     * @author idbohorquez
     * @creacion 01/11/2024
     * @param paramConsulta
     * @return int
     * @throws Exception 
     */
    int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception;
    
    /**
     * Consultar lista de registros
     * @author idbohorquez
     * @creacion 01/11/2024
     * @param paramConsulta
     * @return List<CntjOtrosiAdjunto>
     * @throws Exception 
     */
    List<CntjOtrosiAdjunto> consultarLista(ParamConsulta paramConsulta) throws Exception;

    /**
     * Guardar nuevo registros
     * @author idbohorquez
     * @return int
     * @creacion 01/11/2024
     * @param objeto
     * @throws Exception 
     */
    int insertar(CntjOtrosiAdjunto objeto) throws Exception;

    /**
     * Consultar registro por id
     * @author idbohorquez
     * @param id
     * @return int
     * @creacion 01/11/2024
     * @throws Exception 
     */
    CntjOtrosiAdjunto consultar(int id) throws Exception;
    
    /**
     * Consultar lista de registros
     * @author idbohorquez
     * @param id
     * @creacion 30/09/2024
     * @return List<CntjOtrosiAdjunto>
     * @throws Exception 
     */
    List<CntjOtrosiAdjunto> adjuntosOtrosi(int id) throws java.lang.Exception;
    
    /**
     * Eliminar registro
     * @author idbohorquez
     * @param id
     * @creacion 30/09/2024
     * @return CntjLineaAdjunto
     * @throws Exception 
     */
    CntjOtrosiAdjunto eliminar(int id) throws Exception;
       
}
