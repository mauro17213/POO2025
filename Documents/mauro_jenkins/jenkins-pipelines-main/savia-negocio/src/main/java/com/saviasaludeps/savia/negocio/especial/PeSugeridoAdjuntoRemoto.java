/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.saviasaludeps.savia.negocio.especial;

import com.saviasaludeps.savia.dominio.especial.PeSugeridoAdjunto;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import java.util.List;

/**
 *
 * @author idbohorquez
 */
public interface PeSugeridoAdjuntoRemoto {
    
    
    /**
     * Consulta de cantidad de registros en una lista
     * @author idbohorquez
     * @creacion 15/02/2023
     * @param paramConsulta
     * @return int
     * @throws Exception 
     */
    int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception;
    
    /**
     * Consultar lista de registros en sugeridos 
     * @author idbohorquez
     * @creacion 15/02/2023
     * @param paramConsulta
     * @return List<PeSugeridoAdjunto>
     * @throws Exception 
     */
    List<PeSugeridoAdjunto> consultarLista(ParamConsulta paramConsulta) throws Exception;
    
    
    /**
     * Insertar nuevo registro adjunto sugerido
     * @author idbohorquez
     * @creacion 15/02/2023
     * @param obj
     * @return int
     * @throws Exception 
     */
    int insertar(PeSugeridoAdjunto obj) throws Exception;
    
    /**
     * Consultar lista de registros de adjuntos en sugeridos 
     * @author idbohorquez
     * @creacion 15/02/2023
     * @return List<PeSugeridoAdjunto>
     * @throws Exception 
     */
    List<PeSugeridoAdjunto> listar() throws Exception;
    
    /**
     * Consultar lista de registros de adjuntos por id sugerido
     * @author idbohorquez
     * @param idSugerido
     * @creacion 1/02/2023
     * @return List<PeSugeridoAdjunto>
     * @throws Exception 
     */
    List<PeSugeridoAdjunto> listar(Integer idSugerido) throws Exception;
    
}
