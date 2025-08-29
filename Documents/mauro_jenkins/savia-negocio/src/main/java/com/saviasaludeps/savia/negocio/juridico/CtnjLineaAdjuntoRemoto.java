package com.saviasaludeps.savia.negocio.juridico;

import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.juridico.CntjLineaAdjunto;
import java.util.List;

/**
 *
 * @author idbohorquez
 */
public interface CtnjLineaAdjuntoRemoto {
    
    /**
     * Consulta de cantidad de registros 
     * @author idbohorquez
     * @creacion 25/09/2024
     * @param paramConsulta
     * @return int
     * @throws Exception 
     */
    int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception;
    
    /**
     * Consultar lista de registros
     * @author idbohorquez
     * @creacion 25/09/2024
     * @param paramConsulta
     * @return List<CntjLineaAdjunto>
     * @throws Exception 
     */
    List<CntjLineaAdjunto> consultarLista(ParamConsulta paramConsulta) throws Exception;

    /**
     * Guardar nuevo registros
     * @author idbohorquez
     * @return int
     * @creacion 25/09/2024
     * @param objeto
     * @throws Exception 
     */
    int insertar(CntjLineaAdjunto objeto) throws Exception;

    /**
     * Consultar registro por id
     * @author idbohorquez
     * @param id
     * @return int
     * @creacion 25/09/2024
     * @throws Exception 
     */
    CntjLineaAdjunto consultar(int id) throws Exception;
    
    /**
     * Consultar lista de registros
     * @author idbohorquez
     * @param idlinea
     * @creacion 30/09/2024
     * @return List<CntjLineaAdjunto>
     * @throws Exception 
     */
    List<CntjLineaAdjunto> adjuntosLineas(int idlinea) throws java.lang.Exception;
    
    /**
     * Eliminar registro
     * @author idbohorquez
     * @param id
     * @creacion 30/09/2024
     * @return CntjLineaAdjunto
     * @throws Exception 
     */
    CntjLineaAdjunto eliminar(int id) throws Exception;
       
}
