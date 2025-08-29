/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.crue;

import com.saviasaludeps.savia.dominio.crue.AuAnexo2;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import java.util.List;

/**
 *
 * @author AlexanderDiaz
 */
public interface AuAnexo2Remoto {
    
    /**
     * Consulta de cantidad de registros en una lista
     * @param paramConsulta
     * @return
     * @throws Exception 
     */
    int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception;
    
    /**
     * Consultar lista de registros
     * @param paramConsulta
     * @return
     * @throws Exception 
     */
    List<AuAnexo2> consultarLista(ParamConsulta paramConsulta) throws Exception;
    
    /**
     * Consulta un registro de RefAnexos9 por id
     * @param id
     * @return AuAnexo2
     * @throws Exception 
     */
    AuAnexo2 consultar(int id) throws Exception;

    int insertar(AuAnexo2 obj) throws Exception;
    
    void actualizar(AuAnexo2 obj) throws Exception;
    
    void actualizarEstado(AuAnexo2 obj) throws Exception;
    
    void actualizarConsecutivo(String consecutivo, Integer id) throws Exception;
    
    AuAnexo2 eliminar(int id) throws Exception;
}
