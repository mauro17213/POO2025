/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.autorizacion;

import com.saviasaludeps.savia.dominio.autorizacion.AuGrupoDiagnostico;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import java.util.List;

/**
 *
 * @author Stiven Giraldo
 */
public interface AuGrupoDiagnosticoRemoto {
    
    /**
     * Consulta el objeto dado el id
     * @param id
     * @return
     * @throws Exception 
     */
    AuGrupoDiagnostico consultar(int id) throws Exception;
    
    /**
     * Inserta el objeto
     * @param obj
     * @return
     * @throws Exception 
     */
    int insertar(AuGrupoDiagnostico obj) throws Exception;
    
    /**
     * Actualiza el objeto
     * @param obj
     * @throws Exception 
     */
    void actualizar(AuGrupoDiagnostico obj) throws Exception;
    
    /**
     * Actualiza el objeto
     * @param id
     * @return 
     * @throws Exception 
     */
    AuGrupoDiagnostico eliminar(int id) throws Exception;
    
    /**
     * Listar por Id grupo
     * @param idGrupo
     * @return
     * @throws Exception 
     */
    List<AuGrupoDiagnostico> consultarListaPorIdGrupo(int idGrupo) throws Exception;
    
    /**
     * Cuenta la cantidad de lista dado unos parametros
     * @param paramConsulta
     * @return 
     * @throws Exception 
     */
    int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception;
    
    /**
     * Lista dados unos parametros
     * @param paramConsulta
     * @return 
     * @throws Exception 
     */
    List<AuGrupoDiagnostico> consultarLista(ParamConsulta paramConsulta) throws Exception;
    
    /**
     * Valida la existencia de un diagnostico
     * @param idDiagnostico
     * @param idGrupo
     * @return
     * @throws Exception 
     */
    boolean validarDiagnostico(int idDiagnostico, int idGrupo) throws Exception;
    
}
