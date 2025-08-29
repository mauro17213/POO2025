/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.especial;

import com.saviasaludeps.savia.dominio.especial.PeAfiliadoSugerido;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import java.util.List;

/**
 *
 * @author idbohorquez
 */
public interface PeAfiliadoSugeridoRemoto {
    
    /**
     * Consulta de cantidad de registros en una lista
     * @author idbohorquez
     * @creacion 04/11/2022
     * @param paramConsulta
     * @return int
     * @throws Exception 
     */
    int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception;
    
    /**
     * Consultar lista de registros en sugeridos 
     * @author idbohorquez
     * @creacion 04/11/2022
     * @param paramConsulta
     * @return List<PeSugerido>
     * @throws Exception 
     */
    List<PeAfiliadoSugerido> consultarLista(ParamConsulta paramConsulta) throws Exception;
    
    /**
     * Insertar nuevo registro sugerido
     * @author idbohorquez
     * @creacion 18/11/2022
     * @param obj
     * @return int
     * @throws Exception 
     */
    int insertar(PeAfiliadoSugerido obj) throws Exception;
    
    /**
     * Metodo para consultar un registro de sugerido por su id
     * @author idbohorquez
     * @param id
     * @creacion 15/11/2022
     * @return PeAfiliadoSugerido
     * @throws Exception 
     */
    public PeAfiliadoSugerido consultar(int id) throws Exception;
    
    /**
     * Metodo para cambiar estado de sugeridos
     * @author idbohorquez
     * @param obj
     * @creacion 17/11/2022
     * @throws Exception 
     */
    void cambiarEstado(PeAfiliadoSugerido obj) throws Exception;
    
    /**
     * Metodo para consultar sugerido por afiliado y programa, debe estar pendiente o marcado
     * @param idAfiliado
     * @param idPrograma
     * @return
     * @throws Exception 
     */
    PeAfiliadoSugerido consultarAfiliadoPorProgramaActivos(int idAfiliado, int idPrograma) throws Exception;
    
    /**
     * Metodo para rechazar un registro de sugeridos
     * @author idbohorquez
     * @param obj
     * @creacion 14/02/2023
     * @throws Exception 
     */
    void rechazar(PeAfiliadoSugerido obj) throws Exception;
    
    /**
     * Metodo para rechazar los registros de afilaido retirado
     * @author idbohorquez
     * @param obj
     * @creacion 14/02/2023
     * @throws Exception 
     */
    void rechazarAfiliadoretirado(PeAfiliadoSugerido obj) throws Exception;
    
    /**
     * Metodo para consultar listado de sugeridos por id afiliado.
     * @author idbohorquez
     * @param idAfiliado
     * @return 
     * @creacion 11/06/2024
     * @throws Exception 
     */
    List<PeAfiliadoSugerido> consultarSugeridosAfiliado(int idAfiliado) throws Exception;
    
}
