/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.especial;

import com.saviasaludeps.savia.dominio.especial.PeIpsPrograma;
import com.saviasaludeps.savia.dominio.especial.PePrograma;
import java.util.List;

/**
 *
 * @author Jaime Andres Olarte
 */
public interface PeIpsProgramaRemoto {
    
    /**
     * Consulta las Ips prestadoras del programa (PeIpsProgramas) por programa (PeProgramas)
     * @param idPrograma
     * @return
     * @throws Exception 
     */
    List<PeIpsPrograma> consultarPorPrograma(int idPrograma) throws Exception;
    
    /**
     * Consulta los registros por Ips Prestadora y por programa
     * @param idPrograma
     * @param idSede
     * @return
     * @throws Exception 
     */
    List<PeIpsPrograma> consultarPorProgramaEIPS(int idPrograma, int idSede) throws Exception;
    
    /**
     * Guarda un objeto de tipo PeIpsProgramas
     * @param obj
     * @return Id de PeIpsPrograma
     * @throws Exception 
     */
    int insertar(PeIpsPrograma obj) throws Exception;
    
    /**
     * Actualizar un objecto de tipo PeIpsPrograma
     * @param obj
     * @throws Exception 
     */
    void actualizar(PeIpsPrograma obj) throws Exception;
    
    /**
     * 
     * @param id
     * @return
     * @throws Exception 
     */
    PeIpsPrograma eliminar(int id) throws Exception;

    
    public List<PeIpsPrograma> consultarPorProgramaActivo(int idPrograma) throws Exception;
    
    /**
     * Consulta programas dado el prestador
     * @param idPrestador
     * @return
     * @throws Exception 
     */
    List<PePrograma> consultarProgramaPorPrestador(int idPrestador) throws Exception;
}
